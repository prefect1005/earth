package com.gravity.api.provider.service.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.ProviderBasicInfoCompanyModel;
import com.gravity.api.common.view.model.ProviderBasicInfoPersonModel;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationPersonMapper;

@Service
public class ProviderBasicInfoServiceImpl implements ProviderBasicInfoServiceIF {

	@Value("#{filePath['image.provider.indenty.url']}")
	private String providerIndentyImageUrl;

	@Autowired
	ServiceProviderIdentificationCompanyMapper serviceProviderIdentificationCompanyMapper;

	@Autowired
	ServiceProviderIdentificationPersonMapper serviceProviderIdentificationPersonMapper;

	@Override
	public ProviderBasicInfoCompanyModel getBasicInfoCompanyByUserId(int userId) {

		ServiceProviderIdentificationCompanyExample serviceProviderIdentificationCompanyExample = new ServiceProviderIdentificationCompanyExample();

		serviceProviderIdentificationCompanyExample.createCriteria().andUserIdEqualTo(userId);

		List<ServiceProviderIdentificationCompany> basicInfoCompanyList = serviceProviderIdentificationCompanyMapper
				.selectByExampleWithBLOBs(serviceProviderIdentificationCompanyExample);

		if (basicInfoCompanyList == null || basicInfoCompanyList.size() == 0) {
			return null;
		}

		ServiceProviderIdentificationCompany basicInfoCompany = basicInfoCompanyList.get(0);

		ProviderBasicInfoCompanyModel providerBasicInfoCompany = new ProviderBasicInfoCompanyModel();
		providerBasicInfoCompany.setId(basicInfoCompany.getId());
		providerBasicInfoCompany.setCompanyName(basicInfoCompany.getCompanyName());
		providerBasicInfoCompany.setCorporateRepresentative(basicInfoCompany.getCorporateRepresentative());
		providerBasicInfoCompany.setCompanyLocationProvinceId(basicInfoCompany.getCompanyLocationProvinceId());
		providerBasicInfoCompany.setCompanyLocationCityId(basicInfoCompany.getCompanyLocationCityId());
		providerBasicInfoCompany.setCompanyLocationTownId(basicInfoCompany.getCompanyLocationTownId());
		providerBasicInfoCompany.setCompanyLocationDetail(basicInfoCompany.getCompanyLocationDetail());
		providerBasicInfoCompany.setContactsName(basicInfoCompany.getContactsName());
		providerBasicInfoCompany.setCompanyProfile(basicInfoCompany.getCompanyProfile());
		providerBasicInfoCompany.setCompanyEmail(basicInfoCompany.getCompanyEmail());
		providerBasicInfoCompany.setCompanyBankAccount(basicInfoCompany.getCompanyBankAccount());
		providerBasicInfoCompany.setSocialUnifiedCreditCode(basicInfoCompany.getSocialunifiedcreditcode());
		if (StringUtils.isBlank(basicInfoCompany.getStoreDisplayImgSrc())) {
			providerBasicInfoCompany.setStoreDisplayImg("");
		} else {
			providerBasicInfoCompany.setStoreDisplayImg(providerIndentyImageUrl + basicInfoCompany.getStoreDisplayImgSrc());
		}

		String[] imagesUrl = new String[3];
		String[] imageList = basicInfoCompany.getImgSrcList().split(",");
		int index = 0;
		for (String imageSrc : imageList) {
			imagesUrl[index++] = providerIndentyImageUrl + imageSrc;
		}

		providerBasicInfoCompany.setImgSrcList(imagesUrl);

		return providerBasicInfoCompany;
	}

	@Override
	public boolean updateBasicInfoCompanyByIdAndUserId(ProviderBasicInfoCompanyModel providerBasicInfoCompany,
			int userId) {

		if (providerBasicInfoCompany == null) {
			return false;
		}

		ServiceProviderIdentificationCompanyExample serviceProviderIdentificationCompanyExample = new ServiceProviderIdentificationCompanyExample();

		serviceProviderIdentificationCompanyExample.createCriteria().andUserIdEqualTo(userId)
				.andIdEqualTo(providerBasicInfoCompany.getId());

		List<ServiceProviderIdentificationCompany> basicInfoCompanyList = serviceProviderIdentificationCompanyMapper
				.selectByExampleWithBLOBs(serviceProviderIdentificationCompanyExample);

		if (basicInfoCompanyList == null || basicInfoCompanyList.size() == 0) {
			return false;
		}

		ServiceProviderIdentificationCompany basicInfoCompany = basicInfoCompanyList.get(0);
		basicInfoCompany.setCompanyLocationProvinceId(providerBasicInfoCompany.getCompanyLocationProvinceId());
		basicInfoCompany.setCompanyLocationCityId(providerBasicInfoCompany.getCompanyLocationCityId());
		basicInfoCompany.setCompanyLocationTownId(providerBasicInfoCompany.getCompanyLocationTownId());
		basicInfoCompany.setCompanyLocation(providerBasicInfoCompany.getCompanyLocation());
		basicInfoCompany.setCompanyLocationDetail(providerBasicInfoCompany.getCompanyLocationDetail());
		basicInfoCompany.setContactsName(providerBasicInfoCompany.getContactsName());
		basicInfoCompany.setCompanyProfile(providerBasicInfoCompany.getCompanyProfile());
		basicInfoCompany.setCompanyEmail(providerBasicInfoCompany.getCompanyEmail());
		basicInfoCompany.setStoreDisplayImgSrc(providerBasicInfoCompany.getStoreDisplayImg());
		basicInfoCompany.setUpdateTime(new Date());

		int result = serviceProviderIdentificationCompanyMapper.updateByPrimaryKey(basicInfoCompany);
		if (result != 0) {
			return true;
		}
		return false;
	}

