package com.rpframework.website.edongwang.act;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.module.adminbase.act.AdminBaseAct;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.Houses;
import com.rpframework.website.edongwang.service.AdminHouseService;
import com.rpframework.website.edongwang.service.AdminMemberService;

@Controller
@RequestMapping("/admin/house")
public class AdminHouseAct extends AdminBaseAct{


	@Resource AdminHouseService adminHouseService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pager", required = false) Pager pager,Map<Object, Object> model, RedirectAttributes attr) {
		if (pager == null) {
			pager = new Pager();
		}
		pager = adminHouseService.getHousesPager(pager);
		model.put("pager", pager);
		return "house/list";
	}

	
	@RequestMapping("doSave")
	public String doSaveOrUpdate(@ModelAttribute Houses houses, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		
		
		
		return "house/edit";
	}
	
}
