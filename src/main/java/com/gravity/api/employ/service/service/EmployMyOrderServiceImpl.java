package com.gravity.api.employ.service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gravity.api.common.view.model.EmployConfimeServiceModel;
import com.gravity.api.common.view.model.EmployEvaluateModel;
import com.gravity.api.common.view.model.EmployOrderDBModel;
import com.gravity.api.common.view.model.EmployOrderShowModel;
import com.gravity.common.support.mybatis.auto.model.Evaluate;
import com.gravity.common.support.mybatis.auto.model.ProviderAccount;
import com.gravity.common.support.mybatis.auto.model.ProviderAccountExample;
import com.gravity.common.support.mybatis.auto.model.ProviderIncome;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.UserOrder;
import com.gravity.common.support.mybatis.auto.model.UserOrderExample;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.repository.EvaluateMapper;
import com.gravity.common.support.mybatis.auto.repository.ProviderAccountMapper;
import com.gravity.common.support.mybatis.auto.repository.ProviderIncomeMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.UserOrderMapper;
import com.gravity.common.support.mybatis.auto.repository.UsersMapper;

@Service
public class EmployMyOrderServiceImpl implements EmployMyOrderServiceIF {

	public static final int PAGE_SIZE = 2;

	@Value("#{filePath['image.publish.service.screenshot.url']}")
	private String publishServiceImageUrl;

	@Autowired
	UsersMapper usersMapper;

	@Autowired
	UserOrderMapper userOrderMapper;

	@Autowired
	ServiceProviderIdentificationCompanyMapper serviceProviderIdentificationCompanyMapper;

	@Autowired
	ServiceProviderIdentificationPersonMapper serviceProviderIdentificationPersonMapper;

	@Autowired
	EvaluateMapper evaluateMapper;

	@Autowired
	ProviderIncomeMapper providerIncomeMapper;

	@Autowired
	ProviderAccountMapper providerAccountMapper;

	@Override
	public List<EmployOrderShowModel> getEmployOrderDetailByPage(int userId, int typeCp, int page, String row,
			String queryKey) {

		int beginSize = (page - 1) * PAGE_SIZE;

		List<EmployOrderShowModel> employOrderShowList = new ArrayList<EmployOrderShowModel>();

		if (typeCp == 1) { // 企业
			if ("".equals(row)) {
				employOrderShowList = userOrderMapper.selectByEmployIdAndPageCompany(userId, beginSize, PAGE_SIZE);
			} else if ("serviceTitle".equals(row)) {
				System.out.println("queryKey:" + queryKey);
				employOrderShowList = userOrderMapper.selectByEmployIdAndPageAndServiceTitleCompany(userId, "%"
						+ queryKey + "%", beginSize, PAGE_SIZE);
			} else if ("status".equals(row)) {
				if ("0".equals(queryKey)) {
					employOrderShowList = userOrderMapper.selectByEmployIdAndPageAndIsCancelCompany(userId, "1",
							beginSize, PAGE_SIZE);
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
					employOrderShowList = userOrderMapper.selectByEmployIdAndPageAndStatusCompany(userId, ""
							+ orderStatus, beginSize, PAGE_SIZE);
				}

			}
		} else { // 个人
			if ("".equals(row)) {
				employOrderShowList = userOrderMapper.selectByEmployIdAndPagePerson(userId, beginSize, PAGE_SIZE);
			} else if ("serviceTitle".equals(row)) {
				employOrderShowList = userOrderMapper.selectByEmployIdAndPageAndServiceTitlePerson(userId, "%"
						+ queryKey + "%", beginSize, PAGE_SIZE);
			} else if ("status".equals(row)) {
				if ("0".equals(queryKey)) {
					employOrderShowList = userOrderMapper.selectByEmployIdAndPageAndIsCancelPerson(userId, "1",
							beginSize, PAGE_SIZE);
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
					employOrderShowList = userOrderMapper.selectByEmployIdAndPageAndStatusPerson(userId, ""
							+ orderStatus, beginSize, PAGE_SIZE);
				}

			}
		}

		if (employOrderShowList != null && employOrderShowList.size() != 0) {
			for (int i = 0; i < employOrderShowList.size(); i++) {
				EmployOrderShowModel employOrderShow = employOrderShowList.get(i);
				employOrderShow.setCreateTime(employOrderShow.getCreateTime().substring(0, 10));
				String urls = employOrderShow.getServiceLogoUrl();
				String[] urlsArray = urls.split(",");
				employOrderShow.setServiceLogoUrl(publishServiceImageUrl + urlsArray[0]);
				employOrderShowList.set(i, employOrderShow);
			}
		}

		return employOrderShowList;
	}

