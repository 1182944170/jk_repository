package com.rpframework.website.xtexam.act.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpframework.core.BaseAct;

@Controller
@RequestMapping("/api")
public class XTExamApiAct extends BaseAct {
	
	@RequestMapping("/login")
	public String login(){
		return "api/login";
	}
}
