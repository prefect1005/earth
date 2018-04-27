package com.gravity.api.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SystemPropertyUtils;

import com.gravity.api.common.view.model.AdminEmployModel;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.repository.EmployIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.EmployIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.UsersMapper;

@Service
public class AdminEmployServiceImpl implements AdminEmployServiceIF {

	public static final int PAGE_SIZE = 2;
	public static final int GroupType = 2; // 雇佣方

	@Autowired
	UsersMapper usersMapper;

	@Autowired
	EmployIdentificationCompanyMapper employIdentificationCompanyMapper;

	@Autowired
	EmployIdentificationPersonMapper employIdentificationPersonMapper;

	@Override
	public List<AdminEmployModel> getEmployDetail(int page, String row, String queryKey) {

		List<AdminEmployModel> adminEmployList = new ArrayList<AdminEmployModel>();

		int beginSize = (page - 1) * PAGE_SIZE;
		
		List<Users> userList = new ArrayList<Users>();
		if ("".equals(row)) {
			userList = usersMapper.selectUsersByPageAndGroupType(GroupType, beginSize, PAGE_SIZE);
		} else if ("employ-number".equals(row)){
			if (!"".equals(queryKey)) {
				userList = usersMapper.selectUsersByPageAndGroupTypeAndPhone("%" + queryKey + "%", GroupType, beginSize, PAGE_SIZE);
			}
		} else if ("employ-type".equals(row)) {
			if (!"".equals(queryKey)) {
				if ("企业".equals(queryKey)) {
					userList = usersMapper.selectUsersByPageAndGroupTypeAndTypeCp(1, GroupType, beginSize, PAGE_SIZE);
				} else if ("个人".equals(queryKey)) {
					userList = usersMapper.selectUsersByPageAndGroupTypeAndTypeCp(2, GroupType, beginSize, PAGE_SIZE);
				}
			}
		} else if ("employ-name".equals(row)) {
			
			List<AdminEmployModel> adminEmployListAll = new ArrayList<AdminEmployModel>();
			
			EmployIdentificationCompanyExample example = new EmployIdentificationCompanyExample();
			example.createCriteria().andCompanyNameLike("%" + queryKey + "%");
			List<EmployIdentificationCompany> employCompanyList = employIdentificationCompanyMapper
					.selectByExample(example);
			if (employCompanyList != null && employCompanyList.size() != 0) {
				for (EmployIdentificationCompany eic : employCompanyList) {
					AdminEmployModel adminEmployModel = new AdminEmployModel();
					adminEmployModel.setEmployName(eic.getCompanyName());
					
					Users user = usersMapper.selectByPrimaryKey(eic.getUserId());
					
					adminEmployModel.setEmployType(user.getTypeCp());
					adminEmployModel.setPhoneNumber(user.getPhone());
					adminEmployListAll.add(adminEmployModel);
				}
			}
			
			EmployIdentificationPersonExample personExample = new EmployIdentificationPersonExample();
			personExample.createCriteria().andNameLike(queryKey);
			List<EmployIdentificationPerson> employPersonList = employIdentificationPersonMapper
					.selectByExample(personExample);
			if (employPersonList != null && employPersonList.size() != 0) {
				for (EmployIdentificationPerson eip : employPersonList) {
					AdminEmployModel adminEmployModel = new AdminEmployModel();
					adminEmployModel.setEmployName(eip.getName());
					
					Users user = usersMapper.selectByPrimaryKey(eip.getUserId());
					
					adminEmployModel.setEmployType(user.getTypeCp());
					adminEmployModel.setPhoneNumber(user.getPhone());
					adminEmployListAll.add(adminEmployModel);
					
				}
			}
			
			for (int i = beginSize; i < beginSize + PAGE_SIZE; i++) {
				if (i >= adminEmployListAll.size()) {
					break;
				}
				adminEmployList.add(adminEmployListAll.get(i));
			}
			
			return adminEmployList;
		}

		for (Users user : userList) {
			AdminEmployModel adminEmployModel = new AdminEmployModel();
			adminEmployModel.setPhoneNumber(user.getPhone());
			adminEmployModel.setEmployType(user.getTypeCp());
			if (user.getTypeCp() == 1) { // 企业
				EmployIdentificationCompanyExample example = new EmployIdentificationCompanyExample();
				example.createCriteria().andUserIdEqualTo(user.getId());
				List<EmployIdentificationCompany> employCompanyList = employIdentificationCompanyMapper
						.selectByExample(example);
				if (employCompanyList != null && employCompanyList.size() != 0) {
					EmployIdentificationCompany employIdentificationCompany = employCompanyList.get(0);
					adminEmployModel.setEmployName(employIdentificationCompany.getCompanyName());
				}
			} else { // 个人
				EmployIdentificationPersonExample example = new EmployIdentificationPersonExample();
				example.createCriteria().andUserIdEqualTo(user.getId());
				List<EmployIdentificationPerson> employPersonList = employIdentificationPersonMapper
						.selectByExample(example);
				if (employPersonList != null && employPersonList.size() != 0) {
					EmployIdentificationPerson employIdentificationPerson = employPersonList.get(0);
					adminEmployModel.setEmployName(employIdentificationPerson.getName());
				}
			}
			adminEmployList.add(adminEmployModel);
		}

		return adminEmployList;
	}

	@Override
	public int getTotalEmployCount(String row, String queryKey) {
		
		int totalResult = 0;
		
		if ("".equals(row)) {
			totalResult = usersMapper.userCountByGroupType(GroupType);
		} else if ("employ-number".equals(row)){
			if (!"".equals(queryKey)) {
				totalResult = usersMapper.userCountByGroupTypeAndPhone("%" + queryKey + "%", GroupType);
			}
		} else if ("employ-type".equals(row)) {
			if (!"".equals(queryKey)) {
				if ("企业".equals(queryKey)) {
					totalResult = usersMapper.userCountByGroupTypeAndTypeCp(1, GroupType);
				} else if ("个人".equals(queryKey)) {
					totalResult = usersMapper.userCountByGroupTypeAndTypeCp(2, GroupType);
				}
			}
		} else if ("employ-name".equals(row)) {
			
			EmployIdentificationCompanyExample example = new EmployIdentificationCompanyExample();
			example.createCriteria().andCompanyNameLike("%" + queryKey + "%");
			List<EmployIdentificationCompany> employCompanyList = employIdentificationCompanyMapper
					.selectByExample(example);
			
			EmployIdentificationPersonExample personExample = new EmployIdentificationPersonExample();
			personExample.createCriteria().andNameLike("%" + queryKey + "%");
			List<EmployIdentificationPerson> employPersonList = employIdentificationPersonMapper
					.selectByExample(personExample);
			
			totalResult = employCompanyList.size() + employPersonList.size();
		}

		return totalResult;
	}

}
