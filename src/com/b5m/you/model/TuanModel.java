package com.b5m.you.model;

import java.io.Serializable;

/**
 * Title:TuanModel.java
 * 
 * Description:TuanModel.java
 * 
 * Copyright: Copyright (c) 2013-12-23
 * 
 * Company: IZENE Software(Shanghai) Co., Ltd.
 * 
 * @author Shengjie Guo
 * 
 * @version 1.0
 */
public class TuanModel implements Serializable{
	
	private static final long serialVersionUID = 495736624002356306L;
	
	private String id;
	
	private String title;
	
	private String img;
	
	private String url;
	
	private String price;
	
	private String salesPrice;
	
	private String discount;
	
	private String sold;
	
	private String source;
	
	private String sourceUrl;
	
	private String shortTitle;

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getImg() {
		return img;
	}

	public String getUrl() {
		return url;
	}

	public String getPrice() {
		return price;
	}

	public String getSalesPrice() {
		return salesPrice;
	}

	public String getDiscount() {
		return discount;
	}

	public String getSold() {
		return sold;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public void setSold(String sold) {
		this.sold = sold;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
}
