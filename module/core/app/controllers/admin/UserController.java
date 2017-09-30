package controllers.admin;


import annotations.Check;
import annotations.For;
import annotations.Login;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import models.core.user.User;
import play.mvc.With;

@Login
@Check()
@Module(value="core")
@For(User.class)
@With({AdminActionIntercepter.class,Secure.class})
public class UserController extends CRUD{

}
