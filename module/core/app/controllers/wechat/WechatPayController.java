package controllers.wechat;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import controllers.wechat.config.WechatPayParams;
import controllers.wechat.model.WechatJsApiPay;
import controllers.wechat.model.WechatPayData;
import controllers.wechat.model.WechatResponseBody;
import controllers.wechat.util.JaxbParser;
import controllers.wechat.util.WechatUtil;
import exceptions.WechatPayException;
import models.core.common.ResponseData;
import models.core.order.Order;
import play.Logger;
import play.mvc.Controller;
import plugins.router.Get;
import plugins.router.Post;
import utils.Json;

public class WechatPayController extends Controller {

	/**
	 * 微信公众号支付
	 * @param out_trade_no
	 */
	@Get("/jsapipay/?")
	public static void jsapipay(String out_trade_no ) {
		Logger.info("jsapipay request params:[out_trade_no:%s]", out_trade_no);

		//获取订单信息
		Order order = Order.find("out_trade_no", out_trade_no).first();
		
		//组装微信支付数据		
		WechatPayData jsapiData = WechatPayParams.setWechatPayData(order,"JSAPI");
		try{
			if (jsapiData.checkAttribute(jsapiData)) {
				Map<String, String> map = WechatPayParams.getWechatPayResult(jsapiData);
				Logger.info("jsapipay return map:%s", Json.toJson(map));
				if (map.get("return_code").equals("SUCCESS")) {
					//统一下单成功跳转到付款页面
					WechatJsApiPay apiparams = WechatPayParams.setWechatJsApiPay(map.get("prepay_id"));
					//支付选择微信公众号支付
					Order.selectPayWay(order.out_trade_no, 2, StringUtils.defaultString(map.get("prepay_id")));

					Logger.info("WechatPayData apiparams:%s", Json.toJson(apiparams));
					renderJSON(ResponseData.response(true, apiparams, "支付信息"));
				} else {
					Logger.info("WechatPayData params:%s", jsapiData);
					renderJSON(ResponseData.response(false, "获取JSAPI结果失败"));
				}
			} 
		}catch(WechatPayException wpe){
			Logger.info("jsapipay error, message:%s", wpe.getMessage());
			renderJSON(ResponseData.response(false, wpe.getMessage()));
		}catch(Exception e){
			Logger.info("jsapipay error, message:%s", e.getMessage());
			renderJSON(ResponseData.response(false, e.getMessage()));
		}
	}

	/**
	 * 接收微信结果通知 
	 */
	@Post("/wechat/notify/?")
	public static void wechat_notify(String no){
		try{
			Map<String, String> resultMap = WechatUtil.getMapFromXML(request.params.get("body"));
			if (resultMap.get("result_code").equals("SUCCESS")) {
				String sign = resultMap.get("sign");
				resultMap.remove("sign");
				String localsign = WechatUtil.createMapSign(resultMap);
				WechatResponseBody response = new WechatResponseBody();
				if (sign.equals(localsign)) {
					response.setReturn_code("SUCCESS");
					response.setReturn_msg("签名成功");
					String out_trade_no = resultMap.get("out_trade_no");
					String transaction_id = resultMap.get("transaction_id");
					//更新订单信息
					Order.notifyUpdateOrder(out_trade_no, transaction_id);
				} else {
					response.setReturn_code("FAIL");
					response.setReturn_msg("签名失败");
				}
				JaxbParser parser = new JaxbParser(response.getClass());
				String[] keyarr = WechatUtil.getFiledNameArr(response);
				parser.setCdataNode(keyarr);
				String responseXml = parser.toXML(response);
				renderXml(responseXml);
			}
		}catch(Exception e){
			Logger.info("wechat_notify error, message:%s", e.getMessage());
		}
	}

}
