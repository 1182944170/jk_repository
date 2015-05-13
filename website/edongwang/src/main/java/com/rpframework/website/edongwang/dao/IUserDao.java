package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.User;

public interface IUserDao extends IDao{
	
	List<User> doPager(Map<?,?> paramMap);

	User findUserByContact(String contact);

}
