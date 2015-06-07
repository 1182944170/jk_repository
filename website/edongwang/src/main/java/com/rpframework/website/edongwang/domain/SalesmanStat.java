package com.rpframework.website.edongwang.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;
import com.rpframework.core.vo.BaseUserVO;

/**
 * 
 * title: RecommendStat.java 
 * 统计评价
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月7日 下午5:31:06
 *@SuppressWarnings("serial")
*/
@TableMapperAnnotation(tableName = "salesman_stat", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class SalesmanStat extends Domain {

	//序号、接单人姓名、联系方式、所在楼盘（表结构）、接单次数、成交套数、成交总面积、成交总金额
	/**描述*/  
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	Integer userId;
	@FieldMapperAnnotation
	Integer houseId;
	@FieldMapperAnnotation
	Integer grabCount;
	@FieldMapperAnnotation
	Integer dealCount;
	@FieldMapperAnnotation
	Double totalSurface;
	@FieldMapperAnnotation
	Double totalDealPrice;
	
	BaseUserVO user;
	House house;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getHouseId() {
		return houseId;
	}
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	public Integer getGrabCount() {
		return grabCount;
	}
	public void setGrabCount(Integer grabCount) {
		this.grabCount = grabCount;
	}
	public Integer getDealCount() {
		return dealCount;
	}
	public void setDealCount(Integer dealCount) {
		this.dealCount = dealCount;
	}
	public Double getTotalSurface() {
		return totalSurface;
	}
	public void setTotalSurface(Double totalSurface) {
		this.totalSurface = totalSurface;
	}
	public Double getTotalDealPrice() {
		return totalDealPrice;
	}
	public void setTotalDealPrice(Double totalDealPrice) {
		this.totalDealPrice = totalDealPrice;
	}
	public BaseUserVO getUser() {
		return user;
	}
	public void setUser(BaseUserVO user) {
		this.user = user;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
}