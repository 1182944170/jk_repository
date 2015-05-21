package com.rpframework.website.datangwenshen.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.website.datangwenshen.dao.IMessageDao;

@Service
public class MessageService extends BaseService {
	public  @Resource IMessageDao messageDao;
}
