//package com.b5m.tao.service.impl;
//
//import static org.junit.Assert.fail;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import javax.annotation.Resource;
//
//import junit.framework.Assert;
//
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.b5m.tao.dao.ITaoGoodsDao;
//import com.b5m.tao.dto.TaoSearchDto;
//import com.b5m.tao.model.TaoGoods;
//import com.b5m.tao.service.ITaoGoodsService;
//import com.b5m.web.core.B5MPageList;
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:com/b5m/tao/service/impl/applicationContext*.xml")
//public class TestTaoGoodsServiceImpl extends AbstractJUnit4SpringContextTests {
//
//	private final Logger logger = Logger.getLogger(this.getClass());
//	
//	public TestTaoGoodsServiceImpl(){
//		org.apache.log4j.PropertyConfigurator.configure(this.getClass().getResource("/com/b5m/tao/service/impl/log4j.properties"));
//	}
//	
//	@Resource
//	private ITaoGoodsService taoGoodsService;
//
//	@Resource
//	private ITaoGoodsDao taoGoodsDao;
//	
//	@Resource
//	private ComboPooledDataSource dataSource;
//
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindTaoGoodByRanking() {
//		List<TaoGoods> list = taoGoodsDao.findTaoGoodByRanking(5);
//		Assert.assertEquals(5, list.size());
//
//	}
//
//	/**
//	 * 压力测试点击量功能
//	 * @throws InterruptedException 
//	 * @throws ExecutionException 
//	 */
//	@Test
//	public void testLoading4ClickCount() throws InterruptedException, ExecutionException{
//		ThreadPoolExecutor tp = new ThreadPoolExecutor(100, 100, 1, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(100));
//		List<Callable<Void>> tasks = new ArrayList<Callable<Void>>();
//		
//		final int threadNumber = 10;
//		final int goodsId = 28307;
//		logger.info("start testLoading4ClickCount");
//		int expectedTotal = taoGoodsService.findTotalClick(goodsId) + threadNumber;
//		int expectedToday = taoGoodsService.findTodayClick(goodsId) + threadNumber;
//		logger.info(new StringBuilder("expectedTotal:").append(expectedTotal)
//					.append(", expectedToday:").append(expectedToday).toString());
//		final AtomicInteger count = new AtomicInteger();
//		for(int i = 0;i < threadNumber;i++){
//			tasks.add(new Callable<Void>(){
//
//				@Override
//				public Void call() throws Exception {
//					taoGoodsService.totalClick(goodsId);
//					logger.info(new StringBuilder("Executing #").append(count.addAndGet(1)).toString());
//					return null;
//				}
//				
//			});
//		}
//		List<Future<Void>> results = tp.invokeAll(tasks);
//		for(Future<Void> result : results){
//			result.get();
//		}
//		int resultToday = taoGoodsService.findTodayClick(goodsId);
//		int resultTotal = taoGoodsService.findTotalClick(goodsId);
//		logger.info(new StringBuilder("expectedTotal:").append(resultTotal)
//				.append(", expectedToday:").append(resultToday).toString());
//		
//		Assert.assertEquals(expectedToday, resultToday);
//		Assert.assertEquals(expectedTotal, resultTotal);
//	}
//	
//	/**
//	 * 超低折扣商品显示测试
//	 */
//	@Test
//	public void testFindTaoGoodByDiscount(){
//		TaoSearchDto ttDto = new TaoSearchDto();
//		ttDto.setPageType("discount");
//		ttDto.setAjaxType("taoDiscount");
//		ttDto.setCurrPageNo(1);
//		ttDto.setCurrPageNo(1);
//		B5MPageList<TaoGoods> list = taoGoodsService.findTaoGoodByDiscount(ttDto);
//		for(TaoGoods tao : list.getAll()){
//			logger.info(new StringBuilder("findTaoGoodByDiscount totalPage:").append(list.getTotalPages())
//					.append(", taoName:").append(tao.getName()).toString());
//		}
//	}
//
//	/**
//	 * 超级降价商品显示测试
//	 */
//	@Test
//	public void testFindTaoGoodByCutprice(){
//		TaoSearchDto ttDto = new TaoSearchDto();
//		ttDto.setPageType("cutprice");
//		ttDto.setAjaxType("taoCutprice");
//		ttDto.setCurrPageNo(1);
//		ttDto.setCurrPageNo(1);
//		B5MPageList<TaoGoods> list = taoGoodsService.findTaoGoodByDiscount(ttDto);
//		for(TaoGoods tao : list.getAll()){
//			logger.info(new StringBuilder("findTaoGoodByDiscount totalPage:").append(list.getTotalPages())
//					.append(", taoName:").append(tao.getName()).toString());
//		}
//	}
//	
//	/**
//	 * 限时特价首页商品显示测试
//	 */
//	@Test
//	public void testGetTaoGoodsIndex(){
//		TaoSearchDto ttDto = new TaoSearchDto();
//		ttDto.setPageType("specialOffer");
//		ttDto.setAjaxType("taoIndex");
//		ttDto.setCurrPageNo(1);
//		ttDto.setCurrPageNo(1);
//		B5MPageList<TaoGoods> list = taoGoodsService.getTaoGoodsIndex(ttDto);
//		for(TaoGoods tao : list.getAll()){
//			logger.info(new StringBuilder("findTaoGoodByDiscount totalPage:").append(list.getTotalPages())
//					.append(", taoName:").append(tao.getName()).toString());
//		}
//	}
//	
//}
