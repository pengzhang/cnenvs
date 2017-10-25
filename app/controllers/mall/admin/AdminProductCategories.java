package controllers.mall.admin;

import annotations.Check;
import annotations.For;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import models.mall.ProductCategory;
import play.mvc.With;

@Check()
@Module("mall")
@For(ProductCategory.class)
@With({AdminActionIntercepter.class,Secure.class})
public class AdminProductCategories extends CRUD{

}
