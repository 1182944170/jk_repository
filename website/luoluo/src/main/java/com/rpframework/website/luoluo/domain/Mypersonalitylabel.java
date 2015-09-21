package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="mypersonalitylabel" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")

public class Mypersonalitylabel {
	@FieldMapperAnnotation
	private Integer id;  //id
	@FieldMapperAnnotation
	private Integer userid;  //id
	@FieldMapperAnnotation
	private Integer mylabel1;  //id
	@FieldMapperAnnotation
	private Integer mylabel2;  //id
	@FieldMapperAnnotation
	private Integer mylabel3;  //id
	@FieldMapperAnnotation
	private Integer mylabel4;  //id
	@FieldMapperAnnotation
	private Integer mylabel5;  //id
	@FieldMapperAnnotation
	private Integer mylabel6;  //id
	@FieldMapperAnnotation
	private Integer mylabel7;  //id
	@FieldMapperAnnotation
	private Integer mylabel8;  //id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getMylabel1() {
		return mylabel1;
	}
	public void setMylabel1(Integer mylabel1) {
		this.mylabel1 = mylabel1;
	}
	public Integer getMylabel2() {
		return mylabel2;
	}
	public void setMylabel2(Integer mylabel2) {
		this.mylabel2 = mylabel2;
	}
	public Integer getMylabel3() {
		return mylabel3;
	}
	public void setMylabel3(Integer mylabel3) {
		this.mylabel3 = mylabel3;
	}
	public Integer getMylabel4() {
		return mylabel4;
	}
	public void setMylabel4(Integer mylabel4) {
		this.mylabel4 = mylabel4;
	}
	public Integer getMylabel5() {
		return mylabel5;
	}
	public void setMylabel5(Integer mylabel5) {
		this.mylabel5 = mylabel5;
	}
	public Integer getMylabel6() {
		return mylabel6;
	}
	public void setMylabel6(Integer mylabel6) {
		this.mylabel6 = mylabel6;
	}
	public Integer getMylabel7() {
		return mylabel7;
	}
	public void setMylabel7(Integer mylabel7) {
		this.mylabel7 = mylabel7;
	}
	public Integer getMylabel8() {
		return mylabel8;
	}
	public void setMylabel8(Integer mylabel8) {
		this.mylabel8 = mylabel8;
	}
	
	
}
