package com.rpframework.website.xtexam.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;
import com.rpframework.module.common.domain.Document;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.GsonUtils;

@TableMapperAnnotation(tableName = "xt_exam_subject", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class ExamSubject extends Domain {
	public static final Integer CHOOICE_SUBJECT_TYPE = 1;
	public static final Integer SIMPLE_SUBJECT_TYPE = 2;
	/**描述*/  
	
	private static final long serialVersionUID = 4054166887587894294L;
	
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation(dbFieldName = "examClassifyId", fieldType=FieldType.Object)
	ExamSubChapterClassify examClassify;
	
	@FieldMapperAnnotation
	Integer subjectType; //1- 选择题 2-简写题
	@FieldMapperAnnotation
	String title;
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
	@FieldMapperAnnotation
	String ext;
	
	public String getExt() {
		return ext;
	}
	
	public boolean checkIsRightAnswer(String userAnswer) {
		if(this.getSubjectType() == CHOOICE_SUBJECT_TYPE) {
			String[] answers;
			int rightCount = 0;
			if(this.getIsSingeChoice() == 1) { //单选题
				answers = new String[]{userAnswer};
			} else {
				answers = StringUtils.split(userAnswer, "|");
			}
			
			for (ExamSubjectOption option : this.getOptions()) {
				if(option.isRightAnswer == 1) {
					for (String answer : answers) {
						if(StringUtils.equals(option.getOptionName(), answer)) {
							rightCount+=1;
							break;
						}
					}
				}
			}
			
			return answers.length == rightCount;
		} else {
			return true;
		}
	}
	
	public void setExt(String ext) {
		this.ext = ext;
		
		if(StringUtils.isNotBlank(this.ext)) {
			Gson gson = new Gson();
			JsonObject json = new JsonParser().parse(this.ext).getAsJsonObject();
			if(json.has("isSingeChoice")) {//选择题
				this.isSingeChoice = GsonUtils.getInt(json, "isSingeChoice");
				JsonArray optionsArray = json.get("options").getAsJsonArray();
				options = new ArrayList<ExamSubjectOption>();
				for (JsonElement jsonElement : optionsArray) {
					ExamSubjectOption option = gson.fromJson(jsonElement, ExamSubjectOption.class);
					options.add(option);
				}
			}
		}
	}
	
	//选择题具备的属性
	Integer isSingeChoice;
	List<ExamSubjectOption> options;
	public List<ExamSubjectOption> getOptions() {
		return options;
	}
	public void setOptions(List<ExamSubjectOption> options) {
		this.options = options;
		
		whenSetExt();
	}
	
	public Integer getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

	public Integer getIsSingeChoice() {
		return isSingeChoice;
	}
	public void setIsSingeChoice(Integer isSingeChoice) {
		this.isSingeChoice = isSingeChoice;
		
		whenSetExt();
	}
	
	
	private void whenSetExt(){
		if(this.subjectType == CHOOICE_SUBJECT_TYPE) {
			Gson gson = new Gson();
			JsonObject json = new JsonObject();
			JsonArray array = new JsonArray();
			json.addProperty("isSingeChoice", this.isSingeChoice);
			json.add("options", array);
			if(CollectionUtils.isNotEmpty(this.options)) {
				for (ExamSubjectOption option : this.options) {
					array.add(gson.toJsonTree(option));
				}
			}
			
			this.ext = json.toString();
		}
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