package com.rpframework.website.xtexam.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.xtexam.domain.XTUserExamLog;

public interface IXTUserExamLogDao extends IDao {
	List<XTUserExamLog> getUserExamLog(Integer userId);

	int getUserExamLogCount(Integer userId);
}
