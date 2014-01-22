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
import com.b5m.you.model.HotelKeyWords;
import com.b5m.you.model.YouHotel;
import com.b5m.you.service.IYouHotelService;

/**
 * <p>
 * 此类组合了{@link YouHotelServiceImpl}类，包装了此类的{@link IYouHotelService}接口，只对 {@link #totalClick(Integer)}进行修改。
 * 
 * <p>
 * {@link #totalClick(Integer)}将引用raindrop项目中的raindrop-tao-client模块，并调用 {@link ICounterService}实现商品点击数的统计功能。
 * 
 * @author jacky
 * 
 */
@Service("hotelServiceRaindrop")
public class YouHotelServiceRaindrop implements IYouHotelService {
	public static Logger logger = Logger.getLogger(YouHotelServiceRaindrop.class);
	/**
	 * hotelService的默认实现
	 */
	@Resource(name = "hotelService")
	private IYouHotelService _origService;

	@Resource
	private ICounterService counterService2;

	public YouHotelServiceRaindrop() {
	}

	public ICounterService getCounterService() {
		return counterService2;
	}

	public void setCounterService2(ICounterService counterService2) {
		this.counterService2 = counterService2;
	}

	@Override
	public YouHotel getById(String id) {
		return _origService.getById(id);
	}

	@Override
	public YouHotel getById(Integer id) {
		return _origService.getById(id);
	}

	@Override
	public int save(Object obj) {

		return _origService.save(obj);
	}

	@Override
	public int update(YouHotel obj) {

		return _origService.update(obj);
	}

	@Override
	public int remove(YouHotel obj) {

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
	public List<YouHotel> getList() {

		return _origService.getList();
	}

	@Override
	public List getListByQuery(String hql, B5MQuery dto) {

		return _origService.getListByQuery(hql, dto);
	}

	@Override
	public List<YouHotel> getListByQuery(B5MQuery dto) {

		return _origService.getListByQuery(dto);
	}

	@Override
	public List<YouHotel> getListByWhere(String hqlWhere) {

		return _origService.getListByWhere(hqlWhere);
	}

	@Override
	public List<YouHotel> getListByWhere(String hqlWhere, Object[] whereArgs) {

		return _origService.getListByWhere(hqlWhere, whereArgs);
	}

	@Override
	public <O> List<O> getListByWhere(String hqlWhere, Class<O> clazz) {

		return _origService.getListByWhere(hqlWhere, clazz);
	}

	@Override
	public <O> List<O> getListByWhere(String hqlWhere, Object[] whereArgs, Class<O> clazz) {

		return (List<O>) _origService.getByWhere(hqlWhere, whereArgs, clazz);
	}

	@Override
	public YouHotel getByWhere(String hqlWhere) {

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
	public B5MPageList<YouHotel> getPageList(B5MQuery dto) {

		return _origService.getPageList(dto);
	}

	@Override
	public int executeUpdate(String hql) {

		return _origService.executeUpdate(hql);
	}

	@Override
	public int executeUpdate(String[] hql) {

		return _origService.executeUpdate(hql);
	}

	/*@Override
	public JdbcTemplate getJdbcTemplate() {

		return _origService.getJdbcTemplate();
	}*/

	@Override
	public B5MPageList<YouHotel> getYouHotelList(YouSearchDto ttDto) {

		return _origService.getYouHotelList(ttDto);
	}

	@Override
	public List<YouHotel> findsIndexHotelByList(YouSearchDto ttDto) {

		return _origService.findsIndexHotelByList(ttDto);
	}

	@Override
	public YouHotel getYouHotelById(YouSearchDto ttDto) {

		return _origService.getYouHotelById(ttDto);
	}

	@Override
	public Map<String, String> getHotelCity() {

		return _origService.getHotelCity();
	}

	@Override
	public void totalClick(YouSearchDto dto) {
		// _origService.totalClick(dto);
		final Integer _goodsId = Integer.parseInt(dto.getClikcId());
		logger.info("It would direct to invoke {totalClick} hotel method " + _goodsId);
		counterService2.clickGoods(dto.getClikcId(), new IExceptionCallback() {
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
	public List<YouHotel> findsLandingPageByList(YouSearchDto ttDto, String cId) {
		return _origService.findsLandingPageByList(ttDto, cId);
	}

	@Override
	public List<YouHotel> findsNotesPageByList(String city, YouSearchDto dto) {
		return _origService.findsNotesPageByList(city, dto);
	}

	@Override
	public List getSiteMapList(YouSearchDto dto) {
		return _origService.getSiteMapList(dto);
	}

	@Override
	public Map getMapKeyWordsList(YouSearchDto dto) {
		return _origService.getMapKeyWordsList(dto);
	}

	@Override
	public B5MPageList<YouHotel> getMetroHotelList(YouSearchDto ttDto) {
		return _origService.getMetroHotelList(ttDto);
	}

	@Override
	public HotelKeyWords getMetroHotelListByType(YouSearchDto dto, String type) {
		// TODO Auto-generated method stub
		return _origService.getMetroHotelListByType(dto, type);
	}

	@Override
	public List findTopicYouHotels(YouSearchDto dto) {
		// TODO Auto-generated method stub
		return _origService.findTopicYouHotels(dto);
	}

	@Override
	public List getHotelsForMap(YouSearchDto dto) {
		return _origService.getHotelsForMap(dto);
	}

	@Override
	public String getCityCenterLatAndLong(YouSearchDto dto) {
		return _origService.getCityCenterLatAndLong(dto);
	}

	@Override
	public List<YouHotel> getRecommendHotel(YouSearchDto dto) {
		return _origService.getRecommendHotel(dto);
	}
}
