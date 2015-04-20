package com.rpframework.website.xtexam.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.xtexam.domain.ExamSpecialtyClassify;

/**
 * title: IExamSpecialtyClassifyDao.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年4月15日 下午8:38:33
 */
public interface IExamSpecialtyClassifyDao extends IDao {
	List<ExamSpecialtyClassify> getAll();
	
	/**
	 * 获取该科目下的专业
	 * @return
	 */
	List<ExamSpecialtyClassify> getListByCoursesId(Integer coursesId);
	List<ExamSpecialtyClassify> getListEffectiveByCoursesId(Integer coursesId);
}
