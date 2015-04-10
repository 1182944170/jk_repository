package com.rpframework.website.edongwang.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * loupan
 * @author hzyuanyong@126.com
 *
 */
@TableMapperAnnotation(tableName = "e_house", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Houses extends Domain{

	
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	
	@FieldMapperAnnotation
	String name; //楼盘名称
	
	@FieldMapperAnnotation
	String propertyType; //物业类型
	
	@FieldMapperAnnotation
	String areaCode ; //区域
	
	@FieldMapperAnnotation
	String surfaceType; //面积
	
	@FieldMapperAnnotation
	String totalPrice; //总价
	
	@FieldMapperAnnotation
	String recommendPrice ; //推荐奖励
	
	@FieldMapperAnnotation
	String bargainPrice; // 成交奖励
	
	@FieldMapperAnnotation
	String houseImg ; //楼盘主图
	
	@FieldMapperAnnotation
	String declareHouse ; //推荐流程及奖励说明
	
	@FieldMapperAnnotation
	String introHouse; //楼盘简介
	
	@FieldMapperAnnotation
	String houseTypeImg ; //户型图
	
	@FieldMapperAnnotation
	Long protocolBeginTime ; //协议开始时间
	
	@FieldMapperAnnotation
	Long protocolEndTime ; //协议结束时间
	
	@FieldMapperAnnotation
	Long createTime; //创建时间

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getSurfaceType() {
		return surfaceType;
	}

	public void setSurfaceType(String surfaceType) {
		this.surfaceType = surfaceType;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getRecommendPrice() {
		return recommendPrice;
	}

	public void setRecommendPrice(String recommendPrice) {
		this.recommendPrice = recommendPrice;
	}

	public String getBargainPrice() {
		return bargainPrice;
	}

	public void setBargainPrice(String bargainPrice) {
		this.bargainPrice = bargainPrice;
	}

	public String getHouseImg() {
		return houseImg;
	}

	public void setHouseImg(String houseImg) {
		this.houseImg = houseImg;
	}

	public String getDeclareHouse() {
		return declareHouse;
	}

	public void setDeclareHouse(String declareHouse) {
		this.declareHouse = declareHouse;
	}

	public String getIntroHouse() {
		return introHouse;
	}

	public void setIntroHouse(String introHouse) {
		this.introHouse = introHouse;
	}

	public String getHouseTypeImg() {
		return houseTypeImg;
	}

	public void setHouseTypeImg(String houseTypeImg) {
		this.houseTypeImg = houseTypeImg;
	}

	public Long getProtocolBeginTime() {
		return protocolBeginTime;
	}

	public void setProtocolBeginTime(Long protocolBeginTime) {
		this.protocolBeginTime = protocolBeginTime;
	}

	public Long getProtocolEndTime() {
		return protocolEndTime;
	}

	public void setProtocolEndTime(Long protocolEndTime) {
		this.protocolEndTime = protocolEndTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
}
