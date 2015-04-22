package com.rpframework.website.xtexam.utils.cache;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.core.utils.cache.CacheObj;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.website.xtexam.domain.ExamChapterClassify;
import com.rpframework.website.xtexam.domain.ExamClassify;
import com.rpframework.website.xtexam.domain.ExamCoursesClassify;
import com.rpframework.website.xtexam.domain.ExamSpecialtyClassify;
import com.rpframework.website.xtexam.domain.ExamSubChapterClassify;
import com.rpframework.website.xtexam.service.ExamChapterService;
import com.rpframework.website.xtexam.service.ExamCoursesService;
import com.rpframework.website.xtexam.service.ExamSpecialtyService;
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
	public JsonArray array;
	
	public Map<String, ExamCoursesClassify> coursesMap = new LinkedHashMap<String, ExamCoursesClassify>();
	public Map<String, List<ExamSpecialtyClassify>> specialtyMap = new LinkedHashMap<String, List<ExamSpecialtyClassify>>();
	public Map<String, List<ExamChapterClassify>> chapterMap = new LinkedHashMap<String, List<ExamChapterClassify>>();
	public Map<String, List<ExamSubChapterClassify>> subchapterMap = new LinkedHashMap<String, List<ExamSubChapterClassify>>();
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
		
		ExamCoursesService coursesService = SpringUtils.getBean(ExamCoursesService.class);
		ExamSpecialtyService specialtyService = SpringUtils.getBean(ExamSpecialtyService.class);
		ExamChapterService chapterService = SpringUtils.getBean(ExamChapterService.class);
		ExamSubChapterService subchapterService = SpringUtils.getBean(ExamSubChapterService.class);
		
		
		List<ExamCoursesClassify> all = coursesService.examCoursesClassifyDao.getAllEffective();
		array = new JsonArray();
		for (ExamCoursesClassify courses : all) {
			JsonObject coursesJson = new JsonObject();
			JsonArray specialtys = new JsonArray();
			coursesJson.addProperty("name", courses.getName());
			coursesJson.addProperty("isOpen", courses.getIsOpen());
			coursesJson.add("specialtys", specialtys);
			array.add(coursesJson);
			List<ExamSpecialtyClassify> specialtyList = specialtyService.examSpecialtyClassifyDao.getListEffectiveByCoursesId(courses.getId());
			if(CollectionUtils.isEmpty(specialtyList)) continue;
			
			for (ExamSpecialtyClassify specialty : specialtyList) {
				JsonObject specialtyJson = new JsonObject();
				JsonArray chapters = new JsonArray();
				specialtyJson.addProperty("name", specialty.getName());
				specialtyJson.add("chapters", chapters);
				specialtys.add(specialtyJson);
				
				List<ExamChapterClassify> chapterList = chapterService.examChapterClassifyDao.getListEffectiveBySpecialtyId(specialty.getId());
				if(CollectionUtils.isEmpty(chapterList)) continue;
				for (ExamChapterClassify chapter : chapterList) {
					JsonObject chapterJson = new JsonObject();
					JsonArray subchapters = new JsonArray();
					chapterJson.addProperty("name", chapter.getName());
					chapterJson.add("subchapters", subchapters);
					chapters.add(chapterJson);
					
					List<ExamSubChapterClassify> subchapterList = subchapterService.examSubChapterClassifyDao.getListEffectiveByChapterId(chapter.getId());
					for (ExamSubChapterClassify subchapter : subchapterList) {
						JsonObject subchapterJson = new JsonObject();
						subchapterJson.addProperty("name", subchapter.getName());
						subchapterJson.addProperty("examTime", subchapter.getExamTime());
						subchapterJson.addProperty("totalSubjectNum", subchapter.getTotalSubjectNum());
						subchapterJson.addProperty("totalScore", subchapter.getTotalScore());
						subchapterJson.addProperty("passScore", subchapter.getPassScore());
						subchapterJson.addProperty("price", subchapter.getPrice());
						subchapterJson.addProperty("discount", subchapter.getDiscount());
						subchapters.add(subchapterJson);
					}
				}
			}
			
		}
		
		List<ExamSubChapterClassify> list = subchapterService.examSubChapterClassifyDao.getAll();
		if(CollectionUtils.isNotEmpty(list)) {
			for (ExamSubChapterClassify examSubChapterClassify : list) {
				map.put(examSubChapterClassify.getId(), examSubChapterClassify);
			}
		}
		return new Object();
	}

}
