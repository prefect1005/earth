package com.gravity.api.provider.service.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gravity.common.support.mybatis.auto.model.Users;

@Controller
@RequestMapping(value = "providerModifyPwd")
public class ProviderModifyPwd {

	@RequestMapping(value = "")
	public String initModifyPwd(Model model, HttpServletRequest request) {

		Users user = (Users) request.getSession().getAttribute("user");
		String basicInfoUrl = "";
		if (user.getTypeCp() == 1) {
			basicInfoUrl = "/providerBasicInfo/basicInfoCompany";
		} else {
			basicInfoUrl = "/providerBasicInfo/basicInfoPerson";
		}
		model.addAttribute("basicInfoUrl", basicInfoUrl);
		return "provider-modify-pwd";
	}

}
