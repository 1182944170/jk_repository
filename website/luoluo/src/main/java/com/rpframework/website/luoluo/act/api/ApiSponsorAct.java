package com.rpframework.website.luoluo.act.api;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.parser.ParserException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.website.luoluo.domain.Sponsorlis;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.SponsorService;


@Controller
@RequestMapping("api/spons")
public class ApiSponsorAct extends BaseAct{
	@Resource SponsorService sponsorService;
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
	
	
	
}