	@Override
	public int getEmployOrderCount(int userId, String row, String queryKey) {

		if ("".equals(row)) {
			return userOrderMapper.selectCountByEmployId(userId);
		} else { // 有条件查询
			if ("serviceTitle".equals(row)) {
				return userOrderMapper.selectCountByEmployIdAndServiceTitle(userId, "%" + queryKey + "%");
			} else if ("status".equals(row)) {
				if ("0".equals(queryKey)) {
					return userOrderMapper.selectCountByEmployIdAndIsCancel(userId, "" + 1);
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
					return userOrderMapper.selectCountByEmployIdAndStatus(userId, "" + orderStatus);
				}
			}
		}
		return 0;
	}

	@Override
	public List<EmployOrderShowModel> getEmployOrderDetailByPage(int userId, int page, String row, String queryKey) {

		int beginSize = (page - 1) * PAGE_SIZE;

		List<EmployOrderShowModel> employOrderShowList = new ArrayList<EmployOrderShowModel>();

		List<EmployOrderDBModel> employOrderDBList = new ArrayList<EmployOrderDBModel>();

		if ("".equals(row)) {
			employOrderDBList = userOrderMapper.selectByEmployIdAndPage(userId, beginSize, PAGE_SIZE);
		} else if ("serviceTitle".equals(row)) {
			System.out.println("queryKey:" + queryKey);
			employOrderDBList = userOrderMapper.selectByEmployIdAndPageAndServiceTitle(userId, "%" + queryKey + "%",
					beginSize, PAGE_SIZE);
		} else if ("status".equals(row)) {
			if ("0".equals(queryKey)) {
				employOrderDBList = userOrderMapper.selectByEmployIdAndPageAndIsCancel(userId, "1", beginSize,
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
				employOrderDBList = userOrderMapper.selectByEmployIdAndPageAndStatus(userId, "" + orderStatus,
						beginSize, PAGE_SIZE);
			}

		}

		if (employOrderDBList != null && employOrderDBList.size() != 0) {
			for (int i = 0; i < employOrderDBList.size(); i++) {
				EmployOrderShowModel employOrderShowModel = new EmployOrderShowModel();
				EmployOrderDBModel employOrderDB = employOrderDBList.get(i);
				employOrderShowModel.setCreateTime(employOrderDB.getCreateTime().substring(0, 10));
				employOrderShowModel.setOrderNo(employOrderDB.getOrderNo());
				int providerId = employOrderDB.getServiceProviderId();
				employOrderShowModel.setProviderId(providerId);
				employOrderShowModel.setServiceId(employOrderDB.getServiceId());
				Users user = usersMapper.selectByPrimaryKey(providerId);
				if (user.getTypeCp() == 1) { // 企业
					ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
					example.createCriteria().andUserIdEqualTo(providerId);
					List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
							.selectByExample(example);
					if (companyList != null && companyList.size() != 0) {
						ServiceProviderIdentificationCompany serviceProviderIdentificationCompany = companyList.get(0);
						employOrderShowModel.setServiceProviderName(serviceProviderIdentificationCompany
								.getCompanyName());
					}
				} else {
					ServiceProviderIdentificationPersonExample example = new ServiceProviderIdentificationPersonExample();
					example.createCriteria().andUserIdEqualTo(providerId);
					List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
							.selectByExample(example);
					if (personList != null && personList.size() != 0) {
						ServiceProviderIdentificationPerson serviceProviderIdentificationPerson = personList.get(0);
						employOrderShowModel.setServiceProviderName(serviceProviderIdentificationPerson.getName());
					}
				}

				employOrderShowModel.setServiceTitle(employOrderDB.getServiceTitle());
				String urls = employOrderDB.getServiceLogoUrl();
				String[] urlsArray = urls.split(",");
				employOrderShowModel.setServiceLogoUrl(publishServiceImageUrl + urlsArray[0]);
				employOrderShowModel.setDealPrice(employOrderDB.getDealPrice());
				employOrderShowModel.setIsCancel(employOrderDB.getIsCancel());
				employOrderShowModel.setIsAfterSale(employOrderDB.getIsAfterSale());
				employOrderShowModel.setOrderStatus(employOrderDB.getOrderStatus());
				employOrderShowModel.setToken(employOrderDB.getToken());
				employOrderShowList.add(employOrderShowModel);
			}
		}

		return employOrderShowList;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public String saveEvaluateDetail(EmployEvaluateModel employEvaluate) {

		UserOrderExample example = new UserOrderExample();
		example.createCriteria().andIdNoEqualTo(employEvaluate.getIdNo())
				.andServiceIdEqualTo(employEvaluate.getServiceId())
				.andProviderUserIdEqualTo(employEvaluate.getProviceId()).andTokenEqualTo(employEvaluate.getToken());
		List<UserOrder> userOrderList = userOrderMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(userOrderList)) {
			return "数据参数不正确";
		}

		UserOrder userOrder = userOrderList.get(0);

		if (userOrder.getOrderStatus() != 2) {
			return "不合法的请求";
		}

		userOrder.setOrderStatus(3);//设置状态为订单完成
		userOrder.setFinishTime(new Date());
		int result = userOrderMapper.updateByPrimaryKey(userOrder);

		if (result != 0) {
			Evaluate evaluate = new Evaluate();
			evaluate.setIdNo(employEvaluate.getIdNo());
			evaluate.setServiceId(employEvaluate.getServiceId());
			evaluate.setProviderUserId(employEvaluate.getProviceId());
			evaluate.setEvaluateLevel(employEvaluate.getEvaluateLevel());
			evaluate.setEvaluateContent(employEvaluate.getEvaluateContent());
			evaluate.setCreateTime(new Date());
			result = evaluateMapper.insert(evaluate);
			if (result != 0) {
				return "评价成功";
			} else {
				return "网络错误，请重试";
			}
		}
		return "";
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public String confimeServiceFinish(EmployConfimeServiceModel employConfimeService, int userId) {

		UserOrderExample example = new UserOrderExample();
		example.createCriteria().andIdNoEqualTo(employConfimeService.getIdNo())
				.andProviderUserIdEqualTo(employConfimeService.getProviceId())
				.andTokenEqualTo(employConfimeService.getToken());
		List<UserOrder> userOrderList = userOrderMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(userOrderList)) {
			return "数据参数不正确";
		}

		UserOrder userOrder = userOrderList.get(0);
		
		if (userOrder.getEmployUserId() != userId) {
			return "参数不正确";
		}

		if (userOrder.getOrderStatus() != 1) {
			return "不合法的请求";
		}

		ProviderIncome providerIncome = new ProviderIncome();
		providerIncome.setOrderId(userOrder.getId());
		providerIncome.setOrderIdNo(userOrder.getIdNo());
		providerIncome.setEmployId(userOrder.getEmployUserId());
		providerIncome.setProviderId(userOrder.getProviderUserId());
		providerIncome.setAmount(userOrder.getDealPrice());
		providerIncome.setCreateTime(new Date());
		int result = providerIncomeMapper.insert(providerIncome); //插入收入详细表

		if (result != 0) {
			ProviderAccountExample providerAccountexample = new ProviderAccountExample();
			providerAccountexample.createCriteria().andProviderUserIdEqualTo(employConfimeService.getProviceId());
			List<ProviderAccount> accountList = providerAccountMapper.selectByExample(providerAccountexample);
			if (CollectionUtils.isNotEmpty(accountList)) {
				ProviderAccount account = accountList.get(0);
				int allAmount = account.getAllAmount();
				int currentAmount = account.getCurrentAmount();
				account.setAllAmount(allAmount + userOrder.getDealPrice());
				account.setCurrentAmount(currentAmount + userOrder.getDealPrice());
				result = providerAccountMapper.updateByPrimaryKey(account); //更新总账户表
				if (result != 0) {
					userOrder.setOrderStatus(2);
					userOrder.setComfirmReceiveTime(new Date());
					result = userOrderMapper.updateByPrimaryKey(userOrder);
					if (result != 0) {
						return "true";
					}
				}
			}
		}

		return "false";
	}

	@Override
	public String cancelOrder(String token) {
		UserOrderExample example = new UserOrderExample();
		example.createCriteria().andTokenEqualTo(token);
		List<UserOrder> orderList = userOrderMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(orderList)) {
			UserOrder order = orderList.get(0);
			order.setIsCancel(1);
			order.setOrderStatus(3);
			int result = userOrderMapper.updateByPrimaryKey(order);
			if (result != 0) {
				return "取消订单成功";
			}
		} else {
			return "参数错误";
		}
		return "";
	}
}
