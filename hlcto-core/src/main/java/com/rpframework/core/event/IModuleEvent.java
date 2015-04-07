package com.rpframework.core.event;

import javax.servlet.ServletContext;

import org.sitemesh.builder.SiteMeshFilterBuilder;

/**
 * 
 * title: IModuleEvent.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年3月30日 下午5:44:13
 */
public interface IModuleEvent extends IBaseEvent {
	
	void applySiteMeshCustomConfiguration(SiteMeshFilterBuilder builder);
	
	void init(ServletContext servletContext);
}
