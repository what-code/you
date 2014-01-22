package com.b5m.you.dao;

import java.util.List;

import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.IBaseDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGoods;

public interface IYouGoodsDao extends IBaseDao<YouGoods> {

	/**
	 * 首页展示所有当天包邮（根据条件）查询商品
	 * 
	 * @param ttDto
	 *            查询dto条件
	 * @param price
	 *            查询价格参数
	 * @param dto
	 *            分页dto
	 * @return 查询商品集合
	 */
	public B5MPageList<YouGoods> findYouGoodsByList(YouSearchDto ttDto, String youType);

	/**
	 * 查询包邮人气商品集合
	 * 
	 * @param postal
	 *            包邮
	 * @param salesPrice
	 *            现价
	 * @return 集合
	 */
	public List<YouGoods> findYouGoods(int hotCount);

	/**
	 * 查询商品明细
	 * 
	 * @return
	 */
	public YouGoods findYouGoodsById(YouSearchDto dto);

	/**
	 * 查询首页按类型查询
	 * 
	 * @param ttjSearchDto
	 *            相关条件dto
	 * @return 置顶集合数据
	 */
	public List<YouGoods> findsIndexSpecialTravelByList(YouSearchDto ttDto, String youType);

	/**
	 * 点击数
	 * 
	 * @param dto
	 */
	public Integer totalClick(YouSearchDto dto);

	/**
	 * 获得帮5游站点城市
	 * 
	 * @return
	 */
	public List<String> getYouCity(YouSearchDto dto);
	
	public List getSiteMap(YouSearchDto dto);
	
	public List<YouGoods> findTopicYouGoods(YouSearchDto dto);
}
