package com.gravity.api.employ.service.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.common.view.model.EmployBasicInfoCompanyModel;
import com.gravity.api.common.view.model.EmployBasicInfoPersonModel;
import com.gravity.api.employ.service.service.EmployBasicInfoServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "employBasicInfo")
public class EmployBasicInfoController {

	@Autowired
	EmployBasicInfoServiceIF employBasicInfoService;

	@RequestMapping(value = "basicInfoPerson")
	public String initBasicInfoPersonPage(Model model, HttpServletRequest request) {

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if ((user.getGroupAuthority() != 2) || (user.getTypeCp() != 2)) {
			return "index"; // 身份信息不符
		}

		EmployBasicInfoPersonModel employBasicInfoPerson = employBasicInfoService.getBasicInfoPersonByUser(user);
		System.out.println(employBasicInfoPerson);
		model.addAttribute("employBasicInfoPerson", employBasicInfoPerson);

		return "employ-basic-info-person";
	}
	
	@RequestMapping(value = "basicInfoCompany")
	public String initBasicInfoCompanyPage(Model model, HttpServletRequest request) {
		
		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if ((user.getGroupAuthority() != 2) || (user.getTypeCp() != 1)) {
			return "index"; // 身份信息不符
		}
		
		EmployBasicInfoCompanyModel employBasicInfoCompany = employBasicInfoService.getBasicInfoCompanyByUser(user);
		
		model.addAttribute("employBasicInfoCompany", employBasicInfoCompany);
		
		return "employ-basic-info-company";
	}

	@RequestMapping(value = "updateBasicInfoPerson", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult updateEmployBasicInfoPersonDetail(
			@RequestBody EmployBasicInfoPersonModel employBasicInfoPersonModel, HttpServletRequest request) {
		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if ((user.getGroupAuthority() != 2) || (user.getTypeCp() != 2)) {
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}

		System.out.println("employBasicInfoPersonModel:" + employBasicInfoPersonModel);
		
		boolean result = employBasicInfoService.updateBasicInfoPersonByInfoId(employBasicInfoPersonModel, user.getId());
		if (result) {
			ajaxResult = AjaxResult.success();
		}

		return ajaxResult;
	}
	
	
	@RequestMapping(value = "updateBasicInfoCompany", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult updateEmployBasicInfoCompanyDetail(
			@RequestBody EmployBasicInfoCompanyModel employBasicInfoCompanyModel, HttpServletRequest request) {
		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if ((user.getGroupAuthority() != 2) || (user.getTypeCp() != 1)) {
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}

		System.out.println("employBasicInfoCompanyModel:" + employBasicInfoCompanyModel);
		
		boolean result = employBasicInfoService.updateBasicInfoCompanyByInfoId(employBasicInfoCompanyModel, user.getId());
		if (result) {
			ajaxResult = AjaxResult.success();
		}

		return ajaxResult;
	}

}
