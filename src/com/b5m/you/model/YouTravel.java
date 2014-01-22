package com.b5m.you.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.b5m.web.core.AbstractBaseModel;

/**
 * Title:YouTravel.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-12-27
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Jia Liu
 * 
 * @version 1.0
 */
@Entity
@Table(name = "you_travel")
public class YouTravel extends AbstractBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7871443778419759375L;

	@Id
	@GeneratedValue
	private Integer id;

	// 城市名称
	@Column(name = "city_name")
	private String cityName;

	// 旅游类型
	@Column(name = "travel_type")
	private String travelType;

	// 城市url
	@Column(name = "city_url")
	private String cityUrl;

	// 图片地址
	@Column(name = "image_url")
	private String imageUrl;

	// 排序
	@Column(name = "order_seq")
	private Integer orderSeq;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public String getCityUrl() {
		return cityUrl;
	}

	public void setCityUrl(String cityUrl) {
		this.cityUrl = cityUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(Integer orderSeq) {
		this.orderSeq = orderSeq;
	}

}
