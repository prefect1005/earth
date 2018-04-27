package com.gravity.api.home.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gravity.api.common.view.model.HomePayOrderShowModel;
import com.gravity.api.common.view.model.HomePlaceOrderModel;
import com.gravity.api.common.view.model.HomeSubmitOrderModel;
import com.gravity.api.common.view.model.OrderShowModel;
import com.gravity.api.login.service.LoginServiceIF;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderService;
import com.gravity.common.support.mybatis.auto.model.UserOrder;
import com.gravity.common.support.mybatis.auto.model.UserOrderExample;
import com.gravity.common.support.mybatis.auto.model.UserPrivilege;
import com.gravity.common.support.mybatis.auto.model.UserPrivilegeExample;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderServiceMapper;
import com.gravity.common.support.mybatis.auto.repository.UserOrderMapper;
import com.gravity.common.support.mybatis.auto.repository.UserPrivilegeMapper;
import com.gravity.common.support.mybatis.auto.repository.UsersMapper;

@Service
public class OrderServiceImpl implements OrderServiceIF {

	@Value("#{filePath['image.publish.service.screenshot.url']}")
	private String publishServiceImageUrl;

	@Autowired
	private UserOrderMapper userOrderMapper;

	@Autowired
	private UserPrivilegeMapper userPrivilegeMapper;

	@Autowired
	private ServiceProviderServiceMapper serviceProviderServiceMapper;

	@Autowired
	ServiceProviderIdentificationCompanyMapper serviceProviderIdentificationCompanyMapper;

	@Autowired
	ServiceProviderIdentificationPersonMapper serviceProviderIdentificationPersonMapper;

	@Autowired
	UsersMapper usersMapper;

	@Autowired
	private LoginServiceIF loginService;

	// 待付款
	public static final int WAIT_PAY = 0;

	// 付款到平台
	public static final int PAY_TO_PLATFORM = 1;

	// 服务商用户账户增加金额
	public static final int PLATFORM_TO_USER = 2;

	// 订单完成
	public static final int ORDER_END = 3;

