package controllers.pay;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import controllers.ActionIntercepter;
import controllers.wechat.WechatAuthController;
import exceptions.WechatPayException;
import models.pay.Order;
import play.Logger;
import play.mvc.Controller;
import play.mvc.With;
import plugins.router.Get;
import plugins.router.Post;
import services.pay.OrderService;
import services.wechat.config.WechatPayConfig;
import services.wechat.config.WechatPayParams;
import services.wechat.model.WechatJsApiPay;
import services.wechat.model.WechatPayData;
import services.wechat.model.WechatResponseBody;
import services.wechat.util.JaxbParser;
import services.wechat.util.WechatUtil;
import utils.Json;
import utils.StreamUtil;

@With({ActionIntercepter.class})
public class WechatpayController extends Controller {
	
	@Inject
	static OrderService service;

	/**
	 * 获取微信授权
	 * @param out_trade_no
	 */
	@Get("/wechat/pay/?")
	public static void wechatPay(String out_trade_no){
		String redirect_url = WechatPayConfig.WXPAY_DOMAIN + "/jsapipay/openid";
		try {
			redirect_url=URLEncoder.encode(redirect_url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			Logger.info("wechat auth redirect url encode error,%s", e.getMessage());
		}
		Logger.info("wechat auth redirect url,%s", redirect_url);
		WechatAuthController.snsapi_base(WechatPayConfig.APPID, redirect_url, out_trade_no);
	}

	/**
	 * @param code
	 * @param state
	 * 公众号获取openid,并更新订单
	 */
	@Get("/jsapipay/openid/?")
	public static void jsapipayOpenid(String code, String state ) {
		String out_trade_no = state;
		Logger.info("jsapipay request params:[code:%s, out_trade_no:%s]", code, out_trade_no);

		//获取订单信息
		Order order = PayController.getOrder(out_trade_no);

		//获取openid
		Map<String,String> result = WechatAuthController.authToken(WechatPayConfig.APPID, WechatPayConfig.APPSECRET, code);
		String openid = result.get("openid");
		if(StringUtils.isEmpty(openid)){
			PayController.payError(out_trade_no, "获取openid失败", order.return_url);
		}else{
			service.updateOpenid(out_trade_no, openid);
		}
		redirect("/jsapipay/?out_trade_no=" + out_trade_no);
	}
	
	/**
	 * 微信公众号支付
	 * @param out_trade_no
	 */
	@Get("/jsapipay/?")
	public static void jsapipay(String out_trade_no ) {
		Logger.info("jsapipay request params:[out_trade_no:%s]", out_trade_no);

		//获取订单信息
		Order order = PayController.getOrder(out_trade_no);
		
		//组装微信支付数据		
		WechatPayData jsapiData = WechatPayParams.setWechatPayData(order,"JSAPI");
		try{
			if (jsapiData.checkAttribute(jsapiData)) {
				Map<String, String> map = WechatPayParams.getWechatPayResult(jsapiData);
				Logger.info("jsapipay return map:%s", Json.toJson(map));
				if (map.get("return_code").equals("SUCCESS")) {
					//统一下单成功跳转到付款页面
					WechatJsApiPay pay = WechatPayParams.setWechatJsApiPay(map.get("prepay_id"));
					//支付选择微信公众号支付
					service.selectPayWay(order.out_trade_no, 2, StringUtils.defaultString(map.get("prepay_id")));

					String apiparams = new Gson().toJson(pay);
					render(apiparams, out_trade_no);
				} else {
					Logger.info("WechatPayData params:%s", jsapiData);
					PayController.payError(out_trade_no, "获取JSAPI结果失败", order.return_url);
				}
			} 
		}catch(WechatPayException wpe){
			Logger.info("jsapipay error, message:%s", wpe.getMessage());
			PayController.payError(out_trade_no, wpe.getMessage(), order.return_url);
		}catch(Exception e){
			Logger.info("jsapipay error, message:%s", e.getMessage());
			PayController.payError(out_trade_no, e.getMessage(), order.return_url);
		}
	}

	/**
	 * 
	 * @param out_trade_no
	 * @param openid
	 * 二维码支付下单
	 */
	@Get("/nativepay/?")
	public static void nativepay(String out_trade_no) {

		Logger.info("nativepay request params:[out_trade_no:%s]", out_trade_no);
		Order order = PayController.getOrder(out_trade_no);

		try{
			WechatPayData nativeData  = WechatPayParams.setWechatPayData(order,"NATIVE");
			if (nativeData.checkAttribute(nativeData)) {
				Map<String, String> map = WechatPayParams.getWechatPayResult(nativeData);
				Logger.info("nativepay return map:%s", Json.toJson(map));
				if (map.get("return_code").equals("SUCCESS")) {
					service.selectPayWay(order.out_trade_no, 1, StringUtils.defaultString(map.get("prepay_id")));
					String code_url = map.get("code_url");
					render(code_url, out_trade_no);
				} else {
					Logger.info("WechatPayData params:%s", nativeData);
					PayController.payError(out_trade_no, "获取NATIVE结果失败", order.return_url);
				}
			} 
		}catch(WechatPayException wpe){
			Logger.info("nativepay error, WechatPayException message:%s", wpe.getMessage());
			PayController.payError(out_trade_no, wpe.getMessage(), order.return_url);
		}catch(Exception e){
			Logger.info("nativepay error, Exception message:%s", e.getMessage());
			PayController.payError(out_trade_no, e.getMessage(), order.return_url);
		}
	}

	/**
	 * 接收微信结果通知 
	 */
	@Post("/wechat/notify/?")
	public static void wechat_notify(){
		try{
			InputStream is = request.body;
			String xmlStr = StreamUtil.streamToString(is);
			Logger.info("wechat_notify response data: %s", xmlStr);
			xmlStr = new String(xmlStr.getBytes("ISO-8859-1"), "utf-8");
			Map<String, String> resultMap = WechatUtil.getMapFromXML(xmlStr);
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
					service.notifyUpdateOrder(out_trade_no, transaction_id);
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
