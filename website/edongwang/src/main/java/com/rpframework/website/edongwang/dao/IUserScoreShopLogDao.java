package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.UserScoreShopLog;

public interface IUserScoreShopLogDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<UserScoreShopLog> doPager(Map packageMyBatisParam);
}