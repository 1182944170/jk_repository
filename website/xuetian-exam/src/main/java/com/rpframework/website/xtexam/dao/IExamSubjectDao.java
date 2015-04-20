package com.rpframework.website.xtexam.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.xtexam.domain.ExamSubject;
/**
 * 
 * title: IExamChapterClassifyDao.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年4月15日 下午9:30:49
 */
public interface IExamSubjectDao extends IDao {
	/**
	 * 获取试卷下的的试题
	 * @return
	 */
	List<ExamSubject> getListBySubChapterId(Integer subChapterId);

	@SuppressWarnings("rawtypes")
	List<ExamSubject> doPager(Map paramMap);
}
