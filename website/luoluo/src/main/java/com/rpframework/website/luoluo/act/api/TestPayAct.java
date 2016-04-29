package com.rpframework.website.luoluo.act.api;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.print.resources.serviceui;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.module.common.pay.alipay.config.AlipayConfig;
import com.rpframework.module.common.pay.alipay.util.AlipayNotify;
import com.rpframework.module.common.pay.wxpay.api.WXpayApi;
import com.rpframework.module.common.pay.wxpay.util.WXpayCore;
import com.rpframework.module.common.springmvc.event.OrderPaySuccEvent;
import com.rpframework.module.common.springmvc.event.vo.OrderPaySuccVO;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.Activitypicture;
import com.rpframework.website.luoluo.domain.Classification;
import com.rpframework.website.luoluo.domain.Monlyjournallist;
import com.rpframework.website.luoluo.domain.Sponsorlis;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.ActivitypictureSercice;
import com.rpframework.website.luoluo.service.ClassificationService;
import com.rpframework.website.luoluo.service.MonlyjournallistService;
import com.rpframework.website.luoluo.service.SponsorService;
import com.rpframework.website.luoluo.service.UserService;
/****
 * 订单
 * ***/
@Controller
@RequestMapping("/api/order")
public class TestPayAct {
	@Resource ActivitypictureSercice activitypictureSercice;
	@Resource ActivityService   activityService ;
	@Resource UserService userService;
	@Resource SponsorService sponsorService;
	@Resource ClassificationService classfica;
	@Resource MonlyjournallistService monlyjournalsService;
	final Logger logger = LoggerFactory.getLogger(getClass());
		//支付
		@RequestMapping(value="/test_pay",produces = "application/json; charset=utf-8")
		public @ResponseBody JsonElement orderList(
				@RequestParam(value="orderId",required=true) String orderId,//订单id
				@RequestParam(value="ClaName",required=true) String ClaName,//订单名
				@RequestParam(value="Activityname",required=true) String Activityname,//订单内容
				@RequestParam(value="money",required=true) Double money,//订单价格										
				@RequestParam(value="memo",required=false) String memo//显示备注
				) {
					JsonObject json = new JsonObject();
					//验证order是否存在   并返回order
					
					//返回密钥、公钥、合作身份者ID、商户账号、回调地址
					String private_key =AlipayConfig.private_key;	
					String public_key = AlipayConfig.ali_public_key;
					String partner = AlipayConfig.partner;
					String seller_id = AlipayConfig.seller_email;
						json.addProperty("succ", true);
						json.addProperty("public_key", public_key);//支付宝公钥
						json.addProperty("private_key", private_key);//商户私钥
						json.addProperty("partner", partner);//合作者PID
						json.addProperty("seller_id", seller_id);//商户支付宝账号
						
						json.addProperty("_input_charset", AlipayConfig.input_charset);//字符编码 
						json.addProperty("sign_type", "RSA");//加密方式 不用更改
						json.addProperty("service", "mobile.securitypay.pay");//固定接口调用
						json.addProperty("notify_url", AlipayConfig.notifyURL);//回调服务端地址
						json.addProperty("code", 0);//回调服务端地址
						
						json.addProperty("out_trade_no", orderId);//订单号
						json.addProperty("subject", ClaName);//订单名
						json.addProperty("body", Activityname);//订单内容
						json.addProperty("total_fee",money);//订单价格
						//json.addProperty("total_fee",0.1);//订单价格
					
						if(memo!=null && "YES".equals(memo.toUpperCase())){
							json.addProperty("public_key=","支付宝公钥");
							json.addProperty("private_key=","商户私钥");
							json.addProperty("partner=","合作者PID");
							json.addProperty("seller_id=","商户支付宝账号");
							
							json.addProperty("_input_charset=", "字符编码");
							json.addProperty("sign_type=", "加密方式 不用更改");
							json.addProperty("service=", "固定接口调用");
							json.addProperty("notify_url=", "回调服务端地址");
							
							json.addProperty("out_trade_no=", "支付宝合作商户唯一订单号");
							json.addProperty("subject=", "订单名");
							json.addProperty("body=", "订单内容");
							json.addProperty("total_fee=", "订单价格");
						}
					return json;
		}

		@SuppressWarnings("rawtypes")
		@RequestMapping("/test_pay_succ")
		public @ResponseBody String TestPaySucc(HttpServletRequest request) throws UnsupportedEncodingException{
			String ret = "";
			//获取支付宝POST过来反馈信息
	
		
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}


