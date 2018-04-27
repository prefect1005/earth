package com.gravity.api.provider.service.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.ProviderIndentyCompanyModel;
import com.gravity.api.common.view.model.ProviderIndentyPersonModel;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.UsersMapper;

@Service
public class ProviderIndentificationServiceImpl implements ProviderIndentificationServiceIF {

	@Autowired
	ServiceProviderIdentificationPersonMapper serviceProviderIdentificationPersonMapper;

	@Autowired
	ServiceProviderIdentificationCompanyMapper serviceProviderIdentificationCompanyMapper;
	
	@Autowired
	UsersMapper usersMapper;

	@Override
	public boolean providerIndentificationPerson(ProviderIndentyPersonModel providerIndentyPersonModel, int userId) {

		ServiceProviderIdentificationPerson serviceProviderIdentificationPerson = new ServiceProviderIdentificationPerson();
		serviceProviderIdentificationPerson.setUserId(userId);
		serviceProviderIdentificationPerson.setName(providerIndentyPersonModel.getName());
		serviceProviderIdentificationPerson.setIdCardNumber(providerIndentyPersonModel.getIdCardNumber());
		serviceProviderIdentificationPerson.setLocationProvinceId(providerIndentyPersonModel.getLocationProvinceId());
		serviceProviderIdentificationPerson.setLocationCityId(providerIndentyPersonModel.getLocationCityId());
		serviceProviderIdentificationPerson.setLocationTownId(providerIndentyPersonModel.getLocationTownId());
		serviceProviderIdentificationPerson.setLocation(providerIndentyPersonModel.getLocation());
		serviceProviderIdentificationPerson.setBankCardId(providerIndentyPersonModel.getBankCardId());
		serviceProviderIdentificationPerson.setPhoneNumber(providerIndentyPersonModel.getPhoneNumber());
		serviceProviderIdentificationPerson.setIsApprove(0);
		serviceProviderIdentificationPerson.setEmail(providerIndentyPersonModel.getEmail());

		StringBuffer sb = new StringBuffer();
		String[] imagelist = providerIndentyPersonModel.getImgSrcList();
		boolean tag = false;
		for (String imageSrc : imagelist) {
			if (tag) {
				sb.append(",");
			}
			tag = true;
			sb.append(imageSrc.substring(imageSrc.indexOf("/serinden/") + 10));

		}
		serviceProviderIdentificationPerson.setImgSrcList(sb.toString());
		serviceProviderIdentificationPerson.setCreateTime(new Date());
		serviceProviderIdentificationPerson.setUpdateTime(new Date());

		int result = serviceProviderIdentificationPersonMapper.insert(serviceProviderIdentificationPerson);

		if (result != 0) {
			Users users = usersMapper.selectByPrimaryKey(userId);
			users.setGroupAuthority(1);// 设置为服务商
			int updateResult = usersMapper.updateByPrimaryKey(users);
			if (updateResult != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean providerIndentificationCompany(ProviderIndentyCompanyModel providerIndentyCompanyModel, int userId) {

		ServiceProviderIdentificationCompany serviceProviderIdentificationCompany = new ServiceProviderIdentificationCompany();
		serviceProviderIdentificationCompany.setUserId(userId);
		serviceProviderIdentificationCompany.setCompanyName(providerIndentyCompanyModel.getCompanyName());
		serviceProviderIdentificationCompany.setCorporateRepresentative(providerIndentyCompanyModel
				.getCorporateRepresentative());
		serviceProviderIdentificationCompany.setCompanyLocationProvinceId(providerIndentyCompanyModel
				.getCompanyLocationProvinceId());
		serviceProviderIdentificationCompany.setCompanyLocationCityId(providerIndentyCompanyModel
				.getCompanyLocationCityId());
		serviceProviderIdentificationCompany.setCompanyLocationTownId(providerIndentyCompanyModel
				.getCompanyLocationTownId());
		serviceProviderIdentificationCompany.setCompanyLocation(providerIndentyCompanyModel.getCompanyLocation());
		serviceProviderIdentificationCompany.setCompanyLocationDetail(providerIndentyCompanyModel
				.getCompanyLocationDetail());
		serviceProviderIdentificationCompany.setContactsName(providerIndentyCompanyModel.getContactsName());
		serviceProviderIdentificationCompany.setCompanyProfile(providerIndentyCompanyModel.getCompanyProfile());
		serviceProviderIdentificationCompany.setCompanyEmail(providerIndentyCompanyModel.getCompanyEmail());
		serviceProviderIdentificationCompany.setCompanyBankAccount(providerIndentyCompanyModel.getCompanyBankAccount());
		serviceProviderIdentificationCompany.setPhoneNumber("0");
		serviceProviderIdentificationCompany.setIsApprove(0);
		serviceProviderIdentificationCompany.setSocialunifiedcreditcode(providerIndentyCompanyModel
				.getSocialUnifiedCreditCode());

		StringBuffer sb = new StringBuffer();
		String[] imagelist = providerIndentyCompanyModel.getImgSrcList();
		boolean tag = false;
		for (String imageSrc : imagelist) {
			if (tag) {
				sb.append(",");
			}
			tag = true;
			sb.append(imageSrc.substring(imageSrc.indexOf("/serinden/") + 10));

		}
		serviceProviderIdentificationCompany.setImgSrcList(sb.toString());
		serviceProviderIdentificationCompany.setCreateTime(new Date());
		serviceProviderIdentificationCompany.setUpdateTime(new Date());
		int result = serviceProviderIdentificationCompanyMapper.insert(serviceProviderIdentificationCompany);

		if (result != 0) {
			Users users = usersMapper.selectByPrimaryKey(userId);
			users.setGroupAuthority(1);// 设置为服务商
			int updateResult = usersMapper.updateByPrimaryKey(users);
			if (updateResult != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Users getUserDetail(int userId) {
		return usersMapper.selectByPrimaryKey(userId);
	}

}
