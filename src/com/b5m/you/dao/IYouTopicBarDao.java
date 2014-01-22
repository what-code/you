package com.b5m.you.dao;

import java.util.List;

import com.b5m.web.core.IBaseDao;
import com.b5m.you.model.YouTopicBar;

public interface IYouTopicBarDao extends IBaseDao<YouTopicBar> {

	/**
	 * 查找活动页模版内容数据
	 * 
	 * @return list
	 */
	public List<YouTopicBar> findActivityTopicBar(Integer id);

}
