package com.rpframework.website.xtexam.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.xtexam.domain.XTUserExamSubjectLog;

public interface IXTUserExamSubjectLogDao extends IDao {
	List<XTUserExamSubjectLog> getListByExamLogId(Integer examLogId);

	XTUserExamSubjectLog findByExamLogIdAndSubjectId(Integer examLogId, Integer subjectId);

	int getUserWrongAnswerCount(Integer userId);

	List<XTUserExamSubjectLog> getUserWrongAnswer(Integer userId);
}
