package com.rpframework.core.event.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sitemesh.builder.SiteMeshFilterBuilder;

import com.rpframework.core.event.IModuleEvent;

public abstract class ModuleEvent implements IModuleEvent {

	@Override
	public void applySiteMeshCustomConfiguration(SiteMeshFilterBuilder builder) {

	}

	@Override
	public void init(ServletContext servletContext) {

	}

	@Override
	public void fisrtRequset(HttpServletRequest request,
			HttpServletResponse response) {

	}

}
