package controllers.cms.admin;

import java.lang.reflect.Constructor;
import java.util.List;

import javax.inject.Inject;

import annotations.Check;
import annotations.DefaultPageParam;
import annotations.For;
import annotations.Module;
import controllers.CRUD;
import controllers.Secure;
import controllers.CRUD.ObjectType;
import models.cms.Category;
import models.cms.Video;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.With;
import plugins.router.Get;
import services.cms.VideoService;

@Check()
@Module(value="cms")
@For(Video.class)
@With({AdminCmsActionIntercepter.class,Secure.class})
public class AdminVideos extends CRUD {

	@Inject
	static VideoService service;

	@DefaultPageParam
	@Get("/admin/video")
	public static void categoryList(int page, int cid) {
		ObjectType type = ObjectType.get(getControllerClass());
		List<Video> objects = service.videoByCategoryList(cid, page, 30);
		Long count = service.countVideoByCategory(cid);
		Long totalCount = Video.count();
		render("/cms/admin/AdminVideos/list.html", type, objects, count, totalCount, page, cid);
	}

	public static void blank(Long cid) throws Exception {
		ObjectType type = ObjectType.get(getControllerClass());
		Video object = new Video();
		object.category = Category.findById(cid);
		try {
            render(type, object);
        } catch (TemplateNotFoundException e) {
            render("CRUD/blank.html", type, object);
        }
	}

}
