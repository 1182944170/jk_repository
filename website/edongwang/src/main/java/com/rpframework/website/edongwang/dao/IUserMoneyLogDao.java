package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.UserMoneyLog;

public interface IUserMoneyLogDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<UserMoneyLog> doPager(Map packageMyBatisParam);

}