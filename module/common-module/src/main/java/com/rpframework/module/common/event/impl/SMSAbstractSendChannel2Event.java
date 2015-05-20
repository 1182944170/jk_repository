package com.rpframework.module.common.event.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rpframework.core.utils.MD5;
import com.rpframework.module.common.event.ISMSEvent;

public abstract class SMSAbstractSendChannel2Event implements ISMSEvent {
	final Logger logger = LoggerFactory.getLogger(getClass());
	protected String smsSvcUrl = "http://121.207.232.78:8860";
	protected String cust_code = "478037";
	protected String password = "fh478037";
	protected String sp_code = "106901278011021";
	
	@Override
	public int getChannelSend() {
		return 2;
	}

	
	public String sendSms(String mobiles, String content) throws IOException {
		return sendSms(mobiles, content, sp_code, 0);
	}

	public String sendSms(String mobiles, String content, long task_id)
			throws IOException {
		return sendSms(mobiles, content, sp_code, task_id);
	}

	public String sendSms(String mobiles, String content, String sp_code)
			throws IOException {
		return sendSms(mobiles, content, sp_code, 0);
	}

	public String sendSms(String mobiles, String content, String sp_code,
			long task_id) throws IOException {
		String urlencContent = URLEncoder.encode(content,"utf-8");
        String sign=MD5.sign(urlencContent, password, "utf-8");
		String postData = "content=" + urlencContent + "&destMobiles="
				+ mobiles + "&sign=" + sign + "&cust_code=" + cust_code
				+ "&sp_code=" + sp_code + "&task_id=" + task_id;
		logger.info(postData);
		URL myurl = new URL(smsSvcUrl);
		URLConnection urlc = myurl.openConnection();
		urlc.setReadTimeout(1000 * 30);
		urlc.setDoOutput(true);
		urlc.setDoInput(true);
		urlc.setAllowUserInteraction(false);

		DataOutputStream server = new DataOutputStream(urlc.getOutputStream());
		//System.out.println("发送数据=" + postData);

		server.write(postData.getBytes("utf-8"));
		server.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				urlc.getInputStream(), "utf-8"));
		String resXml = "", s = "";
		while ((s = in.readLine()) != null)
			resXml = resXml + s + "\r\n";
		in.close();
		logger.info("接收数据=" + URLDecoder.decode(resXml,"utf-8"));
		
		return resXml;
	}
	
	@Override
	public String sendSMS(String phone, String content) {
		try {
			return sendSms(phone, content);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public boolean checkSucc(String result) {
		if(StringUtils.isBlank(result)) {
			return false;
		}
		
		return true;
	}
}
