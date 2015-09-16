package com.rpframework.website.luoluo.act.admin;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Sponsor;
import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.ClassificationService;
import com.rpframework.website.luoluo.service.SponsorService;

@Controller
@RequestMapping("admin/spons")
public class AdminSponsorAct extends AdminAct{
	@Resource SponsorService sponsorService;
	@Resource ActivityService activityService;
	@Resource ClassificationService classificationService;
	/**
	 * 显示个人
	 * @param pager
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("individuallist")
	public String individuallist(@RequestParam(value="pager", required=false) Pager<Sponsor> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager==null){
			pager=new Pager<Sponsor>();
		}
		pager.getSearchMap().put("teyeid", String.valueOf(1));
		pager=sponsorService.getpager(pager);
	
		model.put("pager", pager);
		return this.doPackageURI("sponsor/list");
	}
	/**
	 * 显示公司
	 * @param pager
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("companylist")
	public String companylist(@RequestParam(value="pager", required=false) Pager<Sponsor> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager==null){
			pager=new Pager<Sponsor>();
		}
		pager.getSearchMap().put("teyeid", String.valueOf(2));
		pager=sponsorService.getpager(pager);
		
		model.put("se","ff");
		model.put("pager", pager);
		return this.doPackageURI("sponsor/list");
	}
	//单个用户
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable(value="id") Integer id, Map<Object, Object> model, RedirectAttributes attr){
		if(id==null){
			throw new AdminIllegalArgumentException("不存在该用户");
		}
		Sponsor labelOne = sponsorService.seletOne(id);
		model.put("user", labelOne);
		return this.doPackageURI("sponsor/edit");
	}
	
}
