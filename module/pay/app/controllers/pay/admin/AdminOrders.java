package controllers.pay.admin;

import annotations.Check;
import annotations.For;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import models.pay.Order;
import play.mvc.With;

@Check()
@Module("pay")
@For(Order.class)
@With({AdminActionIntercepter.class,Secure.class})
public class AdminOrders extends CRUD {
	
}
