package com.rpframework.module.common.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.module.common.dao.ISMSDao;
import com.rpframework.module.common.domain.SMS;
import com.rpframework.module.common.event.ISMSEvent;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.DateUtils;

@Service
public class SMSService extends BaseService {
	@Resource public ISMSDao smsDao;
	public boolean sendSMS(int channelType, String phone, String verifyCode, String content, long expirationTime) {
		Map<String, ISMSEvent> beans = SpringUtils.getApplicationContext().getBeansOfType(ISMSEvent.class);
		if(CollectionUtils.isEmpty(beans)) {
			throw new RuntimeException("未找到发送短信的实现!");
		}
		
		ISMSEvent smsEvent = beans.values().iterator().next();
		String responseText = smsEvent.sendSMS(phone, content);
		SMS sms = new SMS();
		sms.setChannelSend(smsEvent.getChannelSend());
		sms.setChannelType(channelType);
		sms.setContent(content);
		sms.setExpirationTime(expirationTime);
		sms.setPhone(phone);
		sms.setResponseText(responseText);
		sms.setSendState(smsEvent.checkSucc(responseText) ? 1 : 0);
		sms.setState(SMS.SMS_STATE_UNVERIFY);
		sms.setVerifyCode(verifyCode);
		sms.setRecordCreateTime(System.currentTimeMillis() / 1000);
		return smsDao.insert(sms);
	}
	
	public boolean sendSMS(int channelType, String phone, String verifyCode, String content) {
		return this.sendSMS(channelType, phone, verifyCode, content, DateUtils.minAdd(30).getTime()/1000);
	}
	
	public boolean setVerifyCodeVaild(int channelType, String phone){
		SMS sms = smsDao.findSMSByChannelTypeAndPhone(channelType, phone);
		if(sms == null) {
			return false;
		} else {
			//update
			sms.setState(SMS.SMS_STATE_VERIFYED);
			sms.setVerifyTime(System.currentTimeMillis() / 1000);
			return smsDao.update(sms);
		}
	}
	
	public boolean checkVerifyCode(int channelType, String phone, String inputVerfiyCode) {
		SMS sms = smsDao.findSMSByChannelTypeAndPhone(channelType, phone);
		if(sms == null) {
			return false;
		} else {
			if(sms.getExpirationTime() > 0 && System.currentTimeMillis()/1000 > sms.getExpirationTime()) {//过期了
				return false;
			}
			
			if(sms.getState() != SMS.SMS_STATE_UNVERIFY) {//已经验证过了
				return false;
			}
			
			if(!StringUtils.equals(sms.getVerifyCode(), inputVerfiyCode)) {
				return false;
			}
			return true;
		}
	}
}
