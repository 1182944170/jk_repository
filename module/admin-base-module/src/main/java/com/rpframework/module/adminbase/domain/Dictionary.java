package com.rpframework.module.adminbase.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * 
 * title: AdminAuthRes.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年3月26日 下午4:52:12
 */
@TableMapperAnnotation(tableName = "dictionary_setting", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "variable") 
public class Dictionary extends Domain {

	/**描述*/  
	
	private static final long serialVersionUID = 1L;
	@FieldMapperAnnotation
	String variable;
	@FieldMapperAnnotation
	String value;
	@FieldMapperAnnotation
	String type;
	@FieldMapperAnnotation
	Integer canUpdate;
	@FieldMapperAnnotation
	Integer canDelete;
	
	@FieldMapperAnnotation
	Long sortIndex;
	
	
	public Integer getCanUpdate() {
		return canUpdate;
	}
	public void setCanUpdate(Integer canUpdate) {
		this.canUpdate = canUpdate;
	}
	public Integer getCanDelete() {
		return canDelete;
	}
	public void setCanDelete(Integer canDelete) {
		this.canDelete = canDelete;
	}
	public String getVariable() {
		return variable;
	}
	public void setVariable(String variable) {
		this.variable = variable;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(Long sortIndex) {
		this.sortIndex = sortIndex;
	}
}
