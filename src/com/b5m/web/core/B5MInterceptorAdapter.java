package com.b5m.web.core;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.b5m.you.common.util.CookieUtils;
import com.b5m.you.common.util.DateUtils;
import com.b5m.you.common.util.MemCachedUtil;
import com.b5m.you.common.util.XMemCachedUtil;

public class B5MInterceptorAdapter extends HandlerInterceptorAdapter {

	public static Logger logger = Logger.getLogger(B5MInterceptorAdapter.class);

	// SSO用到的用户id 长度为32
	static String USER_ID_COOKIE = "token";
	// SSO用到的用户名称
	static String USER_NAME_COOKIE = "name";
	// SSO用到的用户昵称
	static String USER_NICK_NAME_COOKIE = "nickname";
	// SSO用到的mail
	static String USER_MAIL_COOKIE = "email";
	// SSO用到的登录状态
	static String LOGIN_FLAG_COOKIE = "login";
	// SSO用到的用户显示名称
	static String USER_SHOW_NAME_COOKIE = "showname";
	// SSO用到的登录状态
	static String STATUS_OK = "true";
	// SSO用到的非登录状态
	static String STATUS_OFF = "false";
	// SSO用到的userId 长度默认32 位
	static int USER_ID_LENGTH = 32;
	// SSO用到的memcache 缓存 前缀
	static String USER_STATUS_PREFIX = "usercenter_status_";
	// SSO用到的session 存储的key
	static String USER_SESSION_USER_ID = "user_session_key_id";

	static String USER_SESSION_USER_NAME = "user_session_name_id";

	static String USER_SESSION_FLAG = "user_session_login";

	static String USER_SESSION_USER_MAIL = "user_session_email_id";

	static String USER_SESSION_USER_NICK_NAME = "user_session_nick_name";

	static String USER_SESSION_USER_SHOW_NAME = "user_session_show_name";

	static String COOKIE_UTMZ = "__utmz";

	static String COOKIE_SESSION_UTMZ = "session_utmz";
	
	static String COOKIE_YOU_TAG = "you_tag";

	// 利用正则映射到不需要拦截的路径
	private static List<String> excludeMappingURLs = new ArrayList<String>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		ContextUtils.getInstance()._setServlet(request, response);
		// ContextUtils.getInstance()._setContextPath(path);
		if (handler != null && handler instanceof AbstractBaseController) {
			// 注入全部的request和response对象
			((AbstractBaseController) handler)._setServlet(request, response);
		}

		String url = request.getRequestURL().toString();
		// 排除那些不需要拦截url
		for (String excludeMappingURL : excludeMappingURLs) {
			if (url.contains(excludeMappingURL)) {
				return true;
			}
		}

		// 过滤cookie memcache
		Cookie[] cks = request.getCookies();
		// 先后从cookie，memcache userId 和 isLogin 判断 用户 是否登陆
		if (null != cks && cks.length > 0) {
			String userId = CookieUtils.getCooKieValue(USER_ID_COOKIE, cks);
			String isLogin = CookieUtils.getCooKieValue(LOGIN_FLAG_COOKIE, cks);
			String name = CookieUtils.getCooKieValue(USER_NAME_COOKIE, cks);
			String nickName = CookieUtils.getCooKieValue(USER_NICK_NAME_COOKIE, cks);
			String mail = CookieUtils.getCooKieValue(USER_MAIL_COOKIE, cks);
			String showName = CookieUtils.getCooKieValue(USER_SHOW_NAME_COOKIE, cks);

			// 淘宝客feedback
			// String feedback = CookieUtils.getCooKieValue(COOKIE_UTMZ, cks);
			// request.setAttribute(COOKIE_SESSION_UTMZ, feedback);
			try {
				if (null != userId && isLogin != null) {
					if (isLogin.equals(STATUS_OK) && userId.length() == USER_ID_LENGTH
							&& STATUS_OK.equals(MemCachedUtil.getCache(USER_STATUS_PREFIX + userId))) {
						request.setAttribute(USER_SESSION_USER_ID, userId);
						request.setAttribute(USER_SESSION_USER_NAME, URLDecoder.decode(name, "UTF-8"));
						request.setAttribute(USER_SESSION_USER_NICK_NAME, URLDecoder.decode(nickName, "UTF-8"));
						request.setAttribute(USER_SESSION_USER_SHOW_NAME, URLDecoder.decode(showName, "UTF-8"));
						request.setAttribute(USER_SESSION_USER_MAIL, mail);
						request.setAttribute(USER_SESSION_FLAG, STATUS_OK);
						logger.info("is set session :userId----" + userId + "----nickName----" + URLDecoder.decode(nickName, "UTF-8") + "---mail----" + mail
								+ "-----showName-----" + URLDecoder.decode(showName, "UTF-8"));
					} else {
						request.setAttribute(USER_SESSION_FLAG, STATUS_OFF);
					}
				} else {
					request.setAttribute(USER_SESSION_FLAG, STATUS_OFF);
				}
			} catch (Exception e) {
				logger.info("--------memcached请求超时---------");
			}
		}
		/**
		 * you tag(cookie)
		 */
		/*if(null != cks && cks.length > 0 && StringUtils.isBlank(CookieUtils.getCooKieValue(COOKIE_YOU_TAG, cks))){
			Cookie ck1 = new Cookie(COOKIE_YOU_TAG, "b5m_you");
			ck1.setMaxAge(24 * 60 * 60);
			response.addCookie(ck1);
		}*/
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (null == modelAndView)
			return;
		String contentPath = ContextUtils.getInstance().getContextPath();
		String rootPath = request.getScheme() + "://" + request.getServerName()
				+ (request.getServerPort() != 80 ? ":" + request.getServerPort() : "") + contentPath;
		ContextUtils.getInstance()._setRootPath(rootPath);
		modelAndView.addObject("rootPath", rootPath);
		String today = ContextUtils.getInstance().getToday();
		modelAndView.addObject("defaultToday", DateUtils.getToday0());
		modelAndView.addObject("defaultTomorrow",DateUtils.getTomorrow0());
		Object version = MemCachedUtil.getInstance().getCache("v_version");
		if(version != null){
			today = version.toString();
		}
		modelAndView.addObject("today", today);
		modelAndView.addObject("hour", Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
		modelAndView.addObject("nowDate", new Date().getTime());
		super.postHandle(request, response, handler, modelAndView);
	}

	public void setExcludeMappingURLs(List<String> excludeMappingURLs) {
		B5MInterceptorAdapter.excludeMappingURLs = excludeMappingURLs;
	}
}
