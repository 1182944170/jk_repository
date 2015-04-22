package com.rpframework.website.xtexam.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.xtexam.dao.IXTUserDao;
import com.rpframework.website.xtexam.domain.XTUser;

@Service
public class XTUserService extends BaseService {
	public @Resource IXTUserDao xtUserDao;

	public Pager<XTUser> getXTUserPager(Pager<XTUser> pager) {
		long startTime = System.currentTimeMillis();
		List<XTUser> itemList = xtUserDao.doPager(this
				.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public XTUser findXTUserByContact(String contact) {
		return xtUserDao.findXTUserByContact(contact);
	}
	
	public XTUser findXTUserByUserName(String userName) {
		return xtUserDao.findXTUserByUserName(userName);
	}
	
	public boolean updateXTUser(XTUser xtUser) {
		return xtUserDao.update(xtUser);
	}
	
	public boolean doRegistXTUser(XTUser xtUser) {
		if(xtUser == null || StringUtils.isBlank(xtUser.getUserName())
				 || StringUtils.isBlank(xtUser.getPwd()) ) {
			throw new IllegalArgumentException("注册时参数错误!");
		}
			
		return xtUserDao.insert(xtUser);
	}
}
