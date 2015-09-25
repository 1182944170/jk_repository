package com.rpframework.website.luoluo.act.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.druid.sql.parser.ParserException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.Activitypicture;
import com.rpframework.website.luoluo.domain.Sponsorlis;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.ActivitypictureSercice;
import com.rpframework.website.luoluo.service.SponsorService;



@Controller
@RequestMapping("api/activi")
public class ApiActivityAct extends BaseAct{
	Gson gson = new Gson();
	@Resource ActivityService   activityService ;
	@Resource ActivitypictureSercice activitypictureSercice;
	@Resource SponsorService sponsorSercice;
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
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
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
	public @ResponseBody JsonElement activiseleone(@RequestParam(value="pager",required=false) Pager<Activity> pager,
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
		List<Activitypicture> activitypict=activitypictureSercice.selectlist(activiid);
		JsonArray array = new JsonArray();
		json.add("arrays", array);
			List<String> imgList = activity.getPhotoPathList();
			JsonArray imgArray = new JsonArray();
			if(CollectionUtils.isNotEmpty(imgList)) {
				for (String s : imgList) {
					imgArray.add(new JsonPrimitive(TagUtils.getFileFullPath(s)));
				}
			}
			json.add("imgs", imgArray);
			if(pager==null){
	 			pager=new Pager<Activity>();
	 		}
			pager.getSearchMap().put("typeok", String.valueOf(2));
			pager.getSearchMap().put("activiid", String.valueOf(activiid));
			activityService.getpager(pager);
			List<Activity> list = pager.getItemList();
			json.addProperty("size", list.size());
		for (Activitypicture act : activitypict) {
			JsonObject jsonObj=new JsonObject();
			jsonObj.addProperty("oldboy", act.getOldboy());
			jsonObj.addProperty("id", act.getId());
			jsonObj.addProperty("myid", act.getMyld());
			array.add(jsonObj);
		}
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
	/**
	 * 发布活动
	 * @param pager
	 * @param activiid
	 * @return
	 */
	@RequestMapping("/activi_addsave")
	public @ResponseBody JsonElement activiaddsave(
			@RequestParam(value="iconFile", required=false) CommonsMultipartFile iconFile,
			@RequestParam(required=false)Integer sponsorid,
			@RequestParam(required=false)String activityname,
			@RequestParam(required=false)Integer activitycategory,
			@RequestParam(required=false)String activitylocation,
			@RequestParam(required=false)Integer number,
			@RequestParam(required=false)Integer children_expense,
			@RequestParam(required=false)Integer old_expense,
			@RequestParam(value="apicture", required=false) MultipartFile apicture,
			@RequestParam(required=false)String activitycontent,
			@RequestParam(required=false)String starttime,
			@RequestParam(required=false)String outtime,
			@RequestParam(required=false)Integer type,
			@RequestParam(required=false)String lng,
			@RequestParam(required=false)String lat,HttpSession session){
		JsonObject json = new JsonObject();
		Sponsorlis bfgs=sponsorSercice.seletOne(sponsorid);
		if(bfgs.getType()==0){
			json.addProperty("error", "活动主办方未通过审核");
			return json;
		}
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
			Activity activity=new Activity();
			activity.setActivityname(activityname);
			activity.setSponsorid(currUser.getId());
			activity.setActivitycategory(activitycategory);
			activity.setActivitylocation(activitylocation);
			activity.setNumber(number);
			activity.setChildren_expense(children_expense);
			activity.setOld_expense(old_expense);
			//缺活动图片
			activity.setActivitycontent(activitycontent);
			activity.setType(type);
			activity.setLng(lng);
			activity.setLat(lat);
			boolean bFlag = activityService.insertone(activity);
			if(bFlag == true){ // 修改成功
				json.addProperty("succ", true);
			} else { // 修改失败
				json.addProperty("error", false);
			} 
			return json;
	}
	
	/*
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