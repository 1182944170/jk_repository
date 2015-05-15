package com.rpframework.module.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.gson.JsonObject;
import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.core.utils.cache.KVObj;
import com.rpframework.module.user.dao.IScoreShopDao;
import com.rpframework.module.user.domain.ScoreShop;
import com.rpframework.module.user.utils.UserModuleConstants;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Service
public class ScoreShopService extends BaseService {
	public @Resource IScoreShopDao scoreShopDao;
	@Resource FileService fileService;
	@Resource UserScoreShopLogService scoreShopLogService;
	@Resource UserScoreService userScoreService;
	
	public Pager<ScoreShop> getPager(Pager<ScoreShop> pager) {
		long startTime = System.currentTimeMillis();
		List<ScoreShop> itemList = scoreShopDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public boolean doSaveOrUpdate(ScoreShop scoreShop) {
		if(scoreShop == null || StringUtils.isBlank(scoreShop.getName())) {
			throw new IllegalArgumentException();
		}
		
		if(NumberUtils.isValid(scoreShop.getId())) { //update
			ScoreShop scoreShopDB = select(scoreShop.getId());
			Assert.notNull(scoreShopDB);
			scoreShop.setRecordCreateTime(scoreShopDB.getRecordCreateTime());
			scoreShop.setSalesNumber(scoreShopDB.getSalesNumber());
			
			if(StringUtils.isBlank(scoreShop.getImg())) {
				scoreShop.setImg(scoreShopDB.getImg());
			} else if(!StringUtils.equals(scoreShop.getImg(), scoreShopDB.getImg())) {//删除之前的 img
				try {
					fileService.deleteFile(scoreShopDB.getImg());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return update(scoreShop);
		} else {//insert
			
			if(StringUtils.isBlank(scoreShop.getImg())) {
				throw new IllegalArgumentException("新增情况下需上传icon.");
			}
			scoreShop.setRecordCreateTime(System.currentTimeMillis() / 1000);
			scoreShop.setSalesNumber(0);
			return insert(scoreShop);
		}
	}

	public boolean checkScoreShopRule(ScoreShop scoreShop, Integer userId) {
		if(scoreShop.getRule() == UserModuleConstants.ScoreShop.RULE_NO_LIMIT) {
			return true;
		} else if(scoreShop.getRule() == UserModuleConstants.ScoreShop.RULE_ONE_DAY) {
			long startTime = DateUtils.getTodayStartDate().getTime() / 1000;
			long endTime = DateUtils.getTodayEndDate().getTime() / 1000;
			
			return !scoreShopLogService.hasBuyScoreShop(startTime, endTime, scoreShop.getId(), userId);
		} else if(scoreShop.getRule() == UserModuleConstants.ScoreShop.RULE_ONE_WEEK) {
			long startTime = DateUtils.getWeekStartDate().getTime() / 1000;
			long endTime = DateUtils.getWeekEndDate().getTime() / 1000;
			
			return !scoreShopLogService.hasBuyScoreShop(startTime, endTime, scoreShop.getId(), userId);
		} else if(scoreShop.getRule() == UserModuleConstants.ScoreShop.RULE_ONE_MONTH) {
			long startTime = DateUtils.getMonthStartDate().getTime() / 1000;
			long endTime = DateUtils.getMonthEndDate().getTime() / 1000;
			
			return !scoreShopLogService.hasBuyScoreShop(startTime, endTime, scoreShop.getId(), userId);
		}
		throw new IllegalArgumentException("识别不了的商品验证规则:" + scoreShop.getRule());
	}

	public boolean buy(Integer scoreShopId, Integer userId, String name, String concact, String areaCode, String address, KVObj kvObj) {
		if(StringUtils.isBlank(name) || StringUtils.isBlank(concact) || StringUtils.isBlank(areaCode) || StringUtils.isBlank(address)) {
			throw new IllegalArgumentException("error arg.");
		}
		ScoreShop scoreShop = select(scoreShopId);
		Assert.notNull(scoreShop, "不存在的商品:" + scoreShopId);
		
		if(scoreShop.getStockNumber() != -1) {//验证库存
			Assert.isTrue(scoreShop.getStockNumber() > 0, "库存不足");
			scoreShop.setStockNumber(scoreShop.getStockNumber() - 1);
		}
		
//		boolean checkScoreShopRule = checkScoreShopRule(scoreShop, userId);
//		Assert.isTrue(checkScoreShopRule, "商品购买规则验证不通过!");
		
		boolean checkCanCostScore = userScoreService.checkCanCostScore(userId, scoreShop.getScore());
		Assert.isTrue(checkCanCostScore, "积分不够");
		
		scoreShopLogService.addScoreShopLog(scoreShopId, userId, name, concact, areaCode, address);
		JsonObject scoreExtJson = new JsonObject();
		scoreExtJson.addProperty("scoreShopId", scoreShop.getId());
		scoreExtJson.addProperty("scoreShopName", scoreShop.getName());
		boolean flag = userScoreService.costScore(userId, scoreShop.getScore(), kvObj, scoreExtJson.toString());
		
		scoreShop.setSalesNumber(scoreShop.getSalesNumber() + 1);
		update(scoreShop);
		return flag;
	}
}
