package com.rpframework.website.xtexam.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "xt_user_exam_subject_log", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class XTUserExamSubjectLog extends Domain {

	/**
	 * CREATE TABLE `xt_user_exam_subject_log` (
	  `id` int(11) NOT NULL AUTO_INCREMENT,
	  `examLogId` int(11) NOT NULL,
	  `subjectId` int(11) NOT NULL,
	  `userAnswer` varchar(256) NOT NULL DEFAULT '',
	  `isAnswerRight` tinyint(1) NOT NULL DEFAULT '1',
	  PRIMARY KEY (`id`),
	  KEY `EXAM_LOG_ANSWER_FK` (`examLogId`),
	  KEY `EXAM_LOG_ANSWER_FK2` (`subjectId`),
	  CONSTRAINT `EXAM_LOG_ANSWER_FK` FOREIGN KEY (`examLogId`) REFERENCES `xt_user_exam_log` (`id`),
	  CONSTRAINT `EXAM_LOG_ANSWER_FK2` FOREIGN KEY (`subjectId`) REFERENCES `xt_exam_subject` (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 */
	/**描述*/  
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation(dbFieldName="examLogId", fieldType=FieldType.Object)
	XTUserExamLog examLog;
	
	@FieldMapperAnnotation(dbFieldName="subjectId", fieldType=FieldType.Object)
	ExamSubject subject;
	
	@FieldMapperAnnotation
	String userAnswer;
	
	@FieldMapperAnnotation
	Integer isAnswerRight;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public XTUserExamLog getExamLog() {
		return examLog;
	}

	public void setExamLog(XTUserExamLog examLog) {
		this.examLog = examLog;
	}

	public ExamSubject getSubject() {
		return subject;
	}

	public void setSubject(ExamSubject subject) {
		this.subject = subject;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public Integer getIsAnswerRight() {
		return isAnswerRight;
	}

	public void setIsAnswerRight(Integer isAnswerRight) {
		this.isAnswerRight = isAnswerRight;
	}
}