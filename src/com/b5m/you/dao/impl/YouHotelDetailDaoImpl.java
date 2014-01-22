package com.b5m.you.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.b5m.web.core.AbstractBaseDao;
import com.b5m.you.dao.IYouHotelDetailDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouHotelDetail;

@Repository
public class YouHotelDetailDaoImpl extends AbstractBaseDao<YouHotelDetail> implements IYouHotelDetailDao {

	@Override
	public List<YouHotelDetail> getYouHotelDetailById(YouSearchDto ttDto) {
		return this.getListByWhere("WHERE htId =?", new Object[] { Integer.valueOf(ttDto.getClikcId()) });
	}
}
