package com.gravity.api.home.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.common.view.model.ServiceDetailModel;
import com.gravity.api.common.view.model.ServiceEvaluateDetailModel;
import com.gravity.api.home.service.ServiceDetailServiceIF;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "serviceDetail")
public class ServiceDetailController {

	@Autowired
	ServiceDetailServiceIF serviceDetailService;

	@RequestMapping(value = "")
	public String initServiceListPage(@RequestParam(value = "serviceId", defaultValue = "0") int serviceId, Model model) {

		ServiceDetailModel serviceDetail = serviceDetailService.getServiceDetailById(serviceId);
		System.out.println("serviceDetail:" + serviceDetail);
		model.addAttribute("serviceDetail", serviceDetail);

		return "service-detail";
	}

	/*
	 * 获取评论分数
	 */
	@RequestMapping(value = "serviceEvaluateScore")
	@ResponseBody
	public AjaxResult getServiceEvaluateScoreByServiceId(@RequestParam(value = "serviceId") int serviceId) {
		AjaxResult ajaxResult = AjaxResult.fail();
		Map<String, String> resultMap = serviceDetailService.getServiceEvaluateScoreByServiceId(serviceId);
		ajaxResult = AjaxResult.success();

		ajaxResult.addData("data", resultMap);
		return ajaxResult;
	}

	/*
	 * 获取评论内容
	 */
	@RequestMapping(value = "serviceEvaluateDetail")
	@ResponseBody
	public AjaxResult getServiceEvaluateDetailByServiceId(@RequestParam(value = "serviceId") int serviceId) {
		AjaxResult ajaxResult = AjaxResult.fail();
		List<ServiceEvaluateDetailModel> contentList = serviceDetailService
				.getServiceEvaluateDetailByServiceId(serviceId);

		ServiceDetailModel serviceDetail = serviceDetailService.getServiceDetailById(serviceId);

		ajaxResult = AjaxResult.success();

		ajaxResult.addData("contentList", contentList);
		ajaxResult.addData("title", serviceDetail.getTitle());
		ajaxResult.addData("price", serviceDetail.getGroupPrice());
		return ajaxResult;
	}

}
