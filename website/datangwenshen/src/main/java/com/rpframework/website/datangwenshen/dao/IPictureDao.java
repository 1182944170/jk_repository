package com.rpframework.website.datangwenshen.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.datangwenshen.domain.Picture;


public interface IPictureDao extends IDao{
	
	@SuppressWarnings("rawtypes")
	List<Picture> doPager(Map picture);
}
