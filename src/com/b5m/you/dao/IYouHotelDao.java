package com.b5m.you.dao;

import java.util.List;

import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.IBaseDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.HotelMapKeyWord;
import com.b5m.you.model.YouHotel;

/**
 * Title:IYouHotelDao.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-7-17
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Shengjie Guo
 * 
 * @version 1.0
 */
public interface IYouHotelDao extends IBaseDao<YouHotel> {
	public B5MPageList<YouHotel> getYouHotelList(YouSearchDto ttDto);

	public List<YouHotel> findsIndexHotelByList(YouSearchDto ttDto);

	public YouHotel getYouHotelById(YouSearchDto dto);

	public List<String> getHotelCity();
	
	public List<YouHotel> findsLandingPageByList(YouSearchDto ttDto, String cId);
	
	public List<YouHotel> findsNotesPageByList(YouSearchDto ttDto);

	public List getMapSite(YouSearchDto dto);
	
	public List<HotelMapKeyWord> getMapKeyWordsList();
	
	public List<YouHotel> findTopicYouHotels(YouSearchDto dto);
	
	public List<YouHotel> findAdsHotels(YouSearchDto dto);
}
