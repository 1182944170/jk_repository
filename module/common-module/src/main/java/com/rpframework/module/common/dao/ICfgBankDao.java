package com.rpframework.module.common.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.CfgBank;

public interface ICfgBankDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<CfgBank> doPager(Map packageMyBatisParam);

}