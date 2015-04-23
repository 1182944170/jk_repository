package com.rpframework.website.xtexam.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.website.xtexam.dao.IXTUserExamSubjectLogDao;
import com.rpframework.website.xtexam.domain.XTUserExamSubjectLog;

@Service
public class XTUserExamSubjectLogService extends BaseService {
	public @Resource IXTUserExamSubjectLogDao examSubjectLogDao;
	public List<XTUserExamSubjectLog> getListByExamLogId(Integer examLogId) {
		return examSubjectLogDao.getListByExamLogId(examLogId);
	}
	public XTUserExamSubjectLog findByExamLogIdAndSubjectId(Integer examLogId, Integer subjectId) {
		return examSubjectLogDao.findByExamLogIdAndSubjectId(examLogId, subjectId);
	}
	public int getUserWrongAnswerCount(Integer userId) {
		return examSubjectLogDao.getUserWrongAnswerCount(userId);
	}
	
	public List<XTUserExamSubjectLog> getUserWrongAnswer(Integer userId) {
		return examSubjectLogDao.getUserWrongAnswer(userId);
	}
}