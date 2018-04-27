package com.gravity.api.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.common.view.model.ShopDetailModel;
import com.gravity.api.common.view.model.ShopDownServiceShowModel;
import com.gravity.api.common.view.model.ShopOtherServiceModel;
import com.gravity.api.home.service.ShopInfoServiceIF;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "shopInfo")
public class ShopInfoController {

	@Autowired
	ShopInfoServiceIF shopInfoService;

	@RequestMapping(value = "")
	public String initShopInfoPage(@RequestParam(value = "shopId", defaultValue = "0") int userId, Model model) {

		ShopDetailModel shopDetail = shopInfoService.getShopDetailByUserId(userId);
		System.out.println("shopDetail:" + shopDetail);
		model.addAttribute("shopDetail", shopDetail);
		return "shop-info";
	}

	@RequestMapping(value = "test")
	@ResponseBody
	public AjaxResult test() {
		AjaxResult ajaxResult = AjaxResult.success();
		shopInfoService.test();

		return ajaxResult;
	}

	@RequestMapping(value = "getServiceByShopId")
	@ResponseBody
	public AjaxResult getOtherServiceByShopId(@RequestParam(value = "shopId", defaultValue = "0") int userId,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		AjaxResult ajaxResult = AjaxResult.fail();

		List<ShopOtherServiceModel> shopOtherServiceList = shopInfoService.getOtherServiceByShopId(userId, page);
		int count = shopInfoService.getOtherServiceCountByShopId(userId);
		
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("shopOtherService", shopOtherServiceList);
		ajaxResult.addData("count", count);

		return ajaxResult;
	}
	
	@RequestMapping(value = "getDownServiceByShopId")
	@ResponseBody
	public AjaxResult getDownServiceByShopId(@RequestParam(value = "shopId", defaultValue = "0") int userId,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		AjaxResult ajaxResult = AjaxResult.fail();

		List<ShopDownServiceShowModel> shopDownServiceShowlist = shopInfoService.getDownServiceByShopId(userId, page);
		int count = shopInfoService.getDownServiceCountByShopId(userId);

		ajaxResult = AjaxResult.success();
		ajaxResult.addData("shopDownService", shopDownServiceShowlist);
		ajaxResult.addData("count", count);

		return ajaxResult;
	}

}
