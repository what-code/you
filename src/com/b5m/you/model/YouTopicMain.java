package com.b5m.you.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.b5m.web.core.AbstractBaseModel;

/**
 * Title:TaoTopicMain.java
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2013-10-16
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Jia Liu
 * 
 * @version 1.0
 */
@Entity
@Table(name = "you_topic_main")
public class YouTopicMain extends AbstractBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2814446719330611783L;

	@Id
	@GeneratedValue
	private Integer id;

	// 专题名称
	@Column(name = "name")
	private String name;

	// 专题图片
	@Column(name = "img_url")
	private String imgUrl = "../images/bg_body.gif";

	// 背景颜色
	@Column(name = "bgcolor")
	private String bgcolor = "rgb(249,245,220)";

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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		if (StringUtils.isNotBlank(imgUrl)) {
			this.imgUrl = imgUrl;
		}
	}

	public String getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(String bgcolor) {
		if (StringUtils.isNotBlank(bgcolor)) {
			this.bgcolor = bgcolor;
		}
	}

}
