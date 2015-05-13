package com.rpframework.module.user.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.user.domain.UserMoneyLog;

public interface IUserMoneyLogDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<UserMoneyLog> doPager(Map packageMyBatisParam);

}