package com.rpframework.module.common.springmvc.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rpframework.core.InitServlet;
import com.rpframework.core.event.IModuleEvent;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.module.common.springmvc.event.NewDayFirstRequestEvent;
import com.rpframework.module.common.springmvc.event.vo.NewDayFirstRequestVO;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.DateUtils;

public class CommonInterceptor implements HandlerInterceptor {
	final Logger logger = LoggerFactory.getLogger(getClass());
	boolean isFirstReq = true;
	long currDayEndTimestamp; //当前时间 20150606
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long currTimestamp = System.currentTimeMillis() / 1000;
		if(currDayEndTimestamp == 0 || currTimestamp > currDayEndTimestamp) {//过了今天的时间，就是 第二天了
			
			currDayEndTimestamp = DateUtils.getTodayEndDate().getTime() / 1000;
			NewDayFirstRequestVO newDayFirstRequestVO = new NewDayFirstRequestVO(DateUtils.nowDate(DateUtils.YYYYMMDD), currDayEndTimestamp);
			logger.info("NewDayFirstRequestVO 事件:" + ToStringBuilder.reflectionToString(newDayFirstRequestVO, ToStringStyle.SIMPLE_STYLE));
			SpringUtils.publishEvent(new NewDayFirstRequestEvent(newDayFirstRequestVO));
		}
		
		
		if(isFirstReq) {
			isFirstReq = !isFirstReq;
			logger.info("第一次Request事件触发!");
			InitServlet.DOMAIN = TagUtils.getDomain(request);
			//发送第一次请求事件
			SpringUtils.getServletContext().setAttribute("domain", InitServlet.DOMAIN);

			Map<String, IModuleEvent> beans = SpringUtils.getApplicationContext().getBeansOfType(IModuleEvent.class);
			if(CollectionUtils.isNotEmpty(beans)) {
				IModuleEvent moduleEvent = null;
				for(String keys : beans.keySet()) {
					moduleEvent = beans.get(keys);
					moduleEvent.fisrtRequset(request, response);
				}
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

}
