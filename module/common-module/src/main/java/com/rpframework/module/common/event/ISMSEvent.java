package com.rpframework.module.common.event;

import com.rpframework.core.event.IBaseEvent;

public interface ISMSEvent extends IBaseEvent {
	
	/**
	 * 获取发送的渠道编号
	 * @return
	 */
	int getChannelSend();
	/**
	 * 发送短信验证
	 * 描述
	 * @param phone
	 * @param content
	 * @return
	 */
	boolean sendSMS(String phone, String content);
}
