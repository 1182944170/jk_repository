package com.rpframework.module.common.web.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rpframework.core.freemarker.directive.BaseTemplateDirectiveModel;
import com.rpframework.core.freemarker.directive.DirectiveUtils;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.module.common.domain.Document;
import com.rpframework.module.common.service.DocumentService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("common")
public class CommonTemplateDirectiveModel extends BaseTemplateDirectiveModel {
	final Logger logger = LoggerFactory.getLogger(getClass());
	static final String HAS_INCLUDE_FILE = "has_include_file";
	static final String DOCUMENT_LIST = "document_list";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		
		String cmd = DirectiveUtils.getString("cmd", params);
		Map paramWarp = new HashMap(params);
		boolean pass = true;
		if(StringUtils.isBlank(cmd)) {
			
		} else if(StringUtils.equals(cmd, HAS_INCLUDE_FILE)) {
			String name = DirectiveUtils.getString("file", params);
			try {
				env.getTemplateForInclusion(name, "UTF-8", true);
			} catch (Exception e) {
				pass = false;
				logger.info("不存在的file文件:{}", name);
			}
		}  else if(StringUtils.equals(cmd, DOCUMENT_LIST)) {
			DocumentService documentService = SpringUtils.getBean(DocumentService.class);
			List<Document> list = documentService.queryAllByParentId(0);
			paramWarp.put("m_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		} 
		
		Map origWarp = DirectiveUtils.addParamsToVariable(env, paramWarp);
		if(pass && body != null) {
			body.render(env.getOut());
		}
		DirectiveUtils.removeParamsFromVariable(env, paramWarp, origWarp);
	}
}
