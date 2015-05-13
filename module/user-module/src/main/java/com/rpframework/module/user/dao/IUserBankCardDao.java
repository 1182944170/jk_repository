package com.rpframework.module.user.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.module.user.domain.UserBankCard;

public interface IUserBankCardDao extends IDao {

	List<UserBankCard> getCardsByUserId(Integer userId);
}