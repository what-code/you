package com.b5m.you.service;

import java.util.Map;

import com.b5m.web.core.IBaseService;
import com.b5m.you.model.YouSource;

public interface IYouSourceService extends IBaseService<YouSource> {
	
	/**
	 * 查找数据源
	 * 
	 * @return list
	 */
	public Map<String, String> findTaoSource();
}
