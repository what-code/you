package com.b5m.you.test;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom.output.XMLOutputter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.b5m.web.core.ContextUtils;
import com.b5m.you.common.ipUtil.IPSeeker;

public class StartCity {

	public void read() throws Exception {
		// step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器）
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// System.out.println("class name: " + dbf.getClass().getName());

		// step 2:获得具体的dom解析器
		DocumentBuilder db = dbf.newDocumentBuilder();

		// System.out.println("class name: " + db.getClass().getName());

		// step3: 解析一个xml文档，获得Document对象（根结点）
		Document document = db.parse(this.getClass().getResourceAsStream(
				"startCity.xml"));

		NodeList list = document.getElementsByTagName("VacationCity");

		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);
			String city = element.getElementsByTagName("City").item(0)
					.getFirstChild().getNodeValue();
			String startCityName = element
					.getElementsByTagName("StartCityName").item(0)
					.getFirstChild().getNodeValue();
			String isHotStartCity = element
					.getElementsByTagName("IsHotStartCity").item(0)
					.getFirstChild().getNodeValue();
			System.out.println(startCityName + "," + isHotStartCity + "#");
			// map.put(city, startCityName);
		}
	}

	public void read100() throws Exception {
		// step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器）
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// System.out.println("class name: " + dbf.getClass().getName());

		// step 2:获得具体的dom解析器
		DocumentBuilder db = dbf.newDocumentBuilder();

		// System.out.println("class name: " + db.getClass().getName());

		// step3: 解析一个xml文档，获得Document对象（根结点）
		Document document = db.parse(this.getClass().getResourceAsStream(
				"HotHotelCity100.xml"));

		NodeList list = document.getElementsByTagName("CityInfoEntity");

		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);
			String city = element.getElementsByTagName("CityId").item(0)
					.getFirstChild().getNodeValue();
			String startCityName = element.getElementsByTagName("CityName")
					.item(0).getFirstChild().getNodeValue();
			System.out.println(city + "," + startCityName + "#");
			// map.put(city, startCityName);
		}
	}

	public void readTxtAndBuildXmlDoc() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					this.getClass().getResourceAsStream("youCity.txt")));
			// 创建根节点 VacationCityList;
			org.jdom.Element root = new org.jdom.Element("VacationCityList");
			// 根节点添加到文档中；
			org.jdom.Document Doc = new org.jdom.Document(root);
			while (reader.ready()) {
				// 创建节点 VacationCity;
				org.jdom.Element elements = new org.jdom.Element("VacationCity");
				String line = reader.readLine(); // 读取第一行
				String[] citys = line.split("====");
				System.out.println("City:" + citys[0] + "----StartCityName:"
						+ citys[1]);
				// 给 VacationCity 节点添加子节点 City;
				elements.addContent(new org.jdom.Element("City")
						.setText(citys[0]));
				// 给 VacationCity 节点添加子节点 StartCityName;
				elements.addContent(new org.jdom.Element("StartCityName")
						.setText(citys[1]));
				// 给父节点VacationCityList添加VacationCity子节点;
				root.addContent(elements);
			}
			XMLOutputter XMLOut = new XMLOutputter();
			// 输出 NewStartCity.xml 文件；
			XMLOut.output(Doc, new FileOutputStream(
					"/home/jia-liu/NewStartCity.xml"));

			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readXml() throws Exception {
		// step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器）
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		// System.out.println("class name: " + dbf.getClass().getName());

		// step 2:获得具体的dom解析器
		DocumentBuilder db = dbf.newDocumentBuilder();

		// System.out.println("class name: " + db.getClass().getName());

		// step3: 解析一个xml文档，获得Document对象（根结点）
		Document document = db
				.parse("http://price.qoo10.cn/link_data_new/m18/items/409901081.xml");

		NodeList list = document.getElementsByTagName("product");

		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);
			String source = element.getElementsByTagName("source").item(0)
					.getFirstChild().getNodeValue();
			String category = element.getElementsByTagName("category").item(0)
					.getFirstChild().getNodeValue();
			String title = element.getElementsByTagName("title").item(0)
					.getFirstChild().getNodeValue();
			System.out.println(title + "," + source + "," + category + "#");
			// map.put(city, startCityName);
		}
	}

	/**
	 * @param args
	 * @throws
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// new StartCity().readTxtAndBuildXmlDoc();
	}

}
