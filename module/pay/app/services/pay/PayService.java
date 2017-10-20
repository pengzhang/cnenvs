package services.pay;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import models.pay.Order;

/**
 * 支付
 * 
 * @author zp
 *
 */
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

}
