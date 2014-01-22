package com.b5m.web.core;

import java.io.File;
import java.net.URI;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.b5m.you.common.util.DateUtils;

@SuppressWarnings("unchecked")
public class ContextUtils implements ApplicationContextAware, DisposableBean {

	private static ContextUtils _this = null;
	private static ApplicationContext _applicationContext = null;
	private String rootPath = null;
	private String contextPath = null;
	private String today = null;
	
	private  String today0;
	
	private  String tomorrow0;
	
	/**
	 * 是否打开SF1旅游搜索
	 */
	private boolean openSF1TravelSearch = false;
	/**
	 * 是否打开SF1酒店搜索
	 */
	private boolean openSF1HotelSearch = false;
	/**
	 * 是否打开SF1旅游攻略搜索
	 */
	private boolean openSF1TravelGuideSearch = false;
	private HandlerInterceptorAdapter _intercepter = null;
	private final Map taoCache = Collections.synchronizedMap(new HashMap());

	private ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	private ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();

	private ContextUtils() {
	}
	
	public  String getToday0() {
		return today0;
	}


	public  String getTomorrow0() {
		return tomorrow0;
	}


	public static ContextUtils getInstance() {
		if (_this == null)
			_this = new ContextUtils();
		return _this;
	}

	public void _setApplicationContext(ApplicationContext context) {
		_applicationContext = context;
	}

	public Map getContextCache() {
		return this.taoCache;
	}

	/**
	 * 兼容非WebApplicaion情况下的Spring单元测试用
	 * 
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return _applicationContext;
	}

	/**
	 * 实现了getServletContext()方法
	 * 
	 * @return WebApplicationContext
	 */
	public static WebApplicationContext getWebApplicationContext() {
		return (_applicationContext instanceof WebApplicationContext) ? (WebApplicationContext) _applicationContext : null;
	}

	public static String getRootPathNew(){
		String path = _this.rootPath;
		if(StringUtils.isNotBlank(path)){
			path = path.replace("http://you", "");
		}else{
			path = ".b5m.com";
		}
		return path;
	}
	
	/**
	 * 获取Spring的Bean对象
	 * 
	 * @param beanName
	 *            as String
	 * @return Object
	 */
	public static Object getBean(String beanName) {
		Object obj = null;
		if (_applicationContext.containsBean(beanName)) {
			obj = _applicationContext.getBean(beanName);
		}
		return obj;
	}

	public static <T> T getBean(Class<T> clazz) {

		return _applicationContext.getBean(clazz);
	}

	public static <T> T getBean(String beanName, Class<T> clazz) {
		T t = null;
		if (_applicationContext.containsBean(beanName))
			t = (T) _applicationContext.getBean(beanName, clazz);
		return t;
	}

	public final void _setRootPath(String path) {
		_this.rootPath = path;
	}

	public final String getRootPath() {
		return _this.rootPath;
	}

	public final String getRealPath(String path) {
		File webRoot = new File(_this.rootPath);
		URI webRootUri = webRoot.toURI();
		URI uri = webRootUri.resolve(path);
		return uri.getPath();
	}

	public final void _setContextPath(String path) {
		_this.contextPath = path;
	}

	public final String getContextPath() {
		return _this.contextPath;
	};

	/**
	 * 设置并初始化Jsp页面拦截器
	 * 
	 * @param className
	 *            as String
	 */
	public final void _setIntercepter(HandlerInterceptorAdapter inc) {
		_intercepter = inc;
	}

	/**
	 * 获取Jsp页面拦截器
	 * 
	 * @return
	 */
	public final HandlerInterceptorAdapter getJspIntercepter() {
		return _this._intercepter;
	}

	/**
	 * 获取客户端ip地址
	 * 
	 * @param request
	 * @return
	 */
	public final String getIpAddr() {
		String ip = _this.request.get().getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = _this.request.get().getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = _this.request.get().getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = _this.request.get().getRemoteAddr();
		return ip;
	}

	public final void _setServlet(HttpServletRequest request, HttpServletResponse response) {
		_this.request.set(request);
		_this.response.set(response);
	}

	public final HttpServletRequest getRequest() {
		return _this.request.get();
	}

	public final HttpServletResponse getResponse() {
		return _this.response.get();
	}

	@Override
	public void destroy() throws Exception {
		_setApplicationContext(null);
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		_setApplicationContext(context);
	}

	public String getToday() {
		return _this.today;
	}

	public void _setToday(String today) {
		_this.today = today;
	}

	/*
	 * public static void removeCache(String key){ taoCache.remove(key); }
	 * 
	 * public static void setCache(String key,Object value){ taoCache.put(key, value); }
	 * 
	 * public static Object getCache(String key){ if(key != null){ return taoCache.get(key); } return null; }
	 */

	public void removeCache(String key) {
		taoCache.remove(key);
	}

	public void setCache(String key, Object value) {
		taoCache.put(key, value);
	}

	public Object getCache(String key) {
		if (key != null) {
			return taoCache.get(key);
		}
		return null;
	}


	public boolean isOpenSF1TravelSearch() {
		return openSF1TravelSearch;
	}

	public void setOpenSF1TravelSearch(boolean openSF1TravelSearch) {
		this.openSF1TravelSearch = openSF1TravelSearch;
	}

	public boolean isOpenSF1HotelSearch() {
		return openSF1HotelSearch;
	}

	public void setOpenSF1HotelSearch(boolean openSF1HotelSearch) {
		this.openSF1HotelSearch = openSF1HotelSearch;
	}

	public boolean isOpenSF1TravelGuideSearch() {
		return openSF1TravelGuideSearch;
	}

	public void setOpenSF1TravelGuideSearch(boolean openSF1TravelGuideSearch) {
		this.openSF1TravelGuideSearch = openSF1TravelGuideSearch;
	}

	public void setToday0(String today0) {
		this.today0 = today0;
	}

	public void setTomorrow0(String tomorrow0) {
		this.tomorrow0 = tomorrow0;
	}
}
