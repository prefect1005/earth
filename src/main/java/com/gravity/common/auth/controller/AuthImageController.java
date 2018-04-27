package com.gravity.common.auth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gravity.common.util.VerifyCodeUtils;

@Controller
@RequestMapping(value = "authImage")
public class AuthImageController {
	
	@RequestMapping(value = "refresh")
	public void refresh(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		System.out.println("AuthImageController.verifyCode:" + verifyCode);
		// 存入会话session
		HttpSession session = request.getSession(true);
		session.setAttribute("rand", verifyCode.toLowerCase());
		// 生成图片
		int w = 150, h = 50;
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
	}

}
