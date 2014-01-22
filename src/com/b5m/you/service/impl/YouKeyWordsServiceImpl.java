package com.b5m.you.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.b5m.web.core.AbstractBaseService;
import com.b5m.you.common.util.XMemCachedUtil;
import com.b5m.you.dao.IYouKeyWordsDao;
import com.b5m.you.model.YouKeyWords;
import com.b5m.you.service.IYouKeyWordsService;

@Service
public class YouKeyWordsServiceImpl extends AbstractBaseService<YouKeyWords> implements IYouKeyWordsService {

	public static Logger logger = Logger.getLogger(YouKeyWordsServiceImpl.class);

	@Resource
	private IYouKeyWordsDao dao;

	@Override
	public List<YouKeyWords> findKeyWords() {
		String key = "b5m_you_key_words_find";
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if (flag) {
				logger.info("----you_keywords_from_db--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);
				data = dao.findKeyWords();
				XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
				XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_keywords_from_db---90--######---");
				if (data == null) {
					logger.info("----you_keywords_from_db---90--?????????????????-->" + key);
					data = dao.findKeyWords();
					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			}
		}
		return (List<YouKeyWords>) data;
	}

}
