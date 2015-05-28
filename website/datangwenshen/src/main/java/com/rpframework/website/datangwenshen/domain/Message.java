package com.rpframework.website.datangwenshen.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@SuppressWarnings("serial")
@TableMapperAnnotation(tableName = "message", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id") 
public class Message extends Domain {
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation
	String content;
	@FieldMapperAnnotation
	String phone;
	@FieldMapperAnnotation
	Integer sex;
	
	@FieldMapperAnnotation
	long recordCreateTime;
	
	public long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
}
