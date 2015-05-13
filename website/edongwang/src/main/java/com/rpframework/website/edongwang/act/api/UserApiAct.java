package com.rpframework.website.edongwang.act.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.core.api.FileService;
import com.rpframework.module.common.service.SMSService;
import com.rpframework.module.user.domain.CfgBankAddress;
import com.rpframework.module.user.domain.UserBankCard;
import com.rpframework.module.user.service.UserBankCardService;
import com.rpframework.utils.AlgorithmEnum;
import com.rpframework.utils.AlgorithmUtils;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.edongwang.domain.House;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.domain.UserSalesman;
import com.rpframework.website.edongwang.exception.APICodeException;
import com.rpframework.website.edongwang.service.UserSalesmanService;
import com.rpframework.website.edongwang.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserApiAct extends BaseAct {
	
	@Resource UserService userService;
	@Resource FileService fileService;
	@Resource SMSService smsService;
	@Resource UserSalesmanService userSalesmanService;
	@Resource UserBankCardService userBankCardService;
	
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
				throw new APICodeException(-1, "文件上传失败，原因:" + e.getLocalizedMessage());
			}
		}
		
		throw new APICodeException(-2, "参数错误!");
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
	
	@RequestMapping("/change_county_code")
	public @ResponseBody JsonElement changeCountyCode(@RequestParam String countyCode,HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		user.setCountyCode(countyCode);
		userService.update(user);
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		return json;
	}
	
	@RequestMapping("/change_pwssaord")
	public @ResponseBody JsonElement changePassword(@RequestParam String oldPassword, @RequestParam(required=false) String newPassword, HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		
		String oldPasswordMD5 = AlgorithmUtils.encodePassword(oldPassword, AlgorithmEnum.MD5);
		if(!StringUtils.equals(oldPasswordMD5, user.getPassword())) {
			throw new APICodeException(-1, "原密码错误!");
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
			HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		if(user.getIsSalesman() ==1) {//
			throw new APICodeException(-1, "你已经是Salesman，请勿再次提交!");
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
				throw new APICodeException(-1, "文件上传失败，原因:" + e.getLocalizedMessage());
			}
		}
		
		throw new APICodeException(-2, "参数错误!");
	}
	
	@RequestMapping("/bind_bank_card")
	public @ResponseBody JsonElement bindBankCard(@RequestParam Integer bankAddressId,
			@RequestParam String account,
			@RequestParam String name,
			HttpSession session, HttpServletRequest request) {
		
		User user = getSessionUser(session);
		List<UserBankCard> list = userBankCardService.getCardsByUserId(user.getId());
		if(CollectionUtils.isNotEmpty(list)) {
			throw new APICodeException(-1, "已经有绑定银行卡数据!");
		}
		UserBankCard userBankCard = new UserBankCard();
		CfgBankAddress cfgBankAddres = new CfgBankAddress();
		
		userBankCard.setAccount(account);
		cfgBankAddres.setId(bankAddressId);
		userBankCard.setCfgBankAddres(cfgBankAddres );
		userBankCard.setName(name);
		userBankCard.setState(1);
		userBankCard.setUserId(user.getId());
		
		boolean flag = userBankCardService.insert(userBankCard);
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
}
