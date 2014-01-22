package com.b5m.you.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.b5m.web.core.AbstractBaseDao;
import com.b5m.web.core.B5MCondition;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.B5MQuery;
import com.b5m.web.core.Constants;
import com.b5m.web.core.ContextUtils;
import com.b5m.you.common.util.SF1Util;
import com.b5m.you.dao.IYouGoodsDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGoods;
import com.izenesoft.sf1r.bean.search.SF1SearchBean;

@Repository
@Transactional
public class YouGoodsDaoImpl extends AbstractBaseDao<YouGoods> implements IYouGoodsDao {

	/**
	 * 首页所有当天包邮（根据条件）查询商品
	 * 
	 * @param dto
	 *            分页条件dto
	 * @param ttjSearchDto
	 *            相关条件dto
	 * @return 分页集合数据
	 */
	@Override
	public B5MPageList<YouGoods> findYouGoodsByList(YouSearchDto dto, String youType) {
		// ***************sf1查询,从这里改装
		// 组装SF1的查询条件
		SF1SearchBean sfb = new SF1SearchBean();
		B5MQuery bq = new B5MQuery();
		System.out.println("**********************************key words:" + dto.getKeyWords());
		bq.setHqlWhere(" now() BETWEEN b5m_list_time AND b5m_delist_time AND checkstatus = '1'");

		boolean searchFromSF1 = false;
		if (ContextUtils.getInstance().isOpenSF1TravelSearch() && StringUtils.isNotBlank(dto.getKeyWords())) {
			searchFromSF1 = true;
		}

		if (StringUtils.isNotBlank(dto.getKeyWords())) {
			if (searchFromSF1) {
				// *********SF1查询条件设置*************
				sfb.setKeyWord(dto.getKeyWords());
			} else {
				bq.addCondition(new B5MCondition("name", "LIKE", "%" + dto.getKeyWords() + "%"));
			}
		} else {
			if (youType != null && !("").equals(youType)) {
				bq.addCondition(new B5MCondition("youType", "=", youType));
			}
		}

		/*
		 * if (!("").equals(dto.getIpLocate()) && null != dto.getIpLocate() && !("全部").equals(dto.getIpLocate()) &&
		 * !Constants.INDEX.equals(dto.getAjaxType())) { if(searchFromSF1){ //*********SF1查询条件设置************* sfb.addCondition("FromCity", "=",
		 * dto.getIpLocate()); }else{ bq.addCondition(new B5MCondition("fromCity", "=", dto.getIpLocate())); } } bq.addCondition(new
		 * B5MCondition("fromCity", "=", dto.getIpLocate())); }
		 */

		// 推荐部分
		if (Constants.B5M_YOU_SAME_TYPE.equals(dto.getSearchType())) {
			if (!"-1".equals(dto.getSelectedCityId())) {
				if (searchFromSF1) {
					// *********SF1查询条件设置*************
					sfb.addCondition("FromCity", "=", Constants.XML_CITY.get(dto.getSelectedCityId()));
				} else {
					bq.addCondition(new B5MCondition("fromCity", "=", Constants.XML_CITY.get(dto.getSelectedCityId())));
				}
			} else {
				if (searchFromSF1) {
					// *********SF1查询条件设置*************
					sfb.addCondition("FromCity", "=", "上海");
				} else {
					bq.addCondition(new B5MCondition("fromCity", "=", "上海"));
				}
			}
		} else if (!"-1".equals(dto.getSelectedCityId())) {
			if (searchFromSF1) {
				// *********SF1查询条件设置*************
				sfb.addCondition("FromCity", "=", Constants.XML_CITY.get(dto.getSelectedCityId()));
			} else {
				bq.addCondition(new B5MCondition("fromCity", "=", Constants.XML_CITY.get(dto.getSelectedCityId())));
			}
		}

		if (dto.getPageSize() == Constants.DEFAULT_HOT_COUNT && !("").equals(dto.getClikcId()) && dto.getClikcId() != null) {
			if (searchFromSF1) {
				// *********SF1查询条件设置*************
				// sfb.addCondition("ID", "!=",dto.getClikcId());
				sfb.addSort("SalesPrice", "ASC");
			} else {
				bq.setHqlWhere(bq.getHqlWhere() + " AND id != " + dto.getClikcId());
				bq.setOrderBy(" ORDER BY salesPrice ASC");
			}
		} else {
			if (!StringUtils.isBlank(dto.getOrder())) {
				// 这里该怎么设置还不知道
				bq.setOrderBy(dto.getOrder() + " " + dto.getSort());
				// System.out.println("---------------------------------------order:"+dto.getOrder()+"----"+dto.getSort());
				if (searchFromSF1) {
					// *********SF1查询条件设置*************
					if ("salesPrice".equals(dto.getOrder().trim())) {
						sfb.addSort("SalesPrice", dto.getSort());
					}
					if ("totalClick".equals(dto.getOrder().trim())) {
						sfb.addSort("TotalClick", dto.getSort());
					}
				}
				// salesPrice----DESC
				// totalClick----DESC
			} else {
				if (searchFromSF1) {
					// *********SF1查询条件设置*************
					sfb.addSort("Spread", "DESC");
					sfb.addSort("ID", "DESC");
				} else {
					bq.setOrderBy("spread DESC, id DESC");
				}
			}
		}

		if (Constants.DOMESTIC_TRAVEL.equals(dto.getAjaxType().trim())) {
			if (searchFromSF1) {
				// *********SF1查询条件设置*************
				sfb.addCondition("YouType", "=", "0");
			} else {
				bq.addCondition(new B5MCondition("youType", "=", "0"));
			}
		} else if (Constants.ABROAD_TRAVEL.equals(dto.getAjaxType().trim())) {
			if (searchFromSF1) {
				// *********SF1查询条件设置*************
				sfb.addCondition("YouType", "=", "1");
			} else {
				bq.addCondition(new B5MCondition("youType", "=", "1"));
			}
		} else if (Constants.PERIPHERY_TRAVEL.equals(dto.getAjaxType().trim())) {
			if (searchFromSF1) {
				// *********SF1查询条件设置*************
				sfb.addCondition("YouType", "=", "2");
			} else {
				bq.addCondition(new B5MCondition("youType", "=", "2"));
			}
		}

		// 团队游、自助游、其他,0:全部
		if (dto.getType0() != null && !"".equals(dto.getType0().trim()) && !"0".equals(dto.getType0())) {
			if (searchFromSF1) {
				// *********SF1查询条件设置*************
				sfb.addCondition("YouType2", "=", dto.getType0());
			} else {
				bq.addCondition(new B5MCondition("youType2", "=", dto.getType0()));
			}
		}

		// 天数
		if (dto.getDays() != null && !"".equals(dto.getDays().trim()) && !"0".equals(dto.getDays())) {
			if (searchFromSF1) {
				// *********SF1查询条件设置*************
				sfb.addCondition("TimePlanMini", "<=", dto.getDays());
				sfb.addCondition("TimePlanMax", ">=", dto.getDays());
			} else {
				bq.addCondition(new B5MCondition("timePlanMin", "<=", Integer.parseInt(dto.getDays())));
				bq.addCondition(new B5MCondition("timePlanMax", ">=", Integer.parseInt(dto.getDays())));
			}
		}

		// 山水、海岛，type1
		if (dto.getType1() != null && !"".equals(dto.getType1().trim()) && !"0".equals(dto.getType1())) {
			// 这里怎么也用了like?????????
			if (searchFromSF1) {
				// *********SF1查询条件设置*************
				sfb.addCondition("YouType3", "=", dto.getType1());
			} else {
				bq.addCondition(new B5MCondition("YouType3", "LIKE", "%" + dto.getType1() + "%"));
			}
		}

		// price_start && price_end
		if (dto.getPriceStart() != null && !"".equals(dto.getPriceStart()) && Integer.parseInt(dto.getPriceStart()) > 0) {
			if (searchFromSF1) {
				// *********SF1查询条件设置*************
				sfb.addCondition("SalesPrice", ">=", dto.getPriceStart());
			} else {
				bq.addCondition(new B5MCondition("salesPrice", ">=", Integer.parseInt(dto.getPriceStart())));
			}
		}
		if (dto.getPriceEnd() != null && !"".equals(dto.getPriceEnd()) && Integer.parseInt(dto.getPriceEnd()) > 0) {
			if (searchFromSF1) {
				// *********SF1查询条件设置*************
				sfb.addCondition("SalesPrice", "<=", dto.getPriceEnd());
			} else {
				bq.addCondition(new B5MCondition("salesPrice", "<=", Integer.parseInt(dto.getPriceEnd())));
			}
		}
		// time_start && time_end
		/*
		 * if(dto.getTimeStart()!=null && dto.getTimeEnd() != null && !"".equals(dto.getTimeStart().trim()) && !"".equals(dto.getTimeEnd().trim())){
		 * String temp1 = dto.getTimeStart().split("/")[2] + "-" + dto.getTimeStart().split("/")[0] + "-" + dto.getTimeStart().split("/")[1]; String
		 * temp2 = dto.getTimeEnd().split("/")[2] + "-" + dto.getTimeEnd().split("/")[0] + "-" + dto.getTimeEnd().split("/")[1]; bq.addCondition(new
		 * B5MCondition("dayStart", "<=", temp1)); bq.addCondition(new B5MCondition("dayEnd", ">=", temp2)); }
		 */

		if (dto.getTimeStart() != null && !"".equals(dto.getTimeStart().trim())) {
			String temp1 = dto.getTimeStart().split("/")[2] + "-" + dto.getTimeStart().split("/")[0] + "-" + dto.getTimeStart().split("/")[1];
			if (searchFromSF1) {
				// *********SF1查询条件设置*************
				sfb.addCondition("StartDate", "<=", temp1);
				sfb.addCondition("EndDate", ">=", temp1);
			} else {
				bq.addCondition(new B5MCondition("dayStart", "<=", temp1));
				bq.addCondition(new B5MCondition("dayEnd", ">=", temp1));
			}
		}

		if (searchFromSF1) {
			// *********SF1查询条件设置*************
			sfb.setLimit(dto.getPageSize());
			sfb.setOffset((dto.getCurrPageNo() - 1) * dto.getPageSize());
		} else {
			bq.setPageNo(dto.getCurrPageNo());
			bq.setPageSize(dto.getPageSize());
		}
		// 详情页推荐条数
		if (Constants.B5M_YOU_SAME_TYPE.equals(dto.getSearchType())) {
			bq.setPageSize(Constants.YOU_RECOMMEND_SZIE + 1);
		}

		System.out.println("--you_goods_type---->" + dto.getAjaxType());

		if (searchFromSF1) {
			B5MPageList<YouGoods> list = SF1Util.getYouTravelFromSF1(sfb, dto.getCurrPageNo(), dto.getPageSize());
			return list;
		} else {
			return this.getPageList(bq);
		}

		// SF1Query sf1 = new SF1Query();
		// System.out.println("--you_goods_type---->" + dto.getAjaxType());
		// return this.getPageList(bq);
	}

