package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="activity_registration" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Activitypicture {
	@FieldMapperAnnotation
	private Integer id;   //参加报名信息
	@FieldMapperAnnotation
	private String name;
	@FieldMapperAnnotation
	private String phone;
	@FieldMapperAnnotation
	private String emergencyphone;
	@FieldMapperAnnotation
	private String emergencyname;
	@FieldMapperAnnotation
	private String oldboy;
	@FieldMapperAnnotation
	private String chindenboy;
	@FieldMapperAnnotation
	private double monely;  //金额
	@FieldMapperAnnotation
	private String mood;
	@FieldMapperAnnotation
	private Integer type;
	@FieldMapperAnnotation
	private String insure; //投保证件
	@FieldMapperAnnotation
	private String insurenName; //投保姓名
	@FieldMapperAnnotation
	private Integer sponsorld;
	@FieldMapperAnnotation
	private Integer myld;
	@FieldMapperAnnotation
	private Integer number; //总数
	@FieldMapperAnnotation
	private double actualamount;  //实际金额
	@FieldMapperAnnotation
	private double counterFee;  // 手续费
	@FieldMapperAnnotation
	private String ordernumber;  // 订单号
	@FieldMapperAnnotation
	private Integer typeMonely;  // 支付方式
	@FieldMapperAnnotation
	private Integer typeOrder;  // 订单状态
	@FieldMapperAnnotation
	private long newtime;  // 订单状态
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getSponsorld() {
		return sponsorld;
	}
	public void setSponsorld(Integer sponsorld) {
		this.sponsorld = sponsorld;
	}
	public Integer getMyld() {
		return myld;
	}
	public void setMyld(Integer myld) {
		this.myld = myld;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmergencyphone() {
		return emergencyphone;
	}
	public void setEmergencyphone(String emergencyphone) {
		this.emergencyphone = emergencyphone;
	}
	public String getEmergencyname() {
		return emergencyname;
	}
	public void setEmergencyname(String emergencyname) {
		this.emergencyname = emergencyname;
	}
	public String getOldboy() {
		return oldboy;
	}
	public void setOldboy(String oldboy) {
		this.oldboy = oldboy;
	}
	public String getChindenboy() {
		return chindenboy;
	}
	public void setChindenboy(String chindenboy) {
		this.chindenboy = chindenboy;
	}

	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	

	public String getInsurenName() {
		return insurenName;
	}
	public void setInsurenName(String insurenName) {
		this.insurenName = insurenName;
	}
	public void setInsure(String insure) {
		this.insure = insure;
	}
	
	
	public String getInsure() {
		return insure;
	}
	public double getMonely() {
		return monely;
	}
	public void setMonely(double monely) {
		this.monely = monely;
	}
	public double getActualamount() {
		return actualamount;
	}
	public void setActualamount(double actualamount) {
		this.actualamount = actualamount;
	}
	public double getCounterFee() {
		return counterFee;
	}
	public void setCounterFee(double counterFee) {
		this.counterFee = counterFee;
	}
	public Integer getTypeMonely() {
		return typeMonely;
	}
	public void setTypeMonely(Integer typeMonely) {
		this.typeMonely = typeMonely;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	
	public Integer getTypeOrder() {
		return typeOrder;
	}
	public void setTypeOrder(Integer typeOrder) {
		this.typeOrder = typeOrder;
	}
	
	public long getNewtime() {
		return newtime;
	}
	public void setNewtime(long newtime) {
		this.newtime = newtime;
	}
	@Override
	public String toString() {
		return "Activitypicture [id=" + id + ", name=" + name + ", phone="
				+ phone + ", emergencyphone=" + emergencyphone
				+ ", emergencyname=" + emergencyname + ", oldboy=" + oldboy
				+ ", chindenboy=" + chindenboy + ", monely=" + monely
				+ ", mood=" + mood + ", type=" + type + ", insure=" + insure
				+ "]";
	}
	
}
