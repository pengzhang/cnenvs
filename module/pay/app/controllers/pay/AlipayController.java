package controllers.pay;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import controllers.ActionIntercepter;
import models.pay.Order;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import plugins.router.Get;
import plugins.router.Post;
import services.alipay.config.AlipayParams;
import services.alipay.util.AlipayNotify;
import services.alipay.util.AlipaySubmit;
import services.alipay.util.AlipayWapNotify;
import services.alipay.util.AlipayWapSubmit;
import utils.Json;
import utils.UserAgentUtil;

@With({ActionIntercepter.class})
public class AlipayController extends Controller {
	
	/**
	 * 构建支付宝支付数据
	 */
	@Get("/alipay/?")
	public static void alipay(String out_trade_no){
		//获取订单
		Order order = PayController.getOrder(out_trade_no);
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = AlipayParams.setAlipayParams(order); 
		//订单选择支付方式为支付宝
		Order.selectPayWay(order.out_trade_no, 3);  
		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认"); 
		System.out.println(sHtmlText);
		renderHtml(sHtmlText);
	}

	/**
	 * 构建支付宝支付数据
	 * 
	 */
	@Get("/alipay/wap/?")
	public static void wapalipay(String out_trade_no){
		//获取订单
		Order order = PayController.getOrder(out_trade_no);
		// 把请求参数打包成数组
		Map<String, String> sParaTemp = AlipayParams.setAlipayWapParams(order); 
		//订单选择支付方式为支付宝
		Order.selectPayWay(order.out_trade_no, 4);  
		// 建立请求
		String sHtmlText = AlipayWapSubmit.buildRequest(sParaTemp, "get", "确认"); 
		renderHtml(sHtmlText);
	}

	/**
	 * 接收支付宝返回
	 * 
	 * @param out_trade_no
	 * @param trade_no
	 * @param trade_status
	 * @throws UnsupportedEncodingException
	 */
	@Get("/alipay/return/?")
	public static void return_url(String out_trade_no, String trade_no, String trade_status) {

		Logger.info("alipay return params: [out_trade_no:%s,trade_no:%s,trade_status:%s]", out_trade_no, trade_no, trade_status);
		Order order = PayController.getOrder(out_trade_no);

		// 获取支付宝GET过来反馈信息
		Map<String, String> params = AlipayParams.parseAlipayResponse(request.params.data);
		Logger.info("alipay return params: %s", Json.toJson(params));
		
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		boolean verify_result = false;
		if(order.trade_type==3){
			verify_result = AlipayNotify.verify(params);
		}else if(order.trade_type==4){
			verify_result = AlipayWapNotify.verify(params);
		}
		Logger.info("alipay return params: [out_trade_no:%s,verify_result:%s]", out_trade_no, verify_result);
		
		// 验证成功
		if (verify_result) {
			if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
				//更新订单
				Order.notifyUpdateOrder(out_trade_no, trade_no);
				Logger.info("alipay return update order success,params: [out_trade_no:%s, trade_no:%s, trade_status:%s]", out_trade_no, trade_no, trade_status);
				PayController.paySuccess(out_trade_no, "支付成功", order.return_url);
			}
		} else {
			if(order.status){
				Logger.info("order paied,params: [out_trade_no:%s, trade_no:%s, pay_date:%s]", out_trade_no, trade_no, order.pay_date);
				PayController.paySuccess(out_trade_no, "支付成功", order.return_url);
			}else{
				PayController.payError(out_trade_no, "支付宝参数有误", order.return_url);
			}

		}
	}

	/**
	 * 接收支付宝通知
	 * 
	 * @param out_trade_no
	 * @param trade_no
	 * @param trade_status
	 * @throws UnsupportedEncodingException
	 */
	@Post("/alipay/notify/?")
	public static void notify_url(String out_trade_no, String trade_no, String trade_status) {

		Logger.info("alipay notify params: [out_trade_no:%s,trade_no:%s,trade_status:%s]", out_trade_no, trade_no, trade_status);
		Order order = PayController.getOrder(out_trade_no);
		// 获取支付宝GET过来反馈信息
		Map<String, String> params = AlipayParams.parseAlipayResponse(request.params.data);
		params.put("body", getBodyParam(request.params.get("body")));
		Logger.info("alipay notify params: %s", Json.toJson(params));
		
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		boolean verify_result = false;
		if(order.trade_type==3){
			verify_result = AlipayNotify.verify(params);
		}else if(order.trade_type==4){
			verify_result = AlipayWapNotify.verify(params);
		}
		Logger.info("alipay notify params: [out_trade_no:%s,verify_result:%s]", out_trade_no, verify_result);
		
		if (verify_result) {// 验证成功
			if (trade_status.equals("TRADE_FINISHED")) {
				//更新订单
				Order.notifyUpdateOrder(out_trade_no, trade_no);
				Logger.info("alipay notify update order success,params: [out_trade_no:%s, trade_no:%s, trade_status:%s]", out_trade_no, trade_no, trade_status);
				
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				//更新订单
				Order.notifyUpdateOrder(out_trade_no, trade_no);
				Logger.info("alipay notify update order success,params: [out_trade_no:%s, trade_no:%s, trade_status:%s]", out_trade_no, trade_no, trade_status);
			}
			renderText("success"); // 请不要修改或删除

		} else {// 验证失败
			renderText("fail");
		}
	}
	
	private static String getBodyParam(String body){
		try {
			body = URLDecoder.decode(body,"utf-8");
		} catch (UnsupportedEncodingException e) {
			Logger.info("alipay body param:%s, error:%s", body, e.getMessage());
		}
		for(String param: body.split("[&]")){
			if(param.contains("body")){
				String bodyParam = param.split("=")[1];
				System.out.println(bodyParam);
				return bodyParam;
			}
		}
		return "";
	}
}
