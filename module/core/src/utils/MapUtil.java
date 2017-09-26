package utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Map与Object转换
 * @author zp
 *
 */
public class MapUtil {

	public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {      
		if (map == null)    
			return null;    
		Object obj = beanClass.newInstance();    
		BeanUtils.populate(obj, map);    
		return obj;    
	}      

	public static Map<?, ?> objectToMap(Object obj) {    
		if(obj == null)    
			return null;     
		return new BeanMap(obj);    
	}      

}
