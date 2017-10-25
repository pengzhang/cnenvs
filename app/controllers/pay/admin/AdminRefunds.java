package controllers.pay.admin;

import annotations.Check;
import annotations.For;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import models.pay.Refund;
import play.mvc.With;

@Check()
@Module("pay")
@For(Refund.class)
@With({AdminActionIntercepter.class,Secure.class})
public class AdminRefunds extends CRUD {
	
}
