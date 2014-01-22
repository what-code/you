package com.b5m.web.core;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.b5m.you.common.util.StartCity;
import com.b5m.you.dto.YouSearchDto;

public class Constants {

	private static Logger logger = Logger.getLogger(Constants.class);

	/** 请求或发送数据时的默认编码,UTF-8 */
	public static String DEFAULT_ENCODING = "UTF-8";

	/** HTML类型文档的ContentType,text/html;charset=$default_encoding */
	public final static String HTML_CONTENT_TYPE = "text/html;charset=" + DEFAULT_ENCODING;

	public final static String XML_CONTENT_TYPE = "text/xml;charset=" + DEFAULT_ENCODING;

	/** application/x-json */
	public final static String JSON_CONTENT_TYPE = "application/x-json";

	/** 数据类型 */
	public final static int TYPE_STRING = Types.VARCHAR;
	public final static int TYPE_INTEGER = Types.INTEGER;
	public final static int TYPE_LONG = Types.BIGINT;
	public final static int TYPE_BOOLEAN = Types.BOOLEAN;
	public final static int TYPE_DOUBLE = Types.DOUBLE;
	public final static int TYPE_FLOAT = Types.FLOAT;
	public final static int TYPE_DATE = Types.DATE;
	public final static int TYPE_ARRAY = Types.ARRAY;

	/** 数据类型映射表 */
	public final static Map<String, Integer> TYPE_MAPPING = new HashMap<String, Integer>();

	public final static Map<String, String> POINTS_TYPE_MAPPING = new HashMap<String, String>();

	/** 数据来源地 */
	public final static Map<String, String> DATA_SOURCE = new HashMap<String, String>();

	/**
	 * 解析xml的城市列表 格式:1--->北京
	 */
	public final static Map<String, String> XML_CITY = new HashMap<String, String>();

	/**
	 * 解析xml的城市列表Key
	 * 
	 * 格式:北京--->1
	 */
	public final static Map<String, String> XML_CITY_KEY = new HashMap<String, String>();

	public final static List<String> REG_LIST = new ArrayList<String>();

	static {
		TYPE_MAPPING.put("string", TYPE_STRING);
		TYPE_MAPPING.put("integer", TYPE_INTEGER);
		TYPE_MAPPING.put("long", TYPE_LONG);
		TYPE_MAPPING.put("boolean", TYPE_BOOLEAN);
		TYPE_MAPPING.put("double", TYPE_DOUBLE);
		TYPE_MAPPING.put("float", TYPE_FLOAT);
		TYPE_MAPPING.put("date", TYPE_DATE);
		TYPE_MAPPING.put("array", TYPE_ARRAY);
		POINTS_TYPE_MAPPING.put("0", "元充值卡");
		POINTS_TYPE_MAPPING.put("1", "Q币");

		DATA_SOURCE.put("ctrip", "携程");
		DATA_SOURCE.put("qunar", "去哪儿");
		DATA_SOURCE.put("elong", "艺龙");
		DATA_SOURCE.put("uzai", "悠哉");
		DATA_SOURCE.put("lvmama", "驴妈妈");

		XML_CITY.putAll(StartCity.getInstance().readHotCity());
		XML_CITY_KEY.putAll(StartCity.getInstance().readHotCityKey());

		REG_LIST.add("<br>");
		REG_LIST.add("<br/>");
		REG_LIST.add("&lt;br&gt;");
		REG_LIST.add("&lt;br/&gt;");

		REG_LIST.add("<A href=");
		
		REG_LIST.add("<BR>");
		REG_LIST.add("<BR/>");
		REG_LIST.add("&lt;BR&gt;");
		REG_LIST.add("&lt;BR/&gt;");
		
		REG_LIST.add("<b>");
		REG_LIST.add("</b>");
		REG_LIST.add("&lt;b&gt;");
		REG_LIST.add("&lt;/b&gt;");
	}

	public final static String LOG_NAME = "plugin.b5m.log";
	public final static String MSG_FORMAT_START = "[";
	public final static String MSG_FORMAT_END = "]";
	public final static String MSG_FORMAT_SPLIT = " ";

	public static String USER_STATUS_PREFIX = "usercenter_status_";

	public static String USER_SESSION_USER_ID = "user_session_key_id";

	public static String USER_SESSION_USER_NAME = "user_session_name_id";

	public static String USER_SESSION_FLAG = "user_session_login";

	public static String USER_SESSION_USER_NICK_NAME = "user_session_nick_name";

	public static String USER_SESSION_USER_SHOW_NAME = "user_session_show_name";

	public static String USER_SESSION_USER_MAIL = "user_session_email_id";

	// 默认人气包邮个数
	public static int DEFAULT_HOT_COUNT = 12;
	// 默认现价
	public static String DEFAULT_SALES_PRICE = "9.9";
	// 默认包邮
	public static String DEAFAULT_POSTAL = "1";
	// 搜索结果页面中--最人气--图片每行条数
	public static int PER_LINE_COUNT = 3;

	// 默认每页大小
	public static int DEFAULT_PAGE_SIZE = 24;
	// 默认明日预告每页大小
	public static int DEFAULT_MINGRI_PAGE_SIZE = 6;
	// 默认当前页码
	public static int DEFAULT_CURRENT_PAGE_NO = 1;
	// 详细页评论页码
	public static int DETAILED_PAGE_SIZE = 10;

