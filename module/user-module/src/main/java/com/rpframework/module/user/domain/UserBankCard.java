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
	CfgBankAddress cfgBankAddres;
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
	public CfgBankAddress getCfgBankAddres() {
		return cfgBankAddres;
	}
	public void setCfgBankAddres(CfgBankAddress cfgBankAddres) {
		this.cfgBankAddres = cfgBankAddres;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
