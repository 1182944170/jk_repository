package com.rpframework.website.edongwang.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.google.gson.JsonObject;
import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.edongwang.dao.IUserSalesmanDao;
import com.rpframework.website.edongwang.domain.House;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.domain.UserSalesman;
import com.rpframework.website.edongwang.utils.EConstants;

@Service
public class UserSalesmanService extends BaseService {
	
	@Resource public IUserSalesmanDao userSalesmanDao;
	@Resource UserService userService;
	@Resource UserScoreService userScoreService;
	@Resource HouseRecommendService houseRecommendService;
	@Resource FileService fileService;
	@Resource HouseService houseService;
	
	public UserSalesman getUserSalesmanByUserId(Integer userId) {
		return select(userId);
	}

	public boolean doSaveOrUpdate(UserSalesman userSalesman) {
		if(userSalesman == null || NumberUtils.isNotValid(userSalesman.getUserId())
				|| StringUtils.isBlank(userSalesman.getCredentialsImg())) {
			throw new IllegalArgumentException();
		}
		
		UserSalesman userSalesmanDB = getUserSalesmanByUserId(userSalesman.getUserId());
		userSalesman.setRecordModifyTime(System.currentTimeMillis() / 10000);
		if(userSalesmanDB == null) {
			userSalesman.setRecordCreateTime(System.currentTimeMillis() / 10000);
			return insert(userSalesman);
		} else {
			userSalesman.setRecordCreateTime(userSalesmanDB.getRecordCreateTime());
			if(userSalesman.getState() == 1) {//审核成功 更新User信息
				User user = userService.select(userSalesman.getUserId());
				Assert.notNull(user);
				if(user.getIsSalesman() != 1) {
					user.setIsSalesman(1);
					boolean flag = userService.update(user);
					
					JsonObject scoreExtJson = new JsonObject();
					Integer finishProfileScore = NumberUtils.parseInt(DictionarySettingUtils.getParameterValue(EConstants.ScoreChannel.USERSCORE_VALUECFG_FINISHPROFILE), 0);
					//抢单积分给 业务员的
					userScoreService.addScore(userSalesman.getUserId(), finishProfileScore, EConstants.ScoreChannel.FINISH_PROFILE, scoreExtJson.toString());
				
					Assert.isTrue(flag);
				}
			}
			return update(userSalesman);
		}
	}

	public boolean insertOrUpdate(UserSalesman userSalesman) {
		UserSalesman userSalesmanDB = getUserSalesmanByUserId(userSalesman.getUserId());
		boolean flag = true;
		if(userSalesmanDB == null) {
			userSalesman.setRecordCreateTime(System.currentTimeMillis() / 1000);
			flag = insert(userSalesman);
		} else {//有可能审核不通过，再次提交
			userSalesman.setRecordModifyTime(System.currentTimeMillis() / 1000);
			userSalesman.setRecordCreateTime(userSalesmanDB.getRecordModifyTime());
			flag = update(userSalesman);
		}
		return flag;
	}

	public boolean changeSalesman(Integer userId, Integer newHouseId,
			String credentialsImg) {
		UserSalesman userSalesman = getUserSalesmanByUserId(userId);
		Assert.notNull(userSalesman, "找不到该二级会员的认证信息:" + userId);
		
		House newHouse = houseService.select(newHouseId);
		Assert.notNull(newHouse, "找不到该楼盘信息:" + newHouseId);
		if(userSalesman.getHouse().getId().equals(newHouseId)) {
			try {
				fileService.deleteFile(credentialsImg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			throw new IllegalArgumentException("认证的楼盘相同，无需修改!");
		}
		
		if(houseRecommendService.hasUnfinishedRecommend(userId)) {
			
			try {
				fileService.deleteFile(credentialsImg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			throw new IllegalArgumentException("该二级会员有未完成的单子，无法更改认证楼盘信息!");
		}
		
		try {
			fileService.deleteFile(userSalesman.getCredentialsImg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		userSalesman.setCredentialsImg(credentialsImg);
		userSalesman.setRecordModifyTime(System.currentTimeMillis() / 1000);
		userSalesman.setHouse(newHouse);
		
		boolean update = update(userSalesman);
		Assert.isTrue(update);
		return update;
	}
}
