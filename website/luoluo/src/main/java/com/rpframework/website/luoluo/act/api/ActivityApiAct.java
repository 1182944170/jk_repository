package com.rpframework.website.luoluo.act.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.utils.DateUtils;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.service.ActivityService;

@Controller
@RequestMapping("api/huodong")
public class ActivityApiAct extends BaseAct{
	@Resource ActivityService service;
	
	//活动筛选 根据 sponsorid主办方只发活动 myld==userId谁参加
	//1.activitycategory活动分类id 
	//2.活动开始时间（今天，明天，后天，大于后天） starttime
	//3.活动天数（开始时间--结束时间区间） starttime outtime
	//4.多妹子（活动报名表，用户性别为女） 
	//5.同城活动 city相同，周边活动（同省），长途（除了同城的，除了周边的）
	/**
	 * 
	 * @param categoryId
	 * @param time
	 * @param days
	 * @param span
	 * @param area
	 * @return
	 * @time 2016年3月3日 下午4:14:35
	 * 
	 * #所有活动
		SELECT * FROM release_activity t1
		#按类型分类 1户外 2旅行
		WHERE t1.activitycategory = 2
		#开始时间1456615800  为今天 明天 后天 大于后天
		AND t1.starttime BETWEEN 1456610000 #今天凌晨00：00
		                 AND 1456620000 #今天晚上23：59
		#活动天数
		AND (t1.outtime - t1.starttime) < 86400*1 #一天以内含一天 86400 = 1天
		#同城市 周边 省外 #179
		AND t1.city IN #Not 长途活动 省外
									 #(SELECT c.code FROM city c  WHERE c.codycity = 179) #同城
							     (SELECT c.code FROM city c LEFT JOIN city cc ON cc.countryCode = c.countryCode WHERE c.codycity = 179) #周边			
		#多妹子
		AND
		t1.Id IN 
		#报名表里sponsorid 报名 用户性别为女的 只能是有妹子
		(SELECT t2.sponsorld FROM activity_registration t2 WHERE t2.myld IN(
			SELECT t3.id FROM user t3 WHERE t3.sex = 1
		))
	 */
	@RequestMapping("/list")
	public @ResponseBody JsonElement activityList(
			@RequestParam(value="categoryId",required=false) Integer categoryId,//分类id
			@RequestParam(value="time",required=false) Integer time,//活动开始时间 1今天 2明天 3后天 4大于后天
			@RequestParam(value="days",required=false) Integer days,//活动天数  1-7天，大于7天
			@RequestParam(value="span",required=false) Integer span,//标签 1官方 2多妹子 3多图 4周末
			@RequestParam(value="area",required=false) Integer area,//区域 1同城 2周边同省 3其它不同省 179
			@RequestParam(value="page",required=false) Integer page,//分页
			@RequestParam(value="limit",required=false) Integer limit//每页数量
			
			){
		JsonObject json = new JsonObject();
		Long st = System.currentTimeMillis()/1000;
		Long et = System.currentTimeMillis()/1000;
		List<Activity> list = service.doApiList(categoryId,st,et,days,span,area,page,limit); 
		JsonArray array = new JsonArray();
		for(Activity li : list){
			JsonObject obj = new JsonObject();
			obj.addProperty("id", li.getId());//
			obj.addProperty("name", li.getActivityname());//
			obj.addProperty("cover", li.getCover());//图片
			obj.addProperty("address", li.getActivitylocation());//地址
			String week = DateUtils.getWeekOfDate(li.getStarttime());
			String weekend = "";
			obj.addProperty("time", week);//开始时间
			if("周六".equals(week)||"周日".equals(week)){//周末字样
				weekend = "周末";
			}
			if(li.getSponsorid()==1){//官方字样
				
			}
			if(li.getActivitypicture().length()>1){//多图
				
			}
			//多妹子 活动报名表里
			List<Integer> idList = service.doActivityIdList();
			json.addProperty("idList", idList.toString());
			obj.addProperty("weekend",weekend);
			obj.addProperty("span", "标签");//标签 1官方 2多图 3多妹子 4周末
			obj.addProperty("person", "人数");//人数
			array.add(obj);
		}
		json.add("array", array);
		return json;
	}
	
}
