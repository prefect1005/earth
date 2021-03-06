package com.gravity.common.support.mybatis.auto.model;

import java.util.Date;

public class ProviderWithDraw {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column provider_with_draw.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column provider_with_draw.provider_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer providerId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column provider_with_draw.amount
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer amount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column provider_with_draw.is_success
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer isSuccess;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column provider_with_draw.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column provider_with_draw.success_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Date successTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column provider_with_draw.id
	 * @return  the value of provider_with_draw.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column provider_with_draw.id
	 * @param id  the value for provider_with_draw.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column provider_with_draw.provider_id
	 * @return  the value of provider_with_draw.provider_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getProviderId() {
		return providerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column provider_with_draw.provider_id
	 * @param providerId  the value for provider_with_draw.provider_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column provider_with_draw.amount
	 * @return  the value of provider_with_draw.amount
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column provider_with_draw.amount
	 * @param amount  the value for provider_with_draw.amount
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column provider_with_draw.is_success
	 * @return  the value of provider_with_draw.is_success
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getIsSuccess() {
		return isSuccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column provider_with_draw.is_success
	 * @param isSuccess  the value for provider_with_draw.is_success
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column provider_with_draw.create_time
	 * @return  the value of provider_with_draw.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column provider_with_draw.create_time
	 * @param createTime  the value for provider_with_draw.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column provider_with_draw.success_time
	 * @return  the value of provider_with_draw.success_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Date getSuccessTime() {
		return successTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column provider_with_draw.success_time
	 * @param successTime  the value for provider_with_draw.success_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table provider_with_draw
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
		ProviderWithDraw other = (ProviderWithDraw) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getProviderId() == null ? other.getProviderId() == null : this.getProviderId().equals(
						other.getProviderId()))
				&& (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
				&& (this.getIsSuccess() == null ? other.getIsSuccess() == null : this.getIsSuccess().equals(
						other.getIsSuccess()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(
						other.getCreateTime()))
				&& (this.getSuccessTime() == null ? other.getSuccessTime() == null : this.getSuccessTime().equals(
						other.getSuccessTime()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table provider_with_draw
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getProviderId() == null) ? 0 : getProviderId().hashCode());
		result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
		result = prime * result + ((getIsSuccess() == null) ? 0 : getIsSuccess().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getSuccessTime() == null) ? 0 : getSuccessTime().hashCode());
		return result;
	}
}