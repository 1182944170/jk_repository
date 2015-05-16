package com.rpframework.module.user.act.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.module.user.act.UserModuleBaseAct;
import com.rpframework.module.user.domain.CfgScore;
import com.rpframework.module.user.service.CfgScoreService;

@Controller
@RequestMapping("/admin/common/cfg_score")
public class AdminCfgScoreAct extends UserModuleBaseAct{
	@Resource CfgScoreService cfgScoreService;
	
	@RequestMapping("/list")
	public String list(Map<Object, Object> model, RedirectAttributes attr){
		List<CfgScore> list = cfgScoreService.queryAll();
		model.put("list", list);
		return this.doPackageURI("cfg_score/list");
	}
	
	@RequestMapping("/{level}/edit")
	public String edit(@PathVariable Integer level, Map<Object, Object> model,RedirectAttributes attr){
		CfgScore cfgScore = cfgScoreService.select(level);
		Assert.notNull(cfgScore, "不存在的level:" + level);
		model.put("cfgScore", cfgScore);
		return this.add(attr, model);
	}
	
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		return this.doPackageURI("cfg_score/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute CfgScore cfgScore, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		cfgScoreService.doSaveOrUpdate(cfgScore);
		setInfoMsg("操作成功！", attr);
		return redirect("list");
	}
}
