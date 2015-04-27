package com.rpframework.website.yunpiaopiao.act.home;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpframework.website.yunpiaopiao.service.UserService;

@Controller
@RequestMapping("/")
public class IndexAct {
	
	@Resource UserService userService;
	
	@RequestMapping
	public String execute(){

		return "/home/index";
	}
	
	@RequestMapping("/register")
	public String register(){
		
		
		
		
		return "/home/register";
	}
	
	@RequestMapping("/reghone")
	public String regPhone(){
		
		return "/home/regPhone";
	}
	
	
	@RequestMapping("/login")
	public String login(){
		
		return "/home/login";
	}

}
