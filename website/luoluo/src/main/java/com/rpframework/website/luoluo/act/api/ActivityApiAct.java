package com.rpframework.website.luoluo.act.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
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
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.Activitypicture;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.ActivitypictureSercice;
import com.rpframework.website.luoluo.service.UserService;

@Controller
@RequestMapping("api/huodong")
public class ActivityApiAct extends BaseAct{
	@Resource ActivityService service;
	@Resource UserService userService;
	@Resource ActivitypictureSercice joinService;
	
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
			HttpSession session,
			@RequestParam(value="pager", required=false)Pager<Activity> pager,
			@RequestParam(value="search",required=false) String search,//经度
			@RequestParam(value="lng",required=false) String lng,//经度
			@RequestParam(value="lat",required=false) String lat,//纬度
			@RequestParam(value="categoryId",required=false) Integer categoryId,//分类id
			@RequestParam(value="time",required=false) Integer time,//活动开始时间 1今天 2明天 3后天 4大于后天
			@RequestParam(value="days",required=false) Integer days,//活动天数  1-7天，大于7天
			@RequestParam(value="span",required=false) Integer span,//标签 1官方 2多妹子 3多图 4周末
			@RequestParam(value="area",required=false) Integer area,//区域 1同城 2周边同省 3其它不同省 179
			@RequestParam(value="baiduCode",required=false) Integer baiduCode,//区域 1同城 2周边同省 3其它不同省 179
			@RequestParam(value="type",required=false) String type,//8个页面 publish join finish collection
			@RequestParam(value="page",required=false) Integer page,//分页
			@RequestParam(value="limit",required=false) Integer limit,//每页数量
			@RequestParam(value="remark",required=false) String remark//备注
			){
		JsonObject json = new JsonObject();
		User user = getSessionUser(session);
		if(user == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		if(pager==null){
			pager=new Pager<Activity>();
		}
		if(NumberUtils.isNotValid(page) && NumberUtils.isNotValid(limit)){
			throw new APICodeException(-5, "page,limit为必填参数！");
		}else{
			pager.setCurrentPage(page);
		}
		
		JsonArray array = new JsonArray();
		List<Activity> list =null;
		//Integer userId=4; 换成有数据的测试用户id
		Integer userId =user.getId();
		if(StringUtils.isNotBlank(search)){//查活动编号或 
			list = service.doActivityListSearch(search,page,limit);
			array = getArray(list,span);
			json.add("array", array);
			return json;
		}
		if("publish".equals(type)){//查我发布的 
			list = service.doActivityListByUserId(userId,page,limit);
			//json.addProperty("totalPage",list.get(0).getTotalPage());
			array = getArray(list,span);
			json.add("array", array);
			return json;
		}
		if("join".equals(type)){//查我参加的
			list = service.doActivityListByUserJoin(userId,page,limit);
			//json.addProperty("totalPage",list.get(0).getTotalPage());
			array = getArray(list,span);
			json.add("array", array);
			return json;
		}
		if("finish".equals(type)){//查询成功举办的
			list = service.doActivityListByFinish(page,limit);
			//json.addProperty("totalPage",list.get(0).getTotalPage());
			array = getArray(list,span);
			json.add("array", array);
			return json;
		}
		if("collection".equals(type)){//查询成功举办的
			list = service.doApiListByCollection(userId,page,limit);
			//json.addProperty("totalPage",list.get(0).getTotalPage());
			array = getArray(list,span);
			json.add("array", array);
			return json;
		}
		if(lng!=null)
		pager.getSearchMap().put("lng", String.valueOf(lng));
		if(lat!=null)
		pager.getSearchMap().put("lat", String.valueOf(lat));
		if(categoryId!=null)
		pager.getSearchMap().put("categoryId", String.valueOf(categoryId));
		if(NumberUtils.isValid(days)){
			Long l =null;
			l = days*86400l;//几天 days
			if(days<=7){
				pager.getSearchMap().put("l", String.valueOf(l));
			}
		}
		if(NumberUtils.isValid(time)){
			Long[] arrl = null;
			arrl = service.getFormatTime(time); 
			if(time<4){
				pager.getSearchMap().put("st", String.valueOf(arrl[0]));
				pager.getSearchMap().put("et", String.valueOf(arrl[1]));
			}
		}
		if(NumberUtils.isValid(baiduCode)){
			pager.getSearchMap().put("baiduCode", String.valueOf(baiduCode));
		}
		if(NumberUtils.isValid(span)){
			if(span == 1){
				pager.getSearchMap().put("authority", String.valueOf("authority"));
			}
			if(span == 3){
				pager.getSearchMap().put("moregirl", String.valueOf("moregirl"));
			}
		}
		if(NumberUtils.isValid(area)){
			if(area == 1){
				pager.getSearchMap().put("city", String.valueOf("city"));
			}
			if(area == 2){
				pager.getSearchMap().put("province", String.valueOf("province"));
			}
			if(area == 3){
				pager.getSearchMap().put("other", String.valueOf("other"));
			}
		}
		pager.getSearchMap().put("type", "2");//这是客户端的请求 只允许查询审核通过的数据
		//service.getPagerTest(pager);
		service.getpager(pager);
		if(remark!=null && "Y".equals(remark.toUpperCase()))
			json.add("remark",service.getJsonInfo());
		//参数处理 time day span area
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		list = pager.getItemList();
		if(NumberUtils.isValid(span)){
			array  = getArray(list,span);
		}else{
			array = getArray(list,0);
		}
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
	private JsonArray getArray(List<Activity> list, Integer span) {
		JsonArray array = new JsonArray();
		if(list!=null && list.size()>0)
			for(Activity li : list){
				JsonObject obj = new JsonObject();
				String week = DateUtils.getWeekOfDate(li.getStarttime()*1000);
				if(NumberUtils.isValid(span)){
					if(span == 2){
						if(li.getActivitypicture().indexOf(",")<0){
							continue;
						}
					}
					if(span == 3){
						List<Integer> idList = service.doActivityIdList();
						String strList = idList.toString().replace("[", "");
						strList = strList.replace("]", "");
						boolean flag = service.isExist(li.getId().toString(),strList);
						if(!flag){
							continue;
						}
					}
					if(span == 4){
						if("周六".equals(week)||"周日".equals(week)){
						}else{
							continue;
						}
					}
				}
				
				obj.addProperty("id", li.getId());//
				obj.addProperty("name", li.getActivityname());//
				obj.addProperty("cover", IMG+li.getCover());//图片
				obj.addProperty("address", li.getActivitylocation());//地址
				int bm = 0;
				List<Activitypicture> apList = joinService.queryByAcitvity(li.getId());
				for(Activitypicture ap : apList){
					bm = bm+ap.getOldboy()+ap.getChindenboy()+ap.getGrilexpense();//每条报名的总人数
				}
				obj.addProperty("bm", bm);//报名
				obj.addProperty("number",li.getNumber());//限制
				StringBuilder spans =new StringBuilder();
				if(li.getSponsorid()==1){//官方字样
					spans.append("1");
				}
				if(li.getActivitypicture().indexOf(",")>0){//多图
					spans = !spans.toString().equals("") ? spans.append(",").append("2") : spans.append("2");
				}
				//多妹子 活动报名表里
				//List<Integer> idList = service.doActivityIdList();
				//String strList = idList.toString().replace("[", "");
				//strList = strList.replace("]", "");
				//boolean flag = service.isExist(li.getId().toString(),strList);
				if(li.getJoinNumber()>=3){
					spans = !spans.toString().equals("") ? spans.append(",").append("3") :spans.append("3");
				}
				if("周六".equals(week)||"周日".equals(week)){//周末字样
					spans = !spans.toString().equals("") ? spans.append(",").append("4") :spans.append("4");
				}
				String sdate = TagUtils.formatDate(li.getStarttime());
				String edate = TagUtils.formatDate(li.getOuttime());
				
				//obj.addProperty("date",sdate.substring(5,10).replace("-", "/"));//要不要做成01/01 1/1
				//obj.addProperty("time",sdate.substring(sdate.length()-8,sdate.length()-3));
				obj.addProperty("timeFormat", 
	week+" "+sdate.substring(5,10).replace("-", "/")+" "+sdate.substring(sdate.length()-8,sdate.length()-3)+" - "+DateUtils.getWeekOfDate(li.getOuttime()*1000)+" "+edate.substring(5,10).replace("-", "/")+" "+edate.substring(edate.length()-8,edate.length()-3));
				obj.addProperty("startTime", li.getStarttime());
				obj.addProperty("endTime", li.getOuttime());
				obj.addProperty("span",spans.toString());//标签 1官方 2多图 3多妹子 4周末
				obj.addProperty("count", service.getJoinUserById(li.getId())==0 ? "0" : service.getJoinUserById(li.getId()).toString());//人数
				String range = "";
				if(li.getJuli()!=null&&li.getJuli()>0){
					range = format(li.getJuli().toString());
				}else{
					range = "0.000km";
				}
				obj.addProperty("range",range);//距离
				array.add(obj);
			}
		return array;
	}
	@RequestMapping("/del")
	public @ResponseBody JsonElement deleteActivity(
			@RequestParam(value="id",required=false) Integer id){
		JsonObject json = new JsonObject();
		Integer i = service.getJoinNumber(id);
		if(i>0){
			json.addProperty("succ", false);
			json.addProperty("msg", "当前活动已经有人报名！");
			return json;
		}
		boolean f = service.deleteAll(id);
		if(f){
			json.addProperty("succ", true);
			json.addProperty("msg", "删除成功！");
		}else{
			json.addProperty("succ", false);
			json.addProperty("msg", "删除失败！");
		}
		return json;
	}
	//已报名的人员列表
	@RequestMapping("/joinList")
	public @ResponseBody JsonElement joinList(
			@RequestParam(value="activityId",required=false) Integer activityId,
			HttpSession session
			){
		JsonObject json = new JsonObject();
		if(NumberUtils.isNotValid(activityId)){
			throw new APICodeException(-5, "activityId为必填参数！");
		}
		User user = getSessionUser(session);
		if(user == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		List<Activitypicture> list = joinService.getListByActivity(activityId);
		JsonArray array = new JsonArray();
		for(Activitypicture li : list){
			User u = userService.select(li.getMyld());
			if(u==null){continue;}
			JsonObject obj = new JsonObject();
			obj.addProperty("image",IMG+u.getNamePic());
			obj.addProperty("name", u.getNameNick());
			obj.addProperty("girl", li.getGrilexpense());//
			obj.addProperty("man", li.getOldboy());//
			obj.addProperty("child", li.getChindenboy());//
			obj.addProperty("id", u.getId());//
			obj.addProperty("sex", u.getSex());//
			obj.addProperty("age", u.getAge());//0男 1女
			Activity a = service.select(li.getSponsorld());
			if(a!=null && a.getSponsorid() == user.getId()){
				obj.addProperty("tel", u.getPhone());
				obj.addProperty("insure", li.getInsure());//投保证件
				obj.addProperty("money", li.getMonely());//投保金额
				obj.addProperty("info", "");//投保金额
			}else{
				obj.addProperty("tel", "");
				obj.addProperty("insure", "");//投保证件
				obj.addProperty("money", "");//投保金额
				obj.addProperty("info", "");//投保金额
			}
			array.add(obj);
		}
		json.add("array", array);
		return json;
	}
	
}
