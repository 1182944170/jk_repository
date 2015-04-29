package com.rpframework.website.yunpiaopiao.act.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class EntityAct {

	@RequestMapping("/entity")
	public String execute(){
		
		return "/home/entity/index";
	}
}
