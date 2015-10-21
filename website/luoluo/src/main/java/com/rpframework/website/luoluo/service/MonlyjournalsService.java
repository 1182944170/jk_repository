package com.rpframework.website.luoluo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.website.luoluo.dao.IMonlyjournalsDao;
import com.rpframework.website.luoluo.domain.Monlyjournals;

@Service
public class MonlyjournalsService extends BaseService{
	@Resource IMonlyjournalsDao imonlyjournalsDao;
	
	public boolean insertdo(Monlyjournals weifu) {
		// TODO Auto-generated method stub
		return imonlyjournalsDao.insert(weifu);
	}
	
}
