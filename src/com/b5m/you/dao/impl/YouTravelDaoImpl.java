package com.b5m.you.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.b5m.base.common.dao.hibernate.HibernateDaoImpl;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.Constants;
import com.b5m.you.common.util.SF1Util;
import com.b5m.you.dao.IYouTravelDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.TuanModel;
import com.b5m.you.model.YouGoods;
import com.b5m.you.model.YouTravel;
import com.izenesoft.sf1r.bean.search.SF1SearchBean;

@Repository
@Transactional
public class YouTravelDaoImpl extends HibernateDaoImpl implements IYouTravelDao {
	// extends AbstractBaseDao<YouGoods> implements IYouTranvelDao {

	public static Logger logger = Logger.getLogger(YouTravelDaoImpl.class);

	@Override
	public B5MPageList<YouGoods> findTravelByList(YouSearchDto dto) {
		// 组装SF1的查询条件
		SF1SearchBean sfb = new SF1SearchBean();
		// *********SF1查询条件设置*************
		setParameter(sfb, dto);
		return SF1Util.getYouTravelFromSF1(sfb, dto.getCurrPageNo(), dto.getPageSize());
	}

	/**
	 * SF1查询条件设置
	 * 
	 * @param sfb
	 * @param dto
	 * @return
	 */
	private void setParameter(SF1SearchBean sfb, YouSearchDto dto) {
		// 关键字查询
		if (StringUtils.isNotBlank(dto.getKeyWords())) {
			sfb.setKeyWord(dto.getKeyWords());
		}

		// 城市设置
		if (!"-1".equals(dto.getSelectedCityId()) && !"0".equals(dto.getSelectedCityId())) {
			sfb.addCondition("FromCity", "=", Constants.XML_CITY.get(dto.getSelectedCityId()));
			// } else {
			// sfb.addCondition("FromCity", "=", "上海");
		}

		// 排序设置
		if (!("").equals(dto.getClikcId()) && dto.getClikcId() != null) {
			sfb.addSort("Spread", "DESC");
		} else {
			if (!StringUtils.isBlank(dto.getOrder())) {
				if ("salesPrice".equals(dto.getOrder().trim())) {
					sfb.addSort("SalesPrice", dto.getSort());
				}
				if ("totalClick".equals(dto.getOrder().trim())) {
					sfb.addSort("TotalClick", dto.getSort());
				}
			} else {
				sfb.addSort("Spread", "DESC");
				sfb.addSort("ID", "DESC");
			}
		}

		// 旅游类型
		if (Constants.DOMESTIC_TRAVEL.equals(dto.getAjaxType().trim()) || ("0").equals(dto.getAjaxType().trim())) {
			sfb.addCondition("YouType", "=", "0");
		} else if (Constants.ABROAD_TRAVEL.equals(dto.getAjaxType().trim()) || ("1").equals(dto.getAjaxType().trim())) {
			sfb.addCondition("YouType", "=", "1");
		} else if (Constants.PERIPHERY_TRAVEL.equals(dto.getAjaxType().trim()) || ("2").equals(dto.getAjaxType().trim())) {
			sfb.addCondition("YouType", "=", "2");
		}

		// 团队游、自助游、其他,0:全部
		if (dto.getType0() != null && !"".equals(dto.getType0().trim()) && !"0".equals(dto.getType0())) {
			sfb.addCondition("YouType2", "=", dto.getType0());
		}

		// 天数
		// if (dto.getDaysMini() != null && !"".equals(dto.getDaysMini().trim()) && !"0".equals(dto.getDaysMini()) && dto.getDaysMax() != null
		// && !"".equals(dto.getDaysMax().trim()) && !"0".equals(dto.getDaysMax())) {
		if (dto.getDaysMini() != null && !"".equals(dto.getDaysMini().trim()) && !"0".equals(dto.getDaysMini())) {
			sfb.addCondition("TimePlanMini", ">=", dto.getDaysMini());
		}
		if (dto.getDaysMax() != null && !"".equals(dto.getDaysMax().trim()) && !"0".equals(dto.getDaysMax())) {
			sfb.addCondition("TimePlanMax", "<=", dto.getDaysMax());
		}

		// 山水、海岛，type1
		if (dto.getType1() != null && !"".equals(dto.getType1().trim()) && !"0".equals(dto.getType1())) {
			sfb.addCondition("YouType3", "=", dto.getType1());
		}

		// price_start && price_end
		if (dto.getPriceStart() != null && !"".equals(dto.getPriceStart()) && Integer.parseInt(dto.getPriceStart()) > 0) {
			sfb.addCondition("SalesPrice", ">=", dto.getPriceStart());
		}
		if (dto.getPriceEnd() != null && !"".equals(dto.getPriceEnd()) && Integer.parseInt(dto.getPriceEnd()) > 0) {
			sfb.addCondition("SalesPrice", "<=", dto.getPriceEnd());
		}

		// 时间设置
		if (dto.getTimeStart() != null && !"".equals(dto.getTimeStart().trim())) {
			String temp1 = dto.getTimeStart().split("/")[2] + "-" + dto.getTimeStart().split("/")[0] + "-" + dto.getTimeStart().split("/")[1];
			sfb.addCondition("StartDate", "<=", temp1);
			sfb.addCondition("EndDate", ">=", temp1);
		}

		// 分页数据
		sfb.setLimit(dto.getPageSize());
		sfb.setOffset((dto.getCurrPageNo() - 1) * dto.getPageSize());

	}

	@Override
	public List<TuanModel> findTravelTuan(String city, String type, int size) {
		return SF1Util.getTuanDataFromSF1(city, type, size);
	}

	@Override
	public List<YouTravel> findIndexHotTravel(String travelType) {
		return this.queryByHql("FROM YouTravel WHERE travelType = ? ORDER BY orderSeq", new Object[] { travelType }, YouTravel.class);
	}
}
