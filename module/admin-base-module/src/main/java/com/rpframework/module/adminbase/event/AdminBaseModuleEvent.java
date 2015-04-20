package com.rpframework.module.adminbase.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rpframework.core.event.impl.ModuleEvent;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.core.utils.cache.KVObj;
import com.rpframework.module.adminbase.domain.Dictionary;
import com.rpframework.module.adminbase.service.DictionaryService;
import com.rpframework.module.adminbase.utils.cache.RoleAdminAuthResCache;
import com.rpframework.utils.CollectionUtils;

@Component
public class AdminBaseModuleEvent extends ModuleEvent {
	final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 文件请放在/views/下面，可以方便通过spring取值
	 * 具体相见com.rpframework.core.freemarker.FreemarkerFilter
	 */
	public static final String ADMIN_BASE_DECORATOR_PATH = "/admin-base-decorator.ftl";
	@Override
	public void applySiteMeshCustomConfiguration(SiteMeshFilterBuilder builder) {
		logger.debug("AdminBaseModuleEvent>>>>>");
		builder.addDecoratorPath("/admin/*", ADMIN_BASE_DECORATOR_PATH)
		.addExcludedPath("/admin/login")
		.addExcludedPath("/admin/login/*");
//		.addExcludedPath("/admin/main")
//		.addExcludedPath("/admin/main/*");
	}
	
	@Override
	public void init(ServletContext servletContext) {
		this.initDictionary();
		CacheUtils.getIntance().add(new RoleAdminAuthResCache());
	}
	
	public void initDictionary() {
		//读取数据库的字典
		DictionaryService dictionaryService = SpringUtils.getBean(DictionaryService.class);
		List<Dictionary> list = dictionaryService.quertAll();
		if(CollectionUtils.isNotEmpty(list)) {
			Map<String, String> constantsMap = new HashMap<String, String>();
			List<KVObj> constantsList = new ArrayList<KVObj>();
			KVObj kvObj = null;
			for (Dictionary dictionary : list) {
				if("map".equalsIgnoreCase(dictionary.getType())) {
					constantsMap.put(dictionary.getVariable(), dictionary.getValue());
				} else if("list".equalsIgnoreCase(dictionary.getType())) {
					kvObj = new KVObj(dictionary.getVariable(), dictionary.getValue());
					constantsList.add(kvObj);
				} else {
					logger.warn("不支持的字典类型:{}", dictionary.getType());
				}
			}
			
			DictionarySettingUtils.setAllConstantsList(constantsList);
			DictionarySettingUtils.setAllConstantsMap(constantsMap);
			DictionarySettingUtils.reInit();
		} else {
			logger.info("字典集合为 nil !");
		}
		
//		Test
//		Dictionary dictionary = dictionaryService.dictionaryDao.select("admin.name");
//		dictionary.setVariable("hhh.jjj24591rt");
//		dictionaryService.dictionaryDao.insert(dictionary);
//		dictionaryService.dictionaryDao.delete(dictionary.getVariable());
//		
//		
//		dictionary.setVariable("hhh.jjj2090");
//		dictionaryService.dictionaryDao.insert(dictionary);
//		
//		
//		dictionary.setValue("dddd");
//		dictionaryService.dictionaryDao.update(dictionary);
	}

	@Override
	public void fisrtRequset(HttpServletRequest request,
			HttpServletResponse response) {
		
	}
}
