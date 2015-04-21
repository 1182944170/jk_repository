package com.rpframework.website.xtexam.event.impl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rpframework.core.event.impl.ModuleEvent;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.module.common.service.SMSService;
import com.rpframework.utils.FileUtils;
import com.rpframework.utils.HttpClientUtils;
import com.rpframework.website.xtexam.utils.XTExamConfig;
import com.rpframework.website.xtexam.utils.cache.ExamClassifyCache;
@Component
public class XTExamModuleEvent extends ModuleEvent {
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void applySiteMeshCustomConfiguration(SiteMeshFilterBuilder builder) {
	}

	@Override
	public void init(ServletContext servletContext) {
		CacheUtils.getIntance().add(new ExamClassifyCache());
		
		XTExamConfig xtExamConfig = SpringUtils.getBean(XTExamConfig.class);
		logger.info(ToStringBuilder.reflectionToString(xtExamConfig, ToStringStyle.MULTI_LINE_STYLE));
		
//		//测试
//		SMSService smsService = SpringUtils.getBean(SMSService.class);
//		boolean sendSMS = smsService.sendSMS(1, "15390891113", "1113", "才是验证码");
//		logger.info("" + sendSMS);
//		
//		smsService.checkVerifyCode(1, "15390891113", "1113");
	}

	@Override
	public void fisrtRequset(HttpServletRequest request, HttpServletResponse response) {
		String url = FileUtils.splicePaths(TagUtils.getDomain(request), "admin/courses/sync_static_js");
		String body = HttpClientUtils.get(url, null);
		logger.info("同步学天分类静态数据返回值:{}", body);
	}
}