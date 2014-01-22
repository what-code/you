package com.b5m.you.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.b5m.web.core.AbstractBaseModel;

/**
 * Title:DestinationCitys.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-9-16
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Shengjie Guo
 * 
 * @version 1.0
 */
@Entity
@Table(name = "destination_citys")
public class DestinationCitys extends AbstractBaseModel{
	private static final long serialVersionUID = 4957366240023700316L;
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "city_id")
	private String cityId;
	
	@Column(name = "city_name")
	private String cityName;
	
	@Column(name = "parent_id")
	private Integer parentId;
	
	@Column(name = "level")
	private Integer level;

	public Integer getId() {
		return id;
	}

	public String getCityName() {
		return cityName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
