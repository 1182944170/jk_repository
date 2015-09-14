package com.rpframework.module.adminbase.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.adminbase.dao.IAdminMenuDao;
import com.rpframework.module.adminbase.domain.AdminMenu;

@Service("adminMenuService")
public class AdminMenuService extends BaseService {

	@Resource public IAdminMenuDao adminMenuDao;
	
	public List<AdminMenu> getMenuListByParentId(Integer parentId) {
		return adminMenuDao.getMenuListByParentId(parentId);
	}
}