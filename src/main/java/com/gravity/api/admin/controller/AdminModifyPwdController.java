package com.gravity.api.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "adminModifyPwd")
public class AdminModifyPwdController {

	@RequestMapping(value = "")
	public String initAdminModifyPwdPage() {

		return "admin-modify-key";
	}

}
