package com.rpframework.module.adminbase.event;

import javax.servlet.ServletContext;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rpframework.core.event.IModuleEvent;
import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.module.adminbase.utils.cache.RoleAdminAuthResCache;

@Component
public class AdminBaseModuleEvent implements IModuleEvent {
	final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 文件请放在/views/下面，可以方便通过spring取值
	 * 具体相见com.rpframework.core.freemarker.FreemarkerFilter
	 */
	public static final String ADMIN_BASE_DECORATOR_PATH = "/admin-base-decorator.ftl";
	@Override
	public void applySiteMeshCustomConfiguration(SiteMeshFilterBuilder builder) {
		logger.debug("AdminBaseModuleEvent>>>>>");
		builder.addDecoratorPath("/admin/*", ADMIN_BASE_DECORATOR_PATH)
		.addExcludedPath("/admin/login")
		.addExcludedPath("/admin/login/*");
//		.addExcludedPath("/admin/main")
//		.addExcludedPath("/admin/main/*");
	}
	
	@Override
	public void init(ServletContext servletContext) {
		CacheUtils.getIntance().add(new RoleAdminAuthResCache());
	}

}
