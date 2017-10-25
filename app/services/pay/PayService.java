package services.pay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import models.pay.Order;
import models.pay.OrderStatistics;

/**
 * 支付
 * 
 * @author zp
 *
 */
@Named
public class PayService {

	public long alipayUseTotal() {
		return Order.count("trade_type=? and status=?", 3, true);
	}

	public long weixinUserTotal() {
		return Order.count("trade_type=? and status=?", 1, true);
	}
	
	public long wapalipayUseTotal() {
		return Order.count("trade_type=? and status=?", 4, true);
	}

	public long wxclientUserTotal() {
		return Order.count("trade_type=? and status=?", 2, true);
	}

	public long totalFee() {
		return Order.find("select sum(total_fee) from Order where status=?", true).first();
	}

	public long totalTodayFee() {
		return sumFee(getDateTime(0), getCurrentDateTime());
	}

	public long totalYesterDayFee() {
		return sumFee(getDateTime(-1), getDateTime(0));
	}

	public long totalMonthFee() {
		return sumFee(getDateTime(-30), getCurrentDateTime());
	}

	private long sumFee(long minDate, long maxDate) {
		String min = String.valueOf(minDate);
		String max = String.valueOf(maxDate);
		try {
			return Order.find("select sum(total_fee) from Order where status=? and pay_date between ? and ?", true, min, max).first();
		} catch (Exception e) {
			return 0L;
		}
	}

	private long getDateTime(int d) {
		return DateUtils.truncate(DateUtils.addDays(new Date(), d), Calendar.DATE).getTime();
	}

	private long getCurrentDateTime() {
		return new Date().getTime();
	}
	
	public Map<String, Object> getStatis() {
		List<OrderStatistics> statis = OrderStatistics.find("order by pay_date desc").fetch(30);
		
		Map<String,Object> statisData = new HashMap<>();
		
		List<String> pay_date = new ArrayList<>();
		List<Long> use_alipay = new ArrayList<>(); 
		List<Long> use_wapalipay = new ArrayList<>();
		List<Long> use_weixin = new ArrayList<>();
		List<Long> use_wxclient = new ArrayList<>();
		
		for(OrderStatistics stat : statis){
			pay_date.add(stat.pay_date);
			use_alipay.add(stat.use_alipay);
			use_wapalipay.add(stat.use_wapalipay);
			use_weixin.add(stat.use_weixin);
			use_wxclient.add(stat.use_wxclient);
		}
		statisData.put("pay_date", pay_date);
		statisData.put("use_alipay", use_alipay);
		statisData.put("use_wapalipay", use_wapalipay);
		statisData.put("use_weixin", use_weixin);
		statisData.put("use_wxclient", use_wxclient);
		
		return statisData;
	}

	public void mockOrderStatisticsDatas() {
		for (int i = 0; i < 50; i++) {
			Date date = DateUtils.truncate(DateUtils.addDays(new Date(), (i * -1)), Calendar.DATE);
			OrderStatistics statis = new OrderStatistics();
			statis.pay_date = DateFormatUtils.format(date, "yyyyMMdd");
			statis.alipay = NumberUtils.toLong(RandomStringUtils.randomNumeric(3));
			statis.use_alipay = statis.alipay;
			statis.wapalipay = NumberUtils.toLong(RandomStringUtils.randomNumeric(3));
			statis.use_wapalipay = statis.wapalipay;
			statis.weixin = NumberUtils.toLong(RandomStringUtils.randomNumeric(3));
			statis.use_weixin = statis.weixin;
			statis.wxclient = NumberUtils.toLong(RandomStringUtils.randomNumeric(3));
			statis.use_wxclient = statis.wxclient;
			statis.total = statis.alipay + statis.wapalipay + statis.weixin + statis.wxclient;
			statis.save();
		}
	}
}
