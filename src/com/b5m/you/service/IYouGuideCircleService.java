package com.b5m.you.service;

import java.util.List;

import com.b5m.web.core.IBaseService;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGuideCircle;

/**
 * Title:IYouGuideService.java
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
public interface IYouGuideCircleService extends IBaseService<YouGuideCircle> {

	public List<YouGuideCircle> findGuideCircle(YouSearchDto dto);
}
