package com.b5m.you.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.b5m.web.core.AbstractBaseModel;

/**
 * Title:YouHotel.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-7-17
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Shengjie Guo
 * 
 * @version 1.0
 */
@Entity
@Table(name = "you_hotel")
public class YouHotel extends AbstractBaseModel {
	private static final long serialVersionUID = 4957366240023700806L;

	@Id
	@GeneratedValue
	private Integer id;

	// 推广url
	@Column(name = "source_url")
	private String sourceUrl;

	// 酒店代码
	@Column(name = "code")
	private String code;

	// 酒店名称
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

	// 最低价格（起）
	@Column(name = "sales_price")
	private Integer salesPrice;

	// 酒店返利（‘0’表示不返利，‘1’表示返利）
	@Column(name = "rebate_flag")
	private String rebateFlag;

	// 酒店品牌
	@Column(name = "brand")
	private String brand;

	// 酒店所在城市
	@Column(name = "city")
	private String city;

	// 行政区
	@Column(name = "district")
	private String district;

	// 周边热门地标信息
	@Column(name = "buildings")
	private String buildings;

	// 评级信息
	@Column(name = "level_info")
	private String levelInfo;

	// 品牌信息
	@Column(name = "brand_info")
	private String brandInfo;

	// 酒店建造时间
	@Column(name = "build_time")
	private String buildTime;

	// 酒店信息上次更新时间
	@Column(name = "last_up_time")
	private String lastUpTime;

	// 经纬度
	@Column(name = "location_data")
	private String locationData;

	// 酒店标签和分类
	@Column(name = "category_lable")
	private String categoryLable;

	// 酒店状态
	@Column(name = "status")
	private String status;

	// 综合设施
	@Column(name = "comp_facility")
	private String compFacility;

	// 活动设施
	@Column(name = "sport_facility")
	private String sportFacility;

	// 服务项目
	@Column(name = "service_project")
	private String serviceProject;

	// 客房设施
	@Column(name = "room_facility")
	private String roomFacility;

	// 总抢购数
	@Column(name = "total_click")
	private Integer totalClick;

	// 置顶优先级
	@Column(name = "spread")
	private Integer spread;

	// 商品原url
	@Column(name = "click_url")
	private String clickUrl;

	// 来源，‘ctrip’表示携程
	@Column(name = "source")
	private String source;

	// 所属商圈（关联商圈表id）
	@Column(name = "c_id")
	private Integer cId;

	// 推荐理由（仅供旅游攻略用，表you_guide...）
	@Column(name = "recommendation")
	private String recommendation;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "new_latitude")
	private String newLatitude;

	@Column(name = "new_longitude")
	private String newLongitude;

	@Column(name = "you_tuan")
	private String youTuan;

	@Column(name = "address")
	private String address;

	// 店用户评分:5分制
	@Column(name = "score")
	private String score;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Integer getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Integer salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getRebateFlag() {
		return rebateFlag;
	}

	public void setRebateFlag(String rebateFlag) {
		this.rebateFlag = rebateFlag;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBuildings() {
		return buildings;
	}

	public void setBuildings(String buildings) {
		this.buildings = buildings;
	}

	public String getLevelInfo() {
		return levelInfo;
	}

	public void setLevelInfo(String levelInfo) {
		this.levelInfo = levelInfo;
	}

	public String getBrandInfo() {
		return brandInfo;
	}

	public void setBrandInfo(String brandInfo) {
		this.brandInfo = brandInfo;
	}

	public String getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}

	public String getLocationData() {
		return locationData;
	}

	public void setLocationData(String locationData) {
		this.locationData = locationData;
	}

	public String getCategoryLable() {
		return categoryLable;
	}

	public void setCategoryLable(String categoryLable) {
		this.categoryLable = categoryLable;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompFacility() {
		return compFacility;
	}

	public void setCompFacility(String compFacility) {
		this.compFacility = compFacility;
	}

	public String getSportFacility() {
		return sportFacility;
	}

	public void setSportFacility(String sportFacility) {
		this.sportFacility = sportFacility;
	}

	public String getServiceProject() {
		return serviceProject;
	}

	public void setServiceProject(String serviceProject) {
		this.serviceProject = serviceProject;
	}

	public String getRoomFacility() {
		return roomFacility;
	}

	public void setRoomFacility(String roomFacility) {
		this.roomFacility = roomFacility;
	}

	public Integer getTotalClick() {
		return totalClick;
	}

	public void setTotalClick(Integer totalClick) {
		this.totalClick = totalClick;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getSpread() {
		return spread;
	}

	public void setSpread(Integer spread) {
		this.spread = spread;
	}

	public String getLastUpTime() {
		return lastUpTime;
	}

	public void setLastUpTime(String lastUpTime) {
		this.lastUpTime = lastUpTime;
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getYouTuan() {
		return youTuan;
	}

	public void setYouTuan(String youTuan) {
		this.youTuan = youTuan;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNewLatitude() {
		return newLatitude;
	}

	public String getNewLongitude() {
		return newLongitude;
	}

	public void setNewLatitude(String newLatitude) {
		this.newLatitude = newLatitude;
	}

	public void setNewLongitude(String newLongitude) {
		this.newLongitude = newLongitude;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}
