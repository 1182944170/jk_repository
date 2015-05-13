package com.rpframework.module.user.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "u_money", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "userId")
public class UserMoney extends Domain {

	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer userId;
	@FieldMapperAnnotation
	Double money;
	@FieldMapperAnnotation
	Double freezeMoney;
	@FieldMapperAnnotation
	Double usedMoney;
	@FieldMapperAnnotation
	Long recordCreateTime;
	@FieldMapperAnnotation
	Long recordModifyTime;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getFreezeMoney() {
		return freezeMoney;
	}
	public void setFreezeMoney(Double freezeMoney) {
		this.freezeMoney = freezeMoney;
	}
	public Double getUsedMoney() {
		return usedMoney;
	}
	public void setUsedMoney(Double usedMoney) {
		this.usedMoney = usedMoney;
	}
	public Long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
	public Long getRecordModifyTime() {
		return recordModifyTime;
	}
	public void setRecordModifyTime(Long recordModifyTime) {
		this.recordModifyTime = recordModifyTime;
	}
}
