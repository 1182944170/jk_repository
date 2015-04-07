package com.rpframework.module.adminbase.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * 
 * title: AdminRole.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年3月26日 下午4:35:59
 */
@TableMapperAnnotation(tableName = "admin_role", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")  
public class AdminRole extends Domain{
	
	/**
	 * CREATE TABLE `admin_role` ( `id` int(11) NOT NULL AUTO_INCREMENT, `name`
	 * varchar(255) DEFAULT NULL, `description` varchar(255) DEFAULT NULL,
	 * `state` int(11) DEFAULT NULL, `priority` int(11) DEFAULT '0', `isSuper`
	 * int(11) DEFAULT '1', PRIMARY KEY (`id`) ) ENGINE=MyISAM AUTO_INCREMENT=2
	 * DEFAULT CHARSET=utf8;
	 */

	/**描述*/  
	
	private static final long serialVersionUID = 1L;
	
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation
	String description;
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	Integer priority;
	@FieldMapperAnnotation
	Integer isSuper;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getIsSuper() {
		return isSuper;
	}
	public void setIsSuper(Integer isSuper) {
		this.isSuper = isSuper;
	}
}