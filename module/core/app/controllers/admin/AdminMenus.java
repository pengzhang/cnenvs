package controllers.admin;

import java.util.List;

import annotations.Check;
import annotations.For;
import annotations.Login;
import annotations.Module;
import controllers.AdminActionIntercepter;
import controllers.CRUD;
import controllers.Secure;
import models.core.setting.AdminMenu;
import play.cache.Cache;
import play.mvc.With;

@Login
@Check()
@Module(value="core")
@For(AdminMenu.class)
@With({AdminActionIntercepter.class,Secure.class})
public class AdminMenus extends CRUD {
	
	
	/**
	 * 对比菜单顺序
	 * @param modelName 
	 * @param otherControllerName
	 * @return 对比结果
	 */
	public static int menuCompareTo(String controllerName, String otherControllerName) {
		
		Object mn = 0;
    	Object omn = 0;
    	if(Cache.get(controllerName) == null || Cache.get(otherControllerName) == null) {
    		List<AdminMenu> menus = AdminMenu.all().fetch();
    		for(AdminMenu menu : menus) {
    			Cache.set(menu.controllerName, menu.seq, "5mn");
    			if(controllerName.equals(menu.controllerName)) {
    				mn = menu.seq;
    			}
    			if(otherControllerName.equals(menu.controllerName)) {
    				omn = menu.seq;
    			}
    		}
    	}else {
    		 mn = Cache.get(controllerName);
        	 omn = Cache.get(otherControllerName);
    	}
    	if((int)mn > (int) omn) {
    		return 1;
    	}else if((int)mn == (int) omn) {
    		return 0;
    	}else {
    		return -1;
    	}
	}

}
