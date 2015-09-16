package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;
@TableMapperAnnotation(tableName="personalitylabel" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Personalabel {
	@FieldMapperAnnotation
	private Integer id;  //id
	@FieldMapperAnnotation
	private String label;  //标签
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return "persona_label [id=" + id + ", label=" + label + "]";
	}
}
