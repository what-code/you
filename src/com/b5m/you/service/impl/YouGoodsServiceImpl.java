package com.b5m.you.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.b5m.web.core.AbstractBaseService;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.Constants;
import com.b5m.you.common.util.XMemCachedUtil;
import com.b5m.you.dao.IYouCitysDao;
import com.b5m.you.dao.IYouGoodsDao;
import com.b5m.you.dao.IYouTopicBarDao;
import com.b5m.you.dao.IYouTopicMainDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.DestinationCitys;
import com.b5m.you.model.YouGoods;
import com.b5m.you.model.YouTopicBar;
import com.b5m.you.model.YouTopicMain;
import com.b5m.you.service.IYouGoodsService;

@Service("taoGoodsService")
public class YouGoodsServiceImpl extends AbstractBaseService<YouGoods> implements IYouGoodsService {
	public static Logger logger = Logger.getLogger(YouGoodsServiceImpl.class);
	@Resource
	private IYouGoodsDao taoGoodsDao;
	@Resource
	private IYouCitysDao citysDao;
	@Resource
	private IYouTopicMainDao topicDao;
	@Resource
	private IYouTopicBarDao barDao;

	public void setTaoGoodsDao(IYouGoodsDao taoGoodsDao) {
		this.taoGoodsDao = taoGoodsDao;
		this.setBaseDao(taoGoodsDao);
	}

	/**
	 * 国内游
	 * 
	 * @param dto
	 *            分页dto
	 * @param ttjSearchDto
	 *            搜索相关条件dto
	 * @return List<TaoGoods> 分页集合
	 */
	@Override
	public B5MPageList<YouGoods> getYouGoodsByDomesticTravel(YouSearchDto ttDto) {
		return (B5MPageList<YouGoods>) getDataFromCache(ttDto, 0, "", 0);
	}

	/**
	 * 境外游
	 * 
	 * @param dto
	 *            分页dto
	 * @param ttjSearchDto
	 *            搜索相关条件dto
	 * @return List<TaoGoods> 分页集合
	 */
	@Override
	public B5MPageList<YouGoods> getYouGoodsByAbroadTravel(YouSearchDto dto) {
		return (B5MPageList<YouGoods>) getDataFromCache(dto, 0, "", 1);
	}

	/**
	 * 周边游
	 * 
	 * @param dto
	 * @return
	 */
	@Override
	public B5MPageList<YouGoods> getYouGoodsByPeripheryTravel(YouSearchDto dto) {
		return (B5MPageList<YouGoods>) getDataFromCache(dto, 0, "", 2);
	}

	/**
	 * 搜索结果页首次 分页获取 数据
	 * 
	 * @param dto
	 *            分页dto
	 * @param ttjSearchDto
	 *            搜索相关条件dto
	 * @return 分页集合
	 */
	@Override
	public B5MPageList<YouGoods> getYouGoodsSearch(YouSearchDto dto) {
		return (B5MPageList<YouGoods>) getDataFromCache(dto, 0, "", 3);
	}

	/**
	 * 查询包邮人气商品集合
	 * 
	 * @param postal
	 *            包邮
	 * @param salesPrice
	 *            现价
	 * @param hotCount
	 *            集合数量
	 * @return 集合
	 */
	@Override
	public List<YouGoods> getTaoGoods(int hotCount) {
		return (List<YouGoods>) getDataFromCache(null, hotCount, "", 4);
	}

	/**
	 * 查询首页国内游
	 * 
	 * @param ttjSearchDto
	 *            相关条件dto
	 * @return 置顶集合数据
	 */
	@Override
	public List<YouGoods> findsIndexSpecialTravelByList(YouSearchDto dto, String youType) {
		return (List<YouGoods>) getDataFromCache(dto, 0, youType, 5);
	}

	/**
	 * 详细页面
	 */
	@Override
	public YouGoods findYouGoodsById(YouSearchDto dto) {
		return (YouGoods) getDataFromCache(dto, 0, "", 6);
	}

	/**
	 * 点击数
	 */
	@Override
	public void totalClick(YouSearchDto dto) {
		taoGoodsDao.totalClick(dto);
	}

