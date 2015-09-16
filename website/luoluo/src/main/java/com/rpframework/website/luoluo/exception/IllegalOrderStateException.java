package com.rpframework.website.luoluo.exception;



@SuppressWarnings("serial")
public class IllegalOrderStateException extends IllegalArgumentException {
	int currProgress = 0;
	int verifyProgress = 0;
	public IllegalOrderStateException(String msg, int currProgress, int verifyProgress) {
		super(msg);
		
		this.currProgress = currProgress;
		this.verifyProgress = verifyProgress;
	}
	public IllegalOrderStateException(int currProgress, int verifyProgress) {
		this("订单状态非法.订单当前状态:" + currProgress + ", 验证状态:" + verifyProgress, currProgress, verifyProgress);
	}
	public int getCurrProgress() {
		return currProgress;
	}
	public void setCurrProgress(int currProgress) {
		this.currProgress = currProgress;
	}
	public int getVerifyProgress() {
		return verifyProgress;
	}
	public void setVerifyProgress(int verifyProgress) {
		this.verifyProgress = verifyProgress;
	}
}
