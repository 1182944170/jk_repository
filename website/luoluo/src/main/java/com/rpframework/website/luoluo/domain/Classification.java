	package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="classification" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")

public class Classification {
	@FieldMapperAnnotation
	private Integer id;
	@FieldMapperAnnotation
	private String claName;
	@FieldMapperAnnotation
	private String claImg;
	@FieldMapperAnnotation
	private Integer seqn;
	@FieldMapperAnnotation
	private String bigImg;
	@FieldMapperAnnotation
	private int procedures;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getClaName() {
		return claName;
	}
	public void setClaName(String claName) {
		this.claName = claName;
	}
	public String getClaImg() {
		return claImg;
	}
	public void setClaImg(String claImg) {
		this.claImg = claImg;
	}
	public Integer getSeqn() {
		return seqn;
	}
	public void setSeqn(Integer seqn) {
		this.seqn = seqn;
	}
	public String getBigImg() {
		return bigImg;
	}
	public void setBigImg(String bigImg) {
		this.bigImg = bigImg;
	}
	
	public int getProcedures() {
		return procedures;
	}
	public void setProcedures(int procedures) {
		this.procedures = procedures;
	}
	@Override
	public String toString() {
		return "Classification [id=" + id + ", claName=" + claName
				+ ", claImg=" + claImg + ", seqn=" + seqn + ", bigImg="
				+ bigImg + "]";
	}
	
	
}
