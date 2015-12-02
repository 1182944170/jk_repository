package com.rpframework.website.luoluo.act.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.core.utils.MessageFormatter;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.module.common.service.SMSService;
import com.rpframework.utils.AlgorithmEnum;
import com.rpframework.utils.AlgorithmUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserloginApiAct extends BaseAct{
	@Resource UserService userService;
	@Resource SMSService smsService;
	
	/**
	 * 验证手机号码是否存在
	 * @time 2015年7月20日 下午5:07:30
	 */
	@RequestMapping("val_phone/{phone}")
	public @ResponseBody JsonElement valPhone(@PathVariable("phone") String phone){
		JsonObject json = new JsonObject();
		
		User user = userService.findUserByPhone(phone); // 根据手机号码查找用户
		
		if(user == null){ // user 为空 说明该手机号码没有被使用
			json.addProperty("succ", true);
		} else { // 该手机号码被使用
			json.addProperty("error", false);
		} 
		return json;
	}

	/**
	 * 发送短信验证码
	 * @time 2015年7月17日 下午4:21:05
	 */
	@RequestMapping("/sendsms_for_regist/{userPhone}")
	public @ResponseBody JsonElement sendSMSForRegist(@PathVariable("userPhone") String userPhone) {
		if(StringUtils.isEmpty(userPhone)) {
			throw new APICodeException(-1, "非法手机号!");
		}
		
		String verifyCode = String.valueOf(NumberUtils.random(6));
		String sendContent = DictionarySettingUtils.getParameterValue("sendsms.regist");
		if(StringUtils.isBlank(sendContent)) {
			sendContent =  "本次注册验证码:{}，请牢记";
		}
		sendContent = MessageFormatter.format(sendContent, verifyCode);
		
		// 发送短信验证码
		boolean bFlag = smsService.sendSMS(1, userPhone, verifyCode, sendContent);
		if(!bFlag) {
			throw new APICodeException(-3, "短信发送失败!");
		}
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}

	/**
	 * 用户注册
	 * @time 2015年7月17日 下午5:52:23
	 */
	@RequestMapping("/regist_user")
	public @ResponseBody JsonElement regist(HttpSession session, HttpServletRequest request,RedirectAttributes attr) {
		
		// 获得请求参数值
		String phone = request.getParameter("phone");
		String passWord = request.getParameter("passWord");
		String code = request.getParameter("code");
		JsonObject json = new JsonObject();
		if(StringUtils.isBlank(phone) ||StringUtils.isBlank(passWord) ||StringUtils.isBlank(code)) {
			throw new APICodeException(-1, "非法参数!");
		}
		
		if(!smsService.checkVerifyCode(1, phone, code)) {
			throw new APICodeException(-2, "验证码不正确!");
		}
		
		// 查询手机号是否存在
		User user = userService.findUserByPhone(phone);
		if(user != null) {
			throw new APICodeException(-3, "手机已经存在!");
		}
		// 密码MD5加密
		passWord = AlgorithmUtils.encodePassword(passWord, AlgorithmEnum.MD5);
		boolean bFlag = userService.registUser(phone, passWord); // 注册用户
		User newuser = userService.findUserByPhone(phone);
		//产生10位数的随机号码
		String num =RandomStringUtils.random(9, false, true);
		if(userService.seleAccout(num)!=null){
			 num =RandomStringUtils.random(9, false, true);
			 newuser.setAcnumber(num);
		}else{
			newuser.setAcnumber(num);
		}
		userService.updatedo(newuser);
		
		smsService.setVerifyCodeVaild(1, phone); // 设置短信的类型
		session.removeAttribute(SESSION_USER_KEY);
		json.addProperty("succ", bFlag);
		return json;
	}
	/**
	 * 用户退出
	 * @time 2015年7月20日 下午7:21:33
	 */
	@RequestMapping("remove_session_user")
	public @ResponseBody JsonElement removeSessionUser(HttpSession session){
		JsonObject json = new JsonObject();
		
		User currUser = getSessionUser(session);
		if(currUser != null){
			session.removeAttribute(SESSION_USER_KEY);
		}
		json.addProperty("succ", true);
		return json;
	}

	/**
	 * 忘记密码
	 * @time 2015年8月10日 上午9:30:39
	 */
	@RequestMapping("/sendsms_for_forget_password")
	public @ResponseBody JsonElement sendSMSForForgetPassowrd(@RequestParam(required=false) String phone, HttpSession session) {
		if(StringUtils.isBlank(phone)) {
			throw new APICodeException(-1, "非法参数!");
		}
		
		String verifyCode = String.valueOf(NumberUtils.random(6));
		String sendContent = DictionarySettingUtils.getParameterValue("sendsms.forget_passowrd");
		if(StringUtils.isBlank(sendContent)) {
			sendContent =  "本次重置密码验证码:{}，请牢记";
		}
		sendContent = MessageFormatter.format(sendContent, verifyCode);
		
		boolean flag = smsService.sendSMS(1, phone, verifyCode, sendContent);
		if(!flag) {
			throw new APICodeException(-2, "短信发送失败!");
		}
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
	@RequestMapping("/reset_password")
	public @ResponseBody JsonElement resetPassword(HttpSession session, HttpServletRequest request) {
		String phone = request.getParameter("phone");
		String passWord = request.getParameter("passWord");
		String newpassWord = request.getParameter("passWord");
		String code = request.getParameter("code");
		JsonObject json = new JsonObject();
		if(StringUtils.isBlank(phone) || StringUtils.isBlank(passWord) || StringUtils.isBlank(code)) {
			throw new APICodeException(-1, "非法参数!");
		}
		if(passWord!=newpassWord){
			throw new APICodeException(-2, "密码不能一致!");
		}
		if(!smsService.checkVerifyCode(1, phone, code)) {
			throw new APICodeException(-3, "验证码不正确!");
		}
		User user = userService.findUserByPhone(phone);
		Assert.notNull(user, "找不到用户!");
		String newPasswordMD5 = AlgorithmUtils.encodePassword(passWord, AlgorithmEnum.MD5);
		if(StringUtils.equals(user.getPassword(), passWord)) {
			throw new APICodeException(-4, "密码一致!");
		}
		user.setPassword(newPasswordMD5);
		userService.updatedo(user);
		smsService.setVerifyCodeVaild(1, phone);
		
		
		json.addProperty("succ", true);
		return json;
	}
	/**
	 * 用户登录
	 * @time 2015年7月14日 下午8:41:00
	 */
	@RequestMapping("/user_login")
	public @ResponseBody JsonElement userLogin(@RequestParam(required=false) String phone, 
			@RequestParam(required=false) String passWord,
			HttpSession session){
		JsonObject json = new JsonObject();

		if(StringUtils.isBlank(phone) || StringUtils.isBlank(passWord)) {
			throw new APICodeException(-1, "非法参数!");
		}
		
		User lUser = userService.findUserByPhone(phone);
		if(lUser == null) {
			throw new APICodeException(-2, "用户不存在!");
		}
		
 		passWord = AlgorithmUtils.encodePassword(passWord, AlgorithmEnum.MD5);
		if (!StringUtils.equals(passWord, lUser.getPassword())) {
			throw new APICodeException(-3, "密码错误!");
		}
		
		if (lUser.getType() ==1) {
			throw new APICodeException(-4, "状态异常!");
		}

		boolean bFlag = userService.userLogin(lUser);
		
		if(bFlag){
			
			
			JsonObject jsonObj =new JsonObject();
			jsonObj.addProperty("id", lUser.getId());
			jsonObj.addProperty("id", lUser.getId());
			jsonObj.addProperty("name", lUser.getName());
			jsonObj.addProperty("nameNick", lUser.getNameNick());
			jsonObj.addProperty("phone", lUser.getPhone());
			jsonObj.addProperty("sex", lUser.getSex());
			jsonObj.addProperty("age", lUser.getAge());
			jsonObj.addProperty("marriage", lUser.getMarriage());
			jsonObj.addProperty("hobbues", lUser.getHobbues());
			jsonObj.addProperty("constellation", lUser.getConstellation());
			jsonObj.addProperty("company", lUser.getCompany());
			jsonObj.addProperty("nowlive", lUser.getNowlive());
			jsonObj.addProperty("hometown", lUser.getHometown());
			jsonObj.addProperty("qqaccount", lUser.getQqaccount());
			jsonObj.addProperty("loveStar", lUser.getLoveStar());
			jsonObj.addProperty("lovemuice", lUser.getLovemuice());
			jsonObj.addProperty("loveDeliciousfood", lUser.getLoveDeliciousfood());
			jsonObj.addProperty("signature", lUser.getSignature());
			jsonObj.addProperty("ctiontime", TagUtils.formatDate(lUser.getCtiontime()));
			jsonObj.addProperty("loveFilm", lUser.getLoveFilm());
			jsonObj.addProperty("acnumber", lUser.getAcnumber());
			jsonObj.addProperty("namePic", TagUtils.getFileFullPath(lUser.getNamePic()));
			jsonObj.addProperty("personalMany", lUser.getPersonalMany());
			jsonObj.addProperty("lng", lUser.getLng());
			jsonObj.addProperty("lat", lUser.getLat());
			json.add("user", jsonObj);
			session.setAttribute(SESSION_USER_KEY, lUser);
			json.addProperty("succ", true);
		} else {
			json.addProperty("err", false);
		}	
		return json;
	}
	/**
	 * 修改密码		
	 * @time 2015年7月23日 下午3:08:27
	 */
	@RequestMapping("/chage_pwd")
	public @ResponseBody JsonElement changePwd(@RequestParam(required=false) String oldPassWord ,
			@RequestParam(required=false) String nPassWord ,
			@RequestParam(required=false) String qPassWord , 
			HttpSession session){
		JsonObject json = new JsonObject();
		
		User uUser = getSessionUser(session);
		
		if( uUser == null ){
			throw new APICodeException(-4, "你还没登陆!");
		}
		
		oldPassWord = AlgorithmUtils.encodePassword(oldPassWord, AlgorithmEnum.MD5);
		
		if(!oldPassWord.equals(uUser.getPassword())){
			throw new APICodeException(-1, "原密码错误!");
		}
		
		if(StringUtils.isBlank(nPassWord)){
			throw new APICodeException(-2, "新密码不能为空!");
		}
		
		if(!nPassWord.equals(qPassWord)){
			throw new APICodeException(-3, "两次密码不一致!");
		}
		
		String nPwd = AlgorithmUtils.encodePassword(nPassWord, AlgorithmEnum.MD5);
		
		boolean bFlag = userService.changePwd(uUser.getPhone(), nPwd);
		
		if(bFlag){
			json.addProperty("succ", true);
		} else {
			json.addProperty("err", false);
		}	
		return json;
	}
	
}
