package com.rpframework.module.common.event;

import com.rpframework.core.event.IBaseEvent;

public interface ISMSEvent extends IBaseEvent {
	
	void initForSet();
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
	String sendSMS(String phone, String content);
	boolean checkSucc(String result);
}
