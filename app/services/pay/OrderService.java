package services.pay;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import models.pay.Order;
import play.db.jpa.Transactional;

@Named
public class OrderService {

	/**
	 * 更新订单Openid
	 * @param out_trade_no
	 * @param openid
	 */
	@Transactional
	public void updateOpenid(String out_trade_no, String openid) {
		Order order = Order.find("out_trade_no", out_trade_no).first();
		order.openid = openid;
		order.updateDate = new Date();
		order.save();
	}

	/**
	 * 选择支付方式
	 * @param out_trade_no
	 * @param trade_type
	 */
	@Transactional
	public void selectPayWay(String out_trade_no, int trade_type) {
		Order order = Order.find("out_trade_no", out_trade_no).first();
		order.trade_type = trade_type;
		order.updateDate = new Date();
		order.save();
	}
	
	/**
	 * 选择支付方式(微信)
	 * @param out_trade_no
	 * @param trade_type
	 * @param openid
	 * @param perpay_id
	 */
	@Transactional
	public void selectPayWay(String out_trade_no, int trade_type, String perpay_id) {
		Order order = Order.find("out_trade_no", out_trade_no).first();
		order.trade_type = trade_type;
		order.prepay_id = perpay_id;
		order.updateDate = new Date();
		order.save();
	}

	/**
	 * 支付成功更新状态
	 * @param out_trade_no
	 * @param trade_no
	 */
	@Transactional
	public void notifyUpdateOrder(String out_trade_no, String trade_no) {
		Order order = Order.find("out_trade_no", out_trade_no).first();
		order.trade_no = trade_no;
		order.status = true;
		order.pay_date = String.valueOf(System.currentTimeMillis());
		order.updateDate = new Date();
		order.save();
		//统计支付情况
		order.statis();
	}
	
}
