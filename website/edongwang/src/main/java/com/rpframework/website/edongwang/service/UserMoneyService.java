package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.core.utils.cache.KVObj;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.dao.IUserMoneyDao;
import com.rpframework.website.edongwang.domain.UserMoney;

@Service
public class UserMoneyService extends BaseService {
	public @Resource IUserMoneyDao userMoneyDao;
	@Resource UserMoneyLogService userMoneyLogService;
	
	public Pager<UserMoney> getPager(Pager<UserMoney> pager) {
		long startTime = System.currentTimeMillis();
		List<UserMoney> itemList = userMoneyDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	public boolean costMoney(Integer userId, double money, KVObj kvObj, String ext) {
		Assert.isTrue(money >= 0, "扣除的金额必须大于0");
		return operateMoney(userId, - money, kvObj, ext);
	}
	
	public boolean addMoney(Integer userId, double money, KVObj kvObj, String ext) {
		Assert.isTrue(money >= 0, "增加的金额必须大于0");
		return operateMoney(userId, money, kvObj, ext);
	}
	public boolean operateMoney(Integer userId, double money, KVObj kvObj, String ext) {
		UserMoney userMoney = getUserMoney(userId);
		boolean succ = true;
		if(money > 0) {
			userMoney.setMoney(userMoney.getMoney() + money);
		} else {
			if(checkCanCostMoney(userId, money)) {
				userMoney.setMoney(userMoney.getMoney() + money);
				userMoney.setUsedMoney(userMoney.getUsedMoney() + Math.abs(money));
			} else {
				succ = false;
			}
		}
		
		if(succ) {
			userMoney.setRecordModifyTime(System.currentTimeMillis() / 1000);
			boolean b = update(userMoney);
			Assert.isTrue(b, "update operateMoney fail.");
			userMoneyLogService.addLog(userId, money, userMoney, kvObj, ext);
		}
		return succ;
	}
	
	public boolean freezeMoney(Integer userId, double freezeMoney) {
		if(checkCanCostMoney(userId, freezeMoney)) {
			UserMoney userMoney = getUserMoney(userId);
			userMoney.setFreezeMoney(userMoney.getFreezeMoney() + freezeMoney);
			userMoney.setMoney(userMoney.getMoney() - freezeMoney);
			userMoney.setRecordModifyTime(System.currentTimeMillis() / 1000);
			return update(userMoney);
		} 
		
		return false;
	}
	
	public boolean checkCanCostMoney(Integer userId, double costMoney) {
		return getUserMoney(userId).getMoney() >= costMoney;
	}
	
	public boolean checkCanFreezeMoney(Integer userId, double costMoney) {
		return checkCanCostMoney(userId, costMoney);
	}
	
	public boolean unFreezeMoney(Integer userId, double freezeMoney) {
		Assert.isTrue(freezeMoney > 0);
		UserMoney userMoney = getUserMoney(userId);
		Assert.isTrue(userMoney.getFreezeMoney()>=freezeMoney, "冻结金额不足，无法释放!");
		userMoney.setFreezeMoney(userMoney.getFreezeMoney() - freezeMoney);
		userMoney.setMoney(userMoney.getMoney() + freezeMoney);
		
		return update(userMoney);
	}
	
	public UserMoney getUserMoney(Integer userId) {
		UserMoney userMoney = select(userId);
		
		if(userMoney == null) {
			userMoney = new UserMoney();
			userMoney.setUserId(userId);
			userMoney.setUsedMoney(0D);
			userMoney.setRecordModifyTime(System.currentTimeMillis() / 1000);
			userMoney.setRecordCreateTime(System.currentTimeMillis() / 1000);
			userMoney.setMoney(0D);
			userMoney.setFreezeMoney(0D);
			
			boolean b = insert(userMoney);
			Assert.isTrue(b, "add userMoney fail.");
		}
			
		return userMoney;
	}
}
