package com.b5m.you.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.*;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.b5m.web.core.AbstractBaseService;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.Constants;
import com.b5m.you.common.util.HttpRequest;
import com.b5m.you.common.util.XMemCachedUtil;
import com.b5m.you.dao.IYouHotelDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.HotelKeyWords;
import com.b5m.you.model.HotelMapKeyWord;
import com.b5m.you.model.YouHotel;
import com.b5m.you.model.HotelKeyWords.Level1;
import com.b5m.you.service.IYouHotelService;

/**
 * Title:YouHotelServiceImpl.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-7-17
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Shengjie Guo
 * 
 * @version 1.0
 */
@Service("hotelService")
public class YouHotelServiceImpl extends AbstractBaseService<YouHotel> implements IYouHotelService {
	public static Logger logger = Logger.getLogger(YouHotelServiceImpl.class);
	@Resource
	private IYouHotelDao dao;

	@Override
	public B5MPageList<YouHotel> getYouHotelList(YouSearchDto ttDto) {
		String key = getMemCachedKey(ttDto);
		//推荐部分
		if("1".equals(ttDto.getIsRecommendtion())){
			key = Constants.B5M_YOU_SAME_TYPE + "_hotel";
		}
		logger.info("hotel--key-->" + key);
		B5MPageList<YouHotel> list = (B5MPageList<YouHotel>) getDataFromCache(ttDto, key, 0, "");
		//对当前页码超过总页数的处理
		if(list.getTotalPages() > 0 && ttDto.getCurrPageNo() > list.getTotalPages()){
			ttDto.setCurrPageNo(list.getTotalPages());
			return getYouHotelList(ttDto);
		}
		return list;
	}
	
	public  B5MPageList<YouHotel> getYouHotelTempList(YouSearchDto ttDto) {
		long mis = new Date().getTime();
		boolean flag = false;
		if("-1".equals(ttDto.getSelectedCityId())){
			Map mapKey = getMapKeyWordsList(ttDto);
			flag = isKeyWordInMap(ttDto.getKeyWords(),mapKey);
			ttDto.setKeyWordInMap(flag);
		}
		
		// 列表页的key
		String key = getMemCachedKey(ttDto) + flag;
		logger.info(ttDto.getKeyWords() + "---->" + flag);
		if(flag && Constants.PAGE_MODE_MAP.equals(ttDto.getPageMode()) && !"-1".equals(ttDto.getSelectedCityId()) && StringUtils.isNotBlank(ttDto.getKeyWords())){
			String city = Constants.XML_CITY.get(ttDto.getSelectedCityId());
			String pointsKey = "POINTS_KEY_" + city + "_" + ttDto.getKeyWords();
			String points = (String)getDataFromCache(ttDto,pointsKey,9,city);
			//logger.info("--t1--->" + (new Date().getTime() - mis));
			String[] locations = points.split("#");
			//用纬度,经度的范围过滤数据
			if("0".equals(locations[4])){
				//纬度,经度的范围
				String[] range = getDistanceRange(Double.parseDouble(locations[1]),Double.parseDouble(locations[2]),1000).split("#");
				ttDto.setLatitudeRange(range[0]);
				ttDto.setLongitudeRange(range[1]);
				ttDto.setLatitude(locations[1]);
				ttDto.setLongitude(locations[2]);
			}
			//logger.info("--t2--->" + (new Date().getTime() - mis));
		}
		// 详情页的key
		if (Constants.B5M_HOTEL_SAME_CITY.equals(ttDto.getSearchType())) {
			key = Constants.B5M_HOTEL_SAME_CITY  +ttDto.getIpLocate() + "_" + ttDto.getSelectedCityId();
		}
		B5MPageList<YouHotel> list = (B5MPageList<YouHotel>) getDataFromCache(ttDto, key, 0, "");
		//logger.info("--t3--->" + (new Date().getTime() - mis));
		return list;
	}

