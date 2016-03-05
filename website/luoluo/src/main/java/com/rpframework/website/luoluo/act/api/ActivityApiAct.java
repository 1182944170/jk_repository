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
	
	/*  #{0} lng 经度
	    #{1} lat 纬度
	    #{2} categoryId 分类id
	    #{3} st 传入日期的凌晨00:00点时间戳1456610000 
	    #{4} et 传入日期的23:59点时间戳1456620000
	    #{5} days 传入活动时间几天 1 2 3 4 5 6 7 8 * 86400
	    #{6} baiduCode 传入百度表对应city表的codycity 
	    #{7} page
	    #{8} limit
	 */
	@RequestMapping("/list")
	public @ResponseBody JsonElement activityList(
			@RequestParam(value="lng",required=false) String lng,//经度
			@RequestParam(value="lat",required=false) String lat,//纬度
			@RequestParam(value="categoryId",required=false) Integer categoryId,//分类id
			@RequestParam(value="time",required=false) Integer time,//活动开始时间 1今天 2明天 3后天 4大于后天
			@RequestParam(value="days",required=false) Integer days,//活动天数  1-7天，大于7天
			@RequestParam(value="span",required=false) Integer span,//标签 1官方 2多妹子 3多图 4周末
			@RequestParam(value="area",required=false) Integer area,//区域 1同城 2周边同省 3其它不同省 179
			@RequestParam(value="baiduCode",required=false) Integer baiduCode,//区域 1同城 2周边同省 3其它不同省 179
			@RequestParam(value="type",required=false) String type,//8个页面  home find publish join near nearh nearp finish
			@RequestParam(value="page",required=false) Integer page,//分页
			@RequestParam(value="limit",required=false) Integer limit,//每页数量
			@RequestParam(value="remark",required=false) String remark//备注
			){
		JsonObject json = new JsonObject();
		if(remark!=null && "Y".equals(remark.toUpperCase()))
			json.add("remark",service.getJsonInfo());
		//参数处理 time day span area
		//Long l = days*86400l;//几天 days
		//Long[] arrl = service.getFormatTime(time); 
		
		json.addProperty("totalPage", service.doApiCount1());
		List<Activity> list = service.doApiTest();
				//service.doApiList(lng,lat,categoryId,arrl[0],arrl[1],l,baiduCode,page,limit); 
		JsonArray array = new JsonArray();
		array = getArray(list,0);
		json.add("array", array);
		return json;
	}
	/**
	 * 格式化距离 1.43565868652
	 * @param String r
	 * @return 1.435km
	 * @time 2016年3月4日 上午11:26:21
	 */
	public String format(String r){
		String range ="";
		if(r.indexOf(".")>0){
			r = r.replace(".", ",");
			String[] arr = r.split(",");
			if(arr[1].length()>=3) range =arr[0]+"."+arr[1].substring(0,3);
			else range = arr[0]+"."+arr[1];
		}else{
			range = "0.000";
		}
		range = range+"km";
		return range;
	}
	private JsonArray getArray(List<Activity> list, Integer area) {
		JsonArray array = new JsonArray();
		if(list!=null && list.size()>0)
			for(Activity li : list){
				JsonObject obj = new JsonObject();
				obj.addProperty("id", li.getId());//
				obj.addProperty("name", li.getActivityname());//
				obj.addProperty("cover", IMG+li.getCover());//图片
				obj.addProperty("address", li.getActivitylocation());//地址
				String week = DateUtils.getWeekOfDate(li.getStarttime()*1000);
				StringBuilder spans =new StringBuilder();
				obj.addProperty("week", week);//开始时间
				if(li.getSponsorid()==1){//官方字样
					spans.append("1");
				}
				if(li.getActivitypicture().length()>1){//多图
					spans = !spans.toString().equals("") ? spans.append(",").append("2") : spans.append("2");
				}
				//多妹子 活动报名表里
				List<Integer> idList = service.doActivityIdList();
				String strList = idList.toString().replace("[", "");
				strList = strList.replace("]", "");
				boolean flag = service.isExist(li.getId().toString(),strList);
				if(!flag){
					spans = !spans.toString().equals("") ? spans.append(",").append("3") :spans.append("3");
				}
				if("周六".equals(week)||"周日".equals(week)){//周末字样
					spans = !spans.toString().equals("") ? spans.append(",").append("4") :spans.append("4");
				}
				String sdate = TagUtils.formatDate(li.getStarttime());
				obj.addProperty("date",sdate.substring(5,10).replace("-", "/"));//要不要做成01/01 1/1
				obj.addProperty("time",sdate.substring(sdate.length()-8,sdate.length()-3));
				obj.addProperty("span",spans.toString());//标签 1官方 2多图 3多妹子 4周末
				obj.addProperty("count", service.getJoinUserById(li.getId()));//人数
				String range = "";
				if(li.getJuli()!=null&&li.getJuli()>0){
					range = format(li.getJuli().toString());
				}else{
					range = "0.000km";
				}
				obj.addProperty("range",range);//人数
				array.add(obj);
			}
		return array;
	}
}
