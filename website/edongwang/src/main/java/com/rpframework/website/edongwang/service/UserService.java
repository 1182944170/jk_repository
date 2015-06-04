package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.utils.AlgorithmEnum;
import com.rpframework.utils.AlgorithmUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.dao.IUserDao;
import com.rpframework.website.edongwang.domain.User;

@Service
public class UserService extends BaseService {
	
	@Resource public IUserDao userDao;
	
	public Pager<User> getPager(Pager<User> pager) {
		long startTime = System.currentTimeMillis();
		List<User> itemList = userDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public User findUserByContact(String contact) {
		return userDao.findUserByContact(contact);
	}

	public boolean doLoginRecord(User user, HttpServletRequest request) {
		user.setLastLoginIp(user.getLoginIp()) ;
		user.setLoginIp(request.getRemoteAddr());
		user.setLastLoginTime(user.getLoginTime());
		user.setLoginTime(System.currentTimeMillis()/1000);
		
		return update(user);
	}
	
	public boolean changePassword(Integer userId,String oldPassword, String newPassword) {
		User user = select(userId);
		Assert.notNull(user);
		return changePassword(user, oldPassword, newPassword);
	}
	
	public boolean changePassword(User user,String oldPassword, String newPassword) {
		
		if(StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
			throw new IllegalArgumentException("非法参数!");
		}
		
		if(StringUtils.equals(newPassword, oldPassword)) {
			throw new IllegalArgumentException("俩次密码一致!");
		}
		
		String oldPasswordMD5 = AlgorithmUtils.encodePassword(oldPassword, AlgorithmEnum.MD5);
		if(!StringUtils.equals(oldPasswordMD5, user.getPassword())) {
			throw new IllegalArgumentException("原始密码不一致!");
		}
		
		String newPasswordMD5 = AlgorithmUtils.encodePassword(newPassword, AlgorithmEnum.MD5);
		user.setPassword(newPasswordMD5);
		
		return update(user);
	}

}
