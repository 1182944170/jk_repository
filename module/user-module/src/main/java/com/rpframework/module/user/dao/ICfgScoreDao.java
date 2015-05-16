package com.rpframework.module.user.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.module.user.domain.CfgScore;

public interface ICfgScoreDao extends IDao {

	List<CfgScore> queryAll();
}