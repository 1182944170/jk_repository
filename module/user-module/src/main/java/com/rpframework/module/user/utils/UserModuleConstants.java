package com.rpframework.module.user.utils;

public class UserModuleConstants {
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
}
