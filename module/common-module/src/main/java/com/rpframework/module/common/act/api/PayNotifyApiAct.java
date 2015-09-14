package com.rpframework.module.common.act.api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.module.common.pay.PayUtils;
import com.rpframework.module.common.pay.alipay.util.AlipayNotify;
import com.rpframework.module.common.pay.bill99.Bill99Api;
import com.rpframework.module.common.pay.bill99.Bill99Config;
import com.rpframework.module.common.pay.bill99.Pkipair;
import com.rpframework.module.common.pay.wxpay.util.WXpayCore;
import com.rpframework.module.common.springmvc.event.OrderPaySuccEvent;
import com.rpframework.module.common.springmvc.event.vo.OrderPaySuccVO;
import com.rpframework.utils.NumberUtils;

@Controller
@RequestMapping("/api/common/pay_notify")
public class PayNotifyApiAct extends CommonBaseAct {
	public static Logger PAY = LoggerFactory.getLogger("pay");
	Gson gson = new Gson();
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("alipay")
	public @ResponseBody String alipayNotify(HttpServletRequest request) throws UnsupportedEncodingException {
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
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		PAY.info("alipayNotify--" + gson.toJson(params));
		System.out.println("alipayNotify--" + gson.toJson(params));
		
		//JUST TEST.
//		OrderPaySuccVO orderPaySuccVO = new OrderPaySuccVO();
//		orderPaySuccVO.setOrderNo("ddd");
//		orderPaySuccVO.setPayChannel("WX");
//		orderPaySuccVO.setSucc(true);
//		orderPaySuccVO.setRetInfo(new Gson().toJsonTree(params).getAsJsonObject());
//		SpringUtils.publishEvent(new OrderPaySuccEvent(orderPaySuccVO));
//		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号

//		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			double total_fee = NumberUtils.parseDouble(request.getParameter("total_fee"));
			
			OrderPaySuccVO orderPaySuccVO = new OrderPaySuccVO();
			orderPaySuccVO.setOrderId(NumberUtils.parseLong(out_trade_no));
			orderPaySuccVO.setPayChannel("ALIPAY");
			orderPaySuccVO.setSucc(true);
			orderPaySuccVO.setPrice(total_fee);
			orderPaySuccVO.setRetInfo(new Gson().toJsonTree(params).getAsJsonObject());
			
			if(trade_status.equals("TRADE_FINISHED")) {
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				SpringUtils.publishEvent(new OrderPaySuccEvent(orderPaySuccVO));	
				//注意：
				//该种交易状态只在两种情况下出现
				//1、开通了普通即时到账，买家付款成功后。
				//2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
			} else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				SpringUtils.publishEvent(new OrderPaySuccEvent(orderPaySuccVO));
				//注意：
				//该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			ret = "success";	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			ret = "fail";
		}
		
