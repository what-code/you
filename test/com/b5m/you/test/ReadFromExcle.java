package com.b5m.you.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Title:ReadFromExcle.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-7-9
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Shengjie Guo
 * 
 * @version 1.0
 */
public class ReadFromExcle {

	public void read() {
		InputStream is = this.getClass().getResourceAsStream("city_list1.xls");
		Map map = new HashMap();
		Workbook book;
		try {
			book = Workbook.getWorkbook(is);
			Sheet sheet = book.getSheet(0);
			// 得到第一列第一行的单元格
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			int count = 0;
			for (int i = 0; i < cols; i++) {
				for (int j = 0; j < 40; j++) {
					Cell cell1 = sheet.getCell(i, j);
					String content = cell1.getContents().trim();
					if (!"".equals(content.trim())) {
						count ++;
						System.out.println("["+count + ",\"" + cell1.getContents().trim() + "\"],");
					}
				}
			}
			book.close();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ReadFromExcle().read();
	}
}
