package com.b5m.you.dao;

import java.util.List;

import com.b5m.web.core.IBaseDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouHotelDetail;

public interface IYouHotelDetailDao extends IBaseDao<YouHotelDetail> {

	public List<YouHotelDetail> getYouHotelDetailById(YouSearchDto ttDto);
}
