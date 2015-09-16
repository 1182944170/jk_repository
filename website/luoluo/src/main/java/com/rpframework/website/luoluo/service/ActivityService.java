package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IActivityDao;
import com.rpframework.website.luoluo.domain.Activity;


@Service
public class ActivityService extends BaseService{
	@Resource IActivityDao iactivitydao;
	public Pager<Activity> getpager(Pager<Activity> pager){
		long startTime = System.currentTimeMillis();
		List<Activity> list = iactivitydao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	/**
	 * 查询单个
	 * @param id
	 * @return
	 */
	public Activity selectcal(Integer id){

		Activity cationDO=iactivitydao.select(id);
		return cationDO;
	}
}