	// YOU 同类商品推荐数目
	public static int YOU_RECOMMEND_SZIE = 12;

	// YOU 着陆页热门游
	public static int YOU_LANDING_SZIE = 6;

	// 国内游
	public static String MENU_DOMESTIC_TRAVEL = "domestic";
	// 境外游
	public static String MENU_ABROAD_TRAVEL = "abroad";
	// 周边游
	public static String MENU_PERIPHERY_TRAVEL = "periphery";

	// 首页
	public static String INDEX = "index";

	// 国内游
	public static String DOMESTIC_TRAVEL = "domestic";

	// 境外游
	public static String ABROAD_TRAVEL = "abroad";

	// 周边游
	public static String PERIPHERY_TRAVEL = "periphery";

	// 酒店
	public static String YOU_HOTEL = "hotel";

	// trip
	public static String SEARCH = "searchresult";

	// hotel_index:首页的【酒店】搜索请求
	public static String INDEX_HOTEL_SEARCH = "ihs";
	// hotel_trip:首页的【旅游】搜索请求
	public static String INDEX_TRIP_SEARCH = "its";
	// hotel_note:首页的【攻略】搜索请求
	public static String INDEX_NOTE_SEARCH = "ins";

	// hotel
	public static String HOTEL_SEARCH = "hotel";

	// notes
	public static String NOTES_SEARCH = "noteSearchresult";

	// md5加密key
	public static String B5M_KEY = "b5m";

	// 轮播专题缓存
	public static String B5M_YOU_MEM_CACHE_CAROUSEL = "b5mYouMemCacheCarousel";
	// 首页专题缓存
	public static String B5M_YOU_MEM_CACHE_INDEX_TOPIC = "b5mYouMemCacheIndexTopic";
	// 数据源缓存
	public static String B5M_YOU_MEM_CACHE_SOURCE = "b5mYouMemCacheSource";

	// ip定位
	public static String B5M_YOU_IP_LOCATE = "b5mYouIpLocate";

	// 酒店城市
	public static String B5M_YOU_HOTEL_CITY = "b5mYouHotelCity";

	// 首页酒店城市
	public static String B5M_YOU_INDEX_HOTEL_CITY_MEM_CACHE_KEY = "b5mYouIndexHotelCity";

	// 帮5游城市
	public static String B5M_YOU_CITY = "b5mYouCity";

	// site map trip
	public static String B5M_SITE_MAP = "b5mSiteMap";

	// site map hotel
	public static String B5M_SITE_MAP_HOTEL = "b5mSiteMapHotel";

	// 同类YOU产品
	public static String B5M_YOU_SAME_TYPE = "B5M_YOU_SAME_TYPE_";
	// 同城市酒店
	public static String B5M_HOTEL_SAME_CITY = "B5M_HOTEL_SAME_CITY_";

	// 着陆页面缓存key
	public static String B5M_YOU_LANDING_GUIDE_MEM_CACHE_KEY = "b5mYouLandingGuide";

	public static String NOTES_HOT = "hotNotes";

	public static String NOTES_NEW = "newNotes";

	public final static Map<String, String> HOTEL_INT_CHN_MAP = new HashMap<String, String>();
	static {
		HOTEL_INT_CHN_MAP.put("0", "三星以下");
		HOTEL_INT_CHN_MAP.put("1", "三星以下");
		HOTEL_INT_CHN_MAP.put("2", "三星以下");
		HOTEL_INT_CHN_MAP.put("3", "三星");
		HOTEL_INT_CHN_MAP.put("4", "四星");
		HOTEL_INT_CHN_MAP.put("5", "五星");
		HOTEL_INT_CHN_MAP.put("1000", "五星以上");
	}
	
	public final static Map<String, String> TRIP_EN_CHN_MAP = new HashMap<String, String>();
	static {
		TRIP_EN_CHN_MAP.put("periphery", "周边");
		TRIP_EN_CHN_MAP.put("domestic", "国内");
		TRIP_EN_CHN_MAP.put("abroad", "境外");
	}

	/**
	 * 获取本地城市
	 * 
	 * @param dto
	 * @return
	 */
	public final static String getLocate(YouSearchDto dto) {
		dto.setIpLocate("");
		String currCity = dto.getIpLocate();

		logger.info("当前城市:" + currCity);

		// currCity
		if (currCity == null || "".equals(currCity.trim())) {
			currCity = "全部";
			// 地址获取失败
			dto.setSelectedCityId("-1");
		} else {
			// 获得城市地址
			dto.setSelectedCityId(Constants.XML_CITY_KEY.get(currCity));
		}
		dto.setSelectedCity(currCity);
		return currCity;
	}

	// page mode
	public final static String PAGE_MODE_LIST = "list";

	public final static String PAGE_MODE_SEARCH = "search";

	public final static String PAGE_MODE_MAP = "map";

	public final static String PAGE_MODE_METRO = "metro";

	public final static String YOU_ALL_MAP_KEYWORDS = "YOU_ALL_MAP_KEYWORDS";

	public final static String YOU_HOTEL_METRO_LINE = "YOU_HOTEL_METRO_LINE";

	public final static String B5M_YOU_TOPIC_CACHE_MAIN = "B5M_YOU_TOPIC_CACHE_MAIN";

	public final static String B5M_TAO_TOPIC_CACHE_BAR = "B5M_TAO_TOPIC_CACHE_BAR";
}
