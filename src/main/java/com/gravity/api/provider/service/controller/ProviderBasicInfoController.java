package com.gravity.api.provider.service.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
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

import com.gravity.api.common.view.model.ProviderBasicInfoCompanyModel;
import com.gravity.api.common.view.model.ProviderBasicInfoPersonModel;
import com.gravity.api.provider.service.service.ProviderBasicInfoServiceIF;
import com.gravity.api.provider.service.service.PublishServiceServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;

@Controller
@RequestMapping(value = "providerBasicInfo")
public class ProviderBasicInfoController {

	@Autowired
	ProviderBasicInfoServiceIF providerBasicInfoService;
	
	@Autowired
	private PublishServiceServiceIF publishServiceService;

	@Value("#{filePath['image.provider.indenty']}")
	private String providerIndentificationReal;

	@Value("#{filePath['image.provider.indenty.url']}")
	private String providerIndentyImageUrl;

	@RequestMapping(value = "basicInfoPerson")
	public String initBasicInfoPersonPage(Model model, HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if ((user.getGroupAuthority() != 1) || (user.getTypeCp() != 2)) {
			return "index"; // 身份信息不符
		}

		ProviderBasicInfoPersonModel providerBasicInfoPerson = providerBasicInfoService
				.getBasicInfoPersonByUserId(user.getId());
		System.out.println("providerBasicInfoPerson:" + providerBasicInfoPerson);
		
		int isApprove = publishServiceService.getIsApproveByUserId(user.getId(), user.getTypeCp());
		
		model.addAttribute("providerBasicInfoPerson", providerBasicInfoPerson);
		model.addAttribute("isApprove", isApprove);

		return "provider-basic-info-person";
	}

	@RequestMapping(value = "basicInfoCompany")
	public String initBasicInfoCompanyPage(Model model, HttpServletRequest request) {

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if ((user.getGroupAuthority() != 1) || (user.getTypeCp() != 1)) {
			return "index"; // 身份信息不符
		}

		ProviderBasicInfoCompanyModel providerBasicInfoCompany = providerBasicInfoService
				.getBasicInfoCompanyByUserId(user.getId());
		int isApprove = publishServiceService.getIsApproveByUserId(user.getId(), user.getTypeCp());
		model.addAttribute("providerBasicInfoCompany", providerBasicInfoCompany);
		model.addAttribute("isApprove", isApprove);

		return "provider-basic-info-company";
	}

	@RequestMapping(value = "updateBasicInfoCompany", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult updateProviderBasicInfoCompanyDetail(
			@RequestBody ProviderBasicInfoCompanyModel providerBasicInfoCompanyModel, HttpServletRequest request) {
		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if ((user.getGroupAuthority() != 1) || (user.getTypeCp() != 1)) { // 如果不是1的话，说明不是服务商，不允许发布;如果不是企业也不允许继续
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}
		
		String homeDisplaySrc = providerBasicInfoCompanyModel.getStoreDisplayImg();
		if (!StringUtils.isBlank(homeDisplaySrc)) {
			if (homeDisplaySrc.contains("static/img/store_home_default_display")) {
				providerBasicInfoCompanyModel.setStoreDisplayImg("");
			} else {
				homeDisplaySrc = homeDisplaySrc.substring(homeDisplaySrc.indexOf("/serinden/") + 10);
				providerBasicInfoCompanyModel.setStoreDisplayImg(homeDisplaySrc);
			}
		}
		System.out.println(providerBasicInfoCompanyModel);

		boolean result = providerBasicInfoService.updateBasicInfoCompanyByIdAndUserId(providerBasicInfoCompanyModel,
				user.getId());

		if (result) {
			ajaxResult = AjaxResult.success();
		}
		return ajaxResult;
	}

	@RequestMapping(value = "updateBasicInfoPerson", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult updateProviderBasicInfoPersonDetail(
			@RequestBody ProviderBasicInfoPersonModel providerBasicInfoPerson, HttpServletRequest request) {
		AjaxResult ajaxResult = AjaxResult.fail();

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if ((user.getGroupAuthority() != 1) || (user.getTypeCp() != 2)) { // 如果不是1的话，说明不是服务商，不允许发布;如果不是企业也不允许继续
			ajaxResult.addData("message", "级别不符");
			return ajaxResult;
		}
		
		String homeDisplaySrc = providerBasicInfoPerson.getStoreDisplayImg();
		if (!StringUtils.isBlank(homeDisplaySrc)) {
			if (homeDisplaySrc.contains("static/img/store_home_default_display")) {
				providerBasicInfoPerson.setStoreDisplayImg("");
			} else {
				homeDisplaySrc = homeDisplaySrc.substring(homeDisplaySrc.indexOf("/serinden/") + 10);
				providerBasicInfoPerson.setStoreDisplayImg(homeDisplaySrc);
			}
		}

		System.out.println(providerBasicInfoPerson);

		boolean result = providerBasicInfoService.updateBasicInfoPersonByIdAndUserId(providerBasicInfoPerson,
				user.getId());

		if (result) {
			ajaxResult = AjaxResult.success();
		}
		return ajaxResult;
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

			String imageNameString = imageName.toString().replaceAll(",", "");// 去掉文件名称中的逗号,为数据库中存储的照片数组排除错误
			String filePath = providerIndentificationReal + imageNameString;

			System.out.println("filePath:" + filePath);

			try {
				resumeFile.transferTo(new File(filePath));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ajaxResult = AjaxResult.success();
			ajaxResult.addData("imageSrc", providerIndentyImageUrl + imageNameString);
		}

		return ajaxResult;

	}

}
