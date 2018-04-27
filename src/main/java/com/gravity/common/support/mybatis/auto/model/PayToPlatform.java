package com.gravity.common.support.mybatis.auto.model;

import java.util.Date;

public class PayToPlatform {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pay_to_platform.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pay_to_platform.order_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private String orderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pay_to_platform.order_id_no
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private String orderIdNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pay_to_platform.employ_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer employId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pay_to_platform.amount
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer amount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column pay_to_platform.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Date createTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pay_to_platform.id
	 * @return  the value of pay_to_platform.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pay_to_platform.id
	 * @param id  the value for pay_to_platform.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pay_to_platform.order_id
	 * @return  the value of pay_to_platform.order_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pay_to_platform.order_id
	 * @param orderId  the value for pay_to_platform.order_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pay_to_platform.order_id_no
	 * @return  the value of pay_to_platform.order_id_no
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public String getOrderIdNo() {
		return orderIdNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pay_to_platform.order_id_no
	 * @param orderIdNo  the value for pay_to_platform.order_id_no
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setOrderIdNo(String orderIdNo) {
		this.orderIdNo = orderIdNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pay_to_platform.employ_id
	 * @return  the value of pay_to_platform.employ_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getEmployId() {
		return employId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pay_to_platform.employ_id
	 * @param employId  the value for pay_to_platform.employ_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setEmployId(Integer employId) {
		this.employId = employId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pay_to_platform.amount
	 * @return  the value of pay_to_platform.amount
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pay_to_platform.amount
	 * @param amount  the value for pay_to_platform.amount
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column pay_to_platform.create_time
	 * @return  the value of pay_to_platform.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column pay_to_platform.create_time
	 * @param createTime  the value for pay_to_platform.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pay_to_platform
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		PayToPlatform other = (PayToPlatform) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(
						other.getOrderId()))
				&& (this.getOrderIdNo() == null ? other.getOrderIdNo() == null : this.getOrderIdNo().equals(
						other.getOrderIdNo()))
				&& (this.getEmployId() == null ? other.getEmployId() == null : this.getEmployId().equals(
						other.getEmployId()))
				&& (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(
						other.getCreateTime()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table pay_to_platform
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
		result = prime * result + ((getOrderIdNo() == null) ? 0 : getOrderIdNo().hashCode());
		result = prime * result + ((getEmployId() == null) ? 0 : getEmployId().hashCode());
		result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		return result;
	}
}