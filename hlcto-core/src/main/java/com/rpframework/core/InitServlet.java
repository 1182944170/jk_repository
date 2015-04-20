package com.rpframework.core;

import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.rpframework.core.event.IModuleEvent;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.utils.CollectionUtils;

public class InitServlet extends HttpServlet {

	/**描述*/  
	public static String CTX = "";
	public static String REAL_PATH = "";//本程序位于web服务器的真实路径
	public static String DOMAIN = ""; //网站的域名,该值会在 commonIntercept 注入，方便获取
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		CTX = servletContext.getContextPath();
		REAL_PATH = servletContext.getRealPath("");
		
		servletContext.setAttribute("ctx", CTX);
		SpringUtils.setServletContext(servletContext);
		
		Map<String, IModuleEvent> beans = SpringUtils.getApplicationContext().getBeansOfType(IModuleEvent.class);
		if(CollectionUtils.isNotEmpty(beans)) {
			IModuleEvent moduleEvent = null;
			for(String keys : beans.keySet()) {
				moduleEvent = beans.get(keys);
				
				moduleEvent.init(servletContext);
			}
		}
		super.init(config);
	}
}
