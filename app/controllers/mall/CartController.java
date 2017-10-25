package controllers.mall;

import java.util.List;

import javax.inject.Inject;

import annotations.Login;
import controllers.ActionIntercepter;
import controllers.BaseController;
import models.core.common.ResponseData;
import models.mall.Cart;
import play.mvc.With;
import plugins.router.Get;
import services.mall.CartService;

@Login
@With({ActionIntercepter.class})
public class CartController extends BaseController{
	
	@Inject
	static CartService service;
	
	@Get("/my/cart")
	public static void myCart() {
		List<Cart> carts = service.getMyCart(currentUser());
		render(carts);
	}
	
	@Get("/my/cart/add/product")
	public static void myCartAddProduct(long pid, int num) {
		service.addProductToCart(currentUser(), pid, num);
		render(ResponseData.response(true, "添加购物车商品成功"));
	}
	
	@Get("/my/cart/sub/product")
	public static void myCartSubProduct(long pid, int num) {
		service.subProductToCart(currentUser(), pid, num);
		render(ResponseData.response(true, "减少购物车商品成功"));
	}
	
	@Get("/my/cart/add/product")
	public static void myCartDelProduct(long pid, int num) {
		service.delProductFromCart(currentUser(), pid);
		render(ResponseData.response(true, "删除购物车商品成功"));
	}

}
