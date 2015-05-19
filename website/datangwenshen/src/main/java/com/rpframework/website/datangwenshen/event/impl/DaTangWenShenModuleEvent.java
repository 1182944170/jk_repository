package com.rpframework.website.datangwenshen.event.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rpframework.core.event.impl.ModuleEvent;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.website.datangwenshen.utils.DaTangWenShenConfig;
@Component
public class DaTangWenShenModuleEvent extends ModuleEvent {
	private static final String BASE_DECORATOR_PATH = "/datangwenshen-decorator.ftl";
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void applySiteMeshCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/*", BASE_DECORATOR_PATH);
	}

	@Override
	public void init(ServletContext servletContext) {
		DaTangWenShenConfig daTangWenShenConfig = SpringUtils.getBean(DaTangWenShenConfig.class);
		servletContext.setAttribute("webSiteStyle", daTangWenShenConfig.STYLE);
	}

	@Override
	public void fisrtRequset(HttpServletRequest request, HttpServletResponse response) {
	}
}