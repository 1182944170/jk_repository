package com.rpframework.module.common.plugin.networksafe;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * title: IFrequency.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年7月3日 下午8:48:15
 */
public interface IFrequency {
	
	/**
	 * 获取关注的 url
	 * /api/regist or /api/order/{orderId}/detail 
	 * @return
	 */
	String[] getFollowPath();
	
	String getFollowKey(HttpServletRequest request);
	
	RequestFrequencyLimit getRequestFrequencyLimit();
}
