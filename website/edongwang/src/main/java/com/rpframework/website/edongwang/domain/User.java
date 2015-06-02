package com.rpframework.website.edongwang.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "user", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id") 
public class User extends Domain {
	
	/**描述*/  
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	
	@FieldMapperAnnotation
	String contact; //手机号码
	
	@FieldMapperAnnotation
	String password;  //密码
	
	@FieldMapperAnnotation
	Integer sex;  //性别

	@FieldMapperAnnotation
	String realName;
	
	@FieldMapperAnnotation 
	String headImg; 
	
	@FieldMapperAnnotation
	String countyCode;
	
	@FieldMapperAnnotation
	Integer state;
	
	@FieldMapperAnnotation
	Integer isSalesman;
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
	
	UserSalesman userSalesman; //当isSalesman＝＝1 时 //业务员资料
	
	public Long getRecordCreateTime() {
		return recordCreateTime;
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

	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}

	public UserSalesman getUserSalesman() {
		return userSalesman;
	}

	public void setUserSalesman(UserSalesman userSalesman) {
		this.userSalesman = userSalesman;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsSalesman() {
		return isSalesman;
	}

	public void setIsSalesman(Integer isSalesman) {
		this.isSalesman = isSalesman;
	}
}
