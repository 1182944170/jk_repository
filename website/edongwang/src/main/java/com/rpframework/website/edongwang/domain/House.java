package com.rpframework.website.edongwang.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * loupan
 * @author hzyuanyong@126.com
 */
@TableMapperAnnotation(tableName = "e_house", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class House extends Domain{

	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	
	@FieldMapperAnnotation
	String name; //楼盘名称
	
	@FieldMapperAnnotation
	Integer propertyType; //物业类型
	
	@FieldMapperAnnotation
	String areaCode ; //区域
	
	@FieldMapperAnnotation
	String address ; //详细地址
	
	@FieldMapperAnnotation
	Integer surfaceType; //面积
	
	@FieldMapperAnnotation
	Double avgPrice; //总价
	
	@FieldMapperAnnotation
	Double recommendPrice ; //推荐奖励
	
	@FieldMapperAnnotation
	Double bargainPrice; // 成交奖励
	
	@FieldMapperAnnotation
	String houseImg ; //楼盘主图
	
	@FieldMapperAnnotation
	String flowIntro ; //推荐流程及奖励说明
	
	@FieldMapperAnnotation
	String intro; //楼盘简介
	
	@FieldMapperAnnotation
	String houseTypeImg ; //户型图
	
	@FieldMapperAnnotation
	Long protocolBeginTime ; //协议开始时间
	
	@FieldMapperAnnotation
	Long protocolEndTime ; //协议结束时间
	
	@FieldMapperAnnotation
	Long recordCreateTime; //创建时间
	
	@FieldMapperAnnotation
	Integer state;

	public Integer getId() {
		return id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
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

	public Integer getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSurfaceType() {
		return surfaceType;
	}

	public void setSurfaceType(Integer surfaceType) {
		this.surfaceType = surfaceType;
	}

	public Double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}

	public Double getRecommendPrice() {
		return recommendPrice;
	}

	public void setRecommendPrice(Double recommendPrice) {
		this.recommendPrice = recommendPrice;
	}

	public Double getBargainPrice() {
		return bargainPrice;
	}

	public void setBargainPrice(Double bargainPrice) {
		this.bargainPrice = bargainPrice;
	}

	public String getHouseImg() {
		return houseImg;
	}

	public void setHouseImg(String houseImg) {
		this.houseImg = houseImg;
	}

	public String getFlowIntro() {
		return flowIntro;
	}

	public void setFlowIntro(String flowIntro) {
		this.flowIntro = flowIntro;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public Long getRecordCreateTime() {
		return recordCreateTime;
	}

	public void setCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
}