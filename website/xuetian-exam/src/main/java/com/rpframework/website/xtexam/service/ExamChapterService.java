package com.rpframework.website.xtexam.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.xtexam.dao.IExamChapterClassifyDao;
import com.rpframework.website.xtexam.domain.ExamChapterClassify;
import com.rpframework.website.xtexam.domain.ExamClassifyLevelEnum;
import com.rpframework.website.xtexam.domain.ExamSpecialtyClassify;

@Service
public class ExamChapterService {
	public @Resource IExamChapterClassifyDao examChapterClassifyDao;
	@Resource ExamSpecialtyService examSpecialtyService;
	
	public boolean doSaveOrUpdate(ExamChapterClassify chapter) {
		if(chapter == null 
				|| StringUtils.isBlank(chapter.getName())
				|| StringUtils.isBlank(chapter.getThumb())
				|| chapter.getState() == null
				|| chapter.getParent() == null || NumberUtils.isNotValid(chapter.getParent().getId())) {
			throw new AdminIllegalArgumentException("添加、修改章节时 参数异常!");
		}
		
		//验证父类的有效性
		ExamSpecialtyClassify specialty = examSpecialtyService.examSpecialtyClassifyDao.select(chapter.getParent().getId());
		if(specialty == null || specialty.getLevelEnum() != ExamClassifyLevelEnum.SPECIALTY) {
			throw new AdminIllegalArgumentException("父类专业验证不通过!");
		}
		chapter.setLevelEnum(ExamClassifyLevelEnum.CHAPTER);
		chapter.setExt("{}");
		if(NumberUtils.isValid(chapter.getId())) {//update
			ExamChapterClassify chapterDB = this.examChapterClassifyDao.select(chapter.getId());
			if(chapterDB == null) {
				throw new AdminIllegalArgumentException("找不到章节：Id:" + chapter.getId());
			}
			
			return this.examChapterClassifyDao.update(chapter);
		} else {//insert
			return this.examChapterClassifyDao.insert(chapter);
		}
	}
}
