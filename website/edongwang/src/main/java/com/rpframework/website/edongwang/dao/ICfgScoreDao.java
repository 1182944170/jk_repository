package com.rpframework.website.edongwang.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.CfgScore;

public interface ICfgScoreDao extends IDao {

	List<CfgScore> queryAll();
}