package services.alipay.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import models.pay.Order;
import services.alipay.util.AlipayTotalFeeUtil;

/**
 * 封装支付宝参数
 * @author zhangpeng
 *
 */
public class AlipayParams {

	/**
	 * 设置支付宝PC支付参数
	 * @param order
	 * @return
	 */
	public static Map<String, String> setAlipayParams(Order order){
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_email", AlipayConfig.seller_email);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", "1");
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("out_trade_no", order.out_trade_no);
		sParaTemp.put("subject", order.subject);
		sParaTemp.put("total_fee", AlipayTotalFeeUtil.toYuan(order.total_fee));
		sParaTemp.put("body", order.body);
		sParaTemp.put("show_url", order.show_url);
		sParaTemp.put("it_b_pay","5m");
		return sParaTemp;
	}
	
	/**
	 * 设置支付宝手机端支付参数
	 * @param order
	 * @return
	 */
	public static Map<String, String> setAlipayWapParams(Order order){
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
		sParaTemp.put("partner", AlipayWapConfig.partner);
		sParaTemp.put("seller_id", AlipayWapConfig.seller_id);
		sParaTemp.put("_input_charset", AlipayWapConfig.input_charset);
		sParaTemp.put("payment_type", "1");
		sParaTemp.put("notify_url", AlipayWapConfig.notify_url);
		sParaTemp.put("return_url", AlipayWapConfig.return_url);
		sParaTemp.put("out_trade_no", order.out_trade_no);
		sParaTemp.put("subject", order.subject);
		sParaTemp.put("total_fee", AlipayTotalFeeUtil.toYuan(order.total_fee));
		sParaTemp.put("body", order.body);
		sParaTemp.put("show_url", order.show_url);
		sParaTemp.put("it_b_pay", "5m");
		return sParaTemp;
	}
	
	/**
	 * 分析支付宝返回的响应
	 * @param requestParams
	 * @return
	 */
	public static Map<String, String> parseAlipayResponse(Map requestParams){
		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
//			try {
//				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//			} catch (UnsupportedEncodingException e) {
//				Logger.info("Alipay parseAlipayResponse Exception:%s", e.getMessage());
//			}
			params.put(name, valueStr);
		}
		return params;
	}
}
