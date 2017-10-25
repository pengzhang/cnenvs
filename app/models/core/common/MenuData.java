package models.core.common;

import java.util.List;
import play.db.Model;

/**
 * 菜单数据封装
 * 
 * @author zp
 * 
 */
public class MenuData {

	public String url;
	public String name;
	public String modelType;
	
	public MenuData() {
	}

	public MenuData(String url, String name, String modelType) {
		super();
		this.url = url;
		this.name = name;
		this.modelType = modelType;
	}
}
