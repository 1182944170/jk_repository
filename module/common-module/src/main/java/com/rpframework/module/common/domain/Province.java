package com.rpframework.module.common.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "province", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "code")
public class Province extends Domain {

	/**描述*/  
	
	private static final long serialVersionUID = 1L;
	
	/**
	  * 代码	ISO代码	国标代码	省份名称	简称	面积(平方公里)	人口(万)	省会城市	省政府详细地址	卫星地图	普通地图
	  */
	@FieldMapperAnnotation
	String code;
	@FieldMapperAnnotation
	String isoCode;
	@FieldMapperAnnotation
	String globalCode;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation
	String thumbName;
	@FieldMapperAnnotation
	Double area;
	@FieldMapperAnnotation
	Double population;
	@FieldMapperAnnotation
	String capitalCity;
	@FieldMapperAnnotation
	String provincialAddress;
	public Province(){}
	
	public Province(String code,
			String isoCode,
			String globalCode,
			String name,
			String thumbName,
			Double area,
			Double population,
			String capitalCity,
			String provincialAddress){
		this.code = code;
		this.isoCode = isoCode;
		this.globalCode = globalCode;
		this.name = name;
		this.thumbName = thumbName;
		this.area = area;
		this.population = population;
		this.capitalCity = capitalCity;
		this.population = population;
		this.provincialAddress = provincialAddress;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getGlobalCode() {
		return globalCode;
	}

	public void setGlobalCode(String globalCode) {
		this.globalCode = globalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbName() {
		return thumbName;
	}

	public void setThumbName(String thumbName) {
		this.thumbName = thumbName;
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

	public String getCapitalCity() {
		return capitalCity;
	}

	public void setCapitalCity(String capitalCity) {
		this.capitalCity = capitalCity;
	}

	public String getProvincialAddress() {
		return provincialAddress;
	}

	public void setProvincialAddress(String provincialAddress) {
		this.provincialAddress = provincialAddress;
	}
}
