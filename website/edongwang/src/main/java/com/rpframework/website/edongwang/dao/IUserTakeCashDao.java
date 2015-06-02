package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.UserTakeCash;

public interface IUserTakeCashDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<UserTakeCash> doPager(Map packageMyBatisParam);
}