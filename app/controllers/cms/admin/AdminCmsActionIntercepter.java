package controllers.cms.admin;

import java.util.ArrayList;
import java.util.List;

import controllers.AdminActionIntercepter;
import models.cms.Category;
import models.core.common.MenuData;
import play.mvc.Before;

public class AdminCmsActionIntercepter extends AdminActionIntercepter{
	
//	@Before
//	static void customMenus() {
//		List<MenuData> customMenus = new ArrayList<>();
//		List<Category> categories = Category.findAll();
//		for(Category category : categories) {
//			String url ="/admin/"+category.modelType+"?cid="+category.id;
//			customMenus.add(new MenuData(url, category.category, category.modelType.name()));
//		}
//		renderArgs.put("customMenus", customMenus);
//	}

}
