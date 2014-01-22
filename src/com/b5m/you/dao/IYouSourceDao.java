package com.b5m.you.dao;

import java.util.List;

import com.b5m.web.core.IBaseDao;
import com.b5m.you.model.YouSource;

public interface IYouSourceDao extends IBaseDao<YouSource> {

	/**
	 * 查找数据源
	 * 
	 * @return list
	 */
	public List<YouSource> findTaoSource();
}
