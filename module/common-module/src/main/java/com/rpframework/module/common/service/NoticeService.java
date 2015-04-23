package com.rpframework.module.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.INoticeDao;
import com.rpframework.module.common.domain.Notice;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Service
public class NoticeService extends BaseService {
	@Resource public INoticeDao noticeDao;
	
	public Pager<Notice> getNoticePager(Pager<Notice> pager) {
		long startTime = System.currentTimeMillis();
		List<Notice> itemList = noticeDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	public List<Notice> queryAllEffectiveNotice() {
		return noticeDao.queryAllEffectiveNotice();
	}

	public boolean doSaveOrUpdate(Notice notice) {
		if(notice == null || StringUtils.isBlank(notice.getTitle()) || StringUtils.isBlank(notice.getContent())) {
			throw new IllegalArgumentException("add Notice arg error.");
		}
		
		if(NumberUtils.isValid(notice.getId())) {//update
			Notice noticeDB = noticeDao.select(notice.getId());
			Assert.notNull(noticeDB, "更新时找不到ID:" + notice.getId());
			return noticeDao.update(notice);
		} else {//insert
			notice.setRecoreCreateTime(System.currentTimeMillis() / 1000);
			return noticeDao.insert(notice);
		}
	}
}
