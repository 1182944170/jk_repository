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
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.MyActivities;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.MyActivitiesSercice;

@Controller
@RequestMapping("api/myactivey")
public class ApiMyActivitiesAct extends BaseAct{
	Gson gson = new Gson();
	@Resource MyActivitiesSercice myactivitiesSercice;
	
	/**
	 * 显示我的收藏页面
	 * @param pager
	 * @param session
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("/myactivi_list")
	public @ResponseBody JsonElement myactivilist(@RequestParam(value="pager",required=false) Pager<MyActivities> pager 
			,HttpSession session) throws ParserException, InterruptedException{
 		if(pager==null){
 			pager=new Pager<MyActivities>();
 		}
 		User currUser = getSessionUser(session);	
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		pager.getSearchMap().put("currUserid", String.valueOf(currUser.getId()));
		myactivitiesSercice.getpager(pager);
		
		JsonObject json = new JsonObject();
		List<MyActivities> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (MyActivities act : list) {
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
	/**
	 * 收藏用户
	 * @param type
	 * @param activitiesid
	 * @param session
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping("add")
	public @ResponseBody JsonElement add(
			@RequestParam(required= false)Integer type,//0.1
			@RequestParam(required= false)Integer activitiesid,HttpSession session)throws Exception{
		User currUser = getSessionUser(session);	
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
			MyActivities myactivities=new MyActivities();
			myactivities.setActivitiesid(activitiesid);
			myactivities.setUserid(currUser.getId());
			myactivities.setType(type);
			boolean bFlag=myactivitiesSercice.insertongl(myactivities);
		JsonObject json=new JsonObject();
		if(bFlag == true){ // 修改成功
			json.addProperty("succ", true);
		} else { // 修改失败
			json.addProperty("error", false);
		} 
		return json;
	}
	/**
	 * 收藏用户
	 * @param type
	 * @param activitiesid
	 * @param session
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping("addlise")
	public @ResponseBody JsonElement addlise(
			@RequestParam(required= false)Integer id,//0.1
			@RequestParam(required= false)Integer type,//0.1
			@RequestParam(required= false)Integer activitiesid,HttpSession session)throws Exception{
		User currUser = getSessionUser(session);	
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
			
		MyActivities c=myactivitiesSercice.selectone(id);
		if(c.getType()==1){
			c.setType(0);
		}else if(c.getType()==0){
			c.setType(1);
		}
		myactivitiesSercice.updatedo(c);
		JsonObject json=new JsonObject();
		
		return json;
	}
}
