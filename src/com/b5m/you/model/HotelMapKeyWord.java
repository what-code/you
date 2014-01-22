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
@Table(name = "city_guide")
public class HotelMapKeyWord extends AbstractBaseModel {
	private static final long serialVersionUID = 4957366240003700806L;

	@Id
	@GeneratedValue
	private Integer id;

	// 城市
	@Column(name = "city")
	private String city;

	// 分类1
	@Column(name = "item1")
	private String item1;

	// 分类2
	@Column(name = "item2")
	private String item2;

	// 内容
	@Column(name = "content")
	private String content;

	public Integer getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public String getItem1() {
		return item1;
	}

	public String getItem2() {
		return item2;
	}

	public String getContent() {
		return content;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}

	public void setItem2(String item2) {
		this.item2 = item2;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