	/**
	 * 地铁沿线的酒店列表
	 */
	@Override
	public B5MPageList<YouHotel> getMetroHotelList(YouSearchDto dto) {
		String key = getMemCachedKey(dto);
		return (B5MPageList<YouHotel>)getDataFromCache(dto,key,12,"");
	}
	
	@Override
	public List<YouHotel> findsIndexHotelByList(YouSearchDto ttDto) {
		String key = Constants.B5M_YOU_INDEX_HOTEL_CITY_MEM_CACHE_KEY;
		return (List<YouHotel>) getDataFromCache(ttDto, key, 1, "");
	}

	@Override
	public YouHotel getYouHotelById(YouSearchDto ttDto) {
		String key = getMemCachedKey(ttDto) + "_detail";
		return (YouHotel) getDataFromCache(ttDto, key, 2, "");
	}

	@Override
	public Map<String, String> getHotelCity() {
		String key = Constants.B5M_YOU_HOTEL_CITY;
		return (Map<String, String>) getDataFromCache(null, key, 3, "");
	}

	@Override
	public List<YouHotel> findsLandingPageByList(YouSearchDto ttDto, String cId) {
		String key = Constants.B5M_YOU_LANDING_GUIDE_MEM_CACHE_KEY + ttDto.getClikcId() + "_" + cId;
		return (List<YouHotel>) getDataFromCache(ttDto, key, 4, cId);
	}

	@Override
	public List<YouHotel> findsNotesPageByList(String city, YouSearchDto dto) {
		String location = Constants.XML_CITY_KEY.get(dto.getIpLocate());
		if (!StringUtils.isBlank(city))
			dto.setIpLocate(city);
		String key = "findsNotesPageByList_" + "city_" + getMemCachedKey(dto);
		List<YouHotel> data = (List<YouHotel>) getDataFromCache(dto, key, 5, "");
		dto.setIpLocate(location);
		return data;
	}

