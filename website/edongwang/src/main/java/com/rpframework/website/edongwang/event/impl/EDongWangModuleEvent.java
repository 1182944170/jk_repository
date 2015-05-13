package com.rpframework.website.edongwang.event.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rpframework.core.event.impl.ModuleEvent;
@Component
public class EDongWangModuleEvent extends ModuleEvent {
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void applySiteMeshCustomConfiguration(SiteMeshFilterBuilder builder) {
	}

	@Override
	public void init(ServletContext servletContext) {
	}

	@Override
	public void fisrtRequset(HttpServletRequest request, HttpServletResponse response) {
	}
}