package com.rpframework.module.adminbase.domain;

import java.util.List;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * 
 * title: AdminMenu.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年3月26日 下午4:54:58
 */
@TableMapperAnnotation(tableName = "admin_menu", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class AdminMenu extends Domain {

	/**描述*/  
	
	private static final long serialVersionUID = 1L;

	
/**
 * CREATE TABLE `admin_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL,
  `menuName` varchar(255) DEFAULT '',
  `linkUrl` varchar(255) DEFAULT '',
  `limit` int(11) DEFAULT '0',
  `target` varchar(255) DEFAULT 'MainFrame',
  `orderIndex` double(11,0) unsigned DEFAULT '0',
  `state` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10108 DEFAULT CHARSET=utf8;
 */
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation(dbFieldName = "parentId", fieldType=FieldType.Object)
	AdminMenu parent;
	@FieldMapperAnnotation
	String menuName;
	@FieldMapperAnnotation
	String linkUrl;
	@FieldMapperAnnotation
	String icon;
	@FieldMapperAnnotation
	Integer orderIndex;
	@FieldMapperAnnotation
	Integer state;
	
	List<AdminMenu> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminMenu getParent() {
		return parent;
	}

	public void setParent(AdminMenu parent) {
		this.parent = parent;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<AdminMenu> getChildren() {
		return children;
	}

	public void setChildren(List<AdminMenu> children) {
		this.children = children;
	}
}
