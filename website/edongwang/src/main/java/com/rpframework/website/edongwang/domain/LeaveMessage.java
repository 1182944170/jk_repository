package com.rpframework.website.edongwang.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * title: LeaveMessage.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月7日 下午5:31:26
 */
@TableMapperAnnotation(tableName = "e_leave_msg", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class LeaveMessage extends Domain{

	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	
	@FieldMapperAnnotation
	Integer userId; //楼盘名称
	
	@FieldMapperAnnotation
	String message; //物业类型
	@FieldMapperAnnotation
	Long recordCreateTime; //创建时间
	
	User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
}