package com.rpframework.module.common.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.County;

public interface ICountyDao extends IDao {
	List<County> queryAll();
}