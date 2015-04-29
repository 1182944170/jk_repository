package com.rpframework.website.yunpiaopiao.act.home;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.BaseAct;
import com.rpframework.module.common.service.SlideshowService;
import com.rpframework.website.yunpiaopiao.service.CinemaService;
import com.rpframework.website.yunpiaopiao.service.MovieService;
import com.rpframework.website.yunpiaopiao.service.UserService;

@Controller
@RequestMapping("/")
public class IndexAct {
	
	
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
	
	
	@RequestMapping(value="doRegister",method=RequestMethod.POST)
	public String doRegister(
			@RequestParam(value="email") String email,
			@RequestParam(value="nickName") String nickName,
			@RequestParam(value="pwd") String pwd,
			@RequestParam(value="confPwd") String confPwd,
			@RequestParam(value="code") String code,HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		
		System.out.println();
		
		
		//return  "/home/registerSuccess";
		return "/home/register";
	}
	
	
	@RequestMapping("/reghone")
	public String regPhone(){
		
		return "/home/regPhone";
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		System.out.println("------doLogin---get----");
		return "/home/login";
	}
	
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	public String doLogin(){
		System.out.println("---doLogin---");
		return "";
	}

}
