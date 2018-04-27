package com.gravity.api.admin.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.AdminWithDrawShowModel;
import com.gravity.common.support.mybatis.auto.model.ProviderWithDraw;
import com.gravity.common.support.mybatis.auto.model.ProviderWithDrawExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.repository.ProviderWithDrawMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.UsersMapper;

@Service
public class AdminWithDrawServiceImpl implements AdminWithDrawServiceIF {

	public static final int PAGE_SIZE = 2;

	@Autowired
	ProviderWithDrawMapper providerWithDrawMapper;

	@Autowired
	UsersMapper usersMapper;

	@Autowired
	ServiceProviderIdentificationCompanyMapper serviceProviderIdentificationCompanyMapper;

	@Autowired
	ServiceProviderIdentificationPersonMapper serviceProviderIdentificationPersonMapper;

	@Override
	public List<AdminWithDrawShowModel> getAdminWithDrawList(int page, String row, String queryKey) {

		int beginSize = (page - 1) * PAGE_SIZE;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<AdminWithDrawShowModel> withDrawList = new ArrayList<AdminWithDrawShowModel>();

		List<ProviderWithDraw> providerWithDrawList = new ArrayList<ProviderWithDraw>();

		if ("".equals(row)) {
			providerWithDrawList = providerWithDrawMapper.selectByPage(beginSize, PAGE_SIZE);

			if (CollectionUtils.isNotEmpty(providerWithDrawList)) {
				for (ProviderWithDraw providerWithDraw : providerWithDrawList) {
					int providerId = providerWithDraw.getProviderId();
					Users user = usersMapper.selectByPrimaryKey(providerId);
					if (user.getTypeCp() == 1) { //企业
						ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
						example.createCriteria().andUserIdEqualTo(providerId);
						List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
								.selectByExample(example);
						if (CollectionUtils.isNotEmpty(companyList)) {
							ServiceProviderIdentificationCompany company = companyList.get(0);
							AdminWithDrawShowModel adminWithDrawShow = new AdminWithDrawShowModel();
							adminWithDrawShow.setWithDrawId(providerWithDraw.getId());
							adminWithDrawShow.setProviceShopUrl("/shopInfo?shopId=" + providerId);
							adminWithDrawShow.setProviderName(company.getCompanyName());
							adminWithDrawShow.setProviderNumber(company.getPhoneNumber());
							adminWithDrawShow.setProviderBankIdCard(company.getCompanyBankAccount());
							adminWithDrawShow.setAmount(providerWithDraw.getAmount());
							adminWithDrawShow.setCreateTime(sdf.format(providerWithDraw.getCreateTime()));
							adminWithDrawShow.setStatus(providerWithDraw.getIsSuccess());
							withDrawList.add(adminWithDrawShow);
						}
					} else {
						ServiceProviderIdentificationPersonExample example = new ServiceProviderIdentificationPersonExample();
						example.createCriteria().andUserIdEqualTo(providerId);
						List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
								.selectByExample(example);
						if (CollectionUtils.isNotEmpty(personList)) {
							ServiceProviderIdentificationPerson person = personList.get(0);
							AdminWithDrawShowModel adminWithDrawShow = new AdminWithDrawShowModel();
							adminWithDrawShow.setWithDrawId(providerWithDraw.getId());
							adminWithDrawShow.setProviceShopUrl("/shopInfo?shopId=" + providerId);
							adminWithDrawShow.setProviderName(person.getName());
							adminWithDrawShow.setProviderNumber(person.getPhoneNumber());
							adminWithDrawShow.setProviderBankIdCard(person.getBankCardId());
							adminWithDrawShow.setAmount(providerWithDraw.getAmount());
							adminWithDrawShow.setCreateTime(sdf.format(providerWithDraw.getCreateTime()));
							adminWithDrawShow.setStatus(providerWithDraw.getIsSuccess());
							withDrawList.add(adminWithDrawShow);
						}
					}
				}
			}
		} else if ("name".equals(row)) {

			Set<Integer> userIdSet = new HashSet<Integer>();

			List<AdminWithDrawShowModel> tempResultWithDrawList = new ArrayList<AdminWithDrawShowModel>();

			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andCompanyNameLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);
			if (CollectionUtils.isNotEmpty(companyList)) {
				for (ServiceProviderIdentificationCompany company : companyList) {
					int proviceUserId = company.getUserId();
					userIdSet.add(proviceUserId);
					ProviderWithDrawExample withDrawExample = new ProviderWithDrawExample();
					withDrawExample.createCriteria().andProviderIdEqualTo(proviceUserId);
					List<ProviderWithDraw> tempWithDrawList = providerWithDrawMapper.selectByExample(withDrawExample);
					if (CollectionUtils.isNotEmpty(tempWithDrawList)) {
						for (ProviderWithDraw providerWithDraw : tempWithDrawList) {
							AdminWithDrawShowModel adminWithDrawShow = new AdminWithDrawShowModel();
							adminWithDrawShow.setWithDrawId(providerWithDraw.getId());
							adminWithDrawShow.setProviceShopUrl("/shopInfo?shopId=" + proviceUserId);
							adminWithDrawShow.setProviderName(company.getCompanyName());
							adminWithDrawShow.setProviderNumber(company.getPhoneNumber());
							adminWithDrawShow.setProviderBankIdCard(company.getCompanyBankAccount());
							adminWithDrawShow.setAmount(providerWithDraw.getAmount());
							adminWithDrawShow.setCreateTime(sdf.format(providerWithDraw.getCreateTime()));
							adminWithDrawShow.setStatus(providerWithDraw.getIsSuccess());
							tempResultWithDrawList.add(adminWithDrawShow);
						}
					}
				}
			}

			ServiceProviderIdentificationPersonExample personExample = new ServiceProviderIdentificationPersonExample();
			personExample.createCriteria().andNameLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(personExample);
			if (CollectionUtils.isNotEmpty(personList)) {
				for (ServiceProviderIdentificationPerson person : personList) {
					int proviceUserId = person.getUserId();
					if (userIdSet.contains(proviceUserId)) {
						continue;
					}
					ProviderWithDrawExample withDrawExample = new ProviderWithDrawExample();
					withDrawExample.createCriteria().andProviderIdEqualTo(proviceUserId);
					List<ProviderWithDraw> tempWithDrawList = providerWithDrawMapper.selectByExample(withDrawExample);
					if (CollectionUtils.isNotEmpty(tempWithDrawList)) {
						for (ProviderWithDraw providerWithDraw : tempWithDrawList) {
							AdminWithDrawShowModel adminWithDrawShow = new AdminWithDrawShowModel();
							adminWithDrawShow.setWithDrawId(providerWithDraw.getId());
							adminWithDrawShow.setProviceShopUrl("/shopInfo?shopId=" + proviceUserId);
							adminWithDrawShow.setProviderName(person.getName());
							adminWithDrawShow.setProviderNumber(person.getPhoneNumber());
							adminWithDrawShow.setProviderBankIdCard(person.getBankCardId());
							adminWithDrawShow.setAmount(providerWithDraw.getAmount());
							adminWithDrawShow.setCreateTime(sdf.format(providerWithDraw.getCreateTime()));
							adminWithDrawShow.setStatus(providerWithDraw.getIsSuccess());
							tempResultWithDrawList.add(adminWithDrawShow);
						}
					}
				}
			}

			for (int i = beginSize; i < beginSize + PAGE_SIZE; i++) {
				if (i >= tempResultWithDrawList.size()) {
					break;
				}
				withDrawList.add(tempResultWithDrawList.get(i));
			}
		} else if ("phone".equals(row)) {
			Set<Integer> userIdSet = new HashSet<Integer>();
			List<AdminWithDrawShowModel> tempResultWithDrawList = new ArrayList<AdminWithDrawShowModel>();

			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andPhoneNumberLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);
			if (CollectionUtils.isNotEmpty(companyList)) {
				for (ServiceProviderIdentificationCompany company : companyList) {
					int proviceUserId = company.getUserId();
					userIdSet.add(proviceUserId);
					ProviderWithDrawExample withDrawExample = new ProviderWithDrawExample();
					withDrawExample.createCriteria().andProviderIdEqualTo(proviceUserId);
					List<ProviderWithDraw> tempWithDrawList = providerWithDrawMapper.selectByExample(withDrawExample);
					if (CollectionUtils.isNotEmpty(tempWithDrawList)) {
						for (ProviderWithDraw providerWithDraw : tempWithDrawList) {
							AdminWithDrawShowModel adminWithDrawShow = new AdminWithDrawShowModel();
							adminWithDrawShow.setWithDrawId(providerWithDraw.getId());
							adminWithDrawShow.setProviceShopUrl("/shopInfo?shopId=" + proviceUserId);
							adminWithDrawShow.setProviderName(company.getCompanyName());
							adminWithDrawShow.setProviderNumber(company.getPhoneNumber());
							adminWithDrawShow.setProviderBankIdCard(company.getCompanyBankAccount());
							adminWithDrawShow.setAmount(providerWithDraw.getAmount());
							adminWithDrawShow.setCreateTime(sdf.format(providerWithDraw.getCreateTime()));
							adminWithDrawShow.setStatus(providerWithDraw.getIsSuccess());
							tempResultWithDrawList.add(adminWithDrawShow);
						}
					}
				}
			}

