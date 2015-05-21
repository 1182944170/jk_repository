package com.rpframework.module.common.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.CfgBankAddress;

public interface ICfgBankAddressDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<CfgBankAddress> doPager(Map packageMyBatisParam);

}