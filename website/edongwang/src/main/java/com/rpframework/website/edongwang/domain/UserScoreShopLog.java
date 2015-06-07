package com.rpframework.website.edongwang.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;
import com.rpframework.core.vo.BaseUserVO;

/**
 * 
 * title: UserScoreShopLog.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月7日 下午5:32:33
 */
@TableMapperAnnotation(tableName = "u_score_shop_log", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class UserScoreShopLog extends Domain {
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	Integer userId;
	@FieldMapperAnnotation
	Integer scoreShopId;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation
	String concact;
	@FieldMapperAnnotation
	String areaCode;
	@FieldMapperAnnotation
	String address;
	@FieldMapperAnnotation
	Integer sendShopState;
	@FieldMapperAnnotation
	Long recordCreateTime;
	@FieldMapperAnnotation
	Long recordModifyTime;
	
	ScoreShop scoreShop;
	BaseUserVO user;
	
	public Long getRecordModifyTime() {
		return recordModifyTime;
	}
	public void setRecordModifyTime(Long recordModifyTime) {
		this.recordModifyTime = recordModifyTime;
	}
	public BaseUserVO getUser() {
		return user;
	}
	public void setUser(BaseUserVO user) {
		this.user = user;
	}
	public ScoreShop getScoreShop() {
		return scoreShop;
	}
	public void setScoreShop(ScoreShop scoreShop) {
		this.scoreShop = scoreShop;
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
	public Integer getScoreShopId() {
		return scoreShopId;
	}
	public void setScoreShopId(Integer scoreShopId) {
		this.scoreShopId = scoreShopId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConcact() {
		return concact;
	}
	public void setConcact(String concact) {
		this.concact = concact;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getSendShopState() {
		return sendShopState;
	}
	public void setSendShopState(Integer sendShopState) {
		this.sendShopState = sendShopState;
	}
	public Long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
}