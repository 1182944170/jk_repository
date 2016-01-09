package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="mypersonalitylabel" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")

public class Mypersonalitylabel {
	@FieldMapperAnnotation
	private Integer id;  //id
	@FieldMapperAnnotation
	private Integer userid;  //id
	@FieldMapperAnnotation
	private String mylabela;  //id
	@FieldMapperAnnotation
	private String mylabelb;  //id
	@FieldMapperAnnotation
	private String mylabelc;  //id
	@FieldMapperAnnotation
	private String mylabeld;  //id
	@FieldMapperAnnotation
	private String mylabele;  //id
	@FieldMapperAnnotation
	private String mylabelf;  //id
	@FieldMapperAnnotation
	private String mylabelg;  //id
	@FieldMapperAnnotation
	private String mylabelh;  //id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getMylabela() {
		return mylabela;
	}
	public void setMylabela(String mylabela) {
		this.mylabela = mylabela;
	}
	public String getMylabelb() {
		return mylabelb;
	}
	public void setMylabelb(String mylabelb) {
		this.mylabelb = mylabelb;
	}
	public String getMylabelc() {
		return mylabelc;
	}
	public void setMylabelc(String mylabelc) {
		this.mylabelc = mylabelc;
	}
	public String getMylabeld() {
		return mylabeld;
	}
	public void setMylabeld(String mylabeld) {
		this.mylabeld = mylabeld;
	}
	public String getMylabele() {
		return mylabele;
	}
	public void setMylabele(String mylabele) {
		this.mylabele = mylabele;
	}
	public String getMylabelf() {
		return mylabelf;
	}
	public void setMylabelf(String mylabelf) {
		this.mylabelf = mylabelf;
	}
	public String getMylabelg() {
		return mylabelg;
	}
	public void setMylabelg(String mylabelg) {
		this.mylabelg = mylabelg;
	}
	public String getMylabelh() {
		return mylabelh;
	}
	public void setMylabelh(String mylabelh) {
		this.mylabelh = mylabelh;
	}


	
	
}
