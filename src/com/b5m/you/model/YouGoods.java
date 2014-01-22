package com.b5m.you.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.b5m.web.core.AbstractBaseModel;

@Entity
@Table(name = "you_goods")
public class YouGoods extends AbstractBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4957366240023700306L;

	@Id
	@GeneratedValue
	private Integer id;
	// 商品来源url(cps转码以后的判断)
	@Column(name = "source_url")
	private String sourceUrl;

	// 商品来源编号
	@Column(name = "source_item")
	private String sourceItem;

	// 商品名称
	@Column(name = "name")
	private String name;

	// 图片集合，以,分割
	@Column(name = "b5m_img_urls")
	private String b5mImgUrls;

	// 商品图片url
	@Column(name = "b5m_img")
	private String imgurl;

	// 来源图片集合，以,分割
	@Column(name = "source_img_urls")
	private String sourceImgUrls;

	// 出发地
	@Column(name = "from_city")
	private String fromCity;

	// 目的地
	@Column(name = "to_city")
	private String toCity;

	// 时间安排
	@Column(name = "time_plan")
	private String timePlan;

	// 发团日期
	@Column(name = "group_date")
	private String groupDate;

	// 住行描述
	@Column(name = "hotel_transform")
	private String hotelTransform;

	// 推荐理由
	@Column(name = "recommendation")
	private String recommendation;

	// 商品原价
	@Column(name = "source_price")
	private Float sourcePrice;

	// 商品现价
	@Column(name = "sales_price")
	private Integer salesPrice;

	// cps转化标识（为空表示未转化，不为空表示已转化）
	@Column(name = "shop_click_url")
	private String shopClickUrl;

	// 价格折扣，用现价除以原价
	@Column(name = "discount")
	private Float discount;

	// 初始抢购数
	@Column(name = "init_click")
	private Integer initClick;

	// 总抢购数
	@Column(name = "total_click")
	private Integer totalClick;

	// 推广模式，'0'表示最推荐，'1'表示最人气，'2'表示最超值，'3'表示明日10点抢
	@Column(name = "spread")
	private String spread;

	// 来源地
	@Column(name = "source")
	private String source;

	// 商品来源url
	@Column(name = "click_url")
	private String clickUrl;

	// 类型（‘0‘表示国内游，’1‘表示境外游，’2‘表示周边游）
	@Column(name = "you_type")
	private String youType;

	// 旅游类型（‘1’表示跟团游，‘2’表示自由行，‘3’表示其它）
	@Column(name = "you_type2")
	private String youType2;

	// 旅游类型（多值，以英文逗号分割，1海岛/2山水/3温泉/4城市/5游乐/6古镇/7港澳台/8游轮/9漂流）
	@Column(name = "you_type3")
	private String youType3;

	@Column(name = "time_plan_mini")
	private Integer timePlanMin;

	@Column(name = "time_plan_max")
	private Integer timePlanMax;

	// 团队游的结束及开始时间
	@Column(name = "start_date")
	private String dayStart;

	@Column(name = "end_date")
	private String dayEnd;

	@Column(name = "you_tuan")
	private String youTuan;

	// 程序处理过的推荐理由
	@Transient
	private String listRecommendation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSourceItem() {
		return sourceItem;
	}

	public void setSourceItem(String sourceItem) {
		this.sourceItem = sourceItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getB5mImgUrls() {
		return b5mImgUrls;
	}

	public void setB5mImgUrls(String b5mImgUrls) {
		this.b5mImgUrls = b5mImgUrls;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getSourceImgUrls() {
		return sourceImgUrls;
	}

	public void setSourceImgUrls(String sourceImgUrls) {
		this.sourceImgUrls = sourceImgUrls;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getTimePlan() {
		return timePlan;
	}

	public void setTimePlan(String timePlan) {
		this.timePlan = timePlan;
	}

	public String getGroupDate() {
		return groupDate;
	}

	public void setGroupDate(String groupDate) {
		this.groupDate = groupDate;
	}

	public String getHotelTransform() {
		return hotelTransform;
	}

	public void setHotelTransform(String hotelTransform) {
		this.hotelTransform = hotelTransform;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public Float getSourcePrice() {
		return sourcePrice;
	}

	public void setSourcePrice(Float sourcePrice) {
		this.sourcePrice = sourcePrice;
	}

	public Integer getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Integer salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getShopClickUrl() {
		return shopClickUrl;
	}

	public void setShopClickUrl(String shopClickUrl) {
		this.shopClickUrl = shopClickUrl;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Integer getInitClick() {
		return initClick;
	}

	public void setInitClick(Integer initClick) {
		this.initClick = initClick;
	}

	public Integer getTotalClick() {
		return totalClick;
	}

	public void setTotalClick(Integer totalClick) {
		this.totalClick = totalClick;
	}

	public String getSpread() {
		return spread;
	}

	public void setSpread(String spread) {
		this.spread = spread;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getYouType() {
		return youType;
	}

	public void setYouType(String youType) {
		this.youType = youType;
	}

	public Integer getTimePlanMin() {
		return timePlanMin;
	}

	public Integer getTimePlanMax() {
		return timePlanMax;
	}

	public void setTimePlanMin(Integer timePlanMin) {
		this.timePlanMin = timePlanMin;
	}

	public void setTimePlanMax(Integer timePlanMax) {
		this.timePlanMax = timePlanMax;
	}

	public String getYouType2() {
		return youType2;
	}

	public String getYouType3() {
		return youType3;
	}

	public String getDayStart() {
		return dayStart;
	}

	public String getDayEnd() {
		return dayEnd;
	}

	public String getYouTuan() {
		return youTuan;
	}

	public void setYouType2(String youType2) {
		this.youType2 = youType2;
	}

	public void setYouType3(String youType3) {
		this.youType3 = youType3;
	}

	public void setDayStart(String dayStart) {
		this.dayStart = dayStart;
	}

	public void setDayEnd(String dayEnd) {
		this.dayEnd = dayEnd;
	}

	public void setYouTuan(String youTuan) {
		this.youTuan = youTuan;
	}

	public String getListRecommendation() {
		return listRecommendation;
	}

	public void setListRecommendation(String listRecommendation) {
		this.listRecommendation = listRecommendation;
	}
}