	/**
	 * 根据查询条件获取 获取taoGoods 集合
	 * 
	 * @param hql
	 *            hql 语句
	 * @param conditions
	 *            查询条件
	 * @return List<YouGoods>
	 * @throws Exception
	 */
	@Override
	public List<YouGoods> findYouGoods(int hotCount) {
		B5MQuery bq = new B5MQuery();
		bq.setHqlWhere(" now() between b5m_list_time and b5m_delist_time");
		bq.addCondition(new B5MCondition("checkstatus", "=", "1"));
		bq.setOrderBy(" ORDER BY b5m_list_time DESC ");
		bq.setPageNo(1);
		bq.setPageSize(hotCount);
		return this.getListByQuery(bq);
	}

	/**
	 * 查询商品明细
	 * 
	 * @return
	 */
	@Override
	public YouGoods findYouGoodsById(YouSearchDto dto) {
		return this.getById(Integer.parseInt(dto.getClikcId()));
	}

	/**
	 * 查询首页按类型查询
	 * 
	 * @param ttjSearchDto
	 *            相关条件dto
	 * @return 置顶集合数据
	 */
	@Override
	public List<YouGoods> findsIndexSpecialTravelByList(YouSearchDto ttDto, String youType) {
		return this.getHqlList("FROM YouGoods WHERE now() BETWEEN b5m_list_time AND b5m_delist_time AND checkstatus = '1' AND youType = '" + youType
				+ "'" + getWhereFromCity(ttDto) + " ORDER BY spread DESC, id DESC", 0, ttDto.getPageSize());
	}

