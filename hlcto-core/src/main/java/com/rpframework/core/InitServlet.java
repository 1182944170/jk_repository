package com.rpframework.core;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rpframework.core.event.IModuleEvent;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.utils.CollectionUtils;

public class InitServlet extends HttpServlet {

	/**描述*/  
	public static String CTX = "";
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		CTX = servletContext.getContextPath();
		servletContext.setAttribute("ctx", CTX);
		
		SpringUtils.setServletContext(servletContext);
		
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		Map<String, IModuleEvent> beans = applicationContext.getBeansOfType(IModuleEvent.class);
		if(CollectionUtils.isNotEmpty(beans)) {
			IModuleEvent iModuleEvent = null;
			for(String keys : beans.keySet()) {
				iModuleEvent = beans.get(keys);
				
				iModuleEvent.init(servletContext);
			}
		}
		super.init(config);
	}
}
