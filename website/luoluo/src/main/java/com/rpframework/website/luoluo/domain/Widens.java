package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="widens" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Widens {
	@FieldMapperAnnotation
	private Integer id;
	@FieldMapperAnnotation
	private String syTitle ; //系统标题
	@FieldMapperAnnotation
	private String sycontent ; //系统内容
	@FieldMapperAnnotation
	private String syimages ;  //系统图片
	@FieldMapperAnnotation
	private Long sytime;	//系统时间
	@FieldMapperAnnotation
	private Integer type;	//状态
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSycontent() {
		return sycontent;
	}
	public void setSycontent(String sycontent) {
		this.sycontent = sycontent;
	}
	public String getSyimages() {
		return syimages;
	}
	public void setSyimages(String syimages) {
		this.syimages = syimages;
	}

	
	public Long getSytime() {
		return sytime;
	}
	public void setSytime(Long sytime) {
		this.sytime = sytime;
	}
	public String getSyTitle() {
		return syTitle;
	}
	public void setSyTitle(String syTitle) {
		this.syTitle = syTitle;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Widens [id=" + id + ", syTitle=" + syTitle + ", sycontent="
				+ sycontent + ", syimages=" + syimages + ", sytime=" + sytime
				+ ", type=" + type + "]";
	}
	
	
	
}
