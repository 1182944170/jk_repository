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
import com.rpframework.module.common.domain.Help;
import com.rpframework.module.common.domain.Slideshow;
import com.rpframework.module.common.service.DocumentService;
import com.rpframework.module.common.service.HelpSevice;
import com.rpframework.module.common.service.NoticeService;
import com.rpframework.module.common.service.SlideshowService;
import com.rpframework.utils.Pager;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("common")
public class CommonTemplateDirectiveModel extends BaseTemplateDirectiveModel {
	final Logger logger = LoggerFactory.getLogger(getClass());
	static final String HAS_INCLUDE_FILE = "has_include_file";
	static final String DOCUMENT_LIST = "document_list";
	static final String NOTICE_LIST = "notice_list";
	static final String SLIDESHOW_LIST = "slideshow_list";
	static final String HELP_LIST_GROUP_TYPE = "help_list_group_type";
	static final String HELP_BY_ALIASES_TITLE = "help_by_aliasesTitle";
	
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
		} else if(StringUtils.equals(cmd, DOCUMENT_LIST)) {
			DocumentService documentService = SpringUtils.getBean(DocumentService.class);
			List<Document> list = documentService.queryAllByParentId(0);
			paramWarp.put("m_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		} else if(StringUtils.equals(cmd, SLIDESHOW_LIST)) {
			Integer type = DirectiveUtils.getInt("type", params);
			SlideshowService slideshowService = SpringUtils.getBean(SlideshowService.class);
			List<Slideshow> list = slideshowService.queryEffectiveSlideshow(type);
			paramWarp.put("m_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		} else if(StringUtils.equals(cmd, NOTICE_LIST)) {
			String pagerString = DirectiveUtils.getString("pagerString", params);
			Integer pageSize = DirectiveUtils.getInt("pageSize", params);
			Pager pager = Pager.convertStringToPager(pagerString);
			pager.setPageSize(pageSize);
			
			NoticeService noticeService = SpringUtils.getBean(NoticeService.class);
			noticeService.getNoticePager(pager);
			paramWarp.put("m_pager", ObjectWrapper.DEFAULT_WRAPPER.wrap(pager));
		} else if(StringUtils.equals(cmd, HELP_LIST_GROUP_TYPE)) {
			HelpSevice helpService = SpringUtils.getBean(HelpSevice.class);
			Map<Integer, List<Help>> map = helpService.getHelpGroupByType();
			SimpleHash mm = new SimpleHash();
			for (Map.Entry<Integer, List<Help>> entry : map.entrySet()) {
				SimpleSequence ss = new SimpleSequence(entry.getValue());
				mm.put(String.valueOf(entry.getKey()), ss);
			}
			paramWarp.put("map", mm);
		} else if(StringUtils.equals(cmd, HELP_BY_ALIASES_TITLE)) {
			String aliasesTitle = DirectiveUtils.getString("aliasesTitle", params);
			HelpSevice helpService = SpringUtils.getBean(HelpSevice.class);
			Help help = helpService.getHelpByaliasesTitle(aliasesTitle);
			paramWarp.put("help", ObjectWrapper.DEFAULT_WRAPPER.wrap(help));
		}
		
		Map origWarp = DirectiveUtils.addParamsToVariable(env, paramWarp);
		if(pass && body != null) {
			body.render(env.getOut());
		}
		DirectiveUtils.removeParamsFromVariable(env, paramWarp, origWarp);
	}
}
