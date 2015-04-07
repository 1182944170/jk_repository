package com.rpframework.module.adminbase.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.adminbase.dao.IAdminUserDao;
import com.rpframework.module.adminbase.domain.AdminUser;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.Pager;

@Service("adminUserService")
public class AdminUserService extends BaseService {

	@Resource public IAdminUserDao adminUserDao;
	
	public AdminUser findAdminUserByName(String userName) {
		return adminUserDao.findAdminUserByName(userName);
	}
	
	public Pager<AdminUser> getAdminUserPager(Pager<AdminUser> pager) {
		long startTime = System.currentTimeMillis();
		List<AdminUser> itemList = adminUserDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public boolean canDeleteThisRole(Integer adminRoleId) {
		List<AdminUser> adminUserList = adminUserDao.findAdminUserByRoleId(adminRoleId);
		return CollectionUtils.isEmpty(adminUserList);
	}
}