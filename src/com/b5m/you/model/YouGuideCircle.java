package com.b5m.you.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.b5m.web.core.AbstractBaseModel;

/**
 * Title:YouGuideCircle.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-7-29
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Jia Liu
 * 
 * @version 1.0
 */
@Entity
@Table(name = "you_guide_circle")
public class YouGuideCircle extends AbstractBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	// 商圈名称
	@Column(name = "name")
	private String name;

	// 商圈介绍
	@Column(name = "introduction")
	private String introduction;

	// 旅游攻略外键
	@Column(name = "guide_id")
	private Integer guideId;

	@Transient
	private List<YouHotel> hotelList = new ArrayList<YouHotel>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getGuideId() {
		return guideId;
	}

	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}

	public List<YouHotel> getHotelList() {
		return hotelList;
	}

	public void setHotelList(List<YouHotel> hotelList) {
		this.hotelList = hotelList;
	}

}
