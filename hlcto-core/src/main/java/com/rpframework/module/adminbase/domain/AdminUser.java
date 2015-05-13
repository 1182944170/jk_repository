package com.rpframework.module.adminbase.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * 
 * title: AdminUser.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年3月26日 下午4:58:13
 */

@TableMapperAnnotation(tableName = "admin_user", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")  
public class AdminUser extends Domain {

	/**描述*/  
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(128) NOT NULL DEFAULT '0',
  `selfAdmin` int(11) DEFAULT '0' COMMENT '受限管理员,只能管理自己的数据',
  `viewOnlyAdmin` int(11) DEFAULT '0' COMMENT '只读管理员',
  `userName` varchar(64) NOT NULL DEFAULT '',
  `state` int(11) DEFAULT '1',
  `pwd` varchar(128) NOT NULL DEFAULT '',
  `recordCreateTime` int(11) DEFAULT '0',
  `lastLoginTime` int(11) DEFAULT '0',
  `loginTime` int(11) DEFAULT '0',
  `loginIp` int(11) DEFAULT '0',
  `lastLoginIp` int(11) DEFAULT '0',
  `contact` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='后台用户';
	 * 
	 * 
	 */
	@FieldMapperAnnotation
	Integer id;
	
	@FieldMapperAnnotation(dbFieldName = "adminRoleId", fieldType=FieldType.Object)
	AdminRole adminRole;
	
	@FieldMapperAnnotation
	Integer selfAdmin;
	
	@FieldMapperAnnotation
	Integer viewOnlyAdmin;
	
	@FieldMapperAnnotation
	String userName;
	
	@FieldMapperAnnotation
	Integer state;
	
	@FieldMapperAnnotation
	String pwd;
	
	@FieldMapperAnnotation
	Long recordCreateTime;
	
	@FieldMapperAnnotation
	Long lastLoginTime;
	
	@FieldMapperAnnotation
	Long loginTime;
	
	@FieldMapperAnnotation
	String loginIp;
	
	@FieldMapperAnnotation
	String lastLoginIp;
	
	@FieldMapperAnnotation
	String contact;
	
	@FieldMapperAnnotation
	String email;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AdminRole getAdminRole() {
		return adminRole;
	}
	public void setAdminRole(AdminRole adminRole) {
		this.adminRole = adminRole;
	}
	public Integer getSelfAdmin() {
		return selfAdmin;
	}
	public void setSelfAdmin(Integer selfAdmin) {
		this.selfAdmin = selfAdmin;
	}
	public Integer getViewOnlyAdmin() {
		return viewOnlyAdmin;
	}
	public void setViewOnlyAdmin(Integer viewOnlyAdmin) {
		this.viewOnlyAdmin = viewOnlyAdmin;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
	public Long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
}
