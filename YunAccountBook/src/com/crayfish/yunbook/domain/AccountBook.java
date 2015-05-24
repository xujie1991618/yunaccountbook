package com.crayfish.yunbook.domain;

import java.util.Date;

/**
 * 
 * @author huashuncai
 * 
 */
public class AccountBook {
	/**
	 * 表示收入
	 */
	public static final String TYPE_INCOME = "1";
	
	/**
	 * 表示支出
	 */
	public static final String TYPE_EXPEND = "2";
	
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 收入、支出
	 */
	private String type;

	/**
	 * 金额
	 */
	private Float money;

	/**
	 * 创建日期
	 */
	private Date createDate;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 标签类型
	 */
	private String labelType;

	/**
	 * 默认构造
	 */
	public AccountBook() {
		// TODO Auto-generated constructor stub
	}

	public AccountBook(String type, Float money, Date createDate,
			String remark, String labelType) {
		this.type = type;
		this.money = money;
		this.createDate = createDate;
		this.remark = remark;
		this.labelType = labelType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLabelType() {
		return labelType;
	}

	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}
}
