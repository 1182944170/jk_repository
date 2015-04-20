package com.rpframework.website.xtexam.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.xtexam.dao.IExamSubChapterClassifyDao;
import com.rpframework.website.xtexam.domain.ExamChapterClassify;
import com.rpframework.website.xtexam.domain.ExamClassifyLevelEnum;
import com.rpframework.website.xtexam.domain.ExamSubChapterClassify;

@Service
public class ExamSubChapterService {
	public @Resource IExamSubChapterClassifyDao examSubChapterClassifyDao;
	@Resource ExamChapterService examChapterService;
	
	public boolean doSaveOrUpdate(ExamSubChapterClassify subChapter) {
		if(subChapter == null 
				|| StringUtils.isBlank(subChapter.getName())
				|| StringUtils.isBlank(subChapter.getThumb())
				|| subChapter.getState() == null
				|| subChapter.getParent() == null || NumberUtils.isNotValid(subChapter.getParent().getId())) {
			throw new AdminIllegalArgumentException("添加、修改试卷时 参数异常!");
		}
		
		//验证父类的有效性
		ExamChapterClassify chapter = examChapterService.examChapterClassifyDao.select(subChapter.getParent().getId());
		if(chapter == null || chapter.getLevelEnum() != ExamClassifyLevelEnum.CHAPTER) {
			throw new AdminIllegalArgumentException("父类章节验证不通过!");
		}
		subChapter.setLevelEnum(ExamClassifyLevelEnum.SUB_CHAPTER);
		if(NumberUtils.isValid(subChapter.getId())) {//update
			ExamSubChapterClassify subChapterDB = this.examSubChapterClassifyDao.select(subChapter.getId());
			if(subChapterDB == null) {
				throw new AdminIllegalArgumentException("找不到试卷：Id:" + subChapter.getId());
			}
			
			return this.examSubChapterClassifyDao.update(subChapter);
		} else {//insert
			return this.examSubChapterClassifyDao.insert(subChapter);
		}
	}
}
