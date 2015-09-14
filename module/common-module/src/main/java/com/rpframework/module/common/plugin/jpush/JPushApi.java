package com.rpframework.module.common.plugin.jpush;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

public class JPushApi {
	final static Logger LOG = LoggerFactory.getLogger(JPushApi.class);
	
	static Map<String, JPushClient> jPushClientMap = new HashMap<String, JPushClient>(); 
	public static void registJPushClient(String appKey, String masterSecret, int maxRetryTimes) {
		jPushClientMap.put(appKey, new JPushClient(masterSecret, appKey, maxRetryTimes));
		LOG.info("getJPushClient 注册appKey:{}, masterSecret:{}", appKey, masterSecret);
	}
	
	public static JPushClient getJPushClient(String appKey) {
		if(jPushClientMap.containsKey(appKey)) {
			return jPushClientMap.get(appKey);
		}
		
		LOG.error("getJPushClient 找不到实现: " + appKey);
		return null;
	}
	
	public static JPushClient getDefaultJPushClient() {
		return getJPushClient(jPushClientMap.keySet().toArray()[0].toString());
	}
	
	public static void push(PushPayload payload, String key) {
		try {
			PushResult result = getJPushClient(key).sendPush(payload);
			LOG.info("Got result - " + result);

		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);

		} catch (APIRequestException e) {
			LOG.error("JPush Error response from JPush server. Should review and fix it. ",
					e);
			LOG.error("HTTP Status: " + e.getStatus());
			LOG.error("Error Code: " + e.getErrorCode());
			LOG.error("Error Message: " + e.getErrorMessage());
			LOG.error("Msg ID: " + e.getMsgId());
			LOG.error("APP_KEY: " + key);
			LOG.error("payload: " + payload.toString());
		} catch (Exception e) {
			LOG.error("push Exception: " + e.getLocalizedMessage());
		}
	}

//	public static PushPayload buildPushObjectAndroid() {
//		return PushPayload
//				.newBuilder()
//				.setPlatform(Platform.android())
//				.setAudience(Audience.all())
//				.setNotification(
//						Notification.android("only android " + new Date(),
//								" hello android", null))
//
//				.setMessage(Message.content("this hahahahahhaha"))
//
//				.build();
//	}
//
//	public static PushPayload buildPushObjectIOS() {
//		return PushPayload
//				.newBuilder()
//				.setPlatform(Platform.ios())
//				.setAudience(Audience.all())
//				// .setAudience(Audience.alias("123"))
//
//				.setNotification(Notification.ios_set_badge(10))
//				.setNotification(
//						Notification.ios("Hello All IOS" + new Date(), null))
//				.setMessage(Message.content("what are yous"))
//
//				.build();
//	}

//	public static PushPayload buildPushObject_all_all_alert() {
//		return PushPayload.alertAll(ALERT);
//	}
//
//	public static PushPayload buildPushObject_all_alias_alert() {
//		return PushPayload.newBuilder().setPlatform(Platform.all())
//				.setAudience(Audience.alias("alias1"))
//				.setNotification(Notification.alert(ALERT)).build();
//	}
//
//	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
//		return PushPayload.newBuilder().setPlatform(Platform.android())
//				.setAudience(Audience.tag("tag1"))
//				.setNotification(Notification.android(ALERT, TITLE, null))
//				.build();
//	}
//
//	public static PushPayload buildPushObject_android_and_ios() {
//		return PushPayload
//				.newBuilder()
//				.setPlatform(Platform.android_ios())
//				.setAudience(Audience.all())
//				.setNotification(
//						Notification
//								.newBuilder()
//								.setAlert("这是推送内容,当前时间：" + new Date())
//								.addPlatformNotification(
//										AndroidNotification.newBuilder()
//												.setTitle("hah Title").build())
//								.addPlatformNotification(
//										IosNotification
//												.newBuilder()
//												.incrBadge(5)
//												.addExtra("key", "123")
//												.addExtra("context",
//														"extra content")
//												.build()).build()).build();
//	}
//
//	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
//		return PushPayload
//				.newBuilder()
//				.setPlatform(Platform.ios())
//				.setAudience(Audience.all())
//				.setNotification(
//						Notification
//								.newBuilder()
//								.addPlatformNotification(
//										IosNotification.newBuilder()
//												.setAlert(ALERT).setBadge(5)
//												.setSound("happy")
//												.addExtra("from", "JPush")
//												.build()).build())
//				.setMessage(Message.content(MSG_CONTENT))
//				.setOptions(
//						Options.newBuilder().setApnsProduction(true).build())
//				.build();
//	}
//
//	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
//		return PushPayload
//				.newBuilder()
//				.setPlatform(Platform.android_ios())
//				.setAudience(
//						Audience.newBuilder()
//								.addAudienceTarget(
//										AudienceTarget.tag("tag1", "tag2"))
//								.addAudienceTarget(
//										AudienceTarget
//												.alias("alias1", "alias2"))
//								.build())
//				.setMessage(
//						Message.newBuilder().setMsgContent(MSG_CONTENT)
//								.addExtra("from", "JPush").build()).build();
//	}
}
