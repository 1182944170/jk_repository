package com.rpframework.website.luoluo.act.admin;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.api.FileService;
import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.module.adminbase.domain.AdminUser;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.Banscity;
import com.rpframework.website.luoluo.domain.Classification;
import com.rpframework.website.luoluo.domain.Sponsorlis;
import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.BanscityService;
import com.rpframework.website.luoluo.service.ClassificationService;
import com.rpframework.website.luoluo.service.SponsorService;

@Controller
@RequestMapping("admin/actcy")
public class AdminActivityAct extends AdminAct{
	
	@Resource BanscityService banscityService;
	@Resource FileService fileService;
	@Resource ActivityService activityService;
	@Resource SponsorService sponsorlisService;
	@Resource ClassificationService classificationService;

	
	//显示页面
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager<Activity> pager, Map<Object, Object> model, RedirectAttributes attr,HttpSession session){
		if(pager==null){
			pager=new Pager<Activity>();
		}
		AdminUser admin = (AdminUser) session.getAttribute(SESSION_ADMIN_USER_KEY);
		if(!admin.getAdminRole().getId().equals(1)){
			pager.getSearchMap().put("cityoles", admin.getCityCode());
		}
		//pager.getSearchMap().put("se", "se"); //后台没有模糊搜索的功能
		List<Classification> cal=classificationService.queryAll();
		pager=activityService.getpager(pager);
		List<Activity> list=pager.getItemList();
		for(Activity act :list){
			Sponsorlis sponsorlis=sponsorlisService.seletOne(act.getSponsorid());
			if(sponsorlis==null){
				act.setBm_num(0);
			}else{
				act.setBm_num(sponsorlis.getTypeopp());	
			}
		}
		model.put("cal", cal);
		model.put("pager", pager);
		return this.doPackageURI("activity/list");
	}
	@RequestMapping("/listchenggong")
	public String listchenggong(@RequestParam(value="pager", required=false) Pager<Activity> pager, Map<Object, Object> model, RedirectAttributes attr,HttpSession session){
		if(pager==null){
			pager=new Pager<Activity>();
		}
		AdminUser admin = (AdminUser) session.getAttribute(SESSION_ADMIN_USER_KEY);
		if(!admin.getAdminRole().getId().equals(1)){
			pager.getSearchMap().put("cityoles", admin.getCityCode());
		}
		pager.getSearchMap().put("se", "se");
		List<Classification> cal=classificationService.queryAll();
		pager=activityService.getpager(pager);
		List<Activity> list=pager.getItemList();
		long i=System.currentTimeMillis()/1000;
		List<Activity> acc=new ArrayList<Activity>();
		for(Activity act :list){
			if(act.getOuttime()<=i){
					acc.add(act);
			}
		}
		model.put("cal", cal);
		model.put("pager", acc);
		return this.doPackageURI("activity/jieshulist");
	}
	
	
	
	
	//查询分类的内容
	@RequestMapping("/{id}/listOlyeone")
	public String listOlyeone(@PathVariable Integer id, @RequestParam(value="pager", required=false) Pager<Activity> pager, Map<Object, Object> model, RedirectAttributes attr,HttpSession session){
		if(pager==null){
			pager=new Pager<Activity>();
		}
		AdminUser admin = (AdminUser) session.getAttribute(SESSION_ADMIN_USER_KEY);
		if(!admin.getAdminRole().getId().equals(1)){
			pager.getSearchMap().put("cityCode", admin.getCityCode());
		}
		pager.getSearchMap().put("se", "se");
		List<Classification> cal=classificationService.queryAll();
		pager.getSearchMap().put("calid",  String.valueOf(id));
		pager=activityService.getpager(pager);
		List<Activity> list=pager.getItemList();
		for(Activity act :list){
			Sponsorlis sponsorlis=sponsorlisService.seletOne(act.getSponsorid());
			if(sponsorlis==null){
				act.setBm_num(0);
			}else{
				act.setBm_num(sponsorlis.getTypeopp());	
			}
		}
		model.put("cal", cal);
		model.put("pager", pager);
		return this.doPackageURI("activity/list");
	}
	
	//跳转添加页面
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable Integer id, Map<Object, Object> model,RedirectAttributes attr){
		Activity activity = activityService.selectcal(id);
		if(activity == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + id);
		}
		List<Banscity> bancity=banscityService.selectall();
		Classification fitlist=classificationService.selectcal(activity.getActivitycategory());
		model.put("oop", activity);
		model.put("fitlist", fitlist);
		model.put("bancity", bancity);
		return this.doPackageURI("activity/edit");
		//return this.add(attr, model);
	}
	//跳转添加页面
	@RequestMapping("/{id}/zle")
	public String zle(@PathVariable Integer id, Map<Object, Object> model,RedirectAttributes attr){
		Activity activity = activityService.selectcal(id);
		if(activity == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + id);
		}
		List<Banscity> bancity=banscityService.selectall();
		Classification fitlist=classificationService.selectcal(activity.getActivitycategory());
		model.put("oop", activity);
		model.put("fitlist", fitlist);
		model.put("bancity", bancity);
		return this.doPackageURI("activity/addlesore");
		//return this.add(attr, model);
	}
	
	@RequestMapping("/add")
	public String add(@RequestParam(value="pager", required=false) Pager<Classification> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager<Classification>();
		}
		List<Banscity> bancity=banscityService.selectall();
		pager = classificationService.getPager(pager);
		model.put("pager", pager);
		model.put("bancity", bancity);
		return this.doPackageURI("activity/addlse");
	}
		

	
	/**
	 * 修改信息
	 * @param activity
	 * @param model
	 * @param fitlist
	 * @param iconFile
	 * @param attr
	 * @return
	 */
	@RequestMapping("/dosave")
	public String dosave(@ModelAttribute Activity activity, Map<Object, Object> model,
			RedirectAttributes attr){
	
		activity=activityService.selectcal(activity.getId());

		if(activity.getType()==1){
			activity.setType(0);
			activityService.updatedo(activity);
		}else if(activity.getType()==0){
			activity.setType(1);
			activityService.updatedo(activity);
		}
		if (NumberUtils.isValid(activity.getId())) {// update
			setInfoMsg("审核成功！", attr);
		} else {// insert
			setInfoMsg("审核成功！", attr);
		}
		return redirect("list");
	}

	
	/**
	 * 添加用户
	 * @param activity
	 * @param model
	 * @param attr
	 * @param iconFile
	 * @return
	 */
	@RequestMapping("/addsave")
	public String addsave(@ModelAttribute Activity activity,
			@RequestParam(value="starttimeString")String starttimeString,
			@RequestParam(value="outtimeString")String outtimeString,
			Map<Object, Object> model, RedirectAttributes attr,
			@RequestParam(value="iconFile", required=false) CommonsMultipartFile iconFile
			)throws Exception{
		
		if(StringUtils.isBlank(starttimeString) ||StringUtils.isBlank(outtimeString)){
			throw new AdminIllegalArgumentException("请选择时间");
		}
		if(activity.getCity()==null){
			throw new AdminIllegalArgumentException("城市不存在");
		}
		activity.setStarttime(DateUtils.parse(starttimeString).getTime()/1000);
		activity.setOuttime(DateUtils.parse(outtimeString).getTime()/1000);
		if(iconFile.getSize() > 0 ) { // 判断 icon 大小是否大于0
			try {
				String relativelyPath = "/fenl/" + NumberUtils.random(3) + iconFile.getOriginalFilename(); // new 随即产生随即4位数开头的一个相对路径文件名
				fileService.saveFile(iconFile.getInputStream(), relativelyPath);  // 保存文件
				activity.setCover(relativelyPath); // 设置相对路径
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
			
		} else {
			
		}
		activity.setSponsorid(1);
		activity.setNowforetime(System.currentTimeMillis()/1000);
		activity.setType(0);
		activity.setTypeok(0);
		activityService.insertone(activity);
		setInfoMsg("添加成功！", attr);
		return redirect("/admin/actcy/list");
	}
	/**
	 * 添加用户
	 * @param activity
	 * @param model
	 * @param attr
	 * @param iconFile
	 * @return
	 */
	@RequestMapping("/fffsave")
	public String fffsave(@ModelAttribute Activity activity,
			@RequestParam(value="starttimeString")String starttimeString,
			@RequestParam(value="outtimeString")String outtimeString,
			Map<Object, Object> model, RedirectAttributes attr,
			@RequestParam(value="iconFile", required=false) CommonsMultipartFile iconFile
			)throws Exception{
		
		if(StringUtils.isBlank(starttimeString) ||StringUtils.isBlank(outtimeString)){
			throw new AdminIllegalArgumentException("请选择时间");
		}
		if(activity.getCity()==null){
			throw new AdminIllegalArgumentException("城市不存在");
		}
		activity.setStarttime(DateUtils.parse(starttimeString).getTime()/1000);
		activity.setOuttime(DateUtils.parse(outtimeString).getTime()/1000);
		if(iconFile.getSize() > 0 ) { // 判断 icon 大小是否大于0
			try {
				String relativelyPath = "/fenl/" + NumberUtils.random(3) + iconFile.getOriginalFilename(); // new 随即产生随即4位数开头的一个相对路径文件名
				fileService.saveFile(iconFile.getInputStream(), relativelyPath);  // 保存文件
				activity.setCover(relativelyPath); // 设置相对路径
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
			
		} 
		activity.setSponsorid(1);
		activity.setNowforetime(System.currentTimeMillis()/1000);
		activity.setType(0);
		activity.setTypeok(0);
		activityService.updatedo(activity);
		setInfoMsg("添加成功！", attr);
		return redirect("/admin/actcy/list");
	}
	/**
	 * 删除用户
	 * @param id
	 * @param attr
	 * @return
	 */
	@RequestMapping("/{id}/deletUser")
	public String deletUser(@PathVariable Integer id,RedirectAttributes attr){
		Activity activity=activityService.selectcal(id);
		activity.setTypeok(5);
		activityService.updatedo(activity);
		setInfoMsg("删除成功！", attr);
		return redirect("/admin/actcy/list");
	}
	
	@RequestMapping("/{id}/chenggongUser")
	public String chenggongUser(@PathVariable Integer id,RedirectAttributes attr){
		Activity activity=activityService.selectcal(id);
		Sponsorlis sponsorlis=sponsorlisService.seletOnesponsor(activity.getSponsorid());
		sponsorlis.setGoint(sponsorlis.getGoint()*1+1);
		activity.setTypeok(6);
		sponsorlisService.updatedo(sponsorlis);
		activityService.updatedo(activity);
		setInfoMsg("确认成功！", attr);
		return redirect("/admin/actcy/listchenggong");
	}
}
