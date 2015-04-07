package com.rpframework.module.adminbase.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.adminbase.dao.IAdminAuthResDao;
import com.rpframework.module.adminbase.domain.AdminAuthRes;
import com.rpframework.module.adminbase.domain.RoleAdminAuthRes;
import com.rpframework.utils.CollectionUtils;

@Service("adminAuthResService")
public class AdminAuthResService extends BaseService {

	@Resource public IAdminAuthResDao adminAuthResDao;
	
	@Resource public RoleAdminAuthResService roleAdminAuthResService;
	public boolean canDeleteAdminAuthRes(Integer adminAuthResId) {
		AdminAuthRes adminAuthRes = adminAuthResDao.select(adminAuthResId);
		if(adminAuthRes == null || CollectionUtils.isNotEmpty(adminAuthRes.getChildren())) {
			return false;
		}
		return true;
	}
	public boolean deleteAdminAuthRes(Integer adminAuthResId) {
		if(!canDeleteAdminAuthRes(adminAuthResId)) {
			return false;
		}
		List<RoleAdminAuthRes> list = roleAdminAuthResService.getRoleAdminAuthResListByAdminAuthResId(adminAuthResId);
		boolean ret = true;
		if(CollectionUtils.isNotEmpty(list)) {
			for (RoleAdminAuthRes roleAdminAuthRes : list) {
				boolean flag = roleAdminAuthResService.roleAdminAuthResDao.delete(roleAdminAuthRes);
				if(!flag) {
					logger.warn("删除RoleAdminAuthRes时不成功!");
					ret = flag;
				}
			}
		}
		
		if(ret) {
			adminAuthResDao.delete(adminAuthResId);
		}
		
		return ret;
	}
	
}