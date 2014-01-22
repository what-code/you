package com.b5m.you.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.b5m.base.common.dao.hibernate.HibernateDao;
import com.b5m.web.cache.MCache;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.Constants;
import com.b5m.you.common.util.XMemCachedUtil;
import com.b5m.you.dao.IYouTravelDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.TuanModel;
import com.b5m.you.model.YouGoods;
import com.b5m.you.model.YouTravel;
import com.b5m.you.service.IYouTravelService;

@Service("travelService")
public class YouTravelServiceImpl implements IYouTravelService {
	// extends AbstractBaseService<YouGoods> implements IYouTravelService {

	public static Logger logger = Logger.getLogger(YouTravelServiceImpl.class);

	@Resource
	private HibernateDao hibernateDao;

	@Resource
	private IYouTravelDao travelDao;

	@Override
//	@MCache
	public B5MPageList<YouGoods> findTravelByList(YouSearchDto dto) {
		String key = getMemCachedKey(dto);
		//推荐部分
		if("1".equals(dto.getIsRecommendtion())){
			key = Constants.B5M_YOU_SAME_TYPE + "_travel";
		}
		logger.info("----findTravelByList:" + key);

		Object data = XMemCachedUtil.getCache(key);
		Integer num = XMemCachedUtil.getDataMemCached(data, key);
		if (num != 0) {
			data = travelDao.findTravelByList(dto);
			XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
			XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
		}
		// String key = getMemCachedKey(dto);
		// logger.info("----findTravelByList:" + key);
		//
		// Object data = XMemCachedUtil.getCache(key);
		// Integer num = XMemCachedUtil.getDataMemCached(data, key);
		// if (num != 0) {
		// data = travelDao.findTravelByList(dto);
		// XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
		// XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
		// }

		// logger.info("----findTravelByList------");
		// B5MPageList<YouGoods> data = travelDao.findTravelByList(dto);

		return (B5MPageList<YouGoods>) data;
	}

	@Override
	public List<TuanModel> findTravelTuan(String city, String type, int size) {
		String key = "findTravelTuan_" + city + "_" + type + "_" + size;
		logger.info("----findTravelTuan:" + key);

		Object data = XMemCachedUtil.getCache(key);
		Integer num = XMemCachedUtil.getDataMemCached(data, key);
		if (num != 0) {
			data = travelDao.findTravelTuan(city, type, size);
			XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
			XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
		}

		return (List<TuanModel>) data;
	}

	@Override
	public List<YouTravel> findIndexHotTravel(String travelType) {
		String key = "findIndexHotTravel:" + travelType;
		logger.info("----findIndexHotTravel:" + key);

		Object data = XMemCachedUtil.getCache(key);
		Integer num = XMemCachedUtil.getDataMemCached(data, key);
		if (num != 0) {
			data = travelDao.findIndexHotTravel(travelType);
			XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
			XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
		}

		return (List<YouTravel>) data;
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
					.append(ttDto.getPageSize()).append(ttDto.getIpLocate()).append(ttDto.getCity()).append(ttDto.getOrder()).append(ttDto.getSort())
					.append(ttDto.getType0()).append(ttDto.getDays()).append(ttDto.getType1()).append("s" + ttDto.getPriceStart())
					.append("e" + ttDto.getPriceEnd()).append(ttDto.getTimeStart()).append(ttDto.getTimeEnd()).append(ttDto.getSelectedCityId())
					.append(ttDto.getDaysMini()).append(ttDto.getDaysMax());
		} else {
			return "YOU_CACHE_KEY_IS_NULL_";
		}
		return key.toString();
	}
}
