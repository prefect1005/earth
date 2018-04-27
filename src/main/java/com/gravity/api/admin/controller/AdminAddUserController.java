package com.gravity.api.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.admin.service.AdminAddUserServiceIF;
import com.gravity.api.common.view.model.AdminCreateUserModel;
import com.gravity.api.common.view.model.UserModel;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "adminAddUser")
public class AdminAddUserController {

	private final String specalAccount = "13800000000";

	@Autowired
	AdminAddUserServiceIF adminAddUserService;

	@RequestMapping(value = "")
	public String initAdminAddUserPage(HttpServletRequest request, Model model) {

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);

		if (!specalAccount.equals(user.getPhone())) {
			return "redirect:/home/index";
		}

		return "admin-add-user";
	}

	@RequestMapping(value = "createAdminUser", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult createAdminUser(@RequestBody AdminCreateUserModel adminCreateUser, HttpServletRequest request) {
		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);

		if (!specalAccount.equals(user.getPhone())) {
			ajaxResult.addData("msg", "权限不够");
			return ajaxResult;
		}
		System.out.println("adminCreateUser:" + adminCreateUser);
		boolean result = adminAddUserService.addAdminUser(adminCreateUser);
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("msg", result);
		ajaxResult.addData("location", "/home/index");
		return ajaxResult;
	}
	
	@RequestMapping(value = "specalAccount")
	@ResponseBody
	public AjaxResult getSpecalAccount() {
		AjaxResult ajaxResult = AjaxResult.success();
		ajaxResult.addData("specalAccount", specalAccount);
		return ajaxResult;
	}

}
