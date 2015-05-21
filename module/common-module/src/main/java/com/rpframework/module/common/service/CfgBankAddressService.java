package com.rpframework.module.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.ICfgBankAddressDao;
import com.rpframework.module.common.domain.CfgBankAddress;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Service
public class CfgBankAddressService extends BaseService {
	public @Resource ICfgBankAddressDao cfgBankAddressDao;
	
	public Pager<CfgBankAddress> getPager(Pager<CfgBankAddress> pager) {
		long startTime = System.currentTimeMillis();
		List<CfgBankAddress> itemList = cfgBankAddressDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	public boolean doSaveOrUpdate(CfgBankAddress cfgBankAddress) {
		if(cfgBankAddress == null || StringUtils.isBlank(cfgBankAddress.getAddress()) 
				|| StringUtils.isBlank(cfgBankAddress.getCountyCode())
				|| cfgBankAddress.getCfgBank() == null) {
			throw new IllegalArgumentException("add CfgBankAddress arg error.");
		}
		
		if(NumberUtils.isValid(cfgBankAddress.getId())) {//update
			CfgBankAddress cfgBankAddressDB = select(cfgBankAddress.getId());
			Assert.notNull(cfgBankAddressDB, "update cannot find id:" + cfgBankAddress.getId());
			return update(cfgBankAddress);
		} else {//insert
			return insert(cfgBankAddress);
		}
	}

	public List<CfgBankAddress> queruAllEffective() {
		Pager<CfgBankAddress> pager = new Pager<CfgBankAddress>();
		pager.setPageSize(Integer.MAX_VALUE);
		pager.getSearchMap().put("state", "1");
		getPager(pager);
		return pager.getItemList();
	}
}
