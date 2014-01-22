package com.b5m.you.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.b5m.web.core.Constants;
import com.b5m.web.core.ContextUtils;
import com.b5m.you.common.ipUtil.IPSeeker;

/**
 * 参数请求类
 * 
 * @author liujia
 * 
 */
public class YouSearchDto implements Serializable {

	private static final long serialVersionUID = 689315504362056640L;

	private IPSeeker aa = IPSeeker.getInstance();

	// added by wiley
	// 当前页码
	private Integer currPageNo = 1;

	// 临时当前页码
	private String currPageNoTemp = "1";

	// 搜索关键字
	private String keyWords = "";

	// 请求ajax字段
	private String ajaxType = "all";

	// 请求页面
	private String pageType = "";

	// 点击商品id
	private String clikcId;

	// 登陆源url
	private String url;

	// 登陆显示url
	private String showUrl;

	// 页大小
	private int pageSize = 10;

	// 城市
	private String city;

	// ip所在地
	private String ipLocate;

	// 用户选择的城市
	private String selectedCity = "全部";

	// 用户选择的城市
	private String selectedCityId = "-1";

	// 业务type
	private String searchType;

	// 帮5游类型
	private String youType;

	// 排序字段
	private String order;

	// 排序类型
	private String sort = "ASC";

	// 分页排序类型
	private String pageSort;

	// YOU 新添加搜索搜索条件开始
	private String type0;

	private String destination;

	// mini天数
	private String daysMini = "0";

	// max天数
	private String daysMax = "0";

	// max天数
	private String days;

	private String type1;

	private String priceStart = "0";

	private String priceEnd = "0";

	private String timeStart;

	private String timeEnd;

	// 酒店新的搜索条件
	private String hotelPrice;

	private String hotelStar;

	private String hotelStarChn;

	private String hotelBeginDate;

	private String hotelEndDate;
	//是否海外酒店 0:国内   1:海外
	private String hwOrNot = "0";

	// YOU 新添加搜索搜索条件结束

	// 页面的模式：list 或 search,默认为list
	private String pageMode = Constants.PAGE_MODE_LIST;

	private String siteMapType;
	// 纬度,经度及半径
	private String latitude;

	private String longitude;

	private int radius;
	// 纬度,经度的范围,格式：low-high
	private String latitudeRange;

	private String longitudeRange;

	// 详情页来源
	private String btmSource;
	
	//详情页的商品ID
	private String gid;
	
	//广告
	//商品作为广告的位置
	private String adLocation;
	//广告商品或团购接口   0：团购接口  1：广告
	private String adOrInterface = "0";
	
	private boolean isKeyWordInMap = false;

	private boolean isSF1 = true;
	
	//0:非推荐  1：推荐
	private String isRecommendtion = "0";

	public boolean isSF1() {
		return isSF1;
	}

	public void setSF1(boolean isSF1) {
		this.isSF1 = isSF1;
	}

	public boolean isKeyWordInMap() {
		return isKeyWordInMap;
	}