	@Override
	public ProviderBasicInfoPersonModel getBasicInfoPersonByUserId(int userId) {

		ServiceProviderIdentificationPersonExample example = new ServiceProviderIdentificationPersonExample();
		example.createCriteria().andUserIdEqualTo(userId);

		List<ServiceProviderIdentificationPerson> basicInfoPersonList = serviceProviderIdentificationPersonMapper
				.selectByExample(example);

		if (basicInfoPersonList == null || basicInfoPersonList.size() == 0) {
			return null;
		}

		ServiceProviderIdentificationPerson basicInfoPerson = basicInfoPersonList.get(0);

		ProviderBasicInfoPersonModel providerBasicInfoPerson = new ProviderBasicInfoPersonModel();

		providerBasicInfoPerson.setId(basicInfoPerson.getId());
		providerBasicInfoPerson.setName(basicInfoPerson.getName());
		providerBasicInfoPerson.setIdCardNumber(basicInfoPerson.getIdCardNumber());
		providerBasicInfoPerson.setLocationProvinceId(basicInfoPerson.getLocationProvinceId());
		providerBasicInfoPerson.setLocationCityId(basicInfoPerson.getLocationCityId());
		providerBasicInfoPerson.setLocationTownId(basicInfoPerson.getLocationTownId());
		providerBasicInfoPerson.setBankCardId(basicInfoPerson.getBankCardId());
		providerBasicInfoPerson.setPhoneNumber(basicInfoPerson.getPhoneNumber());
		providerBasicInfoPerson.setEmail(basicInfoPerson.getEmail());
		
		if (StringUtils.isBlank(basicInfoPerson.getStoreDisplayImgSrc())) {
			providerBasicInfoPerson.setStoreDisplayImg("");
		} else {
			providerBasicInfoPerson.setStoreDisplayImg(providerIndentyImageUrl + basicInfoPerson.getStoreDisplayImgSrc());
		}

		String[] imagesUrl = new String[3];
		String[] imageList = basicInfoPerson.getImgSrcList().split(",");
		int index = 0;
		for (String imageSrc : imageList) {
			imagesUrl[index++] = providerIndentyImageUrl + imageSrc;
		}
		providerBasicInfoPerson.setImgSrcList(imagesUrl);

		return providerBasicInfoPerson;
	}

	@Override
	public boolean updateBasicInfoPersonByIdAndUserId(ProviderBasicInfoPersonModel providerBasicInfoPerson, int userId) {

		if (providerBasicInfoPerson == null) {
			return false;
		}

		ServiceProviderIdentificationPersonExample example = new ServiceProviderIdentificationPersonExample();
		example.createCriteria().andUserIdEqualTo(userId).andIdEqualTo(providerBasicInfoPerson.getId());
		List<ServiceProviderIdentificationPerson> basicInfoPersonList = serviceProviderIdentificationPersonMapper
				.selectByExample(example);

		if (basicInfoPersonList == null || basicInfoPersonList.size() == 0) {
			return false;
		}

		ServiceProviderIdentificationPerson basicInfoPerson = basicInfoPersonList.get(0);
		basicInfoPerson.setLocationProvinceId(providerBasicInfoPerson.getLocationProvinceId());
		basicInfoPerson.setLocationCityId(providerBasicInfoPerson.getLocationCityId());
		basicInfoPerson.setLocationTownId(providerBasicInfoPerson.getLocationTownId());
		basicInfoPerson.setLocation(providerBasicInfoPerson.getLocation());
		basicInfoPerson.setEmail(providerBasicInfoPerson.getEmail());
		basicInfoPerson.setStoreDisplayImgSrc(providerBasicInfoPerson.getStoreDisplayImg());
		basicInfoPerson.setUpdateTime(new Date());
		int result = serviceProviderIdentificationPersonMapper.updateByPrimaryKey(basicInfoPerson);
		if (result != 0) {
			return true;
		}
		return false;
	}

}
