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
	@FieldMapperAnnotation(dbFieldName="subjectId", fieldType=FieldType.Object)
	ExamSubject subject;
	@FieldMapperAnnotation
	String content;
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	Long recoreCreateTime;
	
	public Long getRecoreCreateTime() {
		return recoreCreateTime;
	}
	public void setRecoreCreateTime(Long recoreCreateTime) {
		this.recoreCreateTime = recoreCreateTime;
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
	public ExamSubject getSubject() {
		return subject;
	}
	public void setSubject(ExamSubject subject) {
		this.subject = subject;
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