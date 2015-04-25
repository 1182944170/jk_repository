package com.rpframework.website.yunpiaopiao.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.yunpiaopiao.domian.User;

public interface IUserDao extends IDao {
	User findUserByUserName(String userName);
	
	@SuppressWarnings("rawtypes")
	List<User> doPager(Map map);

	User findUserByContact(String contact);
}
