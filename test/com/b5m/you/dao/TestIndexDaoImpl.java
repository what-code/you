package com.b5m.you.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.b5m.you.model.YouGoods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com/b5m/tao/service/impl/applicationContext*.xml")
public class TestIndexDaoImpl extends AbstractJUnit4SpringContextTests {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private HibernateTemplate hibernateTemplate;

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindIndexTop() {
		final String sql = "select * from tao_goods where now()  between b5m_list_time and b5m_delist_time and checkstatus = '1' and (postal=1 or source!='taobao') and categoryid is not null  "
				+ "and stock > 0 and up_status = '1' and spread > '0' order by convert(spread,signed) desc, b5m_list_time desc,id desc limit 12";

		List<YouGoods> list = hibernateTemplate.execute(new HibernateCallback<List<YouGoods>>() {

			@Override
			public List<YouGoods> doInHibernate(Session session) throws HibernateException, SQLException {
				List<YouGoods> taoList = session.createSQLQuery(sql).addEntity(YouGoods.class).list();
				return taoList;
			}
		});
		for (YouGoods taoGoods : list) {
			logger.info("name:" + taoGoods.getName());
		}
	}

	@Test
	public void testFindIndexTomorrowTop() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String now = df.format(cal.getTime());
		final String sql = "select * from tao_goods where '" + now
				+ "' = str_to_date(b5m_list_time,'%Y-%m-%d') and checkstatus = '1' and (postal=1 or source!='taobao') and categoryid is not null  "
				+ "and stock > 0 and up_status = '1' order by convert(spread,signed) desc, b5m_list_time desc,id desc limit 12";

		List<YouGoods> list = hibernateTemplate.execute(new HibernateCallback<List<YouGoods>>() {

			@Override
			public List<YouGoods> doInHibernate(Session session) throws HibernateException, SQLException {
				List<YouGoods> taoList = session.createSQLQuery(sql).addEntity(YouGoods.class).list();
				return taoList;
			}
		});
		for (YouGoods taoGoods : list) {
			logger.info("name:" + taoGoods.getName());
			System.out.println("name:" + taoGoods.getName());
		}
	}
}
