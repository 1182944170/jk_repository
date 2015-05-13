package com.rpframework.website.edongwang.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * loupan
 * @author hzyuanyong@126.com
 */
@TableMapperAnnotation(tableName = "e_house_recommend_progress", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class HouseRecommendProgress extends Domain {

	/**
	 * CREATE TABLE `e_house_recommend_progress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `houseRecommendId` int(11) NOT NULL,
  `type` tinyint(1) NOT NULL COMMENT '1-审核 2-到访 3-成交 4-佣金',
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `ext` varchar(256) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 */
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	Integer houseRecommendId;
	@FieldMapperAnnotation
	Integer type;
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	String ext;
	@FieldMapperAnnotation
	Long recordCreateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getHouseRecommendId() {
		return houseRecommendId;
	}
	public void setHouseRecommendId(Integer houseRecommendId) {
		this.houseRecommendId = houseRecommendId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public Long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
}