package com.b5m.you.dao;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.b5m.web.core.B5MPageList;
import com.b5m.you.common.util.HttpRequest;

import java.util.*;
import java.util.concurrent.*;

import org.apache.commons.codec.digest.Md5Crypt;

/**
 * Title:TestHttp.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-5-14
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Shengjie Guo
 * 
 * @version 1.0
 */
public class TestHttp {
	private String str = "莘庄#外环路#莲花路#锦江乐园#上海南站#漕宝路#上海体育馆#徐家汇#衡山路#常熟路#陕西南路#黄陂南路#人民广场#新闸路#汉中路#上海火车站#中山北路#延长路#上海马戏城#汶水路#彭浦新村#共康路#通河新村#呼兰路#共富新村#宝安公路#友谊西路#富锦路";
	private String[] arrs = str.split("#");
	public String getMapData(String key){
		String temp1 = "上海";
		String temp2 = "";
		String result = "";
		try {
			temp1 = URLEncoder.encode(temp1,"utf-8");
			temp2 = URLEncoder.encode(key,"utf-8");
			//synchronized(this){
				Date t = new Date();
				String url = "http://api.map.baidu.com/place/v2/search?&query=" + temp2 + "&region=" + temp1 + "&output=json&ak=60b9d75a06c0d53bf0ec47bf5b3bd773";
				result = HttpRequest.getRequest(url, null);
				JSONObject json = new JSONObject(result);
				JSONArray ja = new JSONArray(json.getString("results"));
				String name = ja.getJSONObject(0).get("name").toString();
				String lat = ja.getJSONObject(0).getJSONObject("location").get("lat").toString();
				String lng = ja.getJSONObject(0).getJSONObject("location").get("lng").toString();
				String address = ja.getJSONObject(0).get("address").toString();
				String status = json.get("status").toString();
				result = t.getTime() + "#" + new Date().getTime() + "#" + (new Date().getTime() - t.getTime()) + "#" + name + "#" + lat + "#" + lng + "#" + address + "#" + status;
				//System.out.println("===>" + t.getTime() + "========>" + new Date().getTime() + "-----key--->" + key + "---" + result + "--cost-->" + (new Date().getTime() - t.getTime()));
			//}
		} catch (Exception e) {
			e.printStackTrace();
			result = "####-1";
		}
		return result;
	}
	
	//M00---------------------------------------------------------------------
	public void MutilThreads(){
		Date t = new Date();
		for(int i = 0;i < arrs.length;i++){
			Date t1 = new Date();
			getMapData(arrs[i]);
			//System.out.println("arrs-->" + i + "--cost--" + (new Date().getTime() - t1.getTime()));
		}
		System.out.println("all cost-->" + (new Date().getTime() - t.getTime()));
	}
	
	//M01---Threads------------------------------------------------------------------
	public void MutilThreads01(){
		Thread[] t0 = new Thread[arrs.length];
		Date d = new Date();
		for(int i = 0;i < arrs.length;i++){
			final String temp = arrs[i];
			Thread t = new Thread(new Runnable(){
				public void run(){
					getMapData(temp);
				}
			});
			t0[i] = t;
			t.start();
		}
		for(int i = 0;i < arrs.length;i++){
			try {
				t0[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("all01 cost-->" + (new Date().getTime() - d.getTime()));
	}
	
	//M02---ThreadPool------------------------------------------------------------------
	private List<Future<String>> tasks = new ArrayList<Future<String>>();
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 50 * 3, 1L,
			TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(100),
			new ThreadPoolExecutor.CallerRunsPolicy());
	public void MutilThreads02(){
		Date d = new Date();
		for(String key : arrs){
			Tasks task = new Tasks(key);
			FutureTask<String> task01 = new FutureTask<String>(task);
			tasks.add(task01);
			if (!executor.isShutdown()) {
				executor.submit(task01);
			}
		}
		List<String> list = getResult();
		Collections.sort(list);
		for(String str : list){
			System.out.println("cost-->" + str);
		}
		System.out.println("--->" + (new Date().getTime() - d.getTime()));
	}
	
	class Tasks implements Callable<String> {
		private String key;
		public Tasks(String key){
			this.key = key;
		}
		
		@Override
		public String call() throws Exception {
			B5MPageList listAll = null;
			String result = getMapData(key);
			return result;
		}
	}
	
	public List getResult() {
		List result = Collections.synchronizedList(new ArrayList());
		for (Future<String> task : tasks) {
			try {
				// 如果计算未完成则阻塞
				String r1 = task.get();
				result.add(r1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//M03----CyclicBarrier-----------------------------------------------------------------
	private static List<String> mapList = new ArrayList();
	private Date d9;
	public void MutilThreads03(){
		TestHttp th = new TestHttp();
		MapDataResult mdr = new MapDataResult();
		CyclicBarrier cb = new CyclicBarrier(arrs.length,mdr);
		d9 = new Date();
		for(String key : arrs){
			new Thread(th.new MapData(key,cb)).start();
		}
	}
	
	class MapData implements Runnable{
		private String key;
		private final CyclicBarrier barrier;
		public MapData(String key,CyclicBarrier barrier){
			this.key = key;
			this.barrier = barrier;
		}
		
		@Override
		public void run() {
			/*long duration=(long)(Math.random()*10);
			try {
				TimeUnit.SECONDS.sleep(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			String result = getMapData(key);
			mapList.add(result);
			//System.out.println(mapList.size() + "-------" + Thread.currentThread().getId() + "---" + Thread.currentThread().getName() + "----has finished----" + result);
			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
	
	class MapDataResult implements Runnable{
		@Override
		public void run() {
			for(int i = 0; i < mapList.size();i++){
				System.out.println("--obj-->" + mapList.get(i));
			}
			System.out.println("--all finish--" + mapList.size() + "-----" +new Date().getTime() + "----" + d9.getTime() + "---" + (new Date().getTime() - d9.getTime()));
		}
	}
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	
	
	public static void main(String[] args){
		/*String url = "http://172.16.3.22:8080/taoweb_api/getTradeInfoByUid";
		String data = "{uid:2903f99e5cf7460b98a22ab572fbcdc5,pageNo:1,pageSize:10,sort=record_crt_time,sortType=desc}";
		String result = HttpRequest.postRequest(url, data);
		JSONObject jsonObject = JSONObject.fromObject(result);  
		System.out.println("--0->" + jsonObject);*/
		//new TestHttp().MutilThreads03();
		String str = "中国A阿斯ab";
		for(int i = 0;i < str.length();i++){
			System.out.println((int)str.charAt(i));
		}
		
	}
}
