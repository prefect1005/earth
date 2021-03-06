package com.gravity.common.support.mybatis.auto.model;

import java.util.Date;

public class CategoryMapping {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column category_mapping.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column category_mapping.category_first_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer categoryFirstId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column category_mapping.category_second_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer categorySecondId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column category_mapping.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Date createTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column category_mapping.id
	 * @return  the value of category_mapping.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column category_mapping.id
	 * @param id  the value for category_mapping.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column category_mapping.category_first_id
	 * @return  the value of category_mapping.category_first_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getCategoryFirstId() {
		return categoryFirstId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column category_mapping.category_first_id
	 * @param categoryFirstId  the value for category_mapping.category_first_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setCategoryFirstId(Integer categoryFirstId) {
		this.categoryFirstId = categoryFirstId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column category_mapping.category_second_id
	 * @return  the value of category_mapping.category_second_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getCategorySecondId() {
		return categorySecondId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column category_mapping.category_second_id
	 * @param categorySecondId  the value for category_mapping.category_second_id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setCategorySecondId(Integer categorySecondId) {
		this.categorySecondId = categorySecondId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column category_mapping.create_time
	 * @return  the value of category_mapping.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column category_mapping.create_time
	 * @param createTime  the value for category_mapping.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table category_mapping
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
		CategoryMapping other = (CategoryMapping) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getCategoryFirstId() == null ? other.getCategoryFirstId() == null : this.getCategoryFirstId()
						.equals(other.getCategoryFirstId()))
				&& (this.getCategorySecondId() == null ? other.getCategorySecondId() == null : this
						.getCategorySecondId().equals(other.getCategorySecondId()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(
						other.getCreateTime()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table category_mapping
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getCategoryFirstId() == null) ? 0 : getCategoryFirstId().hashCode());
		result = prime * result + ((getCategorySecondId() == null) ? 0 : getCategorySecondId().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		return result;
	}
}