package com.rpframework.module.common.springmvc.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rpframework.core.InitServlet;
import com.rpframework.core.event.IModuleEvent;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.utils.CollectionUtils;

public class CommonInterceptor implements HandlerInterceptor {
	final Logger logger = LoggerFactory.getLogger(getClass());
	boolean isFirstReq = true;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if(isFirstReq) {
			isFirstReq = !isFirstReq;
			logger.info("第一次Request事件触发!");
			InitServlet.DOMAIN = TagUtils.getDomain(request);
			//发送第一次请求事件
			

			Map<String, IModuleEvent> beans = SpringUtils.getApplicationContext().getBeansOfType(IModuleEvent.class);
			if(CollectionUtils.isNotEmpty(beans)) {
				IModuleEvent moduleEvent = null;
				for(String keys : beans.keySet()) {
					moduleEvent = beans.get(keys);
					moduleEvent.fisrtRequset(request, response);
				}
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
