package controllers.mall;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import annotations.DefaultPageParam;
import controllers.ActionIntercepter;
import controllers.BaseController;
import models.mall.Product;
import models.mall.ProductCategory;
import models.mall.ProductComment;
import models.mall.ProductTag;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.With;
import plugins.router.Get;
import plugins.router.Post;
import services.mall.ProductService;

@With({ActionIntercepter.class})
public class ProductController extends BaseController{
	
	
	@Inject
	static ProductService service;
	
	@Get("/product")
	public static void product(long id) {
		Product product = Product.findById(id);
		render(product);
	}
	
	@Get("/product/category")
	public static void productCategories() {
		List<ProductCategory> categories = ProductCategory.findAll();
		render(categories);
	}
	
	@DefaultPageParam
	@Get("/product/by/category")
	public static void productsByCategory(long cid, int page, int size) {
		List<Product> products = service.getProductsByCategory(cid, page,size);
		render(products);
	}
	
	
	@DefaultPageParam
	@Get("/product/by/category/sort")
	public static void productsByCategorySort(long cid, String sort, int page, int size) {
		List<Product> products = new ArrayList<>();
		if(sort.equalsIgnoreCase("desc")) {
			products = service.getProductsByPriceDesc(cid, page, size);
		}else {
			products = service.getProductsByPriceAsc(cid, page, size);
		}
		render(products);
	}
	
	@DefaultPageParam
	@Get("/product/newest")
	public static void productNewest(int page, int size) {
		List<Product> products = service.getNewestProducts(page, size);
		render(products);
	}
	
	
	@Post("/product/add/comment")
	public static void addComment(long id, ProductComment comment) {
		comment = service.addProductComment(id, comment,currentUser());
		render("/cms/ArticleController/sectionComment.html",comment);
	}
	
	@DefaultPageParam
	@Get("/product/get/comments")
	public static void getCommentList(long id, int page, int size) {
		Product product = Product.findById(id);
		List<ProductComment> comments = service.getComments(id, page, size);
		render("/cms/ArticleController/sectionCommentList.html", product, comments, page, size);
		
	}
	
	@Before()
	static void getProductTags(){
		List<ProductTag> tags = (List<ProductTag>) Cache.get("product_tags");
		if(tags == null) {
			tags = service.getProductTags(10);
			Cache.set("product_tags", tags, "1h");
		}
		renderArgs.put("product_tags", tags);
	}

}
