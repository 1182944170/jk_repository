package com.rpframework.website.datangwenshen.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.datangwenshen.dao.IMessageDao;
import com.rpframework.website.datangwenshen.domain.Message;

@Service
public class MessageService extends BaseService {
	public  @Resource IMessageDao messageDao;
	public Pager<Message> getPager(Pager<Message> pager) {
		long startTime = System.currentTimeMillis();
		List<Message> list = messageDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	public boolean createMessage(Message message){
		message.setRecordCreateTime(System.currentTimeMillis() / 1000);
	    return insert(message);
	    
	}

}