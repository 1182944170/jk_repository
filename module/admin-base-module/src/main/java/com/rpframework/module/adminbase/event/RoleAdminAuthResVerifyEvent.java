package com.rpframework.module.adminbase.event;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.module.adminbase.domain.AdminAuthRes;
import com.rpframework.module.adminbase.domain.AdminRole;
import com.rpframework.module.adminbase.domain.AdminUser;
import com.rpframework.module.adminbase.utils.cache.RoleAdminAuthResCache;
import com.rpframework.utils.CollectionUtils;

/**  
 * title: AdminAuthResVerifyEvent.java 
 * 对后台的请求路径
 * 判断这个用户是否有权限
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2013-7-6 上午12:43:35 
 */  

public class RoleAdminAuthResVerifyEvent {
	AntPathMatcher antPathMatcher = null;
	
	public RoleAdminAuthResVerifyEvent(){
		antPathMatcher = new AntPathMatcher();
	}
	final Logger logger = LoggerFactory.getLogger(getClass());
	public boolean checkLimit(AdminUser au, String uri) {
		//验证是否有权限操作逻辑
		String globalUser = "globalUser"; //FIXME: 稍后采用配置
		if(StringUtils.equals(au.getUserName(), globalUser)) {
			//网站全局的直接pass;
			return true;
		}
		
		if(au.getViewOnlyAdmin() == 1) { //只读管理员
			if(isModify(uri)) { //修改或新增或删除操作不能进行
				return false;
			}
		}
		
		return checkRoleLimit(au.getAdminRole(), uri);
	}

	public boolean checkRoleLimit(AdminRole adminRole, String uri) {
		AdminRole role = adminRole;
		//看看是否具备所有权限
		if(role != null && role.getIsSuper() == 1) {
			return true;
		}
		
		if(! isExits(uri)) { //并且uri不存在配置中的话 则通过
			return true;
		}
		
		//到这里说明 uri 在AdminAuthRes有在配置里
		if(role == null) {
			return false;
		}
		RoleAdminAuthResCache cache = CacheUtils.getIntance().get2(RoleAdminAuthResCache.k);
		if(isExits(uri, cache.getByRoleId(adminRole.getId()))) { //如果uri包括在这角色的资源映射里面的话
			return true;
		}
		return false;
	}
	private boolean isModify(String uri) {
		return StringUtils.indexOf(uri, "save") > -1
			|| StringUtils.indexOf(uri, "update") > -1
			||StringUtils.indexOf(uri, "delete") > -1
			||StringUtils.indexOf(uri, "del") > -1
			||StringUtils.indexOf(uri, "remove") > -1;
	}

	public boolean isExits(String uri) {
		RoleAdminAuthResCache cache = CacheUtils.getIntance().get2(RoleAdminAuthResCache.k);
		if(isExits(uri, cache.getAllRes())) {
			return true;
		}
		
		return false;
	}

	private boolean isExits(String uri, List<AdminAuthRes> l) {
		if(CollectionUtils.isEmpty(l)) {
			return false;
		}
		
		
		for (AdminAuthRes ar : l) {
			if(StringUtils.indexOf(ar.getPath(), uri) >= 0) {
				return true;
			} else {
				if(StringUtils.indexOf(ar.getPath(), ".") > 0) {
					String[] arrString = ar.getPath().split(".");
					for (String subPath : arrString) {
						if(antPathMatcher.match(subPath, uri)) {
							return true;
						}
					}
				} else {
					if(antPathMatcher.match(ar.getPath(), uri)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
