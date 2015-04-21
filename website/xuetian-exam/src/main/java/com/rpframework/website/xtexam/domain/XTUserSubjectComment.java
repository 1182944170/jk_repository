package com.rpframework.website.xtexam.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "xt_user_subject_comment", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class XTUserSubjectComment extends Domain {

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
	Integer state;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}