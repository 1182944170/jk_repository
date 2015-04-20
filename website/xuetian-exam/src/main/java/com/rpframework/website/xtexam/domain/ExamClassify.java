package com.rpframework.website.xtexam.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "xt_exam_classify", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class ExamClassify extends Domain {
	/**描述*/  
	
	private static final long serialVersionUID = 4054166887587894294L;
	/**
	 * CREATE TABLE `xt_exam_classify` (
	  `id` int(11) NOT NULL AUTO_INCREMENT,
	  `parentId` int(11) NOT NULL,
	  `name` varchar(128) NOT NULL DEFAULT '',
	  `thumb` varchar(64) NOT NULL DEFAULT '',
	  `levelNum` tinyint(1) NOT NULL,
	  `ext` varchar(256) NOT NULL DEFAULT '',
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 */
	
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation(dbFieldName = "parentId", fieldType=FieldType.Object)
	ExamClassify parent;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation
	String thumb;
	@FieldMapperAnnotation
	Integer levelNum;
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	String ext;
	
	ExamClassifyLevelEnum levelEnum;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ExamClassify getParent() {
		return parent;
	}

	public void setParent(ExamClassify parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public Integer getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(Integer levelNum) {
		this.setLevelEnum(ExamClassifyLevelEnum.getLevelEnum(levelNum));
		this.levelNum = levelNum;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public ExamClassifyLevelEnum getLevelEnum() {
		return levelEnum;
	}

	public void setLevelEnum(ExamClassifyLevelEnum levelEnum) {
		this.levelEnum = levelEnum;
		this.levelNum = levelEnum.levelNum;
	}
}