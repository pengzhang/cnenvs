package controllers.mall.admin;

import annotations.Check;
import annotations.For;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import models.AdminModel;
import models.mall.VipProduct;
import play.mvc.With;

@Check()
@Module("mall")
@For(VipProduct.class)
@With({AdminActionIntercepter.class,Secure.class})
public class AdminVipProducts extends CRUD{

}
