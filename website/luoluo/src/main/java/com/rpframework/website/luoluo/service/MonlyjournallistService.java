package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.website.luoluo.dao.IMonlyjournallistDao;
import com.rpframework.website.luoluo.domain.Monlyjournallist;

@Service
public class MonlyjournallistService extends BaseService{
	@Resource IMonlyjournallistDao imonlyjournalsDao;
	
	public boolean insertdo(Monlyjournallist weifu) {
		// TODO Auto-generated method stub
		return imonlyjournalsDao.insert(weifu);
	}



	public List<Monlyjournallist> selectdoole(Integer id) {
		return imonlyjournalsDao.selecttole(id);
	}
	
}
