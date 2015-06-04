package com.rpframework.website.edongwang.act.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rpframework.core.BaseAct;
import com.rpframework.core.api.FileService;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.core.utils.GsonUtils;
import com.rpframework.core.utils.MessageFormatter;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.module.common.service.SMSService;
import com.rpframework.utils.AlgorithmEnum;
import com.rpframework.utils.AlgorithmUtils;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.House;
import com.rpframework.website.edongwang.domain.LeaveMessage;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.domain.UserBankCard;
import com.rpframework.website.edongwang.domain.UserMoney;
import com.rpframework.website.edongwang.domain.UserMoneyLog;
import com.rpframework.website.edongwang.domain.UserSalesman;
import com.rpframework.website.edongwang.domain.UserScore;
import com.rpframework.website.edongwang.domain.UserScoreLog;
import com.rpframework.website.edongwang.domain.UserScoreShopLog;
import com.rpframework.website.edongwang.domain.UserTakeCash;
import com.rpframework.website.edongwang.service.LeaveMessageService;
import com.rpframework.website.edongwang.service.UserBankCardService;
import com.rpframework.website.edongwang.service.UserMoneyLogService;
import com.rpframework.website.edongwang.service.UserMoneyService;
import com.rpframework.website.edongwang.service.UserSalesmanService;
import com.rpframework.website.edongwang.service.UserScoreLogService;
import com.rpframework.website.edongwang.service.UserScoreService;
import com.rpframework.website.edongwang.service.UserScoreShopLogService;
import com.rpframework.website.edongwang.service.UserService;
import com.rpframework.website.edongwang.service.UserTakeCashService;
import com.rpframework.website.edongwang.utils.EConstants;

@Controller
@RequestMapping("/api/user")
public class UserApiAct extends BaseAct {
	Gson gson = new Gson();
	@Resource UserService userService;
	@Resource FileService fileService;
	@Resource SMSService smsService;
	@Resource UserSalesmanService userSalesmanService;
	@Resource UserBankCardService userBankCardService;
	@Resource LeaveMessageService leaveMessageService;
	@Resource UserScoreShopLogService userScoreShopLogService;
	@Resource UserScoreLogService userScoreLogService;
	@Resource UserScoreService userScoreService;
	@Resource UserMoneyLogService userMoneyLogService;
	@Resource UserTakeCashService userTakeCashService;
	@Resource UserMoneyService userMoneyService;
	
	@RequestMapping("userdata")
	public @ResponseBody JsonElement execute(HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		JsonObject json = new JsonObject();
		json.addProperty("id", user.getId());
		json.addProperty("contact", user.getContact());
		json.addProperty("sex", user.getSex());
		json.addProperty("realName", user.getRealName());
		json.addProperty("headImg", TagUtils.getFileFullPath(user.getHeadImg()));
		json.addProperty("countyCode", user.getCountyCode());
		
		int salesmanState = 0;
		if(user.getIsSalesman() != 1) {//
			if(user.getUserSalesman() == null) {
			} else {
				if(user.getUserSalesman().getState() == -1) {
					salesmanState = -1;
				} else {
					salesmanState = 2;
				}
			}
		} else {
			salesmanState = 1;
			
			JsonObject salesmanJson = new JsonObject();
			JsonObject houseJson = new JsonObject();
			salesmanJson.add("house", houseJson);
			json.add("salesman", salesmanJson);
			salesmanJson.addProperty("credentialsImg", TagUtils.getFileFullPath(user.getUserSalesman().getCredentialsImg()));
			salesmanJson.addProperty("recordCreateTime", user.getUserSalesman().getRecordCreateTime());
			
			houseJson.addProperty("id", user.getUserSalesman().getHouse().getId());
			houseJson.addProperty("name", user.getUserSalesman().getHouse().getName());
		}
		
		json.addProperty("salesmanState", salesmanState);
		
		List<UserBankCard> cards = userBankCardService.getCardsByUserId(user.getId());
		if(CollectionUtils.isNotEmpty(cards)) {
			UserBankCard userBankCard = cards.get(0);
			json.add("userBankCard", packageUserBankCard(userBankCard));
		}
		
		UserMoney userMoney = userMoneyService.getUserMoney(user.getId());
		json.add("userMoney", gson.toJsonTree(userMoney));
		return json;
	}
	
