package com.rpframework.website.xtexam.domain;

import java.util.List;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "xt_user_exam_log", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class XTUserExamLog extends Domain {

	/**
	 * CREATE TABLE `xt_user_exam_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `examClassifyId` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1 -  完结 0 - 位作完',
  `recordCreateTime` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `USER_EXAM_LOG_FK` (`userId`),
  CONSTRAINT `USER_EXAM_LOG_FK` FOREIGN KEY (`userId`) REFERENCES `xt_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 */
	/**描述*/  
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation(dbFieldName="userId", fieldType=FieldType.Object)
	XTUser user;
	
	@FieldMapperAnnotation(dbFieldName="examClassifyId", fieldType=FieldType.Object)
	ExamSubChapterClassify examClassify;
	
	@FieldMapperAnnotation
	Integer score;
	
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	Long recordCreateTime;
	@FieldMapperAnnotation
	Long finishedTime;
	
	List<XTUserExamSubjectLog> examSubjectLogs; //每道题目的记录

	public Long getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(Long finishedTime) {
		this.finishedTime = finishedTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public XTUser getUser() {
		return user;
	}

	public void setUser(XTUser user) {
		this.user = user;
	}

	public ExamSubChapterClassify getExamClassify() {
		return examClassify;
	}

	public void setExamClassify(ExamSubChapterClassify examClassify) {
		this.examClassify = examClassify;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getRecordCreateTime() {
		return recordCreateTime;
	}

	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}

	public List<XTUserExamSubjectLog> getExamSubjectLogs() {
		return examSubjectLogs;
	}

	public void setExamSubjectLogs(List<XTUserExamSubjectLog> examSubjectLogs) {
		this.examSubjectLogs = examSubjectLogs;
	}
}