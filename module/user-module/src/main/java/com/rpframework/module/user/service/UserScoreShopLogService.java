package com.rpframework.module.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.module.user.dao.IUserScoreShopLogDao;
import com.rpframework.module.user.domain.UserScoreShopLog;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.Pager;

@Service
public class UserScoreShopLogService extends BaseService {
	public @Resource IUserScoreShopLogDao userScoreShopLogDao;
	Map<Integer, List<UserScoreShopLog>> userScoreShopLogMap;
	
	public void resetUserScoreShopLogs(Integer userId) {
		if(userScoreShopLogMap == null) {
			userScoreShopLogMap = new HashMap<Integer, List<UserScoreShopLog>>(1024);
		}
		
		if(userScoreShopLogMap.containsKey(userId)) {
			userScoreShopLogMap.remove(userId);
		}
	}
	
	public Integer getUserScoreShopLogNumber(int userId, int scoreShopId, long startTime, long endTime) {
		List<UserScoreShopLog> list = getUserScoreShopLogs(userId);
		int count = 0;
		
		for (UserScoreShopLog userScoreShopLog : list) {
			if(scoreShopId == userScoreShopLog.getScoreShopId() && userScoreShopLog.getRecordCreateTime() >= startTime && userScoreShopLog.getRecordCreateTime() < endTime) {
				count ++;
			}
		}
		
		return count;
	}
	
	public List<UserScoreShopLog> getUserScoreShopLogs(Integer userId) {
		if(userScoreShopLogMap == null) {
			userScoreShopLogMap = new HashMap<Integer, List<UserScoreShopLog>>(1024);
		}
		
		if(!userScoreShopLogMap.containsKey(userId)) {
			//最大缓存一个月的日志
			Pager<UserScoreShopLog> pager = new Pager<UserScoreShopLog>();
			
			long startTime = DateUtils.getMonthStartDate().getTime() / 1000;
			long endTime = DateUtils.getMonthEndDate().getTime() / 1000;
			pager.getSearchMap().put("startTime", String.valueOf(startTime));
			pager.getSearchMap().put("endTime", String.valueOf(endTime));
			pager.getSearchMap().put("userId", String.valueOf(userId));
			pager.setPageSize(Integer.MAX_VALUE);
			getPager(pager);
			
			userScoreShopLogMap.put(userId, pager.getItemList());
		}
		
		return userScoreShopLogMap.get(userId);
	}
	
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
		if(userScoreShopLogDB.getSendShopState() == 0 || userScoreShopLogDB.getSendShopState() == 2) {
			userScoreShopLogDB.setSendShopState(sendShopState);
			return update(userScoreShopLogDB);
		} 
		
		throw new IllegalArgumentException("不能重复处理该商品.");
	}

	public boolean hasBuyScoreShop(long startTime, long endTime, Integer scoreShopId, Integer userId) {
		return getUserScoreShopLogNumber(userId, scoreShopId, startTime, endTime) > 0;
	}

	public void addScoreShopLog(Integer scoreShopId, Integer userId,
			String name, String concact, String areaCode, String address) {
		
		UserScoreShopLog log = new UserScoreShopLog();
		log.setAddress(address);
		log.setAreaCode(areaCode);
		log.setConcact(concact);
		log.setName(name);
		log.setRecordCreateTime(System.currentTimeMillis() / 1000);
		log.setScoreShopId(scoreShopId);
		log.setSendShopState(0);
		log.setUserId(userId);
		
		insert(log);
		resetUserScoreShopLogs(userId);
	}

	public List<UserScoreShopLog> getListByScoreShopId(Integer scoreShopId) {
		Pager<UserScoreShopLog> pager = new Pager<UserScoreShopLog>();
		pager.getSearchMap().put("scoreShopId", String.valueOf(scoreShopId));
		pager.setPageSize(Integer.MAX_VALUE);
		getPager(pager);
		return pager.getItemList();
	}
}
