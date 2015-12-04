package com.rpframework.module.common.pay.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088021129818550";
	// 商户的私钥
	public static String private_key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAO3dgbfgfucz/YzbpqffzPHyOaWzW/wiH9W5JoHuVDuxL8ggBJiPtyrc165mFYhH3FozbD2rn218zb2NAImdyPwddIDZyD5pD0uI+zHLdrp4K9P+5fDLsGxOXMRJZRIvuqQWxcE+D/52BBPZEoxdMSsitJ+weFRKfyojfTkqV6SxAgMBAAECgYAY4jtXyRx9g2ra+X+NwFPoW7a8Ju9QiSFbcmsc3JnjRL4C6ZaRfzdiPIgzrmpCP4JFzWtdaD7dbVj4o2+S9fa4dZkDQ+yJybhGAKvbe58WxxoKXoWHmC+HNqycm/wbI97dEvfP8nMy5e6TQNnbKX47qFqKntfpgA3RsB5cYg7uoQJBAPsOR5gQuVX3z/p4DbjbLIyAXeskzZBV+wIZFpF/PoVtMKqCR6TnNMSce6TxBfCk0r7pmRJMFsHguDQ1D54GWWsCQQDyjLnRtJ47V2sXjb1VbJD5rcvJrTGn0fOa+5C4d+6M9Blw3E8YmAJoKgPDUvZRUBxmjHupumqkIFJZBpNHMLVTAkA2/4DVokxHtfsc3rH63iDbQr/QZIaX6Q4M/pwAEcULi8kOgW3ahT/9Fl7L2nU3q5PzeM8sv/h4Tpdr8HfUYYWzAkB1rt4B57bWaAXn+behmPmGi1LTWZs8J9wgmGqRdm9iKJWnct1Vu3t/etM0lC47Kbl98C+YHIDdfWk3FL+kzPdNAkAMW77vKNGccZcZ7J9Hg1bXUtGjyWWxW7p3Kh9ZBHZ9ModJ8i9BdLpuWNNX/sDcT6eo9O5AfJqsUQOIR5fFFulY";
	public static String md5key = "";
	public static String seller_email = "13713789000@qq.com";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDt3YG34H7nM/2M26an38zx8jmls1v8Ih/VuSaB7lQ7sS/IIASYj7cq3NeuZhWIR9xaM2w9q59tfM29jQCJncj8HXSA2cg+aQ9LiPsxy3a6eCvT/uXwy7BsTlzESWUSL7qkFsXBPg/+dgQT2RKMXTErIrSfsHhUSn8qI305KleksQIDAQAB";

	public static String notifyURL = "http://app.rofor.com:8080/api/order/test_pay_succ";
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

	public static void initSetParam(String partner, String private_key, String ali_public_key, String log_path, String input_charset, String sign_type, String seller_email, String notifyURL, String md5key) {
		AlipayConfig.partner = partner;
		AlipayConfig.private_key = private_key;
		AlipayConfig.ali_public_key = ali_public_key;
		AlipayConfig.log_path = log_path;
		AlipayConfig.input_charset = input_charset;
		AlipayConfig.sign_type = sign_type;
		AlipayConfig.seller_email = seller_email;
		AlipayConfig.notifyURL = notifyURL;
		AlipayConfig.md5key = md5key;
	}
}
