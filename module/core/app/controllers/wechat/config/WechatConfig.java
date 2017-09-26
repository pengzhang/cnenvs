package controllers.wechat.config;

import play.Play;

public class WechatConfig {
	
	public static String APPID = Play.configuration.getProperty("wechat.wxpay_appid"); 
	public static String APPSECRET = Play.configuration.getProperty("wechat.wxpay_appsecret"); 
	public static String MCHID = Play.configuration.getProperty("wechat.wxpay_mchid"); 
	public static String KEY = Play.configuration.getProperty("wechat.wxpay_key"); 
	public static String CURL_PROXY_HOST = Play.configuration.getProperty("wechat.wxpay_curl_proxy_host"); 
	public static String CURL_PROXY_PORT = Play.configuration.getProperty("wechat.wxpay_curl_proxy_port"); 
	public static String REPORT_LEVENL = Play.configuration.getProperty("wechat.wxpay_report_levenl"); 
	public static String SSLCERT_PATH = Play.configuration.getProperty("wechat.wxpay_sslcert_path"); 
	public static String SSLKEY_PATH = Play.configuration.getProperty("wechat.wxpay_sslkey_path"); 
	public static String SSLROOTCA_PATH = Play.configuration.getProperty("wechat.wxpay_sslrootca_path"); 
	public static String NOTIFY_URL = Play.configuration.getProperty("wechat.wxpay_notify_url"); 
	public static String WXPAY_DOMAIN = Play.configuration.getProperty("wechat.wxpay_domain"); 
	
}
