package com.rpframework.website.yunpiaopiao.act.home;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rpframework.core.BaseAct;
import com.rpframework.module.common.service.SlideshowService;
import com.rpframework.website.yunpiaopiao.service.CinemaService;
import com.rpframework.website.yunpiaopiao.service.MovieService;
import com.rpframework.website.yunpiaopiao.service.UserService;

@Controller
@RequestMapping("/")
public class IndexAct extends BaseAct{
	
	
	@Resource MovieService movieService; //电影
	
	@Resource CinemaService cinemaService; //影院
	
	@Resource SlideshowService slideshowService; //幻灯片
	
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
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		
		return "/home/login";
	}
	
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	public String doLogin(){
		System.out.println("---doLogin---");
		return "";
	}

}
