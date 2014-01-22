package com.b5m.you.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.b5m.you.model.TuanModel;
import com.b5m.you.model.YouGoods;
import com.b5m.you.model.YouGuideNotes;
import com.b5m.you.model.YouHotel;
import com.b5m.web.core.B5MPageList;
import com.b5m.web.core.Constants;
import com.izenesoft.sf1r.bean.dto.SearchDTO;
import com.izenesoft.sf1r.bean.search.GroupLabelBean;
import com.izenesoft.sf1r.bean.search.SF1SearchBean;
import com.izenesoft.sf1r.bean.search.SelectSearchBean;
import com.izenesoft.sf1r.config.InitConfigInfoAgen;
import com.izenesoft.sf1r.search.DocumentsSearch;

/**
 * SF1查询帮助类
 * 
 * @author oscarshan
 * 
 */
public class SF1Util {

	public static Logger logger = Logger.getLogger(SF1Util.class);

	public static final String COLLECTION_NAME_TOURP = "tourp";
	private static Map<String, List<SelectSearchBean>> selectSearceBeans = new HashMap<String, List<SelectSearchBean>>();

	/**
	 * 从sf1的接口中获得you旅游数据查询结果
	 * 
	 * @param s
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static B5MPageList<YouGoods> getYouTravelFromSF1(SF1SearchBean s, int pageNo, int pageSize) {
		try {
			B5MPageList<YouGoods> pageList = new B5MPageList<YouGoods>();
			DocumentsSearch ds = new DocumentsSearch();
			s.setCollectionName("touro");
			s.setSelectLst(getSelectListBean("touro"));
			if (StringUtils.isBlank(s.getKeyWord())) {
				s.setKeyWord("*");
			}
			SearchDTO dto = ds.searchDoc(s);
			logger.info("sf1----总数:" + dto.getTotalCount());
			logger.info("sf1----查询字符串:" + dto.getRequestJson());

			long totalCount = dto.getTotalCount();
			int mod = (int) totalCount % pageSize;
			long totalPages = totalCount / pageSize + (mod > 0 ? 1 : 0);

			if (pageNo < 1)
				pageNo = 1;
			if (pageNo > totalPages)
				pageNo = (int) totalPages;
			long beginRow = (pageNo - 1) * pageSize + 1;
			long endRow = pageNo == totalPages ? totalCount : beginRow + pageSize - 1;

			boolean isFirstPage = pageNo == 1;
			boolean isLastPage = pageNo == totalPages;
			boolean hasNextPage = pageNo < totalPages;
			boolean hasPrevPage = pageNo > 1;

			pageList.setFirstPage(isFirstPage);
			pageList.setLastPage(isLastPage);
			pageList.setHasNextPage(hasNextPage);
			pageList.setHasPrevPage(hasPrevPage);

			pageList.setPageNo(pageNo);
			pageList.setPageSize(pageSize);
			pageList.setTotalCount(totalCount);
			pageList.setTotalPages((int) totalPages);
			pageList.setBeginRow(beginRow);
			pageList.setEndRow(endRow);

			List<YouGoods> ygs = new ArrayList<YouGoods>();
			for (Map<String, String> docs : dto.getResourcesList()) {
				YouGoods yg = new YouGoods();
				try {
					yg.setId(Integer.parseInt(docs.get("id")));
				} catch (Exception e) {
					e.printStackTrace();
				}
				yg.setSourceUrl(docs.get("sourceUrl"));
				try {
					if (docs.get("salesPrice") != null && !"".equals(docs.get("salesPrice").trim())) {
						yg.setSalesPrice((int) Float.parseFloat(docs.get("salesPrice")));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				yg.setSpread(docs.get("spread"));
				yg.setSource(docs.get("source"));
				yg.setName(docs.get("name"));
				yg.setClickUrl(docs.get("clickUrl"));
				yg.setYouTuan(docs.get("youTuan"));
				yg.setImgurl(docs.get("imgurl"));
				yg.setRecommendation(docs.get("Content"));
				String content = docs.get("Content");
				for (String reg : Constants.REG_LIST) {
					content = content.replaceAll(reg, "");
				}
				yg.setListRecommendation(content);
				yg.setGroupDate(docs.get("groupDate"));
				try {
					if (docs.get("totalClick") != null && !"".equals(docs.get("totalClick").trim())) {
						yg.setTotalClick(Integer.parseInt(docs.get("totalClick")));
					} else {

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (docs.get("sourcePrice") != null && !"".equals(docs.get("sourcePrice").trim())) {
						yg.setSourcePrice(Float.parseFloat(docs.get("sourcePrice")));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				ygs.add(yg);
			}
			pageList.addAll(ygs);
			pageList.setCount(ygs.size());
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
			return new B5MPageList<YouGoods>();
		}
	}

	/**
	 * 从sf1接口中获得you项目酒店数据
	 * 
	 * @param s
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static B5MPageList<YouHotel> getYouHotelFromSF1(SF1SearchBean s, int pageNo, int pageSize) {
		try {
			B5MPageList<YouHotel> pageList = new B5MPageList<YouHotel>();
			DocumentsSearch ds = new DocumentsSearch();
			s.setCollectionName("hotel");
			s.setSelectLst(getSelectListBean("hotel"));
			if (StringUtils.isBlank(s.getKeyWord())) {
				s.setKeyWord("*");
			}
			SearchDTO dto = ds.searchDoc(s);
			logger.info("sf1----总数:" + dto.getTotalCount());
			logger.info("查询字符串:" + dto.getRequestJson());

			long totalCount = dto.getTotalCount();
			int mod = (int) totalCount % pageSize;
			long totalPages = totalCount / pageSize + (mod > 0 ? 1 : 0);

			if (pageNo < 1)
				pageNo = 1;
			if (pageNo > totalPages)
				pageNo = (int) totalPages;
			long beginRow = (pageNo - 1) * pageSize + 1;
			long endRow = pageNo == totalPages ? totalCount : beginRow + pageSize - 1;

			boolean isFirstPage = pageNo == 1;
			boolean isLastPage = pageNo == totalPages;
			boolean hasNextPage = pageNo < totalPages;
			boolean hasPrevPage = pageNo > 1;

			pageList.setFirstPage(isFirstPage);
			pageList.setLastPage(isLastPage);
			pageList.setHasNextPage(hasNextPage);
			pageList.setHasPrevPage(hasPrevPage);

			pageList.setPageNo(pageNo);
			pageList.setPageSize(pageSize);
			pageList.setTotalCount(totalCount);
			pageList.setTotalPages((int) totalPages);
			pageList.setBeginRow(beginRow);
			pageList.setEndRow(endRow);

			List<YouHotel> ygs = new ArrayList<YouHotel>();
			for (Map<String, String> docs : dto.getResourcesList()) {
				YouHotel yg = new YouHotel();
				try {
					yg.setId(Integer.parseInt(docs.get("id")));
				} catch (Exception e) {
					e.printStackTrace();
				}
				yg.setSourceUrl(docs.get("sourceUrl"));
				try {
					if (docs.get("salesPrice") != null && !"".equals(docs.get("salesPrice").trim())) {
						yg.setSalesPrice((int) Float.parseFloat(docs.get("salesPrice")));
					} else {
						yg.setSalesPrice(0);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				String newLat = docs.get("latitudeNew");
				String newLng = docs.get("longitudeNew");
				String brandInfo = docs.get("brandInfo");
				if (StringUtils.isNotBlank(brandInfo)) {
					brandInfo = getChnSub(brandInfo, 236) + "...";
				}
				yg.setBrandInfo(brandInfo);
				if (newLat != null && !"".equals(newLat.trim()) && newLng != null && !"".equals(newLng.trim())) {
					yg.setLatitude(newLat);
					yg.setLongitude(newLng);
				} else {
					String location = docs.get("location");
					if (location != null && !"".equals(location.trim())) {
						String[] ll = location.split(",");
						if (ll.length != 2) {
							yg.setLatitude(docs.get("latitude"));
							yg.setLongitude(docs.get("longitude"));
						} else {
							if (ll[0].startsWith("0.0")) {
								yg.setLatitude(docs.get("latitude"));
							} else {
								yg.setLatitude(ll[0]);
							}
							if (ll[1].startsWith("0.0")) {
								yg.setLongitude(docs.get("longitude"));
							} else {
								yg.setLongitude(ll[1]);
							}
						}
					} else {
						yg.setLatitude(docs.get("latitude"));
						yg.setLongitude(docs.get("longitude"));
					}
				}
				String address = docs.get("address");
				if (address == null || "".equals(address.trim())) {
					yg.setAddress("null");
				} else {
					yg.setAddress(docs.get("address"));
				}
				yg.setSource(docs.get("source"));
				yg.setName(docs.get("name"));
				yg.setYouTuan(docs.get("youTuan"));
				yg.setClickUrl(docs.get("clickUrl"));
				yg.setImgurl(docs.get("imgurl"));
				yg.setLevelInfo(docs.get("levelStar"));
				// yg.setScore(docs.get("score"));
				yg.setDistrict(docs.get("district"));
				try {
					if (docs.get("totalClick") != null && !"".equals(docs.get("totalClick").trim())) {
						yg.setTotalClick(Integer.parseInt(docs.get("totalClick")));
					} else {

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				ygs.add(yg);
			}
			pageList.addAll(ygs);
			pageList.setCount(ygs.size());
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
			return new B5MPageList<YouHotel>();
		}
	}

	/**
	 * sf1的旅游攻略查询接口
	 * 
	 * @param s
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static B5MPageList<YouGuideNotes> getYouGuideFromSF1(SF1SearchBean s, int pageNo, int pageSize) {
		try {
			B5MPageList<YouGuideNotes> pageList = new B5MPageList<YouGuideNotes>();
			DocumentsSearch ds = new DocumentsSearch();
			s.setCollectionName("tourguide");
			s.setSelectLst(getSelectListBean("tourguide"));
			if (StringUtils.isBlank(s.getKeyWord())) {
				s.setKeyWord("*");
			}
			List<String> list = new ArrayList<String>();
			list.add("Name");
			s.setTargetLst(list);
			SearchDTO dto = ds.searchDoc(s);
			logger.info("sf1----总数:" + dto.getTotalCount());
			logger.info("查询字符串:" + dto.getRequestJson());

			long totalCount = dto.getTotalCount();
			int mod = (int) totalCount % pageSize;
			long totalPages = totalCount / pageSize + (mod > 0 ? 1 : 0);

			if (pageNo < 1)
				pageNo = 1;
			if (pageNo > totalPages)
				pageNo = (int) totalPages;
			long beginRow = (pageNo - 1) * pageSize + 1;
			long endRow = pageNo == totalPages ? totalCount : beginRow + pageSize - 1;

			boolean isFirstPage = pageNo == 1;
			boolean isLastPage = pageNo == totalPages;
			boolean hasNextPage = pageNo < totalPages;
			boolean hasPrevPage = pageNo > 1;

			pageList.setFirstPage(isFirstPage);
			pageList.setLastPage(isLastPage);
			pageList.setHasNextPage(hasNextPage);
			pageList.setHasPrevPage(hasPrevPage);

			pageList.setPageNo(pageNo);
			pageList.setPageSize(pageSize);
			pageList.setTotalCount(totalCount);
			pageList.setTotalPages((int) totalPages);
			pageList.setBeginRow(beginRow);
			pageList.setEndRow(endRow);

			List<YouGuideNotes> ygs = new ArrayList<YouGuideNotes>();
			for (Map<String, String> docs : dto.getResourcesList()) {
				YouGuideNotes yg = new YouGuideNotes();
				try {
					yg.setId(Integer.parseInt(docs.get("id")));
				} catch (Exception e) {
					e.printStackTrace();
				}
				yg.setSourceUrl(docs.get("sourceUrl"));
				yg.setB5mImg(docs.get("b5mImg"));
				yg.setAuthor(docs.get("author"));
				String publishTimeSF1 = docs.get("publishTime");
				if (publishTimeSF1 != null && !"".equals(publishTimeSF1.trim())) {

					yg.setPublishTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyyMMdd'T'HHmmss")
							.parse(publishTimeSF1)));
				} else {
					yg.setPublishTime("");
				}
				yg.setName(docs.get("name"));
				String mini = docs.get("contentMini");
				if (StringUtils.isNotBlank(mini)) {
					mini = getChnSub(mini, 236) + "...";
				}
				yg.setContentMini(mini);
				try {
					if (docs.get("totalClick") != null && !"".equals(docs.get("totalClick").trim())) {
						yg.setTotalClick(Integer.parseInt(docs.get("totalClick")));
					} else {

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				ygs.add(yg);
			}
			pageList.addAll(ygs);
			pageList.setCount(ygs.size());
			return pageList;
		} catch (Exception e) {
			e.printStackTrace();
			return new B5MPageList<YouGuideNotes>();
		}
	}

	/**
	 * 获取团购的数据
	 * 
	 * @param size
	 * @return
	 */
	public static List<TuanModel> getTuanDataFromSF1(String city, String type, int size) {
		String key = "YOU_TUAN_DATA_" + city + "_" + type + "_" + size;
		Object obj = XMemCachedUtil.getInstance().getCache(key);
		if (obj != null) {
			return (List<TuanModel>) obj;
		}
		List<TuanModel> list = new ArrayList<TuanModel>();
		TuanModel tm = new TuanModel();
		SF1SearchBean ssb = new SF1SearchBean();
		DocumentsSearch ds = new DocumentsSearch();
		ssb.setCollectionName("tuana");
		ssb.setKeyWord("*");
		ssb.setSelectLst(getSelectListBean("tuana"));
		GroupLabelBean glb = new GroupLabelBean();
		glb.setName("Category");

		// 0:旅游 ,1：酒店
		if ("1".equals(type)) {
			glb.setValues(new String[] { "旅游酒店", "酒店" });
		} else {
			glb.setValues(new String[] { "旅游酒店", "旅游" });
		}
		if (!"".equals(city)) {
			ssb.addCondition("City", "=", city);
		}else{
			ssb.setKeyWord("旅游酒店");
		}
		List glbList = new ArrayList();
		glbList.add(glb);
		// 分类
		ssb.setGroupLst(glbList);

		// 按销量降序
		ssb.addSort("Sales", "desc");
		ssb.setLimit(size);
		ssb.setOffset(0);
		SearchDTO dto = ds.searchDoc(ssb);
		logger.info("sf1----总数:" + dto.getTotalCount());
		logger.info("查询字符串:" + dto.getRequestJson());
		for (Map<String, String> docs : dto.getResourcesList()) {
			tm = new TuanModel();
			String temp = docs.get("title");
			String docId = docs.get("DocId");
			String url = "http://tuan.b5m.com/view_" + docId + ".html";
			if (temp.length() >= 27) {
				temp = temp.substring(0, 27) + "...";
			}
			tm.setTitle(temp);
			tm.setSold(docs.get("sales"));
			tm.setUrl(url);
			tm.setImg(docs.get("originalPicture"));
			tm.setPrice(docs.get("priceOrign"));
			tm.setSalesPrice(docs.get("price"));
			tm.setSource(docs.get("source"));
			/* tm.setSourceUrl(docs.get("sourceUrl")); */
			tm.setSourceUrl(url);
			tm.setDiscount(docs.get("discount"));
			list.add(tm);
		}
		if(list.size() == 0){
			return getTuanDataFromSF1("",type,size);
		}
		XMemCachedUtil.getInstance().setCache(key, list, XMemCachedUtil.YOU_CACHE_TIME_HOUR);
		return list;
	}

