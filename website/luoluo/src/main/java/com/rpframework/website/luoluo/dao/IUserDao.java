package com.rpframework.website.luoluo.dao;


import java.util.List;
import java.util.Map;




import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.User;

public interface IUserDao extends IDao{
	List<User> doPager(Map<?, ?> map);
	List<User> findUserByName(String name);
	List<User> findUserBy(String name);
	User findUserByPhone(String phone); 
	User AccoutSelect(String uid);
}
