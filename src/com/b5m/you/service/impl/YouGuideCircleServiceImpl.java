package com.b5m.you.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.b5m.web.core.AbstractBaseService;
import com.b5m.you.common.util.XMemCachedUtil;
import com.b5m.you.dao.IYouGuideCircleDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGuideCircle;
import com.b5m.you.model.YouHotel;
import com.b5m.you.service.IYouGuideCircleService;
import com.b5m.you.service.IYouHotelService;

/**
 * Title:YouGuideServiceImpl.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-7-30
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Jia Liu
 * 
 * @version 1.0
 */
@Service
public class YouGuideCircleServiceImpl extends AbstractBaseService<YouGuideCircle> implements IYouGuideCircleService {

	public static Logger logger = Logger.getLogger(YouGuideCircleServiceImpl.class);

	@Resource
	private IYouGuideCircleDao guideCircleDao;

	@Resource
	private IYouHotelService hotelService;

	@Override
	public List<YouGuideCircle> findGuideCircle(YouSearchDto dto) {

		String key = "findGuideCircle" + dto.getClikcId();
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if (flag) {
				logger.info("----you_guide_circle_from db--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);

				List<YouGuideCircle> circlelList = guideCircleDao.findGuideCircle(dto);
				for (YouGuideCircle youGuideCircle : circlelList) {
					List<YouHotel> hotelList = hotelService.findsLandingPageByList(dto, youGuideCircle.getId().toString());
					youGuideCircle.getHotelList().addAll(hotelList);
				}
				data = circlelList;

				XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
				XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_guide_circle_from---90--######---");
				if (data == null) {
					logger.info("----you_guide_circle_from---90--?????????????????-->" + key);

					List<YouGuideCircle> circlelList = guideCircleDao.findGuideCircle(dto);
					for (YouGuideCircle youGuideCircle : circlelList) {
						List<YouHotel> hotelList = hotelService.findsLandingPageByList(dto, youGuideCircle.getId().toString());
						youGuideCircle.getHotelList().addAll(hotelList);
					}
					data = circlelList;

					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			}

		}
		return (List<YouGuideCircle>) data;

	}
}
