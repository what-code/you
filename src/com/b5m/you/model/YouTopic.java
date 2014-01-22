package com.b5m.you.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.b5m.web.core.AbstractBaseModel;

@Entity
@Table(name = "you_topic")
public class YouTopic extends AbstractBaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7368163919207266344L;

	@Id
	@GeneratedValue
	private Integer id;

	// 图片地址
	@Column(name = "img_url")
	private String imgUrl;

	// 关键字1
	@Column(name = "keyword1")
	private String keyword1;

	// 关键字2
	@Column(name = "keyword2")
	private String keyword2;

	// 标题1
	@Column(name = "title1")
	private String title1;

	// 标题2
	@Column(name = "title2")
	private String title2;

	// url地址1
	@Column(name = "url1")
	private String url1;

	// url地址2
	@Column(name = "url2")
	private String url2;

	// 颜色1
	@Column(name = "color1")
	private String color1;

	// 颜色2
	@Column(name = "color2")
	private String color2;

	// 专题类型
	@Column(name = "topic_type")
	private String topicType;

	// 排序
	@Column(name = "sort")
	private Integer sort;

	// 开始时间
	@Column(name = "start_time")
	private Date startTime;

	// 结束时间
	@Column(name = "end_time")
	private Date endTime;

	// 操作时间
	@Column(name = "oper_time")
	private String operTime;

	// 操作人
	@Column(name = "operator")
	private Integer operator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getKeyword1() {
		return keyword1;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}

	public String getKeyword2() {
		return keyword2;
	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getUrl1() {
		return url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getColor1() {
		return color1;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public String getColor2() {
		return color2;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

}
