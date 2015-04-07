package com.rpframework.core.sitemesh;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.rpframework.core.event.IModuleEvent;
import com.rpframework.utils.CollectionUtils;

public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {
	final Logger logger = LoggerFactory.getLogger(getClass());
	private FilterConfig filterConfig;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		super.init(filterConfig);
	}
	
	

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		super.doFilter(servletRequest, servletResponse, filterChain);
	}



	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.filterConfig.getServletContext());
		Map<String, IModuleEvent> beans = applicationContext.getBeansOfType(IModuleEvent.class);
		if(CollectionUtils.isNotEmpty(beans)) {
			IModuleEvent iModuleEvent = null;
			for(String keys : beans.keySet()) {
				iModuleEvent = beans.get(keys);
				iModuleEvent.applySiteMeshCustomConfiguration(builder);
			}
		}
		logger.debug("applyCustomConfiguration >>>");
		super.applyCustomConfiguration(builder);
	}
}
