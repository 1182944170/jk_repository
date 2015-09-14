package com.rpframework.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.rpframework.utils.Pager;

public class BaseService implements IService {
	public final Logger logger = LoggerFactory.getLogger(getClass());
	private IDao dao;
	
	@SuppressWarnings("rawtypes")
	public Map packageMyBatisParam(Pager<?> pager) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("page", pager);
		return m;
	}
	
	protected IDao getIDao() {
		if(dao == null) {
			try {
				Field[] declaredFields = this.getClass().getDeclaredFields();
				for (Field field : declaredFields) {
					if(Modifier.isPublic(field.getModifiers()) || Modifier.isProtected(field.getModifiers())) { //按照约定 Service的Dao 是public的 或者 protected
						if(field.get(this) instanceof IDao) {
							this.dao = (IDao) field.get(this);
						}
					}
				}
				
				Assert.notNull(dao, "找不到dao属性!");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} 
		}
		
		return dao;
	}
	
	@Override
	public <T> T select(Object idObj) {
		return getIDao().select(idObj);
	}
	@Override
	public <T> boolean update(T t) {
		return getIDao().update(t);
	}
	@Override
	public boolean delete(Object idObj) {
		return getIDao().delete(idObj);
	}
	@Override
	public <T> boolean insert(T t) {
		return getIDao().insert(t);
	}
}
