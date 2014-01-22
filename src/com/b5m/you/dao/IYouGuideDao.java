package com.b5m.you.dao;

import com.b5m.web.core.IBaseDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGuide;

/**
 * Title:IYouGuideDao.java
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
public interface IYouGuideDao extends IBaseDao<YouGuide> {
	public YouGuide findGuide(YouSearchDto dto);
}
