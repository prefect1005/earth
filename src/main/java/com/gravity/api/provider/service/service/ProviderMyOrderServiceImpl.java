package com.gravity.api.provider.service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.EmployOrderShowModel;
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
public class ProviderMyOrderServiceImpl implements ProviderMyOrderServiceIF {

	public static final int PAGE_SIZE = 2;

	@Value("#{filePath['image.publish.service.screenshot.url']}")
	private String publishServiceImageUrl;

	// @Value("#{filePath['file.order.data.url']}")
	// private String orderFileDownloadUrl;

	@Value("#{filePath['file.order.data']}")
	private String orderFileReal;

	@Autowired
	UserOrderMapper userOrderMapper;

	@Autowired
	UsersMapper usersMapper;

	@Autowired
	EmployIdentificationCompanyMapper employIdentificationCompanyMapper;

	@Autowired
	EmployIdentificationPersonMapper employIdentificationPersonMapper;

	@Override
	public List<ProviderOrderShowModel> getProviderOrderDetailByPage(int userId, int page, String row,
			String queryKey) {

		int beginSize = (page - 1) * PAGE_SIZE;
		List<ProviderOrderShowModel> providerOrderList = new ArrayList<ProviderOrderShowModel>();

		List<ProviderOrderDBModel> providerOrderDBList = new ArrayList<ProviderOrderDBModel>();

		if ("".equals(row)) {
			providerOrderDBList = userOrderMapper.selectByProviderIdAndPage(userId, beginSize, PAGE_SIZE);
		} else if ("serviceTitle".equals(row)) {
			System.out.println("queryKey:" + queryKey);
			providerOrderDBList = userOrderMapper.selectByProviderIdAndPageAndServiceTitle(userId, "%" + queryKey + "%",
					beginSize, PAGE_SIZE);
		} else if ("status".equals(row)) {
			if ("0".equals(queryKey)) {
				providerOrderDBList = userOrderMapper.selectByProviderIdAndPageAndIsCancel(userId, "1", beginSize,
						PAGE_SIZE);
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
				providerOrderDBList = userOrderMapper.selectByProviderIdAndPageAndStatus(userId, "" + orderStatus,
						beginSize, PAGE_SIZE);
			}

		}

		if (providerOrderDBList != null && providerOrderDBList.size() != 0) {
			for (ProviderOrderDBModel providerOrderDB : providerOrderDBList) {

				ProviderOrderShowModel providerOrderShowModel = new ProviderOrderShowModel();
				providerOrderShowModel.setCreateTime(providerOrderDB.getCreateTime().substring(0, 10));
				providerOrderShowModel.setOrderNo(providerOrderDB.getOrderNo());
				int employId = providerOrderDB.getServiceEmployId();
				Users user = usersMapper.selectByPrimaryKey(employId);
				if (user.getTypeCp() == 1) { // 企业
					EmployIdentificationCompanyExample example = new EmployIdentificationCompanyExample();
					example.createCriteria().andUserIdEqualTo(employId);
					List<EmployIdentificationCompany> companyList = employIdentificationCompanyMapper
							.selectByExample(example);
					System.out.println("companyList:" + companyList);
					if (companyList != null && companyList.size() != 0) {
						EmployIdentificationCompany employIdentificationCompany = companyList.get(0);
						providerOrderShowModel.setServiceEmployName(employIdentificationCompany.getCompanyName());
					}
				} else {
					EmployIdentificationPersonExample example = new EmployIdentificationPersonExample();
					example.createCriteria().andUserIdEqualTo(employId);
					List<EmployIdentificationPerson> personList = employIdentificationPersonMapper
							.selectByExample(example);
					if (personList != null && personList.size() != 0) {
						EmployIdentificationPerson employIdentificationPerson = personList.get(0);
						providerOrderShowModel.setServiceEmployName(employIdentificationPerson.getName());
					}
				}
				providerOrderShowModel.setContants(providerOrderDB.getContants());
				providerOrderShowModel.setServiceTitle(providerOrderDB.getServiceTitle());
				String urls = providerOrderDB.getServiceLogoUrl();
				String[] urlsArray = urls.split(",");
				providerOrderShowModel.setServiceLogoUrl(publishServiceImageUrl + urlsArray[0]);
				providerOrderShowModel.setDealPrice(providerOrderDB.getDealPrice());
				providerOrderShowModel.setIsCancel(providerOrderDB.getIsCancel());
				providerOrderShowModel.setIsAfterSale(providerOrderDB.getIsAfterSale());
				providerOrderShowModel.setOrderStatus(providerOrderDB.getOrderStatus());

				String fileName = providerOrderDB.getFileUrl();
				if (fileName == null || "".equals(fileName)) {
					providerOrderShowModel.setFileUrl("");
				} else {
					providerOrderShowModel.setFileUrl("/providerOrder/download?fileName=" + fileName);
				}
				providerOrderShowModel.setRemark(providerOrderDB.getRemark());
				providerOrderShowModel.setToken(providerOrderDB.getToken());
				providerOrderList.add(providerOrderShowModel);
			}

		}

		return providerOrderList;
	}

	@Override
	public int getCountProviderOrder(int userId, String row, String queryKey) {

		if ("".equals(row)) {
			return userOrderMapper.selectCountByProviderId(userId);
		} else if ("serviceTitle".equals(row)) {
			return userOrderMapper.selectCountByProviderIdAndServiceTitle(userId, "%" + queryKey + "%");
		} else if ("status".equals(row)) {
			if ("0".equals(queryKey)) {
				return userOrderMapper.selectCountByProviderIdAndIsCancel(userId, "" + 1);
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
				return userOrderMapper.selectCountByProviderIdAndStatus(userId, "" + orderStatus);
			}
		}

		return 0;
	}

}