	@Override
	public Map<String, String> getYouCity(YouSearchDto dto) {
		return (Map<String, String>) getDataFromCache(dto, 0, "", 7);
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
	private Object getDataFromCache(YouSearchDto ttDto, int hotCount, String youType, int type) {
		String key = "";
		if (ttDto != null)
			key = getMemCachedKey(ttDto);
		if (type == 4) {
			key = "YOU_hotList_" + hotCount;
		} else if (type == 5) {
			key = "Index_Domestic_Travel" + youType + ttDto.getPageSize();
		} else if (type == 6) {
			key = key + "Index_Detailed";
		} else if (type == 7) {
			key = Constants.B5M_YOU_CITY;
		}else if (type == 8) {
			key = Constants.B5M_SITE_MAP + "_" + ttDto.getIpLocate() + "_" + ttDto.getSiteMapType();
		}else if(type == 9){
			key = Constants.B5M_YOU_TOPIC_CACHE_MAIN + "_" + youType;
		}else if(type == 10){
			key = Constants.B5M_TAO_TOPIC_CACHE_BAR + "_" + hotCount;
		}
		// 详情页的key
		if (ttDto != null && Constants.B5M_YOU_SAME_TYPE.equals(ttDto.getSearchType())) {
			key = Constants.B5M_YOU_SAME_TYPE + ttDto.getIpLocate() + "_" + ttDto.getAjaxType() + "_" + ttDto.getSelectedCityId();
		}
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if (flag) {
				logger.info("----you_goods_from_db--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);
				if (type == 0) {
					data = taoGoodsDao.findYouGoodsByList(ttDto, "0");
				} else if (type == 1) {
					data = taoGoodsDao.findYouGoodsByList(ttDto, "1");
				} else if (type == 2) {
					data = taoGoodsDao.findYouGoodsByList(ttDto, "2");
				} else if (type == 3) {
					data = taoGoodsDao.findYouGoodsByList(ttDto, null);
				} else if (type == 4) {
					data = taoGoodsDao.findYouGoods(hotCount);
				} else if (type == 5) {
					data = taoGoodsDao.findsIndexSpecialTravelByList(ttDto, youType);
				} else if (type == 6) {
					data = taoGoodsDao.findYouGoodsById(ttDto);
				} else if (type == 7) {
					data = getMapCity(ttDto);
				}else if (type == 8) {
					data = taoGoodsDao.getSiteMap(ttDto);
				}else if(type == 9){
					data = topicDao.findActivityTopicMain(youType);
				}else if(type == 10){
					data = barDao.findActivityTopicBar(hotCount);
				}else if(type == 11){
					data = taoGoodsDao.findTopicYouGoods(ttDto);
				}
				// site map 缓存时间长点，1 day
				if(type == 8){
					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_20DAY);
				}else{
					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_goods_from_db---90--######---");
				if (data == null) {
					logger.info("----you_goods_from_db---90--?????????????????-->" + key);
					if (type == 0) {
						data = taoGoodsDao.findYouGoodsByList(ttDto, "0");
					} else if (type == 1) {
						data = taoGoodsDao.findYouGoodsByList(ttDto, "1");
					} else if (type == 2) {
						data = taoGoodsDao.findYouGoodsByList(ttDto, "2");
					} else if (type == 3) {
						data = taoGoodsDao.findYouGoodsByList(ttDto, null);
					} else if (type == 4) {
						data = taoGoodsDao.findYouGoods(hotCount);
					} else if (type == 5) {
						data = taoGoodsDao.findsIndexSpecialTravelByList(ttDto, youType);
					} else if (type == 6) {
						data = taoGoodsDao.findYouGoodsById(ttDto);
					} else if (type == 7) {
						data = getMapCity(ttDto);
					}else if (type == 8) {
						data = taoGoodsDao.getSiteMap(ttDto);
					}else if(type == 9){
						data = topicDao.findActivityTopicMain(youType);
					}else if(type == 10){
						data = barDao.findActivityTopicBar(hotCount);
					}else if(type == 11){
						data = taoGoodsDao.findTopicYouGoods(ttDto);
					}
					// site map 缓存时间长点，1 day
					if(type == 8){
						XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
						XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_20DAY);
					}else{
						XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
						XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
					}
				}
			}
		}
		if ((type == 0 || type == 1 || type == 2) && ttDto.getClikcId() != null && (Constants.B5M_YOU_SAME_TYPE.equals(ttDto.getSearchType()))) {
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
	private LinkedHashMap<String, String> getMapCity(YouSearchDto ttDto) {
		LinkedHashMap<String, String> youMap = new LinkedHashMap<String, String>();
		Map<String, Integer> mapAll = new HashMap<String, Integer>();
		for (int i = 0; i < 3; i++) {
			ttDto.setYouType(i + "");
			List<String> list = taoGoodsDao.getYouCity(ttDto);
			for (String string : list) {
				Integer v = mapAll.get(string);
				if (v == null)
					mapAll.put(string, 1);
				else
					mapAll.put(string, ++v);
			}
		}
		List<Integer> list = new ArrayList<Integer>();
		for (String keys : mapAll.keySet()) {
			Integer value = mapAll.get(keys);
			if (value == 3) {
				String cityKey = Constants.XML_CITY_KEY.get(keys);
				if (cityKey != null && !("").equals(cityKey)) {
					list.add(Integer.valueOf(cityKey));
				}

			}
		}
		Collections.sort(list);
		for (Integer keys : list) {
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
		YouGoods yh = null;
		B5MPageList result = new B5MPageList();
		List<YouGoods> list = nlist.getAll();
		logger.info("--list001 size--->" + list.size());
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
			key = new StringBuffer("b5m_you").append(ttDto.getPageType()).append(ttDto.getAjaxType()).append(ttDto.getKeyWords().replaceAll(" ", ""))
					.append(ttDto.getCurrPageNo()).append(ttDto.getClikcId()).append(ttDto.getShowUrl()).append(ttDto.getUrl())
					.append(ttDto.getPageSize()).append(ttDto.getIpLocate()).append(ttDto.getCity()).append(ttDto.getOrder())
					.append(ttDto.getSort()).append(ttDto.getType0()).append(ttDto.getDays()).append(ttDto.getType1())
					.append("s" + ttDto.getPriceStart()).append("e" + ttDto.getPriceEnd()).append(ttDto.getTimeStart())
					.append(ttDto.getTimeEnd()).append(ttDto.getSelectedCityId());
		} else {
			return "YOU_CACHE_KEY_IS_NULL_";
		}
		return key.toString();
	}
	
	/**
	 * 获取所有目的地城市
	 * @return
	 */
	public List getAllCitys(){
		String key = "you_destinationcitys_list";
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if(data == null){
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if(flag){
				data = citysDao.getAllCitys();
				XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
				XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
			}else{
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				if (data == null) {
					data = citysDao.getAllCitys();
					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			}
		}
		return (List)data;
	}
	
	/**
	 * 根据关键字回去城市列表
	 * @param keyword
	 * @return
	 */
	@Override
	public List getCitysByKeyword(String keyword){
		List<DestinationCitys> list = (List<DestinationCitys>)getAllCitys();
		DestinationCitys tempDC = null;
		List result = new ArrayList();
		for(DestinationCitys dc:list){
			if(keyword.trim().equals(dc.getCityName().trim())){
				tempDC = dc;
				break;
			}
		}
		if(tempDC != null){
			result.add(tempDC);
			for(DestinationCitys dc:list){
				if(dc.getParentId().equals(tempDC.getId())){
					result.add(dc);
				}
			}
		}
		return result;
	}

	@Override
	public List getSiteMapList(YouSearchDto dto) {
		return (List)getDataFromCache(dto,0,null,8);
	}
	
	/**
	 * 查找活动页模版数据
	 * 
	 * @return TaoTopicMain
	 */
	@Override
	public YouTopicMain findActivityTopicMain(String id) {
		logger.info("findActivityTopicMain:" + id);
		return (YouTopicMain) getDataFromCache(null, 0, id, 9);
	}

	@Override
	public List<YouTopicBar> findActivityTopicBar(Integer id) {
		logger.info("findActivityTopicSub:" + id);
		return (List<YouTopicBar>)getDataFromCache(null,id,"",10);
	}

	@Override
	public List<YouGoods> findTopicYouGoods(YouSearchDto dto) {
		logger.info("findTopicYouGoods");
		return (List<YouGoods>)getDataFromCache(dto,0,"",11);
	}
}
