package com.b5m.you.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.b5m.web.core.AbstractBaseModel;

@Entity
@Table(name = "you_hotel_detail")
public class YouHotelDetail extends AbstractBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	// 房间图片url
	@Column(name = "imgurl")
	private String imgurl;

	// 房间类型
	@Column(name = "room_type")
	private String roomType;

	// 返利（‘0’表示不返利，‘1’表示返利）
	@Column(name = "rebate_flag")
	private String rebateFlag;

	// 床型
	@Column(name = "room_model")
	private String roomModel;

	// 早餐
	@Column(name = "breakfast")
	private String breakfast;

	// 价格
	@Column(name = "price")
	private Integer price;

	// 酒店id，对应you_hotel表的主键
	@Column(name = "ht_id")
	private Integer htId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRebateFlag() {
		return rebateFlag;
	}

	public void setRebateFlag(String rebateFlag) {
		this.rebateFlag = rebateFlag;
	}

	public String getRoomModel() {
		return roomModel;
	}

	public void setRoomModel(String roomModel) {
		this.roomModel = roomModel;
	}

	public String getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getHtId() {
		return htId;
	}

	public void setHtId(Integer htId) {
		this.htId = htId;
	}
	
	
}
