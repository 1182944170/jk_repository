package com.rpframework.website.luoluo.act.api;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.module.common.pay.alipay.config.AlipayConfig;
import com.rpframework.module.common.pay.alipay.util.AlipayNotify;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.Activitypicture;
import com.rpframework.website.luoluo.domain.Classification;
import com.rpframework.website.luoluo.domain.Monlyjournals;
import com.rpframework.website.luoluo.domain.Sponsorlis;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.ActivitypictureSercice;
import com.rpframework.website.luoluo.service.ClassificationService;
import com.rpframework.website.luoluo.service.MonlyjournalsService;
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
	@Resource MonlyjournalsService monlyjournalsService;
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
						//json.addProperty("total_fee",money);//订单价格
						json.addProperty("total_fee",0.1);//订单价格
					
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
			logger.info("GoodsGrabTipQuartzJob 处1开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商1理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品11开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品111开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开111抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开11抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开1抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开111抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开111抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开111抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品111开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
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
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			logger.info("GoodsGrabTipQuartzJob 处理商品开rwerw抢通知数 " +params);
			logger.info("GoodsGrabTipQuartzJob 处理商品开1抢通知数 " +params );
			logger.info("GoodsGrabTipQuartzJob 处理商品开rwerew抢通知数 "  +params);
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 "  +params);
			logger.info("GoodsGrabTipQuartzJob 处理商fdasfd品开fdsafwe抢通知数 " +params );
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 "  +params);
		
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			logger.info("GoodsGrabTipQuartzJob 处理商品开rwerw抢通知数 " +out_trade_no);
			logger.info("GoodsGrabTipQuartzJob 处理商品开1抢通知数 " +out_trade_no );
			logger.info("GoodsGrabTipQuartzJob 处理商品开rwerew抢通知数 "  +out_trade_no);
			logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 "  +out_trade_no);
					
			//支付宝交易号
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
			
			if(AlipayNotify.verify(params)){//验证成功
				double total_fee = NumberUtils.parseDouble(request.getParameter("total_fee"));
				//////////////////////////////////////////////////////////////////////////////////////////
				logger.info("GoodsGrabTipQuartzJob 处1开抢2知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商1理商品开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品211开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品111开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开22222抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开111抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品2222开11抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开1抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢222通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开111抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开111抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢222通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开111抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开2222抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品111开抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开222抢通知数 " );
				logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
				if (trade_status.equals("TRADE_FINISHED")|| trade_status.equals("TRADE_SUCCESS")) {
					//请在这里加上商户的业务逻辑程序代码
					logger.info("GoodsGrabTipQuartzJob 处理商3333品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品3333开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品333开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品333抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品33开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开333抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					logger.info("GoodsGrabTipQuartzJob 处理商品开抢通知数 " );
					Activitypicture  cc= activitypictureSercice.selecttrade(out_trade_no);
					
					cc.setTypeOrder(2);
					activitypictureSercice.updatedo(cc);
					
					
					Activity acticity=activityService.selectcal(cc.getSponsorld());
					Sponsorlis sp = sponsorService.seletOne(acticity.getSponsorid());
					User uman=userService.selectmonly(sp.getUserid());
					User myman=userService.selectmonly(cc.getMyld());
					
					Classification dd=classfica.selectcal(acticity.getActivitycategory());
					uman.setPersonalMany(uman.getPersonalMany()*1+total_fee);
					userService.updatedo(uman);
					
					Monlyjournals mysope=new Monlyjournals();
					mysope.setMonly(+total_fee);
					mysope.setNewtime(System.currentTimeMillis()/1000);
					mysope.setType(1);
					mysope.setUserid(uman.getId());
					mysope.setRemark(myman.getNameNick()+"-  汇款");
					monlyjournalsService.insertdo(mysope);
					
					Monlyjournals weifu=new Monlyjournals();
					weifu.setMonly(-total_fee);
					weifu.setNewtime(System.currentTimeMillis()/1000);
					weifu.setType(1);
					weifu.setUserid(myman.getId());
					weifu.setRemark(dd.getClaName()+"- 支付");
					monlyjournalsService.insertdo(weifu);
				
				}
				ret = "success";	//请不要修改或删除
				//////////////////////////////////////////////////////////////////////////////////////////
			}else{//验证失败
				ret = "fail";
			}
			return ret;
		} 	
}
