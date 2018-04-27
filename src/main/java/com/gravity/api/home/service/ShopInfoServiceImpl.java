package com.gravity.api.home.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.ShopDetailModel;
import com.gravity.api.common.view.model.ShopDownServiceModel;
import com.gravity.api.common.view.model.ShopDownServiceShowModel;
import com.gravity.api.common.view.model.ShopOtherServiceModel;
import com.gravity.common.support.mybatis.auto.model.CategorySecond;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.EmployIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.Evaluate;
import com.gravity.common.support.mybatis.auto.model.EvaluateExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompany;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationCompanyExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPerson;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderIdentificationPersonExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderServiceExample;
import com.gravity.common.support.mybatis.auto.model.UserOrder;
import com.gravity.common.support.mybatis.auto.model.UserOrderExample;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.mybatis.auto.repository.CategorySecondMapper;
import com.gravity.common.support.mybatis.auto.repository.EmployIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.EmployIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.EvaluateMapper;
import com.gravity.common.support.mybatis.auto.repository.OrderAndUserMapper;
import com.gravity.common.support.mybatis.auto.repository.PublicServiceAndSecondCategoryMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationCompanyMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderIdentificationPersonMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderServiceMapper;
import com.gravity.common.support.mybatis.auto.repository.UserOrderMapper;
import com.gravity.common.support.mybatis.auto.repository.UsersMapper;

@Service
public class ShopInfoServiceImpl implements ShopInfoServiceIF {

	@Autowired
	UsersMapper usersMapper;

	@Autowired
	ServiceProviderIdentificationCompanyMapper serviceProviderIdentificationCompanyMapper;

	@Autowired
	ServiceProviderIdentificationPersonMapper serviceProviderIdentificationPersonMapper;

	@Autowired
	UserOrderMapper userOrderMapper;

	@Autowired
	EvaluateMapper evaluateMapper;

	@Autowired
	CategorySecondMapper categorySecondMapper;

	@Autowired
	ServiceProviderServiceMapper serviceProviderServiceMapper;

	@Autowired
	PublicServiceAndSecondCategoryMapper publicServiceAndSecondCategoryMapper;

	@Autowired
	OrderAndUserMapper orderAndUserMapper;

	@Autowired
	EmployIdentificationCompanyMapper employIdentificationCompanyMapper;

	@Autowired
	EmployIdentificationPersonMapper employIdentificationPersonMapper;

	@Value("#{filePath['image.provider.indenty.url']}")
	private String providerIndentyImageUrl;

	public static final int PAGE_SIZE = 5;

	@Override
	public ShopDetailModel getShopDetailByUserId(int userId) {
		ShopDetailModel shopDetailModel = new ShopDetailModel();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Users user = usersMapper.selectByPrimaryKey(userId);
		if (user.getGroupAuthority() != 1) { // 该用户不是服务商用户,参数不合法返回null
			return null;
		}

		shopDetailModel.setShopId(userId);

		if (user.getTypeCp() == 1) { // 企业
			ServiceProviderIdentificationCompanyExample example = new ServiceProviderIdentificationCompanyExample();
			example.createCriteria().andUserIdEqualTo(userId);
			List<ServiceProviderIdentificationCompany> companyList = serviceProviderIdentificationCompanyMapper
					.selectByExampleWithBLOBs(example);
			if (companyList != null && companyList.size() != 0) {
				ServiceProviderIdentificationCompany company = companyList.get(0);
				shopDetailModel.setStoreDisplayImgSrc(providerIndentyImageUrl + company.getStoreDisplayImgSrc());
				shopDetailModel.setName(company.getCompanyName());
				shopDetailModel.setRegisterDate(sdf.format(user.getCreateTime()));
				shopDetailModel.setPhoneNumber(company.getPhoneNumber());
				shopDetailModel.setUserLocation(company.getCompanyLocation());
				shopDetailModel.setServicerProfile(company.getCompanyProfile());
				String imgSrcList = company.getImgSrcList();
				String[] imgArray = imgSrcList.split(",");
				List<String> imgList = new ArrayList<String>();
				for (String temp : imgArray) {
					imgList.add(providerIndentyImageUrl + temp);
				}
				shopDetailModel.setProveImgUrl(imgList);
			}
		} else { // 2 ：个人
			ServiceProviderIdentificationPersonExample example = new ServiceProviderIdentificationPersonExample();
			example.createCriteria().andUserIdEqualTo(userId);
			List<ServiceProviderIdentificationPerson> personList = serviceProviderIdentificationPersonMapper
					.selectByExample(example);
			if (personList != null && personList.size() != 0) {
				ServiceProviderIdentificationPerson person = personList.get(0);
				shopDetailModel.setStoreDisplayImgSrc(person.getStoreDisplayImgSrc());
				shopDetailModel.setName(person.getName());
				shopDetailModel.setRegisterDate(sdf.format(person.getCreateTime()));
				shopDetailModel.setPhoneNumber(person.getPhoneNumber());
				shopDetailModel.setUserLocation(person.getLocation());

				shopDetailModel.setServicerProfile("这里是个人服务商");
				String imgSrcList = person.getImgSrcList();

				String[] imgArray = imgSrcList.split(",");
				List<String> imgList = new ArrayList<String>();
				for (String temp : imgArray) {
					imgList.add(providerIndentyImageUrl + temp);
				}
				shopDetailModel.setProveImgUrl(imgList);
			}
		}

		UserOrderExample userOrderExample = new UserOrderExample();
		userOrderExample.createCriteria().andProviderUserIdEqualTo(userId).andOrderStatusEqualTo(2);// 订单状态用户确认收货的就可以算作已完成
		List<UserOrder> orderList = userOrderMapper.selectByExample(userOrderExample);
		if (orderList != null && orderList.size() != 0) {
			shopDetailModel.setCompleteServiceNumber(orderList.size());
		} else {
			shopDetailModel.setCompleteServiceNumber(0);
		}

		EvaluateExample evaluateExample = new EvaluateExample();
		evaluateExample.createCriteria().andProviderUserIdEqualTo(userId).andEvaluateLevelGreaterThan(3); // 3以上,即4/5
		List<Evaluate> evaluateList = evaluateMapper.selectByExample(evaluateExample);
		if (evaluateList != null && evaluateList.size() != 0) {
			shopDetailModel.setGoodReputationNumber(evaluateList.size());
		} else {
			shopDetailModel.setGoodReputationNumber(0);
		}

		List<Integer> secondType = serviceProviderServiceMapper.selectSecondLevelByUserId(userId);
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		for (int temp : secondType) {
			CategorySecond categorySecond = categorySecondMapper.selectByPrimaryKey(temp);
			if (flag) {
				sb.append("/");
			} else {
				flag = true;
			}
			sb.append(categorySecond.getName());
		}
		shopDetailModel.setServiceScope(sb.toString());

		return shopDetailModel;
	}

