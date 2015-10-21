package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="monlyjournal" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Monlyjournals {
	@FieldMapperAnnotation
	private Integer id;   // id
	@FieldMapperAnnotation
	private String remark;   // 支付的名字
	@FieldMapperAnnotation
	private Integer userid;   // 用户id
	@FieldMapperAnnotation
	private double monly;   // 金额
	@FieldMapperAnnotation
	private long newtime;   // 时间
	@FieldMapperAnnotation
	private Integer type;   // id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public double getMonly() {
		return monly;
	}
	public void setMonly(double monly) {
		this.monly = monly;
	}
	public long getNewtime() {
		return newtime;
	}
	public void setNewtime(long newtime) {
		this.newtime = newtime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
