package com.gravity.api.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gravity.api.admin.service.AdminBasicInfoServiceIF;
import com.gravity.api.common.view.model.AdminBasicInfoDetailModel;
import com.gravity.common.support.mybatis.auto.model.AdminBasicInfo;
import com.gravity.common.support.mybatis.auto.model.Users;

@Controller
@RequestMapping(value = "adminBasicInfo")
public class AdminBasicInfoController {

	@Autowired
	AdminBasicInfoServiceIF adminBasicInfoService;

	@RequestMapping(value = "")
	public String initAdminBasicInfoPage(HttpServletRequest request, Model model) {

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);

		if (user.getGroupAuthority() != 3) {
			return "redirect:/home/index";
		}

		AdminBasicInfoDetailModel adminBasicInfoDetail = adminBasicInfoService.getAdminBasicInfoByUserId(user.getId(), user.getPhone());
		model.addAttribute("adminBasicInfoDetail", adminBasicInfoDetail);
		return "admin-basic-info";
	}

}
