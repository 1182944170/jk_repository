package com.rpframework.module.common.springmvc.event.vo;

import com.google.gson.JsonObject;
import com.rpframework.core.Domain;

/**
 * 
 * title: OrderPaySuccVO.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月30日 下午5:52:13
 */
public class OrderPaySuccVO extends Domain {
	/**描述*/  
	
	private static final long serialVersionUID = -4699632150756073128L;

	Long orderId;
	Double price;
	boolean succ;
	String payChannel;
	JsonObject retInfo;
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public boolean isSucc() {
		return succ;
	}
	public void setSucc(boolean succ) {
		this.succ = succ;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public JsonObject getRetInfo() {
		return retInfo;
	}
	public void setRetInfo(JsonObject retInfo) {
		this.retInfo = retInfo;
	}
}
