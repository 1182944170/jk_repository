package com.rpframework.website.xtexam.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.xtexam.domain.ExamCoursesClassify;

/**
 * title: IExamCoursesClassifyDao.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年4月15日 下午8:37:35
 */
public interface IExamCoursesClassifyDao extends IDao {
	List<ExamCoursesClassify> getAll();
	List<ExamCoursesClassify> getAllEffective();
}
