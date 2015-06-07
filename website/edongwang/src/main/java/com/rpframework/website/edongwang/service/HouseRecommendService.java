package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rpframework.core.BaseService;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.core.utils.GsonUtils;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.dao.IHouseRecommendDao;
import com.rpframework.website.edongwang.domain.HouseRecommend;
import com.rpframework.website.edongwang.domain.HouseRecommendProgress;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.utils.EConstants;

@Service
public class HouseRecommendService extends BaseService {
	@Resource UserService userService;
	@Resource public IHouseRecommendDao recommendDao;
	@Resource HouseRecommendProgressService houseRecommendProgressService;
	@Resource UserScoreService userScoreService;
	@Resource SalesmanStatService salesmanStatService;
	@Resource RecommendStatService recommendStatService;
	
	public Pager<HouseRecommend> getPager(Pager<HouseRecommend> pager) {
		long startTime = System.currentTimeMillis();
		List<HouseRecommend> itemList = recommendDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public Pager<HouseRecommend> getOverPager(Pager<HouseRecommend> pager) {
		long startTime = System.currentTimeMillis();
		List<HouseRecommend> itemList = recommendDao.doOverPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	/**
	 * 
	 * 该业务员是否有未完成的单子
	 * @param userId
	 * @return
	 */
	public boolean hasUnfinishedRecommend(Integer userId) {
		//acceptSalesmanId
		//state=2
		
		Pager<HouseRecommend> pager = new Pager<HouseRecommend>();
		pager.getSearchMap().put("acceptSalesmanId", String.valueOf(userId));
		pager.getSearchMap().put("state", String.valueOf(EConstants.Recommend.STATE_DEALING));
		getPager(pager);
		
		return CollectionUtils.isNotEmpty(pager.getItemList());
	}
	/**
	 * 推荐
	 * @param houseRecommend
	 * @return
	 */
	public boolean addRecommend(Integer userId, Integer houseId, String customerName, String contact, Integer propertyType, Integer surfaceType, Integer totalPriceType,String areaCode,String customerInfo) {
		
		if(StringUtils.isBlank(customerName)
				||StringUtils.isBlank(contact)
				||StringUtils.isBlank(areaCode)
				||StringUtils.isBlank(customerInfo)
				||NumberUtils.isNotValid(propertyType)
				||NumberUtils.isNotValid(surfaceType)
				||NumberUtils.isNotValid(totalPriceType)
				||NumberUtils.isNotValid(houseId)
				) {
			throw new IllegalArgumentException("参数异常");
		}
		
		HouseRecommend houseRecommend = new HouseRecommend();
		houseRecommend.setAreaCode(areaCode);
		houseRecommend.setContact(contact);
		houseRecommend.setCustomerInfo(customerInfo);
		houseRecommend.setCustomerName(customerName);
		houseRecommend.setHouseId(houseId);
		houseRecommend.setPropertyType(propertyType);
		houseRecommend.setSurfaceType(surfaceType);
		houseRecommend.setTotalPriceType(totalPriceType);
		houseRecommend.setRecommendUserId(userId);
		
		houseRecommend.setState(1);
		houseRecommend.setRecordCreateTime(System.currentTimeMillis() / 1000);
		
		boolean insert = insert(houseRecommend);
		
		if(insert) {//推荐次数埋点
			recommendStatService.totalCountStat(userId);
		}
		return insert;
	}
	
	/**
	 * 抢单
	 * @param userId
	 * @param houseRecommendId
	 * @return
	 */
	public synchronized boolean grab(Integer userId, Integer houseRecommendId) {
		HouseRecommend houseRecommend = select(houseRecommendId);
		User user = userService.select(userId);
		if(houseRecommend == null || user == null) {
			throw new IllegalArgumentException("无效参数.");
		}
		
		if(houseRecommend.getState() != EConstants.Recommend.STATE_OPEN) {
			throw new IllegalArgumentException("无效的状态");
		}
		
		if(user.getIsSalesman() != 1) {
			throw new IllegalArgumentException("只有业务员才能抢单");
		}
		
		if(houseRecommend.getRecommendUserId() == userId) {
			throw new IllegalArgumentException("不能抢自己推荐的单子");
		}
		
		if(!user.getUserSalesman().getHouse().getId().equals(houseRecommend.getHouseId())) {//如果不是自己楼盘下面的则不让接单
			throw new IllegalArgumentException("不能抢不是自己楼盘下的单子");
		}
		
		JsonObject scoreExtJson = new JsonObject();
		Integer grabRecommendScore = NumberUtils.parseInt(DictionarySettingUtils.getParameterValue(EConstants.ScoreChannel.USERSCORE_VALUECFG_GRABRECOMMEND), 0);
		scoreExtJson.addProperty("houseRecommendId", houseRecommend.getId());
		scoreExtJson.addProperty("houseId", houseRecommend.getHouseId());
		scoreExtJson.addProperty("houseName", houseRecommend.getHouse().getName());
		//抢单积分给 业务员的
		userScoreService.addScore(houseRecommend.getAcceptSalesmanId(), grabRecommendScore, EConstants.ScoreChannel.GRAB, scoreExtJson.toString());
		
		houseRecommend.setState(EConstants.Recommend.STATE_DEALING);
		houseRecommend.setAcceptSalesmanId(userId);
		houseRecommend.setAcceptTime(System.currentTimeMillis() / 1000);
		boolean update = update(houseRecommend);
		if(update) {//接单次数
			salesmanStatService.grabCountStat(userId, houseRecommend.getHouseId());
		}
		return update;
	}

	
	protected void checkLimit(User user, HouseRecommend houseRecommend) {
		if(houseRecommend == null || user == null) {
			throw new IllegalArgumentException("无效参数.");
		}
		
		if(houseRecommend.getState() != EConstants.Recommend.STATE_DEALING) {
			throw new IllegalArgumentException("无效的状态.");
		}
		
		if(user.getIsSalesman() != 1) {
			throw new IllegalArgumentException("只有业务员才能操作.");
		}
		
		if(user.getId().equals(houseRecommend.getAcceptSalesmanId()) 
				|| (user.getUserSalesman().getIsLeader() == 1 && houseRecommend.getHouseId().equals(user.getUserSalesman().getHouse().getId()))) {
		} else {
			throw new IllegalArgumentException("你无权操作!");
		}
	}
	public boolean stop(Integer userId, Integer houseRecommendId, String remark) {
		HouseRecommend houseRecommend = select(houseRecommendId);
		User user = userService.select(userId);
		this.checkLimit(user, houseRecommend);
		
		if(StringUtils.isBlank(remark)) {
			remark = "";
		}
		JsonObject json = new JsonObject();
		json.addProperty("remark", remark);
		
		HouseRecommendProgress hrp = new HouseRecommendProgress();
		hrp.setHouseRecommendId(houseRecommendId);
		hrp.setRecordCreateTime(System.currentTimeMillis() /1000);
		hrp.setState(0);
		hrp.setExt(json.toString());
		
		//获取当前的进度
		List<HouseRecommendProgress> list = houseRecommend.getProgresses();
		if(CollectionUtils.isEmpty(list)) {//有效性时无效
			hrp.setType(EConstants.Progress.P1);
		} else if(list.size() == 1) {//回访时无效
			hrp.setType(EConstants.Progress.P2);
		} else if(list.size() == 2) {//成交时无效
			hrp.setType(EConstants.Progress.P3);
		} else {
			throw new IllegalArgumentException("错误的进度.");
		}
		
		houseRecommendProgressService.insert(hrp);
		
		houseRecommend.setState(EConstants.Recommend.STATE_OVER);
		boolean update = update(houseRecommend);
		if(update) {//无效次数埋点
			recommendStatService.invalidCountStat(userId);
		}
		return update;
	}
	
	/**
	 * 回访记录
	 * @param userId
	 * @param houseRecommendId
	 * @param state 回访 状态 1-有效 0-无效
	 * @param infoStar 描述星级
	 * @param intentStar 意向星级
	 * @return
	 */
	public boolean valid(Integer userId, Integer houseRecommendId, Integer intentStar, String remark) {
		HouseRecommend houseRecommend = select(houseRecommendId);
		User user = userService.select(userId);
		this.checkLimit(user, houseRecommend);
		
		if(!houseRecommend.checkNextProgressIsVaild(EConstants.Progress.P1)) {
			throw new IllegalArgumentException("进度流不正确.");
		}
		if(StringUtils.isBlank(remark)) {
			remark = "";
		}
		JsonObject json = new JsonObject();
		json.addProperty("intentStar", intentStar);
		json.addProperty("remark", remark);
		
		HouseRecommendProgress hrp = new HouseRecommendProgress();
		hrp.setHouseRecommendId(houseRecommendId);
		hrp.setRecordCreateTime(System.currentTimeMillis() /1000);
		hrp.setState(1);
		hrp.setType(EConstants.Progress.P1);
		hrp.setExt(json.toString());
		
		
		if(intentStar >= 2) {
			JsonObject scoreExtJson = new JsonObject();
			scoreExtJson.addProperty("houseRecommendId", houseRecommend.getId());
			scoreExtJson.addProperty("houseId", houseRecommend.getHouseId());
			scoreExtJson.addProperty("houseName", houseRecommend.getHouse().getName());
			
			Integer recommendScore = NumberUtils.parseInt(DictionarySettingUtils.getParameterValue(EConstants.ScoreChannel.USERSCORE_VALUECFG_RECOMMEND), 0);
			userScoreService.addScore(houseRecommend.getRecommendUserId(), recommendScore, EConstants.ScoreChannel.RECOMMEND_STAR, scoreExtJson.toString());
		}
		
		return houseRecommendProgressService.insert(hrp);
	}

	public boolean visit(Integer userId, Integer houseRecommendId, String remark) {
		HouseRecommend houseRecommend = select(houseRecommendId);
		User user = userService.select(userId);
		this.checkLimit(user, houseRecommend);
		
		if(!houseRecommend.checkNextProgressIsVaild(EConstants.Progress.P2)) {
			throw new IllegalArgumentException("进度流不正确.");
		}
		if(StringUtils.isBlank(remark)) {
			remark = "";
		}
		JsonObject json = new JsonObject();
		json.addProperty("remark", remark);
		
		HouseRecommendProgress hrp = new HouseRecommendProgress();
		hrp.setHouseRecommendId(houseRecommendId);
		hrp.setRecordCreateTime(System.currentTimeMillis() /1000);
		hrp.setState(1);
		hrp.setType(EConstants.Progress.P2);
		hrp.setExt(json.toString());
		
		return houseRecommendProgressService.insert(hrp);
	}

	public boolean deal(Integer userId, Integer houseRecommendId, Long dealTime, Double surface,Double price) {
		HouseRecommend houseRecommend = select(houseRecommendId);
		User user = userService.select(userId);
		this.checkLimit(user, houseRecommend);
		
		if(!houseRecommend.checkNextProgressIsVaild(EConstants.Progress.P3)) {
			throw new IllegalArgumentException("进度流不正确.");
		}
		JsonObject json = new JsonObject();
		json.addProperty("dealTime", dealTime);
		json.addProperty("surface", surface);
		json.addProperty("price", price);
		
		HouseRecommendProgress hrp = new HouseRecommendProgress();
		hrp.setHouseRecommendId(houseRecommendId);
		hrp.setRecordCreateTime(System.currentTimeMillis() /1000);
		hrp.setState(1);
		hrp.setType(EConstants.Progress.P3);
		hrp.setExt(json.toString());
		
		return houseRecommendProgressService.insert(hrp);
	}
	
	public boolean over(Integer houseRecommendId) {
		HouseRecommend houseRecommend = select(houseRecommendId);
		if(houseRecommend == null) {
			throw new IllegalArgumentException("无效参数.");
		}
		
		if(houseRecommend.getState() != EConstants.Recommend.STATE_DEALING) {
			throw new IllegalArgumentException("无效的状态.");
		}
		
		if(!houseRecommend.checkNextProgressIsVaild(EConstants.Progress.P_OVER)) {
			throw new IllegalArgumentException("进度流不正确.");
		}
		JsonObject json = new JsonObject();
		
		HouseRecommendProgress hrp = new HouseRecommendProgress();
		hrp.setHouseRecommendId(houseRecommendId);
		hrp.setRecordCreateTime(System.currentTimeMillis() /1000);
		hrp.setState(1);
		hrp.setType(EConstants.Progress.P_OVER);
		hrp.setExt(json.toString());
		boolean flag = update(houseRecommend);
		Assert.isTrue(flag, "结佣时更新失败.");
		
		houseRecommend.setState(EConstants.Recommend.STATE_OVER);
		if(flag) {
			flag = houseRecommendProgressService.insert(hrp);
			
			//成交积分 和推荐积分 是后台 结佣后 才增加 增加积分
			HouseRecommendProgress progressP1 = houseRecommend.getProgress(EConstants.Progress.P1);
			HouseRecommendProgress progressP3 = houseRecommend.getProgress(EConstants.Progress.P3);
			Assert.notNull(progressP1, "完结状态下找不到进度type ：" + EConstants.Progress.P1 + ":houseRecommendId:" + houseRecommendId);
			Assert.notNull(progressP3, "完结状态下找不到进度type ：" + EConstants.Progress.P3 + ":houseRecommendId:" + houseRecommendId);
			
			Integer dealScoreRecommend = NumberUtils.parseInt(DictionarySettingUtils.getParameterValue(EConstants.ScoreChannel.USERSCORE_VALUECFG_HOUSEDEAL_RECOMMEND), 0);
			Integer dealScoreJD = NumberUtils.parseInt(DictionarySettingUtils.getParameterValue(EConstants.ScoreChannel.USERSCORE_VALUECFG_HOUSEDEAL_JD), 0);
			
			JsonObject scoreExtJson = new JsonObject();
			scoreExtJson.addProperty("houseRecommendId", houseRecommend.getId());
			scoreExtJson.addProperty("houseId", houseRecommend.getHouseId());
			scoreExtJson.addProperty("houseName", houseRecommend.getHouse().getName());
			
			//成交积分给 推荐者的
			userScoreService.addScore(houseRecommend.getRecommendUserId(), dealScoreRecommend, EConstants.ScoreChannel.DEAL_RECOMMEND, scoreExtJson.toString());
			userScoreService.addScore(houseRecommend.getAcceptSalesmanId(), dealScoreJD, EConstants.ScoreChannel.DEAL_JD, scoreExtJson.toString());
			
			JsonObject jsonObjectP1 = new JsonParser().parse(progressP1.getExt()).getAsJsonObject();
			JsonObject jsonObjectP3 = new JsonParser().parse(progressP3.getExt()).getAsJsonObject();
			
			double price = GsonUtils.getInt(jsonObjectP3, "price");
			double surface = GsonUtils.getInt(jsonObjectP3, "surface");
			int infoStar = GsonUtils.getInt(jsonObjectP1, "infoStar");
			if(infoStar >= 2) {
				//推荐积分也是给 推荐者的
				Integer pjjf = NumberUtils.parseInt(DictionarySettingUtils.getParameterValue(EConstants.ScoreChannel.USERSCORE_VALUECFG_PJJF), 0);
				userScoreService.addScore(houseRecommend.getRecommendUserId(), pjjf, EConstants.ScoreChannel.COMMENT, scoreExtJson.toString());
			}
			
			//推荐者统计埋点
			//A，B，C，D 有效次数、 成交套数、成交总面积、成交总金额 埋点
			//infoStar 1-D 2-C 3-B 4-A
			if(infoStar == 1) {
				recommendStatService.dCountStat(houseRecommend.getRecommendUserId());
			} else if(infoStar == 2) {
				recommendStatService.cCountStat(houseRecommend.getRecommendUserId());
			} else if(infoStar == 3) {
				recommendStatService.bCountStat(houseRecommend.getRecommendUserId());
			} else if(infoStar == 4) {
				recommendStatService.aCountStat(houseRecommend.getRecommendUserId());
			}
			
			recommendStatService.effectiveCountStat(houseRecommend.getRecommendUserId());
			recommendStatService.dealHouseCountStat(houseRecommend.getRecommendUserId());
			recommendStatService.totalDealPriceStat(houseRecommend.getRecommendUserId(), price);
			recommendStatService.totalSurfaceStat(houseRecommend.getRecommendUserId(), surface);
			
			//业务员统计埋点
			salesmanStatService.dealCountStat(houseRecommend.getAcceptSalesmanId(), houseRecommend.getHouseId());//成交套数
			salesmanStatService.totalDealPriceStat(houseRecommend.getAcceptSalesmanId(), houseRecommend.getHouseId(), price);
			salesmanStatService.totalSurfaceStat(houseRecommend.getAcceptSalesmanId(), houseRecommend.getHouseId(), surface);
			
		}
		return flag;
	}
}
