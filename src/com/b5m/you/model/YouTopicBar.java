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
@Table(name = "you_topic_bar")
public class YouTopicBar extends AbstractBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2814446719330611783L;

	@Id
	@GeneratedValue
	private Integer id;

	// 条幅标题
	@Column(name = "title")
	private String title;

	// 标题字体颜色
	@Column(name = "fontcolor")
	private String fontcolor;

	// 条幅背景图片
	@Column(name = "img_url")
	private String imgUrl;

	// 专题表tao_topic_main外键
	@Column(name = "topic_id")
	private Integer topicId;

	// 活动页数据(非持久化属性)
	@Transient
	private List<YouHotel> topics = new ArrayList<YouHotel>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title.trim();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFontcolor() {
		return fontcolor;
	}

	public void setFontcolor(String fontcolor) {
		this.fontcolor = fontcolor;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public List<YouHotel> getTopics() {
		return topics;
	}

	public void setTopics(List<YouHotel> topics) {
		this.topics = topics;
	}
}
