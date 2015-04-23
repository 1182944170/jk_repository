package com.rpframework.website.xtexam.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "xt_order", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class XTOrder extends Domain {

	/**描述*/  
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	String orderNo;
	@FieldMapperAnnotation(dbFieldName="userId", fieldType=FieldType.Object)
	XTUser user;
	@FieldMapperAnnotation
	Integer rechargeChannel;//充值渠道
	@FieldMapperAnnotation
	String orderName;
	@FieldMapperAnnotation
	String remark;
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	Double costMoney;
	@FieldMapperAnnotation
	Integer recordCreateTime;
	@FieldMapperAnnotation
	Integer lastModifyTime;
	
	Integer rechargeExamSubchapterId;//充值的试卷ID

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public XTUser getUser() {
		return user;
	}

	public void setUser(XTUser user) {
		this.user = user;
	}

	public Integer getRechargeChannel() {
		return rechargeChannel;
	}

	public void setRechargeChannel(Integer rechargeChannel) {
		this.rechargeChannel = rechargeChannel;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Double getCostMoney() {
		return costMoney;
	}

	public void setCostMoney(Double costMoney) {
		this.costMoney = costMoney;
	}

	public Integer getRecordCreateTime() {
		return recordCreateTime;
	}

	public void setRecordCreateTime(Integer recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}

	public Integer getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Integer lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Integer getRechargeExamSubchapterId() {
		return rechargeExamSubchapterId;
	}

	public void setRechargeExamSubchapterId(Integer rechargeExamSubchapterId) {
		this.rechargeExamSubchapterId = rechargeExamSubchapterId;
	}
}