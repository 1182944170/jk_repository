package com.rpframework.website.xtexam.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "xt_order", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class XTOrder extends Domain {

	/**描述*/  
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	String orderNo;
	@FieldMapperAnnotation
	Integer rechargeChannel;//充值渠道
	@FieldMapperAnnotation
	String orderName;
	@FieldMapperAnnotation
	String remark;
	@FieldMapperAnnotation
	XTUser user;
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	Double costMoney;
	@FieldMapperAnnotation
	Integer recordCreateTime;
	@FieldMapperAnnotation
	Integer lastModifyTime;
	
	Integer rechargeExamSubChapterId;//充值的试卷ID
}