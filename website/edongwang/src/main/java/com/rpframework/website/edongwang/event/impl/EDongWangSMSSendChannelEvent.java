package com.rpframework.website.edongwang.event.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.module.common.event.impl.SMSAbstractSendChannel1Event;

@Component
public class EDongWangSMSSendChannelEvent extends SMSAbstractSendChannel1Event {

	@Override
	public void initForSet() {
		String sendCount = DictionarySettingUtils.getParameterValue("admin.sms.channel1.sendCount");
		String sendPwd = DictionarySettingUtils.getParameterValue("admin.sms.channel1.sendPwd");
		if(StringUtils.isNotBlank(sendCount) && StringUtils.isNotBlank(sendPwd)) {
			accout = sendCount;
			pswd = sendPwd;
			logger.info("设置短信渠道:[" + getChannelSend() + "],账号:["+accout+"],密码:["+sendPwd+"]");
		} else {
			logger.warn("短信发送渠道[{0}]并没有设置账号密码，将采用默认!", getChannelSend());
		}
	}

}