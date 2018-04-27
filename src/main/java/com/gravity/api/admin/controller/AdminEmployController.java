package com.gravity.api.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.admin.service.AdminEmployServiceIF;
import com.gravity.api.common.view.model.AdminEmployModel;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "adminEmploy")
public class AdminEmployController {

	@Autowired
	AdminEmployServiceIF adminEmployService;

	@RequestMapping(value = "")
	public String initAdminEmployPage() {

		return "admin-employ";
	}

	@RequestMapping(value = "getEmployDetail")
	@ResponseBody
	public AjaxResult getEmployDetail(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "row", defaultValue = "") String row,@RequestParam(value = "queryKey", defaultValue = "") String queryKey,
			HttpServletRequest request) {
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

		List<AdminEmployModel> adminEmployList = adminEmployService.getEmployDetail(page, row, queryKey.trim());
		int totalNumber = adminEmployService.getTotalEmployCount(row, queryKey.trim());
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("data", adminEmployList);
		ajaxResult.addData("totalNumber", totalNumber);

		return ajaxResult;
	}

}
