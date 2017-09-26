package services.alipay.util;

import java.math.BigDecimal;

public class AlipayTotalFeeUtil {

	public static String toYuan(long fee) {
		BigDecimal dec = new BigDecimal(fee);
		BigDecimal d = dec.divide(new BigDecimal(100));
		return d.toString();
	}
	
	public static String toYuan(String fee) {
		BigDecimal dec = new BigDecimal(fee);
		BigDecimal d = dec.divide(new BigDecimal(100));
		return d.toString();
	}
}
