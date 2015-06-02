package com.rpframework.website.edongwang.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.UserBankCard;

public interface IUserBankCardDao extends IDao {

	List<UserBankCard> getCardsByUserId(Integer userId);
}