package com.rpframework.website.xtexam.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.xtexam.dao.IExamSpecialtyClassifyDao;
import com.rpframework.website.xtexam.domain.ExamClassifyLevelEnum;
import com.rpframework.website.xtexam.domain.ExamCoursesClassify;
import com.rpframework.website.xtexam.domain.ExamSpecialtyClassify;

@Service
public class ExamSpecialtyService {
	public @Resource IExamSpecialtyClassifyDao examSpecialtyClassifyDao;
	@Resource ExamCoursesService examCoursesService;
	
	public boolean doSaveOrUpdate(ExamSpecialtyClassify specialty) {
		if(specialty == null 
				|| StringUtils.isBlank(specialty.getName())
				|| StringUtils.isBlank(specialty.getThumb())
				|| specialty.getState() == null
				|| specialty.getParent() == null || NumberUtils.isNotValid(specialty.getParent().getId())) {
			throw new AdminIllegalArgumentException("添加、修改专业时 参数异常!");
		}
		
		//验证父类的有效性
		ExamCoursesClassify courses = examCoursesService.examCoursesClassifyDao.select(specialty.getParent().getId());
		if(courses == null || courses.getLevelEnum() != ExamClassifyLevelEnum.COURSES) {
			throw new AdminIllegalArgumentException("父类科目验证不通过!");
		}
		specialty.setLevelEnum(ExamClassifyLevelEnum.SPECIALTY);
		specialty.setExt("{}");
		if(NumberUtils.isValid(specialty.getId())) {//update
			ExamSpecialtyClassify specialtyDB = this.examSpecialtyClassifyDao.select(specialty.getId());
			if(specialtyDB == null) {
				throw new AdminIllegalArgumentException("找不到专业：Id:" + specialty.getId());
			}
			
			return this.examSpecialtyClassifyDao.update(specialty);
		} else {//insert
			return this.examSpecialtyClassifyDao.insert(specialty);
		}
	}
}
