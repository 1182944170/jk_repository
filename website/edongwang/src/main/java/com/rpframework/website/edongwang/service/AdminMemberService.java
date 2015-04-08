package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.dao.IAdminMemberDao;
import com.rpframework.website.edongwang.domain.Member;

@Service("adminMemberService")
public class AdminMemberService extends BaseService {
	
	@Resource public IAdminMemberDao adminMemberDao;
	
	public Pager<Member> getMemberPager(Pager<Member> pager) {
		long startTime = System.currentTimeMillis();
		List<Member> itemList = adminMemberDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

}
