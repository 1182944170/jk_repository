package com.rpframework.module.user.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "u_bank_card", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class UserBankCard extends Domain {

	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	Integer userId;
	@FieldMapperAnnotation
	String account;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation(dbFieldName="openAddrId", fieldType=FieldType.Object)
	CfgBankAddress cfgBankAddress;
	@FieldMapperAnnotation(dbFieldName="bankId", fieldType=FieldType.Object) //cfgBankAddres 跟 cfgBank、address 是互斥的，兼容俩种情况
	CfgBank cfgBank;
	@FieldMapperAnnotation
	String address;
	@FieldMapperAnnotation
	Integer state;
	
	
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
	public CfgBankAddress getCfgBankAddress() {
		return cfgBankAddress;
	}
	public void setCfgBankAddress(CfgBankAddress cfgBankAddress) {
		this.cfgBankAddress = cfgBankAddress;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
