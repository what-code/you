package com.b5m.you.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.b5m.web.core.AbstractBaseModel;

@Entity
@Table(name = "you_source")
public class YouSource extends AbstractBaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2007502459926828381L;

	@Id
	@GeneratedValue
	private Integer id;

	// 商品来源url(cps转码以后的判断)
	@Column(name = "source")
	private String source;

	// 商品来源url(cps转码以后的判断)
	@Column(name = "source_name")
	private String sourceName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

}
