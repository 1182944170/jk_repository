package com.rpframework.module.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.ICountryDao;

@Service("countryService")
public class CountryService extends BaseService {
	@Resource public ICountryDao countryDao;
}
