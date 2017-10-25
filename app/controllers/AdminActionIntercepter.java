package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import annotations.Module;
import exceptions.ControllerException;
import play.Logger;
import play.Play;
import play.mvc.After;
import play.mvc.Before;
import play.mvc.Catch;
import play.mvc.Controller;
import play.mvc.Finally;
import tasks.core.AccessLogTask;

public class AdminActionIntercepter extends Intercepter {
	
	@Before()
	static void checkModules(){
		Set<String> modules = Play.modules.keySet();
//		modules.remove("core");
//		modules.remove("_docviewer");
		renderArgs.put("modules", modules);
	}
	
	@Before()
	static void checkMenus() {
		try {
			List<String> menus = new ArrayList<>();
			Class controller = request.controllerClass;
			if(controller.isAnnotationPresent(Module.class)) {
				String module = ((Module)controller.getAnnotation(Module.class)).value();
				List<Class> adminControllers = Play.classloader.getAnnotatedClasses(Module.class);
				for(Class adminController : adminControllers) {
					Module adminModule = (Module) adminController.getAnnotation(Module.class);
					if(module.equals(adminModule.value())) {
						if(adminModule.display()) {
							menus.add(adminController.getSimpleName());
						}
					}
				}
			}
			renderArgs.put("menus", menus);
		} catch (Exception e) {
			error(e.getMessage());
		}
		
	}
}
