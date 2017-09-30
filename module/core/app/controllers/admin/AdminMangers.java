package controllers.admin;


import annotations.Check;
import annotations.For;
import annotations.Login;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import models.core.adminuser.AdminUser;
import play.mvc.With;

@Login
@Module(value="core")
@Check()
@For(AdminUser.class)
@With({AdminActionIntercepter.class,Secure.class})
public class AdminMangers extends CRUD{

}
