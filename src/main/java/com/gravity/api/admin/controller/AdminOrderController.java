package com.gravity.api.admin.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.admin.service.AdminOrderServiceIF;
import com.gravity.api.common.view.model.AdminrOrderShowModel;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "adminOrder")
public class AdminOrderController {

	@Autowired
	AdminOrderServiceIF adminOrderService;

	@RequestMapping(value = "")
	public String initAdminModifyPwdPage(HttpServletRequest request) {
		
		Users user = (Users) request.getSession().getAttribute("user");
		if (user.getGroupAuthority() != 3) {
			return "redirect:/home/index";
		}

		return "admin-all-order";
	}

	@RequestMapping(value = "getAllOrderDetail")
	@ResponseBody
	public AjaxResult getProviderOrderDetail(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "row", defaultValue = "") String row,
			@RequestParam(value = "queryKey", defaultValue = "") String queryKey, HttpServletRequest request) {

		// row 有服务名称serviceTitle和交易状态status
		// queryKey 有任意的title和status的固定值（0,1,2,3,4）（取消订单，待付款，服务进行中，带评价，已完成）

		try {
			// TODO 前段传过来的汉字是乱码(局部解决)
			queryKey = new String(queryKey.getBytes("ISO-8859-1"), "UTF-8").trim();
		} catch (UnsupportedEncodingException e3) {
			e3.printStackTrace();
		}
		System.out.println("row:" + row);
		System.out.println("queryKey:" + queryKey);
		AjaxResult ajaxResult = AjaxResult.fail();
		Users user = (Users) request.getSession().getAttribute("user");

		if (user.getGroupAuthority() != 3) {
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}
		List<AdminrOrderShowModel> orderList = adminOrderService.getAllOrderDetailByPage(page, row, queryKey);
		int totalSize = adminOrderService.getCountOrder(row, queryKey);
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("orderList", orderList);
		ajaxResult.addData("totalSize", totalSize);
		return ajaxResult;
	}

}
