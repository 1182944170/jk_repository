package com.rpframework.website.luoluo.event.impl;




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
import com.rpframework.website.luoluo.service.UserService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("showprive")
public class MyNameAndPriveShow extends BaseTemplateDirectiveModel {

	final Logger logger = LoggerFactory.getLogger(getClass());
	static final String GET_MFPIC_LIST = "get_showprive_list";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		String cmd = DirectiveUtils.getString("cmd", params);
		Map paramWarp = new HashMap(params);
		boolean pass = true;
		if (StringUtils.isBlank(cmd)) {

		} else if (StringUtils.equals(cmd, GET_MFPIC_LIST)) {
			//int source = DirectiveUtils.getInt("source", params);
			Integer type = DirectiveUtils.getInt("type", params);
			UserService userService = SpringUtils.getBean(UserService.class);

			String pagerString = DirectiveUtils
					.getString("pagerString", params);
			Integer pageSize = DirectiveUtils.getInt("pageSize", params);
			Pager pager = Pager.convertStringToPager(pagerString);
			pager.setPageSize(pageSize);
			pager.getSearchMap().put("typeOne", String.valueOf(type));
			userService.Userpager(pager);

			paramWarp.put("m_pager", ObjectWrapper.DEFAULT_WRAPPER.wrap(pager));
		
		
		}

		Map origWarp = DirectiveUtils.addParamsToVariable(env, paramWarp);
		if (pass && body != null) {
			body.render(env.getOut());
		}
		DirectiveUtils.removeParamsFromVariable(env, paramWarp, origWarp);
	}
}
