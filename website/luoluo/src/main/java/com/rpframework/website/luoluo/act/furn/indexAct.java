package com.rpframework.website.luoluo.act.furn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpframework.core.BaseAct;

@Controller
@RequestMapping("")
public class indexAct extends BaseAct{
	@RequestMapping("/index")
	public String index(){
		return "admin/index";
	}
}
