package com.rpframework.website.luoluo.domain;

import java.util.List;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="release_activity" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Activity {
	@FieldMapperAnnotation
	private Integer id;   //
	@FieldMapperAnnotation
	private Integer sponsorid; // 主办方Id
	@FieldMapperAnnotation
	private String Activity_number;  // 活动编号
	@FieldMapperAnnotation
	private String Activityname;  //活动名称
	@FieldMapperAnnotation
	private String Activitycategory;   //活动类别
	@FieldMapperAnnotation
	private String Activitylocation;   //活动地点
	@FieldMapperAnnotation
	private Integer number;  //活动人数
	@FieldMapperAnnotation
	private Integer children_expense;    //儿童费用
	@FieldMapperAnnotation
	private Integer old_expense;  //成年人费用
	@FieldMapperAnnotation
	private String activity_picture;  //活动图片
	@FieldMapperAnnotation
	private String activity_content;  //活动内容
	@FieldMapperAnnotation
	private String Starttime;   //开始时间
	@FieldMapperAnnotation
	private String Outtime;   //结束时间
	@FieldMapperAnnotation
	private String nowtime;   //现在时间
	@FieldMapperAnnotation
	private Integer type;  //类型
	@FieldMapperAnnotation
	private Integer typeok;  //举办是否成功
	
	
	private List<ClaGoods>  classList;//商品分类集合
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSponsorid() {
		return sponsorid;
	}
	public void setSponsorid(Integer sponsorid) {
		this.sponsorid = sponsorid;
	}
	public String getActivity_number() {
		return Activity_number;
	}
	public void setActivity_number(String activity_number) {
		Activity_number = activity_number;
	}
	public String getActivityname() {
		return Activityname;
	}
	public void setActivityname(String activityname) {
		Activityname = activityname;
	}
	public String getActivitycategory() {
		return Activitycategory;
	}
	public void setActivitycategory(String activitycategory) {
		Activitycategory = activitycategory;
	}
	public String getActivitylocation() {
		return Activitylocation;
	}
	public void setActivitylocation(String activitylocation) {
		Activitylocation = activitylocation;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getChildren_expense() {
		return children_expense;
	}
	public void setChildren_expense(Integer children_expense) {
		this.children_expense = children_expense;
	}
	public Integer getOld_expense() {
		return old_expense;
	}
	public void setOld_expense(Integer old_expense) {
		this.old_expense = old_expense;
	}
	public String getActivity_picture() {
		return activity_picture;
	}
	public void setActivity_picture(String activity_picture) {
		this.activity_picture = activity_picture;
	}
	public String getActivity_content() {
		return activity_content;
	}
	public void setActivity_content(String activity_content) {
		this.activity_content = activity_content;
	}
	public String getStarttime() {
		return Starttime;
	}
	public void setStarttime(String starttime) {
		Starttime = starttime;
	}
	public String getOuttime() {
		return Outtime;
	}
	public void setOuttime(String outtime) {
		Outtime = outtime;
	}
	public String getNowtime() {
		return nowtime;
	}
	public void setNowtime(String nowtime) {
		this.nowtime = nowtime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getTypeok() {
		return typeok;
	}
	public void setTypeok(Integer typeok) {
		this.typeok = typeok;
	}
	
	public List<ClaGoods> getClassList() {
		return classList;
	}
	public void setClassList(List<ClaGoods> classList) {
		this.classList = classList;
	}
	@Override
	public String toString() {
		return "Activity [id=" + id + ", sponsorid=" + sponsorid
				+ ", Activity_number=" + Activity_number + ", Activityname="
				+ Activityname + ", Activitycategory=" + Activitycategory
				+ ", Activitylocation=" + Activitylocation + ", number="
				+ number + ", children_expense=" + children_expense
				+ ", old_expense=" + old_expense + ", activity_picture="
				+ activity_picture + ", activity_content=" + activity_content
				+ ", Starttime=" + Starttime + ", Outtime=" + Outtime
				+ ", nowtime=" + nowtime + ", type=" + type + ", typeok="
				+ typeok + "]";
	}
	
}
