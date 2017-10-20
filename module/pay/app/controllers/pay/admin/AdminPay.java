package controllers.pay.admin;

import java.util.Map;

import javax.inject.Inject;

import org.junit.After;

import annotations.Check;
import annotations.For;
import annotations.Login;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import models.AdminModel;
import play.mvc.Before;
import play.mvc.With;
import services.pay.PayService;

@Login
@Check()
@Module("pay")
@For(AdminModel.class)
@With({AdminActionIntercepter.class,Secure.class})
public class AdminPay extends CRUD {
	
	@Inject
	static PayService service;
	
	public static void list() {
		long alipayUseTotal = service.alipayUseTotal();
		long weixinUseTotal = service.weixinUserTotal();
		long wapalipayUseTotal = service.wapalipayUseTotal();
		long wxclientUseTotal = service.wxclientUserTotal();
		long totalFee = service.totalFee();
		long todayFee = service.totalTodayFee();
		long yesterDayFee = service.totalYesterDayFee();
		long monthFee = service.totalMonthFee();
		Map<String,Object> statis = service.getStatis();
		render(alipayUseTotal, weixinUseTotal, wapalipayUseTotal, wxclientUseTotal, totalFee, todayFee, yesterDayFee, monthFee, statis);
	}
	
}
