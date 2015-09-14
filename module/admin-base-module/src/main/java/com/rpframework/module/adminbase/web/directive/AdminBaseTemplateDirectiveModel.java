package com.rpframework.module.adminbase.web.directive;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.rpframework.core.freemarker.directive.BaseTemplateDirectiveModel;
import com.rpframework.core.freemarker.directive.DirectiveUtils;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.module.adminbase.act.AdminBaseAct;
import com.rpframework.module.adminbase.domain.AdminAuthRes;
import com.rpframework.module.adminbase.domain.AdminMenu;
import com.rpframework.module.adminbase.domain.AdminRole;
import com.rpframework.module.adminbase.domain.AdminUser;
import com.rpframework.module.adminbase.event.RoleAdminAuthResVerifyEvent;
import com.rpframework.module.adminbase.service.AdminAuthResService;
import com.rpframework.module.adminbase.service.AdminMenuService;
import com.rpframework.module.adminbase.service.AdminRoleService;
import com.rpframework.module.adminbase.utils.cache.RoleAdminAuthResCache;
import com.rpframework.utils.CollectionUtils;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("admin_perm")
public class AdminBaseTemplateDirectiveModel extends BaseTemplateDirectiveModel {
	static final String AD_MENU_LIST = "ad_menu_list";
	static final String AD_ROLE_LIST = "ad_role_list";
	static final String AD_ROLE_HAS_PERM = "ad_role_has_perm";
	static final String AD_CHECK_MENU_LIMIT = "ad_check_menu_limit";
	static final String AD_PARENT_ADMINAUTHRES_LIST = "ad_parent_adminauthres_list";
	RoleAdminAuthResVerifyEvent roleAdminAuthResVerifyEvent = new RoleAdminAuthResVerifyEvent();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		//得到前端传进来的参数
		String cmd = DirectiveUtils.getString("cmd", params);
		Map paramWarp = new HashMap(params);
		AdminUser adminUser = DirectiveUtils.getSessionAttrFormEnvironment(env, AdminBaseAct.SESSION_ADMIN_USER_KEY);
		boolean pass = true;
		if(StringUtils.isBlank(cmd)) {
			String uri = DirectiveUtils.getString("uri", params);
			if(StringUtils.isBlank(uri)) {
				
			} else {
				pass = roleAdminAuthResVerifyEvent.checkLimit(adminUser, uri);
			}
		} else if(StringUtils.equals(cmd, AD_MENU_LIST)) { //如果是AD_MENU_LIST
			Integer pId = DirectiveUtils.getInt("pId", params);
			AdminMenuService adminMenuService = SpringUtils.getBean("adminMenuService");
			List<AdminMenu> list = adminMenuService.getMenuListByParentId(pId);
			paramWarp.put("m_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		} else if(StringUtils.equals(cmd, AD_CHECK_MENU_LIMIT)) {
			if(adminUser == null) {
				pass = false;
			} else {
				AdminMenu adminMenu = DirectiveUtils.getObject("adminMenu", params);
				if(StringUtils.isBlank(adminMenu.getLinkUrl())) {
					if(CollectionUtils.isEmpty(adminMenu.getChildren())) {
						pass = false;
					} else {
						pass = false;
						for (AdminMenu tempam : adminMenu.getChildren()) {
							boolean t = roleAdminAuthResVerifyEvent.checkLimit(adminUser, tempam.getLinkUrl());
							if(t) {
								pass = true;
								break;
							}
						}
					}
				} else {
					pass = roleAdminAuthResVerifyEvent.checkLimit(adminUser, adminMenu.getLinkUrl());
				}
			}
			
			
		} else if(StringUtils.equals(cmd, AD_ROLE_LIST)) {
			AdminRoleService adminRoleService = SpringUtils.getBean("adminRoleService");
			List<AdminRole> list = adminRoleService.findAll();
			paramWarp.put("r_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		} else if(StringUtils.equals(cmd, AD_PARENT_ADMINAUTHRES_LIST)) {
			AdminAuthResService adminAuthResService = SpringUtils.getBean("adminAuthResService");
			List<AdminAuthRes> list = adminAuthResService.getAdminAuthResByParentId(new Integer(0));
			paramWarp.put("ar_list", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
		} else if(StringUtils.equals(cmd, AD_ROLE_HAS_PERM)) {
			AdminRole adminRole = DirectiveUtils.getObject("adminRole", params);
			if(adminRole == null) {
				pass = false;
			} else {
				Integer adminAuthResId = DirectiveUtils.getInt("adminAuthResId", params);
				RoleAdminAuthResCache cache = CacheUtils.getIntance().get2(RoleAdminAuthResCache.k);
				
				List<AdminAuthRes> list = cache.getByRoleId(adminRole.getId());
				
				pass = false;
				if(CollectionUtils.isNotEmpty(list)) {
					for (AdminAuthRes adminAuthRes : list) {
						if(adminAuthResId == adminAuthRes.getId()) {
							pass = true;
							break;
						}
					}
				}
			}
			
		}
		
		Map origWarp = DirectiveUtils.addParamsToVariable(env, paramWarp);
		if(pass && body != null) {
			body.render(env.getOut());
		}
		DirectiveUtils.removeParamsFromVariable(env, paramWarp, origWarp);
	}
}
