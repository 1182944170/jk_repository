package com.rpframework.website.edongwang.act.api;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.core.utils.MessageFormatter;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.module.common.service.SMSService;
import com.rpframework.utils.AlgorithmEnum;
import com.rpframework.utils.AlgorithmUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.exception.APICodeException;
import com.rpframework.website.edongwang.service.UserService;
import com.rpframework.website.edongwang.utils.EConfig;
import com.rpframework.website.edongwang.utils.EConstants;

@Controller
@RequestMapping("/api")
public class ApiAct extends BaseAct {
	@Resource UserService userService;
	@Resource SMSService smsService;
	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/just_view_text")
	public String justViewText(@RequestParam String text, HttpSession session,Map model, HttpServletRequest request, HttpServletResponse response){
		model.put("text", text);
		return "just_view_text";
	}
	
	
	@RequestMapping("/login")
	public @ResponseBody JsonElement login(@RequestParam(required=false) String contact, @RequestParam(required=false) String password, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		if(StringUtils.isBlank(contact) || StringUtils.isBlank(password)) {
			throw new APICodeException(-5, "非法参数!");
		}
		
		User user = userService.findUserByContact(contact);
		if(user == null) {
			throw new APICodeException(-2, "用户名不存在!");
		}
		
		password = AlgorithmUtils.encodePassword(password, AlgorithmEnum.MD5) ;
		if (!StringUtils.equals(password, user.getPassword())) {
			throw new APICodeException(-3, "密码错误!");
		}
		
		if (user.getState() != 1) {
			throw new APICodeException(-4, "您的帐户不是启用状态！");
		}
		
		userService.doLoginRecord(user, request);
		
		session.setAttribute(SESSION_USER_KEY, user);
		//用户Id + 加密tokenKey + 加密时间 加密成一条密钥存在cookie里，在 EDongWangApi 连接器里坐未登录的情况下是否自动登陆
		EConfig examConfig = SpringUtils.getBean(EConfig.class);
		JsonObject tokenJson = new JsonObject();
		tokenJson.addProperty("id", user.getId());
		tokenJson.addProperty("ct", System.currentTimeMillis()/1000);
		tokenJson.addProperty("tk", examConfig.tokenkey);
		
		String loginEncrypt = AlgorithmUtils.enBase64(tokenJson.toString());
		Cookie cookie = new Cookie(EConstants.COOKIE_LOGIN_ENCRYPT_KEY, loginEncrypt);
		response.addCookie(cookie);
		
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
	
	@RequestMapping("/sendsms_for_forget_password")
	public @ResponseBody JsonElement sendSMSForForgetPassowrd(@RequestParam(required=false) String contact, HttpSession session, HttpServletRequest request) {
		if(StringUtils.isBlank(contact)) {
			throw new IllegalArgumentException("非法参数!");
		}
		
		String verifyCode = String.valueOf(NumberUtils.random(6));
		String sendContent = DictionarySettingUtils.getParameterValue("sendsms.forget_passowrd");
		if(StringUtils.isBlank(sendContent)) {
			sendContent =  "本次重置密码验证码:{}，请牢记";
		}
		sendContent = MessageFormatter.format(sendContent, verifyCode);
		
		boolean flag = smsService.sendSMS(EConstants.ChannelType.SEND_SMS_FORGET_PASSWORD_CHANNEL_TYPE, contact, verifyCode, sendContent);
		if(!flag) {
			throw new IllegalArgumentException("短信发送失败!");
		}
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
	
	
	@RequestMapping("/reset_password")
	public @ResponseBody JsonElement resetPassword(HttpSession session, HttpServletRequest request) {
		String contact = request.getParameter("contact");
		String password = request.getParameter("password");
		String verifyCode = request.getParameter("verifyCode");
		
		if(StringUtils.isBlank(contact) || StringUtils.isBlank(password) || StringUtils.isBlank(verifyCode)) {
			throw new IllegalArgumentException("非法参数!");
		}
		
		if(!smsService.checkVerifyCode(EConstants.ChannelType.SEND_SMS_FORGET_PASSWORD_CHANNEL_TYPE, contact, verifyCode)) {
			throw new IllegalArgumentException("验证码不正确!");
		}
		
		User user = userService.findUserByContact(contact);
		Assert.notNull(user, "找不到用户!");
		password = AlgorithmUtils.encodePassword(password, AlgorithmEnum.MD5);
		if(StringUtils.equals(user.getPassword(), password)) {
			throw new IllegalArgumentException("密码一致!");
		}
		user.setPassword(password);
		userService.update(user);
		smsService.setVerifyCodeVaild(EConstants.ChannelType.SEND_SMS_FORGET_PASSWORD_CHANNEL_TYPE, contact);
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
	
	
	@RequestMapping("/sendsms_for_regist")
	public @ResponseBody JsonElement sendSMSForRegist(@RequestParam(required=false) String contact, HttpSession session, HttpServletRequest request) {
		if(StringUtils.isEmpty(contact)) {
			throw new APICodeException(-1, "非法参数!");
		}
		
		String verifyCode = String.valueOf(NumberUtils.random(6));
		String sendContent = DictionarySettingUtils.getParameterValue("sendsms.regist");
		if(StringUtils.isBlank(sendContent)) {
			sendContent =  "本次注册验证码:{}，请牢记";
		}
		sendContent = MessageFormatter.format(sendContent, verifyCode);
		
		boolean flag = smsService.sendSMS(EConstants.ChannelType.SEND_SMS_REGIST_CHANNEL_TYPE, contact, verifyCode, sendContent);
		if(!flag) {
			throw new APICodeException(-2, "短信发送失败!");
		}
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
	
	@RequestMapping(value="/regist") //, method=RequestMethod.POST
	public @ResponseBody JsonElement regist(HttpSession session, HttpServletRequest request) {
		//headImg
		
		String contact = request.getParameter("contact");
		String password = request.getParameter("password");
//		Integer sex = NumberUtils.parseInt(request.getParameter("sex"));
//		String realName = request.getParameter("realName");
//		String countyCode = request.getParameter("countyCode");
		String verifyCode = request.getParameter("verifyCode");
		
		if(StringUtils.isBlank(contact)
				||StringUtils.isBlank(password)
				||StringUtils.isBlank(verifyCode)) {
			throw new APICodeException(-1, "非法参数!");
		}
		
		if(!smsService.checkVerifyCode(EConstants.ChannelType.SEND_SMS_REGIST_CHANNEL_TYPE, contact, verifyCode)) {
			throw new APICodeException(-4, "验证码不正确!");
		}
		smsService.setVerifyCodeVaild(EConstants.ChannelType.SEND_SMS_REGIST_CHANNEL_TYPE, contact);
		User user = userService.findUserByContact(contact);
		if(user != null) {
			throw new APICodeException(-2, "存在的手机号!");
		}
		
		password = AlgorithmUtils.encodePassword(password, AlgorithmEnum.MD5);
		
		user = new User();
		user.setContact(contact);
		user.setCountyCode("0");
		user.setHeadImg("");
		user.setIsSalesman(0);
		user.setPassword(password);
		user.setRealName("");
		
		user.setSex(1);
		user.setState(1);
		user.setRecordCreateTime(System.currentTimeMillis() / 1000);
		user.setState(1);
		
		boolean falg = userService.insert(user);
		session.removeAttribute(SESSION_USER_KEY);
		JsonObject json = new JsonObject();
		json.addProperty("succ", falg);
		return json;
	}
	
	@RequestMapping("/logout")
	public @ResponseBody JsonElement logout(HttpSession session, HttpServletRequest request){
		session.removeAttribute(SESSION_USER_KEY);
		Cookie[] cookies = request.getCookies();//clear all cookie
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
		}
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
}
