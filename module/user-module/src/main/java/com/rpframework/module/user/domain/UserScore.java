package com.rpframework.module.user.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;
import com.rpframework.core.vo.BaseUserVO;

@TableMapperAnnotation(tableName = "u_score", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "userId")
public class UserScore extends Domain {

	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer userId;
	@FieldMapperAnnotation
	Integer score;
	@FieldMapperAnnotation
	Integer usedScore;
	@FieldMapperAnnotation
	Long recordCreateTime;
	@FieldMapperAnnotation
	Long recordModifyTime;
	
	BaseUserVO user;
	
	public BaseUserVO getUser() {
		return user;
	}
	public void setUser(BaseUserVO user) {
		this.user = user;
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
	public Long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
	public Long getRecordModifyTime() {
		return recordModifyTime;
	}
	public void setRecordModifyTime(Long recordModifyTime) {
		this.recordModifyTime = recordModifyTime;
	}
	public Integer getUsedScore() {
		return usedScore;
	}
	public void setUsedScore(Integer usedScore) {
		this.usedScore = usedScore;
	}
}
