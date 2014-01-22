package com.b5m.you.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.b5m.web.core.AbstractBaseModel;

/**
 * Title:YouGuideNotes.java
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
@Table(name = "you_guide_notes")
public class YouGuideNotes extends AbstractBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4005343738644986852L;

	@Id
	@GeneratedValue
	private Integer id;

	// 标题
	@Column(name = "name")
	private String name;

	// 游记城市
	@Column(name = "city")
	private String city;

	// 图片
	@Column(name = "b5m_img")
	private String b5mImg;

	// 游记来源链接
	@Column(name = "source_url")
	private String sourceUrl;

	// 链接
	@Column(name = "url")
	private String url;

	// 作者
	@Column(name = "author")
	private String author;

	// 发布内容
	@Column(name = "content")
	private String content;

	// 发布内容列表页使用
	@Column(name = "content_mini")
	private String contentMini;

	// 发布地点
	@Column(name = "publish_place")
	private String publishPlace;

	// 发布时间
	@Column(name = "publish_time")
	private String publishTime;

	// 初始化浏览量
	@Column(name = "init_click")
	private Integer initClick;

	// 总浏览量
	@Column(name = "total_click")
	private Integer totalClick;

	// 旅游攻略外键
	@Column(name = "guide_id")
	private Integer guideId;

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

	public String getB5mImg() {
		return b5mImg;
	}

	public void setB5mImg(String b5mImg) {
		this.b5mImg = b5mImg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishPlace() {
		return publishPlace;
	}

	public void setPublishPlace(String publishPlace) {
		this.publishPlace = publishPlace;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
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

	public Integer getGuideId() {
		return guideId;
	}

	public void setGuideId(Integer guideId) {
		this.guideId = guideId;
	}

	public String getContentMini() {
		return contentMini;
	}

	public void setContentMini(String contentMini) {
		this.contentMini = contentMini;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

}
