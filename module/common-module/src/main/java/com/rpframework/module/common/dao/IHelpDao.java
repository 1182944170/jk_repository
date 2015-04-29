package com.rpframework.module.common.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.Help;

public interface IHelpDao extends IDao {
	
	List<Help> doPager(Map<?, ?> map);
	
	Help getHelpByaliasesTitle(String aliasesTitle);
}