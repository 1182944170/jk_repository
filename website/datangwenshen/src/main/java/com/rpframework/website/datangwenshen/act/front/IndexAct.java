package com.rpframework.website.datangwenshen.act.front;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpframework.core.BaseAct;
import com.rpframework.website.datangwenshen.utils.DaTangWenShenConfig;

@Controller
@RequestMapping("/")
public class IndexAct extends BaseAct {
	@Resource DaTangWenShenConfig  daTangWenShenConfig;
	
	@RequestMapping
	public String execute() {
		return index();
	}
	
	@RequestMapping("index")
	public String index() {
		return "/" + daTangWenShenConfig.STYLE + "/index";
	}
}


