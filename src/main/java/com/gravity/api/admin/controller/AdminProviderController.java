package com.gravity.api.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.admin.service.AdminProviderServiceIF;
import com.gravity.api.common.view.model.AdminEmployModel;
import com.gravity.api.common.view.model.AdminProviderShowModel;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "adminProvider")
public class AdminProviderController {

	@Autowired
	AdminProviderServiceIF adminProviderService;

	@RequestMapping(value = "")
	public String initAdminProviderPage() {

		return "admin-provider";
	}

	@RequestMapping(value = "getProviderDetail")
	@ResponseBody
	public AjaxResult getEmployDetail(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "row", defaultValue = "") String row,
			@RequestParam(value = "queryKey", defaultValue = "") String queryKey, HttpServletRequest request) {

		//row有全部(""),服务商名称(name),手机号(phoneNumber),类型(type),地址(location),是否认证(isApprove)(0/1)

		try {
			// TODO 前段传过来的汉字是乱码(局部解决)
			queryKey = new String(queryKey.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e3) {
			e3.printStackTrace();
		}

		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);

		if (user.getGroupAuthority() != 3) {
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}

		List<AdminProviderShowModel> providerList = adminProviderService.getProviderDetail(page, row, queryKey.trim());
		int totalNumber = adminProviderService.getCountProvider(row, queryKey.trim());
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("providerList", providerList);
		ajaxResult.addData("totalNumber", totalNumber);

		return ajaxResult;
	}

	@RequestMapping(value = "setApprove")
	@ResponseBody
	public AjaxResult setApprove(
			@RequestParam(value = "providerIdentificationId", defaultValue = "1") int providerIdentificationId,
			@RequestParam(value = "userId", defaultValue = "1") int userId, HttpServletRequest request) {

		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);

		if (user.getGroupAuthority() != 3) {
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}

		boolean result = adminProviderService.setApproveById(providerIdentificationId, userId);
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("message", result);

		return ajaxResult;
	}

}
