package com.rpframework.core.springmvc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.rpframework.core.utils.SpringUtils;
import com.rpframework.utils.CollectionUtils;

import freemarker.cache.TemplateLoader;
import freemarker.template.TemplateDirectiveModel;

public class MyFreeMarkerConfigurer extends FreeMarkerConfigurer {
	
	@Override
	protected TemplateLoader getTemplateLoaderForPath(String templateLoaderPath) {
		return new SpringTemplateLoader(getResourceLoader(), templateLoaderPath);
	}
	@Override
	public void setFreemarkerVariables(Map<String, Object> variables) {
		if(variables == null) {
			variables = new HashMap<String, Object>();
		}
		ApplicationContext ctx = SpringUtils.getApplicationContext();
		Map<String, TemplateDirectiveModel> beansOfType = ctx.getBeansOfType(TemplateDirectiveModel.class);
		if(CollectionUtils.isNotEmpty(beansOfType)) {
			for(String key : beansOfType.keySet()) {
				Object bean = beansOfType.get(key);
				variables.put(key, bean);
				logger.info("注入自定义标签 :" + key + ", bean:" + bean);
			}
		}

		logger.info("Freemarker Custom Tag Info:" + variables);
		
		super.setFreemarkerVariables(variables);
	}
	
}
