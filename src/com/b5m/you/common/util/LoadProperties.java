package com.b5m.you.common.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Title:LoadProperties.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-5-15
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Shengjie Guo
 * 
 * @version 1.0
 */
public class LoadProperties {
	public static String getProperties(String key){
		InputStream in;
		Properties p = null;
		try {
			in = LoadProperties.class.getClassLoader().getResourceAsStream("config.properties");
			p = new Properties();
			p.load(in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p.getProperty(key);
	}
}
