package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.ScoreShop;

public interface IScoreShopDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<ScoreShop> doPager(Map packageMyBatisParam);
}