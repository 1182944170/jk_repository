package com.rpframework.module.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.user.dao.IUserBankCardDao;
import com.rpframework.module.user.domain.UserBankCard;

@Service
public class UserBankCardService extends BaseService {
	public @Resource IUserBankCardDao userBankCardDao;

	public List<UserBankCard> getCardsByUserId(Integer userId) {
		return userBankCardDao.getCardsByUserId(userId);
	}
}