			ServiceProviderIdentificationPersonExample personExample = new ServiceProviderIdentificationPersonExample();
			personExample.createCriteria().andPhoneNumberLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(personExample);
			if (CollectionUtils.isNotEmpty(personList)) {
				for (ServiceProviderIdentificationPerson person : personList) {
					int proviceUserId = person.getUserId();
					if (userIdSet.contains(proviceUserId)) {
						continue;
					}
					ProviderWithDrawExample withDrawExample = new ProviderWithDrawExample();
					withDrawExample.createCriteria().andProviderIdEqualTo(proviceUserId);
					List<ProviderWithDraw> tempWithDrawList = providerWithDrawMapper.selectByExample(withDrawExample);
					if (CollectionUtils.isNotEmpty(tempWithDrawList)) {
						for (ProviderWithDraw providerWithDraw : tempWithDrawList) {
							AdminWithDrawShowModel adminWithDrawShow = new AdminWithDrawShowModel();
							adminWithDrawShow.setWithDrawId(providerWithDraw.getId());
							adminWithDrawShow.setProviceShopUrl("/shopInfo?shopId=" + proviceUserId);
							adminWithDrawShow.setProviderName(person.getName());
							adminWithDrawShow.setProviderNumber(person.getPhoneNumber());
							adminWithDrawShow.setProviderBankIdCard(person.getBankCardId());
							adminWithDrawShow.setAmount(providerWithDraw.getAmount());
							adminWithDrawShow.setCreateTime(sdf.format(providerWithDraw.getCreateTime()));
							adminWithDrawShow.setStatus(providerWithDraw.getIsSuccess());
							tempResultWithDrawList.add(adminWithDrawShow);
						}
					}
				}
			}

