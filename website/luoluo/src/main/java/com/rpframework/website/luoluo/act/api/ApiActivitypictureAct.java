package com.rpframework.website.luoluo.act.api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.parser.ParserException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.module.common.pay.alipay.config.AlipayConfig;
import com.rpframework.module.common.pay.alipay.sign.RSA;
import com.rpframework.module.common.pay.alipay.util.AlipayNotify;
import com.rpframework.module.common.pay.wxpay.api.WXpayApi;
import com.rpframework.module.common.pay.wxpay.util.WXpayCore;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.Activitypicture;
import com.rpframework.website.luoluo.domain.Classification;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.ActivitypictureSercice;
import com.rpframework.website.luoluo.service.ClassificationService;


@Controller
@RequestMapping("api/picture")
public class ApiActivitypictureAct extends BaseAct{
		Gson gson = new Gson();
	@Resource ActivitypictureSercice activitypictureSercice;
	@Resource ActivityService   activityService ;
	@Resource ClassificationService   classiftionservice;
	/**
	 * 添加报名信息
	 * 提交订单
	 * @param name
	 * @param phone
	 * @param emergencyphone
	 * @param emergencyname
	 * @param oldboy
	 * @param chindenboy
	 * @param monely
	 * @param mood
	 * @param insure
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public  @ResponseBody JsonElement add(
			@RequestParam(required=false) String sponsorld,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String phone,
			@RequestParam(required=false) String emergencyphone,
			@RequestParam(required=false) String emergencyname,
			@RequestParam(required=false) String oldboy,
			@RequestParam(required=false) String chindenboy,
			@RequestParam(required=false) String grilexpense,
			@RequestParam(required=false) double money,
			@RequestParam(required=false) String mood,
			@RequestParam(required=false) String insure, //投保证件
			@RequestParam(required=false) String insurename, //投保姓名
			@RequestParam(required=false) Integer typeMonely,
			@RequestParam(required=false) Integer type,
			HttpSession session )throws Exception{
			boolean bFlag = false;
			User currUser = getSessionUser(session);
			if(currUser == null){
				throw new APICodeException(-4, "你还没登陆!");
			}	
		
			int sponsorlds=Integer.parseInt(sponsorld);
			Activity activity = activityService.selectcal(sponsorlds);
			Classification  classi = classiftionservice.selectcal(activity.getActivitycategory());
				Activitypicture Activitypi=new Activitypicture();
				Activitypi.setSponsorld(sponsorlds);
				Activitypi.setMyld(currUser.getId());
				Activitypi.setName(name);
				Activitypi.setPhone(phone);
				Activitypi.setEmergencyname(emergencyname);
				Activitypi.setEmergencyphone(emergencyphone);
				Activitypi.setOldboy(oldboy);
				Activitypi.setChindenboy(chindenboy);
				Activitypi.setGrilexpense(grilexpense);
				Activitypi.setMonely(money);
				double cc=money;
				double counterFee=0;
				if(activity.getType()==1){
					Classification cole=classiftionservice.selectcal(activity.getActivitycategory());
					counterFee=(int) (cc*cole.getProcedures()/100);
				}
				double actualamount=cc-counterFee;
				Activitypi.setCounterFee(counterFee);
				Activitypi.setActualamount(actualamount);
				Activitypi.setMood(mood);
				Activitypi.setInsure(insure);
				Activitypi.setInsurenName(insurename);
				Activitypi.setTypeMonely(typeMonely);
				Activitypi.setNewtime(System.currentTimeMillis()/1000);
				Activitypi.setType(activity.getType());
				Activitypi.setTypeOrder(1);
				activitypictureSercice.insertdo(Activitypi);
				Activitypi.setOrdernumber(DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random(5)+Activitypi.getId());
				activitypictureSercice.updatedo(Activitypi);
			
				
		if(NumberUtils.isValid(typeMonely)){
			if(typeMonely == 1){
				TestPayAct ss=new TestPayAct();
				String memo = null;
				bFlag = activitypictureSercice.bagPay(currUser.getId(), Activitypi.getId(),activity);
				return ss.orderList(Activitypi.getOrdernumber() , activity.getActivityname(),classi.getClaName(), money,memo);
				///支付宝支付
			} else if (typeMonely == 2) {
				
				bFlag = activitypictureSercice.bagPay(currUser.getId(), Activitypi.getId(),activity);
			
			} else if(typeMonely == 3){
			/*
				//微信支付
				 System.out.println(" =============》预付款开始:");
			        Map<String, String> retMap = wxzhifu(activity,Activitypi,osele,classi);;
			        System.out.println(" =============》预付款结束:");
			        System.out.println(WXpayCore.isRetSuccess(retMap)); // 判断统一下单（预支付）接口是否成功
			        if (WXpayCore.isRetSuccess(retMap)) {
			            // 预支付成功，组装真正支付需要的参数，返回给app使用
			            System.out.println(" =============》组装app使用参数:");
			            System.out.println(WXpayApi.makePaymentMap(retMap));
			            bFlag = activitypictureSercice.bagPay(currUser.getId(), Activitypi.getId(),activity);
			        } else {
			            System.out.println(WXpayCore.getErrMsg(retMap));
			        }
*/			}else{
				throw new APICodeException(-1, "支付类型错误...");
			}
		} else {
			throw new APICodeException(-2, "请选择支付类型...");
		}
		
		JsonObject orderjson=new JsonObject();
		if(bFlag){
			orderjson.addProperty("succ", true);
		} else { // 添加失败
			orderjson.addProperty("error", false);
		}
		return orderjson;
	}
	
	/**
	 * 查询我的订单
	 * @param id
	 * @param pager
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("list")
	public @ResponseBody JsonElement list(@RequestParam Integer id,@RequestParam(value="pager",required=false) Pager<Activitypicture> pager 
			) throws ParserException, InterruptedException{
 		if(pager==null){
 			pager=new Pager<Activitypicture>();
 		}
 		pager.getSearchMap().put("sponserid", String.valueOf(id));
 		activitypictureSercice.getpager(pager);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<Activitypicture> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Activitypicture act : list) {
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
	/**
	 * 取消订单
	 * @param id
	 * @param session
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("outgotu")
	public @ResponseBody JsonElement quxiao(
			@RequestParam(required=false) Integer id,HttpSession session
			) throws ParserException, InterruptedException{
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		
		Activitypicture cc=activitypictureSercice.selectone(id);
		Activity activity = activityService.selectcal(cc.getSponsorld());
		cc.setType(2);
		boolean bFlag = activitypictureSercice.baggo(currUser,cc,activity);
		JsonObject json=new JsonObject();
		if(bFlag){
			activitypictureSercice.updatedo(cc);
			json.addProperty("succ", true);
		} else { // 添加失败
			json.addProperty("error", false);
		}
		return json;
	}
	/**
	 * 查询详细信息 报名的 
	 * @param id
	 * @param pager
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("listone")
	public @ResponseBody JsonElement cassifcationlist(@RequestParam Integer id
			,HttpSession session) throws ParserException, InterruptedException{
	
		JsonObject json = new JsonObject();
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		Activitypicture cc=activitypictureSercice.selectone(id);
		json.addProperty("id", cc.getId());
		json.addProperty("username", cc.getName());
		json.addProperty("emergencyname", cc.getEmergencyname());
		json.addProperty("phone", cc.getPhone());
		json.addProperty("emergencyname", cc.getEmergencyname());
		json.addProperty("oldboy", cc.getOldboy());
		json.addProperty("chindenboy", cc.getChindenboy());
		json.addProperty("monely", cc.getMonely());
		json.addProperty("type", cc.getType());
		String [] arr = cc.getInsure().split(",");
		String [] forname = cc.getInsurenName().split(",");
	

		for(String orr:arr){
			json.addProperty("insure", orr);
		}
		for(String foreal:forname){
			json.addProperty("insureName", foreal);
		}
		
		return json;
	}
	
	
	//支付宝返回参数
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
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号
		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			Activitypicture apfoled = activitypictureSercice.selecttrade(out_trade_no);
			apfoled.setTypeOrder(2);
			activitypictureSercice.updatedo(apfoled);
		
			ret = "success";	//请不要修改或删除
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			ret = "fail";
		}
		return ret;
	} 
	/**
	 * 微信支付
	 * @param activity
	 * @param activitypi
	 * @param osele
	 * @param classi
	 * @return
	 */
	public Map<String, String> wxzhifu( Activity activity,Activitypicture activitypi,String osele,Classification classi) {
		 Map<String, String> testMap = new HashMap<String, String>();
	        testMap.put("device_info", osele); // 设备号
	        testMap.put("body",classi.getClaName()); // 商品描述
	        testMap.put("detail", activity.getActivityname()); // 商品详情
	        testMap.put("attach", ""); // 附加数据
	        testMap.put("out_trade_no", activitypi.getOrdernumber()); // 商户订单号
	        testMap.put("total_fee", 0.1+""); // 总金额
	        testMap.put("spbill_create_ip", "192.168.0.1"); // 终端IP
	        testMap.put("time_start", activitypi.getNewtime()+""); // 交易起始时间
	        testMap.put("time_expire", ""); // 交易结束时间
	        testMap.put("goods_tag", ""); // 商品标记
	        testMap.put("notify_url", "https://baidu.com"); // 通知地址
	        testMap.put("trade_type", "APP"); // 交易类型
	        testMap.put("product_id", ""); // 商品ID
	        testMap.put("openid", ""); // 用户标识
	        Map<String, String> retMap = WXpayApi.unifiedOrderRetMap(testMap);

	  /*      Map<String, String> retMap = WXpayApi.unifiedOrderRetMap(testMap);
	        System.out.println(" =============》查询订单开始:");
	        Map<String, String> queRet = testOrderQuery(activitypi.getOrdernumber());
	        System.out.println(" =============》查询订单结束:");
	        System.out.println(WXpayCore.isRetSuccess(queRet));
	        
	        if (WXpayCore.isRetSuccess(retMap)) {
	            // 预支付成功，组装真正支付需要的参数，返回给app使用
	            System.out.println(" =============》组装app使用参数:");
            System.out.println(WXpayApi.makePaymentMap(retMap));
	        } else {
            System.out.println(WXpayCore.getErrMsg(retMap));
        }*/
	        return retMap;
	}
	 public static Map<String, String> testOrderQuery(String aa) {
	        Map<String, String> testMap = new HashMap<String, String>();
	        testMap.put("out_trade_no", aa); // 商户订单号
	        System.out.println(testMap);
	        Map<String, String> retMap = WXpayApi.orderQueryRetMap(testMap);
	        System.out.println(retMap);
	        WXpayCore.isRetSuccess(retMap);
	      //  boolean retSuccess = WXpayCore.isRetSuccess(retMap);
	        return retMap;
	    }
	 
	 
	 
}

