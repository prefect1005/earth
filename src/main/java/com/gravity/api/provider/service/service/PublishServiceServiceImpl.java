package com.gravity.api.provider.service.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.PublishServiceModel;
import com.gravity.common.support.mybatis.auto.model.CategoryFirst;
import com.gravity.common.support.mybatis.auto.model.CategorySecond;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderService;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.repository.CategoryFirstMapper;
import com.gravity.common.support.mybatis.auto.repository.CategorySecondByFirstMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderServiceMapper;

@Service
public class PublishServiceServiceImpl implements PublishServiceServiceIF {

	@Autowired
	private CategoryFirstMapper categoryFirstMapper;
	
	@Autowired
	private ServiceProviderServiceMapper serviceProviderServiceMapper;

	@Autowired
	private CategorySecondByFirstMapper categorySecondByFirstMapper;
	
	@Autowired
	private ServiceProviderIdentificationCompanyMapper serviceProviderIdentificationCompanyMapper;

	@Autowired
	private ServiceProviderIdentificationPersonMapper serviceProviderIdentificationPersonMapper;
	
	@Override
	public Map<String, String> getFirstCategory() {
		Map<String, String> resultList = new HashMap<>();
		List<CategoryFirst> list = categoryFirstMapper.selectAll();
		if (list != null && !list.isEmpty()) {
			for (CategoryFirst temp : list) {
				resultList.put(temp.getId().toString(), temp.getName());
			}
		}
		return resultList;
	}

	@Override
	public Map<String, String> getSecondCategoryByFirst(int firstId) {
		Map<String, String> resultList = new HashMap<>();

		List<CategorySecond> list = categorySecondByFirstMapper.selectSecondByFirstId(firstId);
		if (list != null && !list.isEmpty()) {
			for (CategorySecond temp : list) {
				resultList.put(temp.getId().toString(), temp.getName());
			}
		}

		return resultList;
	}

	@Override
	public boolean savePublishService(PublishServiceModel publishServiceModel, Users user) {

		if (publishServiceModel == null) {
			return false;
		}
		ServiceProviderService serviceProviderService = new ServiceProviderService();
		serviceProviderService.setUserId(user.getId());
		serviceProviderService.setTitle(publishServiceModel.getTitle());
		serviceProviderService.setFirstLevel(Integer.parseInt(publishServiceModel.getFirstlevel()));
		serviceProviderService.setSecondLevel(Integer.parseInt(publishServiceModel.getSecondlevel()));
		
		if (user.getTypeCp() == 1) { //企业
			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andUserIdEqualTo(user.getId());
			List<ServiceProviderIdentificationCompany> providerCompanyDetailList = serviceProviderIdentificationCompanyMapper.selectByExample(example);
			if (providerCompanyDetailList != null && providerCompanyDetailList.size() != 0) {
				ServiceProviderIdentificationCompany providerCompanyDetail = providerCompanyDetailList.get(0);
				serviceProviderService.setProvinceId(providerCompanyDetail.getCompanyLocationProvinceId());
				serviceProviderService.setCityId(providerCompanyDetail.getCompanyLocationCityId());
			}
		} else if (user.getTypeCp() == 2) { //个人
			ServiceProviderIdentificationPersonExample example = new ServiceProviderIdentificationPersonExample();
			example.createCriteria().andUserIdEqualTo(user.getId());
			List<ServiceProviderIdentificationPerson>  providerPersonDetailList = serviceProviderIdentificationPersonMapper.selectByExample(example);
			if (providerPersonDetailList != null && providerPersonDetailList.size() != 0) {
				ServiceProviderIdentificationPerson providerCompanyDetail = providerPersonDetailList.get(0);
				serviceProviderService.setProvinceId(providerCompanyDetail.getLocationProvinceId());
				serviceProviderService.setCityId(providerCompanyDetail.getLocationCityId());
			}
		}
		
		serviceProviderService.setSalesNumber(0);
		serviceProviderService.setKeyword(publishServiceModel.getKeyword());
		serviceProviderService.setOneIntroduction(publishServiceModel.getOneintroduction());
		serviceProviderService.setIntroductionDetail(publishServiceModel.getIntroductiondetail());
		serviceProviderService.setOriginalPrice(publishServiceModel.getOprice());
		serviceProviderService.setGroupPrice(publishServiceModel.getGprice());
		StringBuffer imgSrcList = new StringBuffer();
		String[] imagelist = publishServiceModel.getImgSrcList();
		boolean tag = false;
		for (String imageSrc : imagelist) {
			if (tag) {
				imgSrcList.append(",");
			}
			tag = true;
			imgSrcList.append(imageSrc.substring(imageSrc.indexOf("/pubser/") + 8));
			
		}
		serviceProviderService.setImgSrcList(imgSrcList.toString());
		serviceProviderService.setOnline(1);
		serviceProviderService.setCreateTime(new Date());
		serviceProviderService.setUpdateTime(new Date());

		int result = serviceProviderServiceMapper.insert(serviceProviderService);
		if (result != 0) {
			return true;
		}
		return false;
	}

	@Override
	public int getIsApproveByUserId(int userId, int userType) {
		
		if (userType == 1) { //企业
			ServiceProviderIdentificationCompanyExample companyExample = new ServiceProviderIdentificationCompanyExample();
			companyExample.createCriteria().andUserIdEqualTo(userId);
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper.selectByExample(companyExample);
			if (CollectionUtils.isNotEmpty(companyList)) {
				ServiceProviderIdentificationCompany company = companyList.get(0);
				return company.getIsApprove();
			}
		} else { //个人
			ServiceProviderIdentificationPersonExample example = new ServiceProviderIdentificationPersonExample();
			example.createCriteria().andUserIdEqualTo(userId);
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper.selectByExample(example);
			if (CollectionUtils.isNotEmpty(personList)) {
				ServiceProviderIdentificationPerson person = personList.get(0);
				return person.getIsApprove();
			}
		}
		return 0;
	}

}
