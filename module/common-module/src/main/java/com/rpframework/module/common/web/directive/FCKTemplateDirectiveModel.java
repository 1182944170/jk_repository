package com.rpframework.module.common.web.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.fckeditor.FCKeditor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rpframework.core.freemarker.directive.BaseTemplateDirectiveModel;
import com.rpframework.core.freemarker.directive.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**  
 * title: HlctoFunTemplateDirectiveModel.java 
 * freemarker 自定义函数总入口
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2013-6-27 下午1:43:29 
 */  
@Component("fck")
public class FCKTemplateDirectiveModel extends BaseTemplateDirectiveModel {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String value = DirectiveUtils.getString("value", params);
		String instanceName = DirectiveUtils.getString("instanceName", params);
		String height = DirectiveUtils.getString("height", params);
		String width = DirectiveUtils.getString("width", params);
		String inputName = DirectiveUtils.getString("inputName", params);
		String toolbarSet = DirectiveUtils.getString("toolbarSet", params);
		String SkinPath = DirectiveUtils.getString("SkinPath", params);
		boolean pass = true;
		try {
			Map paramWrap = new HashMap(params);
			/**
			 *  <FCK:editor instanceName="content" inputName="news.content" height="300px;" toolbarSet="Basic_No_Image">
						<jsp:attribute name="value">${news.content}</jsp:attribute>
						<jsp:body><FCK:config SkinPath="skins/office2003/"/></jsp:body>
				</FCK:editor>
			 */
			
			FCKeditor editor = new FCKeditor(DirectiveUtils.getRequest(env), instanceName);
			if (StringUtils.isNotBlank(height))
				editor.setHeight(height);

			if (StringUtils.isNotBlank(width))
				editor.setWidth(width);

			if (StringUtils.isNotBlank(inputName))
				editor.setInputName(inputName);

			if (StringUtils.isNotBlank(toolbarSet))
				editor.setToolbarSet(toolbarSet);

			if (StringUtils.isNotBlank(SkinPath))
				editor.setConfig("SkinPath", SkinPath);
		    editor.setValue(value);
		    String fck_body = editor.createHtml();
			paramWrap.put("fck_body", ObjectWrapper.DEFAULT_WRAPPER.wrap(fck_body));
			
			Map origMap = DirectiveUtils.addParamsToVariable(env, paramWrap);
			if(pass)
				body.render(env.getOut());
			DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
