package com.b5m.you.dao;

import java.util.List;

import com.b5m.web.core.IBaseDao;
import com.b5m.you.model.DestinationCitys;

public interface IYouCitysDao extends IBaseDao<DestinationCitys> {
	public List getAllCitys();
}
