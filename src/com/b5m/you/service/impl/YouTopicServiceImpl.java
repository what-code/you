package com.b5m.you.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.b5m.web.core.AbstractBaseDao;
import com.b5m.web.core.Constants;
import com.b5m.you.common.util.XMemCachedUtil;
import com.b5m.you.dao.IYouTopicDao;
import com.b5m.you.model.YouTopic;
import com.b5m.you.service.IYouTopicService;

@Service
public class YouTopicServiceImpl extends AbstractBaseDao<YouTopic> implements IYouTopicService {

	@Resource
	private IYouTopicDao taoTopicDao;

	public static Logger logger = Logger.getLogger(YouTopicServiceImpl.class);

	/**
	 * 查找轮播专题位
	 * 
	 * @return list
	 */
	@Override
	public List<YouTopic> findCarouselTopic() {
		// Object data = MemCachedUtil.getCache(Constants.B5M_YOU_MEM_CACHE_CAROUSEL);
		// if (null == data) {
		// List<YouTopic> taoTopic = taoTopicDao.findCarouselTopic();
		// MemCachedUtil.setCache(Constants.B5M_YOU_MEM_CACHE_CAROUSEL, taoTopic, MemCachedUtil.DEFAULT_CACHE_TIME);
		// return taoTopic;
		// }
		return (List<YouTopic>) getDataFromCache(0);
	}

	/**
	 * 查找首页专题位
	 * 
	 * @return list
	 */
	@Override
	public List<YouTopic> findIndexTopTopic() {
		// Object data = MemCachedUtil.getCache(Constants.B5M_YOU_MEM_CACHE_INDEX_TOPIC);
		// if (null == data) {
		// List<YouTopic> taoTopic = taoTopicDao.findIndexTopTopic();
		// MemCachedUtil.setCache(Constants.B5M_YOU_MEM_CACHE_INDEX_TOPIC, taoTopic, MemCachedUtil.DEFAULT_CACHE_TIME);
		// return taoTopic;
		// }
		return (List<YouTopic>) getDataFromCache(1);
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
	private Object getDataFromCache(int type) {
		String key = "";
		if (type == 0) {
			key = Constants.B5M_YOU_MEM_CACHE_CAROUSEL;
		} else if (type == 1) {
			key = Constants.B5M_YOU_MEM_CACHE_INDEX_TOPIC;
		}
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if (flag) {
				logger.info("----you_topic_from_db--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);
				if (type == 0) {
					data = taoTopicDao.findCarouselTopic();
				} else if (type == 1) {
					data = taoTopicDao.findIndexTopTopic();
				}
				XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
				XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_topic_from_db---90--######---");
				if (data == null) {
					logger.info("----you_topic_from_db---90--?????????????????-->" + key);
					if (type == 0) {
						data = taoTopicDao.findCarouselTopic();
					} else if (type == 1) {
						data = taoTopicDao.findIndexTopTopic();
					}
					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			}

		}
		return data;
	}
}
