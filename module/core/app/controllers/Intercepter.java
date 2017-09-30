package controllers;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import annotations.DefaultPageParam;
import annotations.Login;
import annotations.Module;
import controllers.wechat.WechatAuthController;
import controllers.wechat.config.WechatConfig;
import exceptions.ControllerException;
import play.Logger;
import play.Play;
import play.cache.Cache;
import play.mvc.After;
import play.mvc.Before;
import play.mvc.Catch;
import play.mvc.Controller;
import play.mvc.Finally;
import tasks.AccessLogTask;
import utils.UserAgentUtil;

public class Intercepter extends Controller {
	
	@Before()
	private static void actionBeforeProcess() {
		AccessLogTask.record(request);
	}
	
	@Before
	static void devDefaultUser() {
		if(Play.mode.isDev()) {
			session.put("uid", 1);
		}
	}

	@Before
	static void defaultPageParam() throws Exception {
		Class controller = Class.forName("controllers." + request.action.substring(0, request.action.lastIndexOf(".")));
		Method[] methods = controller.getMethods();
		for(Method method : methods) {
			if(method.isAnnotationPresent(DefaultPageParam.class)  && request.actionMethod.equals(method.getName())) {
				String page = request.params.get("page");
				if(StringUtils.isEmpty(page)) {
					request.params.put("page", "1");
				}else if(Integer.parseInt(page) < 1) {
					request.params.put("page", "1");
				}

				String size = request.params.get("size");
				if(StringUtils.isEmpty(size)) {
					request.params.put("size", "10");
				}else if(Integer.parseInt(size) < 1) {
					request.params.put("size", "10");
				}
			}
		}
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
