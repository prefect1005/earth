package com.gravity.api.employ.service.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.common.view.model.EmployConfimeServiceModel;
import com.gravity.api.common.view.model.EmployEvaluateModel;
import com.gravity.api.common.view.model.EmployOrderShowModel;
import com.gravity.api.employ.service.service.EmployMyOrderServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "employOrder")
public class EmployMyOrderController {

	@Autowired
	EmployMyOrderServiceIF employMyOrderService;

	@RequestMapping(value = "")
	public String initMyOrderPage(Model model, HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		String basicInfoUrl = "";
		if (user.getTypeCp() == 1) {
			basicInfoUrl = "/employBasicInfo/basicInfoCompany";
		} else {
			basicInfoUrl = "/employBasicInfo/basicInfoPerson";
		}
		model.addAttribute("basicInfoUrl", basicInfoUrl);
		return "employ-my-order";
	}

	@RequestMapping(value = "getEmployOrderDetail")
	@ResponseBody
	public AjaxResult getEmployOrderDetail(@RequestParam(value = "page", defaultValue = "1") int page,
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

		if (user.getGroupAuthority() != 2) {
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}

		List<EmployOrderShowModel> employOrderShowList = employMyOrderService.getEmployOrderDetailByPage(user.getId(),
				page, row, queryKey);
		int totalSize = employMyOrderService.getEmployOrderCount(user.getId(), row, queryKey);
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("employOrderShowList", employOrderShowList);
		ajaxResult.addData("totalSize", totalSize);
		// ajaxResult.addData("totalSize", 8);
		return ajaxResult;
	}

	/**
	 * 用户确认服务完成接口
	 * @param 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "confimeServiceFinish", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult confimeServiceFinish(@RequestBody EmployConfimeServiceModel employConfimeService,
			HttpServletRequest request) {

		System.out.println("employConfimeService:" + employConfimeService);
		AjaxResult ajaxResult = AjaxResult.fail();
		Users user = (Users) request.getSession().getAttribute("user");

		if (user.getGroupAuthority() != 2) {
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}

		String resultMsg = employMyOrderService.confimeServiceFinish(employConfimeService, user.getId());

		ajaxResult = AjaxResult.success();
		ajaxResult.addData("msg", resultMsg);
		return ajaxResult;
	}

	/**
	 * 用户评价接口
	 * @param employEvaluate
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveEvaluateDetail", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveEvaluateDetail(@RequestBody EmployEvaluateModel employEvaluate, HttpServletRequest request) {

		System.out.println("employEvaluate:" + employEvaluate);
		AjaxResult ajaxResult = AjaxResult.fail();
		Users user = (Users) request.getSession().getAttribute("user");

		if (user.getGroupAuthority() != 2) {
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}

		String resultMsg = employMyOrderService.saveEvaluateDetail(employEvaluate);

		ajaxResult = AjaxResult.success();
		ajaxResult.addData("msg", resultMsg);
		return ajaxResult;
	}

	/**
	 * 取消订单接口
	 * @param employEvaluate
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "cancelOrder")
	@ResponseBody
	public AjaxResult cancelOrder(@RequestParam(value = "token", defaultValue = "") String token,
			HttpServletRequest request) {

		AjaxResult ajaxResult = AjaxResult.fail();
		Users user = (Users) request.getSession().getAttribute("user");

		if (user.getGroupAuthority() != 2) {
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}

		String resultMsg = employMyOrderService.cancelOrder(token);

		ajaxResult = AjaxResult.success();
		ajaxResult.addData("msg", resultMsg);
		return ajaxResult;
	}

}
