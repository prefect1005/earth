package com.gravity.common.support.mybatis.auto.repository;

import com.gravity.common.support.mybatis.Mapper;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderService;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ServiceProviderServiceMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int countByExample(ServiceProviderServiceExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int deleteByExample(ServiceProviderServiceExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int insert(ServiceProviderService record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int insertSelective(ServiceProviderService record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	List<ServiceProviderService> selectByExampleWithBLOBs(ServiceProviderServiceExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	List<ServiceProviderService> selectByExample(ServiceProviderServiceExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	ServiceProviderService selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByExampleSelective(@Param("record") ServiceProviderService record,
			@Param("example") ServiceProviderServiceExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByExampleWithBLOBs(@Param("record") ServiceProviderService record,
			@Param("example") ServiceProviderServiceExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByExample(@Param("record") ServiceProviderService record,
			@Param("example") ServiceProviderServiceExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByPrimaryKeySelective(ServiceProviderService record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByPrimaryKeyWithBLOBs(ServiceProviderService record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table service_provider_service
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByPrimaryKey(ServiceProviderService record);

	int onlineCountByUserId(@Param(value = "userId") int userId);

	List<ServiceProviderService> selectServiceProviderServiceByPage(@Param(value = "userId") int userId,
			@Param(value = "page") int page, @Param(value = "onePageSize") int onePageSize);

	List<ServiceProviderService> selectServiceByCategory(@Param(value = "secondCategory") int secondCategory,
			@Param(value = "inOrderType") int inOrderType, @Param(value = "inOrderTypeUpDown") int inOrderTypeUpDown,
			@Param(value = "location") String location, @Param(value = "page") int page,
			@Param(value = "onePageSize") int onePageSize);

	int selectServiceByCategoryTotalNumber(@Param(value = "secondCategory") int secondCategory,
			@Param(value = "location") String location);

	List<ServiceProviderService> selectServiceByKeyWord(@Param(value = "inOrderType") int inOrderType,
			@Param(value = "inOrderTypeUpDown") int inOrderTypeUpDown, @Param(value = "location") String location,
			@Param(value = "searchKeyWord") String searchKeyWord, @Param(value = "page") int page,
			@Param(value = "onePageSize") int onePageSize);

	int selectServiceByKeyWordTotalNumber(@Param(value = "location") String location,
			@Param(value = "searchKeyWord") String searchKeyWord);
	
	List<Integer> selectSecondLevelByUserId(@Param(value = "userId") int userId);
}