package com.rpframework.website.yunnongxin.act.admin;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/type")
public class AdminTypeAct extends AdminAct{

	@RequestMapping
	public String execute(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		
		pager = new Pager();
		model.put("pager", pager);
		return this.doPackageURI("type/list");
	}
	
	
	@RequestMapping("/add")
	public String add(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		
		pager = new Pager();
		model.put("pager", pager);
		return this.doPackageURI("type/edit");
	}
	
	
	
}
