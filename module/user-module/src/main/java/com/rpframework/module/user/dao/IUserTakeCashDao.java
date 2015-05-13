package com.rpframework.module.user.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.user.domain.UserTakeCash;

public interface IUserTakeCashDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<UserTakeCash> doPager(Map packageMyBatisParam);
}