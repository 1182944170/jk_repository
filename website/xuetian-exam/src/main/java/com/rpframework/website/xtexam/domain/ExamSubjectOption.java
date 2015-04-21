package com.rpframework.website.xtexam.domain;

import com.rpframework.core.Domain;

public class ExamSubjectOption extends Domain {
	/**描述*/  
	private static final long serialVersionUID = 4054166887587894294L;
	
	String optionName;
	String optionContent;
	Integer isRightAnswer;
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