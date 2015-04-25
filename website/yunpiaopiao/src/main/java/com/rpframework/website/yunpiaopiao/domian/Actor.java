package com.rpframework.website.yunpiaopiao.domian;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "actor", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Actor extends Domain {
	/**描述*/  
	
	private static final long serialVersionUID = 1L;
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation
	Integer gender;
	@FieldMapperAnnotation
	String icon;
	@FieldMapperAnnotation
	String content;
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
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
