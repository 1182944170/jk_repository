package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;




import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.ISponsorDao;
import com.rpframework.website.luoluo.domain.Sponsorlis;

@Service
public class SponsorService extends BaseService{
	@Resource ISponsorDao isponsorDao;
	/**
	 * 查询整个表
	 * @param pager
	 * @return
	 */
	public Pager<Sponsorlis> getpager(Pager<Sponsorlis> pager){
		long startTime = System.currentTimeMillis();
		List<Sponsorlis> list = isponsorDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	/**
	 * 查询单个数据
	 * @param id
	 * @return
	 */
	public Sponsorlis seletOne(Integer id){
		Sponsorlis spor=isponsorDao.select(id);
		return spor;
	}
	
	public boolean updatedo(Sponsorlis sponsorlis) {
		return isponsorDao.update(sponsorlis);
	}
	public boolean insertsponsor(Sponsorlis sponsor) {
		// TODO Auto-generated method stub
		return isponsorDao.insert(sponsor);
	}
	public boolean deletesell(Integer deleteid) {
		// TODO Auto-generated method stub
		return isponsorDao.delete(deleteid);
	}
}
