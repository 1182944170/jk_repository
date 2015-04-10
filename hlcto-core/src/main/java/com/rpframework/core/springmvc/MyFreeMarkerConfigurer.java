package com.rpframework.core.springmvc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.rpframework.core.utils.SpringUtils;
import com.rpframework.utils.CollectionUtils;

import freemarker.cache.TemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

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
		Map<String, TemplateDirectiveModel> beans = ctx.getBeansOfType(TemplateDirectiveModel.class);
		if(CollectionUtils.isNotEmpty(beans)) {
			for(String key : beans.keySet()) {
				Object bean = beans.get(key);
				variables.put(key, bean);
				logger.info("注入自定义标签 :" + key + ", bean:" + bean);
			}
		}

		logger.info("Freemarker Custom Tag Info:" + variables);
		
		BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
		try {
			TemplateModel templateModel = wrapper.getStaticModels().get("com.rpframework.core.utils.DictionarySettingUtils");
			Assert.notNull(templateModel, "cannot find com.rpframework.core.utils.DictionarySettingUtils class!");
			
			logger.info("注入自定义标签 dicSetting:, bean:" + templateModel);
			variables.put("dicSetting", templateModel);
			
			
			TemplateModel tagUtilsTemplateModel = wrapper.getStaticModels().get("com.rpframework.core.utils.TagUtils");
			Assert.notNull(tagUtilsTemplateModel, "cannot find com.rpframework.core.utils.TagUtils class!");
			
			logger.info("注入自定义标签 tagUtils:, bean:" + tagUtilsTemplateModel);
			variables.put("tagUtils", tagUtilsTemplateModel);
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
		super.setFreemarkerVariables(variables);
	}
	
}
