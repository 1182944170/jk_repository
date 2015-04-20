package com.rpframework.website.xtexam.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.website.xtexam.dao.IExamSubjectOptionDao;
import com.rpframework.website.xtexam.domain.ExamSubjectOption;

@Service
public class ExamSubjectOptionService {
	public @Resource IExamSubjectOptionDao examSubjectOptionDao;
	
	/**
	 * 获取试题的选项
	 * @return
	 */
	public List<ExamSubjectOption> getListBySubjectId(Integer subjectId) {
		return examSubjectOptionDao.getListBySubjectId(subjectId);
	}

	public ExamSubjectOption findOptionBySubjectIdAndOptionName(Integer examSubjectId,
			String optionName) {
		return examSubjectOptionDao.findOptionBySubjectIdAndOptionName(examSubjectId,optionName);
	}
}
