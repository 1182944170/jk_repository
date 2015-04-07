package com.rpframework.module.adminbase.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.adminbase.dao.IRoleAdminAuthResDao;
import com.rpframework.module.adminbase.domain.AdminAuthRes;
import com.rpframework.module.adminbase.domain.AdminRole;
import com.rpframework.module.adminbase.domain.RoleAdminAuthRes;
import com.rpframework.utils.CollectionUtils;

@Service("roleAdminAuthResService")
public class RoleAdminAuthResService extends BaseService {

	@Resource public IRoleAdminAuthResDao roleAdminAuthResDao;
	
	public List<RoleAdminAuthRes> getRoleAdminAuthResListByAdminAuthResId(Integer adminAuthResId) {
		return roleAdminAuthResDao.getRoleAdminAuthResListByAdminAuthResId(adminAuthResId);
	}
	
	public List<RoleAdminAuthRes> getRoleAdminAuthResList(Integer adminRoleId) {
		return roleAdminAuthResDao.getRoleAdminAuthResList(adminRoleId);
	}
	
	public List<RoleAdminAuthRes> findAllRoleAdminAuthRes() {
		return roleAdminAuthResDao.findAllRoleAdminAuthRes();
	}
	
	public boolean whenDeleteAdminRole(Integer adminRoleId) {
		List<RoleAdminAuthRes> roleAdminAuthResList = this.getRoleAdminAuthResList(adminRoleId);
		if(CollectionUtils.isEmpty(roleAdminAuthResList)) {
			return true;
		}
		
		boolean ret = true;
		for (RoleAdminAuthRes roleAdminAuthRes : roleAdminAuthResList) {
			boolean flag = roleAdminAuthResDao.delete(roleAdminAuthRes.getId());
			if(!flag) {
				logger.warn("whenDeleteAdminRole 时遇到删除失败的数据: roleAdminAuthResId:" + roleAdminAuthRes.getId());
				ret = false;
			}
		}
		return ret;
	}

	public boolean resetRoleAdminAuthRes(AdminRole adminRole, List<Integer> roleAdminAuthList) {
		roleAdminAuthResDao.deleteByRoleId(adminRole.getId());
		if(CollectionUtils.isNotEmpty(roleAdminAuthList)) {
			RoleAdminAuthRes roleAdminAuthRes;
			AdminAuthRes adminAuthRes;
			for (Integer adminAuthResId : roleAdminAuthList) {
				roleAdminAuthRes = new RoleAdminAuthRes();
				adminAuthRes = new AdminAuthRes();
				adminAuthRes.setId(adminAuthResId);
				
				roleAdminAuthRes.setAdminAuthRes(adminAuthRes);
				roleAdminAuthRes.setAdminRole(adminRole);
				
				this.roleAdminAuthResDao.insert(roleAdminAuthRes);
			}
		}
		
		return true;
	}
}