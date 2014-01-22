package com.b5m.you.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.b5m.web.core.AbstractBaseDao;
import com.b5m.you.dao.IYouSourceDao;
import com.b5m.you.model.YouSource;

@Repository
public class YouSourceDaoImapl extends AbstractBaseDao<YouSource> implements IYouSourceDao {

	/**
	 * 查找数据源
	 * 
	 * @return list
	 */
	@Override
	public List<YouSource> findTaoSource() {
		return this.getList();
	}
}
