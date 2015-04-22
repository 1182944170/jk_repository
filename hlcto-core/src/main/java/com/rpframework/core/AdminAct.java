package com.rpframework.core;


public abstract class AdminAct extends BaseModuleAct {
	public abstract String getModuleName();
	
	public String doPackageURI(String uri) {
		return this.getModuleName() + "/" + uri;
	}
}
