package com.rpframework.website.luoluo.service;

import java.util.List;

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



	public List<Monlyjournals> selectdoole(Integer id) {
		return imonlyjournalsDao.selecttole(id);
	}
	
}
