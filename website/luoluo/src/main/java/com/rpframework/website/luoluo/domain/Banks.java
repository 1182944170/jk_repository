package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="bank" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Banks {
	@FieldMapperAnnotation
	private Integer id;   //
	@FieldMapperAnnotation
	private String bankname; // 银行卡名字
	@FieldMapperAnnotation
	private String bankprivice; // 银行卡图片
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankprivice() {
		return bankprivice;
	}
	public void setBankprivice(String bankprivice) {
		this.bankprivice = bankprivice;
	}

}
