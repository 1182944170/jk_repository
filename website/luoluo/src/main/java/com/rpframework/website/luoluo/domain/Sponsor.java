package com.rpframework.website.luoluo.domain;

import java.util.List;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;
@TableMapperAnnotation(tableName="sponsor" , uniqueKeyType=UniqueKeyType.Single,uniqueKey = "id")
public class Sponsor {
	@FieldMapperAnnotation
	private Integer id;   // id
	@FieldMapperAnnotation
	private Integer activityid;  //活动id 
	@FieldMapperAnnotation
	private Integer userid;  // 用户id
	@FieldMapperAnnotation
	private String user_picture;  //真实头像
	@FieldMapperAnnotation
	private String user_nowlive;  //常驻地
	@FieldMapperAnnotation
	private String user_phone;  //领队电话
	@FieldMapperAnnotation
	private String user_information;  //领队信息
	@FieldMapperAnnotation
	private String user_telephone;  //公司负责手机
	@FieldMapperAnnotation
	private String telephone;  //公司电话
	@FieldMapperAnnotation
	private String responsibility;  // 企业负责任信息
	@FieldMapperAnnotation
	private String ent_introduction;  // 公司介绍
	@FieldMapperAnnotation
	private Integer type;  // 类型
	@FieldMapperAnnotation
	private Long activityTime;  // 成功次数
	
	
	
	private List<ClaGoods>  classList;//商品分类集合
	
	
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
	public String getUser_picture() {
		return user_picture;
	}
	public void setUser_picture(String user_picture) {
		this.user_picture = user_picture;
	}
	public String getUser_nowlive() {
		return user_nowlive;
	}
	public void setUser_nowlive(String user_nowlive) {
		this.user_nowlive = user_nowlive;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_information() {
		return user_information;
	}
	public void setUser_information(String user_information) {
		this.user_information = user_information;
	}
	public String getUser_telephone() {
		return user_telephone;
	}
	public void setUser_telephone(String user_telephone) {
		this.user_telephone = user_telephone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public String getEnt_introduction() {
		return ent_introduction;
	}
	public void setEnt_introduction(String ent_introduction) {
		this.ent_introduction = ent_introduction;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Long getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(Long activityTime) {
		this.activityTime = activityTime;
	}
	
	public List<ClaGoods> getClassList() {
		return classList;
	}
	public void setClassList(List<ClaGoods> classList) {
		this.classList = classList;
	}
	@Override
	public String toString() {
		return "Sponsor [id=" + id + ", activityid=" + activityid + ", userid="
				+ userid + ", user_picture=" + user_picture + ", user_nowlive="
				+ user_nowlive + ", user_phone=" + user_phone
				+ ", user_information=" + user_information
				+ ", user_telephone=" + user_telephone + ", telephone="
				+ telephone + ", responsibility=" + responsibility
				+ ", ent_introduction=" + ent_introduction + ", type=" + type
				+ ", activityTime=" + activityTime + "]";
	}
}
