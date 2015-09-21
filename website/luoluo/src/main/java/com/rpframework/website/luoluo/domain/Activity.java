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
	private String Cover; // 封面图片
	@FieldMapperAnnotation
	private String activitynumber;  // 活动编号
	@FieldMapperAnnotation
	private String activityname;  //活动名称
	@FieldMapperAnnotation
	private Integer activitycategory;   //活动类别
	@FieldMapperAnnotation
	private String activitylocation;   //活动地点
	@FieldMapperAnnotation
	private Integer number;  //活动人数
	@FieldMapperAnnotation
	private Integer children_expense;    //儿童费用
	@FieldMapperAnnotation
	private Integer old_expense;  //成年人费用
	@FieldMapperAnnotation
	private String activitypicture;  //活动图片
	@FieldMapperAnnotation
	private String activitycontent;  //活动内容
	@FieldMapperAnnotation
	private String starttime;   //开始时间
	@FieldMapperAnnotation
	private String outtime;   //结束时间
	@FieldMapperAnnotation
	private long nowforetime;   //现在时间
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

	public String getCover() {
		return Cover;
	}

	public void setCover(String cover) {
		Cover = cover;
	}

	public String getActivitynumber() {
		return activitynumber;
	}

	public void setActivitynumber(String activitynumber) {
		this.activitynumber = activitynumber;
	}

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public Integer getActivitycategory() {
		return activitycategory;
	}

	public void setActivitycategory(Integer activitycategory) {
		this.activitycategory = activitycategory;
	}

	public String getActivitylocation() {
		return activitylocation;
	}

	public void setActivitylocation(String activitylocation) {
		this.activitylocation = activitylocation;
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

	public String getActivitypicture() {
		return activitypicture;
	}

	public void setActivitypicture(String activitypicture) {
		this.activitypicture = activitypicture;
	}

	public String getActivitycontent() {
		return activitycontent;
	}

	public void setActivitycontent(String activitycontent) {
		this.activitycontent = activitycontent;
	}

	
	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getOuttime() {
		return outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
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

	public long getNowforetime() {
		return nowforetime;
	}

	public void setNowforetime(long nowforetime) {
		this.nowforetime = nowforetime;
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
		return "Activity [id=" + id + ", sponsorid=" + sponsorid + ", Cover="
				+ Cover + ", activitynumber=" + activitynumber
				+ ", activityname=" + activityname + ", activitycategory="
				+ activitycategory + ", activitylocation=" + activitylocation
				+ ", number=" + number + ", children_expense="
				+ children_expense + ", old_expense=" + old_expense
				+ ", activitypicture=" + activitypicture + ", activitycontent="
				+ activitycontent + ", Starttime=" + starttime + ", Outtime="
				+ outtime + ", nowtime=, type=" + type
				+ ", typeok=" + typeok + ", classList=" + classList + "]";
	}
	
	
	
	
	
}
