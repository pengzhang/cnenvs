package controllers.cms.admin;

import annotations.Check;
import annotations.For;
import annotations.Login;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import models.AdminModel;
import play.mvc.With;

@Login
@Check()
@Module("cms")
@For(AdminModel.class)
@With({AdminActionIntercepter.class,Secure.class})
public class AdminCMS extends CRUD {
	
}