	@Transactional(rollbackFor = Throwable.class)
	public HomePayOrderShowModel submitOrder(HomeSubmitOrderModel homeSubmitOrder, int userId) {

		System.out.println("homeSubmitOrder:" + homeSubmitOrder);

		if (homeSubmitOrder == null) {
			return null;
		}

		// 判断参数是否合法
		boolean isPrivilegeIdLegal = false;

		// 判断手机号验证码是否合法
		boolean isVCodeLegal = false;

		// 判断价格参数是否合法
		boolean isPriceLegal = false;

		HomePayOrderShowModel payOrderShow = new HomePayOrderShowModel();

		//首先计算优惠码的金额
		int privilegeSum = 0;
		if (!"".equals(homeSubmitOrder.getPrivilegeId().trim())) { //填写了优惠码

			UserPrivilegeExample example = new UserPrivilegeExample();
			example.createCriteria().andPrivilegeNumberEqualTo(homeSubmitOrder.getPrivilegeId().trim())
					.andIsUsedEqualTo(0);
			List<UserPrivilege> userPrivilegeList = userPrivilegeMapper.selectByExample(example);
			if (userPrivilegeList != null && userPrivilegeList.size() != 0) {
				UserPrivilege userPrivilege = userPrivilegeList.get(0);
				privilegeSum = userPrivilege.getPrivilegeSum();
				userPrivilege.setIsUsed(1);
				userPrivilegeMapper.updateByPrimaryKey(userPrivilege);
			}
		}

		homeSubmitOrder.setTotalPrice(homeSubmitOrder.getGroupPrice() - privilegeSum);

		// 首先验证优惠码金额和id是否对应
		//		if (!"".equals(homeSubmitOrder.getPrivilegeId().trim())) {
		//
		//			UserPrivilegeExample example = new UserPrivilegeExample();
		//			example.createCriteria().andPrivilegeNumberEqualTo(homeSubmitOrder.getPrivilegeId().trim());
		//			List<UserPrivilege> userPrivilegeList = userPrivilegeMapper.selectByExample(example);
		//			if (userPrivilegeList != null && userPrivilegeList.size() != 0) {
		//				UserPrivilege userPrivilege = userPrivilegeList.get(0);
		//				if (userPrivilege.getPrivilegeSum() == homeSubmitOrder.getPrivilegeSum()) {
		//					isPrivilegeIdLegal = true;
		//				}
		//			}
		//		} else {
		//			if (homeSubmitOrder.getPrivilegeSum() == 0) {
		//				isPrivilegeIdLegal = true;
		//			}
		//		}
		//		if (!isPrivilegeIdLegal) {
		//			return null;
		//		}

		// 判断验证码
		isVCodeLegal = loginService.verifyVCode(homeSubmitOrder.getEmployOrderPhone(), homeSubmitOrder.getVerCode());
		if (!isVCodeLegal) {
			return null;
		}

		// 再判断前段页面提交过来的价格，通过serviceid和数据库的价格是否一致，保证接口参数不被修改
		ServiceProviderService serviceProviderService = serviceProviderServiceMapper.selectByPrimaryKey(homeSubmitOrder
				.getServiceId());
		// 这个判断，是用来判断，服务的价格，是否等于提交过来参数价格加上优惠价格
		if ((serviceProviderService.getGroupPrice() == (homeSubmitOrder.getTotalPrice() + privilegeSum))
				&& (serviceProviderService.getGroupPrice() == homeSubmitOrder.getGroupPrice())) {
			isPriceLegal = true;
		}
		if (!isPriceLegal) {
			return null;
		}

		// 插入表后,查询payOrderShow字段供交钱页面使用
		if (/*isPrivilegeIdLegal && */isVCodeLegal && isPriceLegal) { // 如果参数校验没问题
			UserOrder userOrder = new UserOrder();
			// 生成订单编号
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String orderNo = sdf.format(date) + userId + (int) (Math.random() * 9000 + 1000);
			userOrder.setIdNo(orderNo);
			userOrder.setEmployUserId(userId);
			userOrder.setProviderUserId(serviceProviderService.getUserId());
			userOrder.setServiceId(homeSubmitOrder.getServiceId());
			userOrder.setIsCancel(0);
			userOrder.setIsAfterSales(homeSubmitOrder.getIsAfterSales());
			userOrder.setDealPrice(homeSubmitOrder.getTotalPrice());
			userOrder.setServicePrice(homeSubmitOrder.getGroupPrice());
			userOrder.setEmployOrderPhone(homeSubmitOrder.getEmployOrderPhone());
			userOrder.setOrderRemark(homeSubmitOrder.getOrderRemark());
			userOrder.setOrderRemarkFile(homeSubmitOrder.getOrderRemarkFileUrl());
			userOrder.setOrderStatus(WAIT_PAY);
			String token = UUID.randomUUID().toString().replaceAll("-", "");
			userOrder.setToken(token);
			userOrder.setCreateTime(date);
			//			userOrder.setPayTime(date);
			//			userOrder.setComfirmReceiveTime(date);
			//			userOrder.setFinishTime(date);
			int result = userOrderMapper.insert(userOrder);
			if (result != 0) {
				// 下订单成功，更新service_sales表，更新订单销量
				// ServiceSalesExample example = new ServiceSalesExample();
				// example.createCriteria().andServiceIdEqualTo(homeSubmitOrder.getServiceId());
				// List<ServiceSales> serviceSaleList =
				// serviceSalesMapper.selectByExample(example);
				// if (serviceSaleList != null && serviceSaleList.size() != 0) {
				// ServiceSales serviceSales = serviceSaleList.get(0);
				// serviceSales.setSalesNumber(serviceSales.getSalesNumber() +
				// 1);
				// serviceSalesMapper.updateByPrimaryKey(serviceSales);
				// } else {
				// ServiceSales serviceSales = new ServiceSales();
				// serviceSales.setServiceId(homeSubmitOrder.getServiceId());
				// serviceSales.setSalesNumber(1);
				// serviceSalesMapper.insert(serviceSales);
				// }
				serviceProviderService.setSalesNumber(serviceProviderService.getSalesNumber() + 1);
				serviceProviderServiceMapper.updateByPrimaryKey(serviceProviderService);

				int userOrderId = userOrder.getId();
				System.out.println("userOrderId:" + userOrderId);
				System.out.println("orderNo:" + orderNo);
				String serviceTitle = serviceProviderService.getTitle();
				payOrderShow.setServiceTitle(serviceTitle);
				payOrderShow.setOrderNo(orderNo);
				payOrderShow.setTotalPirce(userOrder.getDealPrice());
				payOrderShow.setToken(userOrder.getToken());
				String urls = serviceProviderService.getImgSrcList();
				if (StringUtils.isNotBlank(urls)) {
					String[] urlsArray = urls.split(",");
					payOrderShow.setServiceIconUrl(publishServiceImageUrl + urlsArray[0]);
				}
			}
		}

		return payOrderShow;
	}

