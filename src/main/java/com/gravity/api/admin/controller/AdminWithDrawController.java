package com.gravity.api.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.admin.service.AdminWithDrawServiceIF;
import com.gravity.api.common.view.model.AdminWithDrawShowModel;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "adminWithDraw")
public class AdminWithDrawController {

	@Autowired
	AdminWithDrawServiceIF adminWithDrawService;

	@RequestMapping(value = "")
	public String initadminWithDrawPage() {

		return "admin-withdraw";
	}

	/**
	 * 获取提现表
	 * @param page
	 * @param row
	 * @param queryKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getProviderWithDrawDetail")
	@ResponseBody
	public AjaxResult getProviderIncomeDetail(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "row", defaultValue = "") String row,
			@RequestParam(value = "queryKey", defaultValue = "") String queryKey, HttpServletRequest request) {

		//row全部（""）,服务商名称（name）手机号（phone）银行卡号（bankId）提现状态（status）

		try {
			// TODO 前段传过来的汉字是乱码(局部解决)
			queryKey = new String(queryKey.getBytes("ISO-8859-1"), "UTF-8").trim();
		} catch (UnsupportedEncodingException e3) {
			e3.printStackTrace();
		}

		AjaxResult ajaxResult = AjaxResult.fail();
		Users user = (Users) request.getSession().getAttribute("user");

		if (user.getGroupAuthority() != 3) {
			ajaxResult.addData("msg", "级别不符");
		}

		List<AdminWithDrawShowModel> withDrawList = adminWithDrawService.getAdminWithDrawList(page, row,
				queryKey.trim());
		int count = adminWithDrawService.getCountAdminWithDraw(row, queryKey);
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("withDrawList", withDrawList);
		ajaxResult.addData("count", count);
		return ajaxResult;
	}

	/**
	 * 更新提现状态
	 * @param page
	 * @param row
	 * @param queryKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "updateWithDrawStatus")
	@ResponseBody
	public AjaxResult updateWithDrawStatus(@RequestParam(value = "withDrawId", defaultValue = "1") int withDrawId,
			HttpServletRequest request) {

		AjaxResult ajaxResult = AjaxResult.fail();
		Users user = (Users) request.getSession().getAttribute("user");

		if (user.getGroupAuthority() != 3) {
			ajaxResult.addData("msg", "级别不符");
		}

		boolean result = adminWithDrawService.updateWithDrawStatus(withDrawId);

		ajaxResult = AjaxResult.success();
		ajaxResult.addData("result", result);
		return ajaxResult;
	}

}
