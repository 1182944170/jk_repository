package com.rpframework.module.common.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.Notice;

public interface INoticeDao extends IDao {
	List<Notice> doPager(Map<?, ?> paramMap);
	List<Notice> queryAllEffectiveNotice();
}