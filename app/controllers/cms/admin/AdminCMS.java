package controllers.cms.admin;

import java.util.List;

import annotations.Check;
import annotations.For;
import annotations.Module;
import controllers.CRUD;
import controllers.Secure;
import models.AdminModel;
import models.cms.Article;
import models.cms.Video;
import play.mvc.With;

@Check()
@Module("cms")
@For(AdminModel.class)
@With({AdminCmsActionIntercepter.class,Secure.class})
public class AdminCMS extends CRUD {
	
	public static void list() {
		List<Article> articles = Article.find("order by view_total desc").fetch(4);
		List<Video> videos = Video.find("order by view_total desc").fetch(4);
		render(articles, videos);
	}
	
}
