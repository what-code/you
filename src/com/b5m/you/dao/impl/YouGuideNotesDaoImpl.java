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
import com.b5m.you.dao.IYouGuideNotesDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGuideNotes;
import com.izenesoft.sf1r.bean.search.SF1SearchBean;

/**
 * Title:YouGuideNotesDaoImpl.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-7-30
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Jia Liu
 * 
 * @version 1.0
 */
@Repository
public class YouGuideNotesDaoImpl extends AbstractBaseDao<YouGuideNotes> implements IYouGuideNotesDao {

	@Override
	public List<YouGuideNotes> findGuideNotes(YouSearchDto dto) {
		return this.getListByWhere("WHERE guideId =? ORDER BY addtime DESC", new Object[] { Integer.valueOf(dto.getClikcId()) });
	}

	@Override
	public List<YouGuideNotes> findGuideNotesBySearchResul(String ipLocate, String limit) {
		String city = "";
		if (!StringUtils.isBlank(ipLocate)) {
			city = "WHERE city = '" + ipLocate + "'";
		}
		return this.getHqlList("FROM YouGuideNotes " + city + " ORDER BY totalClick DESC", 0, Integer.valueOf(limit));
	}

	//@Override
	public B5MPageList<YouGuideNotes> findGuideNotesBySearchResulOld(YouSearchDto dto) {
		B5MQuery bq = new B5MQuery();

		boolean searchFromSF1 = false;
		//组装SF1的查询条件
		SF1SearchBean sfb = new SF1SearchBean();
		if(ContextUtils.getInstance().isOpenSF1TravelGuideSearch() && StringUtils.isNotBlank(dto.getKeyWords())){
			searchFromSF1 = true;
		}
		
		if (StringUtils.isNotBlank(dto.getKeyWords())) {
			if(searchFromSF1){
				//SF1条件组装
				sfb.setKeyWord(dto.getKeyWords());
			}else{
				bq.addCondition(new B5MCondition("name", "LIKE", "%" + dto.getKeyWords() + "%"));
			}
		}

		//if (!("").equals(dto.getIpLocate()) && null != dto.getIpLocate() && !("全部").equals(dto.getIpLocate())
		//		&& !Constants.INDEX.equals(dto.getAjaxType())) {
		//	bq.addCondition(new B5MCondition("city", "=", dto.getIpLocate()));
		//}
		if(!"-1".equals(dto.getSelectedCityId())){
			if(searchFromSF1){
				//SF1条件组装
				sfb.addCondition("City", "=", Constants.XML_CITY.get(dto.getSelectedCityId()));
			}else{
				bq.addCondition(new B5MCondition("city", "=", Constants.XML_CITY.get(dto.getSelectedCityId())));
			}
		}
		if(Constants.NOTES_NEW.equals(dto.getOrder())){
			if(searchFromSF1){
				//SF1条件组装
				sfb.addSort("PublishTime", "desc");
			}else{
				bq.setOrderBy(" ORDER BY publish_time DESC");
			}
		}else{
			if(searchFromSF1){
				//SF1条件组装
				sfb.addSort("TotalClick", "desc");
			}else{
				bq.setOrderBy(" ORDER BY total_click DESC");
			}
		}
		//bq.setGroupBy(" GROUP BY city");
		if(searchFromSF1){				
			//*********SF1查询条件设置*************
			sfb.setLimit(dto.getPageSize());
			sfb.setOffset((dto.getCurrPageNo()-1)*dto.getPageSize());
		}else{
			bq.setPageNo(dto.getCurrPageNo());
			bq.setPageSize(dto.getPageSize());
		}
		if(searchFromSF1){
			return SF1Util.getYouGuideFromSF1(sfb,dto.getCurrPageNo(),dto.getPageSize());
		}else{
			return this.getPageList(bq);
		}
	}
	
	@Override
	public B5MPageList<YouGuideNotes> findGuideNotesBySearchResult(YouSearchDto dto) {
		//组装SF1的查询条件
		SF1SearchBean sfb = new SF1SearchBean();
		if (StringUtils.isNotBlank(dto.getKeyWords())) {
			sfb.setKeyWord(dto.getKeyWords());
		}
		
		//攻略对城市不做处理
//		if(!"-1".equals(dto.getSelectedCityId())){
//			sfb.addCondition("City", "=", Constants.XML_CITY.get(dto.getSelectedCityId()));
//		}
		
		if(Constants.NOTES_NEW.equals(dto.getOrder())){
			sfb.addSort("PublishTime", "desc");
		}else{
			sfb.addSort("TotalClick", "desc");
		}
			
		sfb.setLimit(dto.getPageSize());
		sfb.setOffset((dto.getCurrPageNo()-1)*dto.getPageSize());
		return SF1Util.getYouGuideFromSF1(sfb,dto.getCurrPageNo(),dto.getPageSize());
	}

	@Override
	public List<String> findNotesCity() {
		List<String> list = new ArrayList();
		list.add("全部");
		list.addAll(this.getHibernateTemplate().find("SELECT city FROM YouGuideNotes GROUP BY city"));
		return list;
	}

	@Override
	public YouGuideNotes findYouGuideNotesDetail(String notesId) {
		return this.getById(Integer.valueOf(notesId));
	}
}
