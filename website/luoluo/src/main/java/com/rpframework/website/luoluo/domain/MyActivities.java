package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="myactivities" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class MyActivities {
	@FieldMapperAnnotation
	private Integer id; //我的活动
	@FieldMapperAnnotation
	private Integer activitiesid;
	@FieldMapperAnnotation
	private Integer userid;
	@FieldMapperAnnotation
	private Integer type; //1收藏2,取消 和 3参加 ,4取消
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActivitiesid() {
		return activitiesid;
	}
	public void setActivitiesid(Integer activitiesid) {
		this.activitiesid = activitiesid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
