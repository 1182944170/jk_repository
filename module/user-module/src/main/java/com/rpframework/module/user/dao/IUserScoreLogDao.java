package com.rpframework.module.user.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.user.domain.UserScoreLog;

public interface IUserScoreLogDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<UserScoreLog> doPager(Map packageMyBatisParam);
}