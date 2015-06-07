package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.dao.IRecommendStatDao;
import com.rpframework.website.edongwang.domain.RecommendStat;

/**
 * title: RecommendStatService.java 
 * 为edongwang的推荐统计埋点
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月7日 下午6:11:41
 */
@Service
public class RecommendStatService extends BaseService {
	public @Resource IRecommendStatDao recommendStatDao;
	
	public Pager<RecommendStat> getPager(Pager<RecommendStat> pager) {
		long startTime = System.currentTimeMillis();
		List<RecommendStat> itemList = recommendStatDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	public RecommendStat getRecommendStat(Integer userId) {
		RecommendStat recommendStat = select(userId);
		if(recommendStat == null) {
			recommendStat = new RecommendStat();
			recommendStat.setaCount(0);
			recommendStat.setbCount(0);
			recommendStat.setcCount(0);
			recommendStat.setdCount(0);
			recommendStat.setDealHouseCount(0);
			recommendStat.setEffectiveCount(0);
			recommendStat.setInvalidCount(0);
			recommendStat.setTotalCount(0);
			recommendStat.setTotalDealPrice(0D);
			recommendStat.setTotalSurface(0D);
			recommendStat.setUserId(userId);
			
			boolean insert = insert(recommendStat);
			Assert.isTrue(insert);
		}
		
		return recommendStat;
	}
	/**
	 * 
	 * 推荐次数埋点
	 * @param userId
	 */
	public void totalCountStat(Integer userId) {
		RecommendStat recommendStat = getRecommendStat(userId);
		recommendStat.setTotalCount(recommendStat.getTotalCount() + 1);
		
		boolean update = update(recommendStat);
		Assert.isTrue(update);
	}
	public void invalidCountStat(Integer userId) {
		RecommendStat recommendStat = getRecommendStat(userId);
		recommendStat.setInvalidCount(recommendStat.getInvalidCount() + 1);
		
		boolean update = update(recommendStat);
		Assert.isTrue(update);
	}

	public void effectiveCountStat(Integer userId) {
		RecommendStat recommendStat = getRecommendStat(userId);
		recommendStat.setEffectiveCount(recommendStat.getEffectiveCount() + 1);
		
		boolean update = update(recommendStat);
		Assert.isTrue(update);
	}
	public void dealHouseCountStat(Integer userId) {
		RecommendStat recommendStat = getRecommendStat(userId);
		recommendStat.setDealHouseCount(recommendStat.getDealHouseCount() + 1);
		
		boolean update = update(recommendStat);
		Assert.isTrue(update);
	}
	public void totalSurfaceStat(Integer userId, Double surface) {
		RecommendStat recommendStat = getRecommendStat(userId);
		recommendStat.setTotalSurface(recommendStat.getTotalSurface() + surface);
		
		boolean update = update(recommendStat);
		Assert.isTrue(update);
	}
	
	public void totalDealPriceStat(Integer userId, Double price) {
		RecommendStat recommendStat = getRecommendStat(userId);
		recommendStat.setTotalDealPrice(recommendStat.getTotalDealPrice() + price);
		
		boolean update = update(recommendStat);
		Assert.isTrue(update);
	}

	public void aCountStat(Integer userId) {
		RecommendStat recommendStat = getRecommendStat(userId);
		recommendStat.setaCount(recommendStat.getaCount() + 1);
		
		boolean update = update(recommendStat);
		Assert.isTrue(update);
	}
	
	public void bCountStat(Integer userId) {
		RecommendStat recommendStat = getRecommendStat(userId);
		recommendStat.setbCount(recommendStat.getbCount() + 1);
		
		boolean update = update(recommendStat);
		Assert.isTrue(update);
	}
	
	public void cCountStat(Integer userId) {
		RecommendStat recommendStat = getRecommendStat(userId);
		recommendStat.setcCount(recommendStat.getcCount() + 1);
		
		boolean update = update(recommendStat);
		Assert.isTrue(update);
	}
	
	public void dCountStat(Integer userId) {
		RecommendStat recommendStat = getRecommendStat(userId);
		recommendStat.setdCount(recommendStat.getdCount() + 1);
		
		boolean update = update(recommendStat);
		Assert.isTrue(update);
	}
	
}
