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
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.domain.Widens;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.WidensService;

@Controller
@RequestMapping("api/widen")
public class ApiWidensAct extends BaseAct{
	Gson gson = new Gson();
	@Resource WidensService widensService;
	
	
	/**
	 * 查询系统消息
	 * @param pager
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("/widen_list")
	public @ResponseBody JsonElement widen(@RequestParam(value="pager",required=false) Pager<Widens> pager,HttpSession session ) throws ParserException, InterruptedException{
 		if(pager==null){
 			pager=new Pager<Widens>();
 		}
 		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
 		widensService.widenpager(pager);
		JsonObject json = new JsonObject();
		List<Widens> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Widens widens : list) {
			JsonObject o = gson.toJsonTree(widens).getAsJsonObject();
			array.add(o);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
}
