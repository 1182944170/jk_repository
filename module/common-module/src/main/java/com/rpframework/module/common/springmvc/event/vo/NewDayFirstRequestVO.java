package com.rpframework.module.common.springmvc.event.vo;

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
public class NewDayFirstRequestVO extends Domain {
	/**描述*/  
	
	private static final long serialVersionUID = -4699632150756073128L;
	String dayFormat;
	long dayEndTimestamp;
	
	public NewDayFirstRequestVO(String dayFormat, long dayEndTimestamp) {
		this.dayEndTimestamp = dayEndTimestamp;
		this.dayFormat = dayFormat;
	}
	
	public String getDayFormat() {
		return dayFormat;
	}
	public void setDayFormat(String dayFormat) {
		this.dayFormat = dayFormat;
	}
	public long getDayEndTimestamp() {
		return dayEndTimestamp;
	}
	public void setDayEndTimestamp(long dayEndTimestamp) {
		this.dayEndTimestamp = dayEndTimestamp;
	}
}
