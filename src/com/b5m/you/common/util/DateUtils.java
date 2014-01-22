/**
 * B5mDateUtils.java
 *
 * 功  能：时间处理工具类
 * 类  名：B5mDateUtils
 *
 *   ver     变更日       公司      作者     变更内容
 * ────────────────────────────────────
 *   V1.00  '12-05-24  iZENEsoft    wiley.wang       初版
 *
 * Copyright (c) 2009 iZENEsoft Business Software corporation All Rights Reserved.
 * LICENSE INFORMATION
 */
package com.b5m.you.common.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 时间处理
 * 
 * @author Wiley
 * @version v1.0
 */
public class DateUtils implements Serializable {

	private static final long serialVersionUID = 6622278926579307357L;
	
	private static final String CONSTANTS_TODAY_KEY = "CONSTANTS_TODAY_KEY";
	
	private static final String CONSTANTS_TOMORROW_KEY = "CONSTANTS_TOMORROW_KEY";
	
	//10min
	private static final int keyMis = 10 * 60;

	public static String getToday() {
		// SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");//ComConstants.SDF_YYYYMMDDHHMMSS;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");// ComConstants.SDF_YYYYMMDDHHMMSS;
		return sdf.format(new Date());
	}

	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// ComConstants.SDF_YYYYMMDDHHMMSS;
		return sdf.format(new Date());
	}

	public static String getToday0() {
		Object obj = XMemCachedUtil.getInstance().getCache(CONSTANTS_TODAY_KEY);
		if(obj != null){
			return (String)obj;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String temp = sdf.format(new Date());
		XMemCachedUtil.getInstance().setCache(CONSTANTS_TODAY_KEY, temp, keyMis);
		return temp;
	}
	
	public static String getTomorrow0() {
		Object obj = XMemCachedUtil.getInstance().getCache(CONSTANTS_TOMORROW_KEY);
		if(obj != null){
			return (String)obj;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String temp = sdf.format(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
		XMemCachedUtil.getInstance().setCache(CONSTANTS_TOMORROW_KEY, temp, keyMis);
		return temp;
	}
	/**
	 * 获取离截止时间的剩余时间
	 * 
	 * @param deadline
	 *            截止时间,格式yyyyMMddHHmmss
	 * @return
	 */
	public static String getRemainingTime(String endDate) {
		String rtn = "";
		if (StringUtils.isBlank(endDate))
			return rtn;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// ComConstants.SDF_YYYYMMDDHHMMSS;//存在线程安全问题，故不能使用静态
		try {
			Date deadline = sdf.parse(endDate);
			long remaining = deadline.getTime() - System.currentTimeMillis();
			// long remaining=lngDeadline-System.currentTimeMillis();
			if (remaining > 0) {
				int ms = (int) (remaining % 1000);
				remaining /= 1000;
				int sc = (int) (remaining % 60);
				remaining /= 60;
				int mn = (int) (remaining % 60);
				remaining /= 60;
				int hr = (int) (remaining % 24);
				long dy = (int) remaining / 24;
				rtn = dy + "天" + hr + "小时" + mn + "分";// + sc + "秒";
			} else {
				rtn = "过期";
			}
		} catch (ParseException e) {

		}
		return rtn;
	}

	public static void main(String[] args) throws Exception {

	}

}
