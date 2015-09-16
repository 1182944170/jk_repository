package com.rpframework.website.luoluo.springmvc;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rpframework.core.InitServlet;
import com.rpframework.module.adminbase.event.RoleAdminAuthResVerifyEvent;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.service.UserService;

public class UserInterceptor implements HandlerInterceptor {
	final Logger logger = LoggerFactory.getLogger(getClass());
	RoleAdminAuthResVerifyEvent roleAdminAuthResVerifyEvent = new RoleAdminAuthResVerifyEvent();
	@Resource UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		User userObj = (User) request.getSession().getAttribute("发发的是");
		if(userObj == null) {
			response.sendRedirect(InitServlet.CTX + "/login");
			return false;
		}
		
		
		//登陆的用户做鉴权操作
		/**
		 * AntPathMatcher antPathMatcher = new AntPathMatcher();
			boolean match = antPathMatcher.match(pattern, path);
		 */
		
		
		String requestURI = StringUtils.replace(request.getRequestURI(), InitServlet.CTX+"/", "");
		logger.info("requestURI....{}", requestURI);
		
		
		requestURI = StringUtils.replace(request.getRequestURI(), InitServlet.CTX+"/", "");
		logger.info("requestURI....{}", requestURI);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
