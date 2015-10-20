package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IBankcardExcutionDao;
import com.rpframework.website.luoluo.domain.BankcardExcution;
import com.rpframework.website.luoluo.domain.User;

@Service
public class BankcardExcutionService  extends BaseService{
		@Resource UserService userService;
		@Resource IBankcardExcutionDao iBankcardExcutionDao;
		
		//查询提现信息
		public Pager<BankcardExcution> getPager(Pager<BankcardExcution> pager){
			long startTime = System.currentTimeMillis();
			List<BankcardExcution> list = iBankcardExcutionDao.doPager(this.packageMyBatisParam(pager));
			pager.setItemList(list);
			pager.setCostTime(System.currentTimeMillis() - startTime);
			return pager;
		}
		
		
		//判断余额
	public boolean bagPay(User user, double extractionMonly) {
		if(user.getPersonalMany()-extractionMonly >= 0){
			user.setPersonalMany(user.getPersonalMany() - extractionMonly);
			return userService.updatedo(user);
		}
		throw new IllegalArgumentException("余额不足.");
	}
	
	public boolean insertdo(BankcardExcution bank) {
		return iBankcardExcutionDao.insert(bank);
		
	}


	public BankcardExcution selectone(Integer uid) {
		
		return iBankcardExcutionDao.select(uid);
	}


	public boolean updatedo(BankcardExcution bfgl) {
		return iBankcardExcutionDao.update(bfgl);
		
	}

}
