package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="advice" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")

public class Adviceopen {
	@FieldMapperAnnotation
	private Integer id;  //意见建议id
	@FieldMapperAnnotation
	private String userphone;
	@FieldMapperAnnotation
	private String comments;
	@FieldMapperAnnotation
	private Integer type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
