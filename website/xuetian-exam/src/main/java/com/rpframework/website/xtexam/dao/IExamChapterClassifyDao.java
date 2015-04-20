package com.rpframework.website.xtexam.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.xtexam.domain.ExamChapterClassify;
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
public interface IExamChapterClassifyDao extends IDao {
	List<ExamChapterClassify> getAll();
	
	/**
	 * 获取该专业下的章节
	 * @return
	 */
	List<ExamChapterClassify> getListBySpecialtyId(Integer specialtyId);
	List<ExamChapterClassify> getListEffectiveBySpecialtyId(Integer specialtyId);
}