	/**
	 * 获得select子项:返回字段和SCD文件中字段对应项
	 * 
	 * @param collectionName
	 * @return
	 */
	private static List<SelectSearchBean> getSelectListBean(String collectionName) {
		if (selectSearceBeans.get(collectionName) != null) {
			return selectSearceBeans.get(collectionName);
		} else {
			List<SelectSearchBean> selectLst = new ArrayList<SelectSearchBean>();
			// logger.info("-------------------" + InitConfigInfoAgen.mapSF1ConfigInfo.get(collectionName));
			// logger.info("-------------------" + InitConfigInfoAgen.mapSF1ConfigInfo.get(collectionName).collectionViewAndSCDField);
			for (String[] agrs : InitConfigInfoAgen.mapSF1ConfigInfo.get(collectionName).collectionViewAndSCDField) {

				SelectSearchBean searchBean = new SelectSearchBean();
				searchBean.setAppName(agrs[0]);
				searchBean.setScdName(agrs[1]);
				selectLst.add(searchBean);
			}
			selectSearceBeans.put(collectionName, selectLst);
			return selectLst;
		}
	}

	/**
	 * 获取中文的子符串
	 * 
	 * @param str
	 * @param size
	 * @return
	 */
	private static String getChnSub(String str, int size) {
		int len = 0;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int temp = (int) str.charAt(i);
			if (temp < 255 && temp > 0) {
				len++;
			} else {
				len = len + 2;
			}
			if (len <= size) {
				buffer.append(str.charAt(i));
			} else {
				break;
			}
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		// System.out.println(getChnSub("中国A阿斯速度dasddasab",11));
	}
}
