package tasks.pay;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controllers.CRUD;
import controllers.CRUD.ObjectType;
import models.core.adminuser.AdminUser;
import models.core.adminuser.Permission;
import models.core.setting.AdminMenu;
import models.core.setting.SystemSetting;
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.libs.Crypto;
import play.mvc.Router;
import play.mvc.Router.Route;
/**
 * 初始化数据
 * @author zhangpeng
 *
 */
@OnApplicationStart
public class InitDataTask extends Job{
	
	public void doJob(){
		initSettings();
	}
	/**
	 * 初始化配置
	 */
	public static void initSettings() {
		List<SystemSetting> sets = SystemSetting.findAll();

		List<String> keys = new ArrayList<String>();
		for(SystemSetting set : sets) {
			keys.add(set.settingKey);
			Play.configuration.setProperty(set.settingKey, set.settingValue);
		}

		setSetting(keys,"wechat.wxpay_mchid", "");
		setSetting(keys,"wechat.wxpay_key", "");
		setSetting(keys,"wechat.wxpay_curl_proxy_host", "");
		setSetting(keys,"wechat.wxpay_curl_proxy_port", "");
		setSetting(keys,"wechat.wxpay_report_levenl", "");
		setSetting(keys,"wechat.wxpay_sslcert_path", "");
		setSetting(keys,"wechat.wxpay_sslkey_path", "");
		setSetting(keys,"wechat.wxpay_sslrootca_path", "");
		setSetting(keys,"wechat.wxpay_notify_url", "");
		setSetting(keys,"wechat.wxpay_domain", "");
		
		setSetting(keys,"alipaykey.alipay_partner", "");
		setSetting(keys,"alipaykey.alipay_key", "");
		setSetting(keys,"alipaykey.alipay_log_path", "");
		setSetting(keys,"alipaykey.alipay_seller_email", "");
		setSetting(keys,"alipaykey.alipay_notify_url", "");
		setSetting(keys,"alipaykey.alipay_return_url", "");
		setSetting(keys,"alipaykey.alipay_wap_partner", "");
		setSetting(keys,"alipaykey.alipay_wap_private_key", "");
		setSetting(keys,"alipaykey.alipay_wap_log_path", "");
		setSetting(keys,"alipaykey.alipay_wap_notify_url", "");
		setSetting(keys,"alipaykey.alipay_wap_return_url", "");
		
	}

	private static void setSetting(List keys, String key, String value) {
		if(!keys.contains(key)) {
			new SystemSetting(key, value).save();
			Play.configuration.setProperty(key, value);
		}
	}

}
