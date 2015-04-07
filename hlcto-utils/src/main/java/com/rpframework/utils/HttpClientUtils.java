package com.rpframework.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author <a href="mailto:rplees.i.ly@gmail.com">rplees</a>
 * date 2013-06-24
 * {@code} httpclient 一些常用操作
 */
public class HttpClientUtils {

	private static HttpClient hc = new DefaultHttpClient();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", "xxx@gmail.com"));
		params.add(new BasicNameValuePair("pwd", "xxx"));
		params.add(new BasicNameValuePair("save_login", "1"));

		String url = "http://www.oschina.net/action/user/login";
		String body = post(url, params);
		System.out.println(body);
	}

	/**
	 * Get请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String get(String url, List<NameValuePair> params) {
		if(params == null) params = new ArrayList<NameValuePair>(1);
		String body = null;
			// Get请求
			HttpGet httpget = new HttpGet(url);
			// 设置参数
			String str;
			try {
				str = EntityUtils.toString(new UrlEncodedFormEntity(params));
				httpget.setURI(new URI(httpget.getURI().toString() + "?" + str));
				// 发送请求
				HttpResponse httpresponse = hc.execute(httpget);
				// 获取返回数据
				HttpEntity entity = httpresponse.getEntity();
				body = EntityUtils.toString(entity);
				if (entity != null) {
					entity.consumeContent();
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return body;
	}

	/**
	 * // Post请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String post(String url, List<NameValuePair> params) {
		if(params == null) params = new ArrayList<NameValuePair>(1);
		String body = null;
		try {
			// Post请求
			HttpPost httppost = new HttpPost(url);
			// 设置参数
			httppost.setEntity(new UrlEncodedFormEntity(params));
			// 发送请求
			HttpResponse httpresponse = hc.execute(httppost);
			// 获取返回数据
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity);
			if (entity != null) {
				entity.consumeContent();
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return body;
	}
	
}
