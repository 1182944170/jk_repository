package com.rpframework.website.xtexam.utils;

import org.springframework.stereotype.Component;

import com.rpframework.core.freemarker.BaseRegistFreemarker;
import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.website.xtexam.utils.cache.ExamClassifyCache;

@Component("xtUtils")
public class XTUtils extends BaseRegistFreemarker {
	
	public static String getExamClassifyPath(Integer subChapterId) {
		ExamClassifyCache cache = CacheUtils.getIntance().get2(ExamClassifyCache.k);
		return cache.getPath(subChapterId);
	}
}
