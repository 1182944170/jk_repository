package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IReportactivityDao;
import com.rpframework.website.luoluo.domain.Reportactivity;
@Service
public class ReportactivityService  extends BaseService{
	
	@Resource IReportactivityDao ireportactivityDao;
	
	public Pager<Reportactivity> sepager(Pager<Reportactivity> pager){
		long startTime = System.currentTimeMillis();
		List<Reportactivity> list = ireportactivityDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	/**
	 * 查询单个
	 * @param id
	 * @return
	 */
	public Reportactivity  selectcal(Integer  id){
		Reportactivity cationDO=ireportactivityDao.select(id);
		return cationDO;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean deletesell(Integer id){
		
		return ireportactivityDao.delete(id);
	}
	public boolean insertdo(Reportactivity reporta){
		return ireportactivityDao.insert(reporta);
	}
}
