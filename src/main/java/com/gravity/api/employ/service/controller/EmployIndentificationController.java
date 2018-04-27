package com.gravity.api.employ.service.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.common.view.model.EmployIndentyCompanyModel;
import com.gravity.api.common.view.model.EmployIndentyPersonModel;
import com.gravity.api.employ.service.service.EmployIndentificationServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "employIdentify")
public class EmployIndentificationController {

	@RequestMapping(value = "identifyPerson")
	public String initIndentificationPersonPage() {
		return "employ-identification-person";
	}

	@RequestMapping(value = "identifyCompany")
	public String initIndentificationCompanyPage() {
		return "employ-identification-company";
	}

	@Autowired
	EmployIndentificationServiceIF employIndentificationService;

	@RequestMapping(value = "uploadDetailForCompany", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveEmployIndentificationCompanyDetail(
			@RequestBody EmployIndentyCompanyModel employIndentyCompanyModel, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if ((user.getTypeCp() != 1)) {
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}

		System.out.println(employIndentyCompanyModel);

		boolean result = employIndentificationService.employIndentificationCompany(employIndentyCompanyModel,
				user.getId());

		if (result) {
			Users updateUser = employIndentificationService.getUserDetail(user.getId());
			request.getSession().setAttribute("user", updateUser);
			response.addHeader("Set-Cookie", "accountShow=" + URLEncoder.encode(employIndentyCompanyModel.getCompanyName(),"utf-8") + "; path=/;");
			ajaxResult = AjaxResult.success();
			ajaxResult.addData("location", "/home/index");
		}
		return ajaxResult;
	}

	@RequestMapping(value = "uploadDetailForPerson", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveEmployIndentificationPersonDetail(
			@RequestBody EmployIndentyPersonModel employIndentyPersonModel, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if ((user.getTypeCp() != 2)) {
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}

		System.out.println(employIndentyPersonModel);

		boolean result = employIndentificationService.employIndentificationPerson(employIndentyPersonModel,
				user.getId());

		if (result) {
			Users updateUser = employIndentificationService.getUserDetail(user.getId());
			request.getSession().setAttribute("user", updateUser);
			response.addHeader("Set-Cookie", "accountShow=" + URLEncoder.encode(employIndentyPersonModel.getName(),"utf-8") + "; path=/;");
			ajaxResult = AjaxResult.success();
			ajaxResult.addData("location", "/home/index");
		}
		return ajaxResult;
	}

}
