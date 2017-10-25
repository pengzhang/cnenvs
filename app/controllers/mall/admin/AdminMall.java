package controllers.mall.admin;

import java.util.List;

import annotations.Check;
import annotations.For;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import models.AdminModel;
import models.mall.Product;
import play.mvc.With;

@Check()
@Module("mall")
@For(AdminModel.class)
@With({AdminActionIntercepter.class,Secure.class})
public class AdminMall extends CRUD{
	
	public static void list() {
		
		List<Product> products = Product.find("order by sale_count desc").fetch(8);
		render(products);
	}

}
