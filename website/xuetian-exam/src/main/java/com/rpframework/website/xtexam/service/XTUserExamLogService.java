package com.rpframework.website.xtexam.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.website.xtexam.dao.IXTUserExamLogDao;
import com.rpframework.website.xtexam.domain.ExamSubject;
import com.rpframework.website.xtexam.domain.XTUserExamLog;
import com.rpframework.website.xtexam.domain.XTUserExamSubjectLog;
import com.rpframework.website.xtexam.utils.XTConstants;
@Service
public class XTUserExamLogService extends BaseService {
	public @Resource IXTUserExamLogDao userExamLogDao;
	@Resource ExamSubjectService examSubjectService;
	@Resource XTUserExamSubjectLogService xtUserExamSubjectLogService;
	@Resource XTUserSubjectNoteService xtUserSubjectNoteService;

	public boolean submitExam(Integer userId, Integer examLogId, List<XTUserExamSubjectLog> optionList) {
		XTUserExamLog log = userExamLogDao.select(examLogId);
		if(log == null) {
			throw new IllegalArgumentException("找不到log." + examLogId);
		}
		
		if(log.getState() != XTConstants.XTUserExamLog.EXAMING) {
			throw new IllegalArgumentException("该试卷不是正在进行的状态.");
		}
		if(log.getUser().getId() != userId) {
			throw new IllegalArgumentException("不能提交别人的.");
		}
		
		int score = 0;
		
		List<ExamSubject> subjects = examSubjectService.getListBySubChapterId(log.getExamClassify().getId());
		for (ExamSubject subject : subjects) {
			XTUserExamSubjectLog userExamSubjectLog = xtUserExamSubjectLogService.findByExamLogIdAndSubjectId(examLogId, subject.getId());
			if(userExamSubjectLog == null) {
				userExamSubjectLog = new XTUserExamSubjectLog();
				userExamSubjectLog.setExamLog(log);
			}
			userExamSubjectLog.setSubject(subject);
			
			XTUserExamSubjectLog xtUserExamSubjectLog = getUserAnswerByOptionList(subject.getId(), optionList);
			if(xtUserExamSubjectLog == null) {//未答题
				userExamSubjectLog.setIsAnswerRight(0);
				userExamSubjectLog.setUserAnswer("");
				userExamSubjectLog.setSubject(subject);
			} else {
				
				int rightNum = 0, wrongNum = 0;
				if(subject.checkIsRightAnswer(xtUserExamSubjectLog.getUserAnswer())) {//答对了
//					if(userExamSubjectLog.getId() != null && userExamSubjectLog.getIsAnswerRight() != 1) {//之前就有记录的 //如果之前答错了，则记录，之前就是答对了，不需要统计
						//记录该题答对次数 +1
						rightNum = 1;
//					}
					
					score += subject.getScore();
					userExamSubjectLog.setIsAnswerRight(1);
				} else {
//					if(userExamSubjectLog.getId() != null && userExamSubjectLog.getIsAnswerRight() != 0) {//之前就有记录的 //如果之前答对了，则记录，之前就是答错了，不需要统计
						//记录该题答错次数 +1
						wrongNum = 1;
//					}
					
					userExamSubjectLog.setIsAnswerRight(0);
				}
				
				userExamSubjectLog.setUserAnswer(xtUserExamSubjectLog.getUserAnswer());
				xtUserSubjectNoteService.addRightWrongLastAnswerSubject(userId, subject.getId(), rightNum, wrongNum, xtUserExamSubjectLog.getUserAnswer());
			}
			
			//record
			if(userExamSubjectLog.getId() != null) {//update
				xtUserExamSubjectLogService.examSubjectLogDao.update(userExamSubjectLog);
			} else {
				xtUserExamSubjectLogService.examSubjectLogDao.insert(userExamSubjectLog);
			}
		}
		
		log.setFinishedTime(System.currentTimeMillis() / 1000);
		log.setState(XTConstants.XTUserExamLog.EXAM_OVER);
		log.setScore(score);
		userExamLogDao.update(log);
		return true;
	}

	private XTUserExamSubjectLog getUserAnswerByOptionList(Integer subjectId, List<XTUserExamSubjectLog> optionList) {
		if(CollectionUtils.isEmpty(optionList)) {
			return null;
		}
		
		for (XTUserExamSubjectLog xtUserExamSubjectLog : optionList) {
			if(xtUserExamSubjectLog.getSubject().getId() == subjectId) {
				return xtUserExamSubjectLog;
			}
		}
		return null;
	}

	public List<XTUserExamLog> findExamLogByUserId(Integer userId) {
		return userExamLogDao.findExamLogByUserId(userId);
	}
}