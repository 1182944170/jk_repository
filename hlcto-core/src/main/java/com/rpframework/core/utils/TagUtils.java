package com.rpframework.core.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.InitServlet;
import com.rpframework.core.api.FileService;
import com.rpframework.core.freemarker.BaseRegistFreemarker;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.FileUtils;

@Component("tagUtils")
public class TagUtils extends BaseRegistFreemarker {
	public static Object getNull() {
		return null;
	}
	
	public static String cutString(String s, int len) {
		if(StringUtils.isBlank(s)) {
			return s;
		}
		
		if(s.length() <= len) {
			return s;
		}
		
		return StringUtils.substring(s, 0, len) + "...";
	}
	public static String getDomain(HttpServletRequest request) {
		String d = request.getScheme() + "://" + request.getServerName();
		if(request.getServerPort() != 80) {
			d += ":" + request.getServerPort();
		}
		
		d += InitServlet.CTX + "/";
		return d;
	}
	
	public static String formatDate(String timestampString) {
		return formatDate(Long.parseLong(timestampString));
	}
	
	public static String formatDate(long timestamp) {
		return formatDate(timestamp, DateUtils.DEFAULT_DATE_FORMAT);
	}
	
	public static boolean logic2(Integer t, Integer t2) {
		if(t == null) return false;
		return (t & t2) > 0;
	}
	
	/**
	 * 
	 * 描述
	 * @param timestamp 秒  不是毫秒
	 * @param format
	 * @return
	 */
	public static String formatDate(long timestamp, String format) {
		return DateUtils.format(format, new Date(timestamp*1000L));
	}
	
	
	public static long getLong(JsonObject jsonObject, String key, Long defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
		}
		
		return jsonElement.getAsLong();
	}
	
	public static Long getLong(JsonObject jsonObject, String key) {
		return getLong(jsonObject, key, 0L);
	}
	
	public static int getInt(JsonObject jsonObject, String key, Integer defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
		}
		
		return jsonElement.getAsInt();
	}
	
	public static int getInt(JsonObject jsonObject, String key) {
		return getInt(jsonObject, key, 0);
	}
	
	public static String getString(JsonObject jsonObject, String key, String defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
		}
		
		return jsonElement.getAsString();
	}
	
	public static String getString(JsonObject jsonObject, String key) {
		return getString(jsonObject, key, "");
	}
	
	public static Double getDouble(JsonObject jsonObject, String key, Double defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
		}
		
		return jsonElement.getAsDouble();
	}
	
	public static Double getDouble(JsonObject jsonObject, String key) {
		return getDouble(jsonObject, key, 0D);
	}
	
	public static String getWebUrl() {
		FileService fileService = SpringUtils.getBean(FileService.class);
		return fileService.getWebUrl();
	}
	
	public static String getFileFullPath(String absPath) {
		if(StringUtils.isBlank(absPath)) {
			return absPath;
		}
		if(StringUtils.indexOf(absPath, "http") >= 0) {
			return absPath;
		}
		String url = FileUtils.splicePaths(getWebUrl(), absPath);
		return StringUtils.replace(url, "\\", "/");
	}
	
	/**
	 * <p>
	 * Web 服务器反向代理中用于存放客户端原始 IP 地址的 Http header 名字，
	 * 若新增其他的需要增加或者修改其中的值。
	 * </p>
	 */
	private static final String[] PROXY_REMOTE_IP_ADDRESS = { "X-Real-IP" ,"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP"};
	 
	 
	/**
	 * <p>
	 * 获取请求的客户端的 IP 地址。若应用服务器前端配有反向代理的 Web 服务器，
	 * 需要在 Web 服务器中将客户端原始请求的 IP 地址加入到 HTTP header 中。
	 * 详见 {@link #PROXY_REMOTE_IP_ADDRESS}
	 * </p>
	 */
	public static String getRemoteIp( HttpServletRequest request ) {
	    for ( int i = 0 ; i < PROXY_REMOTE_IP_ADDRESS.length ; i++ ) {
	        String ip = request.getHeader( PROXY_REMOTE_IP_ADDRESS[i] );
	        if ( ip != null && ip.trim().length() > 0 ) {
	            return getRemoteIpFromForward( ip.trim() );
	        }
	    }
	    return request.getRemoteHost();
	}
	 
	/**
	 * <p>
	 * 从 HTTP Header 中截取客户端连接 IP 地址。如果经过多次反向代理，
	 * 在请求头中获得的是以“,&lt;SP&gt;”分隔 IP 地址链，第一段为客户端 IP 地址。
	 * </p>
	 *
	 * @param xforwardIp 从 HTTP 请求头中获取转发过来的 IP 地址链
	 * @return 客户端源 IP 地址
	 */
	private static String getRemoteIpFromForward( String xforwardIp ) {
	    int commaOffset = xforwardIp.indexOf( ',' );
	    if ( commaOffset < 0 ) {
	        return xforwardIp;
	    }
	    return xforwardIp.substring( 0 , commaOffset );
	}
}
