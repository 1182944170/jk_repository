package com.rpframework.website.luoluo.domain;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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
	private String cover; // 封面图片
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
	private Integer gril_expense;    //儿童费用
	@FieldMapperAnnotation
	private Integer old_expense;  //成年人费用
	@FieldMapperAnnotation
	private String activitypicture;  //活动图片
	@FieldMapperAnnotation
	private String activitycontent;  //活动内容
	@FieldMapperAnnotation
	private long starttime;   //开始时间
	@FieldMapperAnnotation
	private long outtime;   //结束时间
	@FieldMapperAnnotation
	private long nowforetime;   //现在时间
	@FieldMapperAnnotation
	private long allmonly;   //总金额
	@FieldMapperAnnotation
	private Integer type;  //类型
	@FieldMapperAnnotation
	private Integer typeok;  //举办是否成功
	@FieldMapperAnnotation
	private String lng; //经度
	@FieldMapperAnnotation
	private String lat; //纬度
	@FieldMapperAnnotation
	private String phone; //纬度
	@FieldMapperAnnotation
	private String city; //地址
	
	private Integer bm_num; //纬度
	private Integer juli; //纬度




	public Integer getJuli() {
		return juli;
	}

	public void setJuli(Integer juli) {
		this.juli = juli;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

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
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
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

	public Integer getGril_expense() {
		return gril_expense;
	}

	public void setGril_expense(Integer gril_expense) {
		this.gril_expense = gril_expense;
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

	public long getStarttime() {
		return starttime;
	}

	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}

	public long getOuttime() {
		return outtime;
	}

	public void setOuttime(long outtime) {
		this.outtime = outtime;
	}

	public long getNowforetime() {
		return nowforetime;
	}

	public void setNowforetime(long nowforetime) {
		this.nowforetime = nowforetime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getBm_num() {
		return bm_num;
	}

	public void setBm_num(Integer bm_num) {
		this.bm_num = bm_num;
	}

	public Integer getTypeok() {
		return typeok;
	}

	public void setTypeok(Integer typeok) {
		this.typeok = typeok;
	}
	
	public long getAllmonly() {
		return allmonly;
	}
	public void setAllmonly(long allmonly) {
		this.allmonly = allmonly;
	}

	public List<String> getPhotoPathList(){
		if(StringUtils.isBlank(getActivitypicture())) {
			return null;
		}
		JsonArray array = new JsonParser().parse(getActivitypicture()).getAsJsonArray();
		List<String> list = new ArrayList<String>();
		
		for (JsonElement je : array) {
			list.add(je.getAsString());
		}
		return list;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", sponsorid=" + sponsorid + ", cover="
				+ cover + ", activitynumber=" + activitynumber
				+ ", activityname=" + activityname + ", activitycategory="
				+ activitycategory + ", activitylocation=" + activitylocation
				+ ", number=" + number + ", children_expense="
				+ children_expense + ", old_expense=" + old_expense
				+ ", activitypicture=" + activitypicture + ", activitycontent="
				+ activitycontent + ", starttime=" + starttime + ", outtime="
				+ outtime + ", nowforetime=" + nowforetime + ", type=" + type
				+ ", typeok=" + typeok + ", lng=" + lng + ", lat=" + lat + "]";
	}


	
	
	
}
