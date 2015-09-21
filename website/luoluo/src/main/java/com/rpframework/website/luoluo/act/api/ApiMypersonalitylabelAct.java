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
import com.rpframework.website.luoluo.domain.Mypersonalitylabel;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.MypersonalitylabelService;
@Controller
@RequestMapping("api/mypress")
public class ApiMypersonalitylabelAct extends BaseAct{
	
	@Resource MypersonalitylabelService mypersonalitylabelService;
	
	
/**
 * 显示标签库
 * @param session
 * @return
 * @throws ParserException
 * @throws InterruptedException
 */
	@RequestMapping("/userlistid")
	public @ResponseBody JsonElement userlistid(HttpSession session
			) throws ParserException, InterruptedException{
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		Mypersonalitylabel Myperson=mypersonalitylabelService.selectOnlyOne(currUser.getId());
		if(Myperson.getUserid()==null){
			
			Myperson.setUserid(currUser.getId());
			mypersonalitylabelService.insertdo(Myperson);
		}
		JsonObject json = new JsonObject();
		json.addProperty("mylabel1", Myperson.getMylabel1());
		json.addProperty("mylabel2", Myperson.getMylabel2());
		json.addProperty("mylabel3", Myperson.getMylabel3());
		json.addProperty("mylabel4", Myperson.getMylabel4());
		json.addProperty("mylabel5", Myperson.getMylabel5());
		json.addProperty("mylabel6", Myperson.getMylabel6());
		json.addProperty("mylabel7", Myperson.getMylabel7());
		json.addProperty("mylabel8", Myperson.getMylabel8());
		return json;
	}
	
	//修改人物标签库
	@RequestMapping("/mylableupdate")
	public @ResponseBody JsonElement mylableupdate(
			@RequestParam(required=false) Integer userid,
			@RequestParam(required=false) Integer mylabel1,
			@RequestParam(required=false) Integer mylabel2,
			@RequestParam(required=false) Integer mylabel3,
			@RequestParam(required=false) Integer mylabel4,
			@RequestParam(required=false) Integer mylabel5,
			@RequestParam(required=false) Integer mylabel6,
			@RequestParam(required=false) Integer mylabel7,
			@RequestParam(required=false) Integer mylabel8,
			HttpSession session) throws ParserException, InterruptedException{
				User currUser = getSessionUser(session);
				
					if(currUser == null){
						throw new APICodeException(-4, "你还没登陆!");
					}	
					Mypersonalitylabel Myperson=mypersonalitylabelService.selectOnlyOne(userid);
						Myperson.setMylabel1(mylabel1);
						Myperson.setMylabel2(mylabel2);
						Myperson.setMylabel3(mylabel3);
						Myperson.setMylabel4(mylabel4);
						Myperson.setMylabel5(mylabel5);
						Myperson.setMylabel6(mylabel6);
						Myperson.setMylabel7(mylabel7);
						Myperson.setMylabel8(mylabel8);
						boolean c=mypersonalitylabelService.updatedo(Myperson);
		JsonObject json = new JsonObject();
		if(c){ // 添加成功
			json.addProperty("succ", true);
		} else { // 添加失败
			json.addProperty("error", false);
		} 
		return json;
		
	}
}
