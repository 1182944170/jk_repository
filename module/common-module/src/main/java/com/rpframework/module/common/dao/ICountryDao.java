package com.rpframework.module.common.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.Country;

public interface ICountryDao extends IDao {
	List<Country> queryAll();
}