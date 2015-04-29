package com.rpframework.module.common.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "county", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "code")
public class County extends Domain {

	/**描述*/  
	private static final long serialVersionUID = 1L;

	/**
	 * 代码	名称	级别	面积	人口	驻地	邮政编码	区号	经度	纬度	卫星地图	普通地图
	 */
	@FieldMapperAnnotation
	String code;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation
	String level;
	@FieldMapperAnnotation
	Double area;
	@FieldMapperAnnotation
	String station;
	@FieldMapperAnnotation
	Integer postalCode;
	@FieldMapperAnnotation
	Integer areaCode;
	@FieldMapperAnnotation
	Double longitude;
	@FieldMapperAnnotation
	Double dimensions;
	@FieldMapperAnnotation
	String provinceCode;
	public County(){}
	public County(String code,
			String name,
			String level,
			Double area,
			String station,
			Integer postalCode,
			Integer areaCode,
			Double longitude,
			Double dimensions,
			
			String provinceCode
			){
		this.code = code;
		this.name = name;
		this.level = level;
		this.area = area;
		this.station = station;
		this.postalCode = postalCode;
		this.areaCode = areaCode;
		this.longitude = longitude;
		this.dimensions = dimensions;
		
		this.provinceCode = provinceCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public Integer getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}
	public Integer getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(Integer areaCode) {
		this.areaCode = areaCode;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getDimensions() {
		return dimensions;
	}
	public void setDimensions(Double dimensions) {
		this.dimensions = dimensions;
	}
}
