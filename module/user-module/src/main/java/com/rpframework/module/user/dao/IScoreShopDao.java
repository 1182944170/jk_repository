package com.rpframework.module.user.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.user.domain.ScoreShop;

public interface IScoreShopDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<ScoreShop> doPager(Map packageMyBatisParam);
}