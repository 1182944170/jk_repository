package com.rpframework.module.adminbase.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.adminbase.dao.IAdminRoleDao;
import com.rpframework.module.adminbase.domain.AdminRole;

@Service("adminRoleService")
public class AdminRoleService extends BaseService {
	@Resource AdminUserService adminUserService;
	@Resource RoleAdminAuthResService roleAdminAuthResService;
	@Resource public IAdminRoleDao adminRoleDao;
	
	public List<AdminRole> findAll() {
		return adminRoleDao.findAll();
	}
	
	public boolean checkCanDelete(Integer adminRoleId) {
		return adminUserService.canDeleteThisRole(adminRoleId);
	}
	
	public boolean deleteAdminRole(Integer adminRoleId) {
		boolean ret = true;
		if(checkCanDelete(adminRoleId)) {
			ret = roleAdminAuthResService.whenDeleteAdminRole(adminRoleId);
		}
		
		if(ret) {
			adminRoleDao.delete(adminRoleId);
		}
		return ret;
	}
}