package com.rpframework.website.xtexam.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.core.utils.TokenUtils;
import com.rpframework.website.xtexam.domain.XTUser;
import com.rpframework.website.xtexam.service.XTUserService;
import com.rpframework.website.xtexam.utils.XTExamConfig;

public class XTExamApiInterceptor implements HandlerInterceptor {
	XTExamConfig xtExamConfig = null;
	TokenUtils tokenUtils = null;
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(xtExamConfig == null) {
			xtExamConfig = SpringUtils.getBean(XTExamConfig.class);
			tokenUtils = new TokenUtils(xtExamConfig.tokenkey);
		}
//		logger.info("preHandle....");
		Object user = request.getSession().getAttribute(BaseAct.SESSION_USER_KEY);
		if(user == null) {
			//TODO:for test
			XTUserService xtUserService = SpringUtils.getBean(XTUserService.class);
			XTUser xtUser = xtUserService.findXTUserByUserName("test");
			request.getSession().setAttribute(BaseAct.SESSION_USER_KEY, xtUser);
//			throw new NoLoginException();
		}
		
//		Cookie cookies[] = request.getCookies();
//		String token = "";
//		String tokenNext = "";
//		String pwd = "";
//
//		for (Cookie c : cookies) {
//			if (c.getName().equals("token")) {
//				token = c.getValue();
//			} else if (c.getName().equals("next")) {
//				tokenNext = c.getValue();
//			} else if (c.getName().equals("pwd")) {
//				pwd = c.getValue();
//			} else if (c.getName().equals("userName")) {
//				request.setAttribute("userName", c.getValue());
//			}
//		}
//
//		if (!tokenUtils.validToken(token, pwd) && !tokenUtils.validToken(tokenNext, pwd)) {
////			throw new NoLoginException();
//		}
//
//		Date now = new Date(System.currentTimeMillis());
//		token = tokenUtils.getToken(pwd, now);
//		tokenNext = tokenUtils.getToken(pwd, TokenUtils.getNextHour(now));
//		Cookie tokenCookie = new Cookie("token", token);
//		tokenCookie.setPath("/");
//		Cookie nextTokenCookie = new Cookie("next", tokenNext);
//		nextTokenCookie.setPath("/");
//		// 将两个新token发给客户端
//		response.addCookie(tokenCookie);
//		response.addCookie(nextTokenCookie);

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
