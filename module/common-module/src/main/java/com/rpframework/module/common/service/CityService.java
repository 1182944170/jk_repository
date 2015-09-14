package com.rpframework.module.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.ICityDao;
import com.rpframework.module.common.domain.City;

@Service
public class CityService extends BaseService {
	@Resource public ICityDao cityDao;

	public List<City> searchByCity(String provinceCode) {
		// TODO Auto-generated method stub
		return cityDao.searchByCity(provinceCode);
	}
}
