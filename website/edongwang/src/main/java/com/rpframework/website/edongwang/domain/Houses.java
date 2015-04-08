package com.rpframework.website.edongwang.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * loupan
 * @author hzyuanyong@126.com
 *
 */
@TableMapperAnnotation(tableName = "e_house", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Houses extends Domain{

	
	private static final long serialVersionUID = 1L;

	

}
