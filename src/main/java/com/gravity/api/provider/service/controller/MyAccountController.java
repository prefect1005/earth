package com.gravity.api.provider.service.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.common.view.model.ProviderMyAccountIncomeModel;
import com.gravity.api.provider.service.service.MyAccountServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "providerMyAccount")
public class MyAccountController {

	@Autowired
	MyAccountServiceIF myAccountService;

	@RequestMapping(value = "")
	public String initMyAccount(Model model, HttpServletRequest request) {

		Users user = (Users) request.getSession().getAttribute("user");
		String basicInfoUrl = "";
		if (user.getTypeCp() == 1) {
			basicInfoUrl = "/providerBasicInfo/basicInfoCompany";
		} else {
			basicInfoUrl = "/providerBasicInfo/basicInfoPerson";
		}

		List<String> amountList = myAccountService.getAllAmountByUserId(user.getId());

		model.addAttribute("basicInfoUrl", basicInfoUrl);
		model.addAttribute("amountList", amountList); // 1总共，2现在，3级别
		for (String s : amountList) {
			System.out.println("sssss:" + s);
		}
		return "provider-my-account";
	}

	@RequestMapping(value = "getProviderIncomeDetail")
	@ResponseBody
	public AjaxResult getProviderIncomeDetail(@RequestParam(value = "page", defaultValue = "1") int page,
			HttpServletRequest request) {
		AjaxResult ajaxResult = AjaxResult.fail();
		Users user = (Users) request.getSession().getAttribute("user");

		List<ProviderMyAccountIncomeModel> providerMyAccountIncomeList = myAccountService.getProviderIncomeDetail(page,
				user.getId());
		int count = myAccountService.getProviderIncomeCount(user.getId());
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("providerMyAccountIncomeList", providerMyAccountIncomeList);
		ajaxResult.addData("count", count);
		return ajaxResult;
	}

	/**
	 * 提现接口
	 * 
	 * @param amount
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "providerWithDraw")
	@ResponseBody
	public AjaxResult providerWithDraw(@RequestParam(value = "amount", defaultValue = "0") String amount,
			HttpServletRequest request) {
		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		if (user.getGroupAuthority() != 1) { // 不是服务商，不能进行提现操作
			ajaxResult.addData("msg", "没有权限");
		}

		String result = myAccountService.providerWithDraw(amount, user.getId());
		if ("提现成功，请耐心等待平台处理".equals(result)) {
			ajaxResult = AjaxResult.success();
		}
		ajaxResult.addData("result", result);

		return ajaxResult;
	}

}
