package com.rpframework.website.xtexam.domain;

import java.util.List;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;
import com.rpframework.module.common.domain.Document;

@TableMapperAnnotation(tableName = "xt_exam_subject", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class ExamSubject extends Domain {
	/**描述*/  
	
	private static final long serialVersionUID = 4054166887587894294L;
	
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation(dbFieldName = "examClassifyId", fieldType=FieldType.Object)
	ExamSubChapterClassify examClassify;
	@FieldMapperAnnotation
	String title;
	@FieldMapperAnnotation
	Integer isSingeChoice;
	@FieldMapperAnnotation(dbFieldName = "documentId", fieldType=FieldType.Object)
	Document document;
	@FieldMapperAnnotation
	String vedioPath;
	@FieldMapperAnnotation
	Integer commentNum;
	@FieldMapperAnnotation
	Integer score;
	@FieldMapperAnnotation
	Integer supportNum;
	@FieldMapperAnnotation
	Integer state;
	
	List<ExamSubjectOption> options;
	
	public List<ExamSubjectOption> getOptions() {
		return options;
	}
	public void setOptions(List<ExamSubjectOption> options) {
		this.options = options;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ExamSubChapterClassify getExamClassify() {
		return examClassify;
	}
	public void setExamClassify(ExamSubChapterClassify examClassify) {
		this.examClassify = examClassify;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getIsSingeChoice() {
		return isSingeChoice;
	}
	public void setIsSingeChoice(Integer isSingeChoice) {
		this.isSingeChoice = isSingeChoice;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public String getVedioPath() {
		return vedioPath;
	}
	public void setVedioPath(String vedioPath) {
		this.vedioPath = vedioPath;
	}
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	public Integer getSupportNum() {
		return supportNum;
	}
	public void setSupportNum(Integer supportNum) {
		this.supportNum = supportNum;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}