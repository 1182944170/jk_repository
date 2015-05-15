package com.rpframework.website.edongwang.act.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.module.common.service.SMSService;
import com.rpframework.utils.AlgorithmEnum;
import com.rpframework.utils.AlgorithmUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.exception.APICodeException;
import com.rpframework.website.edongwang.service.UserService;
import com.rpframework.website.edongwang.utils.EConstants;

@Controller
@RequestMapping("/api")
public class ApiAct extends BaseAct {
	@Resource UserService userService;
	@Resource SMSService smsService;
	
	@RequestMapping("/login")
	public @ResponseBody JsonElement login(@RequestParam(required=false) String contact, @RequestParam(required=false) String pwd, HttpSession session, HttpServletRequest request){
		if(StringUtils.isBlank(contact) || StringUtils.isBlank(pwd)) {
			throw new APICodeException(-5, "非法参数!");
		}
		
		User user = userService.findUserByContact(contact);
		if(user == null) {
			throw new APICodeException(-2, "用户名不存在!");
		}
		
		String password = AlgorithmUtils.encodePassword(pwd, AlgorithmEnum.MD5) ;
		if (!StringUtils.equals(password, user.getPassword())) {
			throw new APICodeException(-3, "密码错误!");
		}
		
		if (user.getState() != 1) {
			throw new APICodeException(-4, "您的帐户不是启用状态！");
		}
		
		user.setLastLoginIp(user.getLoginIp()) ;
		user.setLoginIp(request.getRemoteAddr());
		user.setLastLoginTime(user.getLoginTime());
		user.setLoginTime(System.currentTimeMillis()/1000);
		userService.update(user);
		
		session.setAttribute(SESSION_USER_KEY, user);
		
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
		String content = "本次注册验证码:" + verifyCode + "，请牢记";
		
		boolean flag = smsService.sendSMS(EConstants.ChannelType.SEND_SMS_REGIST_CHANNEL_TYPE, contact, verifyCode, content);
		if(flag) {
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
		
		//TODO:test 不验证
		if(smsService.checkVerifyCode(EConstants.ChannelType.SEND_SMS_REGIST_CHANNEL_TYPE, contact, verifyCode)) {
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
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
}