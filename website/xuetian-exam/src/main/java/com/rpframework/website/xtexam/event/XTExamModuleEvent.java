package com.rpframework.website.xtexam.event;

import javax.servlet.ServletContext;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rpframework.core.event.IModuleEvent;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.website.xtexam.utils.XTExamConfig;
@Component
public class XTExamModuleEvent implements IModuleEvent {
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void applySiteMeshCustomConfiguration(SiteMeshFilterBuilder builder) {
		
	}

	@Override
	public void init(ServletContext servletContext) {
		XTExamConfig xtExamConfig = SpringUtils.getBean(XTExamConfig.class);
		logger.info(ToStringBuilder.reflectionToString(xtExamConfig, ToStringStyle.MULTI_LINE_STYLE));
	}
}