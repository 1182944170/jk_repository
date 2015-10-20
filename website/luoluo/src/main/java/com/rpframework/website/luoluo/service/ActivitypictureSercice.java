package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IActivitypictureDao;
import com.rpframework.website.luoluo.domain.Activitypicture;
import com.rpframework.website.luoluo.domain.User;

@Service
public class ActivitypictureSercice extends BaseService{
	@Resource IActivitypictureDao tActivitypictureDao;
	@Resource UserService userService;
	
	public boolean updatedo(Activitypicture activitypi) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.update(activitypi);
	}

	public List<Activitypicture> selectlist(Integer activiid) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.selectole(activiid);
	}
	public Activitypicture selectone(Integer id) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.select(id);
	}

	public boolean insertdo(Activitypicture activitypi) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.insert(activitypi);
	}
	public Pager<Activitypicture> getpager(Pager<Activitypicture> pager){
		long startTime = System.currentTimeMillis();
		List<Activitypicture> list = tActivitypictureDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	/**
	 * 余额支付
	 * @param userId
	 * @param detailId
	 * @return
	 */
	public boolean bagPay(Integer userId , Integer detailId){
		User userMoney = userService.select(userId);
		if(userMoney == null){
			throw new IllegalArgumentException("不存在的用户.");
		}
		Activitypicture detail = tActivitypictureDao.select(detailId);
		if(userMoney.getPersonalMany() - detail.getMonely() >= 0){
			//改变成支付状态
			detail.setTypeMonely(2);
			tActivitypictureDao.update(detail);
			//减去余额宝金额
			userMoney.setPersonalMany(userMoney.getPersonalMany() - detail.getMonely());
			userService.update(userMoney);
	
		}
		throw new IllegalArgumentException("余额不足.");
	}
	
}
