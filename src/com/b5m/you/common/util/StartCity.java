package com.b5m.you.common.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class StartCity {

	public static Logger logger = Logger.getLogger(StartCity.class);

	private static final String CITY_FILE = getAddress();

	private static final String HOT_CITY_FILE = getHotCityAddress();

	// 随机文件访问类
	private File cityFile;

	// 随机文件访问类
	private File hotCityFile;

	// 单一模式实例
	private static StartCity instance = new StartCity();

	/**
	 * 私有构造函数
	 */
	private StartCity() {
		try {
			cityFile = new File(CITY_FILE);
			hotCityFile = new File(HOT_CITY_FILE);
		} catch (Exception e) {
			// System.out.println(IPSeeker.class.getResource("/home/jia-liu/QQWry.Dat").toString());
			System.out.println(CITY_FILE);
			System.out.println(HOT_CITY_FILE);
			System.out.println("城市配置文件未找到。");
			cityFile = null;
			hotCityFile = null;
		}
	}

	/** 得到配置文件地址 */
	private static String getAddress() {
		File f = new File(StartCity.class.getClassLoader().getResource("").getPath());
		f = new File(f.getParent());
		return f.getPath() + "/startCity.xml";
	}

	/** 得到配置文件地址 */
	private static String getHotCityAddress() {
		File f = new File(StartCity.class.getClassLoader().getResource("").getPath());
		f = new File(f.getParent());
		//return f.getPath() + "/HotHotelCity100.xml";
		return f.getPath() + "/hotelCityAll.xml";
	}

	/**
	 * @return 单一实例
	 */
	public static StartCity getInstance() {
		return instance;
	}

	private NodeList getNodeLst(File file, String tagName) {
		try {
			// step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器）
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// step 2:获得具体的dom解析器
			DocumentBuilder db = dbf.newDocumentBuilder();
			// step3: 解析一个xml文档，获得Document对象（根结点）
			Document document = db.parse(file);
			NodeList list = document.getElementsByTagName(tagName);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, String> readCity() {
		Map<String, String> XML_CITY = new HashMap<String, String>();
		try {
			NodeList list = getNodeLst(cityFile, "VacationCity");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				String city = element.getElementsByTagName("City").item(0).getFirstChild().getNodeValue();
				String startCityName = element.getElementsByTagName("StartCityName").item(0).getFirstChild().getNodeValue();
				XML_CITY.put(city, startCityName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return XML_CITY;
	}

	public Map<String, String> readCityKey() {
		Map<String, String> XML_CITY = new HashMap<String, String>();
		try {
			NodeList list = getNodeLst(cityFile, "VacationCity");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				String city = element.getElementsByTagName("City").item(0).getFirstChild().getNodeValue();
				String startCityName = element.getElementsByTagName("StartCityName").item(0).getFirstChild().getNodeValue();
				XML_CITY.put(startCityName, city);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return XML_CITY;
	}

	public Map<String, String> readHotCity() {
		Map<String, String> XML_CITY = new HashMap<String, String>();
		try {
			NodeList list = getNodeLst(hotCityFile, "CityInfoEntity");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				String city = "";
				String startCityName = "";
				Node node0 = element.getElementsByTagName("CityId").item(0).getFirstChild();
				Node node1 = element.getElementsByTagName("CityName").item(0).getFirstChild();
				if(node0 != null && node1 != null){
					city = node0.getNodeValue();
					startCityName = node1.getNodeValue();
					XML_CITY.put(city, startCityName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return XML_CITY;
	}

	public Map<String, String> readHotCityKey() {
		Map<String, String> XML_CITY = new HashMap<String, String>();
		try {
			NodeList list = getNodeLst(hotCityFile, "CityInfoEntity");
			for (int i = 0; i < list.getLength(); i++) {
				Element element = (Element) list.item(i);
				String city = "";
				String startCityName = "";
				Node node0 = element.getElementsByTagName("CityId").item(0).getFirstChild();
				Node node1 = element.getElementsByTagName("CityName").item(0).getFirstChild();
				if(node0 != null && node1 != null){
					city = node0.getNodeValue();
					startCityName = node1.getNodeValue();
					XML_CITY.put(startCityName, city);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return XML_CITY;
	}
}
