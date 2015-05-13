package com.rpframework.website.edongwang.domain;

import java.util.List;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * loupan
 * @author hzyuanyong@126.com
 */
@TableMapperAnnotation(tableName = "e_house_recommend", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class HouseRecommend extends Domain{

	/**
	 * CREATE TABLE `e_house_recommend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerName` varchar(128) NOT NULL DEFAULT '',
  `contact` varchar(128) NOT NULL DEFAULT '',
  `propertyType` int(11) NOT NULL DEFAULT '1',
  `surfaceType` int(11) NOT NULL DEFAULT '1',
  `totalPriceType` int(11) NOT NULL DEFAULT '1',
  `areaCode` varchar(128) NOT NULL DEFAULT '',
  `firstHouseId` int(11) NOT NULL,
  `secondHouseId` int(11) NOT NULL,
  `customerInfo` varchar(512) DEFAULT '',
  `acceptSalesmanId` int(11) NOT NULL DEFAULT '0',
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1- 正常状态 2-完结',
  `recordCreateTime` int(11) NOT NULL DEFAULT '0',
  `acceptTime` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 */
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String customerName;
	@FieldMapperAnnotation
	String contact;
	@FieldMapperAnnotation
	Integer propertyType;
	@FieldMapperAnnotation
	Integer surfaceType;
	@FieldMapperAnnotation
	Integer totalPriceType;
	@FieldMapperAnnotation
	String areaCode;
	@FieldMapperAnnotation
	Integer houseId;
	@FieldMapperAnnotation
	String customerInfo;
	@FieldMapperAnnotation
	Integer acceptSalesmanId;
	@FieldMapperAnnotation
	Integer recommendUserId;
	@FieldMapperAnnotation
	Integer state;//'1- 正常状态 2-处理中 8-完结状态'
	@FieldMapperAnnotation
	Long recordCreateTime;
	@FieldMapperAnnotation
	Long acceptTime;
	
	User acceptSalesman;
	House house;
	User recommendUser;
	List<HouseRecommendProgress> progresses;
	
	public boolean checkNextProgressIsVaild(Integer progressType) {
		return this.progresses.size() + 1 == progressType;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}
	public Integer getSurfaceType() {
		return surfaceType;
	}
	public void setSurfaceType(Integer surfaceType) {
		this.surfaceType = surfaceType;
	}
	public Integer getTotalPriceType() {
		return totalPriceType;
	}
	public void setTotalPriceType(Integer totalPriceType) {
		this.totalPriceType = totalPriceType;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	public Integer getRecommendUserId() {
		return recommendUserId;
	}
	public void setRecommendUserId(Integer recommendUserId) {
		this.recommendUserId = recommendUserId;
	}
	public User getRecommendUser() {
		return recommendUser;
	}
	public void setRecommendUser(User recommendUser) {
		this.recommendUser = recommendUser;
	}
	public String getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
	public Integer getAcceptSalesmanId() {
		return acceptSalesmanId;
	}
	public void setAcceptSalesmanId(Integer acceptSalesmanId) {
		this.acceptSalesmanId = acceptSalesmanId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
	public Long getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(Long acceptTime) {
		this.acceptTime = acceptTime;
	}
	public User getAcceptSalesman() {
		return acceptSalesman;
	}
	public void setAcceptSalesman(User acceptSalesman) {
		this.acceptSalesman = acceptSalesman;
	}
	public List<HouseRecommendProgress> getProgresses() {
		return progresses;
	}
	public void setProgresses(List<HouseRecommendProgress> progresses) {
		this.progresses = progresses;
	}
}