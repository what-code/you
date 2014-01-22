package com.b5m.you.testApp;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.b5m.you.common.util.HttpRequest;

public class TestApp {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	public static void main(String[] args) {
		final String url = "http://tao.stage.bang5mai.com/taoPage/discount/taoDiscount";
		String data = "{uid:2903f99e5cf7460b98a22ab572fbcdc5,pageNo:1,pageSize:10,sort=record_crt_time,sortType=desc}";
		final String data1 = "{\"sf1Category\":\"手机数码>手机通讯>手机\",\"size\":8}";
		HttpRequest.getRequest(url, new HashMap());
		/*
		 * String result = HttpRequest.postRequest(url, data); JSONObject jsonObject = JSONObject.fromObject(result); System.out.println("--0->" +
		 * jsonObject);
		 */
		final AtomicInteger counter = new AtomicInteger(0);
		final Map map = new HashMap();
		int len = 2000;
		ExecutorService pools = new ThreadPoolExecutor(2000, 2000 * 2, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(100000),
				new ThreadPoolExecutor.CallerRunsPolicy());

		for (int i = 0; i < len; i++) {
			pools.execute(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 500; j++) {
						String result = HttpRequest.postRequest(url, data1);
						// System.out.println(result);
						counter.incrementAndGet();
					}
				}
			});
		}

		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(2000);
						System.out.println("http request--->" + counter.get());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
