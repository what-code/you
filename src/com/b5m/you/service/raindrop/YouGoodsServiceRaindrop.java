package com.b5m.you.service.raindrop;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.b5m.raindrop.tao.client.ICounterService;
import com.b5m.raindrop.tao.client.IExceptionCallback;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.B5MQuery;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGoods;
import com.b5m.you.model.YouTopicBar;
import com.b5m.you.model.YouTopicMain;
import com.b5m.you.service.IYouGoodsService;

/**
 * <p>
 * 此类组合了{@link YouGoodsServiceImpl}类，包装了此类的{@link IYouGoodsService}接口，只对 {@link #totalClick(Integer)}进行修改。
 * 
 * <p>
 * {@link #totalClick(Integer)}将引用raindrop项目中的raindrop-tao-client模块，并调用 {@link ICounterService}实现商品点击数的统计功能。
 * 
 * @author jacky
 * 
 */
@Service("taoGoodsServiceRaindrop")
public class YouGoodsServiceRaindrop implements IYouGoodsService {
	public static Logger logger = Logger.getLogger(YouGoodsServiceRaindrop.class);
	/**
	 * TaoGoodsService的默认实现
	 */
	@Resource(name = "taoGoodsService")
	private IYouGoodsService _origService;

	@Resource
	private ICounterService counterService1;

	public YouGoodsServiceRaindrop() {
	}

	public ICounterService getCounterService() {
		return counterService1;
	}

	@Override
	public YouGoods getById(String id) {
		return _origService.getById(id);
	}

	@Override
	public YouGoods getById(Integer id) {
		return _origService.getById(id);
	}

	@Override
	public int save(Object obj) {
		return _origService.save(obj);
	}

	@Override
	public int update(YouGoods obj) {
		return _origService.update(obj);
	}

	@Override
	public int remove(YouGoods obj) {
		return _origService.remove(obj);
	}

	@Override
	public long getCounts() {
		return _origService.getCounts();
	}

	@Override
	public long getCounts(String hql) {
		return _origService.getCounts(hql);
	}

	@Override
	public long getCounts(String hql, B5MQuery dto) {
		return _origService.getCounts(hql, dto);
	}

	@Override
	public List<YouGoods> getList() {
		return _origService.getList();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getListByQuery(String hql, B5MQuery dto) {
		return _origService.getListByQuery(hql, dto);
	}

	@Override
	public List<YouGoods> getListByQuery(B5MQuery dto) {
		return _origService.getListByQuery(dto);
	}

	@Override
	public List<YouGoods> getListByWhere(String hqlWhere) {
		return _origService.getListByWhere(hqlWhere);
	}

	@Override
	public List<YouGoods> getListByWhere(String hqlWhere, Object[] whereArgs) {
		return _origService.getListByWhere(hqlWhere, whereArgs);
	}

	@Override
	public <O> List<O> getListByWhere(String hqlWhere, Class<O> clazz) {
		return _origService.getListByWhere(hqlWhere, clazz);
	}

	@Override
	public <O> List<O> getListByWhere(String hqlWhere, Object[] whereArgs, Class<O> clazz) {
		return _origService.getListByWhere(hqlWhere, whereArgs, clazz);
	}

	@Override
	public YouGoods getByWhere(String hqlWhere) {
		return _origService.getByWhere(hqlWhere);
	}

	@Override
	public <O> O getByWhere(String hqlWhere, Class<O> clazz) {
		return _origService.getByWhere(hqlWhere, clazz);
	}

	@Override
	public <O> O getByWhere(String hqlWhere, Object[] whereArgs, Class<O> clazz) {
		return _origService.getByWhere(hqlWhere, whereArgs, clazz);
	}

	@Override
	public B5MPageList<YouGoods> getPageList(B5MQuery dto) {
		return _origService.getPageList(dto);
	}

	@Override
	public int executeUpdate(String hql) {
		return _origService.executeUpdate(hql);
	}

	@Override
	public int executeUpdate(String[] hqls) {
		return _origService.executeUpdate(hqls);
	}

	/*@Override
	public JdbcTemplate getJdbcTemplate() {
		return _origService.getJdbcTemplate();
	}*/

	@Override
	public List<YouGoods> getTaoGoods(int hotCount) {
		return _origService.getTaoGoods(hotCount);
	}

	@Override
	public List<YouGoods> findsIndexSpecialTravelByList(YouSearchDto dto, String youType) {
		return _origService.findsIndexSpecialTravelByList(dto, youType);
	}

	@Override
	public B5MPageList<YouGoods> getYouGoodsByDomesticTravel(YouSearchDto dto) {
		return _origService.getYouGoodsByDomesticTravel(dto);
	}

	@Override
	public B5MPageList<YouGoods> getYouGoodsByAbroadTravel(YouSearchDto ttDto) {
		return _origService.getYouGoodsByAbroadTravel(ttDto);
	}

	@Override
	public B5MPageList<YouGoods> getYouGoodsByPeripheryTravel(YouSearchDto dto) {
		return _origService.getYouGoodsByPeripheryTravel(dto);
	}

	@Override
	public B5MPageList<YouGoods> getYouGoodsSearch(YouSearchDto dto) {
		return _origService.getYouGoodsSearch(dto);
	}

	@Override
	public YouGoods findYouGoodsById(YouSearchDto dto) {
		return _origService.findYouGoodsById(dto);
	}

	@Override
	public void totalClick(YouSearchDto dto) {
		// _origService.totalClick(dto);
		final Integer _goodsId = Integer.parseInt(dto.getClikcId());
		logger.info("It would direct to invoke {totalClick} you method " + _goodsId);
		counterService1.clickGoods(dto.getClikcId(), new IExceptionCallback() {
			@Override
			public void onException(Throwable t) {
				sendExceptionHandle(_goodsId, t);
			}
		});
	}

	private void sendExceptionHandle(Integer goodsId, Throwable t) {
		logger.error(t.getMessage(), t);
		logger.warn("It would direct to invoke {totalClick} method");
		// _origService.totalClick(goodsId);
	}

	@Override
	public Map<String, String> getYouCity(YouSearchDto dto) {
		return _origService.getYouCity(dto);
	}

	@Override
	public List getCitysByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return _origService.getCitysByKeyword(keyword);
	}

	@Override
	public List getSiteMapList(YouSearchDto dto) {
		return _origService.getSiteMapList(dto);
	}

	@Override
	public YouTopicMain findActivityTopicMain(String id) {
		return _origService.findActivityTopicMain(id);
	}

	@Override
	public List<YouTopicBar> findActivityTopicBar(Integer id) {
		// TODO Auto-generated method stub
		return _origService.findActivityTopicBar(id);
	}

	@Override
	public List<YouGoods> findTopicYouGoods(YouSearchDto dto) {
		// TODO Auto-generated method stub
		return _origService.findTopicYouGoods(dto);
	}

}
