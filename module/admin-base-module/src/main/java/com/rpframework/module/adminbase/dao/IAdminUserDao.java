package com.rpframework.module.adminbase.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.adminbase.domain.AdminUser;

/**
 * 
 * title: IAdminUserDao.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年3月26日 下午9:32:16
 */
public interface IAdminUserDao extends IDao{

	AdminUser findAdminUserByName(String userName);
	
	List<AdminUser> findAdminUserByRoleId(Integer roleId);
	List<AdminUser> doPager(Map<?, ?> paramMap);
}
