package controllers.cms.admin;

import annotations.Check;
import annotations.For;
import annotations.Login;
import annotations.Module;
import controllers.CRUD;
import controllers.Secure;
import models.cms.Category;
import play.mvc.With;

@Check()
@Module("cms")
@For(Category.class)
@With({AdminCmsActionIntercepter.class,Secure.class})
public class AdminCategorys extends CRUD {
	
}
