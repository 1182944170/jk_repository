package com.rpframework.module.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.core.utils.cache.KVObj;
import com.rpframework.module.user.dao.IUserScoreDao;
import com.rpframework.module.user.domain.UserScore;
import com.rpframework.utils.Pager;

@Service
public class UserScoreService extends BaseService {
	public @Resource IUserScoreDao userScoreDao;
	@Resource UserScoreLogService userScoreLogService;
	
	public Pager<UserScore> getPager(Pager<UserScore> pager) {
		long startTime = System.currentTimeMillis();
		List<UserScore> itemList = userScoreDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	public boolean operateScore(Integer userId, Integer score, KVObj kvObj) {
		UserScore userScore = getUserScore(userId);
		boolean succ = true;
		if(score > 0) {
			userScore.setScore(userScore.getScore() + score);
		} else {
			if(checkCanCostScore(userId, score)) {
				
			} else {
				succ = false;
			}
		}
		
		if(succ) {
			userScore.setRecordModifyTime(System.currentTimeMillis() / 1000);
			boolean b = update(userScore);
			Assert.isTrue(b, "update operateScore fail.");
			
			userScoreLogService.addLog(userId, score, kvObj);
		}
		return succ;
	}
	
	public boolean checkCanCostScore(Integer userId, double costScore) {
		return getUserScore(userId).getScore() >= costScore;
	}
	
	public UserScore getUserScore(Integer userId) {
		UserScore userScore = select(userId);
		
		if(userScore == null) {
			userScore = new UserScore();
			userScore.setUserId(userId);
			userScore.setScore(0);
			userScore.setRecordModifyTime(System.currentTimeMillis() / 1000);
			userScore.setRecordCreateTime(System.currentTimeMillis() / 1000);
			
			boolean b = insert(userScore);
			Assert.isTrue(b, "add userScore fail.");
		}
			
		return userScore;
	}
}
