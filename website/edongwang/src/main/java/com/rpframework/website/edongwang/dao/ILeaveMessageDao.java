package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.LeaveMessage;

public interface ILeaveMessageDao extends IDao{

	List<LeaveMessage> doPager(Map<?,?> paramMap);
}
