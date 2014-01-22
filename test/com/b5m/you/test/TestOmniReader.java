package com.b5m.you.test;

import static org.junit.Assert.fail;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

public class TestOmniReader extends com.b5m.web.core.AbstractBaseController{

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		System.out.println("ip:" + getRemortIP(getRequest()));
	}

	private String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
