package com.b5m.you.model;

import java.util.*;

/**
 * Title:HotelKeyWords.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-10-23
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Shengjie Guo
 * 
 * @version 1.0
 */
public class HotelKeyWords {
	private String item1;
	
	private List<Level1> le0;
	
	public String getItem1() {
		return item1;
	}

	public List<Level1> getLe0() {
		return le0;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}

	public void setLe0(List<Level1> le0) {
		this.le0 = le0;
	}

	public class Level1{
		private String item1;//1号线
		private String[] arr;//莘庄#外环路#莲花路#锦江乐园#上海南站#漕宝路#上海体育馆#徐家汇#衡山路#常熟路
		public String getItem1() {
			return item1;
		}
		public String[] getArr() {
			return arr;
		}
		public void setItem1(String item1) {
			this.item1 = item1;
		}
		public void setArr(String[] arr) {
			this.arr = arr;
		}
	}
	
	/*public class Level2{
		private String[] list;

		public String[] getList() {
			return list;
		}

		public void setList(String[] list) {
			this.list = list;
		}
	}*/
}
