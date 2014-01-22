package com.b5m.you.dao;

import java.util.List;

import com.b5m.web.core.IBaseDao;
import com.b5m.you.model.YouKeyWords;

public interface IYouKeyWordsDao extends IBaseDao<YouKeyWords> {

	/**
	 * 关键字查找
	 * 
	 * @return
	 */
	public List<YouKeyWords> findKeyWords();
}
