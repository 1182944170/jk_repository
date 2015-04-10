package com.rpframework.module.adminbase.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.adminbase.domain.Dictionary;

public interface IDictionaryDao extends IDao {
	List<Dictionary> queryAll();

	List<Dictionary> doPager(Map<?, ?> packageMyBatisParam);
}
