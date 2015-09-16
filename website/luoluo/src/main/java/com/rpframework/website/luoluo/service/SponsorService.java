package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.ISponsorDao;
import com.rpframework.website.luoluo.domain.Sponsor;

@Service
public class SponsorService extends BaseService{
	@Resource ISponsorDao isponsorDao;
	/**
	 * 查询整个表
	 * @param pager
	 * @return
	 */
	public Pager<Sponsor> getpager(Pager<Sponsor> pager){
		long startTime = System.currentTimeMillis();
		List<Sponsor> list = isponsorDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	/**
	 * 查询单个数据
	 * @param id
	 * @return
	 */
	public Sponsor seletOne(Integer id){
		Sponsor spor=isponsorDao.select(id);
		return spor;
	}
}
