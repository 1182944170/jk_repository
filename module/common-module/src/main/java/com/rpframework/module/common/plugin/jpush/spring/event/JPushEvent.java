package com.rpframework.module.common.plugin.jpush.spring.event;

import org.springframework.context.ApplicationEvent;

import cn.jpush.api.push.model.PushPayload;

/**
 * 
 * title: OrderSuccEvent.java 
 * 订单充值成功事件
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月30日 下午5:48:15
 */
public class JPushEvent extends ApplicationEvent {
	
	String appKey;
	/**描述*/  
	private static final long serialVersionUID = 1238516880586204200L;
	public JPushEvent(PushPayload pushPayload, String appKey) {
		super(pushPayload);
		this.appKey = appKey;
	}
	
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
}