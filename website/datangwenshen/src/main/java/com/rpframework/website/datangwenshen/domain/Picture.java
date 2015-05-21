package com.rpframework.website.datangwenshen.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "picture", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id") 
public class Picture extends Domain{
	private static final long serialVersionUID = 1L;
	
	@FieldMapperAnnotation
	private Integer id; // 图片的ID
	@FieldMapperAnnotation
	private String name; // 名称
	@FieldMapperAnnotation
	private int type;   // 类型  1:  2:  
	@FieldMapperAnnotation
	private int source;  // 来源  1:自己的  2:网络
	@FieldMapperAnnotation
	private String address;  // 图片的地址
	@FieldMapperAnnotation
	private int state; // 图片的状态  1:显示   -1:不显示
	@FieldMapperAnnotation
	private long date; // 时间
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
}
