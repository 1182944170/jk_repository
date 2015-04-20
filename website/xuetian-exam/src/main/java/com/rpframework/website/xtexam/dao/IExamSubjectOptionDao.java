package com.rpframework.website.xtexam.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.xtexam.domain.ExamSubjectOption;
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
public interface IExamSubjectOptionDao extends IDao {
	/**
	 * 获取试题的选项
	 * @return
	 */
	List<ExamSubjectOption> getListBySubjectId(Integer subjectId);

	ExamSubjectOption findOptionBySubjectIdAndOptionName(Integer subjectId,String optionName);
}
