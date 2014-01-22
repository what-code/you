package com.b5m.you.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class GlobalWebCfg {
	public static String bendi = "localhost";
	public static String stage = "ucenter.stage.bang5mai.com";	
	public static String prod = "ucenter.prod.bang5mai.com" ;
	public static String b5m ="ucenter.b5m.com";  
    


	public static String getUcenterUrl(String hostName) {
		if (isLocal(hostName)) {
			return bendi;
		} else if (hostName.indexOf("stage.bang5mai.com") > -1) {
			return stage;
		}  else if (hostName.indexOf("prod.bang5mai.com") > -1) {
			return prod;
		} else if (hostName.indexOf("b5m.com") > -1) {
			return b5m;
		} else {
			return hostName;
		}
	}	

	public static String getDomain(String hostName) {
		if (isLocal(hostName)) {
			return "";
		} else if (hostName.indexOf(".bang5mai.com") > -1) {
			return ".bang5mai.com";
		} else if (hostName.indexOf(".b5m.com") > -1) {
			return ".b5m.com";
		} else {
			return hostName;
		}
	}
	
	private static boolean isLocal(String hostName) {
		if(StringUtils.isBlank(hostName)){
			return true ;
		}
		 return hostName.startsWith("127.0.0.1")
				|| hostName.startsWith("localhost");
	}

	

	public static String getHttpUrl(HttpServletRequest request) {
		return request.getScheme()
				+ "://"
				+ getUcenterUrl(request.getServerName())
				+ (request.getServerPort() != 80 ? ":"
						+ request.getServerPort() : "") + "/";
	}

	

}
