package controllers.wechat.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import controllers.wechat.config.WechatConfig;
import controllers.wechat.sign.MD5;

public class WechatUtil {
	public static String rules = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	public static InputStream getStringStream(String sInputString) {
		ByteArrayInputStream tInputStringStream = null;
		if (sInputString != null && !sInputString.trim().equals("")) {
			tInputStringStream = new ByteArrayInputStream(
					sInputString.getBytes());
		}
		return tInputStringStream;
	}

	public static Map<String, String> getMapFromXML(String xmlString)
			throws ParserConfigurationException, IOException, SAXException {

		// 这里用Dom的方式解析回包的最主要目的是防止API新增回包字段
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputStream is = getStringStream(xmlString);
		Document document = builder.parse(is);

		// 获取到document里面的全部结点
		NodeList allNodes = document.getFirstChild().getChildNodes();
		Node node;
		Map<String, String> map = new HashMap<String, String>();
		int i = 0;
		while (i < allNodes.getLength()) {
			node = allNodes.item(i);
			if (node instanceof Element) {
				map.put(node.getNodeName(), node.getTextContent());
			}
			i++;
		}
		return map;

	}
	
	public static String createNonceSstr() {
		int rpoint = 0;
		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		int length = 32;
		for (int i = 0; i < length; i++) {
			if (rules != null) {
				rpoint = rules.length();
				int randNum = rand.nextInt(rpoint);
				generateRandStr.append(rules.substring(randNum, randNum + 1));
			}
		}
		return generateRandStr + "";
	}
	
	public static String createMapSign(Map<String,String> map){
		Collection<String> keyset = map.keySet();

		List<String> list = new ArrayList<String>(keyset);

		// 对key键值按字典升序排序
		Collections.sort(list);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {

			String fieldKey = list.get(i);
			if (i != list.size() - 1) {
				buffer.append(fieldKey).append("=")
						.append(String.valueOf(map.get(fieldKey))).append("&");
			} else {
				buffer.append(fieldKey).append("=")
						.append(String.valueOf(map.get(fieldKey)));
			}
		}
		String key = "&key="+WechatConfig.KEY;
		return MD5.MD5Encode(buffer.append(key).toString()).toUpperCase();
	}
	
	public static boolean checkSign(Map<String,String>map,String sign){
		Collection<String> keyset = map.keySet();

		List<String> list = new ArrayList<String>(keyset);

		// 对key键值按字典升序排序
		Collections.sort(list);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {

			String fieldKey = list.get(i);
			if (i != list.size() - 1) {
				buffer.append(fieldKey).append("=")
						.append(String.valueOf(map.get(fieldKey))).append("&");
			} else {
				buffer.append(fieldKey).append("=")
						.append(String.valueOf(map.get(fieldKey)));
			}
		}
		String localSign = MD5.MD5Encode(buffer.toString()).toUpperCase();
		if(sign.equals(localSign)){
			return true;
		}else{
			return false;
		}
	}
	
	public static String paySign(Map<String,String>map){
		Collection<String> keyset = map.keySet();

		List<String> list = new ArrayList<String>(keyset);

		// 对key键值按字典升序排序
		Collections.sort(list);
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {

			String fieldKey = list.get(i);
			if (i != list.size() - 1) {
				buffer.append(fieldKey).append("=")
						.append(String.valueOf(map.get(fieldKey))).append("&");
			} else {
				buffer.append(fieldKey).append("=")
						.append(String.valueOf(map.get(fieldKey)));
			}
		}
		String localSign = MD5.MD5Encode(buffer.toString()).toUpperCase();
		return localSign;
	}
	
	public static String createSign(Object wechatPayData) {
		HashMap wechatmap = getFieldName(wechatPayData);
		return createMapSign(wechatmap);
	}

	public static HashMap<String, Object> getFieldName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < fields.length; i++) {
			String fieldname = fields[i].getName();
			Object value = getFieldValue(o, fieldname);
			if (value != null) {
				String fieldKey = fieldname;
				if(fieldname.equals("Wxpackage")){
					fieldKey = "package";
				}
				map.put(fieldKey, getFieldValue(o, fieldname));
			}
		}
		return map;
	}

	public static String[] getFiledNameArr(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		List<String> str = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			String fieldname = fields[i].getName();
			Object value = getFieldValue(o, fieldname);
			if (value != null) {
				str.add(String.valueOf(value));
			}
		}
		Object[] array = str.toArray();
		String[] result = new String[array.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = (String) array[i];
		}
		return result;
	}

	public static Object getFieldValue(Object owner, String fieldName) {

		return invokeMethod(owner, fieldName, null);
	}

	private static Object invokeMethod(Object owner, String fieldName,
			Object[] args) {
		Class<? extends Object> ownerClass = owner.getClass();

		// fieldName -> FieldName
		String methodName = fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);

		Method method = null;
		try {
			method = ownerClass.getMethod("get" + methodName);
		} catch (SecurityException e) {
			// e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// e.printStackTrace();
			return "";
		}

		// invoke getMethod
		try {
			return method.invoke(owner);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String formatNumber(Long number){
		if(number==null){
			number=0L;
		}
		String result=new BigDecimal(number).divide(new BigDecimal(100)).toString();
		return result==null ? "0":result;
	}
	public static void main(String[] args) {
		
	}
}
