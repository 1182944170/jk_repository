package com.rpframework.module.adminbase.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.module.adminbase.domain.AdminRole;

/**
 * 
 * title: IAdminRoleDao.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年3月26日 下午9:32:16
 */
public interface IAdminRoleDao extends IDao {

	List<AdminRole> findAll();
	
	AdminRole findByName(String name);
}
