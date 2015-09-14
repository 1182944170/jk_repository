package com.rpframework.module.common.pay.wxpay.config;


/* *
 *类名：WXpayConfig
 *功能：微信支付相关配置
 */

public class WXpayConfig {

    // 发送内容的编码格式
    public static final String DEFAULT_CHAR_SET = "UTF-8";

    // 接收返回内容的编码格式
    public static final String DEFAULT_RESP_CHAR_SET = "UTF-8";

    // 统一下单接口中使用 trade_type
    public static final String DEFAULT_TRADE_TYPE = "APP";

    // 拼接真正支付使用参数用的，目前固定为 Sign=WXPay
    public static final String PACKAGE = "Sign=WXPay";

    // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    // APPid
    public static String appid = "wx21922ce19d2a4dc6"; //pay.properties
    // API 密钥
    public static String api_key = "1b3f056c0f01ec04c2cfbf777346762f";
    // 商户号
    public static String mch_id = "1249261801";

    public static String notifyURL = "";
    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    public static final String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String orderquery_url = "https://api.mch.weixin.qq.com/pay/orderquery";

    public static void initSetParam(String appid, String api_key, String mch_id, String notifyURL) {
    	WXpayConfig.api_key = api_key;
    	WXpayConfig.appid = appid;
    	WXpayConfig.mch_id = mch_id;
    	WXpayConfig.notifyURL = notifyURL;
	}
}
