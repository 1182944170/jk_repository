package com.rpframework.module.common.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "city", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "code")
public class City extends Domain {

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
	Double population;
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
	String countryCode;
	
	public City(){}
	public City(String code,
			String name,
			String level,
			Double area,
			Double population,
			String station,
			Integer postalCode,
			Integer areaCode,
			Double longitude,
			Double dimensions,
			
			String countryCode
			){
		this.code = code;
		this.name = name;
		this.level = level;
		this.area = area;
		this.population = population;
		this.station = station;
		this.postalCode = postalCode;
		this.areaCode = areaCode;
		this.longitude = longitude;
		this.dimensions = dimensions;
		this.countryCode = countryCode;
	}
	public String getCode() {
		return code;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
	public Double getPopulation() {
		return population;
	}
	public void setPopulation(Double population) {
		this.population = population;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
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
