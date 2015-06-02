package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.core.utils.cache.KVObj;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.dao.IUserMoneyLogDao;
import com.rpframework.website.edongwang.domain.UserMoney;
import com.rpframework.website.edongwang.domain.UserMoneyLog;

@Service
public class UserMoneyLogService extends BaseService {
	public @Resource IUserMoneyLogDao userMoneyLogDao;
	
	public Pager<UserMoneyLog> getPager(Pager<UserMoneyLog> pager) {
		long startTime = System.currentTimeMillis();
		List<UserMoneyLog> itemList = userMoneyLogDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public boolean addLog(Integer userId, double money, UserMoney userMoney, KVObj kvObj, String ext) {
		if(StringUtils.isBlank(ext)) {
			ext = "{}";
		}
		UserMoneyLog userMoneyLog = new UserMoneyLog();
		userMoneyLog.setRecordCreateTime(System.currentTimeMillis() / 1000);
		userMoneyLog.setRemark(String.valueOf(kvObj.v));
		userMoneyLog.setType(Integer.valueOf(kvObj.k));
		userMoneyLog.setCurrMoney(userMoney.getMoney());
		userMoneyLog.setUsedMoney(userMoney.getUsedMoney());
		userMoneyLog.setMoney(money);
		userMoneyLog.setUserId(userId);
		userMoneyLog.setExt(ext);
		return insert(userMoneyLog);
	}
}
