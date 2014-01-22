package com.b5m.you.dao.impl;

import org.springframework.stereotype.Repository;

import com.b5m.web.core.AbstractBaseDao;
import com.b5m.you.dao.IYouTopicMainDao;
import com.b5m.you.model.YouTopicMain;

@Repository
public class YouTopicMainDaoImpl extends AbstractBaseDao<YouTopicMain> implements IYouTopicMainDao {

	@Override
	public YouTopicMain findActivityTopicMain(String id) {
		return this.getById(Integer.valueOf(id));
	}
}
