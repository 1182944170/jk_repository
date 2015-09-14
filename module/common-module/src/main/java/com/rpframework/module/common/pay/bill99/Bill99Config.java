package com.rpframework.module.common.pay.bill99;



/* *
 *类名：WXpayConfig
 *功能：微信支付相关配置
 */

public class Bill99Config {

    // 发送内容的编码格式
    public static final String DEFAULT_CHAR_SET = "UTF-8";

    // 接收返回内容的编码格式
    public static final String DEFAULT_RESP_CHAR_SET = "UTF-8";

    // 统一下单接口中使用 trade_type
    public static String merchantAcctId = "1002451693301";
    public static String ksfis_path = "";
    public static String key_pwd = "1qazxsw2";

    public static String url = "https://www.99bill.com/mobilegateway/recvMerchantInfoAction.htm";
    public static String bgUrl = "";
    public static String showUrl = "";

	public static String cer_path;

	
    public static void initSetParam(String bill99_merchantAcctId, String bill99_ksfis_path, String bill99_key_pwd, 
    		String bill99_url, String bill99_bgUrl,String bill99_showUrl, String cer_path) {
    	Bill99Config.merchantAcctId = bill99_merchantAcctId;
    	Bill99Config.ksfis_path = bill99_ksfis_path;
    	Bill99Config.key_pwd = bill99_key_pwd;
    	Bill99Config.url = bill99_url;
    	Bill99Config.bgUrl = bill99_bgUrl;
    	Bill99Config.showUrl = bill99_showUrl;
    	Bill99Config.cer_path = cer_path;
	}
}
