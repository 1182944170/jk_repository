package com.rpframework.core.vo;

import com.rpframework.core.Domain;

public class BaseUserVO extends Domain {

	/**描述*/  
	private static final long serialVersionUID = -220652698778884437L;
	Integer userId;
	String contact; //手机号码
	String userName; //手机号码
	Integer sex;  //性别
	String realName;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
}