	/**
	 * 点击数
	 * 
	 * @param dto
	 */
	@Override
	public Integer totalClick(YouSearchDto dto) {
		final String sql = "UPDATE you_goods SET total_click= total_click +1 WHERE id =" + dto.getClikcId();

		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery(sql.toString()).executeUpdate();
			}
		});
	}

	@Override
	public List<String> getYouCity(YouSearchDto dto) {
		String nameAndType = " AND youType='" + dto.getYouType() + "' ";
		List<String> list = new ArrayList();
		list.add("全部");
		List<String> temp = this.getHibernateTemplate().find(
				"SELECT fromCity FROM YouGoods WHERE (now() between b5m_list_time AND b5m_delist_time) AND checkstatus='1' " + nameAndType
						+ " GROUP BY fromCity");
		list.addAll(temp);
		return list;
	}

	private String getWhereFromCity(YouSearchDto dto) {
		String fromCity = "";
		// if ((!("").equals(dto.getIpLocate()) && null != dto.getIpLocate() && !("全部").equals(dto.getIpLocate())
		// && !Constants.INDEX.equals(dto.getAjaxType()) && !"".equals(dto.getAjaxType()))) {
		// fromCity = " AND fromCity='" + dto.getIpLocate() + "'";
		// }
		if (!"-1".equals(dto.getSelectedCityId())) {
			fromCity = "AND fromCity = '" + Constants.XML_CITY.get(dto.getSelectedCityId()) + "'";
		}
		return fromCity;
	}

	@Override
	public List getSiteMap(YouSearchDto dto) {
		String temp = "";
		if (dto.getIpLocate() != null && !dto.getIpLocate().equals("")) {
			temp = temp + " and fromCity='" + dto.getIpLocate() + "'";
		}
		if (dto.getSiteMapType() != null && !dto.getSiteMapType().equals("")) {
			temp = temp + " and youType=" + dto.getSiteMapType();
		}
		return this.getHibernateTemplate().find("SELECT id,name,fromCity FROM YouGoods where 1=1 " + temp);
	}

	@Override
	public List<YouGoods> findTopicYouGoods(YouSearchDto dto) {
		return this.getHqlList(
				"FROM YouGoods WHERE now() BETWEEN b5m_list_time AND b5m_delist_time AND checkstatus = '1' AND name like '%" + dto.getKeyWords()
						+ "%' ORDER BY spread DESC, id DESC", 0, 20);
	}
}
