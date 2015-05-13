package com.rpframework.module.user.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.user.domain.UserMoney;

public interface IUserMoneyDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<UserMoney> doPager(Map packageMyBatisParam);

}