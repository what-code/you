package com.b5m.you.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.b5m.web.core.AbstractBaseDao;
import com.b5m.you.dao.IYouCitysDao;
import com.b5m.you.model.DestinationCitys;

/**
 * Title:YouGuideCircleDaoImpl.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-7-30
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Jia Liu
 * 
 * @version 1.0
 */
@Repository
public class YouCitysDaoImpl extends AbstractBaseDao<DestinationCitys>
		implements IYouCitysDao {

	@Override
	public List getAllCitys() {
		return this.getSqlList("from DestinationCitys");
	}
}
