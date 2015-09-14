package com.rpframework.module.common.pay.bill99;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Bill99Api {
	public static String appendParam(String returns, String paramId, String paramValue) {
		if (returns != "") {
			if (paramValue != "") {

				returns += "&" + paramId + "=" + paramValue;
			}

		} else {

			if (paramValue != "") {
				returns = paramId + "=" + paramValue;
			}
		}

		return returns;
	}
	
	public static String appendParam2(String returns, String paramId, String paramValue) throws UnsupportedEncodingException {
		paramValue = URLEncoder.encode(paramValue, "utf-8");
		if (returns != "") {
			if (paramValue != "") {
				
				returns += "&" + paramId + "=" + paramValue;
			}
			
		} else {
			
			if (paramValue != "") {
				returns = paramId + "=" + paramValue;
			}
		}
		
		return returns;
	}
}
