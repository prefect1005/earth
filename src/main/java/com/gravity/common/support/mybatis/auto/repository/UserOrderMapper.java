package com.gravity.common.support.mybatis.auto.repository;

import com.gravity.api.common.view.model.AdminrOrderDBModel;
import com.gravity.api.common.view.model.EmployOrderDBModel;
import com.gravity.api.common.view.model.EmployOrderShowDBModel;
import com.gravity.api.common.view.model.EmployOrderShowModel;
import com.gravity.api.common.view.model.ProviderOrderDBModel;
import com.gravity.api.common.view.model.ProviderOrderShowModel;
import com.gravity.common.support.mybatis.Mapper;
import com.gravity.common.support.mybatis.auto.model.UserOrder;
import com.gravity.common.support.mybatis.auto.model.UserOrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserOrderMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int countByExample(UserOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int deleteByExample(UserOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int insert(UserOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int insertSelective(UserOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	List<UserOrder> selectByExample(UserOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	UserOrder selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByExampleSelective(@Param("record") UserOrder record, @Param("example") UserOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByExample(@Param("record") UserOrder record, @Param("example") UserOrderExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByPrimaryKeySelective(UserOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_order
	 * @mbggenerated  Sat Sep 24 10:43:53 CST 2016
	 */
	int updateByPrimaryKey(UserOrder record);

	List<EmployOrderShowModel> selectByEmployIdAndPageCompany(@Param(value = "employUserId") int employUserId,
			@Param(value = "begin") int begin, @Param(value = "onePageSize") int onePageSize);

	List<EmployOrderShowModel> selectByEmployIdAndPagePerson(@Param(value = "employUserId") int employUserId,
			@Param(value = "begin") int begin, @Param(value = "onePageSize") int onePageSize);

	int selectCountByEmployId(@Param(value = "employUserId") int employUserId);

	List<EmployOrderShowModel> selectByEmployIdAndPageAndServiceTitleCompany(
			@Param(value = "employUserId") int employUserId, @Param(value = "queryKey") String queryKey,
			@Param(value = "begin") int begin, @Param(value = "onePageSize") int onePageSize);

	int selectCountByEmployIdAndServiceTitle(@Param(value = "employUserId") int employUserId,
			@Param(value = "queryKey") String queryKey);

	List<EmployOrderShowModel> selectByEmployIdAndPageAndStatusCompany(@Param(value = "employUserId") int employUserId,
			@Param(value = "queryKey") String queryKey, @Param(value = "begin") int begin,
			@Param(value = "onePageSize") int onePageSize);

	int selectCountByEmployIdAndStatus(@Param(value = "employUserId") int employUserId,
			@Param(value = "queryKey") String queryKey);

	List<EmployOrderShowModel> selectByEmployIdAndPageAndIsCancelCompany(
			@Param(value = "employUserId") int employUserId, @Param(value = "queryKey") String queryKey,
			@Param(value = "begin") int begin, @Param(value = "onePageSize") int onePageSize);

	int selectCountByEmployIdAndIsCancel(@Param(value = "employUserId") int employUserId,
			@Param(value = "queryKey") String queryKey);

	List<EmployOrderShowModel> selectByEmployIdAndPageAndServiceTitlePerson(
			@Param(value = "employUserId") int employUserId, @Param(value = "queryKey") String queryKey,
			@Param(value = "begin") int begin, @Param(value = "onePageSize") int onePageSize);

	List<EmployOrderShowModel> selectByEmployIdAndPageAndStatusPerson(@Param(value = "employUserId") int employUserId,
			@Param(value = "queryKey") String queryKey, @Param(value = "begin") int begin,
			@Param(value = "onePageSize") int onePageSize);

	List<EmployOrderShowModel> selectByEmployIdAndPageAndIsCancelPerson(@Param(value = "employUserId") int employUserId,
			@Param(value = "queryKey") String queryKey, @Param(value = "begin") int begin,
			@Param(value = "onePageSize") int onePageSize);

	// List<ProviderOrderShowModel>
	// selectByProviderIdAndPageCompany(@Param(value = "providerUserId") int
	// providerUserId,
	// @Param(value = "begin") int begin, @Param(value = "onePageSize") int
	// onePageSize);

	List<EmployOrderDBModel> selectByEmployIdAndPage(@Param(value = "employUserId") int employUserId,
			@Param(value = "begin") int begin, @Param(value = "onePageSize") int onePageSize);

	List<EmployOrderDBModel> selectByEmployIdAndPageAndServiceTitle(@Param(value = "employUserId") int employUserId,
			@Param(value = "queryKey") String queryKey, @Param(value = "begin") int begin,
			@Param(value = "onePageSize") int onePageSize);

	List<EmployOrderDBModel> selectByEmployIdAndPageAndIsCancel(@Param(value = "employUserId") int employUserId,
			@Param(value = "queryKey") String queryKey, @Param(value = "begin") int begin,
			@Param(value = "onePageSize") int onePageSize);

	List<EmployOrderDBModel> selectByEmployIdAndPageAndStatus(@Param(value = "employUserId") int employUserId,
			@Param(value = "queryKey") String queryKey, @Param(value = "begin") int begin,
			@Param(value = "onePageSize") int onePageSize);

	List<ProviderOrderDBModel> selectByProviderIdAndPage(@Param(value = "providerUserId") int providerUserId,
			@Param(value = "begin") int begin, @Param(value = "onePageSize") int onePageSize);

	List<ProviderOrderDBModel> selectByProviderIdAndPageAndServiceTitle(
			@Param(value = "providerUserId") int providerUserId, @Param(value = "queryKey") String queryKey,
			@Param(value = "begin") int begin, @Param(value = "onePageSize") int onePageSize);

	List<ProviderOrderDBModel> selectByProviderIdAndPageAndIsCancel(@Param(value = "providerUserId") int providerUserId,
			@Param(value = "queryKey") String queryKey, @Param(value = "begin") int begin,
			@Param(value = "onePageSize") int onePageSize);

	List<ProviderOrderDBModel> selectByProviderIdAndPageAndStatus(@Param(value = "providerUserId") int providerUserId,
			@Param(value = "queryKey") String queryKey, @Param(value = "begin") int begin,
			@Param(value = "onePageSize") int onePageSize);

	int selectCountByProviderId(@Param(value = "providerUserId") int providerUserId);

	int selectCountByProviderIdAndServiceTitle(@Param(value = "providerUserId") int providerUserId,
			@Param(value = "queryKey") String queryKey);

	int selectCountByProviderIdAndIsCancel(@Param(value = "providerUserId") int providerUserId,
			@Param(value = "queryKey") String queryKey);

	int selectCountByProviderIdAndStatus(@Param(value = "providerUserId") int providerUserId,
			@Param(value = "queryKey") String queryKey);

	List<AdminrOrderDBModel> selectByPage(@Param(value = "begin") int begin,
			@Param(value = "onePageSize") int onePageSize);

	List<AdminrOrderDBModel> selectByPageAndServiceTitle(@Param(value = "queryKey") String queryKey,
			@Param(value = "begin") int begin, @Param(value = "onePageSize") int onePageSize);

	List<AdminrOrderDBModel> selectByPageAndIsCancel(@Param(value = "queryKey") String queryKey,
			@Param(value = "begin") int begin, @Param(value = "onePageSize") int onePageSize);

	List<AdminrOrderDBModel> selectByPageAndStatus(@Param(value = "queryKey") String queryKey,
			@Param(value = "begin") int begin, @Param(value = "onePageSize") int onePageSize);

	int selectCount();

	int selectCountByServiceTitle(@Param(value = "queryKey") String queryKey);

	int selectCountByIsCancel(@Param(value = "queryKey") String queryKey);
	
	int selectCountByStatus(@Param(value = "queryKey") String queryKey);

}