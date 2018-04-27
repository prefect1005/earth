package com.gravity.common.support.mybatis.auto.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gravity.api.common.view.model.ShopDownServiceModel;
import com.gravity.common.support.mybatis.Mapper;

@Mapper
public interface OrderAndUserMapper {
	
	List<ShopDownServiceModel> selectDownServiceByUserId(@Param(value = "userId") int userId, @Param(value = "beginSize") int beginSize,
			@Param(value = "onePageSize") int onePageSize);
	
	int selectCountByUserId(@Param(value = "userId") int userId);

}
