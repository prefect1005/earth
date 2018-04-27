package com.gravity.api.provider.service.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.common.view.model.ProviderMyPublishModel;
import com.gravity.api.provider.service.service.MyPublishServiceServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "myPublish")
public class MyPublishServiceController {

	@Autowired
	MyPublishServiceServiceIF myPublishServiceService;

	@RequestMapping(value = "")
	public String initMyPublishPage(Model model, HttpServletRequest request) {

		Users user = (Users) request.getSession().getAttribute("user");
		String basicInfoUrl = "";
		if (user.getTypeCp() == 1) {
			basicInfoUrl = "/providerBasicInfo/basicInfoCompany";
		} else {
			basicInfoUrl = "/providerBasicInfo/basicInfoPerson";
		}
		model.addAttribute("basicInfoUrl", basicInfoUrl);
		return "provider-my-publish";
	}

	@RequestMapping(value = "getProviderMyPublishedDetail")
	@ResponseBody
	public AjaxResult getProviderMyPublishedOnlineByUserId(@RequestParam(value = "page", defaultValue = "1") int page,
			HttpServletRequest request) {
		AjaxResult ajaxResult = AjaxResult.fail();
		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);

		if (user.getGroupAuthority() != 1) { // 如果不是1的话，说明不是服务商，不允许继续
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}
		System.out.println("userid:" + user.getId());
		List<ProviderMyPublishModel> providerMyPublishList = myPublishServiceService
				.getProviderPublishedServiceByUserId(user.getId(), page);
		int totalNumber = myPublishServiceService.getTotalServicePageByUserId(user.getId());
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("totalNumber", totalNumber);
		ajaxResult.addData("providerMyPublishList", providerMyPublishList);
		return ajaxResult;
	}

	@RequestMapping(value = "deleteMyPublish")
	@ResponseBody
	public AjaxResult deleteMyPublish(@RequestParam(value = "serviceId", defaultValue = "1") int serviceId,
			HttpServletRequest request) {
		AjaxResult ajaxResult = AjaxResult.fail();
		Users user = (Users) request.getSession().getAttribute("user");

		if (user.getGroupAuthority() != 1) { // 如果不是1的话，说明不是服务商，不允许继续
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}
		String msg = myPublishServiceService.deleteMyPublishByServiceId(serviceId, user.getId());
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("msg", msg);
		return ajaxResult;
	}

}
