package com.rpframework.module.common.act;

import com.rpframework.core.BaseModuleAct;

public class CommonBaseAct extends BaseModuleAct {
	
	public String getModuleName() {
		return "common-admin";
	};
	
	public String doPackageURI(String uri) {
		return this.getModuleName() + "/" + uri;
	}
}
