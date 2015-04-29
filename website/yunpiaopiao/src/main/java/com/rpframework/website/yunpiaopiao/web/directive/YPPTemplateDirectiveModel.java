package com.rpframework.website.yunpiaopiao.web.directive;

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
import com.rpframework.website.yunpiaopiao.service.CinemaService;
import com.rpframework.website.yunpiaopiao.service.MovieService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("ypp")
public class YPPTemplateDirectiveModel extends BaseTemplateDirectiveModel {
	final Logger logger = LoggerFactory.getLogger(getClass());
	static final String DO_MOVIE_PAGER = "do_movie_pager";
	static final String DO_CINEMA_PAGER = "do_cinema_pager";
	static final String NOTICE_LIST = "NOTICE_LIST";
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		
		String cmd = DirectiveUtils.getString("cmd", params);
		Map paramWarp = new HashMap(params);
		boolean pass = true;
		if(StringUtils.isBlank(cmd)) {
			
		} else if(StringUtils.equals(cmd, DO_MOVIE_PAGER)) {
			String pagerString = DirectiveUtils.getString("pagerString", params);
			Integer pageSize = DirectiveUtils.getInt("pageSize", params);
			Pager pager = Pager.convertStringToPager(pagerString);
			pager.setPageSize(pageSize);
			MovieService movieService = SpringUtils.getBean(MovieService.class);
			pager = movieService.getPager(pager);
			
			paramWarp.put("m_pager", ObjectWrapper.DEFAULT_WRAPPER.wrap(pager));
		} else if(StringUtils.equals(cmd, DO_CINEMA_PAGER)) {
			String pagerString = DirectiveUtils.getString("pagerString", params);
			Integer pageSize = DirectiveUtils.getInt("pageSize", params);
			Pager pager = Pager.convertStringToPager(pagerString);
			pager.setPageSize(pageSize);
			CinemaService cinemaService = SpringUtils.getBean(CinemaService.class);
			pager = cinemaService.doPager(pager);
			
			paramWarp.put("m_pager", ObjectWrapper.DEFAULT_WRAPPER.wrap(pager));
		}
		
		Map origWarp = DirectiveUtils.addParamsToVariable(env, paramWarp);
		if(pass && body != null) {
			body.render(env.getOut());
		}
		DirectiveUtils.removeParamsFromVariable(env, paramWarp, origWarp);
	}
}
