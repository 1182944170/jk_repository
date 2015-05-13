package com.rpframework.module.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.module.user.dao.ICfgBankDao;
import com.rpframework.module.user.domain.CfgBank;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Service
public class CfgBankService extends BaseService {
	public @Resource ICfgBankDao cfgBankDao;
	
	public List<CfgBank> queryAllEffective() {
		Pager<CfgBank> pager = new Pager<CfgBank>();
		pager.setPageSize(Integer.MAX_VALUE);
		pager.getSearchMap().put("state", "1");
		getPager(pager);
		return pager.getItemList();
	}
	
	public Pager<CfgBank> getPager(Pager<CfgBank> pager) {
		long startTime = System.currentTimeMillis();
		List<CfgBank> itemList = cfgBankDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	public boolean doSaveOrUpdate(CfgBank cfgBank) {
		if(cfgBank == null || StringUtils.isBlank(cfgBank.getName())) {
			throw new IllegalArgumentException("add cfgBank arg error.");
		}
		
		if(NumberUtils.isValid(cfgBank.getId())) {//update
			CfgBank cfgBankDB = select(cfgBank.getId());
			Assert.notNull(cfgBankDB, "update cannot find id:" + cfgBank.getId());
			return update(cfgBank);
		} else {//insert
			return insert(cfgBank);
		}
	}
}
