package com.gravity.api.employ.service.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.EmployBasicInfoCompanyModel;
import com.gravity.api.common.view.model.EmployBasicInfoPersonModel;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.repository.EmployIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.EmployIdentificationPersonMapper;

@Service
public class EmployBasicInfoServiceImpl implements EmployBasicInfoServiceIF {

	@Autowired
	EmployIdentificationCompanyMapper employIdentificationCompanyMapper;

	@Autowired
	EmployIdentificationPersonMapper employIdentificationPersonMapper;

	@Override
	public EmployBasicInfoPersonModel getBasicInfoPersonByUser(Users user) {

		if (user == null) {
			return null;
		}

		EmployBasicInfoPersonModel employBasicInfoPerson = new EmployBasicInfoPersonModel();

		EmployIdentificationPersonExample employIdentificationPersonExample = new EmployIdentificationPersonExample();

		employIdentificationPersonExample.createCriteria().andUserIdEqualTo(user.getId());
		List<EmployIdentificationPerson> employPersonList = employIdentificationPersonMapper
				.selectByExample(employIdentificationPersonExample);
		if (employPersonList != null && employPersonList.size() != 0) {
			EmployIdentificationPerson employIdentificationPerson = employPersonList.get(0);

			employBasicInfoPerson.setId(employIdentificationPerson.getId());
			employBasicInfoPerson.setName(employIdentificationPerson.getName());
			employBasicInfoPerson.setIdCardNumber(employIdentificationPerson.getIdCardNumber());
			employBasicInfoPerson.setEmail(employIdentificationPerson.getEmail());
			employBasicInfoPerson.setPhoneNumber(user.getPhone());

			return employBasicInfoPerson;
		}

		return null;
	}

	@Override
	public boolean updateBasicInfoPersonByInfoId(EmployBasicInfoPersonModel employBasicInfoPerson, int userId) {

		EmployIdentificationPersonExample employIdentificationPersonExample = new EmployIdentificationPersonExample();

		employIdentificationPersonExample.createCriteria().andUserIdEqualTo(userId)
				.andIdEqualTo(employBasicInfoPerson.getId());
		List<EmployIdentificationPerson> employPersonList = employIdentificationPersonMapper
				.selectByExample(employIdentificationPersonExample);

		if (employPersonList != null && employPersonList.size() != 0) {
			EmployIdentificationPerson employIdentificationPerson = employPersonList.get(0);
			employIdentificationPerson.setEmail(employBasicInfoPerson.getEmail());
			employIdentificationPerson.setUpdateTime(new Date());
			int result = employIdentificationPersonMapper.updateByPrimaryKey(employIdentificationPerson);
			if (result != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public EmployBasicInfoCompanyModel getBasicInfoCompanyByUser(Users user) {

		if (user == null) {
			return null;
		}

		EmployBasicInfoCompanyModel employBasicInfoCompany = new EmployBasicInfoCompanyModel();

		EmployIdentificationCompanyExample employIdentificationCompanyExample = new EmployIdentificationCompanyExample();
		employIdentificationCompanyExample.createCriteria().andUserIdEqualTo(user.getId());

		List<EmployIdentificationCompany> employCompanyList = employIdentificationCompanyMapper
				.selectByExample(employIdentificationCompanyExample);

		if (employCompanyList != null && employCompanyList.size() != 0) {
			EmployIdentificationCompany employIdentificationCompany = employCompanyList.get(0);
			employBasicInfoCompany.setId(employIdentificationCompany.getId());
			employBasicInfoCompany.setCompanyName(employIdentificationCompany.getCompanyName());
			employBasicInfoCompany.setCorporateRepresentative(employIdentificationCompany.getCorporateRepresentative());
			employBasicInfoCompany
					.setCompanyLocationProvinceId(employIdentificationCompany.getCompanyLocationProvinceId());
			employBasicInfoCompany.setCompanyLocationCityId(employIdentificationCompany.getCompanyLocationCityId());
			employBasicInfoCompany.setCompanyLocationTownId(employIdentificationCompany.getCompanyLocationTownId());
			employBasicInfoCompany.setCompanyLocationDetail(employIdentificationCompany.getCompanyLocationDetail());
			employBasicInfoCompany.setPhoneNumber(user.getPhone());
			employBasicInfoCompany.setCompanyEmail(employIdentificationCompany.getCompanyEmail());
			employBasicInfoCompany.setSocialUnifiedCreditCode(employIdentificationCompany.getSocialunifiedcreditcode());
			return employBasicInfoCompany;

		}

		return null;
	}

	@Override
	public boolean updateBasicInfoCompanyByInfoId(EmployBasicInfoCompanyModel employBasicInfoCompany, int userId) {

		EmployIdentificationCompanyExample employIdentificationCompanyExample = new EmployIdentificationCompanyExample();

		employIdentificationCompanyExample.createCriteria().andUserIdEqualTo(userId)
				.andIdEqualTo(employBasicInfoCompany.getId());
		List<EmployIdentificationCompany> employCompanyList = employIdentificationCompanyMapper
				.selectByExample(employIdentificationCompanyExample);

		if (employCompanyList != null && employCompanyList.size() != 0) {
			EmployIdentificationCompany employIdentificationCompany = employCompanyList.get(0);
			employIdentificationCompany
					.setCompanyLocationProvinceId(employBasicInfoCompany.getCompanyLocationProvinceId());
			employIdentificationCompany.setCompanyLocationCityId(employBasicInfoCompany.getCompanyLocationCityId());
			employIdentificationCompany.setCompanyLocationTownId(employBasicInfoCompany.getCompanyLocationTownId());
			employIdentificationCompany.setCompanyLocation(employBasicInfoCompany.getCompanyLocation());
			employIdentificationCompany.setCompanyLocationDetail(employBasicInfoCompany.getCompanyLocationDetail());
			employIdentificationCompany.setCompanyEmail(employBasicInfoCompany.getCompanyEmail());
			employIdentificationCompany.setUpdateTime(new Date());
			int result = employIdentificationCompanyMapper.updateByPrimaryKey(employIdentificationCompany);
			if (result != 0) {
				return true;
			}
		}

		return false;
	}

}
