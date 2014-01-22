package com.b5m.you.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Calendar;

/**
 * Title:TestReadText.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-7-29
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Jia Liu
 * 
 * @version 1.0
 */
public class TestReadText {

	/**
	 * @param args
	 */
	public static void mainReadText(String[] args) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("/home/jia-liu/无标题文档"));
			while (br.ready()) {
				System.out.println(br.readLine());
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		File fIs = new File("/home/jia-liu/图片/未命名.jpg");
		FileInputStream is = new FileInputStream(fIs);
		String fileName = fIs.getName();
		String prefix = fileName.substring(fileName.lastIndexOf("."));
		Calendar calendar = Calendar.getInstance();
		FileOutputStream os = new FileOutputStream("/home/jia-liu/" + calendar.getTime().toString() + prefix);
		byte[] b = new byte[(int) fIs.length()];
		while (is.read(b) > 0) {
			os.write(b);
		}
		if (null != is) {
			is.close();
		}
		if (null != os) {
			os.close();
		}
	}
}
