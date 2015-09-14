package com.rpframework.module.adminbase.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.adminbase.dao.IAdminMenuDao;

@Service("adminMenuService")
public class AdminMenuService extends BaseService {

	@Resource public IAdminMenuDao adminMenuDao;
}