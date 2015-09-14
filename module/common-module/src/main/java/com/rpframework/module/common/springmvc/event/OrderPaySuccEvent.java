package com.rpframework.module.common.springmvc.event;

import org.springframework.context.ApplicationEvent;

import com.rpframework.module.common.springmvc.event.vo.OrderPaySuccVO;

/**
 * 
 * title: OrderSuccEvent.java 
 * 订单充值成功事件
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月30日 下午5:48:15
 */
public class OrderPaySuccEvent extends ApplicationEvent {
	
	/**描述*/  
	private static final long serialVersionUID = 1238516880586204200L;

	public OrderPaySuccEvent(OrderPaySuccVO orderPaySuccVO) {
		super(orderPaySuccVO);
	}
}