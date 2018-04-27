package com.gravity.api.home.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gravity.api.common.view.model.ServiceDetailModel;
import com.gravity.api.common.view.model.ServiceEvaluateDetailModel;
import com.gravity.common.support.mybatis.auto.model.Evaluate;
import com.gravity.common.support.mybatis.auto.model.EvaluateExample;
import com.gravity.common.support.mybatis.auto.model.ServiceProviderService;
import com.gravity.common.support.mybatis.auto.repository.EvaluateMapper;
import com.gravity.common.support.mybatis.auto.repository.ServiceProviderServiceMapper;

@Service
public class ServiceDetailServiceImpl implements ServiceDetailServiceIF {

	public static final int PAGE_SIZE = 4;

	@Autowired
	ServiceProviderServiceMapper serviceProviderServiceMapper;

	@Autowired
	EvaluateMapper evaluateMapper;

	@Value("#{filePath['image.publish.service.screenshot.url']}")
	private String publishServiceImageUrl;

	@Override
	public ServiceDetailModel getServiceDetailById(int id) {

		ServiceDetailModel serviceDetail = new ServiceDetailModel();

		ServiceProviderService service = serviceProviderServiceMapper.selectByPrimaryKey(id);

		serviceDetail.setId(id);
		serviceDetail.setShopId(service.getUserId()); // 对外展示shopid,实际上就是userID
		serviceDetail.setTitle(service.getTitle());
		serviceDetail.setKeyword(service.getKeyword());
		serviceDetail.setOneIntroduction(service.getOneIntroduction());
		serviceDetail.setOriginalPrice(service.getOriginalPrice());
		serviceDetail.setGroupPrice(service.getGroupPrice());
		double grPrice = (double) service.getGroupPrice();
		double discount = grPrice / service.getOriginalPrice();
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.0");
		serviceDetail.setDiscount(df.format(discount * 10));

		List<String> imgSrcList = new ArrayList<String>();
		String imageString = service.getImgSrcList();
		String[] imgArrray = imageString.split(",");
		for (String imgSrc : imgArrray) {
			imgSrcList.add(publishServiceImageUrl + imgSrc);
		}
		serviceDetail.setImgSrcList(imgSrcList);

		serviceDetail.setSalesNumber(service.getSalesNumber());
		serviceDetail.setIntroductionDetail(service.getIntroductionDetail());

		return serviceDetail;
	}

	@Override
	public Map<String, String> getServiceEvaluateScoreByServiceId(int id) {

		Map<String, String> resultMap = new HashMap<String, String>();

		EvaluateExample example = new EvaluateExample();
		example.createCriteria().andServiceIdEqualTo(id);
		List<Evaluate> evaluateList = evaluateMapper.selectByExample(example);

		if (evaluateList != null && evaluateList.size() != 0) {
			int totalSize = evaluateList.size();
			int fiveSize = 0;
			int fourSize = 0;
			int threeSize = 0;
			int twoSize = 0;
			int oneSize = 0;

			for (Evaluate evaluate : evaluateList) {
				switch (evaluate.getEvaluateLevel()) {
				case 5:
					fiveSize++;
					break;
				case 4:
					fourSize++;
					break;
				case 3:
					threeSize++;
					break;
				case 2:
					twoSize++;
					break;
				case 1:
					oneSize++;
					break;
				}
			}
			double fivePercentage = (double) fiveSize / totalSize * 100;
			double fourPercentage = (double) fourSize / totalSize * 100;
			double threePercentage = (double) threeSize / totalSize * 100;
			double twoPercentage = (double) twoSize / totalSize * 100;
			double onePercentage = (double) oneSize / totalSize * 100;

			double avargeScore = (fivePercentage * 5 + fourPercentage * 4 + threePercentage * 3 + twoPercentage * 2
					+ onePercentage * 1) / 100;

			java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");

			resultMap.put("avargeScore", df.format(avargeScore));
			resultMap.put("totalSize", "" + totalSize);
			if (fivePercentage == 0) {
				resultMap.put("fivePercentage", "0");
			} else {
				resultMap.put("fivePercentage", "" + (int)fivePercentage);
			}

			if (fourPercentage == 0) {
				resultMap.put("fourPercentage", "0");
			} else {
				resultMap.put("fourPercentage", "" + (int)fourPercentage);
			}

			if (threePercentage == 0) {
				resultMap.put("threePercentage", "0");
			} else {
				resultMap.put("threePercentage", "" + (int)threePercentage);
			}

			if (twoPercentage == 0) {
				resultMap.put("twoPercentage", "0");
			} else {
				resultMap.put("twoPercentage", "" + (int)twoPercentage);
			}

			if (onePercentage == 0) {
				resultMap.put("onePercentage", "0");
			} else {
				resultMap.put("onePercentage", "" + (int)onePercentage);
			}
		} else {
			resultMap.put("avargeScore", "0");
			resultMap.put("totalSize", "0");
			resultMap.put("fivePercentage", "0");
			resultMap.put("fourPercentage", "0");
			resultMap.put("threePercentage", "0");
			resultMap.put("twoPercentage", "0");
			resultMap.put("onePercentage", "0");
		}

		return resultMap;
	}

	@Override
	public List<ServiceEvaluateDetailModel> getServiceEvaluateDetailByServiceId(int id) {
		int begin = 0;
		int count = evaluateMapper.selectCountByServiceId(id);
		Random rand = new Random();
		if (count > PAGE_SIZE) {
			begin = rand.nextInt(count - PAGE_SIZE + 1);// nextInt
														// 随机数不包含括号中的数字，因此加一
		}
		List<ServiceEvaluateDetailModel> evaluateList = evaluateMapper.selectByServiceId(id, begin, PAGE_SIZE);

		return evaluateList;
	}

}