	public void setKeyWordInMap(boolean isKeyWordInMap) {
		this.isKeyWordInMap = isKeyWordInMap;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getAjaxType() {
		return ajaxType;
	}

	public void setAjaxType(String ajaxType) {
		this.ajaxType = ajaxType;
	}

	public Integer getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(Integer currPageNo) {
		this.currPageNo = currPageNo;
	}

	public String getClikcId() {
		return clikcId;
	}

	public void setClikcId(String clikcId) {
		this.clikcId = clikcId;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getCurrPageNoTemp() {
		return currPageNoTemp;
	}

	public void setCurrPageNoTemp(String currPageNoTemp) {
		if (currPageNoTemp == null || currPageNoTemp.equals("") || !isNumeric(currPageNoTemp)) {
			currPageNo = 1;
		} else {
			this.currPageNoTemp = currPageNoTemp;
			setCurrPageNo(Integer.parseInt(currPageNoTemp));
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getIpLocate() {
		return ipLocate;
	}

	public void setIpLocate(String ipLocate) {
		if (("").equals(ipLocate) || ipLocate == null) {
			String ip = aa.getAddress(ContextUtils.getInstance().getIpAddr());
			Map<String, String> city = Constants.XML_CITY;
			for (String string : city.values()) {
				if (ip.indexOf(string) >= 0) {
					this.ipLocate = string;
					break;
				}
			}
			if (("").equals(this.ipLocate) || this.ipLocate == null) {
				this.ipLocate = "上海";
			}
			if (("全部").equals(this.ipLocate)) {
				this.ipLocate = "全部";
			}
		} else {
			this.ipLocate = Constants.XML_CITY.get(ipLocate);
		}
	}

	// 1、正则表达式是否是数字
	private boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getYouType() {
		return youType;
	}

	public void setYouType(String youType) {
		this.youType = youType;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		if (("ASC").equals(sort.toUpperCase()))
			this.sort = "DESC";
		else
			this.sort = "ASC";
	}

	public String getPageSort() {
		return pageSort;
	}

	public void setPageSort(String pageSort) {
		if (StringUtils.isBlank(pageSort)) {
			this.pageSort = pageSort;
		} else {
			this.sort = pageSort;
		}
	}

	public String getType0() {
		return type0;
	}

	public String getDestination() {
		return destination;
	}

	public String getType1() {
		return type1;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setType0(String type0) {
		this.type0 = type0;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getHotelPrice() {
		return hotelPrice;
	}

	public String getHotelStar() {
		return hotelStar;
	}

	public void setHotelPrice(String hotelPrice) {
		this.hotelPrice = hotelPrice;
	}

	public void setHotelStar(String hotelStar) {
		this.hotelStar = hotelStar;
	}

	public String getHotelStarChn() {
		return hotelStarChn;
	}

	public void setHotelStarChn(String hotelStarChn) {
		this.hotelStarChn = hotelStarChn;
	}

	public String getPriceStart() {
		return priceStart;
	}

	public String getPriceEnd() {
		return priceEnd;
	}

	public void setPriceStart(String priceStart) {
		this.priceStart = priceStart;
	}

	public void setPriceEnd(String priceEnd) {
		this.priceEnd = priceEnd;
	}

	public String getPageMode() {
		return pageMode;
	}

	public void setPageMode(String pageMode) {
		this.pageMode = pageMode;
	}

	public String getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}

	public String getSelectedCityId() {
		return selectedCityId;
	}

	public void setSelectedCityId(String selectedCityId) {
		this.selectedCityId = selectedCityId;
	}

	public String getSiteMapType() {
		return siteMapType;
	}

	public void setSiteMapType(String siteMapType) {
		this.siteMapType = siteMapType;
	}

	public String getLongitudeRange() {
		return longitudeRange;
	}

	public String getLatitudeRange() {
		return latitudeRange;
	}

	public void setLongitudeRange(String longitudeRange) {
		this.longitudeRange = longitudeRange;
	}

	public void setLatitudeRange(String latitudeRange) {
		this.latitudeRange = latitudeRange;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public int getRadius() {
		return radius;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getBtmSource() {
		return btmSource;
	}

	public void setBtmSource(String btmSource) {
		this.btmSource = btmSource;
	}

	public String getHotelBeginDate() {
		return hotelBeginDate;
	}

	public String getHotelEndDate() {
		return hotelEndDate;
	}

	public void setHotelBeginDate(String hotelBeginDate) {
		this.hotelBeginDate = hotelBeginDate;
	}

	public void setHotelEndDate(String hotelEndDate) {
		this.hotelEndDate = hotelEndDate;
	}


	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}
	
	public String getDaysMini() {
		return daysMini;
	}

	public void setDaysMini(String daysMini) {
		this.daysMini = daysMini;
	}

	public String getDaysMax() {
		return daysMax;
	}

	public void setDaysMax(String daysMax) {
		this.daysMax = daysMax;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getAdLocation() {
		return adLocation;
	}

	public void setAdLocation(String adLocation) {
		this.adLocation = adLocation;
	}

	public String getAdOrInterface() {
		return adOrInterface;
	}

	public void setAdOrInterface(String adOrInterface) {
		this.adOrInterface = adOrInterface;
	}

	public String getHwOrNot() {
		return hwOrNot;
	}

	public void setHwOrNot(String hwOrNot) {
		this.hwOrNot = hwOrNot;
	}

	public String getIsRecommendtion() {
		return isRecommendtion;
	}

	public void setIsRecommendtion(String isRecommendtion) {
		this.isRecommendtion = isRecommendtion;
	}
}
