package com.rpframework.module.adminbase.springmvc.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rpframework.core.InitServlet;
import com.rpframework.module.adminbase.act.AdminBaseAct;
import com.rpframework.module.adminbase.domain.AdminUser;
import com.rpframework.module.adminbase.event.RoleAdminAuthResVerifyEvent;
import com.rpframework.module.adminbase.exception.AdminNoLimtArgumentException;

public class AdminInterceptor implements HandlerInterceptor {
	final Logger logger = LoggerFactory.getLogger(getClass());
	RoleAdminAuthResVerifyEvent roleAdminAuthResVerifyEvent = new RoleAdminAuthResVerifyEvent();
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		logger.info("preHandle....");
		Object adminUserObj = request.getSession().getAttribute(AdminBaseAct.SESSION_ADMIN_USER_KEY);
		if(adminUserObj == null) {
			response.sendRedirect(InitServlet.CTX + "/admin/login");
			return false;
		}
		
		//登陆的用户做鉴权操作
		/**
		 * AntPathMatcher antPathMatcher = new AntPathMatcher();
		boolean match = antPathMatcher.match(pattern, path);
		 */
		String requestURI = StringUtils.replace(request.getRequestURI(), InitServlet.CTX+"/", "");
		logger.info("requestURI....{}", requestURI);
		boolean flag = roleAdminAuthResVerifyEvent.checkLimit((AdminUser) adminUserObj, requestURI);
		if(!flag) {
			throw new AdminNoLimtArgumentException();
		}
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
