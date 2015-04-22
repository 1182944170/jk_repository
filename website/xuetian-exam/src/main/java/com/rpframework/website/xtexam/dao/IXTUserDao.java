package com.rpframework.website.xtexam.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.xtexam.domain.XTUser;

public interface IXTUserDao extends IDao {
	XTUser findXTUserByUserName(String userName);
	
	@SuppressWarnings("rawtypes")
	List<XTUser> doPager(Map map);

	XTUser findXTUserByContact(String contact);
}
