/*
 * [文 件 名]:B5MInitServlet.java
 * [创 建 人]:Wiley
 * [创建时间]:2012-10-9
 * [编　　码]:UTF-8
 * [版　　权]:Copyright © 2012 B5Msoft Co,Ltd. 
 */

package com.b5m.web.core;

import java.util.*;
import java.io.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.b5m.you.common.util.DateUtils;
import com.izenesoft.sf1r.config.InitConfigInfoAgen;
import com.izenesoft.sf1r.config.InitSF1RProp;

/**
 * [简要描述]:初始化 [详细描述]:初始化
 * 
 * @author [Wiley]
 * @email [wiley.wang@b5m.com]
 * @version [版本号,2012-10-9]
 * @see [B5MInitServlet]
 * @package [com.b5m.plugin]
 * @since [comb5mpluginserver]
 */
public class B5MInitServlet extends HttpServlet {

	private static final long serialVersionUID = -599037181821679325L;
	public static Logger logger = Logger.getLogger(B5MInitServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init(config);
			WebApplicationContext webCtx = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext(),
					"org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
			ContextUtils.getInstance()._setContextPath(config.getServletContext().getContextPath());
			ContextUtils.getInstance()._setApplicationContext(webCtx);
			ContextUtils.getInstance()._setIntercepter((HandlerInterceptorAdapter) webCtx.getBean("b5mInterceptor"));
			ContextUtils.getInstance()._setToday(DateUtils.getToday());
			ContextUtils.getInstance().setToday0(DateUtils.getToday0());
			ContextUtils.getInstance().setTomorrow0(DateUtils.getTomorrow0());
			Properties prop = new Properties();
			// System.out.println("----------" + config.getServletContext().getRealPath("/") + "---" + this.getClass().getResource("/"));
			InputStream in = new FileInputStream(config.getServletContext().getRealPath("/") + "WEB-INF/config.properties");
			prop.load(in);
			int intervalAds = Integer.parseInt(prop.getProperty("refresh_cache_interval_for_ads"));
			int intervalIndex = Integer.parseInt(prop.getProperty("refresh_cache_interval_for_index"));
			/**
			 * 根据关键字查询旅游数据是否通过SF1
			 */
			String booleanResultTravel = prop.getProperty("key_words_search_from_sf1_travel");
			if(booleanResultTravel==null || "".equals(booleanResultTravel)){
				ContextUtils.getInstance().setOpenSF1TravelSearch(false);
			}else{
				if("TRUE".equals(booleanResultTravel.trim().toUpperCase())){
					ContextUtils.getInstance().setOpenSF1TravelSearch(true);
				}else{
					ContextUtils.getInstance().setOpenSF1TravelSearch(false);
				}
			}
			/**
			 * 根据关键字查询酒店数据是否通过SF1
			 */
			String booleanResultHotel = prop.getProperty("key_words_search_from_sf1_hotel");
			if(booleanResultHotel==null || "".equals(booleanResultHotel)){
				ContextUtils.getInstance().setOpenSF1HotelSearch(false);
			}else{
				if("TRUE".equals(booleanResultHotel.trim().toUpperCase())){
					ContextUtils.getInstance().setOpenSF1HotelSearch(true);
				}else{
					ContextUtils.getInstance().setOpenSF1HotelSearch(false);
				}
			}
			/**
			 * 根据关键字查询旅游攻略数据是否通过SF1
			 */
			String booleanResultTravelGuide = prop.getProperty("key_words_search_from_sf1_travel_guide");
			if(booleanResultTravelGuide==null || "".equals(booleanResultTravelGuide)){
				ContextUtils.getInstance().setOpenSF1TravelGuideSearch(false);
			}else{
				if("TRUE".equals(booleanResultTravelGuide.trim().toUpperCase())){
					ContextUtils.getInstance().setOpenSF1TravelGuideSearch(true);
				}else{
					ContextUtils.getInstance().setOpenSF1TravelGuideSearch(false);
				}
			}
			
			if (intervalAds <= 0) {
				intervalAds = 20;
			}
			if (intervalIndex <= 0) {
				intervalIndex = 60;
			}
			logger.info("---you refresh_cache_interval_for_ads---" + intervalAds);
			logger.info("---you refresh_cache_interval_for_index---" + intervalIndex);
			// 刷新缓存定时任务(广告)
			// new Timer().schedule(new RefreshCacheTask(),0,intervalAds*60*1000);
			// 刷新缓存定时任务(首页)
			// new Timer().schedule(new RefreshIndexCacheTask(),0,intervalIndex*1000);
			//初始化SF1相关配置
			InitSF1RProp init = new InitSF1RProp();
			init.currentWebRoot=config.getServletContext().getRealPath("/");
            InitConfigInfoAgen.mapSF1ConfigInfo = init.initSf1();
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(0);
		}
	}
}
