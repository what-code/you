package com.b5m.you.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.Cookie;



/**
 * Cookies 的工具类
 *
 */
public class CookieUtils {
    

	
	 /**
	  * 获取cookie 值
	  * @param name
	  *            cookie 的 name
	  * @param cks  
	  *            cookie数组
	  * @return value
	  */
	 public  static String getCooKieValue(String name, Cookie[] cks) {
			String result = null;
			for (Cookie item : cks) {
				if (item != null && item.getName().equals(name)) {
					try {
						result = URLDecoder.decode(item.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					break;
				}
			}
			return result;
		}
	
	
}
