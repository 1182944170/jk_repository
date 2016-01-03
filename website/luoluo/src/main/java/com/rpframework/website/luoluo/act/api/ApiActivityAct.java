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
import com.rpframework.website.luoluo.service.ClassificationService;
import com.rpframework.website.luoluo.service.SponsorService;
import com.rpframework.website.luoluo.service.UserService;



@Controller
@RequestMapping("api/activi")
public class ApiActivityAct extends BaseAct{
	Gson gson = new Gson();
	private static final double EARTH_RADIUS = 6378137;
	@Resource UserService   userService ;
	@Resource ActivityService   activityService ;
	@Resource ActivitypictureSercice activitypictureSercice;
	@Resource SponsorService sponsorSercice;
	@Resource ClassificationService classificationService;
	@Resource FileService fileService;
	private static double rad(double d){
	      return d * Math.PI / 180.0;
   }
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
			
			List<Activitypicture>  cc=activitypictureSercice.selectlist(act.getId());
			int bm_num=0;
			int i=0;
			for (Activitypicture tt : cc) {
				if("".equals(tt.getGrilexpense())){
					tt.setGrilexpense(0+"");
				}else if("".equals(tt.getChindenboy())){
					tt.setChindenboy(0+"");
				}else if("".equals(tt.getOldboy())){
					tt.setOldboy(0+"");
				}
				i=Integer.parseInt(tt.getGrilexpense())+Integer.parseInt(tt.getChindenboy())+Integer.parseInt(tt.getOldboy());
				bm_num+=i;
				
			}
			act.setBm_num(bm_num);
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
	/**
	 * 通过名字，编号查询
	 * @param pager
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("/earch_list")
	public @ResponseBody JsonElement earchlist(@RequestParam String search 
			) throws ParserException, InterruptedException{
		 List<Activity> atcuname=activityService.selectname(search);
		 JsonArray array = new JsonArray();
		 JsonObject json=new JsonObject();
		 json.add("arrays", array);
		 if(atcuname.size()==0){
			 List<Activity> atcunumber=activityService.selectnumbers(search);
			 if(atcunumber.size()==0){
				 json.addProperty("error", false);
				 return json;
			 }else{
				 for (Activity act : atcunumber) {
						JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
						array.add(jsonObj);
					} 
				 System.out.println("user_list: "+json.toString());
				 return json;
			 }
		 }else{
			 for (Activity act : atcuname) {
			
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
		 }
	}

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
		json.addProperty("id", activity.getId());
		json.addProperty("sponsorid", activity.getSponsorid());
		json.addProperty("cover", activity.getCover());
		json.addProperty("activitynumber", activity.getActivitynumber());
		json.addProperty("activitycategory", activity.getActivitycategory());
		json.addProperty("activityname", activity.getActivityname());
		json.addProperty("activitylocation", activity.getActivitylocation());
		json.addProperty("number", activity.getNumber());
		json.addProperty("children_expense", activity.getChildren_expense());
		json.addProperty("old_expense", activity.getOld_expense());
		json.addProperty("gril_expense", activity.getGril_expense());
		json.addProperty("activitycontent", activity.getActivitycontent());
		json.addProperty("starttime", activity.getStarttime());
		json.addProperty("outtime", activity.getOuttime());
		json.addProperty("nowforetime", activity.getNowforetime());
		json.addProperty("lat", activity.getLat());
		json.addProperty("type", activity.getType());
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
		List<Activitypicture> activitypict=activitypictureSercice.selectlist(activiid);
		int bm_num=0;
		for (Activitypicture act : activitypict) {
			int i=0;
				i=Integer.parseInt(act.getGrilexpense())+Integer.parseInt(act.getChindenboy())+Integer.parseInt(act.getOldboy());
				bm_num+=i;
			act.setBm_num(bm_num);
			
		}
		json.addProperty("bm_num", bm_num);
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
				i=Integer.parseInt(act.getGrilexpense())+Integer.parseInt(act.getChindenboy())+Integer.parseInt(act.getOldboy());
				bm_num+=i;
			act.setBm_num(bm_num);
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		return json;
	}
	
	@RequestMapping("/jintweidu")
	public @ResponseBody JsonElement jintweidu(@RequestParam(value="pager",required=false) Pager<Activity> pager,
			@RequestParam(required=false) String lat,
			@RequestParam(required=false) String lng,
			HttpSession session){
		if(pager==null){
 			pager=new Pager<Activity>();
 		}
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
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
			double c=rad(Double.parseDouble(act.getLat()));
			double d=rad(Double.parseDouble(act.getLng()));
			double s=rad(Double.parseDouble(lat));
			double f=rad(Double.parseDouble(lng));
			 double a = c - s;
			 double tow = d - f;
			 double tt = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
					     Math.cos(c)*Math.cos(d)*Math.pow(Math.sin(tow/2),2)));
			 tt = tt * EARTH_RADIUS;
			 tt = Math.round(tt * 10000) / 10000;
				   for(int i=100 ;i<=500 ;i+=100){
						if(tt<i){
							   JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
							   jsonObj.addProperty("mishu", i+"米以内");
								array.add(jsonObj);
								break;
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
			@RequestParam(value="pager",required=false) Pager<Activity> pager 
			){
		if(pager==null){
 			pager=new Pager<Activity>();
 		}
		pager.getSearchMap().put("calid", String.valueOf(activitycategoryid));
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
		pager.getSearchMap().put("activiid", span.getId()+"");
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
			@RequestParam(required=false)Integer type,

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
			activity.setType(0);
			activity.setTypeok(1);
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
					activity.setType(0);
					
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
		List<Activitypicture> acc =activitypictureSercice.selectlist(id);
		JsonObject json = new JsonObject();
		if(acc.size()==0){
			boolean activity = activityService.deletesell(id);
			if(activity){ // 添加成功
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
		return json;
	}
}