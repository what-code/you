package com.b5m.you.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.b5m.web.core.AbstractBaseService;
import com.b5m.you.common.util.XMemCachedUtil;
import com.b5m.you.dao.IYouGuideDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGuide;
import com.b5m.you.service.IYouGuideService;

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
public class YouGuideServiceImpl extends AbstractBaseService<YouGuide> implements IYouGuideService {

	public static Logger logger = Logger.getLogger(YouGuideServiceImpl.class);

	@Resource
	private IYouGuideDao guideDao;

	@Override
	public YouGuide findGuide(YouSearchDto dto) {
		String key = "findGuide" + dto.getClikcId();
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if (flag) {
				logger.info("----you_guide_from_db--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);

				data = guideDao.findGuide(dto);

				XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
				XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_guide_from_db---90--######---");
				if (data == null) {
					logger.info("----you_guide_from_db---90--?????????????????-->" + key);

					data = guideDao.findGuide(dto);

					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			}

		}
		return (YouGuide) data;
	}

}
