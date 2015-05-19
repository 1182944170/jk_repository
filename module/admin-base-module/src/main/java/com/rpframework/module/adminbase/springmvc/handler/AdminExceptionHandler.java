package com.rpframework.module.adminbase.springmvc.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminExceptionHandler implements HandlerExceptionResolver {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.debug("resolveException, {}" , ex);
		String requestURI = request.getRequestURI();
		if(StringUtils.indexOf(requestURI, "/admin/") >= 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ex", ex);
			return new ModelAndView("admin-base/exception", map);
		}
		
		/*if(ex instanceof AdminIllegalArgumentException ||ex instanceof AdminNoLimtArgumentException) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ex", ex);
			return new ModelAndView("admin-base/exception", map);
		}*/
		return null;
	}

}
