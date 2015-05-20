package com.rpframework.module.common.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "sms", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class SMS extends Domain {
	public static final int SMS_STATE_UNVERIFY = 1;
	public static final int SMS_STATE_VERIFYED = 2;
	/*
	 * 
	 * CREATE TABLE `sms` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `channelType` tinyint(1) NOT NULL DEFAULT '1',
  `channelSend` tinyint(1) NOT NULL,
  `phone` varchar(32) NOT NULL DEFAULT '',
  `verifyCode` varchar(32) NOT NULL DEFAULT '',
  `content` varchar(128) NOT NULL DEFAULT '',
  `expirationTime` int(11) NOT NULL COMMENT '0 -  不过期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 * */
	/**描述*/  
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	String id;
	@FieldMapperAnnotation
	Integer channelType;
	@FieldMapperAnnotation
	Integer channelSend;
	@FieldMapperAnnotation
	String phone;
	@FieldMapperAnnotation
	String verifyCode;
	@FieldMapperAnnotation
	String content;
	@FieldMapperAnnotation
	String responseText;
	@FieldMapperAnnotation
	Long expirationTime;
	@FieldMapperAnnotation
	Long recordCreateTime;
	@FieldMapperAnnotation
	Long verifyTime;
	
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	public Long getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(Long verifyTime) {
		this.verifyTime = verifyTime;
	}
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	Integer sendState;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getChannelType() {
		return channelType;
	}
	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}
	public Integer getChannelSend() {
		return channelSend;
	}
	public void setChannelSend(Integer channelSend) {
		this.channelSend = channelSend;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getContent() {
		return content;
	}
	public Long getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(Long expirationTime) {
		this.expirationTime = expirationTime;
	}
	public Long getRecordCreateTime() {
		return recordCreateTime;
	}
	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getSendState() {
		return sendState;
	}
	public void setSendState(Integer sendState) {
		this.sendState = sendState;
	}
}
