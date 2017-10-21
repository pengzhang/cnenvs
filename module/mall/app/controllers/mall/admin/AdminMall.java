package controllers.mall.admin;

import annotations.Check;
import annotations.For;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import models.AdminModel;
import play.mvc.With;

@Check()
@Module("mall")
@For(AdminModel.class)
@With({AdminActionIntercepter.class,Secure.class})
public class AdminMall extends CRUD{

}
