package com.rpframework.module.common.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.City;

public interface ICityDao extends IDao {
	List<City> queryAll();

	List<City> searchByCity(String provinceCode);

	City selectdo(String city);

	City selectdoBycitycode(String city);
}