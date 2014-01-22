package com.b5m.you.service;

import java.util.List;

import com.b5m.web.core.IBaseService;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouHotelDetail;

public interface IYouHotelDetailService extends IBaseService<YouHotelDetail> {
	
	public List<YouHotelDetail> getYouHotelDetailById(YouSearchDto ttDto);
}
