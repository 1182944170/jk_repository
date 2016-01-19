package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="baidu_city" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "code")
public class Banscity {
	@FieldMapperAnnotation
	private Integer code;   //
	@FieldMapperAnnotation
	private String city; // 
	@FieldMapperAnnotation
	private String cotiye; // 
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCotiye() {
		return cotiye;
	}
	public void setCotiye(String cotiye) {
		this.cotiye = cotiye;
	}
	
	
}
