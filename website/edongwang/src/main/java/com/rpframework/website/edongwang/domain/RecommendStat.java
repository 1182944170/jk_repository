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
@TableMapperAnnotation(tableName = "recommend_stat", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "userId")
public class RecommendStat extends Domain {

	//推荐人姓名、联系方式、推荐次数、A，B，C，D，无效次数、有效次数、成交套数、成交总面积、成交总金额
	/**描述*/  
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer userId; //楼盘名称
	@FieldMapperAnnotation
	Integer totalCount;
	@FieldMapperAnnotation
	Integer aCount;
	@FieldMapperAnnotation
	Integer bCount;
	@FieldMapperAnnotation
	Integer cCount;
	@FieldMapperAnnotation
	Integer dCount;
	@FieldMapperAnnotation
	Integer invalidCount;
	@FieldMapperAnnotation
	Integer effectiveCount;
	@FieldMapperAnnotation
	Integer dealHouseCount;
	@FieldMapperAnnotation
	Double totalSurface;
	@FieldMapperAnnotation
	Double totalDealPrice;
	
	BaseUserVO user;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getaCount() {
		return aCount;
	}

	public void setaCount(Integer aCount) {
		this.aCount = aCount;
	}

	public Integer getbCount() {
		return bCount;
	}

	public void setbCount(Integer bCount) {
		this.bCount = bCount;
	}

	public Integer getcCount() {
		return cCount;
	}

	public void setcCount(Integer cCount) {
		this.cCount = cCount;
	}

	public Integer getdCount() {
		return dCount;
	}

	public void setdCount(Integer dCount) {
		this.dCount = dCount;
	}

	public Integer getInvalidCount() {
		return invalidCount;
	}

	public void setInvalidCount(Integer invalidCount) {
		this.invalidCount = invalidCount;
	}

	public Integer getEffectiveCount() {
		return effectiveCount;
	}

	public void setEffectiveCount(Integer effectiveCount) {
		this.effectiveCount = effectiveCount;
	}

	public Integer getDealHouseCount() {
		return dealHouseCount;
	}

	public void setDealHouseCount(Integer dealHouseCount) {
		this.dealHouseCount = dealHouseCount;
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
}
