package com.rpframework.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * title: BaseModuleAct.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年3月26日 下午7:40:41
 */
public abstract class BaseModuleAct extends BaseAct {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	public abstract String getModuleName();
	public abstract String doPackageURI(String uri);
}
