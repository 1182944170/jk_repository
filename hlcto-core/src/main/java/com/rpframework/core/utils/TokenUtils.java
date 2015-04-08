package com.rpframework.core.utils;

import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.util.Assert;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class TokenUtils {
	private String privateKey = "fdas34ljfr好sja@#8$%dfkl;js&4*daklfjsdl;akfjsa342";

	public TokenUtils(String privateKey) {
		Assert.notNull(privateKey, "密钥不能为nil.");
		this.privateKey = privateKey;
	}
	public String getToken(String password, String date) {
		return Hashing.md5().newHasher().putString(password, Charsets.UTF_8)
				.putString(privateKey, Charsets.UTF_8)
				.putString(date, Charsets.UTF_8).hash().toString();
	}

	public String getToken(String password, Date date) {
		return Hashing.md5().newHasher().putString(password, Charsets.UTF_8)
				.putString(privateKey, Charsets.UTF_8)
				.putString(getDate(date), Charsets.UTF_8).hash().toString();
	}

	public String getToken(String password) {
		return Hashing.md5().newHasher().putString(password, Charsets.UTF_8)
				.putString(privateKey, Charsets.UTF_8)
				.putString(getDate(), Charsets.UTF_8).hash().toString();
	}

	public boolean validToken(String token, String password) {
		String confirm = getToken(password);
		if (confirm.equals(token)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getDate() {
		Date date = new Date(System.currentTimeMillis());
		return getDate(date);
	}

	public static String getNextHour(Date now) {
		Date date = new Date(now.getTime() + 60 * 60 * 1000);
		return getDate(date);
	}
	
	public static String getDate(Date now) {
		return FastDateFormat.getInstance("yyyyMMddHH").format(now);
	}
}
