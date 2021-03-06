package com.rpframework.module.user.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;
import com.rpframework.core.vo.BaseUserVO;

@TableMapperAnnotation(tableName = "u_score_log", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class UserScoreLog extends Domain {

	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	Integer userId;
	@FieldMapperAnnotation
	Integer score;
	@FieldMapperAnnotation
	Integer type;
	@FieldMapperAnnotation
	String remark;
	@FieldMapperAnnotation
	String ext;
	@FieldMapperAnnotation
	Long recordCreateTime;
	
	BaseUserVO user;
	
	public BaseUserVO getUser() {
		return user;
	}
	public void setUser(BaseUserVO user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
}
