package com.rpframework.website.luoluo.act.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.parser.ParserException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Myimpression;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.MyimpressionService;
@Controller
@RequestMapping("api/myimp")
public class ApiMyimpressionAct extends BaseAct{
	Gson gson = new Gson();
	@Resource MyimpressionService myimpressionService;
	
	@RequestMapping("add")
	public @ResponseBody JsonElement userlist(	
			@RequestParam(required=false) Integer findid,
			HttpSession session) throws ParserException, InterruptedException{
	
		JsonObject json = new JsonObject();
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		Myimpression reporta=new Myimpression();
		reporta.setUserid(findid);
		reporta.setFindid(currUser.getId());
		reporta.setType(1);
		boolean bFlag = myimpressionService.insertdo(reporta);
		if(bFlag == true){ // 修改成功
			json.addProperty("succ", true);
		} else { // 修改失败
			json.addProperty("error", false);
		} 
		return json;
	}
	/**
	 * 用户列表
	 * @date 2015年7月13日 下午5:47:24
	 */
	@RequestMapping("/user_myimpression")
	public @ResponseBody JsonElement usermyimpression(@RequestParam(value="pager",required=false) Pager<Myimpression> pager,HttpSession session 
			) throws ParserException, InterruptedException{
		if(pager==null){
			pager=new Pager<Myimpression>();
		}
		User currUser = getSessionUser(session);
		pager.getSearchMap().put("type", String.valueOf(currUser.getId()));
		myimpressionService.Userpager(pager);
		JsonObject json = new JsonObject();
		List<Myimpression> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Myimpression myimpression : list) {
			JsonObject o = gson.toJsonTree(myimpression).getAsJsonObject();
			array.add(o);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
}
