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
		json.addProperty("mylabel1", Myperson.getMylabela());
		json.addProperty("mylabel2", Myperson.getMylabelb());
		json.addProperty("mylabel3", Myperson.getMylabelc());
		json.addProperty("mylabel4", Myperson.getMylabeld());
		json.addProperty("mylabel5", Myperson.getMylabele());
		json.addProperty("mylabel6", Myperson.getMylabelf());
		json.addProperty("mylabel7", Myperson.getMylabelg());
		json.addProperty("mylabel8", Myperson.getMylabels());
		return json;
	}
	
	//修改人物标签库
	@RequestMapping("/mylableupdate")
	public @ResponseBody JsonElement mylableupdate(
			@RequestParam(required=false) Integer userid,
			@RequestParam(required=false) String mylabela,
			@RequestParam(required=false) String mylabelb,
			@RequestParam(required=false) String mylabelc,
			@RequestParam(required=false) String mylabeld,
			@RequestParam(required=false) String mylabele,
			@RequestParam(required=false) String mylabelf,
			@RequestParam(required=false) String mylabelg,
			@RequestParam(required=false) String mylabelh,
			HttpSession session) throws ParserException, InterruptedException{
				User currUser = getSessionUser(session);
					if(currUser == null){
						throw new APICodeException(-4, "你还没登陆!");
					}	
					if(mylabela.equals("-1")){
						mylabela="";
					}
					if(mylabelb.equals("-1")){
						mylabelb="";
					}
					if(mylabelc.equals("-1")){
						mylabelc="";
					}
					if(mylabeld.equals("-1")){
						mylabeld="";
					}
					if(mylabele.equals("-1")){
						mylabele="";
					}
					if(mylabelf.equals("-1")){
						mylabelf="";
					}
					if(mylabelg.equals("-1")){
						mylabelg="";
					}
					if(mylabelh.equals("-1")){
						mylabelh="";
					}
					Mypersonalitylabel Myperson=mypersonalitylabelService.selectOnlyOne(userid);
						Myperson.setMylabela(mylabela);
						Myperson.setMylabelb(mylabelb);
						Myperson.setMylabelc(mylabelc);
						Myperson.setMylabeld(mylabeld);
						Myperson.setMylabele(mylabele);
						Myperson.setMylabelf(mylabelf);
						Myperson.setMylabelg(mylabelg);
						Myperson.setMylabels(mylabelh);
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
