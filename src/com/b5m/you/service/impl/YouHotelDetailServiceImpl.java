package com.b5m.you.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.b5m.web.core.AbstractBaseService;
import com.b5m.you.common.util.XMemCachedUtil;
import com.b5m.you.dao.IYouHotelDetailDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouHotelDetail;
import com.b5m.you.service.IYouHotelDetailService;

@Service
public class YouHotelDetailServiceImpl extends AbstractBaseService<YouHotelDetail> implements IYouHotelDetailService {

	public static Logger logger = Logger.getLogger(YouHotelDetailServiceImpl.class);

	@Resource
	private IYouHotelDetailDao dao;

	@Override
	public List<YouHotelDetail> getYouHotelDetailById(YouSearchDto ttDto) {
		String key = getMemCachedKey(ttDto) + "_hotel_detail";
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if (flag) {
				logger.info("----you_hotel_detail_from_db--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);
				data = dao.getYouHotelDetailById(ttDto);
				XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
				XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_hotel_detail_from_db---90--######---");
				if (data == null) {
					logger.info("----you_hotel_detail_from_db---90--?????????????????-->" + key);
					data = dao.getYouHotelDetailById(ttDto);
					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			}

		}
		return (List<YouHotelDetail>) data;
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
			key = new StringBuffer("b5m_hotel_detail_").append(ttDto.getPageType()).append(ttDto.getAjaxType()).append(ttDto.getKeyWords())
					.append(ttDto.getCurrPageNo()).append(ttDto.getClikcId()).append(ttDto.getShowUrl()).append(ttDto.getUrl())
					.append(ttDto.getPageSize()).append(ttDto.getIpLocate()).append(ttDto.getCity());
		} else {
			return "YOU_CACHE_KEY_IS_NULL_";
		}
		return key.toString();
	}
}
