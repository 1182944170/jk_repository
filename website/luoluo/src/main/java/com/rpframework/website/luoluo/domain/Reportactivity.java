package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="report_activity" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")

public class Reportactivity {
	@FieldMapperAnnotation
	private Integer id;  //举报表
	@FieldMapperAnnotation
	private Integer activityid;  //举报活动id
	@FieldMapperAnnotation
	private Integer userid;  //举报的用户
	@FieldMapperAnnotation
	private String reportContext; //举报内容
	@FieldMapperAnnotation
	private Long acttime;  //举报时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActivityid() {
		return activityid;
	}
	public void setActivityid(Integer activityid) {
		this.activityid = activityid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getReportContext() {
		return reportContext;
	}
	public void setReportContext(String reportContext) {
		this.reportContext = reportContext;
	}
	
	public Long getActtime() {
		return acttime;
	}
	public void setActtime(Long acttime) {
		this.acttime = acttime;
	}
	@Override
	public String toString() {
		return "Reportactivity [id=" + id + ", activityid=" + activityid
				+ ", userid=" + userid + ", reportContext=" + reportContext
				+ "]";
	}
	
	
}
