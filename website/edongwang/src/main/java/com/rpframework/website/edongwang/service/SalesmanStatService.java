package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.dao.ISalesmanStatDao;
import com.rpframework.website.edongwang.domain.SalesmanStat;

/**
 * title: RecommendStatService.java 
 * 为edongwang的业务员统计埋点
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月7日 下午6:11:41
 */
@Service
public class SalesmanStatService extends BaseService {
	public @Resource ISalesmanStatDao salesmanStatDao;
	
	public Pager<SalesmanStat> getPager(Pager<SalesmanStat> pager) {
		long startTime = System.currentTimeMillis();
		List<SalesmanStat> itemList = salesmanStatDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	public SalesmanStat getSalesmanStat(Integer userId, Integer houseId) {
		SalesmanStat salesmanStat = salesmanStatDao.selectByUserIdAndHouseId(userId, houseId);
		if(salesmanStat == null) {
			salesmanStat = new SalesmanStat();
			salesmanStat.setDealCount(0);
			salesmanStat.setGrabCount(0);
			salesmanStat.setHouseId(houseId);
			salesmanStat.setTotalDealPrice(0D);
			salesmanStat.setTotalSurface(0D);
			salesmanStat.setUserId(userId);
			boolean insert = insert(salesmanStat);
			Assert.isTrue(insert);
		}
		
		return salesmanStat;
	}
	
	public void grabCountStat(Integer userId, Integer houseId) {
		SalesmanStat salesmanStat = getSalesmanStat(userId, houseId);
		
		salesmanStat.setGrabCount(salesmanStat.getGrabCount() + 1);
		
		boolean update = update(salesmanStat);
		Assert.isTrue(update);
	}
	
	public void dealCountStat(Integer userId, Integer houseId) {
		SalesmanStat salesmanStat = getSalesmanStat(userId, houseId);
		
		salesmanStat.setDealCount(salesmanStat.getDealCount() + 1);
		
		boolean update = update(salesmanStat);
		Assert.isTrue(update);
	}
	
	public void totalSurfaceStat(Integer userId, Integer houseId, Double surface) {
		SalesmanStat salesmanStat = getSalesmanStat(userId, houseId);
		
		salesmanStat.setTotalSurface(salesmanStat.getTotalSurface() + surface);
		
		boolean update = update(salesmanStat);
		Assert.isTrue(update);
	}
	
	public void totalDealPriceStat(Integer userId, Integer houseId, Double price) {
		SalesmanStat salesmanStat = getSalesmanStat(userId, houseId);
		
		salesmanStat.setTotalDealPrice(salesmanStat.getTotalDealPrice() + price);
		
		boolean update = update(salesmanStat);
		Assert.isTrue(update);
	}
}
