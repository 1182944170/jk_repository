package com.rpframework.website.xtexam.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.xtexam.dao.IExamCoursesClassifyDao;
import com.rpframework.website.xtexam.domain.ExamClassifyLevelEnum;
import com.rpframework.website.xtexam.domain.ExamCoursesClassify;

@Service
public class ExamCoursesService {
	public @Resource IExamCoursesClassifyDao examCoursesClassifyDao;

	public boolean doSaveOrUpdate(ExamCoursesClassify courses) {
		if(courses == null 
				|| StringUtils.isBlank(courses.getName())
				|| StringUtils.isBlank(courses.getThumb())
				|| courses.getState() == null) {
			throw new AdminIllegalArgumentException("添加、修改科目时 参数异常!");
		}
		
		courses.setLevelEnum(ExamClassifyLevelEnum.COURSES);
		courses.setParent(new ExamCoursesClassify());
		if(NumberUtils.isValid(courses.getId())) {//update
			ExamCoursesClassify coursesDB = this.examCoursesClassifyDao.select(courses.getId());
			if(coursesDB == null) {
				throw new AdminIllegalArgumentException("更新科目时不存在的ID:" + courses.getId());
			}
			
			return this.examCoursesClassifyDao.update(courses);
		} else {//insert
			return this.examCoursesClassifyDao.insert(courses);
		}
	}
}
