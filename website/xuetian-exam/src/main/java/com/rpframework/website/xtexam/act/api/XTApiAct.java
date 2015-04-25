package com.rpframework.website.xtexam.act.api;

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
import com.rpframework.website.xtexam.domain.XTUser;
import com.rpframework.website.xtexam.exception.APICodeException;
import com.rpframework.website.xtexam.service.XTUserService;
import com.rpframework.website.xtexam.utils.XTConstants;

@Controller
@RequestMapping("/api")
public class XTApiAct extends BaseAct {
	@Resource XTUserService xtUserService;
	@Resource SMSService smsService;
	
	@RequestMapping("/login")
	public @ResponseBody JsonElement login(@RequestParam(required=false) String userName, @RequestParam(required=false) String pwd, HttpSession session, HttpServletRequest request){
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(pwd)) {
			throw new APICodeException(-5, "非法参数!");
		}
		
		XTUser xtUser = xtUserService.findXTUserByUserName(userName);
		if(xtUser == null) {
			throw new APICodeException(-2, "用户名不存在!");
		}
		
		String password = AlgorithmUtils.encodePassword(pwd, AlgorithmEnum.MD5) ;
		if (!StringUtils.equals(password, xtUser.getPwd())) {
			throw new APICodeException(-3, "密码错误!");
		}
		
		if (xtUser.getState() != 1) {
			throw new APICodeException(-4, "您的帐户不是启用状态！");
		}
		
		xtUser.setLastLoginIp(xtUser.getLoginIp()) ;
		xtUser.setLoginIp(request.getRemoteAddr());
		
		xtUser.setLastLoginTime(xtUser.getLoginTime());
		xtUser.setLoginTime(System.currentTimeMillis()/1000);
		
		xtUserService.xtUserDao.update(xtUser);
		
		session.setAttribute(SESSION_USER_KEY, xtUser);
		
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
		
		boolean flag = smsService.sendSMS(XTConstants.ChannelType.SEND_SMS_REGIST_CHANNEL_TYPE, contact, verifyCode, content);
		if(flag) {
			throw new APICodeException(-2, "短信发送失败!");
		}
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
	
	@RequestMapping(value="/regist") //, method=RequestMethod.POST
	public @ResponseBody JsonElement regist(HttpSession session, HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String areaPath = request.getParameter("areaPath");
		String contact = request.getParameter("contact");
		String examClassifyIds = request.getParameter("examClassifyIds");
		String verifyCode = request.getParameter("verifyCode");
		
		if(StringUtils.isBlank(userName)
				||StringUtils.isBlank(pwd)
				||StringUtils.isBlank(areaPath)
				||StringUtils.isBlank(contact)
				||StringUtils.isBlank(verifyCode)
				|| StringUtils.isBlank(examClassifyIds)) {
			throw new APICodeException(-1, "非法参数!");
		}
		
		//TODO:test 不验证
//		if(smsService.checkVerifyCode(XTConstants.ChannelType.SEND_SMS_REGIST_CHANNEL_TYPE, contact, verifyCode)) {
//			throw new APICodeException(-4, "验证码不正确!");
//		}
		XTUser xtUser = xtUserService.findXTUserByUserName(userName);
		if(xtUser != null) {
			throw new APICodeException(-2, "存在的用户名!");
		}
		xtUser = xtUserService.findXTUserByContact(contact);
		if(xtUser != null) {
			throw new APICodeException(-3, "存在的手机号!");
		}
		
		String password = AlgorithmUtils.encodePassword(pwd, AlgorithmEnum.MD5) ;
		
		xtUser = new XTUser();
		xtUser.setAreaPath(areaPath);
		xtUser.setContact(contact);
		xtUser.setEmail("");
		xtUser.setExamClassifyIds(examClassifyIds);
		xtUser.setNickName("");
		xtUser.setPwd(password);
		xtUser.setRecordCreateTime(System.currentTimeMillis() / 1000);
		xtUser.setSourceType(1);
		xtUser.setState(1);
		xtUser.setUserName(userName);
		
		xtUserService.doRegistXTUser(xtUser);
		session.removeAttribute(SESSION_USER_KEY);
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
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
