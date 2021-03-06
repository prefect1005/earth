package com.gravity.common.support.mybatis.auto.model;

import java.util.Date;

public class UsersVerifyCode {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column users_verify_code.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column users_verify_code.phone
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private String phone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column users_verify_code.v_code
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private String vCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column users_verify_code.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Date createTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column users_verify_code.id
	 * @return  the value of users_verify_code.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column users_verify_code.id
	 * @param id  the value for users_verify_code.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column users_verify_code.phone
	 * @return  the value of users_verify_code.phone
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column users_verify_code.phone
	 * @param phone  the value for users_verify_code.phone
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column users_verify_code.v_code
	 * @return  the value of users_verify_code.v_code
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public String getvCode() {
		return vCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column users_verify_code.v_code
	 * @param vCode  the value for users_verify_code.v_code
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setvCode(String vCode) {
		this.vCode = vCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column users_verify_code.create_time
	 * @return  the value of users_verify_code.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column users_verify_code.create_time
	 * @param createTime  the value for users_verify_code.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users_verify_code
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
		UsersVerifyCode other = (UsersVerifyCode) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
				&& (this.getvCode() == null ? other.getvCode() == null : this.getvCode().equals(other.getvCode()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(
						other.getCreateTime()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users_verify_code
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = prime * result + ((getvCode() == null) ? 0 : getvCode().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		return result;
	}
}