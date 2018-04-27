package com.gravity.api.home.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.ServiceLocationModel;
import com.gravity.common.support.mybatis.auto.model.CategoryFirst;
import com.gravity.common.support.mybatis.auto.model.CategoryFirstExample;
import com.gravity.common.support.mybatis.auto.model.CategorySecond;
import com.gravity.common.support.mybatis.auto.model.HomeCompanyLogo;
import com.gravity.common.support.mybatis.auto.model.HomeCompanyLogoExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.repository.CategoryFirstMapper;
import com.gravity.common.support.mybatis.auto.repository.CategorySecondByFirstMapper;
import com.gravity.common.support.mybatis.auto.repository.HomeCompanyLogoMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationPersonMapper;

@Service
public class HomeServiceImpl implements HomeServiceIF {

	@Autowired
	private CategoryFirstMapper categoryFirstMapper;

	@Autowired
	private CategorySecondByFirstMapper categorySecondByFirstMapper;

	@Autowired
	private ServiceProviderIdentificationCompanyMapper serviceProviderIdentificationCompanyMapper;

	@Autowired
	private ServiceProviderIdentificationPersonMapper serviceProviderIdentificationPersonMapper;
	
	@Autowired
	private HomeCompanyLogoMapper homeCompanyLogoMapper;
	
	@Value("#{filePath['image.home.company.logo.url']}")
	private String homeCompanyLogoUrl;

	@Override
	public Map<String, Map<Integer, String>> getCategory() {

		Map<String, Map<Integer, String>> categoryMap = new HashMap<String, Map<Integer, String>>();

		CategoryFirstExample example = new CategoryFirstExample();
		example.createCriteria().andActiveEqualTo(1);
		List<CategoryFirst> categoryFirstList = categoryFirstMapper.selectByExample(example);
		if (categoryFirstList != null && categoryFirstList.size() != 0) {
			for (CategoryFirst categoryFirst : categoryFirstList) {
				Map<Integer, String> secondCategory = new HashMap<Integer, String>();
				List<CategorySecond> categorySecondList = categorySecondByFirstMapper
						.selectSecondByFirstId(categoryFirst.getId());
				if (categorySecondList != null && categorySecondList.size() != 0) {
					for (CategorySecond categorySecond : categorySecondList) {
						secondCategory.put(categorySecond.getId(), categorySecond.getName());
					}
				}
				categoryMap.put(categoryFirst.getName(), secondCategory);
			}
		}

		return categoryMap;
	}

	@Override
	public Set<Map<Integer, Integer>> getServiceLocation() {

		Set<Map<Integer, Integer>> locationListResult = new HashSet<Map<Integer, Integer>>();

		// ServiceProviderIdentificationCompanyExample example = new
		// ServiceProviderIdentificationCompanyExample();
		// example.createCriteria().
		List<ServiceLocationModel> locationList = serviceProviderIdentificationCompanyMapper.selectAllServiceLocation();

		List<ServiceLocationModel> locationPersonList = serviceProviderIdentificationPersonMapper
				.selectAllServiceLocation();

		if (locationList != null && locationList.size() != 0) {
			for (ServiceLocationModel serviceLocation : locationList) {
				Map<Integer, Integer> map = new HashMap<>();
				map.put(serviceLocation.getLocationProvinceId(), serviceLocation.getLocationCityId());
				locationListResult.add(map);
			}
		}

		if (locationPersonList != null && locationPersonList.size() != 0) {
			for (ServiceLocationModel serviceLocation : locationPersonList) {
				Map<Integer, Integer> map = new HashMap<>();
				map.put(serviceLocation.getLocationProvinceId(), serviceLocation.getLocationCityId());
				locationListResult.add(map);
			}
		}

		return locationListResult;
	}

	@Override
	public List<String> getHomeCompanyLogoUrl() {
		
		List<String> logoLostResult = new ArrayList<String>();
		
		HomeCompanyLogoExample example = new HomeCompanyLogoExample();
		example.createCriteria().andIsUsedEqualTo(1);
		List<HomeCompanyLogo>  logoList = homeCompanyLogoMapper.selectByExample(example);
		if (logoList != null && logoList.size() != 0) {
			for (HomeCompanyLogo logo : logoList) {
				logoLostResult.add(homeCompanyLogoUrl + logo.getLogoPath());
			}
		}
		
		return logoLostResult;
	}

}