	private JsonObject packageUserBankCard(UserBankCard userBankCard) {
		JsonObject uBankCardJson = new JsonObject();
		uBankCardJson.addProperty("id", userBankCard.getId());
		uBankCardJson.addProperty("account", userBankCard.getAccount());
		uBankCardJson.addProperty("name", userBankCard.getName());
		uBankCardJson.addProperty("bankId", userBankCard.getCfgBank().getId());
		uBankCardJson.addProperty("address", userBankCard.getAddress());
		return uBankCardJson;
	}
	
	@RequestMapping("/sendsms_for_change_contact")
	public @ResponseBody JsonElement sendSMS4ChangeContact(@RequestParam(required=false) String contact, HttpSession session, HttpServletRequest request) {
		if(StringUtils.isBlank(contact)) {
			throw new IllegalArgumentException("非法参数!");
		}
		
		String verifyCode = String.valueOf(NumberUtils.random(6));
		String sendContent = DictionarySettingUtils.getParameterValue("sendsms.change_contact");
		if(StringUtils.isBlank(sendContent)) {
			sendContent =  "本次修改手机号验证码:{}，请牢记";
		}
		sendContent = MessageFormatter.format(sendContent, verifyCode);
		
		boolean flag = smsService.sendSMS(EConstants.ChannelType.SEND_SMS_CHANGE_CONTACT_CHANNEL_TYPE, contact, verifyCode, sendContent);
		if(!flag) {
			throw new IllegalArgumentException("短信发送失败!");
		}
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
	
	@RequestMapping("/change_pwd")
	public @ResponseBody JsonElement changePwd(@RequestParam String oldPassword,@RequestParam String newPassword, HttpSession session, HttpServletRequest request) {
		User user = getSessionUser(session);
		boolean flag = userService.changePassword(user, oldPassword, newPassword);
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
	
	@RequestMapping("/change_contact")
	public @ResponseBody JsonElement changeContact(@RequestParam String newContact,@RequestParam String verifyCode, HttpSession session, HttpServletRequest request) {
		User user = getSessionUser(session);
		if(StringUtils.isBlank(newContact) || StringUtils.isBlank(verifyCode)) {
			throw new IllegalArgumentException("非法参数!");
		}
		
		String oldContact = user.getContact();
		if(StringUtils.equals(newContact, oldContact)) {
			throw new IllegalArgumentException("手机号相同!");
		}
		if(!smsService.checkVerifyCode(EConstants.ChannelType.SEND_SMS_CHANGE_CONTACT_CHANNEL_TYPE, oldContact, verifyCode)) {
			throw new IllegalArgumentException("验证码不正确!");
		}
		user.setContact(newContact);
		userService.update(user);
		smsService.setVerifyCodeVaild(EConstants.ChannelType.SEND_SMS_FORGET_PASSWORD_CHANNEL_TYPE, oldContact);
		
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
	
	/**
	 * 查看我的楼盘协议
	 * 只有二级会员才能查看
	 * @param message
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/view_my_house_protocol")
	public @ResponseBody JsonElement viewMyHouseProtocol(HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		if(user.getIsSalesman() != 1) {
			throw new IllegalArgumentException("只有二级会员才能查看我的楼盘协议!");
		}
		
		String protocol = user.getUserSalesman().getHouse().getProtocol();
		JsonObject json = new JsonObject();
		json.addProperty("protocol", protocol);
		return json;
	}
	
	
	@RequestMapping("/leave_msg")
	public @ResponseBody JsonElement leaveMsg(@RequestParam String message, HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		LeaveMessage leaveMessage = new LeaveMessage();
		leaveMessage.setMessage(message);
		leaveMessage.setRecordCreateTime(System.currentTimeMillis() / 1000);
		leaveMessage.setUserId(user.getId());
		boolean flag = leaveMessageService.insert(leaveMessage);
		
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
	
	@RequestMapping("/change_head_img")
	public @ResponseBody JsonElement changeHeadImg(@RequestParam(value="headImgFile", required=false) CommonsMultipartFile headImgFile, HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		if(headImgFile != null && headImgFile.getSize() > 0) {
			try {
				String relativelyPath = "resources/head/" + NumberUtils.random(6) + headImgFile.getOriginalFilename();
				fileService.saveFile(headImgFile.getInputStream(), relativelyPath);
				user.setHeadImg(relativelyPath);
				userService.update(user);
				
				JsonObject json = new JsonObject();
				json.addProperty("succ", true);
				return json;
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		}
		
		throw new IllegalArgumentException("参数错误!");
	}
	
	@RequestMapping("/change_sex")
	public @ResponseBody JsonElement changeSex(@RequestParam(value="sex", required=false) Integer sex, HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		user.setSex(sex);
		userService.update(user);
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
	
	@RequestMapping("/change_real_name")
	public @ResponseBody JsonElement changeRealName(@RequestParam String realName,  HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		user.setRealName(realName);
		userService.update(user);
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
	
	/*@RequestMapping("/change_county_code")
	public @ResponseBody JsonElement changeCountyCode(@RequestParam String countyCode,HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		user.setCountyCode(countyCode);
		userService.update(user);
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}*/
	
	@RequestMapping("/change_password")
	public @ResponseBody JsonElement changePassword(@RequestParam String oldPassword, @RequestParam(required=false) String newPassword, HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		
		String oldPasswordMD5 = AlgorithmUtils.encodePassword(oldPassword, AlgorithmEnum.MD5);
		if(!StringUtils.equals(oldPasswordMD5, user.getPassword())) {
			throw new IllegalArgumentException("原密码错误!");
		}
		String newPasswordMD5 = AlgorithmUtils.encodePassword(newPassword, AlgorithmEnum.MD5);
		
		user.setPassword(newPasswordMD5);
		userService.update(user);
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
	
	@RequestMapping("/apply_salesman")
	public @ResponseBody JsonElement applySalesman(@RequestParam(value="credentialsImgFile", required=false) CommonsMultipartFile credentialsImgFile, 
			@RequestParam Integer houseId,
			@RequestParam String realName,
			HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		if(user.getIsSalesman() ==1) {//
			throw new IllegalArgumentException("你已经是Salesman，请勿再次提交!");
		}
		if(StringUtils.isNotBlank(realName)) {
			user.setRealName(realName);
			userService.update(user);
		}
		
		if(credentialsImgFile != null && credentialsImgFile.getSize() > 0) {
			try {
				String relativelyPath = "resources/credentials/" + NumberUtils.random(6) + credentialsImgFile.getOriginalFilename();
				fileService.saveFile(credentialsImgFile.getInputStream(), relativelyPath);
				boolean falg = true;
				
				House house = new House();
				house.setId(houseId);
				
				UserSalesman userSalesman = new UserSalesman();
				userSalesman.setUserId(user.getId());
				userSalesman.setState(0);
				userSalesman.setHouse(house);
				userSalesman.setCredentialsImg(relativelyPath);
				falg = userSalesmanService.insertOrUpdate(userSalesman);
				
				JsonObject json = new JsonObject();
				json.addProperty("succ", falg);
				return json;
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		}
		
		throw new IllegalArgumentException("参数错误!");
	}
	
	@RequestMapping("/bind_bank_card")
	public @ResponseBody JsonElement bindBankCard(
			@RequestParam Integer bankId,
			@RequestParam String address,
			@RequestParam String account,
			@RequestParam String name,
			HttpSession session, HttpServletRequest request) {
		
		User user = getSessionUser(session);
		
		boolean flag = userBankCardService.bind(user.getId(), name, bankId, account, address);
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
	
	@RequestMapping("/rebind_bank_card")
	public @ResponseBody JsonElement reBindBankCard(
			@RequestParam Integer bankId,
			@RequestParam String address,
			@RequestParam String account,
			@RequestParam String name,
			HttpSession session, HttpServletRequest request) {
		
		User user = getSessionUser(session);
		
		boolean flag = userBankCardService.reBind(user.getId(), name, bankId, account, address);
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
	
	@RequestMapping("/apply_take")
	public @ResponseBody JsonElement applyTake(
			@RequestParam Double money,
			@RequestParam Integer bankId,
			@RequestParam String account, 
			@RequestParam String name,
			@RequestParam String address,
			@RequestParam(value="remark", required=false) String remark,
			HttpSession session, HttpServletRequest request) {
		
		User user = getSessionUser(session);
		
		if(money%100 != 0) {
			throw new IllegalArgumentException("提现必须为100整数.");
		}
		boolean flag = userTakeCashService.applyTakeCash(user.getId(), money, account, name, bankId, address, remark);
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
	
	@RequestMapping("user_money")
	public @ResponseBody JsonElement getUserMoney(HttpSession session, HttpServletRequest request) {
		User user = getSessionUser(session);
		UserMoney userMoney = userMoneyService.getUserMoney(user.getId());
		return gson.toJsonTree(userMoney);
	}
	
	@RequestMapping("/score_shop_log")
	public  @ResponseBody JsonElement scoreShopLog(@RequestParam(value = "pager", required = false) Pager<UserScoreShopLog> pager,HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		if (pager == null) {
			pager = new Pager<UserScoreShopLog>();
		}
		pager.getSearchMap().put("userId", String.valueOf(user.getId()));
		pager.getSearchMap().put("sendShopState", "1");
		pager = userScoreShopLogService.getPager(pager);
		
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<UserScoreShopLog> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (UserScoreShopLog userScoreShopLog : list) {
			JsonObject log = new JsonObject();
			JsonObject scoreShop = new JsonObject();
			log.add("scoreShop", scoreShop);
			log.addProperty("id", userScoreShopLog.getId());
			log.addProperty("sendShopState", userScoreShopLog.getSendShopState());
			log.addProperty("recordCreateTime", userScoreShopLog.getRecordCreateTime());
			
			scoreShop.addProperty("name", userScoreShopLog.getScoreShop().getName());
			scoreShop.addProperty("type", userScoreShopLog.getScoreShop().getType());
			scoreShop.addProperty("id", userScoreShopLog.getScoreShop().getId());
			scoreShop.addProperty("successMsg", userScoreShopLog.getScoreShop().getSuccessMsg());
			array.add(log);
		}
		return json;
	}
	
	@RequestMapping("/score_log")
	public  @ResponseBody JsonElement scoreLog(@RequestParam(value = "pager", required = false) Pager<UserScoreLog> pager,HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		if (pager == null) {
			pager = new Pager<UserScoreLog>();
		}
		
		UserScore userScore = userScoreService.getUserScore(user.getId());
		pager.getSearchMap().put("userId", String.valueOf(user.getId()));
		pager = userScoreLogService.getPager(pager);
		
		JsonObject json = new JsonObject();
		json.addProperty("currScore", userScore.getScore());
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<UserScoreLog> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (UserScoreLog userScoreLog : list) {
			JsonObject log = new JsonObject();
			log.addProperty("id", userScoreLog.getId());
			log.addProperty("score", userScoreLog.getScore());
			log.addProperty("type", userScoreLog.getType());
			log.addProperty("remark", userScoreLog.getRemark());
			log.addProperty("recordCreateTime", userScoreLog.getRecordCreateTime());
			
			array.add(log);
		}
		return json;
	}
	
	@RequestMapping("/money_log")
	public  @ResponseBody JsonElement moneyLog(@RequestParam(value = "pager", required = false) Pager<UserMoneyLog> pager,HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		if (pager == null) {
			pager = new Pager<UserMoneyLog>();
		}
		
		pager.getSearchMap().put("userId", String.valueOf(user.getId()));
		pager = userMoneyLogService.getPager(pager);
		
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<UserMoneyLog> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (UserMoneyLog userMoneyLog : list) {
			JsonObject log = new JsonObject();
			String remark = userMoneyLog.getRemark();
			if(StringUtils.isNotBlank(userMoneyLog.getExt())) {
				JsonObject jsonObject = new JsonParser().parse(userMoneyLog.getExt()).getAsJsonObject();
				remark = GsonUtils.getString(jsonObject, "remark") + "-" + remark;
			}
			log.addProperty("id", userMoneyLog.getId());
			log.addProperty("money", userMoneyLog.getMoney());
			log.addProperty("type", userMoneyLog.getType());
			log.addProperty("remark", remark);
			log.addProperty("recordCreateTime", userMoneyLog.getRecordCreateTime());
			
			array.add(log);
		}
		return json;
	}
	
	@RequestMapping("/take_cash_log")
	public  @ResponseBody JsonElement takeCashLog(@RequestParam(value = "pager", required = false) Pager<UserTakeCash> pager,HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		if (pager == null) {
			pager = new Pager<UserTakeCash>();
		}
		
		pager.getSearchMap().put("userId", String.valueOf(user.getId()));
		pager = userTakeCashService.getPager(pager);
		
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<UserTakeCash> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (UserTakeCash userTakeCash : list) {
			JsonObject log = new JsonObject();
			log.addProperty("id", userTakeCash.getId());
			log.addProperty("money", userTakeCash.getMoney());
			log.addProperty("state", userTakeCash.getState());
			log.addProperty("remark", userTakeCash.getRemark());
			log.addProperty("bankId", userTakeCash.getCfgBank().getId());
			log.addProperty("account", userTakeCash.getAccount());
			log.addProperty("name", userTakeCash.getName());
			log.addProperty("address", userTakeCash.getAddress());
			log.addProperty("recordCreateTime", userTakeCash.getRecordCreateTime());
			array.add(log);
		}
		return json;
	}
}
