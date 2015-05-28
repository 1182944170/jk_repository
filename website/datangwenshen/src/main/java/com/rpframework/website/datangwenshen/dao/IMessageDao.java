package com.rpframework.website.datangwenshen.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.datangwenshen.domain.Message;

public interface IMessageDao extends IDao {
	@SuppressWarnings("rawtypes")
	List<Message> doPager(Map message);
}
