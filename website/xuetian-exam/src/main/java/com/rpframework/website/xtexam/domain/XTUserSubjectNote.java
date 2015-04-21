package com.rpframework.website.xtexam.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "xt_user_subject_note", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class XTUserSubjectNote extends Domain {

	/**
	 * CREATE TABLE `xt_user_subject_note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `examSubjectId` int(11) NOT NULL,
  `content` varchar(256) NOT NULL DEFAULT '' COMMENT '笔记',
  `isFav` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否收藏',
  `isLike` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否点赞',
  `rightNum` int(11) NOT NULL DEFAULT '0',
  `wrongNum` int(11) NOT NULL DEFAULT '0',
  `lastAnswerOptionId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `USER_SUBECT_NODE_FK` (`userId`),
  KEY `USER_SUBECT_NODE_FK2` (`examSubjectId`),
  CONSTRAINT `USER_SUBECT_NODE_FK` FOREIGN KEY (`userId`) REFERENCES `xt_user` (`id`),
  CONSTRAINT `USER_SUBECT_NODE_FK2` FOREIGN KEY (`examSubjectId`) REFERENCES `xt_exam_subject` (`id`)
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
	String content;
	
	@FieldMapperAnnotation
	Integer isFav;
	@FieldMapperAnnotation
	Integer isLike;
	@FieldMapperAnnotation
	Integer rightNum;
	@FieldMapperAnnotation
	Integer wrongNum;
	@FieldMapperAnnotation
	String lastAnswer;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIsFav() {
		return isFav;
	}
	public void setIsFav(Integer isFav) {
		this.isFav = isFav;
	}
	public Integer getIsLike() {
		return isLike;
	}
	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	}
	public Integer getRightNum() {
		return rightNum;
	}
	public void setRightNum(Integer rightNum) {
		this.rightNum = rightNum;
	}
	public Integer getWrongNum() {
		return wrongNum;
	}
	public void setWrongNum(Integer wrongNum) {
		this.wrongNum = wrongNum;
	}
	public String getLastAnswer() {
		return lastAnswer;
	}
	public void setLastAnswer(String lastAnswer) {
		this.lastAnswer = lastAnswer;
	}
}