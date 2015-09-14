package com.rpframework.module.common.plugin.networksafe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.rpframework.core.utils.LimitQueue;
import com.rpframework.module.common.plugin.networksafe.exception.RequestFrequencyLimitException;

/**
 * 
 * title: RequestFrequencyLimit.java 
 * 请求频率
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年7月3日 下午5:55:06
 */
public class RequestFrequencyLimit {
	
	LimitQueue<Long> frequencys;
	List<LimitRule> limitRoles;
	
	public RequestFrequencyLimit(){
		limitRoles = new ArrayList<LimitRule>();
	}
	
	public String limitRolesToString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("规则个数").append(limitRoles.size());
		
		for (LimitRule lr : limitRoles) {
			buffer.append(lr.toString());
		}
		
		return buffer.toString();
	}
	
	
	public RequestFrequencyLimit addLimitRule(int intervalTime, int count) {
		return addLimitRule(intervalTime, count, 2);
	}
	public RequestFrequencyLimit addLimitRule(int intervalTime, int count, int orverThresholdWaitTime) {
		Assert.isTrue(count > 1, "出现频率不能小于1.");
		limitRoles.add(new LimitRule(intervalTime, count, orverThresholdWaitTime));
		return this;
	}
	
	LimitQueue<Long> getFrequencys() {
		if(frequencys == null) {
			int maxCount = 0;
			for (LimitRule lr : limitRoles) {
				if(lr.count > maxCount) {
					maxCount = lr.count;
				}
			}
			
			frequencys = new LimitQueue<Long>(maxCount);
		}
		
		return frequencys;
	}
	
	public void recordAndCheck(String key) {
		LimitQueue<Long> limitQueue = getFrequencys();
		Long currTime = System.currentTimeMillis() / 1000;
		
	
		System.out.println("limitQueueSize:" + limitQueue.size() + ";limitQueue.limit:" + (limitQueue.getLimit() - 1) + "->" + currTime);
		if(limitQueue.size() < limitQueue.getLimit() - 1) {//为TA分配的队列没满
			System.out.println(1 + "->" + currTime);
		} else {
			System.out.println(1 + "->" + currTime);
			
			Long[] limitArray = new Long[limitQueue.size() + 1];
			limitQueue.toArray(limitArray);
			//将最新的放到最后
			limitArray[limitArray.length - 1] = currTime;
			
			int orverThresholdCount = 0;//有一条规则验证没有超过阀值 则返回true
			for (LimitRule lr : limitRoles) {
				boolean orverThreshold = isOrverThreshold(limitArray, lr);
				if(orverThreshold) {//如果单个超过阀值，判断
					orverThresholdCount ++;
					
					if(limitArray[limitArray.length - 2] == null) {
						System.out.println("=========================>>>>>>>>>>>>>0000000");
					} else {
						long queueLastUpdateTime = limitArray[limitArray.length - 2];
						if(currTime - queueLastUpdateTime < lr.orverThresholdWaitTime) {
							throw new RequestFrequencyLimitException(key+":" + lr.toString() + ",阀值等待时间" + lr.orverThresholdWaitTime);
						}
					}
				}
			}
			
			if(orverThresholdCount == limitRoles.size()) {//全部阀值都已经触发,抛出异常
				throw new RequestFrequencyLimitException(key);
			}
		}
		
		limitQueue.offer(currTime);//将本次访问的时间戳放如堆栈中
	}

	/**
	 * 是否超过阀值 
	 * @param limitArray
	 * @param lr
	 * @return
	 */
	public boolean isOrverThreshold(Long[] limitArray, LimitRule lr) {
		//最后一个是最新的,切记
		//首先
//		if(limitArray == null || lr == null) {
//			System.out.println(0);
//		}
//		
		if(limitArray[limitArray.length - 1] == null || limitArray[0] == null) {
			System.out.println("=========================>>>>>>>>>>>>>");
			return false;
		}
		long timeDiff = limitArray[limitArray.length - 1] - limitArray[0]; //计算真实这个阀值的时间差//单位秒
		if(timeDiff < lr.intervalTime) {//超过阀值
			return true;
		}
		
		return false;
	}
}

class LimitRule {
	LimitRule(int intervalTime, int count) {
		this(intervalTime, count, 2);
	}
	
	LimitRule(int intervalTime, int count, int orverThresholdWaitTime) {
		this.intervalTime = intervalTime;
		this.count = count;
		this.orverThresholdWaitTime = orverThresholdWaitTime;
	}
	
	int intervalTime;
	int count;
	int orverThresholdWaitTime;
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[").append(this.intervalTime).append("秒时间内请求次数不能超过 ").append(this.count).append("次").append(",超过阀值等待时间:").append(orverThresholdWaitTime).append("秒]");
		return buffer.toString();
	}
}