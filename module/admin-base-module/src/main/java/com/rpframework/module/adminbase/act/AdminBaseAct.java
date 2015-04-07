package com.rpframework.module.adminbase.act;

import javax.servlet.http.HttpSession;

import com.rpframework.core.BaseModuleAct;
import com.rpframework.module.adminbase.domain.AdminUser;

public class AdminBaseAct extends BaseModuleAct {
	
	public static final String SESSION_ADMIN_USER_KEY = "sessionAdminUser";

	public AdminUser getSessionAdminUser(HttpSession session){
		Object attribute = session.getAttribute(SESSION_ADMIN_USER_KEY);
		if(attribute == null) {
			return null;
		}
		
		return (AdminUser) attribute;
	}
	public String getModuleName() {
		return "admin-base";
	};
	
	public String doPackageURI(String uri) {
		return this.getModuleName() + "/" + uri;
	}
}
