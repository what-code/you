package com.b5m.you.dao;

import com.b5m.web.core.IBaseDao;
import com.b5m.you.model.YouTopicMain;

public interface IYouTopicMainDao extends IBaseDao<YouTopicMain> {

	/**
	 * 查找活动页模版数据
	 * 
	 * @return TaoTopicMain
	 */
	public YouTopicMain findActivityTopicMain(String id);

}
