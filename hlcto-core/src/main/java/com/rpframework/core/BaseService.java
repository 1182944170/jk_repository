package com.rpframework.core;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rpframework.utils.Pager;

public class BaseService {
	public final Logger logger = LoggerFactory.getLogger(getClass());
	@SuppressWarnings("rawtypes")
	public Map packageMyBatisParam(Pager<?> pager) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("page", pager);
		return m;
	}
}