	public HomePayOrderShowModel payOrderPage(String idNo, String token, int userId) {
		HomePayOrderShowModel payOrderShow = new HomePayOrderShowModel();

		UserOrderExample example = new UserOrderExample();
		example.createCriteria().andIdNoEqualTo(idNo).andTokenEqualTo(token);
		List<UserOrder> userOrderList = userOrderMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(userOrderList)) {
			UserOrder userOrder = userOrderList.get(0);
			ServiceProviderService serviceProviderService = serviceProviderServiceMapper.selectByPrimaryKey(userOrder
					.getServiceId());
			payOrderShow.setOrderNo(idNo);
			payOrderShow.setServiceTitle(serviceProviderService.getTitle());
			payOrderShow.setToken(token);
			payOrderShow.setTotalPirce(userOrder.getDealPrice());

			String urls = serviceProviderService.getImgSrcList();
			if (StringUtils.isNotBlank(urls)) {
				String[] urlsArray = urls.split(",");
				payOrderShow.setServiceIconUrl(publishServiceImageUrl + urlsArray[0]);
			}

		}

		return payOrderShow;
	}

	@Override
	public OrderShowModel getOrderShowDetail(HomePlaceOrderModel homePlaceOrder) {
		OrderShowModel orderShow = new OrderShowModel();

		int serviceId = homePlaceOrder.getServiceId();
		orderShow.setIconSrcUrl(homePlaceOrder.getIconSrcUrl());

		ServiceProviderService service = serviceProviderServiceMapper.selectByPrimaryKey(serviceId);
		if (service == null) {
			return null;
		}
		orderShow.setServiceKeyWord(service.getKeyword());

		String serviceProider = "";
		int userId = service.getUserId();
		Users user = usersMapper.selectByPrimaryKey(userId);
		if (user.getTypeCp() == 1) { // 公司
			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andUserIdEqualTo(userId);
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExample(example);
			if (companyList != null && companyList.size() != 0) {
				ServiceProviderIdentificationCompany company = companyList.get(0);
				serviceProider = company.getCompanyName();
			}
		} else {
			ServiceProviderIdentificationPersonExample example = new ServiceProviderIdentificationPersonExample();
			example.createCriteria().andUserIdEqualTo(userId);
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(example);
			if (personList != null && personList.size() != 0) {
				ServiceProviderIdentificationPerson person = personList.get(0);
				serviceProider = person.getName();
			}
		}
		orderShow.setServiceProvider(serviceProider);
		orderShow.setServiceId(serviceId);
		orderShow.setGroupPrice(homePlaceOrder.getGroupPrice());
		//orderShow.setPayType(homePlaceOrder.getPayType());
		orderShow.setIsAfterSales(homePlaceOrder.getIsAfterSales());
		return orderShow;
	}

}
