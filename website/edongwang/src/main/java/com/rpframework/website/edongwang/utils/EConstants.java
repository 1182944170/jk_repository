package com.rpframework.website.edongwang.utils;

import com.rpframework.core.utils.cache.KVObj;

public final class EConstants {
	
	public static final class ScoreChannel {
		public final static KVObj DEAL = new KVObj("1", "成交积分");//score.deal
		public final static KVObj RECOMMEND_STAR = new KVObj("2", "推荐积分+评价每星级积分");
		public final static KVObj GRAB = new KVObj("3", "抢单积分");
		public final static KVObj FINISH_PROFILE = new KVObj("4", "完善个人信息积分");
		public final static KVObj SHARE = new KVObj("5", "分享积分");
		public final static KVObj COMMENT = new KVObj("6", "评价信息积分");
		public final static KVObj BUY_SCORE_SHOP = new KVObj("7", "购买积分商品");
		
		public final static String 	USERSCORE_VALUECFG_FINISHPROFILE = "userScore.valueCfg.finishProfile";
		public final static String 	USERSCORE_VALUECFG_GRABRECOMMEND = "userScore.valueCfg.grabRecommend";
		public final static String 	USERSCORE_VALUECFG_HOUSEDEAL = "userScore.valueCfg.houseDeal";
		public final static String 	USERSCORE_VALUECFG_RECOMMEND = "userScore.valueCfg.recommend";
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
	}
	
	public static final class Progress {
		//1-审核 2-到访 3-成交 4-佣金
		public static final int P1 = 1;
		public static final int P2 = 2;
		public static final int P3 = 3;
		public static final int P_OVER = 4;
	}
}
