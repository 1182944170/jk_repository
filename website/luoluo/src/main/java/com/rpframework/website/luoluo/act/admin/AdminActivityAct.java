package com.rpframework.website.luoluo.act.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.ClaGoods;
import com.rpframework.website.luoluo.domain.Classification;
import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.ClaGoodsService;
import com.rpframework.website.luoluo.service.ClassificationService;

@Controller
@RequestMapping("admin/actcy")
public class AdminActivityAct extends AdminAct{
	
	@Resource FileService fileService;
	@Resource ActivityService activityService;
	@Resource ClassificationService classificationService;
	@Resource  ClaGoodsService   claGoodsService;
	
	//显示页面
	@RequestMapping("list")
	public String list(@RequestParam(value="pager", required=false) Pager<Activity> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager==null){
			pager=new Pager<Activity>();
		}
		pager=activityService.getpager(pager);
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
	
		Classification fitlist=classificationService.selectcal(activity.getActivitycategory());
		model.put("oop", activity);
		model.put("fitlist", fitlist);
		return this.doPackageURI("activity/edit");
		//return this.add(attr, model);
	}
	
	@RequestMapping("/add")
	public String add(@RequestParam(value="pager", required=false) Pager<Classification> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager<Classification>();
		}
		pager = classificationService.getPager(pager);
		model.put("pager", pager);
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
			@RequestParam(value="fitlist", required=false) Integer[] fitlist,
			@RequestParam(value="iconFile", required=false) CommonsMultipartFile iconFile,
			RedirectAttributes attr){
		List<ClaGoods> clads= new ArrayList<ClaGoods>();
		if(fitlist != null) {
			for (Integer claId : fitlist) {
				if(NumberUtils.isValid(claId)) {
					ClaGoods claGoods=new ClaGoods();
					Classification   classDO=new Classification();
					classDO.setId(claId);
					claGoods.setClassification(classDO);
					claGoods.setGoodId(activity.getId());
					clads.add(claGoods);
				}
			}
		}
		activity.setClassList(clads);
		claGoodsService.insertOrUpdateByMovie(activity.getId(), activity.getClassList());
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
		if (NumberUtils.isValid(activity.getId())) {// update
			setInfoMsg("更新操作成功！", attr);
		} else {// insert
			setInfoMsg("新增操作成功！", attr);
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
	public String addsave(@ModelAttribute Activity activity,Map<Object, Object> model, RedirectAttributes attr,
			@RequestParam(value="iconFile", required=false) CommonsMultipartFile iconFile
			){
		
		if(StringUtils.isBlank(activity.getStarttime())||StringUtils.isBlank(activity.getOuttime())){
			throw new AdminIllegalArgumentException("开始时间和结束时间不能为空");
		}
		
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
		activity.setNowforetime(System.currentTimeMillis()/1000);
		activity.setType(0);
		activity.setTypeok(0);
		activityService.insertone(activity);
		
		return redirect("admin/activity/list");
	}
	
}
