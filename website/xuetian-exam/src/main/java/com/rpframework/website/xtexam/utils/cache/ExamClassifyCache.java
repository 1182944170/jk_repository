package com.rpframework.website.xtexam.utils.cache;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rpframework.core.utils.SpringUtils;
import com.rpframework.core.utils.cache.CacheObj;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.website.xtexam.domain.ExamClassify;
import com.rpframework.website.xtexam.domain.ExamSubChapterClassify;
import com.rpframework.website.xtexam.service.ExamSubChapterService;

/**  
 * title: CountryCache.java 
 * 省镇区的数据缓存
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2013-6-5 上午11:18:50 
 */  
public class ExamClassifyCache extends CacheObj {
	final Logger logger = LoggerFactory.getLogger(getClass());
	public static final String k = "_exam_classify_cache_";
	public Map<Integer, ExamSubChapterClassify> map = new LinkedHashMap<Integer, ExamSubChapterClassify>();
	
	public ExamClassifyCache() {
		super(300 * 60L, k);
	}
	
	public String getPath(Integer subChapterId) {
		if(map.containsKey(subChapterId)) {
			StringBuffer buffer = new StringBuffer();
			
			ExamSubChapterClassify examSubChapterClassify = map.get(subChapterId);
			buffer.append(examSubChapterClassify.getName()).append("/");
			
			ExamClassify parentClassify = examSubChapterClassify.getParent();
			while(parentClassify != null) {
				buffer.append(parentClassify.getName());
				parentClassify = parentClassify.getParent();
				if(parentClassify != null) {
					buffer.append("/");
				}
			}
			
			return StringUtils.reverseDelimited(buffer.toString(), '/');
		} else {
			logger.warn("不存在的key：{}", subChapterId);
			return "";
		}
	}
	
	@Override
	public Object load() {
		map.clear();
		
		ExamSubChapterService subChapterService = SpringUtils.getBean(ExamSubChapterService.class);
		List<ExamSubChapterClassify> list = subChapterService.examSubChapterClassifyDao.getAll();
		if(CollectionUtils.isNotEmpty(list)) {
			for (ExamSubChapterClassify examSubChapterClassify : list) {
				map.put(examSubChapterClassify.getId(), examSubChapterClassify);
			}
		}
		return new Object();
	}

}
