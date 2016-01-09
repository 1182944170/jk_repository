package com.rpframework.website.luoluo.act.api;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.parser.ParserException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.website.luoluo.domain.Mypersonalitylabel;
import com.rpframework.website.luoluo.domain.Personalabel;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.MypersonalitylabelService;
import com.rpframework.website.luoluo.service.PersonalabelService;
@Controller
@RequestMapping("api/mypress")
public class ApiMypersonalitylabelAct extends BaseAct{
	
	@Resource MypersonalitylabelService mypersonalitylabelService;
	@Resource PersonalabelService personalabelService;
	
	
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
		json.addProperty("Mylabela", Myperson.getMylabela());
		json.addProperty("Mylabelb", Myperson.getMylabelb());
		json.addProperty("Mylabelc", Myperson.getMylabelc());
		json.addProperty("Mylabeld", Myperson.getMylabeld());
		json.addProperty("Mylabele", Myperson.getMylabele());
		json.addProperty("Mylabelf", Myperson.getMylabelf());
		json.addProperty("Mylabelg", Myperson.getMylabelg());
		json.addProperty("Mylabelh", Myperson.getMylabelh());
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
					Mypersonalitylabel Myperson=mypersonalitylabelService.selectOnlyOne(currUser.getId());
						Myperson.setMylabela(mylabela);
						Myperson.setMylabelb(mylabelb);
						Myperson.setMylabelc(mylabelc);
						Myperson.setMylabeld(mylabeld);
						Myperson.setMylabele(mylabele);
						Myperson.setMylabelf(mylabelf);
						Myperson.setMylabelg(mylabelg);
						Myperson.setMylabelh(mylabelh);
						boolean c=mypersonalitylabelService.updatedo(Myperson);
		JsonObject json = new JsonObject();
		if(c){ // 添加成功
			json.addProperty("succ", true);
		} else { // 添加失败
			json.addProperty("error", false);
		} 
		return json;
		
	}

@RequestMapping("/mylistlable")
public @ResponseBody JsonElement mylistlable(
		@RequestParam(required=false) Integer userid,
		HttpSession session) throws ParserException, InterruptedException{
	JsonObject json = new JsonObject();
	Mypersonalitylabel Myperson=mypersonalitylabelService.selectOnlyOne(userid);
	if(StringUtils.isBlank(Myperson.getMylabela())){
		json.addProperty("Mylabelaia", Myperson.getMylabela());
	}else{
		Personalabel personalabel= personalabelService.selectOnlyOne(Integer.parseInt(Myperson.getMylabela()));	
		json.addProperty("Mylabela", personalabel.getLabel());
		json.addProperty("Mylabelaid", Myperson.getMylabela());
	}
	if(StringUtils.isBlank(Myperson.getMylabelb())){
		json.addProperty("Mylabelbib", Myperson.getMylabelb());
	}else{
		Personalabel personalabel= personalabelService.selectOnlyOne(Integer.parseInt(Myperson.getMylabelb()));	
		json.addProperty("Mylabelb", personalabel.getLabel());
		json.addProperty("Mylabelbid", Myperson.getMylabelb());
	}
	if(StringUtils.isBlank(Myperson.getMylabelc())){
		json.addProperty("Mylabelcic", Myperson.getMylabelc());
	}else{
		Personalabel personalabel= personalabelService.selectOnlyOne(Integer.parseInt(Myperson.getMylabelc()));	
		json.addProperty("Mylabelc", personalabel.getLabel());
		json.addProperty("Mylabelcid", Myperson.getMylabelc());
	}
	if(StringUtils.isBlank(Myperson.getMylabeld())){
		json.addProperty("Mylabeldid", Myperson.getMylabeld());
	}else{
		Personalabel personalabel= personalabelService.selectOnlyOne(Integer.parseInt(Myperson.getMylabeld()));	
		json.addProperty("Mylabeld", personalabel.getLabel());
		json.addProperty("Mylabeldid", Myperson.getMylabeld());
	}
	if(StringUtils.isBlank(Myperson.getMylabele())){
		json.addProperty("Mylabeleie", Myperson.getMylabele());
	}else{
		Personalabel personalabel= personalabelService.selectOnlyOne(Integer.parseInt(Myperson.getMylabele()));	
		json.addProperty("Mylabele", personalabel.getLabel());
		json.addProperty("Mylabeleid", Myperson.getMylabele());
	}
	if(StringUtils.isBlank(Myperson.getMylabelf())){
		json.addProperty("Mylabelfif", Myperson.getMylabelf());
	}else{
		Personalabel personalabel= personalabelService.selectOnlyOne(Integer.parseInt(Myperson.getMylabelf()));	
		json.addProperty("Mylabelf", personalabel.getLabel());
		json.addProperty("Mylabelfid", Myperson.getMylabelf());
	}
	if(StringUtils.isBlank(Myperson.getMylabelg())){
		json.addProperty("Mylabelgig", Myperson.getMylabelg());
	}else{
		Personalabel personalabel= personalabelService.selectOnlyOne(Integer.parseInt(Myperson.getMylabelg()));	
		json.addProperty("Mylabelg", personalabel.getLabel());
		json.addProperty("Mylabelgid", Myperson.getMylabelg());
	}
	if(StringUtils.isBlank(Myperson.getMylabelh())){
		json.addProperty("Mylabelhih", Myperson.getMylabelh());
	}else{
		Personalabel personalabel= personalabelService.selectOnlyOne(Integer.parseInt(Myperson.getMylabelh()));	
		json.addProperty("Mylabelh", personalabel.getLabel());
		json.addProperty("Mylabelhid", Myperson.getMylabelh());
	}
	json.addProperty("userid",userid);
	return json;
	
}
}
