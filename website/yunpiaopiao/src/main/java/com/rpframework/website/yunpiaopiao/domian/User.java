package com.rpframework.website.yunpiaopiao.domian;

import org.apache.commons.lang.StringUtils;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * title: XTUser.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年4月15日 下午5:05:34
 */
@TableMapperAnnotation(tableName = "user", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class User extends Domain {
	/**描述*/  
	private static final long serialVersionUID = 1L;
	
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String userName;
	@FieldMapperAnnotation
	String pwd;
	@FieldMapperAnnotation
	String nickName;
	@FieldMapperAnnotation
	String contact;
	@FieldMapperAnnotation
	String email;
	@FieldMapperAnnotation
	Integer state;
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
	String qqOpenId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public String getQqOpenId() {
		return qqOpenId;
	}
	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
	}
}