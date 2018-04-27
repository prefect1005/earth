package com.gravity.api.home.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gravity.api.common.view.model.HomePayOrderShowModel;
import com.gravity.api.common.view.model.HomePlaceOrderModel;
import com.gravity.api.common.view.model.HomeSubmitOrderModel;
import com.gravity.api.common.view.model.OrderShowModel;
import com.gravity.api.home.service.OrderServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "order")
public class OrderController {

	@Value("#{filePath['file.order.data']}")
	private String orderFileReal;

	@Value("#{filePath['file.order.data.url']}")
	private String orderFileDownloadUrl;

	@Autowired
	OrderServiceIF orderService;

	// 服务商详情页面点击立即下单调用接口,返回下订单页面
	@RequestMapping(value = "placeOrder", method = RequestMethod.POST)
	public String placeOrder(@RequestBody HomePlaceOrderModel placeOrder, Model model) {
		System.out.println("placeOrder:" + placeOrder);
		OrderShowModel orderShow = orderService.getOrderShowDetail(placeOrder);
		System.out.println("orderShow:" + orderShow);
		model.addAttribute("orderShow", orderShow);
		return "order-produce";
	}

	// 下订单页面，点击提交订单按钮，生成订单，返回支付页面
	@RequestMapping(value = "submit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult submitOrder(@RequestBody HomeSubmitOrderModel submitOrder, Model model, HttpServletRequest request) {
		
		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);

		if (user.getGroupAuthority() != 2) { // 如果不是2的话，说明不是雇佣方，不允许继续
			ajaxResult.addData("msg", "权限不符");
			return ajaxResult;
		}

		HomePayOrderShowModel payOrderShow = orderService.submitOrder(submitOrder, user.getId());
		model.addAttribute("payOrderShow", payOrderShow);
		System.out.println("payOrderShow:" + payOrderShow);
		
		ajaxResult = AjaxResult.success();
		ajaxResult.addData("msg", "");
		ajaxResult.addData("location", "/order/payOrderParams?idNo="+payOrderShow.getOrderNo()+"&token="+payOrderShow.getToken());
		return ajaxResult;
	}

	// 在我的订单页面点击付款跳转的链接
	@RequestMapping(value = "payOrderParams")
	public String payOrderPage(@RequestParam(value = "idNo") String idNo, @RequestParam(value = "token") String token,
			Model model, HttpServletRequest request) {

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);

		HomePayOrderShowModel payOrderShow = orderService.payOrderPage(idNo, token, user.getId());
		model.addAttribute("payOrderShow", payOrderShow);
		System.out.println("payOrderShow:" + payOrderShow);
		return "pay-order";
	}

	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult uploadFile(@RequestParam("file") MultipartFile resumeFile) {
		AjaxResult ajaxResult = AjaxResult.fail();
		if (!resumeFile.isEmpty()) {
			String fullName = resumeFile.getOriginalFilename();
			System.out.println("fullName:" + fullName);

			StringBuffer imageName = new StringBuffer();
			imageName.append(FilenameUtils.getBaseName(fullName));
			imageName.append("_");
			imageName.append(RandomStringUtils.randomAlphanumeric(10));
			imageName.append(".");
			imageName.append(FilenameUtils.getExtension(fullName));

			String fileNameString = imageName.toString().replaceAll(",", "");// 去掉文件名称中的逗号,为数据库中存储的照片数组排除错误
			String filePath = orderFileReal + fileNameString;

			System.out.println("filePath:" + filePath);

			try {
				resumeFile.transferTo(new File(filePath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ajaxResult = AjaxResult.success();
			ajaxResult.addData("fileName", fileNameString);
		}
		return ajaxResult;

	}

}
