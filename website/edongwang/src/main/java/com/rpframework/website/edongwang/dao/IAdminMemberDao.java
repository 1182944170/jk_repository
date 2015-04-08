package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.Member;

public interface IAdminMemberDao extends IDao{
	
	List<Member> doPager(Map<?,?> paramMap);

}
