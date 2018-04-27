package com.gravity.common.support.mybatis.auto.model;

import java.util.Date;

public class HomeCompanyLogo {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column home_company_logo.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column home_company_logo.logo_path
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private String logoPath;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column home_company_logo.is_used
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Integer isUsed;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column home_company_logo.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	private Date createTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column home_company_logo.id
	 * @return  the value of home_company_logo.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column home_company_logo.id
	 * @param id  the value for home_company_logo.id
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column home_company_logo.logo_path
	 * @return  the value of home_company_logo.logo_path
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public String getLogoPath() {
		return logoPath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column home_company_logo.logo_path
	 * @param logoPath  the value for home_company_logo.logo_path
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column home_company_logo.is_used
	 * @return  the value of home_company_logo.is_used
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Integer getIsUsed() {
		return isUsed;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column home_company_logo.is_used
	 * @param isUsed  the value for home_company_logo.is_used
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column home_company_logo.create_time
	 * @return  the value of home_company_logo.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column home_company_logo.create_time
	 * @param createTime  the value for home_company_logo.create_time
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table home_company_logo
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
		HomeCompanyLogo other = (HomeCompanyLogo) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getLogoPath() == null ? other.getLogoPath() == null : this.getLogoPath().equals(
						other.getLogoPath()))
				&& (this.getIsUsed() == null ? other.getIsUsed() == null : this.getIsUsed().equals(other.getIsUsed()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(
						other.getCreateTime()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table home_company_logo
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getLogoPath() == null) ? 0 : getLogoPath().hashCode());
		result = prime * result + ((getIsUsed() == null) ? 0 : getIsUsed().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		return result;
	}
}