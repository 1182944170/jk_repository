package com.rpframework.module.common.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * 
 * title: NewsPlugin.java 
 * 消息通知给用户
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月10日 下午9:24:45
 */
@TableMapperAnnotation(tableName = "news_plugin", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class NewsPlugin extends Domain {
	private static final long serialVersionUID = -2612839186515764268L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String moduleName;
	@FieldMapperAnnotation
	Integer modulePri;
	@FieldMapperAnnotation
	Integer userId;
	@FieldMapperAnnotation
	Integer count;
	@FieldMapperAnnotation
	Long modifyTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Integer getModulePri() {
		return modulePri;
	}
	public void setModulePri(Integer modulePri) {
		this.modulePri = modulePri;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}
}
