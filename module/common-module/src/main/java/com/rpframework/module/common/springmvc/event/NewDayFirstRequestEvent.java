package com.rpframework.module.common.springmvc.event;

import org.springframework.context.ApplicationEvent;

import com.rpframework.module.common.springmvc.event.vo.NewDayFirstRequestVO;

/**
 * 
 * title: OrderSuccEvent.java 
 * 当天中 第一个请求事件
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月30日 下午5:48:15
 */
public class NewDayFirstRequestEvent extends ApplicationEvent {
	
	/**描述*/  
	private static final long serialVersionUID = 1238516880586204200L;

	public NewDayFirstRequestEvent(NewDayFirstRequestVO newDayFirstRequest) {
		super(newDayFirstRequest);
	}
}