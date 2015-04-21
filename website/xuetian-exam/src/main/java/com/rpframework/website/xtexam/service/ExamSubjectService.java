package com.rpframework.website.xtexam.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.xtexam.dao.IExamSubjectDao;
import com.rpframework.website.xtexam.domain.ExamSubject;
import com.rpframework.website.xtexam.domain.ExamSubjectOption;

@Service
public class ExamSubjectService extends BaseService{
	public @Resource IExamSubjectDao examSubjectDao;
	
	/**
	 * 获取试卷下的的试题
	 * @return
	 */
	public List<ExamSubject> getListBySubChapterId(Integer subChapterId) {
		return examSubjectDao.getListBySubChapterId(subChapterId);
	}
	
	public Pager<ExamSubject> getExamSubjectPager(Pager<ExamSubject> pager) {
		long startTime = System.currentTimeMillis();
		List<ExamSubject> itemList = examSubjectDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public boolean doSaveOrUpdate(ExamSubject subject) {//TODO:考虑事务
		if(subject == null || StringUtils.isBlank(subject.getTitle())
				|| StringUtils.isBlank(subject.getVedioPath())
				|| subject.getDocument() == null || NumberUtils.isNotValid(subject.getDocument().getId())
				|| subject.getExamClassify() == null || NumberUtils.isNotValid(subject.getExamClassify().getId())) {
			throw new IllegalArgumentException("参数异常!");
		}
		
		if(subject.getSubjectType() == ExamSubject.CHOOICE_SUBJECT_TYPE) {
			if(CollectionUtils.isEmpty(subject.getOptions()) || subject.getOptions().size() != 4) {
				throw new IllegalArgumentException("选择题仅支持4个选项!");
			}
			
			//判断是否单选
			int rightAnswerNum = 0;
			for (ExamSubjectOption option : subject.getOptions()) {
				if(option.getIsRightAnswer() == 1) {
					rightAnswerNum ++;
				}
				
				if(rightAnswerNum < 1) {
					throw new IllegalArgumentException("需要一个正确的答案!");
				}
			}
			
			subject.setIsSingeChoice(rightAnswerNum > 1 ? 0 : 1);
		} else {
			subject.setExt("");
		}
		
		
		if(NumberUtils.isValid(subject.getId())) {//update
			ExamSubject subjectDB = examSubjectDao.select(subject.getId());
			Assert.notNull(subjectDB, "找不到试卷ID :" + subject.getId());
			
			return examSubjectDao.update(subject);
		} else {//insert
			boolean flag = examSubjectDao.insert(subject);
			Assert.isTrue(flag, "试卷保存失败.");
			Assert.notNull(subject.getId(), "未知错误.");
			return flag;
		}
	}
}
