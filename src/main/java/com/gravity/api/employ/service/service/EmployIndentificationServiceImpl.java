package com.gravity.api.employ.service.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.EmployIndentyCompanyModel;
import com.gravity.api.common.view.model.EmployIndentyPersonModel;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.repository.EmployIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.EmployIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.UsersMapper;

@Service
public class EmployIndentificationServiceImpl implements EmployIndentificationServiceIF {

	@Autowired
	EmployIdentificationCompanyMapper employIdentificationCompanyMapper;

	@Autowired
	EmployIdentificationPersonMapper employIdentificationPersonMapper;
	
	@Autowired
	UsersMapper usersMapper;

	@Override
	public boolean employIndentificationCompany(EmployIndentyCompanyModel employIndentyCompany, int userId) {

		EmployIdentificationCompanyExample employIdentificationCompanyExample = new EmployIdentificationCompanyExample();
		employIdentificationCompanyExample.createCriteria().andUserIdEqualTo(userId);
		List<EmployIdentificationCompany> employCompanyList = employIdentificationCompanyMapper
				.selectByExample(employIdentificationCompanyExample);
		System.out.println("employCompanyList:" + employCompanyList);
		if (employCompanyList != null && employCompanyList.size() != 0) { // 已经认证过
			return false;
		}

		EmployIdentificationCompany employIdentificationCompany = new EmployIdentificationCompany();
		employIdentificationCompany.setUserId(userId);
		employIdentificationCompany.setCompanyName(employIndentyCompany.getCompanyName());
		employIdentificationCompany.setCorporateRepresentative(employIndentyCompany.getCorporateRepresentative());
		employIdentificationCompany.setCompanyLocationProvinceId(employIndentyCompany.getCompanyLocationProvinceId());
		employIdentificationCompany.setCompanyLocationCityId(employIndentyCompany.getCompanyLocationCityId());
		employIdentificationCompany.setCompanyLocationTownId(employIndentyCompany.getCompanyLocationTownId());
		employIdentificationCompany.setCompanyLocation(employIndentyCompany.getCompanyLocation());
		employIdentificationCompany.setCompanyLocationDetail(employIndentyCompany.getCompanyLocationDetail());
		employIdentificationCompany.setCompanyEmail(employIndentyCompany.getCompanyEmail());
		employIdentificationCompany.setSocialunifiedcreditcode(employIndentyCompany.getSocialUnifiedCreditCode());
		employIdentificationCompany.setCreateTime(new Date());
		employIdentificationCompany.setUpdateTime(new Date());

		int result = employIdentificationCompanyMapper.insert(employIdentificationCompany);
		if (result != 0) {
			Users users = usersMapper.selectByPrimaryKey(userId);
			users.setGroupAuthority(2);// 设置为服务商
			int updateResult = usersMapper.updateByPrimaryKey(users);
			if (updateResult != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean employIndentificationPerson(EmployIndentyPersonModel employIndentyPerson, int userId) {

		EmployIdentificationPersonExample employIdentificationPersonExample = new EmployIdentificationPersonExample();

		employIdentificationPersonExample.createCriteria().andUserIdEqualTo(userId);
		List<EmployIdentificationPerson> employPsersonList = employIdentificationPersonMapper
				.selectByExample(employIdentificationPersonExample);
		if (employPsersonList != null && employPsersonList.size() != 0) { // 已经认证过
			return false;
		}
		EmployIdentificationPerson EmployIdentificationPerson = new EmployIdentificationPerson();
		EmployIdentificationPerson.setUserId(userId);
		EmployIdentificationPerson.setName(employIndentyPerson.getName());
		EmployIdentificationPerson.setIdCardNumber(employIndentyPerson.getIdCardNumber());
		EmployIdentificationPerson.setEmail(employIndentyPerson.getEmail());
		EmployIdentificationPerson.setCreateTime(new Date());
		EmployIdentificationPerson.setUpdateTime(new Date());

		int result = employIdentificationPersonMapper.insert(EmployIdentificationPerson);
		if (result != 0) {
			Users users = usersMapper.selectByPrimaryKey(userId);
			users.setGroupAuthority(2);// 设置为服务商
			int updateResult = usersMapper.updateByPrimaryKey(users);
			if (updateResult != 0) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Users getUserDetail(int userId) {
		Users users = usersMapper.selectByPrimaryKey(userId);
		return users;
	}

}
