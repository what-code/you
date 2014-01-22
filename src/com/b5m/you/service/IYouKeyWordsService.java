package com.b5m.you.service;

import java.util.List;

import com.b5m.web.core.IBaseService;
import com.b5m.you.model.YouKeyWords;

public interface IYouKeyWordsService extends IBaseService<YouKeyWords> {

	/**
	 * 关键字查找
	 * @return
	 */
	public List<YouKeyWords> findKeyWords();
}
