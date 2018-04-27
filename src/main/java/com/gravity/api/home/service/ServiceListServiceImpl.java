package com.gravity.api.home.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.ProviderMyPublishModel;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderService;
import com.gravity.common.support.mybatis.auto.repository.CategorySecondMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderServiceMapper;

@Service
public class ServiceListServiceImpl implements ServiceListServiceIF {

	public static final int PAGE_SIZE = 3;

	@Value("#{filePath['image.publish.service.screenshot.url']}")
	private String publishServiceImageUrl;

	@Autowired
	private ServiceProviderServiceMapper serviceProviderServiceMapper;

	@Autowired
	CategorySecondMapper categorySecondMapper;

	@Override
	public List<ProviderMyPublishModel> getServiceListByCategoryAndOrderType(int secondCategory, int inOrderType,
			int inOrderTypeUpDown, String location, String searchKeyWord, int page) {

		List<ProviderMyPublishModel> providerMyPublishList = new ArrayList<ProviderMyPublishModel>();

		int beginSize = (page - 1) * PAGE_SIZE;

		List<ServiceProviderService> serviceProviderServiceList = new ArrayList<ServiceProviderService>();

		String loc = null;
		if (!"all".equals(location)) {
			loc = location;
		}

		// 为防止map查询数据库文件中，只有desc没有order报错
		if (inOrderType != 0 && inOrderType != 1 && inOrderType != 2) {
			return null;
		}

		if (secondCategory == 0) { // 没选择二级分类，应该为首页搜索框跳转的
			serviceProviderServiceList = serviceProviderServiceMapper.selectServiceByKeyWord(inOrderType,
					inOrderTypeUpDown, loc, "%" + searchKeyWord + "%", beginSize, PAGE_SIZE);
		} else { // 搜索相应二级分类下的服务
			serviceProviderServiceList = serviceProviderServiceMapper.selectServiceByCategory(secondCategory,
					inOrderType, inOrderTypeUpDown, loc, beginSize, PAGE_SIZE);
		}

		if (serviceProviderServiceList != null && serviceProviderServiceList.size() != 0) {
			for (ServiceProviderService serviceProviderService : serviceProviderServiceList) {
				ProviderMyPublishModel providerMyPublish = new ProviderMyPublishModel();
				providerMyPublish.setServiceId(serviceProviderService.getId());
				StringBuffer sb = new StringBuffer();
				sb.append(publishServiceImageUrl);
				sb.append(serviceProviderService.getImgSrcList().split(",")[0]);
				providerMyPublish.setImageSrc(sb.toString());
				providerMyPublish.setTitle(serviceProviderService.getTitle());
				providerMyPublish.setOneIntroduction(serviceProviderService.getOneIntroduction());
				providerMyPublish.setOriginalPrice(serviceProviderService.getOriginalPrice());
				providerMyPublish.setGroupPrice(serviceProviderService.getGroupPrice());
				providerMyPublishList.add(providerMyPublish);
			}
		}

		return providerMyPublishList;
	}

	@Override
	public int getServiceListAllNumber(int secondCategory, String location, String searchKeyWord) {

		int result = 0;

		String loc = null;
		if (!"all".equals(location)) {
			loc = location;
		}

		if (secondCategory == 0) { // 没选择二级分类，应该为首页搜索框跳转的
			result = serviceProviderServiceMapper.selectServiceByKeyWordTotalNumber(loc, "%" + searchKeyWord + "%");
		} else { // 搜索相应二级分类下的服务
			result = serviceProviderServiceMapper.selectServiceByCategoryTotalNumber(secondCategory, loc);
		}

		return result;
	}

	@Override
	public String getServiceListShowTitle(String category, String searchKy) {

		if ("".equals(category)) {
			return searchKy;
		} else {
			return categorySecondMapper.selectByPrimaryKey(Integer.parseInt(category)).getName();
		}
	}

}
