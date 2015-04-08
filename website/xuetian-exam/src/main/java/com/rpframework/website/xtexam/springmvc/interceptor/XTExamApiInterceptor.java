package com.rpframework.website.xtexam.springmvc.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rpframework.core.utils.SpringUtils;
import com.rpframework.core.utils.TokenUtils;
import com.rpframework.website.xtexam.exception.NoLoginException;
import com.rpframework.website.xtexam.utils.XTExamConfig;

public class XTExamApiInterceptor implements HandlerInterceptor {
	XTExamConfig xtExamConfig = SpringUtils.getBean(XTExamConfig.class);
	TokenUtils tokenUtils = new TokenUtils(xtExamConfig.tokenkey);
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		logger.info("preHandle....");
//		Object adminUserObj = request.getSession().getAttribute(BaseAct.SESSION_ADMIN_USER_KEY);
//		if(adminUserObj == null) {
//			throw new NoLoginException();
//			//return false;
//		}
		
		Cookie cookies[] = request.getCookies();
		String token = "";
		String tokenNext = "";
		String password = "";

		for (Cookie c : cookies) {
			if (c.getName().equals("token")) {
				token = c.getValue();
			} else if (c.getName().equals("next")) {
				tokenNext = c.getValue();
			} else if (c.getName().equals("password")) {
				password = c.getValue();
			} else if (c.getName().equals("userName")) {
				request.setAttribute("userName", c.getValue());
			}
		}

		if (!tokenUtils.validToken(token, password) && !tokenUtils.validToken(tokenNext, password)) {
			throw new NoLoginException();
//			return false;
		}

		Date now = new Date(System.currentTimeMillis());
		token = tokenUtils.getToken(password, now);
		tokenNext = tokenUtils.getToken(password, TokenUtils.getNextHour(now));
		Cookie tokenCookie = new Cookie("token", token);
		tokenCookie.setPath("/");
		Cookie nextTokenCookie = new Cookie("next", tokenNext);
		nextTokenCookie.setPath("/");
		// 将两个新token发给客户端
		response.addCookie(tokenCookie);
		response.addCookie(nextTokenCookie);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		logger.info("postHandle....");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		logger.info("afterCompletion....");
	}

}