	/**
	 * 二级缓存 工具方法
	 * 
	 * @param ttDto
	 * @param hotCount
	 * @param youType
	 * @param type
	 * @return
	 */
	private Object getDataFromCache(YouSearchDto ttDto, String key, int type, String cId) {
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if (flag) {
				logger.info("----you_hotel_from_db--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);
				if (type == 0) {
					data = dao.getYouHotelList(ttDto);
				} else if (type == 1) {
					data = dao.findsIndexHotelByList(ttDto);
				} else if (type == 2) {
					data = dao.getYouHotelById(ttDto);
				} else if (type == 3) {
					data = getMapCity();
				} else if (type == 4) {
					data = dao.findsLandingPageByList(ttDto, cId);
				} else if (type == 5) {
					data = dao.findsNotesPageByList(ttDto);
				}else if (type == 6) {
					data = dao.getMapSite(ttDto);
				}else if (type == 7) {
					data = dao.getMapKeyWordsList();
				}else if (type == 8) {
					List<HotelMapKeyWord> list = (List)getDataFromCache(ttDto,Constants.YOU_ALL_MAP_KEYWORDS,7,"");
					data = getSubMapList(list,ttDto);
				}else if (type == 9) {
					data = getLatAndLngByKeyWord(cId,ttDto.getKeyWords());
				}else if(type == 10){
					String type10Key = Constants.YOU_ALL_MAP_KEYWORDS + "_" + ttDto.getSelectedCityId();
					data = getMetroStation((List)getDataFromCache(ttDto,type10Key,8,""));
				}else if(type == 11){
					data = getHotelsNearByStations(ttDto);
				}else if(type == 12){
					data = getHotelsNearByPage(ttDto);
				}else if(type == 13){
					data = dao.findTopicYouHotels(ttDto);
				}else if(type == 14){
					List list = (List)getDataFromCache(ttDto,cId,8,"");
					data = processBizList(list);
				}
				// site map/points 缓存时间长点，1 day
				if(type == 6 || type == 7 || type == 8 || type == 9){
					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_20DAY);
				}else{
					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_hotel_from_db---90--######---");
				if (data == null) {
					logger.info("----you_hotel_from_db---90--?????????????????-->" + key);
					if (type == 0) {
						data = dao.getYouHotelList(ttDto);
					} else if (type == 1) {
						data = dao.findsIndexHotelByList(ttDto);
					} else if (type == 2) {
						data = dao.getYouHotelById(ttDto);
					} else if (type == 3) {
						data = getMapCity();
					} else if (type == 4) {
						data = dao.findsLandingPageByList(ttDto, cId);
					} else if (type == 5) {
						data = dao.findsNotesPageByList(ttDto);
					}else if (type == 6) {
						data = dao.getMapSite(ttDto);
					}else if (type == 7) {
						data = dao.getMapKeyWordsList();
					}else if (type == 8) {
						List<HotelMapKeyWord> list = (List)getDataFromCache(ttDto,Constants.YOU_ALL_MAP_KEYWORDS,7,"");
						data = getSubMapList(list,ttDto);
					}else if (type == 9) {
						data = getLatAndLngByKeyWord(cId,ttDto.getKeyWords());
					}else if(type == 11){
						data = getHotelsNearByStations(ttDto);
					}else if(type == 12){
						data = getHotelsNearByPage(ttDto);
					}else if(type == 14){
						List list = (List)getDataFromCache(ttDto,cId,8,"");
						data = processBizList(list);
					}
					// site map 缓存时间长点，1 day
					if(type == 6 || type == 7 || type == 8 || type == 9){
						XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
						XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_20DAY);
					}else{
						XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
						XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
					}
				}
			}

		}
		if (type == 0 && ttDto.getClikcId() != null && (Constants.B5M_HOTEL_SAME_CITY.equals(ttDto.getSearchType()))) {
			data = getDistinctGoods((B5MPageList) data, ttDto.getClikcId());
		}
		return data;
	}

	/**
	 * 获取城市集合
	 * 
	 * @param ttDto
	 * @return
	 */
	private LinkedHashMap<String, String> getMapCity() {
		LinkedHashMap<String, String> youMap = new LinkedHashMap<String, String>();
		List<String> list = dao.getHotelCity();

		List<Integer> listKey = new ArrayList<Integer>();
		for (String string : list) {
			String cityKey = Constants.XML_CITY_KEY.get(string);
			if (cityKey != null && !("").equals(cityKey)) {
				listKey.add(Integer.valueOf(cityKey));
			}
		}
		Collections.sort(listKey);
		for (Integer keys : listKey) {
			String cityKey = Constants.XML_CITY.get(keys.toString());
			if (cityKey != null && !("").equals(cityKey)) {
				youMap.put(keys.toString(), cityKey);
			}
		}
		return youMap;
	}

	/**
	 * 在list里面去除包含当前商品
	 * 
	 * @param list
	 * @param goodsId
	 * @return
	 */
	private B5MPageList getDistinctGoods(B5MPageList nlist, String goodsId) {
		YouHotel yh = null;
		B5MPageList result = new B5MPageList();
		List<YouHotel> list = nlist.getAll();
		logger.info("--list002 size--->" + list.size());
		for (int i = 0; i < list.size(); i++) {
			yh = list.get(i);
			if (goodsId.equals(yh.getId() + "")) {
				list.remove(i);
				break;
			}
		}
		if (list.size() >= Constants.YOU_RECOMMEND_SZIE) {
			List newList = new ArrayList();
			newList.addAll(list.subList(0, Constants.YOU_RECOMMEND_SZIE));
			result.addAll(newList);
			return result;
		} else {
			result.addAll(list);
		}
		return result;
	}

	/**
	 * 获取缓存的key
	 * 
	 * @param ttDto
	 * @return
	 */
	private String getMemCachedKey(YouSearchDto ttDto) {
		StringBuffer key = new StringBuffer();
		if (ttDto != null) {
			String temp = ttDto.getHotelPrice();
			if(temp != null && "0".equals(temp)){
				temp = "0-150";
			}
			key = new StringBuffer("b5m_hotel_").append(ttDto.getPageType()).append(ttDto.getAjaxType()).append(ttDto.getKeyWords().replaceAll(" ", ""))
					.append(ttDto.getCurrPageNo()).append(ttDto.getClikcId()).append(ttDto.getShowUrl()).append(ttDto.getUrl())
					.append(ttDto.getPageSize()).append(ttDto.getIpLocate()).append(ttDto.getCity()).append(ttDto.getOrder())
					.append(ttDto.getSort()).append(temp).append(ttDto.getHotelStar()).append(ttDto.getSelectedCityId()).append(ttDto.getPageMode());
		} else {
			return "YOU_CACHE_KEY_IS_NULL_";
		}
		return key.toString();
	}

	@Override
	public void totalClick(YouSearchDto dto) {
		// TODO Auto-generated method stub
	}

	@Override
	public List getSiteMapList(YouSearchDto dto) {
		String key = Constants.B5M_SITE_MAP_HOTEL + "_" + dto.getIpLocate();
		return (List)getDataFromCache(dto,key,6,"");
	}

	@Override
	public Map getMapKeyWordsList(YouSearchDto dto) {
		String key = Constants.YOU_ALL_MAP_KEYWORDS + "_" + dto.getSelectedCityId();
		String key1 = Constants.YOU_ALL_MAP_KEYWORDS + "_" + dto.getSelectedCityId() + "_SHORTKEY";
		//List list = (List)getDataFromCache(dto,key,8,"");
		//return processBizList(list);
		return (Map)getDataFromCache(dto,key1,14,key);
	}
	
	private List getSubMapList(List<HotelMapKeyWord> list,YouSearchDto dto){
		List<HotelMapKeyWord> tempList = new ArrayList();
		String city = Constants.XML_CITY.get(dto.getSelectedCityId());
		HotelMapKeyWord hmw = null;
		if(list != null && dto != null){
			for(int i = 0;i < list.size();i++){
				hmw = list.get(i);
				if(city.equals(hmw.getCity())){
					tempList.add(hmw);
				}
			}
		}
		return tempList;
	}
	
	/**
	 * true:在  false:不在
	 * @param keyWord
	 * @param map
	 * @return
	 */
	private boolean isKeyWordInMap(String keyWord,Map map){
		Set<String> set = map.keySet();
		for(String key : set){
			String temp = map.get(key).toString();
			String[] arr = temp.split("#");
			for(String str : arr){
				if(!key.equals("hotel") && str.equals(keyWord)){
					return true;
				}
			}
		}
		return false;
	}
	
	//将数据处理成业务需要的数据
	private Map processBizList(List<HotelMapKeyWord> list){
		Map map = new HashMap();
		String type = null;
		//list1~list6分别表示商区、行政区、地标、地铁、大学、酒店
		StringBuffer list1 = new StringBuffer();
		StringBuffer list2 = new StringBuffer();
		StringBuffer list3 = new StringBuffer();
		StringBuffer list4 = new StringBuffer();
		StringBuffer list5 = new StringBuffer();
		StringBuffer list6 = new StringBuffer("如家#7天连锁#汉庭#格林豪泰#锦江之星#布丁");
		HotelMapKeyWord hmw = null;
		boolean flag = false;
		for(int i = 0;i < list.size();i++){
			hmw = list.get(i);
			type = hmw.getItem1();
			if("2".equals(type)){
				if(!"".equals(hmw.getContent().split("#")[0])){
					list1.append(hmw.getContent().split("#")[0]).append("#");
				}
				list2.append(hmw.getItem2()).append("#");
			}else if("3".equals(type)){
				list3.append(hmw.getContent().split("#")[0]).append("#");
			}else if("4".equals(type)){
				list4.append(hmw.getItem2()).append("#");
			}else if("5".equals(type)){
				if(!flag){
					list5.append(hmw.getContent());
				}
				flag = true;
			}
		}
		map.put("biz", list1);
		map.put("dist", list2);
		map.put("bud", list3);
		map.put("metro", list4);
		map.put("uns", list5);
		map.put("hotel", list6);
		return map;
	}
	
	/**
	 * 获取以某点为中心(纬度,经度)，半径为radius的酒店
	 * @param lat1
	 * @param lng1
	 * @param radius
	 * @return
	 */
	private static String getDistanceRange(double lat1, double lng1,int radius){
		//纬度每秒的距离,单位:m
		double distancePerLatitudeSecs = 30.9;
		//经度每秒的距离,单位:m
		double distancePerLongitudeSecs = distancePerLatitudeSecs * Math.cos(Math.toRadians(lat1));
			
		//纬度的范围
		double latitudeRange = radius/distancePerLatitudeSecs;
		//经度的范围
		double longitudeRange = radius/distancePerLongitudeSecs;
			
		logger.info(distancePerLatitudeSecs + "---" + distancePerLongitudeSecs + "----" 
		+ latitudeRange/3600 + "----" + longitudeRange/3600);
		
		double latLow = lat1 - latitudeRange/3600;
		double latHigh = lat1 + latitudeRange/3600;
		
		double lngLow = lng1 - longitudeRange/3600;
		double lngHigh = lng1 + longitudeRange/3600;
		
		String result = latLow + "-" + latHigh + "#" + lngLow + "-" + lngHigh;
		logger.info("latitudeRange#longitudeRange-->" + result);
		return result;
	}
	
	/**
	 * 获取某城市中心点的坐标
	 * @param ttDto
	 * @return
	 */
	public String getCityCenterLatAndLong(YouSearchDto ttDto){
		String city = Constants.XML_CITY.get(ttDto.getSelectedCityId());
		String pointsKey = "POINTS_KEY_CITY_" + city + "_" + city;
		String temp = ttDto.getKeyWords();
		ttDto.setKeyWords(city);
		String result = (String)getDataFromCache(ttDto,pointsKey,9,city);
		ttDto.setKeyWords(temp);
		return result;
	}
	
	/**
	 * 通过关键字获取纬度,经度
	 * @param keyWord
	 * @return
	 */
	private static String getLatAndLngByKeyWord(String city,String keyWord){
		String temp1 = city;
		String temp2 = keyWord;
		String result = "";
		String url = "";
		try {
			temp1 = URLEncoder.encode(temp1,"utf-8");
			temp2 = URLEncoder.encode(temp2,"utf-8");
		
			url = "http://api.map.baidu.com/place/v2/search?&query=" + temp2 + "&region=" + temp1 + "&output=json&ak=60b9d75a06c0d53bf0ec47bf5b3bd773";
			result = HttpRequest.getRequest(url, null);
			JSONObject json = new JSONObject(result);
			JSONArray ja = new JSONArray(json.getString("results"));
			String name = ja.getJSONObject(0).get("name").toString();
			String lat = ja.getJSONObject(0).getJSONObject("location").get("lat").toString();
			String lng = ja.getJSONObject(0).getJSONObject("location").get("lng").toString();
			String address = "";
			try{
				address = ja.getJSONObject(0).get("address").toString();
			}catch(JSONException je){
				
			}
			
			String status = json.get("status").toString();
			result = name + "#" + lat + "#" + lng + "#" + address + "#" + status;
			logger.info("--points-->" + result);
		} catch (Exception e) {
			logger.info("---------------getLatAndLngByKeyWord---Exception---baidu-api-------------" + url );
			//e.printStackTrace();
			result = "####-1";
		}
		return result;
	}
	
	/**
	 * 获取地铁站点的信息
	 * @param list 该城市的所有信息
	 * @return
	 */
	private static Map getMetroStation(List<HotelMapKeyWord> list){
		HotelMapKeyWord hmw = null;
		String type = null;
		Map map = new HashMap();
		for(int i = 0;i < list.size();i++){
			hmw = list.get(i);
			type = hmw.getItem1();
			if("4".equals(type)){
				map.put(hmw.getItem2(),hmw.getContent());
			}
		}
		return map;
	}
	
	//获取地铁站点的纬度,经度信息
	public  Map getMetroStationLocation(YouSearchDto dto,String city){
		String key = city + "_metro_";
		//地铁站点信息
		Map<String,String> map = (Map<String,String>)getDataFromCache(dto,key,10,"");
		Set<String> set = map.keySet();
		//站点纬度,经度
		String temp0 = "";
		//站点纬度,经度 Map
		Map stationMap = new HashMap();
		//线路纬度,经度 Map
		Map lineMap = new HashMap();
		//temp 线路名称
		for(String temp:set){
			String[] values = map.get(temp).split("#");
			//temp1 站点名称
			for(String temp1 : values){
				if(!"".equals(temp1)){
					temp0 = getLatAndLngByKeyWord(city,temp1);
					stationMap.put(temp1,temp0);
				}
			}
			lineMap.put(temp, stationMap);
		}
		return lineMap;
	}
	
	ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 50 * 3, 1L,
			TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(100),
			new ThreadPoolExecutor.CallerRunsPolicy());
	private CompletionService<B5MPageList> completionService = new ExecutorCompletionService<B5MPageList>(executor);
	
	//地铁周边 酒店列表
	public List getHotelsNearByStations(final YouSearchDto dto){
		String key = Constants.YOU_HOTEL_METRO_LINE + "_" + dto.getSelectedCityId();
		Map allLineMap = (Map)getDataFromCache(dto,key,10,"");
		Set set = allLineMap.keySet();
		final List result = Collections.synchronizedList(new ArrayList());
		Thread[] ths = null;
		for(Object temp:set){
			if(dto.getKeyWords().equals(temp.toString())){
				String temp0 = (String)allLineMap.get(temp.toString());
				final String[] values = temp0.split("#");
				/*for(String tempKey : values){
					GetHotels gh = new GetHotels(dto,tempKey);
					FutureTask<B5MPageList> task = new FutureTask<B5MPageList>(gh);
					tasks.add(task);
					if (!executor.isShutdown()) {
						executor.submit(task);
					}
				}*/
				
				
				for(final String tempKey : values){					
					dto.setKeyWords("地铁" + tempKey);
					dto.setPageMode("map");
					B5MPageList listAll = (B5MPageList)getYouHotelTempList(dto);
					if(listAll.getAll().size() > 0){
						result.add(listAll.getAll().get(0));
					}
				}
				
				/*ths = new Thread[values.length];
				for(int i = 0; i < values.length;i++){
					ths[i] = new Thread(new Runnable(){
						public void run(){
							dto.setKeyWords("地铁" + values[i]);
							dto.setPageMode("map");
							B5MPageList listAll = (B5MPageList)getYouHotelTempList(dto);
							if(listAll.getAll().size() > 0){
								result.add(listAll.getAll().get(0));
							}
						}
					});
				}*/
				
			}
		}
		/*for(int i = 0;i < ths.length;i++){
			try {
				ths[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
		return result;
		//return getResult();
	}
	
	private List<Future<B5MPageList>> tasks = new ArrayList<Future<B5MPageList>>();
	
	class GetHotels implements Callable<B5MPageList> {
		private YouSearchDto dto;
		private String key;
		public GetHotels(YouSearchDto dto,String key){
			this.dto = dto;
			this.key = key;
		}
		
		@Override
		public B5MPageList call() throws Exception {
			B5MPageList listAll = null;
			//for(String tempKey : values){
				dto.setKeyWords("地铁" + key);
				dto.setPageMode("map");
				listAll = (B5MPageList)getYouHotelTempList(dto);
			//}
			return listAll;
		}
	}
	
	public List getResult() {
		List result = Collections.synchronizedList(new ArrayList());
		for (Future<B5MPageList> task : tasks) {
			try {
				// 如果计算未完成则阻塞
				B5MPageList r1 = task.get();
				if(r1.getAll().size() > 0){
					result.add(r1.getAll().get(0));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		//executor.shutdown();
		return result;
	}
	
	class HotelThread extends Thread{
		private String key;
		private YouSearchDto dto;
		
		public HotelThread(String key,YouSearchDto dto){
			this.key = key;
			this.dto = dto;
		}
		
		public void run(){
			/*dto.setKeyWords("地铁" + tempKey);
			dto.setPageMode("map");
			B5MPageList listAll = (B5MPageList)getYouHotelTempList(dto);
			if(listAll.getAll().size() > 0){
				result.add(listAll.getAll().get(0));
			}*/
		}
	}
	
	//地铁周边 酒店列表 分页
	public B5MPageList getHotelsNearByPage(YouSearchDto dto){
		String listKey = Constants.YOU_HOTEL_METRO_LINE + "_" + dto.getSelectedCityId() + "_" + dto.getKeyWords();
		String lineName = dto.getKeyWords();
		List list = (List)getDataFromCache(dto,listKey,11,"");
		dto.setKeyWords(lineName);
		int pageNo = dto.getCurrPageNo();
		int size = 24;
		int pages = list.size() / size;
		if(list.size() % 24 != 0){
			pages = pages + 1;
		}
		
		B5MPageList bl = new B5MPageList();
		int last = (pageNo * size)-1;
		if(last > list.size()){
			last = list.size();
		}else{
			last = last + 1;
		}
		bl.addAll(list.subList((pageNo-1) * size, last));
		bl.setTotalPages(pages);
		bl.setTotalCount(list.size());
		bl.setPageSize(size);
		bl.setCount(bl.getAll().size());
		return bl;
	}
	
	/**
	 * 返回关键字的树结构
	 */
	@Override
	public HotelKeyWords getMetroHotelListByType(YouSearchDto dto,String type) {
		String key = Constants.YOU_ALL_MAP_KEYWORDS + "_" + dto.getSelectedCityId();
		String key1 = key + "_" + type;
		List list = (List)getDataFromCache(dto,key,8,"");
		HotelKeyWords hk = getTreeData(list,type);
		return hk;
	}
	
	/**
	 * 生成关键字的树结构
	 * @param list
	 * @param type
	 * @return
	 */
	private HotelKeyWords getTreeData(List<HotelMapKeyWord> list,String type){
		List<HotelKeyWords.Level1> list1 = new ArrayList();
		List<String> list2 = new ArrayList();
		HotelKeyWords hk = new HotelKeyWords();
		
		//去重item2
		for(HotelMapKeyWord hmw : list){
			if(hmw.getItem1().equals(type) && !list2.contains(hmw.getItem2())){
				if( "5".equals(type) && hmw.getItem2() == null){
					list2.add("大学");
				}else{
					list2.add(hmw.getItem2());
				}
			}
		}
		
		//获取level实例并加入hk 的 list
		for(String str : list2){
			if(str != null){
				boolean flag = false;
				HotelKeyWords.Level1 le1 = hk.new Level1();
				le1.setItem1(str);
				for(HotelMapKeyWord hmw : list){
					String temp3 = hmw.getItem2();
					if( "5".equals(type) && temp3 == null){
						temp3 = "大学";
					}
					if(str.equals(temp3) && type.equals(hmw.getItem1())){
						String[] tempArr = hmw.getContent().split("#");
						le1.setArr(tempArr);
						if(StringUtils.isBlank(hmw.getContent())){
							flag = true;
						}
						break;
					}
				}
				if(!flag){
					list1.add(le1);
				}
			}
		}
		hk.setItem1(type);
		hk.setLe0(list1);
		return hk;
	}

	@Override
	public List findTopicYouHotels(YouSearchDto dto) {
		String key = getMemCachedKey(dto);
		return (List)getDataFromCache(dto,key,13,"");
	}

	@Override
	public List getHotelsForMap(YouSearchDto dto) {
		//B5MPageList<YouHotel> hotelList = getYouHotelList(dto);
		//List<YouHotel> list = hotelList.getAll();
		return null;
	}

	/**
	 * 获取推荐商品——团购或广告商品
	 */
	@Override
	public List<YouHotel> getRecommendHotel(YouSearchDto dto) {
		List<YouHotel> list = null;
		//调用接口
		if("0".equals(dto.getAdOrInterface())){
			//TODO 调用团购的接口
		}else{//查询数据库
			list = dao.findAdsHotels(dto);
		}
		return list;
	}
}
