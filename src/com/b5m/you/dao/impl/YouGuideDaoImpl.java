package com.b5m.you.dao.impl;

import org.springframework.stereotype.Repository;

import com.b5m.web.core.AbstractBaseDao;
import com.b5m.you.dao.IYouGuideDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGuide;

/**
 * Title:YouGuideDaoImpl.java
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
public class YouGuideDaoImpl extends AbstractBaseDao<YouGuide> implements IYouGuideDao {

	@Override
	public YouGuide findGuide(YouSearchDto dto) {
		return this.getByWhere("WHERE NOW() BETWEEN b5m_list_time AND b5m_delist_time AND checkstatus = '1' AND id =?",
				new Object[] { Integer.valueOf(dto.getClikcId()) });
	}

}
