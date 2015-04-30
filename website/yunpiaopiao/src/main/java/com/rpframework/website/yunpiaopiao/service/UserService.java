package com.rpframework.website.yunpiaopiao.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.yunpiaopiao.dao.IUserDao;
import com.rpframework.website.yunpiaopiao.domian.User;

@Service
public class UserService extends BaseService {
	public @Resource IUserDao userDao;

	public Pager<User> getUserPager(Pager<User> pager) {
		long startTime = System.currentTimeMillis();
		List<User> itemList = userDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public User findUserByContact(String contact) {
		return userDao.findUserByContact(contact);
	}
	
	public User findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}
	
	
	
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}
	
	
	public User findUserByNickName(String nickName) {
		return userDao.findUserByNickName(nickName);
	}
	
	
	
	public boolean updateUser(User User) {
		return userDao.update(User);
	}
	
	public boolean doRegistUser(User User) {
		if(User == null || StringUtils.isBlank(User.getUserName())
				 || StringUtils.isBlank(User.getPwd()) ) {
			throw new IllegalArgumentException("注册时参数错误!");
		}
			
		return userDao.insert(User);
	}
}
