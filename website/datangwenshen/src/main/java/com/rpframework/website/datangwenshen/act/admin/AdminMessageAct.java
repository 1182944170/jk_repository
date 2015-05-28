package com.rpframework.website.datangwenshen.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.api.FileService;
import com.rpframework.utils.Pager;
import com.rpframework.website.datangwenshen.service.MessageService;

@Controller
@RequestMapping("/admin/message")
public class AdminMessageAct extends AdminAct {

	@Resource MessageService messageService;
	@Resource FileService fileService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = messageService.getPager(pager);
		model.put("pagerMessage", pager);
		return this.doPackageURI("message/list");
	}
}
