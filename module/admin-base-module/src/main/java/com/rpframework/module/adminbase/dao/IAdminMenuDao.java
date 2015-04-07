package com.rpframework.module.adminbase.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.module.adminbase.domain.AdminMenu;

/**
 * 
 * title: IAdminMenuDao.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年3月26日 下午9:32:16
 */
public interface IAdminMenuDao extends IDao {
	List<AdminMenu> getMenuListByParentId(Integer parentId);
}
