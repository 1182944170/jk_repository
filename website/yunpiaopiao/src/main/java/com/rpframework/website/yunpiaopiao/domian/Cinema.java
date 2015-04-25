package com.rpframework.website.yunpiaopiao.domian;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "cinema", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Cinema extends Domain {
	/**描述*/  
	private static final long serialVersionUID = 1L;
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation
	String address;
	@FieldMapperAnnotation
	String contact;
	@FieldMapperAnnotation
	String feature;//特色
	@FieldMapperAnnotation
	Double mark;//分数
	@FieldMapperAnnotation
	String icon;//图标地址
	@FieldMapperAnnotation
	String addressImage;//地址的地图
	@FieldMapperAnnotation
	String content; //详细描述
	@FieldMapperAnnotation
	Integer isRecommend; //是否推荐
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public Double getMark() {
		return mark;
	}
	public void setMark(Double mark) {
		this.mark = mark;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAddressImage() {
		return addressImage;
	}
	public void setAddressImage(String addressImage) {
		this.addressImage = addressImage;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
}