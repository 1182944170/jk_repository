package com.rpframework.module.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.ICityDao;

@Service("cityService")
public class CityService extends BaseService {
	@Resource public ICityDao cityDao;
}
