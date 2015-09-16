package com.rpframework.website.luoluo.domain;


import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;


@TableMapperAnnotation(tableName = "clafic_goods", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class ClaGoods extends Domain{

	/**描述*/  
	
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation(dbFieldName="claid", fieldType=FieldType.Object)
	Classification classification;
	@FieldMapperAnnotation
	Integer goodId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Classification getClassification() {
		return classification;
	}
	public void setClassification(Classification classification) {
		this.classification = classification;
	}
	public Integer getGoodId() {
		return goodId;
	}
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
