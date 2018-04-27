package com.gravity.api.employ.service.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gravity.common.support.mybatis.auto.model.Users;

@Controller
@RequestMapping(value = "employModifyPwd")
public class EmployModifyPwd {

	@RequestMapping(value = "")
	public String initModifyPwd(Model model, HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		String basicInfoUrl = "";
		if (user.getTypeCp() == 1) {
			basicInfoUrl = "/employBasicInfo/basicInfoCompany";
		} else {
			basicInfoUrl = "/employBasicInfo/basicInfoPerson";
		}
		model.addAttribute("basicInfoUrl", basicInfoUrl);

		return "employ-modify-pwd";
	}

}
