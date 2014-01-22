package com.b5m.you.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.b5m.web.core.AbstractBaseService;
import com.b5m.web.core.Constants;
import com.b5m.you.common.util.XMemCachedUtil;
import com.b5m.you.dao.IYouSourceDao;
import com.b5m.you.model.YouSource;
import com.b5m.you.service.IYouSourceService;

@Service
public class YouSourceServiceImpl extends AbstractBaseService<YouSource> implements IYouSourceService {

	@Resource
	private IYouSourceDao taoSourceDao;

	private Map<String, String> dataSource = new HashMap<String, String>();

	public static Logger logger = Logger.getLogger(YouSourceServiceImpl.class);

	/**
	 * 查找数据源
	 * 
	 * @return list
	 */
	@Override
	public Map<String, String> findTaoSource() {
		// Object data = MemCachedUtil.getCache(Constants.B5M_YOU_MEM_CACHE_SOURCE);
		// if (null == data) {
		// List<YouSource> taoSourceList = taoSourceDao.findTaoSource();
		// if(taoSourceList == null || taoSourceList.size() == 0){
		// dataSource.clear();
		// dataSource.putAll(Constants.DATA_SOURCE);
		// }
		// for (YouSource taoSource : taoSourceList) {
		// dataSource.put(taoSource.getSource(), taoSource.getSourceName());
		// }
		// MemCachedUtil.setCache(Constants.B5M_YOU_MEM_CACHE_SOURCE, dataSource, MemCachedUtil.DEFAULT_CACHE_TIME);
		// return dataSource;
		// }
		return (Map<String, String>) getDataFromCache();
	}

	/**
	 * 二级缓存 工具方法
	 * 
	 * @return
	 */
	private Object getDataFromCache() {
		String key = Constants.B5M_YOU_MEM_CACHE_SOURCE;
		Object data = XMemCachedUtil.getCache(key);
		boolean flag = false;
		if (data == null) {
			flag = XMemCachedUtil.getInstance().add(XMemCachedUtil.getLockKey(key), "YOU_KEY_LOCK");
			if (flag) {
				logger.info("----you_source_from_db--60--%%%%%%%%%%%%%%%%%%%%%%%%%-->" + key);
				List<YouSource> taoSourceList = taoSourceDao.findTaoSource();
				if (taoSourceList == null || taoSourceList.size() == 0) {
					dataSource.clear();
					dataSource.putAll(Constants.DATA_SOURCE);
				}
				for (YouSource taoSource : taoSourceList) {
					dataSource.put(taoSource.getSource(), taoSource.getSourceName());
				}
				data = dataSource;
				XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
			} else {
				data = XMemCachedUtil.getCache(XMemCachedUtil.getNewKey(key));
				logger.info("----you_source_from_db---90--######---");
				if (data == null) {
					logger.info("----you_source_from_db---90--?????????????????-->" + key);
					List<YouSource> taoSourceList = taoSourceDao.findTaoSource();
					if (taoSourceList == null || taoSourceList.size() == 0) {
						dataSource.clear();
						dataSource.putAll(Constants.DATA_SOURCE);
					}
					for (YouSource taoSource : taoSourceList) {
						dataSource.put(taoSource.getSource(), taoSource.getSourceName());
					}
					data = dataSource;
					XMemCachedUtil.setCache(key, data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
					XMemCachedUtil.setCache(XMemCachedUtil.getNewKey(key), data, XMemCachedUtil.YOU_CACHE_TIME_24HOUR);
				}
			}
		}
		return data;
	}
}
