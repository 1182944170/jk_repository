package com.rpframework.module.adminbase.domain;

import java.util.List;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
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
@TableMapperAnnotation(tableName = "admin_auth_res", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id") 
public class AdminAuthRes extends Domain {

	/**描述*/  
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * CREATE TABLE `admin_auth_res` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT '' COMMENT '资源名',
  `path` varchar(255) DEFAULT '' COMMENT '资源路径',
  `sortIndex` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='后台需要权限控制的资源映射表';
	 * 
	 */
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation(dbFieldName = "parentId", fieldType=FieldType.Object)
	AdminAuthRes parent;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation
	String path;
	@FieldMapperAnnotation
	Long sortIndex;
	
	List<AdminAuthRes> children;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AdminAuthRes getParent() {
		return parent;
	}
	public void setParent(AdminAuthRes parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Long getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(Long sortIndex) {
		this.sortIndex = sortIndex;
	}
	public List<AdminAuthRes> getChildren() {
		return children;
	}
	public void setChildren(List<AdminAuthRes> children) {
		this.children = children;
	}
	
	
}
