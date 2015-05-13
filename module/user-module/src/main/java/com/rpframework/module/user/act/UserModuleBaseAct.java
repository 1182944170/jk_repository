package com.rpframework.module.user.act;

import com.rpframework.core.BaseModuleAct;

public class UserModuleBaseAct extends BaseModuleAct {
	
	public String getModuleName() {
		return "user-admin";
	};
	
	public String doPackageURI(String uri) {
		return this.getModuleName() + "/" + uri;
	}
}