			for (int i = beginSize; i < beginSize + PAGE_SIZE; i++) {
				if (i >= tempResultWithDrawList.size()) {
					break;
				}
				withDrawList.add(tempResultWithDrawList.get(i));
			}
		} else if ("bankId".equals(row)) {
			Set<Integer> userIdSet = new HashSet<Integer>();
			List<AdminWithDrawShowModel> tempResultWithDrawList = new ArrayList<AdminWithDrawShowModel>();

			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andCompanyBankAccountLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);
			if (CollectionUtils.isNotEmpty(companyList)) {
				for (ServiceProviderIdentificationCompany company : companyList) {
					int proviceUserId = company.getUserId();
					userIdSet.add(proviceUserId);
					ProviderWithDrawExample withDrawExample = new ProviderWithDrawExample();
					withDrawExample.createCriteria().andProviderIdEqualTo(proviceUserId);
					List<ProviderWithDraw> tempWithDrawList = providerWithDrawMapper.selectByExample(withDrawExample);
					if (CollectionUtils.isNotEmpty(tempWithDrawList)) {
						for (ProviderWithDraw providerWithDraw : tempWithDrawList) {
							AdminWithDrawShowModel adminWithDrawShow = new AdminWithDrawShowModel();
							adminWithDrawShow.setWithDrawId(providerWithDraw.getId());
							adminWithDrawShow.setProviceShopUrl("/shopInfo?shopId=" + proviceUserId);
							adminWithDrawShow.setProviderName(company.getCompanyName());
							adminWithDrawShow.setProviderNumber(company.getPhoneNumber());
							adminWithDrawShow.setProviderBankIdCard(company.getCompanyBankAccount());
							adminWithDrawShow.setAmount(providerWithDraw.getAmount());
							adminWithDrawShow.setCreateTime(sdf.format(providerWithDraw.getCreateTime()));
							adminWithDrawShow.setStatus(providerWithDraw.getIsSuccess());
							tempResultWithDrawList.add(adminWithDrawShow);
						}
					}
				}
			}

			ServiceProviderIdentificationPersonExample personExample = new ServiceProviderIdentificationPersonExample();
			personExample.createCriteria().andBankCardIdLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(personExample);
			if (CollectionUtils.isNotEmpty(personList)) {
				for (ServiceProviderIdentificationPerson person : personList) {
					int proviceUserId = person.getUserId();
					if (userIdSet.contains(proviceUserId)) {
						continue;
					}
					ProviderWithDrawExample withDrawExample = new ProviderWithDrawExample();
					withDrawExample.createCriteria().andProviderIdEqualTo(proviceUserId);
					List<ProviderWithDraw> tempWithDrawList = providerWithDrawMapper.selectByExample(withDrawExample);
					if (CollectionUtils.isNotEmpty(tempWithDrawList)) {
						for (ProviderWithDraw providerWithDraw : tempWithDrawList) {
							AdminWithDrawShowModel adminWithDrawShow = new AdminWithDrawShowModel();
							adminWithDrawShow.setWithDrawId(providerWithDraw.getId());
							adminWithDrawShow.setProviceShopUrl("/shopInfo?shopId=" + proviceUserId);
							adminWithDrawShow.setProviderName(person.getName());
							adminWithDrawShow.setProviderNumber(person.getPhoneNumber());
							adminWithDrawShow.setProviderBankIdCard(person.getBankCardId());
							adminWithDrawShow.setAmount(providerWithDraw.getAmount());
							adminWithDrawShow.setCreateTime(sdf.format(providerWithDraw.getCreateTime()));
							adminWithDrawShow.setStatus(providerWithDraw.getIsSuccess());
							tempResultWithDrawList.add(adminWithDrawShow);
						}
					}
				}
			}

			for (int i = beginSize; i < beginSize + PAGE_SIZE; i++) {
				if (i >= tempResultWithDrawList.size()) {
					break;
				}
				withDrawList.add(tempResultWithDrawList.get(i));
			}
		} else if ("status".equals(row)) {
			int status = 0;
			if ("已转账".equals(queryKey)) {
				status = 1;
			}
			providerWithDrawList = providerWithDrawMapper.selectByStatusAndPage(status, page, PAGE_SIZE);

			if (CollectionUtils.isNotEmpty(providerWithDrawList)) {
				for (ProviderWithDraw providerWithDraw : providerWithDrawList) {
					int providerId = providerWithDraw.getProviderId();
					Users user = usersMapper.selectByPrimaryKey(providerId);
					if (user.getTypeCp() == 1) { //企业
						ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
						example.createCriteria().andUserIdEqualTo(providerId);
						List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
								.selectByExample(example);
						if (CollectionUtils.isNotEmpty(companyList)) {
							ServiceProviderIdentificationCompany company = companyList.get(0);
							AdminWithDrawShowModel adminWithDrawShow = new AdminWithDrawShowModel();
							adminWithDrawShow.setWithDrawId(providerWithDraw.getId());
							adminWithDrawShow.setProviceShopUrl("/shopInfo?shopId=" + providerId);
							adminWithDrawShow.setProviderName(company.getCompanyName());
							adminWithDrawShow.setProviderNumber(company.getPhoneNumber());
							adminWithDrawShow.setProviderBankIdCard(company.getCompanyBankAccount());
							adminWithDrawShow.setAmount(providerWithDraw.getAmount());
							adminWithDrawShow.setCreateTime(sdf.format(providerWithDraw.getCreateTime()));
							adminWithDrawShow.setStatus(providerWithDraw.getIsSuccess());
							withDrawList.add(adminWithDrawShow);
						}
					} else {
						ServiceProviderIdentificationPersonExample example = new ServiceProviderIdentificationPersonExample();
						example.createCriteria().andUserIdEqualTo(providerId);
						List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
								.selectByExample(example);
						if (CollectionUtils.isNotEmpty(personList)) {
							ServiceProviderIdentificationPerson person = personList.get(0);
							AdminWithDrawShowModel adminWithDrawShow = new AdminWithDrawShowModel();
							adminWithDrawShow.setWithDrawId(providerWithDraw.getId());
							adminWithDrawShow.setProviceShopUrl("/shopInfo?shopId=" + providerId);
							adminWithDrawShow.setProviderName(person.getName());
							adminWithDrawShow.setProviderNumber(person.getPhoneNumber());
							adminWithDrawShow.setProviderBankIdCard(person.getBankCardId());
							adminWithDrawShow.setAmount(providerWithDraw.getAmount());
							adminWithDrawShow.setCreateTime(sdf.format(providerWithDraw.getCreateTime()));
							adminWithDrawShow.setStatus(providerWithDraw.getIsSuccess());
							withDrawList.add(adminWithDrawShow);
						}
					}
				}
			}
		}

		return withDrawList;
	}

	@Override
	public int getCountAdminWithDraw(String row, String queryKey) {

		int count = 0;

		if ("".equals(row)) {
			count = providerWithDrawMapper.allCount();
		} else if ("name".equals(row)) {
			Set<Integer> userIdSet = new HashSet<Integer>();
			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andCompanyNameLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);
			if (CollectionUtils.isNotEmpty(companyList)) {
				for (ServiceProviderIdentificationCompany company : companyList) {
					int proviceUserId = company.getUserId();
					userIdSet.add(proviceUserId);
					count += providerWithDrawMapper.allCountByUserId(proviceUserId);
				}
			}

			ServiceProviderIdentificationPersonExample personExample = new ServiceProviderIdentificationPersonExample();
			personExample.createCriteria().andNameLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(personExample);
			if (CollectionUtils.isNotEmpty(personList)) {
				for (ServiceProviderIdentificationPerson person : personList) {
					int proviceUserId = person.getUserId();
					if (userIdSet.contains(proviceUserId)) {
						continue;
					}
					count += providerWithDrawMapper.allCountByUserId(proviceUserId);
				}
			}
		} else if ("phone".equals(row)) {
			Set<Integer> userIdSet = new HashSet<Integer>();
			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andPhoneNumberLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);
			if (CollectionUtils.isNotEmpty(companyList)) {
				for (ServiceProviderIdentificationCompany company : companyList) {
					int proviceUserId = company.getUserId();
					userIdSet.add(proviceUserId);
					count += providerWithDrawMapper.allCountByUserId(proviceUserId);
				}
			}

			ServiceProviderIdentificationPersonExample personExample = new ServiceProviderIdentificationPersonExample();
			personExample.createCriteria().andPhoneNumberLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(personExample);
			if (CollectionUtils.isNotEmpty(personList)) {
				for (ServiceProviderIdentificationPerson person : personList) {
					int proviceUserId = person.getUserId();
					if (userIdSet.contains(proviceUserId)) {
						continue;
					}
					count += providerWithDrawMapper.allCountByUserId(proviceUserId);
				}
			}
		} else if ("bankId".equals(row)) {
			Set<Integer> userIdSet = new HashSet<Integer>();
			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andCompanyBankAccountLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);
			if (CollectionUtils.isNotEmpty(companyList)) {
				for (ServiceProviderIdentificationCompany company : companyList) {
					int proviceUserId = company.getUserId();
					userIdSet.add(proviceUserId);
					count += providerWithDrawMapper.allCountByUserId(proviceUserId);
				}
			}

			ServiceProviderIdentificationPersonExample personExample = new ServiceProviderIdentificationPersonExample();
			personExample.createCriteria().andBankCardIdLike("%" + queryKey + "%");
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(personExample);
			if (CollectionUtils.isNotEmpty(personList)) {
				for (ServiceProviderIdentificationPerson person : personList) {
					int proviceUserId = person.getUserId();
					if (userIdSet.contains(proviceUserId)) {
						continue;
					}
					count += providerWithDrawMapper.allCountByUserId(proviceUserId);
				}
			}
		} else if ("status".equals(row)) {
			int status = 0;
			if ("已转账".equals(queryKey)) {
				status = 1;
			}
			count = providerWithDrawMapper.allCountByStatus(status);
		}

		return count;
	}

	@Override
	public boolean updateWithDrawStatus(int withDrawId) {

		ProviderWithDraw providerWithDraw = providerWithDrawMapper.selectByPrimaryKey(withDrawId);
		providerWithDraw.setIsSuccess(1);
		providerWithDraw.setSuccessTime(new Date());
		int result = providerWithDrawMapper.updateByPrimaryKey(providerWithDraw);
		if (result != 0) {
			return true;
		}
		return false;
	}

}
