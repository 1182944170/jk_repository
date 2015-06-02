package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.domain.CfgBank;
import com.rpframework.module.common.domain.CfgBankAddress;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.website.edongwang.dao.IUserBankCardDao;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.domain.UserBankCard;

@Service
public class UserBankCardService extends BaseService {
	public @Resource IUserBankCardDao userBankCardDao;
	@Resource UserService userService;

	public List<UserBankCard> getCardsByUserId(Integer userId) {
		return userBankCardDao.getCardsByUserId(userId);
	}
	
	public boolean hasBind(Integer userId) {
		List<UserBankCard> list = getCardsByUserId(userId);
		return CollectionUtils.isNotEmpty(list);
	}
	
	public boolean bind(Integer userId, String name, Integer bankId, String account, String address) {
		if(hasBind(userId)) {
			throw new IllegalArgumentException("已经有绑定银行卡数据!");
		}
		
		UserBankCard userBankCard = this.packageBind(userId, name, bankId, account, address);
		User user = userService.select(userId);
		if(StringUtils.isBlank(user.getRealName())) {
			user.setRealName(name);
			userService.update(user);//绑定
		}
		return insert(userBankCard);
	}
	
	
	private UserBankCard packageBind(Integer userId, String name, Integer bankId, String account, String address) {
		UserBankCard userBankCard = new UserBankCard();
		CfgBank cfgBank = new CfgBank();
		userBankCard.setAccount(account);
		cfgBank.setId(bankId);
		userBankCard.setCfgBank(cfgBank);
		userBankCard.setCfgBankAddress(new CfgBankAddress());
		userBankCard.setName(name);
		userBankCard.setAddress(address);
		userBankCard.setState(1);
		userBankCard.setUserId(userId);
		
		return userBankCard;
	}
	
	public boolean reBind(Integer userId, String name, Integer bankId, String account, String address){
		
		List<UserBankCard> list = getCardsByUserId(userId);
		if(CollectionUtils.isNotEmpty(list)) {
			UserBankCard userBankCard = this.packageBind(userId, name, bankId, account, address);
			userBankCard.setId(list.get(0).getId());
			return update(userBankCard);
		} else {
			return bind(userId, name, bankId, account, address);
		}
	}
}
