package com.rpframework.module.common.event;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rpframework.core.event.impl.ModuleEvent;
import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.module.common.utils.cache.CountryCache;

@Component
public class CommonEventModule extends ModuleEvent {
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void applySiteMeshCustomConfiguration(SiteMeshFilterBuilder builder) {
		
	}

	@Override
	public void init(ServletContext servletContext) {
		CacheUtils.getIntance().add(new CountryCache());
	}

	@Override
	public void fisrtRequset(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
}
