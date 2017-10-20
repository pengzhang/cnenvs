package services.pay;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;

import models.pay.Order;
import models.pay.OrderStatistics;

public class OrderStatisticsService {

	public void statis(String out_trade_no) {
		String today = DateFormatUtils.format(new Date(), "yyyyMMdd");
		Order order = Order.find("out_trade_no", out_trade_no).first();
		OrderStatistics statis = OrderStatistics.find("pay_date", today).first();
		if(statis == null) {
			statis = new OrderStatistics();
		}
		statis.sum(order.trade_type, order.total_fee).save();
	}
	
	public List<OrderStatistics> getStatis(){
		return OrderStatistics.find("order by pay_date desc").fetch(30);
	}
}
