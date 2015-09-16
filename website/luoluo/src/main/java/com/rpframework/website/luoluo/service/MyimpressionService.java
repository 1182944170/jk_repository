package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IMyimpressionDao;
import com.rpframework.website.luoluo.domain.Myimpression;

@Service
public class MyimpressionService extends BaseService{
	@Resource IMyimpressionDao iMyimpressionDao;
	
	public boolean  insertdo(Myimpression reporta){
		boolean cationDO=iMyimpressionDao.insert(reporta);
		return cationDO;
	}
	/***
	 * 查询我的好感
	 * @param pager
	 * @return
	 */
	public Pager<Myimpression> Userpager(Pager<Myimpression> pager){
		long startTime = System.currentTimeMillis();
		List<Myimpression> list = iMyimpressionDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
}
