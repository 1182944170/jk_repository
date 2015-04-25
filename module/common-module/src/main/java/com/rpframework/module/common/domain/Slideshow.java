package com.rpframework.module.common.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * 
 * title: Slideshow.java 
 * 幻灯片
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年4月24日 下午4:54:15
 */
@TableMapperAnnotation(tableName = "slideshow", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Slideshow extends Domain {

	/**描述*/  
	
	private static final long serialVersionUID = 1L;
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String icon;
	@FieldMapperAnnotation
	String title;
	@FieldMapperAnnotation
	String url;
	@FieldMapperAnnotation
	Integer type; //1-首页 2-xxxx
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	Integer sortIndex;
	public Integer getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
