package com.rpframework.website.edongwang.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * 
 * title: Job.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年5月16日 下午5:43:57
 */
@TableMapperAnnotation(tableName = "job", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Job extends Domain{

	private static final long serialVersionUID = 1L;
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String name; 
	@FieldMapperAnnotation
	String remark;
	@FieldMapperAnnotation
	String workAddress;
	@FieldMapperAnnotation
	Integer ageType; 
	@FieldMapperAnnotation
	String ageTypeString; 
	@FieldMapperAnnotation
	Integer expType; 
	@FieldMapperAnnotation
	Integer eduType;
	@FieldMapperAnnotation
	Integer jobType;
	@FieldMapperAnnotation
	String money;
	@FieldMapperAnnotation
	String contact;
	@FieldMapperAnnotation
	String countString;
	@FieldMapperAnnotation
	String commissionPercent;
	@FieldMapperAnnotation
	String comRemark;
	@FieldMapperAnnotation
	Long recordCreateTime;
	
	public String getAgeTypeString() {
		return ageTypeString;
	}
	public void setAgeTypeString(String ageTypeString) {
		this.ageTypeString = ageTypeString;
	}
	public String getCountString() {
		return countString;
	}
	public void setCountString(String countString) {
		this.countString = countString;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCommissionPercent() {
		return commissionPercent;
	}
	public void setCommissionPercent(String commissionPercent) {
		this.commissionPercent = commissionPercent;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public Integer getAgeType() {
		return ageType;
	}
	public void setAgeType(Integer ageType) {
		this.ageType = ageType;
	}
	public Integer getExpType() {
		return expType;
	}
	public void setExpType(Integer expType) {
		this.expType = expType;
	}
	public Integer getEduType() {
		return eduType;
	}
	public void setEduType(Integer eduType) {
		this.eduType = eduType;
	}
	public Integer getJobType() {
		return jobType;
	}
	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getComRemark() {
		return comRemark;
	}
	public void setComRemark(String comRemark) {
		this.comRemark = comRemark;
	}
	public Long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
}