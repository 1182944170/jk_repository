package com.rpframework.module.adminbase.domain;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * 
 * title: AdminAuthRes.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年3月26日 下午4:52:12
 */
@TableMapperAnnotation(tableName = "role_admin_auth_res", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id") 
public class RoleAdminAuthRes extends Domain {

	/**描述*/  
	
	private static final long serialVersionUID = 1L;
	
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation(dbFieldName = "adminRoleId", fieldType=FieldType.Object)
	AdminRole adminRole;
	
	@FieldMapperAnnotation(dbFieldName = "adminAuthResId", fieldType=FieldType.Object)
	AdminAuthRes adminAuthRes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminRole getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(AdminRole adminRole) {
		this.adminRole = adminRole;
	}

	public AdminAuthRes getAdminAuthRes() {
		return adminAuthRes;
	}

	public void setAdminAuthRes(AdminAuthRes adminAuthRes) {
		this.adminAuthRes = adminAuthRes;
	}
}
