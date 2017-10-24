package controllers;

import java.util.List;

import javax.inject.Inject;

import models.cms.Article;
import play.mvc.Controller;
import services.cms.ArticleService;

public class Application extends Controller {
	
	@Inject
	static ArticleService articleService;
	

    public static void index() {
    	List<Article> articles = articleService.getNewestList(1, 10);
        render(articles);
    }

}