package com.rpframework.module.user.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "u_money_log", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class UserMoneyLog extends Domain {

	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	Integer userId;
	@FieldMapperAnnotation
	Double usedMoney;
	@FieldMapperAnnotation
	Double currMoney;
	@FieldMapperAnnotation
	Double money;
	@FieldMapperAnnotation
	Integer type;
	@FieldMapperAnnotation
	Long recordCreateTime;
	@FieldMapperAnnotation
	String remark;
	@FieldMapperAnnotation
	String ext;
	
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getUsedMoney() {
		return usedMoney;
	}
	public void setUsedMoney(Double usedMoney) {
		this.usedMoney = usedMoney;
	}
	public Double getCurrMoney() {
		return currMoney;
	}
	public void setCurrMoney(Double currMoney) {
		this.currMoney = currMoney;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
