package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.UserMoney;

public interface IUserMoneyDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<UserMoney> doPager(Map packageMyBatisParam);

}