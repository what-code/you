package com.b5m.you.service;

import java.util.List;

import com.b5m.web.core.B5MPageList;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.TuanModel;
import com.b5m.you.model.YouGoods;
import com.b5m.you.model.YouTravel;

public interface IYouTravelService {

	/**
	 * 列表页数据
	 * 
	 * @return 翻页数据
	 */
	public B5MPageList<YouGoods> findTravelByList(YouSearchDto dto);

	/**
	 * 团购数据
	 * 
	 * @param city
	 * @param type
	 * @param size
	 * @return
	 */
	public List<TuanModel> findTravelTuan(String city, String type, int size);

	/**
	 * 首页当季热门
	 * 
	 * @return
	 */
	public List<YouTravel> findIndexHotTravel(String travelType);
}
