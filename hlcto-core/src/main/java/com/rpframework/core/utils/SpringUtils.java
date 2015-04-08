package com.rpframework.core.utils;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rpframework.utils.CollectionUtils;

public class SpringUtils {
	private static ServletContext context;
	private static ApplicationContext ctx;
	
	public static <T> T getBean(Class<T> clazz) {
		Map<String, T> beans = getApplicationContext().getBeansOfType(clazz);
		if(CollectionUtils.isNotEmpty(beans)) {
			for(String keys : beans.keySet()) {
				return beans.get(keys);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String n) {
		return (T)getApplicationContext().getBean(n);
	}

	public static ApplicationContext getApplicationContext(){
		while (null == SpringUtils.context) {
		}
		
		if (ctx == null) {
			ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(SpringUtils.context);
		}
		return ctx;
	}
	
	public static void setServletContext(ServletContext servletContext) {
		if (SpringUtils.context == null) {
			SpringUtils.context = servletContext;
		}
	}
}