package com.rpframework.website.xtexam.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "xt_exam_subject_option", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class ExamSubjectOption extends Domain {
	/**描述*/  
	private static final long serialVersionUID = 4054166887587894294L;
	
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation(dbFieldName = "examSubjectId", fieldType = FieldType.Object)
	ExamSubject examSubject;
	@FieldMapperAnnotation
	String optionName;
	@FieldMapperAnnotation
	String optionContent;
	@FieldMapperAnnotation
	Integer isRightAnswer;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ExamSubject getExamSubject() {
		return examSubject;
	}
	public void setExamSubject(ExamSubject examSubject) {
		this.examSubject = examSubject;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionContent() {
		return optionContent;
	}
	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}
	public Integer getIsRightAnswer() {
		return isRightAnswer;
	}
	public void setIsRightAnswer(Integer isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}
}