package com.gravity.api.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.AdminEmployModel;
import com.gravity.api.common.view.model.AdminProviderDBModel;
import com.gravity.api.common.view.model.AdminProviderShowModel;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.UsersMapper;

@Service
public class AdminProviderServiceImpl implements AdminProviderServiceIF {

	public static final int PAGE_SIZE = 2;
	public static final int GroupType = 1; // 服务商

	public static final String shopUrl = "/shopInfo?shopId=";

	@Autowired
	ServiceProviderIdentificationCompanyMapper serviceProviderIdentificationCompanyMapper;

	@Autowired
	ServiceProviderIdentificationPersonMapper serviceProviderIdentificationPersonMapper;

	@Autowired
	UsersMapper usersMapper;

	@Override
	public List<AdminProviderShowModel> getProviderDetail(int page, String row, String queryKey) {

		List<AdminProviderShowModel> providerShowList = new ArrayList<AdminProviderShowModel>();
		int beginSize = (page - 1) * PAGE_SIZE;

		List<Users> userList = new ArrayList<Users>();

		if ("".equals(row)) {
			userList = usersMapper.selectUsersByPageAndGroupType(GroupType, beginSize, PAGE_SIZE);
		} else if ("phoneNumber".equals(row)) {
			if (!"".equals(queryKey)) {
				userList = usersMapper.selectUsersByPageAndGroupTypeAndPhone("%" + queryKey + "%", GroupType,
						beginSize, PAGE_SIZE);
			}
		} else if ("type".equals(row)) {
			if (!"".equals(queryKey)) {
				if ("企业".equals(queryKey)) {
					userList = usersMapper.selectUsersByPageAndGroupTypeAndTypeCp(1, GroupType, beginSize, PAGE_SIZE);
				} else if ("个人".equals(queryKey)) {
					userList = usersMapper.selectUsersByPageAndGroupTypeAndTypeCp(2, GroupType, beginSize, PAGE_SIZE);
				}
			}
		} else if ("name".equals(row)) {
			List<AdminProviderShowModel> providerList = new ArrayList<AdminProviderShowModel>();

			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andCompanyNameLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);
			if (companyList != null && companyList.size() != 0) {
				for (ServiceProviderIdentificationCompany providerCompany : companyList) {
					AdminProviderShowModel adminProviderShowModel = new AdminProviderShowModel();
					Users user = usersMapper.selectByPrimaryKey(providerCompany.getUserId());
					adminProviderShowModel.setPhoneNumber(user.getPhone());
					adminProviderShowModel.setProviderName(providerCompany.getCompanyName());
					adminProviderShowModel.setLocation(providerCompany.getCompanyLocation());
					adminProviderShowModel.setProviderType(user.getTypeCp());
					adminProviderShowModel.setShopUrl(shopUrl + user.getId());
					adminProviderShowModel.setUserId(user.getId());
					adminProviderShowModel.setIsApprove(providerCompany.getIsApprove());
					adminProviderShowModel.setProviderIdentificationId(providerCompany.getId());
					providerList.add(adminProviderShowModel);
				}
			}
			ServiceProviderIdentificationPersonExample personExample = new ServiceProviderIdentificationPersonExample();
			personExample.createCriteria().andNameLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(personExample);
			if (personList != null && personList.size() != 0) {
				for (ServiceProviderIdentificationPerson person : personList) {
					AdminProviderShowModel adminProviderShowModel = new AdminProviderShowModel();
					Users user = usersMapper.selectByPrimaryKey(person.getUserId());
					adminProviderShowModel.setPhoneNumber(user.getPhone());
					adminProviderShowModel.setProviderName(person.getName());
					adminProviderShowModel.setLocation(person.getLocation());
					adminProviderShowModel.setProviderType(user.getTypeCp());
					adminProviderShowModel.setShopUrl(shopUrl + user.getId());
					adminProviderShowModel.setUserId(user.getId());
					adminProviderShowModel.setIsApprove(person.getIsApprove());
					adminProviderShowModel.setProviderIdentificationId(person.getId());
					providerList.add(adminProviderShowModel);
				}
			}

			for (int i = beginSize; i < beginSize + PAGE_SIZE; i++) {
				if (i >= providerList.size()) {
					break;
				}
				providerShowList.add(providerList.get(i));
			}
			return providerShowList;
		} else if ("location".equals(row)) {

			List<AdminProviderShowModel> providerList = new ArrayList<AdminProviderShowModel>();

			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andCompanyLocationLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);
			System.out.println("companyList:" + companyList);
			if (companyList != null && companyList.size() != 0) {
				for (ServiceProviderIdentificationCompany providerCompany : companyList) {
					AdminProviderShowModel adminProviderShowModel = new AdminProviderShowModel();
					Users user = usersMapper.selectByPrimaryKey(providerCompany.getUserId());
					adminProviderShowModel.setPhoneNumber(user.getPhone());
					adminProviderShowModel.setProviderName(providerCompany.getCompanyName());
					adminProviderShowModel.setLocation(providerCompany.getCompanyLocation());
					adminProviderShowModel.setProviderType(user.getTypeCp());
					adminProviderShowModel.setShopUrl(shopUrl + user.getId());
					adminProviderShowModel.setUserId(user.getId());
					adminProviderShowModel.setIsApprove(providerCompany.getIsApprove());
					adminProviderShowModel.setProviderIdentificationId(providerCompany.getId());
					providerList.add(adminProviderShowModel);
				}
			}
			ServiceProviderIdentificationPersonExample personExample = new ServiceProviderIdentificationPersonExample();
			personExample.createCriteria().andLocationLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(personExample);
			if (personList != null && personList.size() != 0) {
				for (ServiceProviderIdentificationPerson person : personList) {
					AdminProviderShowModel adminProviderShowModel = new AdminProviderShowModel();
					Users user = usersMapper.selectByPrimaryKey(person.getUserId());
					adminProviderShowModel.setPhoneNumber(user.getPhone());
					adminProviderShowModel.setProviderName(person.getName());
					adminProviderShowModel.setLocation(person.getLocation());
					adminProviderShowModel.setProviderType(user.getTypeCp());
					adminProviderShowModel.setShopUrl(shopUrl + user.getId());
					adminProviderShowModel.setUserId(user.getId());
					adminProviderShowModel.setIsApprove(person.getIsApprove());
					adminProviderShowModel.setProviderIdentificationId(person.getId());
					providerList.add(adminProviderShowModel);
				}
			}

