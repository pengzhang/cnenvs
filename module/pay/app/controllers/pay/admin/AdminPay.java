package controllers.pay.admin;

import javax.inject.Inject;

import annotations.Check;
import annotations.For;
import annotations.Login;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import models.AdminModel;
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
		long totalFee = service.totalFee();
		long todayFee = service.totalTodayFee();
		long yesterDayFee = service.totalYesterDayFee();
		long monthFee = service.totalMonthFee();
		render(alipayUseTotal, weixinUseTotal, totalFee, todayFee, yesterDayFee, monthFee);
	}
}
