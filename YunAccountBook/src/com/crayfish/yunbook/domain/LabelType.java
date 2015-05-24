package com.crayfish.yunbook.domain;

/**
 * 
 * @author huashuncai
 *
 */
public class LabelType {
	private Integer id;
	private String labelTypeId;
	private String labelTypeName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabelTypeId() {
		return labelTypeId;
	}

	public void setLabelTypeId(String labelTypeId) {
		this.labelTypeId = labelTypeId;
	}

	public String getLabelTypeName() {
		return labelTypeName;
	}

	public void setLabelTypeName(String labelTypeName) {
		this.labelTypeName = labelTypeName;
	}
}
