package com.rpframework.website.luoluo.act.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.ClaGoods;
import com.rpframework.website.luoluo.domain.Classification;

import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.ClassificationService;

@Controller
@RequestMapping("admin/actcy")
public class AdminActivityAct extends AdminAct{
	
	@Resource ActivityService activityService;
	@Resource ClassificationService classificationService;
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
		List<Classification> fitlist = new ArrayList<Classification>();
		if(CollectionUtils.isNotEmpty(activity.getClassList())) {
			for (ClaGoods claGoods :activity.getClassList()) {
				fitlist.add(claGoods.getClassification());
			}
		}
		model.put("oop", activity);
		model.put("fitlist", fitlist);
		return this.add(attr, model);
	}
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		List<Classification> claList =classificationService.queryAll();

		model.put("claList", claList);
		return this.doPackageURI("activity/edit");
	}
}
