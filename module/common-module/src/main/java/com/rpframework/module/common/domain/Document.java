package com.rpframework.module.common.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "document", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Document extends Domain {

	/**描述*/  
	private static final long serialVersionUID = 1L;

	/**
	 * 代码	名称	级别	面积	人口	驻地	邮政编码	区号	经度	纬度	卫星地图	普通地图
	 */
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation(dbFieldName="parentId", fieldType = FieldType.Object)
	Document parent;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	String content;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Document getParent() {
		return parent;
	}
	public void setParent(Document parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
