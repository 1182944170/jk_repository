package com.rpframework.website.edongwang.utils;

import com.rpframework.core.utils.cache.KVObj;

public final class EConstants {
	public final static String COOKIE_LOGIN_ENCRYPT_KEY = "loginEncrypt";
	public final static class ScoreShop {
		/**
		 * scoreShop.rule.1	不限次数	map	0	0	1
			scoreShop.rule.2	一天仅可购买一次	map	0	0	2
			scoreShop.rule.3	一周仅可购买一次	map	0	0	3
			scoreShop.rule.4	一月仅可购买一次	map	0	0	4
		 */
		public final static int RULE_NO_LIMIT = 1;
		public final static int RULE_ONE_DAY = 2;
		public final static int RULE_ONE_WEEK = 3;
		public final static int RULE_ONE_MONTH = 4;
	}
	
	public static final class ScoreChannel {
		public final static KVObj DEAL_RECOMMEND = new KVObj("1", "推荐成交积分");//score.deal
		public final static KVObj DEAL_JD = new KVObj("2", "接单成交积分");//score.deal
		public final static KVObj RECOMMEND_STAR = new KVObj("3", "推荐积分");//?
		
		public final static KVObj GRAB = new KVObj("4", "抢单积分");
		public final static KVObj FINISH_PROFILE = new KVObj("5", "完善个人信息积分");//FIMXE 二级业务员
		public final static KVObj COMMENT = new KVObj("6", "评价积分");
		public final static KVObj BUY_SCORE_SHOP = new KVObj("7", "购买积分商品");
		
		public final static String 	USERSCORE_VALUECFG_HOUSEDEAL_RECOMMEND = "userScore.valueCfg.houseDealRecommend";
		public final static String 	USERSCORE_VALUECFG_HOUSEDEAL_JD = "userScore.valueCfg.houseDealJD";
		
		public final static String 	USERSCORE_VALUECFG_GRABRECOMMEND = "userScore.valueCfg.grabRecommend";
		public final static String 	USERSCORE_VALUECFG_FINISHPROFILE = "userScore.valueCfg.finishProfile";
		public final static String 	USERSCORE_VALUECFG_RECOMMEND = "userScore.valueCfg.recommend";
		public final static String 	USERSCORE_VALUECFG_PJJF = "userScore.valueCfg.pjjf";
	}
	
	public static final class API {
		public static final int NO_LOGIN = 999;
	}
	
	public static final class Recommend {
		public static final int STATE_OPEN = 1;
		public static final int STATE_DEALING = 2;
		public static final int STATE_OVER = 8;
	}
	
	public static class ChannelType {
		public static final int SEND_SMS_REGIST_CHANNEL_TYPE = 1;
		public static final int SEND_SMS_FORGET_PASSWORD_CHANNEL_TYPE = 2;
		public static final int SEND_SMS_CHANGE_CONTACT_CHANNEL_TYPE = 3;
		public static final int SEND_SMS_CHANGE_TAKE_CASH_CHANNEL_TYPE = 4;
	}
	
	public static final class Progress {
		//1-审核 2-到访 3-成交 4-佣金
		public static final int P1 = 1;
		public static final int P2 = 2;
		public static final int P3 = 3;
		public static final int P_OVER = 4;
	}
}
