package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.UserScore;

public interface IUserScoreDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<UserScore> doPager(Map packageMyBatisParam);

}