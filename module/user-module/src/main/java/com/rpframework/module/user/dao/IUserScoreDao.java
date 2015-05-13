package com.rpframework.module.user.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.user.domain.UserScore;

public interface IUserScoreDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<UserScore> doPager(Map packageMyBatisParam);

}