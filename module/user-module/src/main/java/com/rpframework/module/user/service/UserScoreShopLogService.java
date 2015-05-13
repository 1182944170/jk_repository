package com.rpframework.module.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.module.user.dao.IUserScoreShopLogDao;
import com.rpframework.module.user.domain.UserScoreShopLog;
import com.rpframework.utils.Pager;

@Service
public class UserScoreShopLogService extends BaseService {
	public @Resource IUserScoreShopLogDao userScoreShopLogDao;
	
	public Pager<UserScoreShopLog> getPager(Pager<UserScoreShopLog> pager) {
		long startTime = System.currentTimeMillis();
		List<UserScoreShopLog> itemList = userScoreShopLogDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	/**
	 * 
	 * 处理
	 * @param userScoreShopLogId
	 * @param state 处理状态
	 * @return
	 */
	public boolean deal(Integer userScoreShopLogId, Integer sendShopState) {
		UserScoreShopLog userScoreShopLogDB = select(userScoreShopLogId);
		Assert.notNull(userScoreShopLogDB);
		if(userScoreShopLogDB.getSendShopState() == 0) {
			userScoreShopLogDB.setSendShopState(sendShopState);
			return update(userScoreShopLogDB);
		} 
		
		throw new IllegalArgumentException("不能重复处理该商品.");
	}
}
