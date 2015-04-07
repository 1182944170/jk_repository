package com.rpframework.module.adminbase.utils.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rpframework.core.utils.SpringUtils;
import com.rpframework.core.utils.cache.CacheObj;
import com.rpframework.module.adminbase.domain.AdminAuthRes;
import com.rpframework.module.adminbase.domain.RoleAdminAuthRes;
import com.rpframework.module.adminbase.service.AdminAuthResService;
import com.rpframework.module.adminbase.service.RoleAdminAuthResService;
import com.rpframework.utils.CollectionUtils;

/**  
 * title: FlowerTotalCache.java 
 * 统计花型的总数
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2013-6-5 上午11:18:50 
 */  

public class RoleAdminAuthResCache extends CacheObj {
	public static final String k = "_role_admin_auth_res_cache_";
	List<AdminAuthRes> all = new ArrayList<AdminAuthRes>();
	public RoleAdminAuthResCache() {
		super(30 * 60L, k);
	}
	
	public List<AdminAuthRes> getAllRes() {
		return all;
	}	
	public List<AdminAuthRes> getByRoleId(long roleId) {
		Map<Long, List<AdminAuthRes>> m = getAdminRes();
		if(CollectionUtils.isEmpty(m))
			return null;
		return m.get(roleId);
	}
	
	public Map<Long, List<AdminAuthRes>> getAdminRes() {
		Map<Long, List<AdminAuthRes>> m = super.getV();
		return m;
	}
	@Override
	public Object load() {
		Map<Integer, List<AdminAuthRes>> m = new HashMap<Integer, List<AdminAuthRes>>();
		RoleAdminAuthResService roleAdminAuthResService = SpringUtils.getBean("roleAdminAuthResService");
		AdminAuthResService adminAuthResService = SpringUtils.getBean("adminAuthResService");
		List<RoleAdminAuthRes> list = roleAdminAuthResService.findAllRoleAdminAuthRes();
		if(CollectionUtils.isEmpty(list)) {
			logger.warn("角色与资源映射为空.");
		}
		
		for (RoleAdminAuthRes roleAdminAuthRes : list) {
			List<AdminAuthRes> _children = null;
			if(m.containsKey(roleAdminAuthRes.getAdminRole().getId())) {
				_children = m.get(roleAdminAuthRes.getAdminRole().getId());
			} else {
				_children = new ArrayList<AdminAuthRes>();
				m.put(roleAdminAuthRes.getAdminRole().getId(), _children);
			}
			
			_children.add(roleAdminAuthRes.getAdminAuthRes());
		}
		
		/***
		 *
		 */
		List<AdminAuthRes> findByParentId = adminAuthResService.adminAuthResDao.getAdminAuthResByParentId(0);
		if(CollectionUtils.isNotEmpty(findByParentId)) {
			all.clear();
			
			for (AdminAuthRes adminAuthRes : findByParentId) {
				
				all.add(adminAuthRes);
				
				if(CollectionUtils.isNotEmpty(adminAuthRes.getChildren())) {
					for (AdminAuthRes c : adminAuthRes.getChildren()) {
						all.add(c);
					}
				}
			}
		}
		return m;
	}

}
