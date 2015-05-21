package com.rpframework.module.common.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "cfg_bank_address", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class CfgBankAddress extends Domain {

	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String countyCode;
	@FieldMapperAnnotation
	String address;
	@FieldMapperAnnotation(dbFieldName="bankId", fieldType=FieldType.Object)
	CfgBank cfgBank;
	@FieldMapperAnnotation
	Integer state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
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
	public CfgBank getCfgBank() {
		return cfgBank;
	}
	public void setCfgBank(CfgBank cfgBank) {
		this.cfgBank = cfgBank;
	}
}
