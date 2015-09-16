package com.rpframework.website.luoluo.event.impl;



import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.module.common.event.impl.SMSAbstractSendChannel1Event;

/**
 * 实现短信发送渠道
 * @date 2015年7月17日 下午4:17:00
 */
@Component
public class DJYSMSSendChannelEvent extends SMSAbstractSendChannel1Event {

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