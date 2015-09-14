package com.rpframework.module.common.plugin.jpush.spring.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.jpush.api.push.model.PushPayload;

import com.rpframework.module.common.plugin.jpush.JPushApi;
import com.rpframework.module.common.plugin.jpush.spring.event.JPushEvent;

/**
 * 
 * title: OrderPaySuccRegisterListener.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月30日 下午5:59:16
 */
@Component
public class JPushRegisterListener implements ApplicationListener<JPushEvent> {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Async
    @Override
    public void onApplicationEvent(final JPushEvent event) {
    	PushPayload pushPayload = (PushPayload) event.getSource();
    	
    	logger.info("jPush 推送信息:" + pushPayload.toJSON());
    	JPushApi.push(pushPayload, event.getAppKey());
    }
}
