package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.dao.IAdminHouseDao;
import com.rpframework.website.edongwang.domain.Houses;

@Service("adminHouseService")
public class AdminHouseService extends BaseService{
	
	@Resource public IAdminHouseDao adminHouseDao;
	
	public Pager<Houses> getHousesPager(Pager<Houses> pager) {
		long startTime = System.currentTimeMillis();
		List<Houses> itemList = adminHouseDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

}
