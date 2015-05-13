package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.gson.JsonObject;
import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.dao.IHouseRecommendDao;
import com.rpframework.website.edongwang.domain.HouseRecommend;
import com.rpframework.website.edongwang.domain.HouseRecommendProgress;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.exception.APICodeException;
import com.rpframework.website.edongwang.utils.EConstants;

@Service
public class HouseRecommendService extends BaseService {
	@Resource UserService userService;
	@Resource public IHouseRecommendDao recommendDao;
	@Resource HouseRecommendProgressService houseRecommendProgressService;
	
	public Pager<HouseRecommend> getPager(Pager<HouseRecommend> pager) {
		long startTime = System.currentTimeMillis();
		List<HouseRecommend> itemList = recommendDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	/**
	 * 抢单
	 * @param userId
	 * @param houseRecommendId
	 * @return
	 */
	public boolean grab(Integer userId, Integer houseRecommendId) {
		HouseRecommend houseRecommend = select(houseRecommendId);
		User user = userService.select(userId);
		if(houseRecommend == null || user == null) {
			throw new APICodeException(-1, "无效参数.");
		}
		
		if(houseRecommend.getState() == EConstants.Recommend.STATE_OPEN) {
			throw new APICodeException(-2, "无效的状态.");
		}
		
		if(user.getIsSalesman() != 1) {
			throw new APICodeException(-3, "只有业务员才能抢单.");
		}
		
		houseRecommend.setState(EConstants.Recommend.STATE_DEALING);
		houseRecommend.setAcceptSalesmanId(userId);
		houseRecommend.setAcceptTime(System.currentTimeMillis() / 1000);
		return update(houseRecommend);
	}

	/**
	 * 
	 * 回访记录
	 * @param userId
	 * @param houseRecommendId
	 * @param state 回访 状态 1-有效 0-无效
	 * @param infoStar 描述星级
	 * @param intentStar 意向星级
	 * @return
	 */
	public boolean valid(Integer userId, Integer houseRecommendId, Integer state, Integer infoStar, Integer intentStar, String remark) {
		HouseRecommend houseRecommend = select(houseRecommendId);
		User user = userService.select(userId);
		if(houseRecommend == null || user == null || !userId.equals(houseRecommend.getRecommendUserId())) {
			throw new APICodeException(-1, "无效参数.");
		}
		
		if(houseRecommend.getState() != EConstants.Recommend.STATE_DEALING) {
			throw new APICodeException(-2, "无效的状态.");
		}
		
		if(user.getIsSalesman() != 1) {
			throw new APICodeException(-3, "只有业务员才能回访.");
		}
		
		if(!houseRecommend.checkNextProgressIsVaild(EConstants.Progress.P1)) {
			throw new APICodeException(-4, "进度流不正确.");
		}
		JsonObject json = new JsonObject();
		json.addProperty("infoStar", infoStar);
		json.addProperty("intentStar", intentStar);
		json.addProperty("remark", remark);
		
		HouseRecommendProgress hrp = new HouseRecommendProgress();
		hrp.setHouseRecommendId(houseRecommendId);
		hrp.setRecordCreateTime(System.currentTimeMillis() /1000);
		hrp.setState(state);
		hrp.setType(EConstants.Progress.P1);
		hrp.setExt(json.toString());
		
		return houseRecommendProgressService.insert(hrp);
	}

	public boolean visit(Integer userId, Integer houseRecommendId, Integer state, String remark) {
		HouseRecommend houseRecommend = select(houseRecommendId);
		User user = userService.select(userId);
		if(houseRecommend == null || user == null || !userId.equals(houseRecommend.getRecommendUserId())) {
			throw new APICodeException(-1, "无效参数.");
		}
		
		if(houseRecommend.getState() != EConstants.Recommend.STATE_DEALING) {
			throw new APICodeException(-2, "无效的状态.");
		}
		
		if(user.getIsSalesman() != 1) {
			throw new APICodeException(-3, "只有业务员才能回访.");
		}
		
		if(!houseRecommend.checkNextProgressIsVaild(EConstants.Progress.P2)) {
			throw new APICodeException(-4, "进度流不正确.");
		}
		JsonObject json = new JsonObject();
		json.addProperty("remark", remark);
		
		HouseRecommendProgress hrp = new HouseRecommendProgress();
		hrp.setHouseRecommendId(houseRecommendId);
		hrp.setRecordCreateTime(System.currentTimeMillis() /1000);
		hrp.setState(state);
		hrp.setType(EConstants.Progress.P2);
		hrp.setExt(json.toString());
		
		return houseRecommendProgressService.insert(hrp);
	}
	
	public boolean deal(Integer userId, Integer houseRecommendId, Integer state, Long dealTime, Double surface, Double price, Double commissionPrice) {
		HouseRecommend houseRecommend = select(houseRecommendId);
		User user = userService.select(userId);
		if(houseRecommend == null || user == null || !userId.equals(houseRecommend.getRecommendUserId())) {
			throw new APICodeException(-1, "无效参数.");
		}
		
		if(houseRecommend.getState() != EConstants.Recommend.STATE_DEALING) {
			throw new APICodeException(-2, "无效的状态.");
		}
		
		if(user.getIsSalesman() != 1) {
			throw new APICodeException(-3, "只有业务员才能成交.");
		}
		
		if(!houseRecommend.checkNextProgressIsVaild(EConstants.Progress.P3)) {
			throw new APICodeException(-4, "进度流不正确.");
		}
		JsonObject json = new JsonObject();
		json.addProperty("dealTime", dealTime);
		json.addProperty("surface", dealTime);
		json.addProperty("price", dealTime);
		json.addProperty("commissionPrice", dealTime);
		
		HouseRecommendProgress hrp = new HouseRecommendProgress();
		hrp.setHouseRecommendId(houseRecommendId);
		hrp.setRecordCreateTime(System.currentTimeMillis() /1000);
		hrp.setState(state);
		hrp.setType(EConstants.Progress.P3);
		hrp.setExt(json.toString());
		
		return houseRecommendProgressService.insert(hrp);
	}
	
	public boolean over(Integer userId, Integer houseRecommendId, Integer state) {
		HouseRecommend houseRecommend = select(houseRecommendId);
		User user = userService.select(userId);
		if(houseRecommend == null || user == null || !userId.equals(houseRecommend.getRecommendUserId())) {
			throw new APICodeException(-1, "无效参数.");
		}
		
		if(houseRecommend.getState() != EConstants.Recommend.STATE_DEALING) {
			throw new APICodeException(-2, "无效的状态.");
		}
		
		if(user.getIsSalesman() != 1) {
			throw new APICodeException(-3, "只有业务员才能结佣.");
		}
		
		if(!houseRecommend.checkNextProgressIsVaild(EConstants.Progress.P_OVER)) {
			throw new APICodeException(-4, "进度流不正确.");
		}
		JsonObject json = new JsonObject();
		
		HouseRecommendProgress hrp = new HouseRecommendProgress();
		hrp.setHouseRecommendId(houseRecommendId);
		hrp.setRecordCreateTime(System.currentTimeMillis() /1000);
		hrp.setState(state);
		hrp.setType(EConstants.Progress.P_OVER);
		hrp.setExt(json.toString());
		
		houseRecommend.setState(EConstants.Recommend.STATE_OVER);
		boolean flag = update(houseRecommend);
		Assert.isTrue(flag, "结佣时更新失败.");
		if(flag) {
			flag = houseRecommendProgressService.insert(hrp);
		}
		return flag;
	}
}
