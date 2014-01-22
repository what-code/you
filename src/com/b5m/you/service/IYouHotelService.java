package com.b5m.you.service;

import java.util.*;

import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.IBaseService;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.HotelKeyWords;
import com.b5m.you.model.YouHotel;

/**
 * Title:IYouHotelService.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013717
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Shengjie Guo
 * 
 * @version 1.0
 */
public interface IYouHotelService extends IBaseService<YouHotel> {
	public B5MPageList<YouHotel> getYouHotelList(YouSearchDto ttDto);

	public List<YouHotel> findsIndexHotelByList(YouSearchDto ttDto);

	public YouHotel getYouHotelById(YouSearchDto ttDto);

	public Map<String, String> getHotelCity();

	public void totalClick(YouSearchDto dto);

	public List<YouHotel> findsLandingPageByList(YouSearchDto ttDto, String cId);

	public List<YouHotel> findsNotesPageByList(String city, YouSearchDto dto);
	
	public List getSiteMapList(YouSearchDto dto);
	
	public Map getMapKeyWordsList(YouSearchDto dto);
	
	public B5MPageList<YouHotel> getMetroHotelList(YouSearchDto ttDto);
	
	public HotelKeyWords getMetroHotelListByType(YouSearchDto dto,String type);
	
	public List findTopicYouHotels(YouSearchDto dto);
	
	public List getHotelsForMap(YouSearchDto dto);
	
	public String getCityCenterLatAndLong(YouSearchDto dto);
	
	public List<YouHotel> getRecommendHotel(YouSearchDto dto);
}