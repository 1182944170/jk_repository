package com.rpframework.website.yunpiaopiao.act.home;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.MD5;
import com.rpframework.module.common.service.SlideshowService;
import com.rpframework.website.yunpiaopiao.domian.User;
import com.rpframework.website.yunpiaopiao.service.CinemaService;
import com.rpframework.website.yunpiaopiao.service.MovieService;
import com.rpframework.website.yunpiaopiao.service.UserService;

@Controller
@RequestMapping("/")
public class IndexAct extends  BaseAct{

	@Resource
	MovieService movieService; // 电影

	@Resource
	CinemaService cinemaService; // 影院

	@Resource
	SlideshowService slideshowService; // 幻灯片

	@Resource
	UserService userService; // 用户

	@RequestMapping
	public String execute() {

		return "/home/index";
	}

	@RequestMapping("/register")
	public String register() {

		return "/home/register";
	}

	@RequestMapping(value = "doRegister", method = RequestMethod.POST)
	public String doRegister(@RequestParam(value = "email") String email,
			@RequestParam(value = "nickName") String nickName,
			@RequestParam(value = "pwd") String pwd,
			@RequestParam(value = "confPwd") String confPwd,
			@RequestParam(value = "code") String code, HttpSession session,
			HttpServletRequest request, RedirectAttributes attr) {

		if (StringUtils.isNotBlank(email) && StringUtils.isNotBlank(nickName)
				&& StringUtils.isNotBlank(pwd)
				&& StringUtils.isNotBlank(confPwd)
				&& StringUtils.isNotBlank(code)) {
				
			if(null != userService.findUserByEmail(email)){
				this.setErrorMsg("邮箱已经存在了!", attr);
				return redirect("/register");
				
			}
			if(null != userService.findUserByNickName(nickName)){
				this.setErrorMsg("昵称已经存在了!", attr);
				return redirect("/register");
			}
			if(!StringUtils.equals(pwd, confPwd)){
				this.setErrorMsg("两次密码输入不一致!", attr);
				return redirect("/register");
			}
			
			if(!StringUtils.equals(session.getAttribute("code").toString(), code)){
				this.setErrorMsg("验证码错误!", attr);
				return redirect("/register");
			}
			
			//用户注册
			User user = new User();
			user.setEmail(email);
			user.setNickName(nickName);
			user.setPwd(confPwd); //密码加密
			
			userService.insert(user);
			
		}else{
			
			this.setErrorMsg("数据错误!", attr);
			return redirect("/register");
		}

		return "/home/register";
	}

	@RequestMapping("/reghone")
	public String regPhone() {

		return "/home/regPhone";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		System.out.println("------doLogin---get----");
		return "/home/login";
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin() {
		System.out.println("---doLogin---");
		return "";
	}

	// 注册校验
	@SuppressWarnings("unused")
	public void check(HttpServletRequest request, HttpServletResponse response) {

		String email = request.getParameter("email");

		String nickName = request.getParameter("nickName");

		String code = request.getParameter("code");

	}

}
