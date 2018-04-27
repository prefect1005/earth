package com.gravity.api.home.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.common.view.model.ProviderMyPublishModel;
import com.gravity.api.home.service.ServiceListServiceIF;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "serviceList")
public class ServiceListController {

	@Autowired
	ServiceListServiceIF serviceListService;

	@RequestMapping(value = "")
	public String initServiceListPage(@RequestParam(value = "category", defaultValue = "") String category,
			@RequestParam(value = "searchKy", defaultValue = "") String searchKy, Model model) {
		
		try {
			// TODO 前段传过来的汉字是乱码(局部解决)
			searchKy = new String(searchKy.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e3) {
			e3.printStackTrace();
		}
		
		String listTitle = serviceListService.getServiceListShowTitle(category, searchKy);
		
		model.addAttribute("listTitle", listTitle);
		model.addAttribute("category", category);
		model.addAttribute("searchKy", searchKy);
		return "service-list";
	}

	/*
	 * 获取满足条件的服务简介
	 */
	@RequestMapping(value = "getService")
	@ResponseBody
	public AjaxResult getServiceList(@RequestParam(value = "secondCategory", defaultValue = "0") int secondCategory,
			@RequestParam(value = "inOrderType", defaultValue = "1") int inOrderType, // 排序类型
																						// 0最新发布
																						// 1销量
																						// 2价格
			@RequestParam(value = "inOrderTypeUpDown", defaultValue = "0") int inOrderTypeUpDown, // 升序降序
			// 0降序
			// 1升序
			@RequestParam(value = "location", defaultValue = "all") String location, // 所有城市中，我返回省市的id，
																						// 存cookie只存市，只传市的id就行
			@RequestParam(value = "searchKeyWord", defaultValue = "") String searchKeyWord,
			@RequestParam(value = "page", defaultValue = "1") int page) {

		try {
			// TODO 前段传过来的汉字是乱码(局部解决)
			searchKeyWord = new String(searchKeyWord.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e3) {
			e3.printStackTrace();
		}

		AjaxResult ajaxResult = AjaxResult.fail();

		List<ProviderMyPublishModel> serviceList = serviceListService.getServiceListByCategoryAndOrderType(
				secondCategory, inOrderType, inOrderTypeUpDown, location, searchKeyWord, page);

		int serviceAllNumber = serviceListService.getServiceListAllNumber(secondCategory, location, searchKeyWord);

		ajaxResult = AjaxResult.success();
		ajaxResult.addData("serviceList", serviceList);
		ajaxResult.addData("serviceAllNumber", serviceAllNumber);

		return ajaxResult;
	}

}
