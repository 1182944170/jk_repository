package com.rpframework.website.luoluo.act.api;

import java.util.List;
import javax.annotation.Resource;
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
import com.rpframework.website.luoluo.service.ActivityService;


@Controller
@RequestMapping("api/activi")
public class ApiActivityAct extends BaseAct{
	Gson gson = new Gson();
	@Resource ActivityService   activityService ;
	
	/**
	 * api查询所有的活动
	 * @param pager
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("/activi_list")
	public @ResponseBody JsonElement activilist(@RequestParam(value="pager",required=false) Pager<Activity> pager 
			) throws ParserException, InterruptedException{
 		if(pager==null){
 			pager=new Pager<Activity>();
 		}
		pager.getSearchMap().put("type", String.valueOf(1));
		activityService.getpager(pager);
		JsonObject json = new JsonObject();
		List<Activity> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Activity act : list) {
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
	/**
	 * 查询详细
	 * @param activiid
	 * @return
	 */
	@RequestMapping("/activi_seleone")
	public @ResponseBody JsonElement activiseleone(
			@RequestParam(required=false) Integer activiid){
		JsonObject json=new JsonObject();
		Activity activity = activityService.selectcal(activiid);
		json.addProperty("id", activity.getId());
		json.addProperty("sponsorid", activity.getSponsorid());
		json.addProperty("cover", activity.getCover());
		json.addProperty("activitynumber", activity.getActivitynumber());
		json.addProperty("activityname", activity.getActivityname());
		json.addProperty("activitylocation", activity.getActivitylocation());
		json.addProperty("number", activity.getNumber());
		json.addProperty("children_expense", activity.getChildren_expense());
		json.addProperty("old_expense", activity.getOld_expense());
		json.addProperty("activitycontent", activity.getActivitycontent());
		json.addProperty("starttime", activity.getStarttime());
		return json;
	}
	/**
	 * 查询分类的
	 */
	@RequestMapping("/activi_activitycategory")
	public @ResponseBody JsonElement activiactivitycategory(
			@RequestParam(required=false) Integer activitycategoryid,
			@RequestParam(value="pager",required=false) Pager<Activity> pager 
			){
		if(pager==null){
 			pager=new Pager<Activity>();
 		}
		pager.getSearchMap().put("calid", String.valueOf(activitycategoryid));
		activityService.getpager(pager);
		JsonObject json = new JsonObject();
		List<Activity> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Activity act : list) {
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
	
	/**
	 * 查询我发布的活动
	 * @param pager
	 * @param activiid
	 * @return
	 */
	@RequestMapping("/activi_myseleone")
	public @ResponseBody JsonElement activimyseleone(@RequestParam(value="pager",required=false) Pager<Activity> pager,
			@RequestParam(required=false) Integer activiid){
		JsonObject json=new JsonObject();
		if(pager==null){
 			pager=new Pager<Activity>();
 		}
		pager.getSearchMap().put("activiid", String.valueOf(activiid));
		activityService.getpager(pager);
		
		List<Activity> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Activity act : list) {
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
	/*8
	 * 删除
	 */
	@RequestMapping("/activi_delete")
	public @ResponseBody JsonElement actividelete(
			@RequestParam(required=false) Integer id){
		boolean activity = activityService.deletesell(id);
		JsonObject json = new JsonObject();
		if(activity){ // 添加成功
			json.addProperty("succ", true);
		} else { // 添加失败
			json.addProperty("error", false);
		} 
		return json;
	}
}