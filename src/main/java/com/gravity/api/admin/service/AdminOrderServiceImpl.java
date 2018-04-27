package com.gravity.api.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.AdminrOrderDBModel;
import com.gravity.api.common.view.model.AdminrOrderShowModel;
import com.gravity.api.common.view.model.ProviderOrderDBModel;
import com.gravity.api.common.view.model.ProviderOrderShowModel;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.repository.EmployIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.EmployIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.UserOrderMapper;
import com.gravity.common.support.mybatis.auto.repository.UsersMapper;

@Service
public class AdminOrderServiceImpl implements AdminOrderServiceIF {

	public static final int PAGE_SIZE = 2;

	@Value("#{filePath['image.publish.service.screenshot.url']}")
	private String publishServiceImageUrl;

	@Autowired
	UserOrderMapper userOrderMapper;

	@Autowired
	UsersMapper usersMapper;

	@Autowired
	EmployIdentificationCompanyMapper employIdentificationCompanyMapper;

	@Autowired
	EmployIdentificationPersonMapper employIdentificationPersonMapper;

	@Autowired
	ServiceProviderIdentificationCompanyMapper serviceProviderIdentificationCompanyMapper;

	@Autowired
	ServiceProviderIdentificationPersonMapper serviceProviderIdentificationPersonMapper;

	@Override
	public List<AdminrOrderShowModel> getAllOrderDetailByPage(int page, String row, String queryKey) {

		int beginSize = (page - 1) * PAGE_SIZE;

		List<AdminrOrderShowModel> orderList = new ArrayList<AdminrOrderShowModel>();
		List<AdminrOrderDBModel> orderDBList = new ArrayList<AdminrOrderDBModel>();

		if ("".equals(row)) {
			orderDBList = userOrderMapper.selectByPage(beginSize, PAGE_SIZE);
		} else if ("serviceTitle".equals(row)) {
			System.out.println("queryKey:" + queryKey);
			orderDBList = userOrderMapper.selectByPageAndServiceTitle("%" + queryKey + "%", beginSize, PAGE_SIZE);
		} else if ("status".equals(row)) {
			if ("0".equals(queryKey)) {
				orderDBList = userOrderMapper.selectByPageAndIsCancel("1", beginSize, PAGE_SIZE);
			} else {
				int orderStatus = 0;
				switch (queryKey) {
				case "1":
					orderStatus = 0;
					break;
				case "2":
					orderStatus = 1;
					break;
				case "3":
					orderStatus = 2;
					break;
				case "4":
					orderStatus = 3;
					break;
				}
				orderDBList = userOrderMapper.selectByPageAndStatus("" + orderStatus, beginSize, PAGE_SIZE);
			}

		}

		if (orderDBList != null && orderDBList.size() != 0) {
			for (AdminrOrderDBModel adminrOrderDB : orderDBList) {

				AdminrOrderShowModel adminrOrderShowModel = new AdminrOrderShowModel();
				adminrOrderShowModel.setCreateTime(adminrOrderDB.getCreateTime().substring(0, 10));
				adminrOrderShowModel.setOrderNo(adminrOrderDB.getOrderNo());
				adminrOrderShowModel.setServicePageUrl("/serviceDetail?serviceId=" + adminrOrderDB.getServiceId());
				int employId = adminrOrderDB.getServiceEmployId();
				Users user = usersMapper.selectByPrimaryKey(employId);
				if (user.getTypeCp() == 1) { // 企业
					EmployIdentificationCompanyExample example = new EmployIdentificationCompanyExample();
					example.createCriteria().andUserIdEqualTo(employId);
					List<EmployIdentificationCompany> companyList = employIdentificationCompanyMapper
							.selectByExample(example);
					if (companyList != null && companyList.size() != 0) {
						EmployIdentificationCompany employIdentificationCompany = companyList.get(0);
						adminrOrderShowModel.setServiceEmployName(employIdentificationCompany.getCompanyName());
					}
				} else {
					EmployIdentificationPersonExample example = new EmployIdentificationPersonExample();
					example.createCriteria().andUserIdEqualTo(employId);
					List<EmployIdentificationPerson> personList = employIdentificationPersonMapper
							.selectByExample(example);
					if (personList != null && personList.size() != 0) {
						EmployIdentificationPerson employIdentificationPerson = personList.get(0);
						adminrOrderShowModel.setServiceEmployName(employIdentificationPerson.getName());
					}
				}
				int providerId = adminrOrderDB.getServiceProviderId();
				user = usersMapper.selectByPrimaryKey(providerId);
				if (user.getTypeCp() == 1) { // 企业
					ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
					example.createCriteria().andUserIdEqualTo(providerId);
					List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
							.selectByExample(example);
					if (companyList != null && companyList.size() != 0) {
						ServiceProviderIdentificationCompany serviceProviderIdentificationCompany = companyList.get(0);
						adminrOrderShowModel
								.setServiceProviderName(serviceProviderIdentificationCompany.getCompanyName());
					}
				} else {
					ServiceProviderIdentificationPersonExample example = new ServiceProviderIdentificationPersonExample();
					example.createCriteria().andUserIdEqualTo(providerId);
					List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
							.selectByExample(example);
					if (personList != null && personList.size() != 0) {
						ServiceProviderIdentificationPerson serviceProviderIdentificationPerson = personList.get(0);
						adminrOrderShowModel.setServiceProviderName(serviceProviderIdentificationPerson.getName());
					}
				}

				adminrOrderShowModel.setContants(adminrOrderDB.getContants());
				adminrOrderShowModel.setServiceTitle(adminrOrderDB.getServiceTitle());
				String urls = adminrOrderDB.getServiceLogoUrl();
				String[] urlsArray = urls.split(",");
				adminrOrderShowModel.setServiceLogoUrl(publishServiceImageUrl + urlsArray[0]);
				adminrOrderShowModel.setDealPrice(adminrOrderDB.getDealPrice());
				adminrOrderShowModel.setIsCancel(adminrOrderDB.getIsCancel());
				adminrOrderShowModel.setIsAfterSale(adminrOrderDB.getIsAfterSale());
				adminrOrderShowModel.setOrderStatus(adminrOrderDB.getOrderStatus());

				String fileName = adminrOrderDB.getFileUrl();
				if (fileName == null || "".equals(fileName)) {
					adminrOrderShowModel.setFileUrl("");
				} else {
					adminrOrderShowModel.setFileUrl("/providerOrder/download?fileName=" + fileName);
				}
				adminrOrderShowModel.setRemark(adminrOrderDB.getRemark());
				adminrOrderShowModel.setToken(adminrOrderDB.getToken());
				orderList.add(adminrOrderShowModel);
			}

		}

		return orderList;
	}

	@Override
	public int getCountOrder(String row, String queryKey) {

		if ("".equals(row)) {
			return userOrderMapper.selectCount();
		} else if ("serviceTitle".equals(row)) {
			return userOrderMapper.selectCountByServiceTitle("%" + queryKey + "%");
		} else if ("status".equals(row)) {
			if ("0".equals(queryKey)) {
				return userOrderMapper.selectCountByIsCancel("" + 1);
			} else {
				int orderStatus = 0;
				switch (queryKey) {
				case "1":
					orderStatus = 0;
					break;
				case "2":
					orderStatus = 1;
					break;
				case "3":
					orderStatus = 2;
					break;
				case "4":
					orderStatus = 3;
					break;
				}
				return userOrderMapper.selectCountByStatus("" + orderStatus);
			}
		}

		return 0;
	}

}
