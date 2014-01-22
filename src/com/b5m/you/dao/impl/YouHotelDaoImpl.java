package com.b5m.you.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.b5m.web.core.AbstractBaseDao;
import com.b5m.web.core.B5MCondition;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.B5MQuery;
import com.b5m.web.core.Constants;
import com.b5m.web.core.ContextUtils;
import com.b5m.you.common.util.SF1Util;
import com.b5m.you.dao.IYouHotelDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.HotelMapKeyWord;
import com.b5m.you.model.YouHotel;
import com.izenesoft.sf1r.bean.search.SF1SearchBean;
import com.izenesoft.sf1r.bean.search.SortSearchBean;

/**
 * Title:YouHotelDaoImpl.java
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
@Repository
public class YouHotelDaoImpl extends AbstractBaseDao<YouHotel> implements IYouHotelDao {

	//@Override
	public B5MPageList<YouHotel> getYouHotelListNew(YouSearchDto ttDto) {
		B5MQuery bq = new B5MQuery();
		
		boolean searchFromSF1 = false;
		//组装SF1的查询条件
		SF1SearchBean sfb = new SF1SearchBean();
		if(ContextUtils.getInstance().isOpenSF1HotelSearch() && StringUtils.isNotBlank(ttDto.getKeyWords())){
			searchFromSF1 = true;
		}
		if(!searchFromSF1){
			bq.setHqlWhere(" now()  BETWEEN b5m_list_time AND b5m_delist_time AND checkstatus = '1'");
		}

		if (StringUtils.isNotBlank(ttDto.getKeyWords()) && !ttDto.isKeyWordInMap()) {
			if(searchFromSF1){
				//SF1条件组装
				sfb.setKeyWord(ttDto.getKeyWords());
			}else{
				bq.addCondition(new B5MCondition("name", "LIKE", "%" + ttDto.getKeyWords() + "%"));
			}
		}
		
		//用纬度,经度的范围过滤数据
		if(ttDto.isKeyWordInMap() && StringUtils.isNotBlank(ttDto.getLongitudeRange()) 
				&& StringUtils.isNotBlank(ttDto.getLatitudeRange())){
			String latLow = ttDto.getLatitudeRange().split("-")[0];
			String latHigh = ttDto.getLatitudeRange().split("-")[1];
			
			String lngLow = ttDto.getLongitudeRange().split("-")[0];
			String lngHigh = ttDto.getLongitudeRange().split("-")[1];
			
			if(searchFromSF1){
				//SF1条件组装
				
				
				sfb.addCondition("Latitude", ">=", new Double(Double.parseDouble(latLow)*3600*128).longValue()+"");
				sfb.addCondition("Latitude", "<=", new Double(Double.parseDouble(latHigh)*3600*128).longValue()+"");
				sfb.addCondition("Longitude", ">=", new Double(Double.parseDouble(lngLow)*3600*128).longValue()+"");
				sfb.addCondition("Longitude", "<=", new Double(Double.parseDouble(lngHigh)*3600*128).longValue()+"");
			}else{
				bq.setAppend(" and latitude BETWEEN " + latLow + " and " + latHigh + " and longitude BETWEEN " + lngLow + " and " + lngHigh);
			}
		}else{
			if("map".equals(ttDto.getPageMode()) && searchFromSF1){
				sfb.addCondition("Latitude", ">","0");
				sfb.addCondition("Longitude", ">", "0");
			}
		}
		
		if("mapNoResult".equals(ttDto.getPageMode())){
			bq.setAppend(bq.getAppend() + " and new_latitude is not null and new_longitude is not null ");
		}
		/*if (!("").equals(ttDto.getIpLocate()) && null != ttDto.getIpLocate() && !("全部").equals(ttDto.getIpLocate())
				&& !Constants.INDEX.equals(ttDto.getAjaxType())) {
			bq.addCondition(new B5MCondition("city", "=", ttDto.getIpLocate()));
		}*/
		
		//推荐部分
		if(Constants.B5M_HOTEL_SAME_CITY.equals(ttDto.getSearchType())){
			if(!"-1".equals(ttDto.getSelectedCityId())){
				if(searchFromSF1){
					//SF1条件组装
					sfb.addCondition("City", "=", Constants.XML_CITY.get(ttDto.getSelectedCityId()));
				}else{
					bq.addCondition(new B5MCondition("city", "=", Constants.XML_CITY.get(ttDto.getSelectedCityId())));
				}
			}else{
				if(searchFromSF1){
					//SF1条件组装
					sfb.addCondition("City", "=", "上海");
				}else{
					bq.addCondition(new B5MCondition("city", "=", "上海"));
				}
			}
		}else if(!"-1".equals(ttDto.getSelectedCityId())){
			if(searchFromSF1){
				//SF1条件组装
				sfb.addCondition("City", "=",Constants.XML_CITY.get(ttDto.getSelectedCityId()));
			}else{
				bq.addCondition(new B5MCondition("city", "=", Constants.XML_CITY.get(ttDto.getSelectedCityId())));
			}
		}		
		//价格区间
		if(ttDto.getHotelPrice() != null && !"".equals(ttDto.getHotelPrice())){
			if("0".equals(ttDto.getHotelPrice())){
				if(searchFromSF1){
					//SF1条件组装
					sfb.addCondition("SalesPrice", "<","150");
				}else{
					bq.addCondition(new B5MCondition("salesPrice", "<", 150));
				}
			}
			
			if("1000".equals(ttDto.getHotelPrice())){
				if(searchFromSF1){
					//SF1条件组装
					sfb.addCondition("SalesPrice", ">","600");
				}else{
					bq.addCondition(new B5MCondition("salesPrice", ">", 600));
				}
			}
			
			if(!"0".equals(ttDto.getHotelPrice()) && !"1000".equals(ttDto.getHotelPrice())){
				String[] temp = ttDto.getHotelPrice().split("-");
				if(!"".equals(temp[0])){
					if(searchFromSF1){
						//SF1条件组装
						sfb.addCondition("SalesPrice", ">=",temp[0]);
					}else{
						bq.addCondition(new B5MCondition("salesPrice", ">=", Integer.parseInt(temp[0])));
					}
				}
				if(temp.length == 2){
					if(searchFromSF1){
						//SF1条件组装
						sfb.addCondition("SalesPrice", "<",temp[1]);
					}else{
						bq.addCondition(new B5MCondition("salesPrice", "<", Integer.parseInt(temp[1])));
					}
				}
			}
		}
		
		//酒店星级
		if(ttDto.getHotelStar() != null && !"".equals(ttDto.getHotelStar())){
			if("0".equals(ttDto.getHotelStar())){
				if(searchFromSF1){
					//SF1条件组装
					sfb.addCondition("LevelStar", "<","3");
				}else{
					bq.addCondition(new B5MCondition("levelInfo", "<", "3"));
				}
			}
			
			if("1000".equals(ttDto.getHotelStar())){
				if(searchFromSF1){
					//SF1条件组装
					sfb.addCondition("LevelStar", ">","5");
				}else{
					bq.addCondition(new B5MCondition("levelInfo", ">", "5"));
				}
			}
			
			if(!"0".equals(ttDto.getHotelStar()) && !"1000".endsWith(ttDto.getHotelStar())){
				if(searchFromSF1){
					//SF1条件组装
					sfb.addCondition("LevelStar", "=",ttDto.getHotelStar());
				}else{
					bq.addCondition(new B5MCondition("levelInfo", "=", ttDto.getHotelStar()));
				}
			}
		}
		
		if (!StringUtils.isBlank(ttDto.getOrder())) {
			bq.setOrderBy(ttDto.getOrder() + " " + ttDto.getSort());
			System.out.println("---------------------------------------order:"+ttDto.getOrder()+"----"+ttDto.getSort());
			//salesPrice----DESC
			//totalClick----DESC
			if(searchFromSF1){				
				//*********SF1查询条件设置*************
				if("salesPrice".equals(ttDto.getOrder().trim())){
					sfb.addSort("SalesPrice", ttDto.getSort());
				}
				if("totalClick".equals(ttDto.getOrder().trim())){
					sfb.addSort("TotalClick", ttDto.getSort());
				}
			}
		} else {
			if(searchFromSF1){
				//SF1条件组装
				sfb.addSort("Spread", "desc");
			}else{
				bq.setOrderBy("spread DESC, id DESC");
			}
		}
		if(searchFromSF1){				
			//*********SF1查询条件设置*************
			sfb.setLimit(ttDto.getPageSize());
			sfb.setOffset((ttDto.getCurrPageNo()-1)*ttDto.getPageSize());
		}else{
			bq.setPageNo(ttDto.getCurrPageNo());
			bq.setPageSize(ttDto.getPageSize());
		}
		// 详情页推荐条数
		if (Constants.B5M_HOTEL_SAME_CITY.equals(ttDto.getSearchType())) {
			bq.setPageSize(Constants.YOU_RECOMMEND_SZIE + 1);
		}
		
		if(searchFromSF1){
			return SF1Util.getYouHotelFromSF1(sfb,ttDto.getCurrPageNo(),ttDto.getPageSize());
		}else{
			return this.getPageList(bq);
		}
	}

	/**
	 * 新的酒店搜索
	 * @param ttDto
	 * @return
	 */
	@Override
	public B5MPageList<YouHotel> getYouHotelList(YouSearchDto ttDto) {
		SF1SearchBean sfb = new SF1SearchBean();
		
		//city
		if(!"-1".equals(ttDto.getSelectedCityId())){
			sfb.addCondition("City", "=",Constants.XML_CITY.get(ttDto.getSelectedCityId()));
		}
		
		//keyword
		if (StringUtils.isNotBlank(ttDto.getKeyWords())) {
			sfb.setKeyWord(ttDto.getKeyWords());
		}
		
		//入住日期和结束日期
		//TODO
		
		//酒店星级
		if(ttDto.getHotelStar() != null && !"".equals(ttDto.getHotelStar())){
			if("0".equals(ttDto.getHotelStar())){
				sfb.addCondition("LevelStar", "<","3");
			}
			
			if("1000".equals(ttDto.getHotelStar())){
				sfb.addCondition("LevelStar", ">","5");
			}
			
			if(!"0".equals(ttDto.getHotelStar()) && !"1000".endsWith(ttDto.getHotelStar())){
				sfb.addCondition("LevelStar", "=",ttDto.getHotelStar());
			}
		}
		
		//排序
		if (!StringUtils.isBlank(ttDto.getOrder())) {
			if("salesPrice".equals(ttDto.getOrder().trim())){
				sfb.addSort("SalesPrice", ttDto.getSort());
			}
			if("totalClick".equals(ttDto.getOrder().trim())){
				sfb.addSort("TotalClick", ttDto.getSort());
			}
		}else{
			sfb.addSort("Spread", SortSearchBean.DESC);
			//sfb.addSort("Img", SortSearchBean.DESC);
		}
				
		//*********SF1查询条件设置*************
		sfb.setLimit(ttDto.getPageSize());
		sfb.setOffset((ttDto.getCurrPageNo()-1)*ttDto.getPageSize());
		
		return SF1Util.getYouHotelFromSF1(sfb,ttDto.getCurrPageNo(),ttDto.getPageSize());
	}
	
	@Override
	public List<YouHotel> findsIndexHotelByList(YouSearchDto ttDto) {
		return this.getHqlList(
				"FROM YouHotel WHERE now() BETWEEN b5m_list_time AND b5m_delist_time AND checkstatus = '1' ORDER BY spread DESC, id DESC", 0, 12);
	}

	@Override
	public YouHotel getYouHotelById(YouSearchDto dto) {
		return this.getById(Integer.parseInt(dto.getClikcId()));
	}

	@Override
	public List<String> getHotelCity() {
		List<String> list = new ArrayList();
		list.add("全部");
		List<String> temp = this.getHibernateTemplate().find(
				"SELECT city FROM YouHotel WHERE (now() BETWEEN b5m_list_time AND b5m_delist_time) AND checkstatus='1' group by city");
		list.addAll(temp);
		return list;
	}

	@Override
	public List<YouHotel> findsLandingPageByList(YouSearchDto ttDto, String cId) {
		return this.getHqlList("FROM YouHotel WHERE now() BETWEEN b5m_list_time AND b5m_delist_time AND checkstatus = '1' AND cId = " + cId
				+ " ORDER BY spread DESC, id DESC", 0, ttDto.getPageSize());
	}

	@Override
	public List<YouHotel> findsNotesPageByList(YouSearchDto dto) {
		String temp = "";
		if(!"-1".equals(dto.getSelectedCityId())){
			temp = "AND city = '" + Constants.XML_CITY.get(dto.getSelectedCityId()) + "'";
		}
		return this.getHqlList(
				"FROM YouHotel WHERE now() BETWEEN b5m_list_time AND b5m_delist_time AND checkstatus = '1' " + temp + " ORDER BY spread DESC, id DESC", 0, dto.getPageSize());
	}

	@Override
	public List getMapSite(YouSearchDto dto) {
		String temp = "";
		if(dto.getIpLocate() != null && !dto.getIpLocate().equals("")){
			temp = temp + " and city='" + dto.getIpLocate() + "'";
		}
		return this.getHibernateTemplate().find("SELECT id,name,city FROM YouHotel where 1=1 " + temp);
	}

	@Override
	public List<HotelMapKeyWord> getMapKeyWordsList() {
		return this.getHibernateTemplate().find("FROM HotelMapKeyWord where item1 != '1'");
	}
	
	@Override
	public List<YouHotel> findTopicYouHotels(YouSearchDto dto) {
		return this.getHqlList("FROM YouHotel WHERE now() BETWEEN b5m_list_time AND b5m_delist_time AND checkstatus = '1' AND name like '%" + dto.getKeyWords()
				+ "%' ORDER BY spread DESC, id DESC", 0, 20);
	}

	/**
	 * 获取广告酒店
	 */
	@Override
	public List<YouHotel> findAdsHotels(YouSearchDto dto) {
		return this.getHqlList("FROM YouHotel WHERE now() BETWEEN b5m_list_time AND b5m_delist_time AND checkstatus = '1' AND ads_location = '" + dto.getAdLocation()
				+ "' ORDER BY spread DESC, id DESC", 0, 10);
	}
}
