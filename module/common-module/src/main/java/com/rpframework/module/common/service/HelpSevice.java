package com.rpframework.module.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.IHelpDao;
import com.rpframework.module.common.domain.Help;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Service
public class HelpSevice extends BaseService {
	
	public @Resource IHelpDao helpDao;
	
	public Pager<Help> doPager(Pager<Help> pager) {
		long startTime = System.currentTimeMillis();
		List<Help> itemList = helpDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	public Help getHelpByaliasesTitle(String aliasesTitle) {
		return helpDao.getHelpByaliasesTitle(aliasesTitle);
	}

	public boolean doSaveOrUpdate(Help help) {
		if(help == null || StringUtils.isBlank(help.getAliasesTitle())
				|| StringUtils.isBlank(help.getTitle())
				|| StringUtils.isBlank(help.getContext())
				) {
			throw new IllegalArgumentException();
		}
		
		if(NumberUtils.isValid(help.getId())) {
			Help helpDB = select(help.getId());
			if(helpDB == null) {
				throw new IllegalArgumentException("update not exits id:" + help.getId());
			}
			
			return update(help);
		} else {
			return insert(help);
		}
	}
}
