package com.b5m.you.dao;

import java.util.List;

import com.b5m.web.core.IBaseDao;
import com.b5m.you.model.YouTopic;

public interface IYouTopicDao extends IBaseDao<YouTopic> {

	/**
	 * 查找轮播专题位
	 * 
	 * @return list
	 */
	public List<YouTopic> findCarouselTopic();
	
	/**
	 * 查找首页专题位
	 * 
	 * @return list
	 */
	public List<YouTopic> findIndexTopTopic();
}
