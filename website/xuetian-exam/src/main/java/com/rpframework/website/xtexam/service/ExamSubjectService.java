package com.rpframework.website.xtexam.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.xtexam.dao.IExamSubjectDao;
import com.rpframework.website.xtexam.domain.ExamSubject;
import com.rpframework.website.xtexam.domain.ExamSubjectOption;

@Service
public class ExamSubjectService extends BaseService{
	public @Resource IExamSubjectDao examSubjectDao;
	@Resource ExamSubjectOptionService examSubjectOptionService;
	
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
		
		if(NumberUtils.isValid(subject.getId())) {//update
			ExamSubject subjectDB = examSubjectDao.select(subject.getId());
			Assert.notNull(subjectDB, "找不到试卷ID :" + subject.getId());
			List<ExamSubjectOption> options = subject.getOptions();
			for (ExamSubjectOption option : options) {
				ExamSubjectOption optionDB = examSubjectOptionService.findOptionBySubjectIdAndOptionName(subject.getId(), option.getOptionName());
				Assert.notNull(optionDB, "找不到试卷选项[subjectId,optionId] :" + subject.getId() + "," + option.getOptionName());
				option.setId(optionDB.getId());
				option.setExamSubject(subject);
				
				boolean updateFlag = examSubjectOptionService.examSubjectOptionDao.update(option);
				Assert.isTrue(updateFlag, "试卷选项更新失败.");
			}
		} else {//insert
			boolean flag = examSubjectDao.insert(subject);//TODO:考虑事务
			Assert.isTrue(flag, "试卷保存失败.");
			Assert.notNull(subject.getId(), "未知错误.");
			
			List<ExamSubjectOption> options = subject.getOptions();
			for (ExamSubjectOption option : options) {
				option.setExamSubject(subject);
				
				boolean insertFlag = examSubjectOptionService.examSubjectOptionDao.insert(option);
				Assert.isTrue(insertFlag, "试卷选项新增失败.");
			}
		}
		
		return true;
	}
}
