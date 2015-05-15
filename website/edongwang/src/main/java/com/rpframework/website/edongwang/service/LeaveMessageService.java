package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.dao.ILeaveMessageDao;
import com.rpframework.website.edongwang.domain.LeaveMessage;

@Service
public class LeaveMessageService extends BaseService {
	
	@Resource public ILeaveMessageDao leaveMessageDao;
	
	public Pager<LeaveMessage> getPager(Pager<LeaveMessage> pager) {
		long startTime = System.currentTimeMillis();
		List<LeaveMessage> itemList = leaveMessageDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
}