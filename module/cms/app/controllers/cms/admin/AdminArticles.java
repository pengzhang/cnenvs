package controllers.cms.admin;

import java.util.List;

import javax.inject.Inject;

import annotations.Check;
import annotations.DefaultPageParam;
import annotations.For;
import annotations.Login;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import controllers.CRUD.ObjectType;
import models.cms.Article;
import models.cms.Category;
import models.cms.Article;
import play.exceptions.TemplateNotFoundException;
import play.mvc.With;
import plugins.router.Get;
import services.cms.ArticleService;

@Check()
@Module(value="cms")
@For(Article.class)
@With({AdminCmsActionIntercepter.class,Secure.class})
public class AdminArticles extends CRUD {

	@Inject
	static ArticleService service;

	@DefaultPageParam
	@Get("/admin/article")
	public static void categoryList(int page, int cid) {
		ObjectType type = ObjectType.get(getControllerClass());
		List<Article> objects = service.articleByCategoryList(cid, page, 30);
		Long count = service.countArticleByCategory(cid);
		Long totalCount = Article.count();
		render("/cms/admin/AdminArticles/list.html", type, objects, count, totalCount, page, cid);
	}

	public static void blank(Long cid) throws Exception {
		ObjectType type = ObjectType.get(getControllerClass());
		Article object = new Article();
		object.category = Category.findById(cid);
		try {
            render(type, object);
        } catch (TemplateNotFoundException e) {
            render("CRUD/blank.html", type, object);
        }
	}
	
}
