package com.b5m.you.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.b5m.web.core.AbstractBaseDao;
import com.b5m.you.dao.IYouTopicDao;
import com.b5m.you.model.YouTopic;

@Repository
public class YouTopicDaoImpl extends AbstractBaseDao<YouTopic> implements IYouTopicDao {

	/**
	 * 查找轮播专题位
	 * 
	 * @return list
	 */
	@Override
	public List<YouTopic> findCarouselTopic() {
		return this.getHqlList("FROM YouTopic WHERE now() BETWEEN start_time AND end_time AND topicType = 0 ORDER BY sort DESC", 0, 4);
	}

	/**
	 * 查找轮播专题位
	 * 
	 * @return list
	 */
	@Override
	public List<YouTopic> findIndexTopTopic() {
		return this.getHqlList("FROM YouTopic WHERE now() BETWEEN start_time AND end_time AND topicType = 1 ORDER BY sort DESC", 0, 3);
	}

}
