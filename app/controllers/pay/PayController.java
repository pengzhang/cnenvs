package controllers.pay;

import java.util.Date;

import controllers.ActionIntercepter;
import models.core.common.ResponseData;
import models.pay.Order;
import play.Logger;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;
import plugins.router.Get;
import utils.UserAgentUtil;

@With({ActionIntercepter.class})
public class PayController extends Controller{

	/**
	 * 支付
	 * @param out_trade_no
	 */
	@Get("/pay")
	public static void pay(String out_trade_no){
		Order order = getOrder(out_trade_no);
		if(order.status){
			payError(out_trade_no, "订单已支付", "");
		}
		render(order);
	}

	/**
	 * 获取订单信息
	 * @param out_trade_no
	 * @return
	 */
	public static Order getOrder(String out_trade_no){

		Order order = Order.find("out_trade_no", out_trade_no).first();
		//订单不存在
		if(order == null){
			Logger.info("get order not exist, out_trade_node:%s", out_trade_no);
			payError(out_trade_no, "订单不存在", "");
		}
		return order;
	}


	@Get("/order/status")
	public static void orderStatus(String out_trade_no){
		Order order = Order.find("out_trade_no", out_trade_no).first();
		if(order == null){
			renderJSON(ResponseData.response(false, "订单不存在"));
		} else{
			if(order.status){
				renderJSON(ResponseData.response(true, "订单已支付"));
			}else{
				renderJSON(ResponseData.response(false, "订单未支付"));
			}
		}
	}

	/**
	 * 支付错误
	 * @param out_trade_no
	 * @param message
	 * @param error
	 */
	@Get("/pay/success")
	public static void paySuccess(String out_trade_no, String message, String return_url){
		renderArgs.put("out_trade_no", out_trade_no);
		renderArgs.put("message", message);
		renderArgs.put("return_url", return_url);
		render();
	}

	/**
	 * 支付错误
	 * @param out_trade_no
	 * @param message
	 * @param error
	 */
	@Get("/pay/error")
	public static void payError(String out_trade_no, String message, String return_url){
		renderArgs.put("out_trade_no", out_trade_no);
		renderArgs.put("message", message);
		renderArgs.put("return_url", return_url);
		render();
	}

	@Get("/pay/test")
	public static void payTest() {
		System.out.println(Play.applicationPath.getAbsolutePath());
		Order order = new Order();
		order.subject = "测试订单";
		order.body = "测试订单描述";
		order.total_fee = 1;
		order.show_url = "";
		order.return_url = "";
		order.save();
		pay(order.out_trade_no);
	}
	
	@Before()
	static void parseDevice() {
		String device = "computer";
		if (UserAgentUtil.isWechat(request)) {
			device = "wechat";
		}else if (UserAgentUtil.isMobile(request)) {
			device = "mobile";
		}
		renderArgs.put("device", device);
	}

}
