package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.UserScoreLog;

public interface IUserScoreLogDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<UserScoreLog> doPager(Map packageMyBatisParam);
}