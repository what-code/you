package com.b5m.you.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Title:CollectionsSortTest.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-8-8
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Jia Liu
 * 
 * @version 1.0
 */
public class CollectionsSortTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test();
	}

	public static void test() {
		Map<Integer, String> mapAll = new HashMap<Integer, String>();
		mapAll.put(2, "上海");
		mapAll.put(1, "北京");
		mapAll.put(17, "杭州");
		mapAll.put(14, "苏州");

		List<Map.Entry<Integer, String>> list = new ArrayList<Map.Entry<Integer, String>>(mapAll.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
			public int compare(Map.Entry<Integer, String> mapping1, Map.Entry<Integer, String> mapping2) {
				return mapping1.getKey().compareTo(mapping2.getKey());
			}
		});
		LinkedHashMap<String, String> rMap = new LinkedHashMap<String, String>();
		for (Map.Entry<Integer, String> keys : list) {
			rMap.put(keys.getKey().toString(), keys.getValue());
			System.out.println(keys.getKey() + ":" + keys.getValue());
		}
		System.out.println(rMap.keySet());
	}
}
