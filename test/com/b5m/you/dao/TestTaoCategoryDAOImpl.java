package com.b5m.you.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.b5m.web.core.B5MPageList;
import com.b5m.you.dao.IYouGoodsDao;
import com.b5m.you.dto.YouSearchDto;
import com.b5m.you.model.YouGoods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com/b5m/tao/service/impl/applicationContext*.xml")
public class TestTaoCategoryDAOImpl extends AbstractJUnit4SpringContextTests {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private HibernateTemplate hibernateTemplate;

	@Resource
	private IYouGoodsDao taoGoodsDao;
	
	@Before
	public void Before(){
		org.apache.log4j.PropertyConfigurator.configure(this.getClass()
				.getResource("/com/b5m/tao/service/impl/log4j.properties"));
	}

	@Test
	public void testFindTaoGoodByRanking() {
		final String sql = "SELECT g.* FROM top_goods p left join tao_goods g on p.goods_id = g.id "
				+ "where now() between start_time and end_time and top_type<>'0' and stock > 0 and up_status = '1' and checkstatus='1' order by sort desc limit 5";

		List<YouGoods> list = hibernateTemplate
				.execute(new HibernateCallback<List<YouGoods>>() {

					@Override
					public List<YouGoods> doInHibernate(Session session)
							throws HibernateException, SQLException {
						List<YouGoods> taoList = session.createSQLQuery(sql)
								.addEntity(YouGoods.class).list();
						if (taoList.size() < 5) {
							StringBuilder taoId = new StringBuilder();
							for (int i = 0; i < taoList.size(); i++) {
								YouGoods tao = taoList.get(i);
								if(i == (taoList.size()-1))
									taoId.append(tao.getId());
								else
									taoId.append(tao.getId()).append(",");
							}
							System.out.println(taoId.toString());
							final String sqlExternal = "select * from tao_goods where stock > 0 and up_status <> '0' and now() between b5m_list_time and b5m_delist_time and checkstatus='1' and categoryid is not null and id not in ("+taoId.toString()+") order by (total_click - init_click) desc limit "
									+ (5 - taoList.size());
							List<YouGoods> listExternal = session
									.createSQLQuery(sqlExternal)
									.addEntity(YouGoods.class).list();
							taoList.addAll(listExternal);
						}
						return taoList;
					}
				});

		for (YouGoods taoGoods : list) {
			System.out.println(taoGoods.getName());
		}
		Assert.assertEquals(5, list.size());
	}
	
	/**
	 * 限时特价首页商品显示测试
	 */
	@Test
	public void testGetTaoGoodsIndex(){
		YouSearchDto ttDto = new YouSearchDto();
		ttDto.setPageType("specialOffer");
		ttDto.setAjaxType("taoIndex");
		ttDto.setKeyWords("1");
		ttDto.setCurrPageNo(1);
		ttDto.setCurrPageNo(1);
		B5MPageList<YouGoods> list = taoGoodsDao.findYouGoodsByList(ttDto,"0");
		for(YouGoods tao : list.getAll()){
			logger.info(new StringBuilder("findTaoGoodByDiscount totalPage:").append(list.getTotalPages())
					.append(", taoName:").append(tao.getName()).toString());
			System.out.println(new StringBuilder("findTaoGoodByDiscount totalPage:").append(list.getTotalPages())
					.append(", taoName:").append(tao.getName()).toString());
		}
		for (Integer integer : list.getCategoryList()) {
			System.out.println(integer);
		}
	}
}
