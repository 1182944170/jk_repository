/*package com.rpframework.website.furnishing.event.impl;





import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rpframework.core.freemarker.directive.BaseTemplateDirectiveModel;
import com.rpframework.core.freemarker.directive.DirectiveUtils;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.utils.Pager;


import com.rpframework.website.furnishing.service.DynamicService;
import com.rpframework.website.furnishing.service.UserCommentSeriver;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("mifancut")
public class MiFanCutDirectiveModel extends BaseTemplateDirectiveModel {

	final Logger logger = LoggerFactory.getLogger(getClass());
	static final String GET_MFCUTPIC_LIST = "get_mfcutpic_list";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		String cmd = DirectiveUtils.getString("cmd", params);
		Map paramWarp = new HashMap(params);
		boolean pass = true;
		if (StringUtils.isBlank(cmd)) {
			
		} else if (StringUtils.equals(cmd, GET_MFCUTPIC_LIST)) {
			//int source = DirectiveUtils.getInt("source", params);
			Integer type = DirectiveUtils.getInt("type", params);
			UserCommentSeriver userCommentSeriver = SpringUtils.getBean(UserCommentSeriver.class);

			String pagerString = DirectiveUtils
					.getString("pagerString", params);
			Integer pageSize = DirectiveUtils.getInt("pageSize", params);
			Pager pager = Pager.convertStringToPager(pagerString);
			pager.setPageSize(pageSize);
			
			
			pager.getSearchMap().put("type", String.valueOf(type));
			userCommentSeriver.getPager(pager);

			paramWarp.put("p_pager", ObjectWrapper.DEFAULT_WRAPPER.wrap(pager));
		}

		Map origWarp = DirectiveUtils.addParamsToVariable(env, paramWarp);
		if (pass && body != null) {
			body.render(env.getOut());
		}
		DirectiveUtils.removeParamsFromVariable(env, paramWarp, origWarp);
	}
}*/
