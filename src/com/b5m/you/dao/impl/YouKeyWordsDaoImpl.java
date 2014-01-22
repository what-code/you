package com.b5m.you.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.b5m.web.core.AbstractBaseDao;
import com.b5m.you.dao.IYouKeyWordsDao;
import com.b5m.you.model.YouKeyWords;

@Repository
public class YouKeyWordsDaoImpl extends AbstractBaseDao<YouKeyWords> implements IYouKeyWordsDao {

	@Override
	public List<YouKeyWords> findKeyWords() {
		return this.getSqlList("SELECT id,keywords,url,sort,guide_id FROM you_keywords ORDER BY sort DESC");
	}

}
