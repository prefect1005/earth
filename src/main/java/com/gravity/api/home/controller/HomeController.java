package com.gravity.api.home.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gravity.api.home.service.HomeServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "home")
public class HomeController {

	@Value("#{config['config.cur.location']}")
	private String curLocationName;

	@Autowired
	HomeServiceIF homeService;

	@RequestMapping(value = "index")
	public String initHomePage(HttpServletRequest request, HttpServletResponse response, Model model) {

		String curLocation = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (curLocationName.equals(cookie.getName())) {
					curLocation = cookie.getValue();
					break;
				}
			}
		}

		if ("".equals(curLocation)) {
			response.addHeader("Set-Cookie", curLocationName + "=all; path=/;");
		}

		List<String> logoList = homeService.getHomeCompanyLogoUrl();
		
		Users user = (Users) request.getSession().getAttribute("user");
		if (user!= null && user.getGroupAuthority() == 3) {
			model.addAttribute("isAdmin", "caibudaoAdmin");
		} else {
			model.addAttribute("isAdmin", false);
		}
		model.addAttribute("logoList", logoList);
		return "index";
	}

	/*
	 * 获取网站的分类
	 */
	@RequestMapping(value = "getCategroy")
	@ResponseBody
	public AjaxResult getCategroy() {
		AjaxResult ajaxResult = AjaxResult.fail();

		Map<String, Map<Integer, String>> category = homeService.getCategory();

		ajaxResult = AjaxResult.success();
		ajaxResult.addData("category", category);
		return ajaxResult;
	}

	/*
	 * 获取网站现有服务的服务所在地
	 */
	@RequestMapping(value = "getServiceLocation")
	@ResponseBody
	public AjaxResult getServiceLocation() {
		AjaxResult ajaxResult = AjaxResult.fail();

		Set<Map<Integer, Integer>> locationList = homeService.getServiceLocation();

		ajaxResult = AjaxResult.success();
		ajaxResult.addData("locationList", locationList);
		return ajaxResult;
	}

	/*
	 * 获取首页底部公司logo展示的url
	 */
	@RequestMapping(value = "getLogoUrl")
	@ResponseBody
	public AjaxResult getHomeCompanyLogoUrl() {
		AjaxResult ajaxResult = AjaxResult.fail();

		List<String> logoList = homeService.getHomeCompanyLogoUrl();

		ajaxResult = AjaxResult.success();
		ajaxResult.addData("logoList", logoList);
		return ajaxResult;
	}

}
