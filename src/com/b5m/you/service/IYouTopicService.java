package com.b5m.you.service;

import java.util.List;

import com.b5m.web.core.IBaseService;
import com.b5m.you.model.YouTopic;

public interface IYouTopicService extends IBaseService<YouTopic> {

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
