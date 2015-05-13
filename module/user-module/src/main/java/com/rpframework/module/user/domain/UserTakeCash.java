package com.rpframework.module.user.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "u_take_cash", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class UserTakeCash extends Domain {
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	Integer userId;
	@FieldMapperAnnotation
	Double money;
	@FieldMapperAnnotation
	String remark;
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	Long recordCreateTime;
	@FieldMapperAnnotation
	Long recordModifyTime;
	@FieldMapperAnnotation
	Integer verifyUId;
	@FieldMapperAnnotation
	String verifyRemark;
	@FieldMapperAnnotation
	Integer userBankCardId;
	
	UserBankCard userBankCard;
	
	public Integer getUserBankCardId() {
		return userBankCardId;
	}
	public void setUserBankCardId(Integer userBankCardId) {
		this.userBankCardId = userBankCardId;
	}
	public UserBankCard getUserBankCard() {
		return userBankCard;
	}
	public void setUserBankCard(UserBankCard userBankCard) {
		this.userBankCard = userBankCard;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public Integer getVerifyUId() {
		return verifyUId;
	}
	public void setVerifyUId(Integer verifyUId) {
		this.verifyUId = verifyUId;
	}
	public String getVerifyRemark() {
		return verifyRemark;
	}
	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}
}