			//支付宝交易号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 买家支付宝
			String buyerEmail = new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8");
			// 卖家支付宝
			String seller_email = new String(request.getParameter("seller_email").getBytes("ISO-8859-1"), "UTF-8");
			// 支付时间
			String notify_time = new String(request.getParameter("notify_time").getBytes("ISO-8859-1"), "UTF-8");
			// 交易状态
			String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			// 买家支付宝账户编号
			String buyer_id = new String(request.getParameter("buyer_id").getBytes("ISO-8859-1"), "UTF-8");
			
			//if(AlipayNotify.verify(params)){//验证成功
				double total_fee = NumberUtils.parseDouble(request.getParameter("total_fee"));
				//////////////////////////////////////////////////////////////////////////////////////////

				
				if (trade_status.equals("TRADE_FINISHED")|| trade_status.equals("TRADE_SUCCESS")) {
					//请在这里加上商户的业务逻辑程序代码
		
					
					Activitypicture  cc= activitypictureSercice.selecttrade(out_trade_no);
					
					cc.setTypeOrder(2);
					cc.setType(2);//回调成功 更改状态 
					activitypictureSercice.updatedo(cc);
					
					
					Activity acticity=activityService.selectcal(cc.getSponsorld());
					Sponsorlis sp = sponsorService.seletOne(acticity.getSponsorid());
					User uman=userService.selectmonly(sp.getUserid());
					User myman=userService.selectmonly(cc.getMyld());
					
					Classification dd=classfica.selectcal(acticity.getActivitycategory());
					uman.setPersonalMany(uman.getPersonalMany()*1+total_fee);
					userService.updatedo(uman);
					
					Monlyjournallist mysope=new Monlyjournallist();
					mysope.setMonly(total_fee);
					mysope.setNewtime(System.currentTimeMillis()/1000);
					mysope.setType(1);
					mysope.setUserid(uman.getId());
					if("".equals(myman.getNameNick())){
						mysope.setRemark(myman.getPhone()+"-  汇款");
					}
					mysope.setRemark(myman.getNameNick()+"-  汇款");
					monlyjournalsService.insertdo(mysope);
					
					Monlyjournallist weifu=new Monlyjournallist();
					weifu.setMonly(-total_fee);
					weifu.setNewtime(System.currentTimeMillis()/1000);
					weifu.setType(1);
					weifu.setUserid(myman.getId());
					weifu.setRemark(dd.getClaName()+"- 支付");
					monlyjournalsService.insertdo(weifu);
				
				}else{
					Activitypicture  cc= activitypictureSercice.selecttrade(out_trade_no);
					if(cc !=null){
						boolean flag = activitypictureSercice.delete(cc);
						if(flag){
							System.out.println("删除无效订单成功~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						}
					}
					
				}
				ret = "success";	//请不要修改或删除
				//////////////////////////////////////////////////////////////////////////////////////////
		//	}else{//验证失败
		//		ret = "fail";
		//	}
			return ret;
		} 	
		@RequestMapping("wx_pay_notify")
		public @ResponseBody String wxNotify(@RequestBody String body,
				HttpServletRequest request) throws UnsupportedEncodingException {
			String ret = "<xml><return_code><![CDATA[{}]]></return_code> <return_msg><![CDATA[{}]]></return_msg></xml>";
			System.out.println(body);
			//PAY.info("wxNotify--" + body);
			Map<String, String> retMap = WXpayCore.getRetMap(body);
			boolean retSuccess = WXpayCore.isRetSuccess(retMap);
			
			if(retSuccess) {
				double total_fee = NumberUtils.parseDouble(retMap.get("total_fee"));
				total_fee /= 100; //分转化为元
				
				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				String out_trade_no = retMap.get("out_trade_no");
				
				Activitypicture t = activitypictureSercice.selecttrade(out_trade_no);
				if(t==null){
					System.out.println("===================报名不存在=========================");			
				}else{
					t.setType(2);
					boolean f = activitypictureSercice.update(t);
					if(f){
						System.out.println("===================更改状态成功=========================");			
					}else{
						System.out.println("===================更改状态失败=========================");			
					}
				}
				
				try {
					ret = MessageFormatter.format(ret, "SUCCESS", "OK");
				} catch (Exception e) {
					e.printStackTrace();
					ret = MessageFormatter.format(ret, "FAIL", e.getLocalizedMessage());
				}
				
			} else {
				String out_trade_no = retMap.get("out_trade_no");
				Activitypicture  cc= activitypictureSercice.selecttrade(out_trade_no);
				if(cc !=null){
					boolean flag = activitypictureSercice.delete(cc);
					if(flag){
						System.out.println("校验不通过,删除无效订单成功~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					}
				}
				ret = MessageFormatter.format(ret, "FAIL", "校验不通过!");
			}
			return ret;
		}
}
