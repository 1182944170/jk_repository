package com.rpframework.module.user.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;
import com.rpframework.core.vo.BaseUserVO;
import com.rpframework.module.common.domain.CfgBank;
import com.rpframework.module.common.domain.CfgBankAddress;

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
	
	@FieldMapperAnnotation
	String account;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation(dbFieldName="bankId", fieldType=FieldType.Object) //cfgBankAddres 跟 cfgBank、address 是互斥的，兼容俩种情况
	CfgBank cfgBank;
	@FieldMapperAnnotation
	String address;
	
	BaseUserVO user;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CfgBank getCfgBank() {
		return cfgBank;
	}
	public void setCfgBank(CfgBank cfgBank) {
		this.cfgBank = cfgBank;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BaseUserVO getUser() {
		return user;
	}
	public void setUser(BaseUserVO user) {
		this.user = user;
	}
	public Integer getUserBankCardId() {
		return userBankCardId;
	}
	public void setUserBankCardId(Integer userBankCardId) {
		this.userBankCardId = userBankCardId;
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