package com.rpframework.website.edongwang.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * 
 * title: ScoreShop.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月7日 下午5:32:03
 */
@TableMapperAnnotation(tableName = "score_shop", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class ScoreShop extends Domain {

	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String img;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation
	Integer type;
	@FieldMapperAnnotation
	String remark;
	@FieldMapperAnnotation
	String successMsg;
	@FieldMapperAnnotation
	Integer stockNumber;
	@FieldMapperAnnotation
	Integer score;
	@FieldMapperAnnotation
	Integer salesNumber;
	@FieldMapperAnnotation
	Integer rule;
	@FieldMapperAnnotation
	Long startTime;
	@FieldMapperAnnotation
	Long endTime;
	@FieldMapperAnnotation
	Long recordCreateTime;
	@FieldMapperAnnotation
	Integer state;
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSalesNumber() {
		return salesNumber;
	}
	public void setSalesNumber(Integer salesNumber) {
		this.salesNumber = salesNumber;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}
	public Integer getRule() {
		return rule;
	}
	public void setRule(Integer rule) {
		this.rule = rule;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
}