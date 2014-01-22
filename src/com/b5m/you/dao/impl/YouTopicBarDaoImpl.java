package com.b5m.you.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.b5m.web.core.AbstractBaseDao;
import com.b5m.you.dao.IYouTopicBarDao;
import com.b5m.you.model.YouTopicBar;

@Repository
public class YouTopicBarDaoImpl extends AbstractBaseDao<YouTopicBar> implements IYouTopicBarDao {

	@Override
	public List<YouTopicBar> findActivityTopicBar(Integer id) {
		return getListByWhere("WHERE topicId =? order by sort desc", new Object[] { id });
	}
}
