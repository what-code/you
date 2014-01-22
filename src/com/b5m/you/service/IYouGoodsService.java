package com.b5m.you.service;

import java.util.List;
import java.util.Map;

import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.IBaseService;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGoods;
import com.b5m.you.model.YouTopicBar;
import com.b5m.you.model.YouTopicMain;

public interface IYouGoodsService extends IBaseService<YouGoods> {

	/**
	 * 国内游
	 * 
	 * @param dto
	 *            分页dto
	 * @param ttjSearchDto
	 *            搜索相关条件dto
	 * @return List<TaoGoods> 分页集合
	 */
	public B5MPageList<YouGoods> getYouGoodsByDomesticTravel(YouSearchDto ttDto);

	/**
	 * 境外游
	 * 
	 * @param dto
	 *            分页dto
	 * @param ttjSearchDto
	 *            搜索相关条件dto
	 * @return List<TaoGoods> 分页集合
	 */
	public B5MPageList<YouGoods> getYouGoodsByAbroadTravel(YouSearchDto ttDto);

	/**
	 * 周边游
	 * 
	 * @param dto
	 *            分页dto
	 * @param ttjSearchDto
	 *            相关条件dto
	 * @return List<TaoGoods> 分页集合
	 */
	public B5MPageList<YouGoods> getYouGoodsByPeripheryTravel(YouSearchDto dto);

	/**
	 * 搜索结果页 首页数据
	 * 
	 * @param dto
	 *            分页dto
	 * @param ttjSearchDto
	 *            搜索相关条件dto
	 * @return 分页集合
	 */
	public B5MPageList<YouGoods> getYouGoodsSearch(YouSearchDto ttjSearchDto);

	/**
	 * 查询包邮人气商品集合
	 * 
	 * @param postal
	 *            包邮
	 * @param salesPrice
	 *            现价
	 * @param hotCount
	 *            集合数量
	 * @return 集合
	 */
	public List<YouGoods> getTaoGoods(int hotCount);

	/**
	 * 查询首页置顶商品
	 * 
	 * @param ttjSearchDto
	 *            相关条件dto
	 * @return 置顶集合数据
	 */
	public List<YouGoods> findsIndexSpecialTravelByList(YouSearchDto dto, String youType);

	/**
	 * 详细页
	 * 
	 * @param dto
	 * @return
	 */
	public YouGoods findYouGoodsById(YouSearchDto dto);

	/**
	 * 点击数增加
	 * 
	 * @param dto
	 */
	public void totalClick(YouSearchDto dto);

	/**
	 * 获得帮5游站点城市
	 * 
	 * @return
	 */
	public Map<String, String> getYouCity(YouSearchDto dto);
	
	
	public List getCitysByKeyword(String keyword);
	
	//网站地图数据列表
	public List getSiteMapList(YouSearchDto dto);
	
	public YouTopicMain findActivityTopicMain(String id);
	
	public List<YouTopicBar>  findActivityTopicBar(Integer id);
	
	public List<YouGoods> findTopicYouGoods(YouSearchDto dto);

}
