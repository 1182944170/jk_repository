package com.rpframework.website.xtexam.domain;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rpframework.utils.GsonUtils;

/**
 * 
 * title: ExamCourses.java 
 * 试卷分类
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年4月15日 下午5:45:00
 */
public class ExamSubChapterClassify extends ExamClassify {
	/**描述*/  
	private static final long serialVersionUID = 1L;
	
	Integer examTime = 0;
	Integer totalSubjectNum = 0;//总题目数量
	Integer totalScore = 0;//总分数
	Integer passScore = 0;//及格分数
	Double price = 0D;//价格 0-免费
	Double discount = 0D;// 折扣
	
	public void wenSetPrivate(){
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("examTime", examTime);
		jsonObject.addProperty("totalSubjectNum", totalSubjectNum);
		jsonObject.addProperty("totalScore", totalScore);
		jsonObject.addProperty("passScore", passScore);
		jsonObject.addProperty("price", price);
		jsonObject.addProperty("discount", discount);
		
		super.ext = jsonObject.toString();
	}
	
	@Override
	public void setExt(String ext) {
		super.setExt(ext);
		
		if(StringUtils.isNotBlank(super.getExt())) {
			JsonObject jsonObject = new JsonParser().parse(super.getExt()).getAsJsonObject();
			this.examTime = GsonUtils.getInt(jsonObject, "examTime");
			this.totalSubjectNum = GsonUtils.getInt(jsonObject, "totalSubjectNum");
			this.totalScore = GsonUtils.getInt(jsonObject, "totalScore");
			this.passScore = GsonUtils.getInt(jsonObject, "passScore");
			this.price = GsonUtils.getDouble(jsonObject, "passScore");
			this.discount = GsonUtils.getDouble(jsonObject, "discount");
		}
	}

	public Integer getExamTime() {
		return examTime;
	}

	public void setExamTime(Integer examTime) {
		this.examTime = examTime;
		wenSetPrivate();
	}

	public Integer getTotalSubjectNum() {
		return totalSubjectNum;
	}

	public void setTotalSubjectNum(Integer totalSubjectNum) {
		this.totalSubjectNum = totalSubjectNum;
		wenSetPrivate();
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
		wenSetPrivate();
	}

	public Integer getPassScore() {
		return passScore;
	}

	public void setPassScore(Integer passScore) {
		this.passScore = passScore;
		wenSetPrivate();
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
		wenSetPrivate();
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
		wenSetPrivate();
	}
}