package com.rpframework.module.common.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "notice", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Notice extends Domain {

	/**描述*/  
	private static final long serialVersionUID = 1L;

	/**
	 * 代码	名称	级别	面积	人口	驻地	邮政编码	区号	经度	纬度	卫星地图	普通地图
	 */
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String title;
	@FieldMapperAnnotation
	String content;
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	Long recoreCreateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getRecoreCreateTime() {
		return recoreCreateTime;
	}
	public void setRecoreCreateTime(Long recoreCreateTime) {
		this.recoreCreateTime = recoreCreateTime;
	}
}
