package com.rpframework.website.luoluo.act.api;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.druid.sql.parser.ParserException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.core.api.FileService;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.luoluo.domain.Sponsorlis;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.SponsorService;


@Controller
@RequestMapping("api/spons")
public class ApiSponsorAct extends BaseAct{
	@Resource SponsorService sponsorService;
	@Resource FileService fileService;
	/**
	 * 查询添加主办方的信息
	 * @param sponsorid
	 * @param session
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("listone")
	public @ResponseBody JsonElement listone(
			@RequestParam(required= false)Integer sponsorid,
			HttpSession session) throws ParserException, InterruptedException{
		Sponsorlis sponsor=sponsorService.seletOnesponsor(sponsorid);
		User currUser = getSessionUser(session);	
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		JsonObject json=new JsonObject();
		json.addProperty("Userphone", sponsor.getUserphone());
		json.addProperty("Username", sponsor.getUsername());
		json.addProperty("responname", sponsor.getResponname());
		json.addProperty("usertelephone", sponsor.getUsertelephone());
		json.addProperty("responsibility", TagUtils.getFileFullPath(sponsor.getResponsibility()));
		json.addProperty("Userinformation", TagUtils.getFileFullPath(sponsor.getUserinformation()));
		json.addProperty("entintroduction", sponsor.getEntintroduction());
 		return json;
	}
	//添加公司
	
	@RequestMapping("/add")
	public @ResponseBody JsonElement add(
			@RequestParam(required= false)String name,
			@RequestParam(required= false)String username,
			@RequestParam(required= false)String usernowlive,
			@RequestParam(required= false)String userphone,
			@RequestParam(required= false)String userinformation,
			@RequestParam(required= false)String usertelephone,
			@RequestParam(required= false)String telephone,
			@RequestParam(required= false)String companyname,
			@RequestParam(required= false)String responsibility,
			@RequestParam(required= false)String responname,
			@RequestParam(required= false)String entintroduction,
			@RequestParam(required= false)Integer type,
			@RequestParam(value="myFile", required=false) CommonsMultipartFile myFile,
			@RequestParam(value="iconFile", required=false) MultipartFile iconFile,
			@RequestParam(value="apicture", required=false) MultipartFile apicture,HttpSession session){
	
		JsonObject json = new JsonObject();
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		Sponsorlis sponsor=new Sponsorlis();
		sponsor.setName(name);
		sponsor.setUserid(currUser.getId());
		sponsor.setUsername(username);
		sponsor.setUsernowlive(usernowlive);
		sponsor.setUserphone(userphone);
		sponsor.setUserinformation(userinformation);
		sponsor.setUsertelephone(usertelephone);
		sponsor.setTelephone(telephone);
		sponsor.setCompanyname(companyname);
		sponsor.setResponsibility(responsibility);
		sponsor.setResponname(responname);
		sponsor.setEntintroduction(entintroduction);
		//头像图片		
		if(myFile.getSize() > 0) {
			try {
				String relativelyCardFrontPhoto = "/luoluo/user/userPic/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(iconFile.getOriginalFilename());
				fileService.saveFile(myFile.getInputStream(), relativelyCardFrontPhoto); 
				sponsor.setUserpicture(relativelyCardFrontPhoto);
				sponsor.setActivityTime(System.currentTimeMillis()/1000);
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		} 
		//公司图片
		if(apicture.getSize() > 0) {
			try {
				String relativelyCardFrontPhoto = "/luoluo/user/userPic/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(apicture.getOriginalFilename());
				fileService.saveFile(apicture.getInputStream(), relativelyCardFrontPhoto); 
				sponsor.setResponsibility(relativelyCardFrontPhoto);
				sponsor.setActivityTime(System.currentTimeMillis()/1000);
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		} 
			if(StringUtils.isBlank(usertelephone) || StringUtils.isBlank(responsibility) ){
				json.addProperty("error", "信息不为空");
				return json;
			}
			//领队图片
			if(iconFile.getSize() > 0) {
				try {
					String relativelyCardFrontPhoto = "/luoluo/user/userPic/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(iconFile.getOriginalFilename());
					fileService.saveFile(iconFile.getInputStream(), relativelyCardFrontPhoto); 
					
					sponsor.setUserinformation(relativelyCardFrontPhoto);
					sponsor.setActivityTime(System.currentTimeMillis()/1000);
				} catch (Exception e) {
					throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
				}
			}			
			sponsorService.insertsponsor(sponsor);
			json.addProperty("succ", "添加成功");
			return json;
	}
	//个人
	@RequestMapping("/addone")
	public @ResponseBody JsonElement addonel(
			@RequestParam(required= false)String name,
			@RequestParam(required= false)String username,
			@RequestParam(required= false)String usernowlive,
			@RequestParam(required= false)String userphone,
			@RequestParam(required= false)String userinformation,
			@RequestParam(value="myFile", required=false) CommonsMultipartFile myFile,
			@RequestParam(value="iconFile", required=false) MultipartFile iconFile,
			@RequestParam(required= false)String entintroduction,
			@RequestParam(required= false)Integer type,
		HttpSession session){
		
		JsonObject json = new JsonObject();
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		Sponsorlis sponsor=new Sponsorlis();
		
		sponsor.setName(name);
		sponsor.setUserid(currUser.getId());
		sponsor.setUsername(username);
		sponsor.setUsernowlive(usernowlive);
		sponsor.setUserphone(userphone);
	
		sponsor.setEntintroduction(entintroduction);
		
//头像图片		
		if(myFile.getSize() > 0) {
			try {
				String relativelyCardFrontPhoto = "/luoluo/user/userPic/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(iconFile.getOriginalFilename());
				fileService.saveFile(myFile.getInputStream(), relativelyCardFrontPhoto); 
				sponsor.setUserpicture(relativelyCardFrontPhoto);
				sponsor.setActivityTime(System.currentTimeMillis()/1000);
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		} 

//个人信息图片
		if(iconFile.getSize() > 0) {
			try {
				String relativelyCardFrontPhoto = "/luoluo/user/userPic/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(iconFile.getOriginalFilename());
				fileService.saveFile(iconFile.getInputStream(), relativelyCardFrontPhoto); 
				sponsor.setUserinformation(relativelyCardFrontPhoto);
				sponsor.setActivityTime(System.currentTimeMillis()/1000);
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		}			
		sponsorService.insertsponsor(sponsor);
		json.addProperty("succ", "添加成功");
		return json;
	}
	
}
