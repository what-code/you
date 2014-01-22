package com.b5m.you.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.b5m.web.core.AbstractBaseModel;

/**
 * Title:YouGuide.java
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
@Table(name = "you_guide")
public class YouGuide extends AbstractBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	// 标题
	@Column(name = "name")
	private String name;

	// 所在城市
	@Column(name = "city")
	private String city;

	// 轮播图片和链接，每个单元之间#分割，单元内用@分割
	@Column(name = "b5m_img_urls")
	private String b5mImgUrls;

	// “看什么”标题，图片和链接，每个单元之间#分割，单元内用@分割
	@Column(name = "look_img_urls")
	private String lookImgUrls;

	// “看什么”内容
	@Column(name = "look_content")
	private String lookContent;

	// “玩什么”标题，图片和链接，每个单元之间#分割，单元内用@分割
	@Column(name = "play_img_urls")
	private String playImgUrls;

	// “玩什么”内容
	@Column(name = "play_content")
	private String playContent;

	// “住什么”标题，图片和链接，每个单元之间#分割，单元内用@分割
	@Column(name = "live_img_urls")
	private String liveImgUrls;

	// “住什么”内容
	@Column(name = "live_content")
	private String liveContent;

	// 审核状态,‘0’表示未审核，‘1’表示已审核，‘2’表示永不显示
	@Column(name = "checkstatus")
	private String checkstatus;

	// 类型（‘0‘表示国内游，’1‘表示境外游，’2‘表示周边游）
	@Column(name = "you_type")
	private String youType;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getB5mImgUrls() {
		return b5mImgUrls;
	}

	public void setB5mImgUrls(String b5mImgUrls) {
		this.b5mImgUrls = b5mImgUrls;
	}

	public String getLookImgUrls() {
		return lookImgUrls;
	}

	public void setLookImgUrls(String lookImgUrls) {
		this.lookImgUrls = lookImgUrls;
	}

	public String getLookContent() {
		return lookContent;
	}

	public void setLookContent(String lookContent) {
		this.lookContent = lookContent;
	}

	public String getPlayImgUrls() {
		return playImgUrls;
	}

	public void setPlayImgUrls(String playImgUrls) {
		this.playImgUrls = playImgUrls;
	}

	public String getPlayContent() {
		return playContent;
	}

	public void setPlayContent(String playContent) {
		this.playContent = playContent;
	}

	public String getLiveImgUrls() {
		return liveImgUrls;
	}

	public void setLiveImgUrls(String liveImgUrls) {
		this.liveImgUrls = liveImgUrls;
	}

	public String getLiveContent() {
		return liveContent;
	}

	public void setLiveContent(String liveContent) {
		this.liveContent = liveContent;
	}

	public String getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}

	public String getYouType() {
		return youType;
	}

	public void setYouType(String youType) {
		this.youType = youType;
	}

}
