package com.rpframework.website.luoluo.act.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.sql.parser.ParserException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.rpframework.core.BaseAct;
import com.rpframework.core.api.FileService;
import com.rpframework.module.common.dao.ICityDao;
import com.rpframework.module.common.domain.City;
import com.rpframework.module.common.service.CityService;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.Activitypicture;
import com.rpframework.website.luoluo.domain.Classification;
import com.rpframework.website.luoluo.domain.Sponsorlis;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.ActivitypictureSercice;
import com.rpframework.website.luoluo.service.BanscityService;
import com.rpframework.website.luoluo.service.ClassificationService;
import com.rpframework.website.luoluo.service.SponsorService;
import com.rpframework.website.luoluo.service.UserService;



@Controller
@RequestMapping("api/activi")
public class ApiActivityAct extends BaseAct{
	Gson gson = new Gson();
	@Resource UserService   userService ;
	@Resource ActivityService   activityService ;
	@Resource ActivitypictureSercice activitypictureSercice;
	@Resource SponsorService sponsorSercice;
	@Resource ClassificationService classificationService;
	@Resource BanscityService banscityService;
	@Resource ICityDao IcityDao;
	
	@Resource FileService fileService;
	@Resource CityService cityService;

	/**
	 * api查询所有的活动
	 * @param pager
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("/activi_list")
	public @ResponseBody JsonElement activilist(@RequestParam(value="pager",required=false) Pager<Activity> pager 
			,@RequestParam(required=false) String city,
			@RequestParam(required=false) String lat,
			@RequestParam(required=false) String lng
			) throws ParserException, InterruptedException{
		City cityc=cityService.selectdoBycitycode(city);
		List<Activity> list = activityService.selectactivice(lat,lng,cityc.getCode());
		if(pager==null){
			pager = new Pager<Activity>();
		}
		long startTime = System.currentTimeMillis();
		long z=System.currentTimeMillis()/1000;
		pager.setItemList(list);
		pager.setTotalCount(list.size());
		pager.setCostTime(System.currentTimeMillis() - startTime);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		JsonArray array = new JsonArray();
		
		json.add("arrays", array);
		for (Activity act : list) {
			List<Activitypicture>  cc=activitypictureSercice.selectlist(act.getId());
			int bm_num=0;
			int i=0;
			for (Activitypicture tt : cc) {
				if("".equals(tt.getGrilexpense())){
					tt.setGrilexpense(0);
				}else if("".equals(tt.getChindenboy())){
					tt.setChindenboy(0);
				}else if("".equals(tt.getOldboy())){
					tt.setOldboy(0);
				}
				i=tt.getOldboy()+tt.getChindenboy()+tt.getGrilexpense();
				bm_num+=i;
			}
			act.setBm_num(bm_num);
			Sponsorlis	span= sponsorSercice.seletOne(act.getSponsorid());
			if(span!=null){
				if(span.getTypeopp()==1){
					if(act.getOuttime()<z){
						
					}else{
						JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
						array.add(jsonObj);
					}
			   
				}
			}
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
/*	@RequestMapping("/activi_listzz")
	public String activilist() throws ParserException, InterruptedException{
		List<Banscity> bb=banscityService.selectall();
		for(Banscity act :bb){
		City c=	cityService.selectdo(act.getCity());
		c.setCodycity(act.getCode()+"");
		IcityDao.update(c);
		}
		return "";
	}*/
	/**
	 * 查询详细
	 * @param activiid
	 * @return
	 */
	@RequestMapping("/activi_seleone")
	public @ResponseBody JsonElement activiseleone(@RequestParam(value="pager",required=false) Pager<Activity> pager,
			@RequestParam(required=false) Integer activiid,HttpSession session){
		JsonObject json=new JsonObject();
		Activity activity = activityService.selectcal(activiid);
		if(activity==null){
			json.addProperty("msg", "活动不存在");
			json.addProperty("succ", false);
			return json;
		}
		User user = getSessionUser(session);
		if(user == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		json.addProperty("id", activity.getId());
		Sponsorlis sp = sponsorSercice.seletOnesponsor(user.getId());
		if(sp!=null && sp.getId() == activity.getSponsorid()){
			json.addProperty("join",2);
		}else
		json.addProperty("join",activityService.doIsJoin(activiid,user.getId()));//是否能报名 1能报名 2不能报名
		json.addProperty("sponsorid", activity.getSponsorid());
		json.addProperty("cover", activity.getCover());
		json.addProperty("activitynumber", activity.getActivitynumber());
		json.addProperty("activitycategory", activity.getActivitycategory());
		json.addProperty("activityname", activity.getActivityname());
		json.addProperty("activitylocation", activity.getActivitylocation());
		json.addProperty("number", activity.getNumber());//限制人数
		json.addProperty("children_expense", activity.getChildren_expense());
		json.addProperty("old_expense", activity.getOld_expense());
		json.addProperty("gril_expense", activity.getGril_expense());
		json.addProperty("activitycontent", activity.getActivitycontent());
		json.addProperty("starttime", activity.getStarttime());
		json.addProperty("outtime", activity.getOuttime());
		json.addProperty("nowforetime", activity.getNowforetime());
		json.addProperty("lat", activity.getLat());
		json.addProperty("lng", activity.getLng());
		json.addProperty("type", activity.getType());
		json.addProperty("zhuangttai", activity.getZhuangttai());
		List<Activity> listseize=activityService.selectluist(activity.getSponsorid());
		json.addProperty("chenggong", activityService.getFinishCount());
		json.addProperty("typeok", activity.getTypeok());
		json.addProperty("starttime", activity.getStarttime());
		Classification classors=classificationService.selectcal(activity.getActivitycategory());
		json.addProperty("classificationname", classors.getClaName());
		if("".equals(activity.getPhone())){
			Sponsorlis spon=sponsorSercice.seletOne(activity.getSponsorid());
			if(spon==null){
				json.addProperty("msg", "活动主办方不存在");
				json.addProperty("succ", false);
				return json;
			}
			json.addProperty("sponuserphone", spon.getUserphone());
		}else{
			json.addProperty("sponuserphone", activity.getPhone());
		}
		List<String> imgList = activity.getPhotoPathList();
		JsonArray imgArray = new JsonArray();
		if(CollectionUtils.isNotEmpty(imgList)) {
			for (String s : imgList) {
				imgArray.add(new JsonPrimitive(s));
			}
		}
		json.add("imgs", imgArray);
		int bm = 0;
		List<Activitypicture> apList = activitypictureSercice.queryByAcitvity(activity.getId());
		for(Activitypicture ap : apList){
			bm = bm+ap.getOldboy()+ap.getChindenboy()+ap.getGrilexpense();//每条报名的总人数
		}
		json.addProperty("bm_num", bm);//已报名的人数
		return json;
	}
	/**
	 * 查询通过活动id查询报名列表
	 * @param activiid
	 * @return
	 */
	@RequestMapping("/baoming")
	public @ResponseBody JsonElement baoming(
			@RequestParam(required=false) Integer activiid,HttpSession session){
		JsonObject json=new JsonObject();
		List<Activitypicture> activitypict=activitypictureSercice.selectlist(activiid);
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Activitypicture act : activitypict) {
			User user=userService.selectOnlyOne(act.getMyld());
			act.setUser(user);
			int bm_num=0;
			int i=0;
			i=act.getOldboy()+act.getChindenboy()+act.getGrilexpense();
				bm_num+=i;
			act.setBm_num(bm_num);
			
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		return json;
	}
	
	@RequestMapping("/jintweidu")
	public @ResponseBody JsonElement jintweidu(@RequestParam(value="pager",required=false) Pager<Activity> pager,
			@RequestParam(required=false) String city,
			@RequestParam(required=false) String lat,
			@RequestParam(required=false) String lng,
			HttpSession session){
		City cityc=cityService.selectdoBycitycode(city);
		List<Activity> list = activityService.selectactivice(lat,lng,cityc.getCode());
		if(pager==null){
			pager = new Pager<Activity>();
		}
		long startTime = System.currentTimeMillis();
		long z=System.currentTimeMillis()/1000;
		pager.setItemList(list);
		pager.setTotalCount(list.size());
		pager.setCostTime(System.currentTimeMillis() - startTime);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Activity act : list) {
							Sponsorlis	span= sponsorSercice.seletOne(act.getSponsorid());
							if(span!=null){
								if(span.getTypeopp()==1){
										if(act.getOuttime()<z){
										
									}else{
										JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
										array.add(jsonObj);
									}
							}
				   }
		}
		return json;
	}
	/**
	 * 查询分类的
	 */
	@RequestMapping("/activi_activitycategory")
	public @ResponseBody JsonElement activiactivitycategory(
			@RequestParam(required=false) Integer activitycategoryid,
			@RequestParam(required=false) String city,
			@RequestParam(required=false) String lat,
			@RequestParam(required=false) String lng,
			@RequestParam(value="pager",required=false) Pager<Activity> pager 
			){
		City cityc=cityService.selectdoBycitycode(city);
		List<Activity> list = activityService.selectacttple(lat,lng,cityc.getCode(),activitycategoryid);
		if(pager==null){
			pager = new Pager<Activity>();
		}
		long startTime = System.currentTimeMillis();
		long z=System.currentTimeMillis()/1000;
		pager.setItemList(list);
		pager.setTotalCount(list.size());
		pager.setCostTime(System.currentTimeMillis() - startTime);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages" , pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Activity act : list) {
			List<Activitypicture>  cc=activitypictureSercice.selectlist(act.getId());
			int bm_num=0;
			int i=0;
			for (Activitypicture tt : cc) {
				i=tt.getOldboy()+tt.getChindenboy()+tt.getGrilexpense();
				bm_num+=i;
				
			}
			act.setBm_num(bm_num);
							Sponsorlis	span= sponsorSercice.seletOne(act.getSponsorid());
							if(span!=null){
								if(span.getTypeopp()==1){
									if(act.getOuttime()<z){
										
									}else{
										JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
										array.add(jsonObj);
									}
							}
							
				   }
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
	/*@RequestMapping("/activi_myseleone")
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
			List<Activitypicture>  cc=activitypictureSercice.selectlist(act.getId());
			int bm_num=0;
			int i=0;
			for (Activitypicture tt : cc) {
				i=Integer.parseInt(tt.getGrilexpense())+Integer.parseInt(tt.getChindenboy())+Integer.parseInt(tt.getOldboy());
				bm_num+=i;
				
			}
			act.setBm_num(bm_num);
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}*/
	@RequestMapping("/activi_myseleone")
	public @ResponseBody JsonElement activimyseleone(@RequestParam(value="pager",required=false) Pager<Activity> pager,HttpSession session){
		JsonObject json=new JsonObject();
		if(pager==null){
			pager=new Pager<Activity>();
		}
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		Sponsorlis span =  sponsorSercice.seletOnesponsor(currUser.getId());
		if(span==null){
			json.addProperty("msg", "活动主办方不存在");
			json.addProperty("succ", false);
			return json;
		}
		pager.getSearchMap().put("activiid", span.getId()+"");
		activityService.getpager(pager);
		List<Activity> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		json.add("arrays", array);
		for (Activity act : list) {
			List<Activitypicture>  cc=activitypictureSercice.selectlist(act.getId());
			int bm_num=0;
			int i=0;
			for (Activitypicture tt : cc) {
				i=tt.getOldboy()+tt.getChindenboy()+tt.getGrilexpense();
				bm_num+=i;
				
			}
			act.setBm_num(bm_num);
			Sponsorlis	spantt= sponsorSercice.seletOne(act.getSponsorid());
			if(spantt!=null){
				if(span.getTypeopp()==1){
				JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
				array.add(jsonObj);
				}
			}
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
	@RequestMapping("/activi_upou")
	public @ResponseBody JsonElement activiupou(
			@RequestParam(required=false)Integer id,
			@RequestParam(required=false)String iconFile,
			
			//@RequestParam(value="iconFile[]", required=false) MultipartFile iconFile[],
			@RequestParam(required=false)Integer sponsorid,
			@RequestParam(required=false)String activityname,
			@RequestParam(required=false)Integer activitycategory,
			@RequestParam(required=false)String activitylocation,
			@RequestParam(required=false)Integer number,
			@RequestParam(required=false)Integer children_expense,
			@RequestParam(required=false)Integer old_expense,
			@RequestParam(required=false)Integer gril_expense,
			@RequestParam(required=false)String apicture,
			//@RequestParam(value="apicture", required=false) CommonsMultipartFile apicture,
			
			@RequestParam(required=false)String activitycontent,
			@RequestParam(required=false)String starttime,
			@RequestParam(required=false)String outtime,
			@RequestParam(required=false)Integer type, //0 收   1 不收   AA费用

			@RequestParam(required=false)String lng,
			@RequestParam(required=false)String lat,HttpSession session){
		JsonObject json = new JsonObject();
		
		
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		Sponsorlis bfgs=sponsorSercice.seletOnesponsor(currUser.getId());
		if(bfgs==null){
			json.addProperty("error", "没有主办方");
			return json;
		}
		Activity activity=activityService.selectcal(id);
		if(activity.getTypeok()==0){
			activity.setActivityname(activityname);
			activity.setSponsorid(bfgs.getId());
			activity.setActivitycategory(activitycategory);
			activity.setActivitylocation(activitylocation);
			activity.setNumber(number);
			activity.setGril_expense(gril_expense);
			activity.setChildren_expense(children_expense);
			activity.setOld_expense(old_expense);
			activity.setActivitypicture(iconFile);
			activity.setCover(apicture);
			/*String corle=activityService.addPhotos(iconFile);
			activity.setActivitypicture("["+corle+"]");
			if(apicture.getSize() > 0) {
				try {
					String relativelyCardFrontPhoto = "/luoluo/user/userPic/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(apicture.getOriginalFilename());
					fileService.saveFile(apicture.getInputStream(), relativelyCardFrontPhoto); 
					activity.setCover(relativelyCardFrontPhoto);
				} catch (Exception e) {
					throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
				}
			} */
			//缺活动图片
			activity.setActivitycontent(activitycontent);
			activity.setType(1);//
			activity.setTypeok(1);
			activity.setZhuangttai(type);
			activity.setLng(lng);
			activity.setLat(lat);
			boolean bFlag = activityService.updatedo(activity);
			if(bFlag == true){ // 修改成功
				json.addProperty("succ", true);
			} else { // 修改失败
				json.addProperty("error", false);
			} 
		}else{
				json.addProperty("addlseorr", "你已经修改过一次了，不能重复修改");
			}
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
			//@RequestParam(value="iconFile[]", required=false) MultipartFile iconFile[],
			@RequestParam(required=false)String iconFile,
			@RequestParam(required=false)Integer sponsorid,
			@RequestParam(required=false)String activityname,
			@RequestParam(required=false)Integer activitycategory,
			@RequestParam(required=false)String activitylocation,
			@RequestParam(required=false)Integer number,
			@RequestParam(required=false)Integer children_expense,
			@RequestParam(required=false)Integer old_expense,
			@RequestParam(required=false)Integer gril_expense,
			@RequestParam(required=false)String apicture,
			//@RequestParam(value="apicture", required=false) CommonsMultipartFile apicture,
			@RequestParam(required=false)String activitycontent,
			@RequestParam(required=false)String starttime,
			@RequestParam(required=false)String outtime,
			@RequestParam(required=false)Integer type,
			@RequestParam(required=false)String city,
			@RequestParam(required=false)String lng,
			@RequestParam(required=false)String lat,HttpSession session){
			JsonObject json = new JsonObject();
			City cityc=cityService.selectdoBycitycode(city);
				User currUser = getSessionUser(session);
				if(currUser == null){
					throw new APICodeException(-4, "你还没登陆!");
				}	
				Sponsorlis bfgs=sponsorSercice.seletOnesponsor(currUser.getId());
				if(bfgs==null){
					json.addProperty("error", "没有主办方");
					return json;
				}

					Activity activity=new Activity();
					activity.setActivityname(activityname);
					activity.setSponsorid(bfgs.getId());
					activity.setActivitycategory(activitycategory);
					activity.setActivitylocation(activitylocation);
					activity.setNumber(number);
					activity.setGril_expense(gril_expense);
					activity.setChildren_expense(children_expense);
					activity.setOld_expense(old_expense);
					activity.setNowforetime(System.currentTimeMillis()/1000);
					activity.setStarttime(DateUtils.parse(starttime).getTime()/1000);
					activity.setOuttime(DateUtils.parse(outtime).getTime()/1000);
					activity.setActivitypicture(iconFile);
					activity.setCover(apicture);
					activity.setCity(cityc.getCode());
					/*String corle=activityService.addPhotos(iconFile);
					activity.setActivitypicture("["+corle+"]");
					
					if(apicture.getSize() > 0) {
						try {
							String relativelyCardFrontPhoto = "/luoluo/user/userPic/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(apicture.getOriginalFilename());
							fileService.saveFile(apicture.getInputStream(), relativelyCardFrontPhoto); 
							activity.setCover(relativelyCardFrontPhoto);
						} catch (Exception e) {
							throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
						}
					} */
					//缺活动图片
					activity.setActivitycontent(activitycontent);
					activity.setType(1);
					activity.setZhuangttai(type);
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
	//编辑的时候将值取出来
	@RequestMapping("/activity_edit")
	public @ResponseBody JsonElement activityEdit(@RequestParam(value = "id",required=false) Integer id){//活动的id
		JsonObject json = new  JsonObject();
		Activity t =  activityService.select(id);
		if(t!=null && t.getTypeok() == 0){
			//查看是否有人报名
			List<Activitypicture> list = activitypictureSercice.queryByAcitvity(t.getId());
			if(list!=null && list.size()>0){
				json.addProperty("succ", false);
				json.addProperty("msg", "不可更改,活动已经有人报名！");
			}
			//没人报名 且是第一次更改
			json.addProperty("succ", true);
			json.addProperty("msg", "可以编辑!");
			json.add("remark", getJsonInfo());
			json.add("data", getActivityDetail(t));
		}else{
			json.addProperty("succ", false);
			json.addProperty("msg", "不可更改,您已更改过一次！");
		}
		
		return json;
	}
	
	
	public JsonElement getActivityDetail(Activity t) {
		JsonObject json = new JsonObject();
		/**
		 * 活动封面（图片数组）
		 * 活动名称
		 * 活动类别
		 * 活动地址 （如果没换地址 经纬度不更改）
		 * 活动人数
		 * 开始时间
		 * 结束时间
		 * 男生费用 
		 * 女生费用 
		 * 儿童费用 
		 * 费用类型
		 * 内容编辑（图片数组）
		 * 内容编辑内容
		 */
		json.addProperty("id", t.getId());
		json.addProperty("sponsorid", t.getSponsorid());
		json.addProperty("cover", t.getCover());
		json.addProperty("name", t.getActivityname());
		json.addProperty("category", t.getActivitycategory());
		json.addProperty("location", t.getActivitylocation());
		json.addProperty("number", t.getNumber());
		json.addProperty("starttime", t.getStarttime());
		json.addProperty("outtime", t.getOuttime());
		json.addProperty("old", t.getOld_expense());
		json.addProperty("children", t.getChildren_expense());
		json.addProperty("girl", t.getGril_expense()); 
		json.addProperty("zhuangtai", t.getZhuangttai());//确定谁收费 费用类型
		json.addProperty("content", t.getActivitycontent());
		json.addProperty("picture", t.getActivitypicture());
		return json;
	}
	public JsonElement getJsonInfo() {
		JsonObject json = new JsonObject();
		/**
		 * 活动封面（图片数组）
		 * 活动名称
		 * 活动类别
		 * 活动地址 （如果没换地址 经纬度不更改）
		 * 活动人数
		 * 开始时间
		 * 结束时间
		 * 男生费用 
		 * 女生费用 
		 * 儿童费用 
		 * 费用类型
		 * 内容编辑（图片数组）
		 * 内容编辑内容
		 */
		json.addProperty("id", "id");
		json.addProperty("cover", "活动封面");
		json.addProperty("name", "活动名称");
		json.addProperty("category", "活动类别");
		json.addProperty("location", "活动地址");
		json.addProperty("number", "活动人数");
		json.addProperty("starttime","开始时间");
		json.addProperty("outtime","结束时间");
		json.addProperty("old", "男生费用");
		json.addProperty("children", "儿童费用 ");
		json.addProperty("girl", "女生费用"); 
		json.addProperty("zhuangtai", "费用类型");//确定谁收费 费用类型
		json.addProperty("content", "内容编辑内容");
		json.addProperty("picture", "内容编辑（图片数组）");
		return json;
	}
	/*
	 * 删除
	 */
	@RequestMapping("/activi_delete")
	public @ResponseBody JsonElement actividelete(
			@RequestParam(required=false) Integer id){
		List<Activitypicture> acc =activitypictureSercice.selectlist(id);
		JsonObject json = new JsonObject();
		if(acc.size()==0){
			Activity activity =activityService.selectcal(id);
			activity.setTypeok(5);
			boolean activitys = activityService.updatedo(activity);
			if(activitys){ // 添加成功
				json.addProperty("succ", true);
			} else { // 添加失败
				json.addProperty("succ", false);
			}
		}else{
			json.addProperty("msg", "不能删除活动，已存在报名人员");
			json.addProperty("succ", false);
		}
		return json;
	}
	
	@RequestMapping("/search_list")
	public @ResponseBody JsonElement searchlist(
			@RequestParam(value="pager",required=false) Pager<Activity> pager,
			@RequestParam(required=false) String search){
		if(pager==null){
			pager=new Pager<Activity>();
		}
		if(!StringUtils.isBlank(search)){
			pager.getSearchMap().put("name", search);
		}
		long z=System.currentTimeMillis()/1000;
		pager.getSearchMap().put("se", "se");
		activityService.getpager(pager);
		List<Activity> list = pager.getItemList();
		JsonArray array = new JsonArray();
		JsonObject json=new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		json.add("arrays", array);
		for (Activity act : list) {
			List<Activitypicture>  cc=activitypictureSercice.selectlist(act.getId());
			int bm_num=0;
			int i=0;
			for (Activitypicture tt : cc) {
				i=tt.getOldboy()+tt.getChindenboy()+tt.getGrilexpense();
				bm_num+=i;
				
			}
			act.setBm_num(bm_num);
			Sponsorlis	span= sponsorSercice.seletOne(act.getSponsorid());
				if(span!=null){
					if(span.getTypeopp()==1){
						if(act.getOuttime()<z){
							
						}else{
							JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
							array.add(jsonObj);
						}
				}
			}
		}
		return json;
	}
	
	@RequestMapping(value="/uploads",produces = "application/json; charset=utf-8")
	public @ResponseBody String dosave(@RequestParam(value="file[]", required=false) MultipartFile file[] ,//图片对象
			@RequestParam(value="folder",required=false) String folder,//文件夹名
			@RequestParam(value="callback",required=false) String callback,//跨域
			HttpServletRequest request){
		JsonObject json = new JsonObject();
		
			folder = (folder==null || folder == "") ? "folder" : folder;//如果上传的文件夹名为空，则默认为folder
			String corle=activityService.addPhotos(file);
			json.addProperty("succ", true);
			json.addProperty("url", "["+corle+"]");//返回物理存储地址
		
		if(StringUtils.isBlank(callback)){
			return json.toString();
		}
		return (callback + "(" + json.toString() + ")");
	}
	
	
	@RequestMapping(value="/upload",produces = "application/json; charset=utf-8")
	public @ResponseBody String dosavedo(@RequestParam(value="file", required=false) MultipartFile file ,//图片对象
			@RequestParam(value="folder",required=false) String folder,//文件夹名
			@RequestParam(value="callback",required=false) String callback,//跨域
			HttpServletRequest request){
		JsonObject json = new JsonObject();
		
		if(file.getSize()>0){
			folder = (folder==null || folder == "") ? "folder" : folder;//如果上传的文件夹名为空，则默认为folder
			String url =activityService.uploadImg(file,folder);//保存图片
			json.addProperty("succ", true);
			json.addProperty("url", url);//返回物理存储地址
		}else{
			json.addProperty("succ", false);
		}
		if(StringUtils.isBlank(callback)){
			return json.toString();
		}
		return (callback + "(" + json.toString() + ")");
	}
	/*
	 * 筛选
	 */
	@RequestMapping("/lete")
	public @ResponseBody JsonElement lete(@RequestParam(value="pager",required=false) Pager<Activity> pager,
			@RequestParam(required=false) Integer day, //天数
			@RequestParam(required=false) long idtime//今天 明天 后天
			){
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
		int ss=(int) Math.abs((act.getOuttime()-act.getStarttime())/(60*60*24));
		int ff=(int) Math.abs((act.getStarttime()-idtime)/(60*60*24));
		//筛选信息
		Sponsorlis	span= sponsorSercice.seletOne(act.getSponsorid());
			if(span!=null){
				if(span.getTypeopp()==1){
					if(ff==0){
						if(ss==day){
							JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
							array.add(jsonObj);
							}
					}else if(ff==1){
						if(ss==day){
							JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
							array.add(jsonObj);
							}
					}else if(ff==2){
							if(ss==day){
								JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
								array.add(jsonObj);
							}
					}else{
							if(ss==day){
									JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
									array.add(jsonObj);
							}
					}
				}
			}
		}
		return json;
	}
}