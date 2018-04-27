package com.gravity.common.support.mybatis.auto.repository;

import java.util.List;

import com.gravity.common.support.mybatis.Mapper;
import com.gravity.common.support.mybatis.auto.model.CategorySecond;

@Mapper
public interface CategorySecondByFirstMapper {
	
	List<CategorySecond> selectSecondByFirstId(int firstId);

}