	@Override
	public void test() {
		List<ShopOtherServiceModel> serviceList = publicServiceAndSecondCategoryMapper.selectByUserId(3, 0, 3);
		System.out.println("l=====l");
		for (ShopOtherServiceModel ss : serviceList) {
			System.out.println(ss);
		}
		System.out.println("[p=====l");
	}

	@Override
	public List<ShopOtherServiceModel> getOtherServiceByShopId(int userId, int page) {
		List<ShopOtherServiceModel> shopOtherServiceList = new ArrayList<ShopOtherServiceModel>();

		int beginSize = (page - 1) * PAGE_SIZE;

		List<ShopOtherServiceModel> serviceList = publicServiceAndSecondCategoryMapper.selectByUserId(userId, beginSize,
				PAGE_SIZE);

		for (ShopOtherServiceModel shopOtherService : serviceList) {
			shopOtherService.setNo(++beginSize);
			shopOtherService.setPublishTime(shopOtherService.getPublishTime().substring(0, 10));
			shopOtherServiceList.add(shopOtherService);
		}

		return shopOtherServiceList;
	}

	@Override
	public int getOtherServiceCountByShopId(int userId) {
		int count = publicServiceAndSecondCategoryMapper.selectCountByUserId(userId);
		if (count % PAGE_SIZE == 0) {
			return count / PAGE_SIZE;
		} else {
			return count / PAGE_SIZE + 1;
		}
	}

	@Override
	public List<ShopDownServiceShowModel> getDownServiceByShopId(int userId, int page) {

		List<ShopDownServiceShowModel> shopDownServiceList = new ArrayList<ShopDownServiceShowModel>();

		int beginSize = (page - 1) * PAGE_SIZE;

		List<ShopDownServiceModel> shopDownList = orderAndUserMapper.selectDownServiceByUserId(userId, beginSize,
				PAGE_SIZE);

		for (ShopDownServiceModel shopDownService : shopDownList) {
			ShopDownServiceShowModel shopDownServiceShow = new ShopDownServiceShowModel();
			shopDownServiceShow.setNo(++beginSize);
			shopDownServiceShow.setTitle(shopDownService.getTitle());

			String employName = "";
			int employUserId = shopDownService.getEmployUserId();
			Users user = usersMapper.selectByPrimaryKey(employUserId);
			if (user.getTypeCp() == 1) { // 企业
				EmployIdentificationCompanyExample example = new EmployIdentificationCompanyExample();
				example.createCriteria().andUserIdEqualTo(employUserId);
				List<EmployIdentificationCompany> companyList = employIdentificationCompanyMapper
						.selectByExample(example);
				if (companyList != null && companyList.size() != 0) {
					EmployIdentificationCompany company = companyList.get(0);
					employName = company.getCompanyName();
				}
			} else { // 个人
				EmployIdentificationPersonExample example = new EmployIdentificationPersonExample();
				example.createCriteria().andUserIdEqualTo(employUserId);
				List<EmployIdentificationPerson> personList = employIdentificationPersonMapper.selectByExample(example);
				if (personList != null && personList.size() != 0) {
					EmployIdentificationPerson person = personList.get(0);
					employName = person.getName();
				}
			}
			shopDownServiceShow.setEmployName(employName);
			shopDownServiceShow.setGroupPrice(shopDownService.getGroupPrice());
			shopDownServiceShow.setDealTime(shopDownService.getDealTime().substring(0, 10));
			shopDownServiceList.add(shopDownServiceShow);
		}

		return shopDownServiceList;
	}

	@Override
	public int getDownServiceCountByShopId(int userId) {
		int count = orderAndUserMapper.selectCountByUserId(userId);
		if (count % PAGE_SIZE == 0) {
			return count / PAGE_SIZE;
		} else {
			return count / PAGE_SIZE + 1;
		}
	}

}
