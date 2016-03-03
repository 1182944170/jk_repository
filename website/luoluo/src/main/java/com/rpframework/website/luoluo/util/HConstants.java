package com.rpframework.website.luoluo.util;

public final class HConstants {
	public final static String COOKIE_LOGIN_ENCRYPT_KEY = "loginEncrypt";
	
	public static final class API {
		public static final int NO_LOGIN = 999;
	}
	
	public static class ChannelType {
		public static final int SEND_SMS_REGIST_CHANNEL_TYPE = 1;
		public static final int SEND_SMS_FORGET_PASSWORD_CHANNEL_TYPE = 2;
		public static final int SEND_SMS_CHANGE_CONTACT_CHANNEL_TYPE = 3;
		public static final int SEND_SMS_CHANGE_TAKE_CASH_CHANNEL_TYPE = 4;
	}
	// 状态
	public static class EntityState{
		/**
		 * 1  正常 状态
		 * */
		public final static int STATE_NORMAL = 1 ; 
		/**
		 * 2  禁用 状态
		 * */
		public final static int STATE_DISABLE = 2 ; 
	}
	/** 物业反馈 */
	public final static int SERVER_TYPE_1 = 1;
	/** 社区活动 */
	public final static int SERVER_TYPE_2 = 2;
	/** 服务订购 */
	public final static int SERVER_TYPE_3 = 3;
}
