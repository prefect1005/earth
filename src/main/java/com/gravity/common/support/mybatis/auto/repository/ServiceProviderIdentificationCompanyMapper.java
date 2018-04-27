package com.gravity.common.support.mybatis.auto.repository;

import com.gravity.api.common.view.model.ServiceLocationModel;
import com.gravity.common.support.mybatis.Mapper;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompanyExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@Mapper
public interface ServiceProviderIdentificationCompanyMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int countByExample(ServiceProviderIdentificationCompanyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int deleteByExample(ServiceProviderIdentificationCompanyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int insert(ServiceProviderIdentificationCompany record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int insertSelective(ServiceProviderIdentificationCompany record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	List<ServiceProviderIdentificationCompany> selectByExampleWithBLOBs(
			ServiceProviderIdentificationCompanyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	List<ServiceProviderIdentificationCompany> selectByExample(ServiceProviderIdentificationCompanyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	ServiceProviderIdentificationCompany selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByExampleSelective(@Param("record") ServiceProviderIdentificationCompany record,
			@Param("example") ServiceProviderIdentificationCompanyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByExampleWithBLOBs(@Param("record") ServiceProviderIdentificationCompany record,
			@Param("example") ServiceProviderIdentificationCompanyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByExample(@Param("record") ServiceProviderIdentificationCompany record,
			@Param("example") ServiceProviderIdentificationCompanyExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByPrimaryKeySelective(ServiceProviderIdentificationCompany record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByPrimaryKeyWithBLOBs(ServiceProviderIdentificationCompany record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_identification_company
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByPrimaryKey(ServiceProviderIdentificationCompany record);

	List<ServiceLocationModel> selectAllServiceLocation ();
}