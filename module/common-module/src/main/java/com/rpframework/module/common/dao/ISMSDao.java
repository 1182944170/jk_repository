package com.rpframework.module.common.dao;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.SMS;

public interface ISMSDao extends IDao {
	
	SMS findSMSByChannelTypeAndPhone(Integer channelType, String phone);
}