		return ret;
	}
	
	@RequestMapping("wx")
	public @ResponseBody String wxNotify(@RequestBody String body,
			HttpServletRequest request) throws UnsupportedEncodingException {
		String ret = "<xml><return_code><![CDATA[{}]]></return_code> <return_msg><![CDATA[{}]]></return_msg></xml>";
		System.out.println(body);
		PAY.info("wxNotify--" + body);
		Map<String, String> retMap = WXpayCore.getRetMap(body);
		boolean retSuccess = WXpayCore.isRetSuccess(retMap);
		
		if(retSuccess) {
			double total_fee = NumberUtils.parseDouble(retMap.get("total_fee"));
			total_fee /= 100; //分转化为元
			
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			OrderPaySuccVO orderPaySuccVO = new OrderPaySuccVO();
			String out_trade_no = retMap.get("out_trade_no");
			
			orderPaySuccVO.setPrice(total_fee);
			orderPaySuccVO.setOrderId(NumberUtils.parseLong(out_trade_no));
			orderPaySuccVO.setPayChannel("WX");
			orderPaySuccVO.setSucc(true);
			orderPaySuccVO.setRetInfo(new Gson().toJsonTree(retMap).getAsJsonObject());
			
			try {
				SpringUtils.publishEvent(new OrderPaySuccEvent(orderPaySuccVO));
				ret = MessageFormatter.format(ret, "SUCCESS", "OK");
			} catch (Exception e) {
				e.printStackTrace();
				ret = MessageFormatter.format(ret, "FAIL", e.getLocalizedMessage());
			}
			
		} else {
			ret = MessageFormatter.format(ret, "FAIL", "校验不通过!");
		}
		return ret;
	}
	
	@RequestMapping("bill99")
	public @ResponseBody String bill99Notify(HttpServletRequest request) throws UnsupportedEncodingException {
		PAY.info("bill99Notify start--");
		//人民币网关账号，该账号为11位人民币网关商户编号+01,该值与提交时相同。
		String merchantAcctId = request.getParameter("merchantAcctId");
		String key = "";//  A3SSXF384EZBW8Q3
		//网关版本，固定值：v2.0,该值与提交时相同。
		String version = request.getParameter("version");
		//语言种类，1代表中文显示，2代表英文显示。默认为1,该值与提交时相同。
		String language = request.getParameter("language");
		//签名类型,该值为4，代表PKI加密方式,该值与提交时相同。
		String signType = request.getParameter("signType");
		//支付方式，一般为00，代表所有的支付方式。如果是银行直连商户，该值为10,该值与提交时相同。
		String payType = request.getParameter("payType");
		//银行代码，如果payType为00，该值为空；如果payType为10,该值与提交时相同。
		String bankId = request.getParameter("bankId");
		//商户订单号，该值与提交时相同。
		String orderId = request.getParameter("orderId");
		//订单提交时间，格式：yyyyMMddHHmmss，如：20071117020101,该值与提交时相同。
		String orderTime = request.getParameter("orderTime");
		//订单金额，金额以“分”为单位，商户测试以1分测试即可，切勿以大金额测试,该值与支付时相同。
		String orderAmount = request.getParameter("orderAmount");
		String bindCard = request.getParameter("bindCard");
		String bindMobile = request.getParameter("bindMobile");
		System.out.println(bindCard+"     "+bindMobile);
		// 快钱交易号，商户每一笔交易都会在快钱生成一个交易号。
		String dealId = request.getParameter("dealId");
		//银行交易号 ，快钱交易在银行支付时对应的交易号，如果不是通过银行卡支付，则为空
		String bankDealId = request.getParameter("bankDealId");
		//快钱交易时间，快钱对交易进行处理的时间,格式：yyyyMMddHHmmss，如：20071117020101
		String dealTime = request.getParameter("dealTime");
		//商户实际支付金额 以分为单位。比方10元，提交时金额应为1000。该金额代表商户快钱账户最终收到的金额。
		String payAmount = request.getParameter("payAmount");
		//费用，快钱收取商户的手续费，单位为分。
		String fee = request.getParameter("fee");
		//扩展字段1，该值与提交时相同。
		String ext1 = request.getParameter("ext1");
		//扩展字段2，该值与提交时相同。
		String ext2 = request.getParameter("ext2");
		//处理结果， 10支付成功，11 支付失败，00订单申请成功，01 订单申请失败
		String payResult = request.getParameter("payResult");
		//错误代码 ，请参照《人民币网关接口文档》最后部分的详细解释。
		String errCode = request.getParameter("errCode");
		//签名字符串 
		String signMsg = request.getParameter("signMsg");
		
		String merchantSignMsgVal = "";
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal,"merchantAcctId", merchantAcctId);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "version",version);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "language",language);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "signType",signType);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "payType",payType);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "bankId",bankId);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "orderId",orderId);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "orderTime",orderTime);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "orderAmount",orderAmount);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "bindCard",bindCard);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "bindMobile",bindMobile);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "dealId",dealId);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "bankDealId",bankDealId);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "dealTime",dealTime);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "payAmount",payAmount);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "fee", fee);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "ext1", ext1);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "ext2", ext2);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "payResult",payResult);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "errCode",errCode);
		merchantSignMsgVal = Bill99Api.appendParam(merchantSignMsgVal, "key", key);
		
		System.out.println("merchantSignMsgVal--" + merchantSignMsgVal);
		System.out.println("signMsg--" + signMsg);
		
		PAY.info("merchantSignMsgVal--" + merchantSignMsgVal);
		PAY.info("signMsg--" + signMsg);
		Pkipair pkipair = new Pkipair();
		boolean flag = pkipair.enCodeByCer(merchantSignMsgVal, signMsg);
//		String signMsg1 = MD5Util.md5Hex(merchantSignMsgVal.getBytes("utf-8")).toUpperCase();
//	    boolean flag = signMsg1.equalsIgnoreCase(signMsg);
		int rtnOK =0;
	  	String rtnUrl="";
	  	PAY.info("flag--" + flag);
	  	System.out.println("flag--" + flag);
	  	
	  	if(flag){
	  		switch(Integer.parseInt(payResult)) {
	  			case 10:
  					/*
  					此处商户可以做业务逻辑处理
  					*/
  					rtnOK=1;
  					//以下是我们快钱设置的show页面，商户需要自己定义该页面。
  					rtnUrl = PayUtils.getNotifyUrl(Bill99Config.showUrl) + "?msg=success";
  					
  					//
  					//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
  					OrderPaySuccVO orderPaySuccVO = new OrderPaySuccVO();
  					double total_fee = NumberUtils.parseDouble(orderAmount);
  					total_fee /= 100; //分转化为元
  					
  					JsonObject json = new JsonObject();
  					json.addProperty("signMsg", signMsg);
  					json.addProperty("merchantSignMsgVal", merchantSignMsgVal);
  					
  					orderPaySuccVO.setPrice(total_fee);
  					orderPaySuccVO.setOrderId(NumberUtils.parseLong(orderId));
  					orderPaySuccVO.setPayChannel("BILL99");
  					orderPaySuccVO.setSucc(true);
  					orderPaySuccVO.setRetInfo(json);
  					
  					SpringUtils.publishEvent(new OrderPaySuccEvent(orderPaySuccVO));
  					break;
	  			default:
  					rtnOK=1;
  					//以下是我们快钱设置的show页面，商户需要自己定义该页面。
					rtnUrl = PayUtils.getNotifyUrl(Bill99Config.showUrl) + "?msg=false";
  					break;
	  		}
	  	}
	  	else
	  	{
	  		rtnOK=1;
	  		//以下是我们快钱设置的show页面，商户需要自己定义该页面。
			rtnUrl = PayUtils.getNotifyUrl(Bill99Config.showUrl) + "?msg=error";
	  	}
	  	
	  	String ret = "<result>{}</result> <redirecturl>{}</redirecturl>";
	  	return MessageFormatter.format(ret, rtnOK, rtnUrl);
	}
	
	
}
