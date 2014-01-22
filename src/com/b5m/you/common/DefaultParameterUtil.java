package com.b5m.you.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;

public class DefaultParameterUtil {

	/**
	 * 处理参数
	 * @param parmters
	 *             获取参数
	 * @param defaultParamter
	 *             默认参数	
	 */
	public static String dealParamters(String parmters,String defaultParamter){			
	    return StringUtils.isBlank(parmters) == true ? defaultParamter : parmters ;			
	}
	
	
	/**
	 * 处理 分页页码参数
	 * @param pageNo 参数页码 String
	 * @return  页码
	 */
	public static int dealParamtersPageNo(String pageNo){	
		try{
			return dealParamtersPageNo(Integer.valueOf(pageNo));
		}catch(Exception e){
	    	return 0 ;
	    }
	}	   
	
	
	/**
	 * 处理 分页页码参数
	 * @param pageNo 参数页码 Integer
	 * @return  页码
	 */
	public static int dealParamtersPageNo(Integer pageNo){	
		if(null == pageNo || 0 == pageNo){
			return 1 ;
		}else{			
	       return pageNo ;
		}
	}

	
	/**
	 *  解析中文参数
	 * @param paramter 前端javascript 传递的 encode中文参数
	 * @return  解析参数后的中文参数
	 */
	public static String dealChineseParamters(String encodeParamter){
		String decodeString = "" ;				
		try {
			decodeString = URLDecoder.decode(encodeParamter,"UTF-8");
			return decodeString ;
		} catch (UnsupportedEncodingException e) {
			return decodeString ;
		}
			
		
		
		
	}

}
