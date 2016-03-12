package com.rpframework.website.luoluo.act.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import com.rpframework.core.utils.TagUtils;
import com.rpframework.module.common.pay.wxpay.api.WXpayApi;
import com.rpframework.module.common.pay.wxpay.util.WXpayCore;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.Activitypicture;
import com.rpframework.website.luoluo.domain.Classification;
import com.rpframework.website.luoluo.domain.Myimpression;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.ActivitypictureSercice;
import com.rpframework.website.luoluo.service.ClassificationService;
import com.rpframework.website.luoluo.service.MyimpressionService;
import com.rpframework.website.luoluo.service.UserService;


@Controller
@RequestMapping("api/picture")
public class ApiActivitypictureAct extends BaseAct{
		Gson gson = new Gson();
	@Resource ActivitypictureSercice activitypictureSercice;
	@Resource ActivityService   activityService ;
	@Resource ClassificationService   classiftionservice;
	@Resource UserService  userService;
	@Resource MyimpressionService  myimpressionService;
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
			@RequestParam(required=false) String sponsorld,//活动id
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String phone,
			@RequestParam(required=false) String emergencyphone,
			@RequestParam(required=false) String emergencyname,
			@RequestParam(required=false) String oldboy,
			@RequestParam(required=false) String chindenboy,
			@RequestParam(required=false) String grilexpense,//女生人数
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
			Activitypicture Activitypi=activitypictureSercice.seletzzle(currUser.getId(), sponsorlds);
			long i=System.currentTimeMillis()/1000;
			if(activity.getStarttime()<i ||activity.getStarttime()==i){
				throw new APICodeException(-20, "活动已经开始不允许报名");
			}
			if(Activitypi==null){
				Activitypi=new Activitypicture();
				Activity a = activityService.select(sponsorld);
				if(a!=null){
					if(StringUtils.isNotBlank(grilexpense)){
						a.setJoinNumber(a.getJoinNumber()+Integer.valueOf(grilexpense));
						activityService.update(a);
					}
				}
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
				Classification cole=classiftionservice.selectcal(activity.getActivitycategory());
				counterFee=(int) (cc*cole.getProcedures()/100);
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
			}
			JsonObject orderjson=new JsonObject();
			if(activity.getZhuangttai()==0){	
				if(NumberUtils.isValid(typeMonely)){
					if(typeMonely == 1){
						TestPayAct ss=new TestPayAct();
						String memo = null;
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
			}else{
				orderjson.addProperty("zhuangtai", 1);
			}
		
		if(bFlag){
		
			orderjson.addProperty("succ", true);
		} else { // 添加失败
			orderjson.addProperty("error", false);
		}
		return orderjson;
	}
	
	
	
	
	/**
	 * 查询活动下的用户
	 * @param id
	 * @param pager
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("list")
	public @ResponseBody JsonElement list(@RequestParam Integer id,@RequestParam(value="pager",required=false) Pager<Activitypicture> pager,HttpSession session 
			) throws ParserException, InterruptedException{
 		if(pager==null){
 			pager=new Pager<Activitypicture>();
 		}
 		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
 		pager.getSearchMap().put("sponserid", String.valueOf(id));
 		pager.getSearchMap().put("myld", currUser.getId().toString());
 		pager.getSearchMap().put("typeOrder", 2+"");
 		pager.getSearchMap().put("type", 1+"");
 		activitypictureSercice.getpager(pager);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<Activitypicture> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		
		for (Activitypicture act : list) {
			Myimpression myimpression =myimpressionService.selectone(currUser.getId(),act.getMyld());
			
			User usr=userService.selectOnlyOne(act.getMyld());
			if(myimpression==null){
				usr.setType(0);
			}else{
				usr.setType(1);
			}
			Activitypicture activity= activitypictureSercice.selectone(act.getId());
			act.setActivitypicture(activity);
			act.setUser(usr);
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
	/**
	 * 查询我的订单
	 * @param id
	 * @param pager
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("listorz")
	public @ResponseBody JsonElement listorz(@RequestParam(value="pager",required=false) Pager<Activitypicture> pager 
			,HttpSession session) throws ParserException, InterruptedException{
		if(pager==null){
			pager=new Pager<Activitypicture>();
		}
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		pager.getSearchMap().put("myld", String.valueOf(currUser.getId()));
		pager.getSearchMap().put("typeOrder", 2+"");
		pager.getSearchMap().put("type", 1+"");
		
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
			Activity acc=activityService.selectcal(act.getSponsorld());
			jsonObj.addProperty("cover", TagUtils.getFileFullPath(acc.getCover()));
			jsonObj.addProperty("activitynumber", acc.getActivitynumber());
			jsonObj.addProperty("activitycategory", acc.getActivitycategory());
			jsonObj.addProperty("activityname", acc.getActivityname());
			jsonObj.addProperty("activitylocation", acc.getActivitylocation());
			jsonObj.addProperty("number", acc.getNumber());
			jsonObj.addProperty("children_expense", acc.getChildren_expense());
			jsonObj.addProperty("old_expense", acc.getOld_expense());
			jsonObj.addProperty("gril_expense", acc.getGril_expense());
			jsonObj.addProperty("activitycontent", acc.getActivitycontent());
			jsonObj.addProperty("starttime", acc.getStarttime());
			jsonObj.addProperty("outtime", acc.getOuttime());
			jsonObj.addProperty("nowforetime", acc.getNowforetime());
			jsonObj.addProperty("lat", acc.getLat());
			jsonObj.addProperty("lng", acc.getLng());
			jsonObj.addProperty("starttime", acc.getStarttime());
			
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
	public @ResponseBody JsonElement outgotu(
			@RequestParam(required=false) Integer id,HttpSession session
			) throws ParserException, InterruptedException{
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		
		Activitypicture cc=activitypictureSercice.selectone(id);
		if(cc == null){
			throw new APICodeException(-7, "订单不存在!");
		}	
		Activity activity = activityService.selectcal(cc.getSponsorld());
		if(activity == null){
			throw new APICodeException(-8, "活动不存在!");
		}
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
	 * 取消详情订单
	 * @param id
	 * @param session
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("/billcancel")
	public @ResponseBody JsonElement billcancel(
			@RequestParam(required=false) Integer activityid,
			HttpSession session
			) throws ParserException, InterruptedException{
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		JsonObject json=new JsonObject();
		try {
			Activitypicture  cc=activitypictureSercice.selecttwo(currUser.getId(),activityid);
			if(cc==null){
				json.addProperty("type",0);
				json.addProperty("succ", true);
			}else{
				json.addProperty("type",1);
				json.addProperty("succ", true);
			}
		} catch (Exception e) {
			json.addProperty("succ", false);
			json.addProperty("msg", e.getMessage());
			// TODO: handle exception
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

