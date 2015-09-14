package com.rpframework.module.common.plugin.networksafe.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rpframework.core.InitServlet;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.module.common.plugin.networksafe.IFrequency;
import com.rpframework.module.common.plugin.networksafe.RequestFrequencyLimit;
import com.rpframework.module.common.springmvc.event.NewDayFirstRequestEvent;

/**
 * 
 * title: FrequencyLimitInterceptor.java 
 * 对于指定url的请求频率的拦截限制
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年7月3日 下午8:50:13
 */
public final class FrequencyLimitInterceptor implements HandlerInterceptor,ApplicationListener<NewDayFirstRequestEvent> {
	final Logger logger = LoggerFactory.getLogger(getClass());
	AntPathMatcher antPathMatcher = null;
	transient Map<String, RequestFrequencyLimit> map;
	transient ListOrderedMap frequencyMap; //<String, IFrequency>
	
	private void init() {//初始化
		antPathMatcher = new AntPathMatcher();
		frequencyMap = new ListOrderedMap();
		map = new ConcurrentHashMap<String, RequestFrequencyLimit>();
		
		Map<String, IFrequency> tempMap = new HashMap<String, IFrequency>();
		List<String> keyList = new ArrayList<String>();
		Map<String, IFrequency> beans = SpringUtils.getApplicationContext().getBeansOfType(IFrequency.class);
		
		for(Map.Entry<String, IFrequency> entry: beans.entrySet()) {
			String[] followPath = entry.getValue().getFollowPath();
			for (String string : followPath) {
				keyList.add(string);
				tempMap.put(string, entry.getValue());
			}
		}
		
		Collections.sort(keyList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		});
		
		for (String string : keyList) {
			IFrequency iFrequency = tempMap.get(string);
			RequestFrequencyLimit requestFrequencyLimit = iFrequency.getRequestFrequencyLimit();
			logger.info("注册频率拦截uri:{},拦截频率:{}", string, requestFrequencyLimit.limitRolesToString());
			frequencyMap.put(InitServlet.CTX + string, iFrequency);
		}
		
	}
	
	private boolean containsURI(String cfg, String uri) {
		if(cfg.equals(uri)) {
			return true;
		} else {
			return antPathMatcher.match(cfg, uri);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(frequencyMap == null) {
			init();
		}
		
		String uri = request.getRequestURI();
		Set<String> keySet = frequencyMap.keySet();
		for (String cfg : keySet) {
			if(containsURI(cfg, uri)) {// 找到了.
				IFrequency frequency = (IFrequency) frequencyMap.get(cfg);
				String followKey = frequency.getFollowKey(request);
				RequestFrequencyLimit requestFrequencyLimit = null;
				
				if(map.containsKey(followKey)) {
					requestFrequencyLimit = map.get(followKey);
				} else {
					requestFrequencyLimit = frequency.getRequestFrequencyLimit();
					map.put(followKey, requestFrequencyLimit);
				}
				
				requestFrequencyLimit.recordAndCheck(cfg);
				break;
			}
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	public static void main(String[] args) {
		String url = "/api/user/userdata/query";
		FrequencyLimitInterceptor ll = new FrequencyLimitInterceptor();
		ll.antPathMatcher = new AntPathMatcher();
		System.out.println(ll.containsURI( "/api/user/userdata/**", url));
		System.out.println(ll.containsURI( "/api/user/userdata", url));
		System.out.println(ll.containsURI( "/api/user/userdata", url));
		System.out.println(ll.containsURI( "/api/user/userdata", url));
	}

	@Override
	public void onApplicationEvent(NewDayFirstRequestEvent event) {
		if(this.map != null)
			this.map.clear();
	}

}
