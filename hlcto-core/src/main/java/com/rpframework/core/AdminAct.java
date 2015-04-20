package com.rpframework.core;

import javax.servlet.http.HttpSession;

public abstract class AdminAct extends BaseModuleAct {
	
	@SuppressWarnings("unchecked")
	public <T> T getSessionAdminUser(HttpSession session){
		Object attribute = session.getAttribute(SESSION_ADMIN_USER_KEY);
		if(attribute == null) {
			return null;
		}
		
		return (T) attribute;
	}
	
	public abstract String getModuleName();
	
	public String doPackageURI(String uri) {
		return this.getModuleName() + "/" + uri;
	}
}
