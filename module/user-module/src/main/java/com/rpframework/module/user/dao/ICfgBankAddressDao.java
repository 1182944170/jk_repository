package com.rpframework.module.user.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.user.domain.CfgBankAddress;

public interface ICfgBankAddressDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<CfgBankAddress> doPager(Map packageMyBatisParam);

}