			for (int i = beginSize; i < beginSize + PAGE_SIZE; i++) {
				if (i >= providerList.size()) {
					break;
				}
				providerShowList.add(providerList.get(i));
			}
			return providerShowList;
		} else if ("isApprove".equals(row)) {

			int isApprove = 0;
			if ("0".equals(queryKey)) {
				isApprove = 0;
			} else {
				isApprove = 1;
			}

			List<AdminProviderShowModel> providerList = new ArrayList<AdminProviderShowModel>();

			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andIsApproveEqualTo(isApprove);
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);
			System.out.println("companyList:" + companyList);
			if (companyList != null && companyList.size() != 0) {
				for (ServiceProviderIdentificationCompany providerCompany : companyList) {
					AdminProviderShowModel adminProviderShowModel = new AdminProviderShowModel();
					Users user = usersMapper.selectByPrimaryKey(providerCompany.getUserId());
					adminProviderShowModel.setPhoneNumber(user.getPhone());
					adminProviderShowModel.setProviderName(providerCompany.getCompanyName());
					adminProviderShowModel.setLocation(providerCompany.getCompanyLocation());
					adminProviderShowModel.setProviderType(user.getTypeCp());
					adminProviderShowModel.setShopUrl(shopUrl + user.getId());
					adminProviderShowModel.setUserId(user.getId());
					adminProviderShowModel.setIsApprove(providerCompany.getIsApprove());
					adminProviderShowModel.setProviderIdentificationId(providerCompany.getId());
					providerList.add(adminProviderShowModel);
				}
			}
			ServiceProviderIdentificationPersonExample personExample = new ServiceProviderIdentificationPersonExample();
			personExample.createCriteria().andIsApproveEqualTo(isApprove);
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(personExample);
			if (personList != null && personList.size() != 0) {
				for (ServiceProviderIdentificationPerson person : personList) {
					AdminProviderShowModel adminProviderShowModel = new AdminProviderShowModel();
					Users user = usersMapper.selectByPrimaryKey(person.getUserId());
					adminProviderShowModel.setPhoneNumber(user.getPhone());
					adminProviderShowModel.setProviderName(person.getName());
					adminProviderShowModel.setLocation(person.getLocation());
					adminProviderShowModel.setProviderType(user.getTypeCp());
					adminProviderShowModel.setShopUrl(shopUrl + user.getId());
					adminProviderShowModel.setUserId(user.getId());
					adminProviderShowModel.setIsApprove(person.getIsApprove());
					adminProviderShowModel.setProviderIdentificationId(person.getId());
					providerList.add(adminProviderShowModel);
				}
			}

			for (int i = beginSize; i < beginSize + PAGE_SIZE; i++) {
				if (i >= providerList.size()) {
					break;
				}
				providerShowList.add(providerList.get(i));
			}
			return providerShowList;
		}

		for (Users user : userList) {
			AdminProviderShowModel adminProviderShowModel = new AdminProviderShowModel();
			adminProviderShowModel.setPhoneNumber(user.getPhone());
			adminProviderShowModel.setProviderType(user.getTypeCp());
			adminProviderShowModel.setShopUrl(shopUrl + user.getId());
			adminProviderShowModel.setUserId(user.getId());
			if (user.getTypeCp() == 1) { // 企业
				ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
				example.createCriteria().andUserIdEqualTo(user.getId());
				List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
						.selectByExample(example);
				if (companyList != null && companyList.size() != 0) {
					ServiceProviderIdentificationCompany serviceProviderIdentificationCompany = companyList.get(0);
					adminProviderShowModel.setProviderName(serviceProviderIdentificationCompany.getCompanyName());
					adminProviderShowModel.setLocation(serviceProviderIdentificationCompany.getCompanyLocation());
					adminProviderShowModel.setProviderIdentificationId(serviceProviderIdentificationCompany.getId());
					adminProviderShowModel.setIsApprove(serviceProviderIdentificationCompany.getIsApprove());
				}
			} else { // 个人
				ServiceProviderIdentificationPersonExample example = new ServiceProviderIdentificationPersonExample();
				example.createCriteria().andUserIdEqualTo(user.getId());
				List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
						.selectByExample(example);
				if (personList != null && personList.size() != 0) {
					ServiceProviderIdentificationPerson serviceProviderIdentificationPerson = personList.get(0);
					adminProviderShowModel.setProviderName(serviceProviderIdentificationPerson.getName());
					adminProviderShowModel.setLocation(serviceProviderIdentificationPerson.getLocation());
					adminProviderShowModel.setProviderIdentificationId(serviceProviderIdentificationPerson.getId());
					adminProviderShowModel.setIsApprove(serviceProviderIdentificationPerson.getIsApprove());
				}
			}
			providerShowList.add(adminProviderShowModel);
		}

		return providerShowList;
	}

	@Override
	public int getCountProvider(String row, String queryKey) {
		int totalResult = 0;

		if ("".equals(row)) {
			totalResult = usersMapper.userCountByGroupType(GroupType);
		} else if ("phoneNumber".equals(row)) {
			if (!"".equals(queryKey)) {
				totalResult = usersMapper.userCountByGroupTypeAndPhone("%" + queryKey + "%", GroupType);
			}
		} else if ("type".equals(row)) {
			if (!"".equals(queryKey)) {
				if ("企业".equals(queryKey)) {
					totalResult = usersMapper.userCountByGroupTypeAndTypeCp(1, GroupType);
				} else if ("个人".equals(queryKey)) {
					totalResult = usersMapper.userCountByGroupTypeAndTypeCp(2, GroupType);
				}
			}
		} else if ("name".equals(row)) {

			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andCompanyNameLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);

			ServiceProviderIdentificationPersonExample personExample = new ServiceProviderIdentificationPersonExample();
			personExample.createCriteria().andNameLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(personExample);

			totalResult = companyList.size() + personList.size();
		} else if ("location".equals(row)) {
			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andCompanyLocationLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);

			ServiceProviderIdentificationPersonExample personExample = new ServiceProviderIdentificationPersonExample();
			personExample.createCriteria().andLocationLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(personExample);

			totalResult = companyList.size() + personList.size();
		} else if ("isApprove".equals(row)) {

			int isApprove = 0;
			if ("0".equals(queryKey)) {
				isApprove = 0;
			} else {
				isApprove = 1;
			}

			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andIsApproveEqualTo(isApprove);
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);

			ServiceProviderIdentificationPersonExample personExample = new ServiceProviderIdentificationPersonExample();
			personExample.createCriteria().andIsApproveEqualTo(isApprove);
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(personExample);

			totalResult = companyList.size() + personList.size();
		}

		return totalResult;
	}

	@Override
	public boolean setApproveById(int priId, int userId) {

		int result = 0;

		Users user = usersMapper.selectByPrimaryKey(userId);

		if (user.getTypeCp() == 1) { //企业
			ServiceProviderIdentificationCompany company = serviceProviderIdentificationCompanyMapper
					.selectByPrimaryKey(priId);
			if (company.getUserId() == userId) {
				company.setIsApprove(1);
				company.setUpdateTime(new Date());
				result = serviceProviderIdentificationCompanyMapper.updateByPrimaryKey(company);
			}
		} else {
			ServiceProviderIdentificationPerson person = serviceProviderIdentificationPersonMapper
					.selectByPrimaryKey(priId);
			if (person.getUserId() == userId) {
				person.setIsApprove(1);
				person.setUpdateTime(new Date());
				result = serviceProviderIdentificationPersonMapper.updateByPrimaryKey(person);
			}
		}

		if (result != 0) {
			return true;
		}

		return false;
	}

}
