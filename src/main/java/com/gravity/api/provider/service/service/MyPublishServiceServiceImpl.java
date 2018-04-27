package com.gravity.api.provider.service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.ProviderMyPublishModel;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderService;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderServiceMapper;

@Service
public class MyPublishServiceServiceImpl implements MyPublishServiceServiceIF {

	public static final int PAGE_SIZE = 2;

	@Value("#{filePath['image.publish.service.screenshot.url']}")
	private String publishServiceImageUrl;

	@Autowired
	private ServiceProviderServiceMapper serviceProviderServiceMapper;

	@Override
	public List<ProviderMyPublishModel> getProviderPublishedServiceByUserId(int userId, int page) {

		List<ProviderMyPublishModel> providerMyPublishList = new ArrayList<ProviderMyPublishModel>();

		int beginSize = (page - 1) * PAGE_SIZE;

		List<ServiceProviderService> serviceProviderServiceList = serviceProviderServiceMapper
				.selectServiceProviderServiceByPage(userId, beginSize, PAGE_SIZE);
		
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
	public int getTotalServicePageByUserId(int userId) {

		int totalSize = serviceProviderServiceMapper.onlineCountByUserId(userId);
		return totalSize;
	}

	@Override
	public String deleteMyPublishByServiceId(int serviceId, int userId) {
		
		ServiceProviderService service = serviceProviderServiceMapper.selectByPrimaryKey(serviceId);
		if (service.getUserId() != userId) {
			return "删除失败，请重试";
		}
		
		service.setOnline(0);
		int result = serviceProviderServiceMapper.updateByPrimaryKey(service);
		if (result != 0) {
			return "删除成功";
		}
		
		return "删除失败，请重试";
	}

}
