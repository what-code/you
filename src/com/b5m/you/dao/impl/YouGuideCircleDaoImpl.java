package com.b5m.you.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.b5m.web.core.AbstractBaseDao;
import com.b5m.you.dao.IYouGuideCircleDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGuideCircle;

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
public class YouGuideCircleDaoImpl extends AbstractBaseDao<YouGuideCircle> implements IYouGuideCircleDao {

	@Override
	public List<YouGuideCircle> findGuideCircle(YouSearchDto dto) {
		return this.getHqlList("FROM YouGuideCircle WHERE guideId = " + dto.getClikcId(), 0, 2);
		// return this.getListByWhere("WHERE guideId =?", new Object[] { Integer.valueOf(dto.getClikcId()) });
	}

}
