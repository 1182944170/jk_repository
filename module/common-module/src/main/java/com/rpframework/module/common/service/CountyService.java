package com.rpframework.module.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.ICountyDao;
import com.rpframework.module.common.domain.County;

@Service
public class CountyService extends BaseService {
	@Resource public ICountyDao countyDao;

	public List<County> searchByCountry(String citycode) {
		return countyDao.searchByCountry(citycode);
	}

	public County searchByCityCode(String dbcode) {
		return countyDao.searchByCityCode(dbcode);
	}
}
