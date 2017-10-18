package services.pay;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import models.pay.Order;

/**
 * 支付
 * 
 * @author zp
 *
 */
@Component
public class PayService {

	public long alipayUseTotal() {
		return Order.count("trade_type=? or trade_type=? and status=?", 3, 4, true);
	}

	public long weixinUserTotal() {
		return Order.count("trade_type=? or trade_type=? and status=?", 1, 2, true);
	}

	public long totalFee() {
		return Order.find("select sum(total_fee) from Order where status=?", true).first();
	}

	public long totalTodayFee() {
		return sumFee(getDate(0), getCurrentDate());
	}

	public long totalYesterDayFee() {
		return sumFee(getDate(-1), getDate(0));
	}

	public long totalMonthFee() {
		return sumFee(getDate(-30), getCurrentDate());
	}

	public List<Long> weixinUserStatis() {
		return Order.find("select count(*) from Order where status=? and pay_date between ? and ? group by ", true, getDate(-30), getCurrentDate()).fetch();
	}

	public List<Long> alipayUserStatis() {
		return Order.find("select sum(total_fee) from Order where status=? and pay_date between ? and ?", true, DateUtils.truncate(DateUtils.addDays(new Date(), -30), Calendar.DATE).getTime(), new Date().getTime()).fetch();

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
	
	private long getDate(int d) {
		return DateUtils.truncate(DateUtils.addDays(new Date(), d), Calendar.DATE).getTime();
	}
	
	private long getCurrentDate() {
		 return new Date().getTime();
	}

}
