package com.rpframework.module.user.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.user.domain.UserScoreShopLog;

public interface IUserScoreShopLogDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<UserScoreShopLog> doPager(Map packageMyBatisParam);
}