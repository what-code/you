package com.b5m.you.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com/b5m/tao/service/impl/applicationContext*.xml")
public class TestTaoTopicDaoImpl extends AbstractJUnit4SpringContextTests {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Resource
	private HibernateTemplate hibernateTemplate;

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCarouselTopic() {
		
	}
}
