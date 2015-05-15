package com.rpframework.module.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.core.utils.cache.KVObj;
import com.rpframework.module.user.dao.IUserScoreLogDao;
import com.rpframework.module.user.domain.UserScoreLog;
import com.rpframework.utils.Pager;

@Service
public class UserScoreLogService extends BaseService {
	public @Resource IUserScoreLogDao userScoreLogDao;

	public Pager<UserScoreLog> getPager(Pager<UserScoreLog> pager) {
		long startTime = System.currentTimeMillis();
		List<UserScoreLog> itemList = userScoreLogDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public boolean addLog(Integer userId, Integer score, KVObj kvObj, String ext) {
		if(StringUtils.isBlank(ext)) {
			ext = "{}";
		}
		UserScoreLog userScoreLog = new UserScoreLog();
		userScoreLog.setRecordCreateTime(System.currentTimeMillis() / 1000);
		userScoreLog.setRemark(String.valueOf(kvObj.v));
		userScoreLog.setType(Integer.valueOf(kvObj.k));
		userScoreLog.setUserId(userId);
		userScoreLog.setScore(score);
		userScoreLog.setExt(ext);
		return insert(userScoreLog);
	}
}
