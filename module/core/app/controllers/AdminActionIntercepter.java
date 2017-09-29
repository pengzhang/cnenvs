package controllers;

import java.util.Set;

import exceptions.ControllerException;
import play.Logger;
import play.Play;
import play.mvc.After;
import play.mvc.Before;
import play.mvc.Catch;
import play.mvc.Controller;
import play.mvc.Finally;
import tasks.AccessLogTask;

public class AdminActionIntercepter extends Controller {

	@Before()
	private static void actionBeforeProcess() {
		AccessLogTask.record(request);
	}
	
	@Before()
	static void checkModules(){
		Set<String> modules = Play.modules.keySet();
		modules.remove("core");
		modules.remove("_docviewer");
		renderArgs.put("modules", modules);
	}

	@After
	private static void actionAfterProcess() {
	}
	
	@Catch(value = ControllerException.class, priority = 2)
	private static void actionControllerExceptionProcess(ControllerException ce) {
		ce.printStackTrace();
		Logger.error("controller exception %s", ce.getMessage());
		error(ce.getMessage());
	}

	@Catch(value = Throwable.class, priority = 1)
	private static void actionExceptionProcess(Throwable throwable) {
		throwable.printStackTrace();
		Logger.error("exception %s", throwable.getMessage());
		error(throwable.getMessage());
	}
	
	@Finally
    static void log() {
    }
	
}
