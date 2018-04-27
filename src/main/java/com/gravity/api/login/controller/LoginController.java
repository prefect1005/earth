package com.gravity.api.login.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.gravity.api.common.view.model.UserModel;
import com.gravity.api.login.service.LoginServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.support.springframework.view.model.AjaxResult;
import com.gravity.common.util.EarthUtils;
import com.gravity.common.util.SAES;
import com.gravity.common.util.Utils;

@Controller
@SessionAttributes(value = { "user" }, types = { Users.class })
public class LoginController {

	@Autowired
	private LoginServiceIF loginService;

	@Value("#{config['config.login.session.tag']}")
	private String loginSessionTag;

	@Value("#{config['config.qencodekey']}")
	private String qencodekey;

	@RequestMapping(value = "/")
	public String initHome() {
		System.out.println("intisdfadsfadsfaergdfadfgadsf");
		return "redirect:/home/index";
	}

	@RequestMapping(value = "loginPage")
	public String initLogin(HttpServletRequest request, Model model) {

		String isrerpd = "";
		String rerpdcon = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("isrerpd".equals(cookie.getName())) {
					isrerpd = cookie.getValue();
				}
				if ("rerpdcon".equals(cookie.getName())) {
					rerpdcon = cookie.getValue();
				}
			}

			if ("true".equals(isrerpd)) {
				try {
					rerpdcon = URLDecoder.decode(rerpdcon);
					// String rerpdconafter = QEncodeUtil.aesDecrypt(rerpdcon,
					// qencodekey);
					// String rerpdconafter = AES.decrypt2Str(rerpdcon,
					// qencodekey);
					String rerpdconafter = SAES.decrypt(rerpdcon, qencodekey);
					String[] rerpdconafterArray = rerpdconafter.split(",");
					model.addAttribute("account", rerpdconafterArray[0]);
					model.addAttribute("password", rerpdconafterArray[1]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return "login";
	}

	@RequestMapping(value = "registerPage")
	public String initRegister() {
		return "register";
	}

	@RequestMapping(value = "findPwdPage")
	public String initFindPwd() {
		return "findPwd";
	}

	/**
	 * 登录接口
	 * 
	 * @param account
	 * @param password
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult login(@RequestParam("account") String account, @RequestParam("password") String password,
			@RequestParam("rememberPwd") boolean rememberPwd, Model model, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("account:" + account);
		System.out.println("password:" + password);
		System.out.println("rememberPwd:" + rememberPwd);
		AjaxResult ajaxResult = AjaxResult.fail();
		Users user = loginService.login(account);

		if (user == null) {
			ajaxResult.addData("error_msg", "用户不存在!");
		} else if (!BCrypt.checkpw(password, user.getPassword())) {
			ajaxResult.addData("error_msg", "密码不正确!");
		} else {
			model.addAttribute("user", user);
			String loginSessionAfterEncrypt = "";
			try {
				// loginSessionAfterEncrypt = QEncodeUtil.aesEncrypt(account +
				// "," + password, qencodekey);
				// loginSessionAfterEncrypt = AES.encrypt2Str(account + "," +
				// password, qencodekey);
				loginSessionAfterEncrypt = SAES.encryptAES(account + "," + password, qencodekey);
				// loginSessionAfterEncrypt =
				// URLEncoder.encode(loginSessionAfterEncrypt);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (rememberPwd) {
				response.addHeader("Set-Cookie", "isrerpd=true; path=/;");
				System.out.println("loginloginSessionAfterEncrypt:" + loginSessionAfterEncrypt);
				response.addHeader("Set-Cookie", "rerpdcon=" + loginSessionAfterEncrypt + "; path=/;");
			}
			response.addHeader("Set-Cookie", loginSessionTag + "=" + loginSessionAfterEncrypt + "; path=/;");
			String accountShow = loginService.getUserNameByUserId(user.getTypeCp(), user.getGroupAuthority(), user.getId());
			response.addHeader("Set-Cookie", "accountShow=" + URLEncoder.encode(accountShow, "utf-8") + "; path=/;");
			ajaxResult = AjaxResult.success();
			ajaxResult.addData("location", "/home/index");
		}

		return ajaxResult;
	}

	@RequestMapping(value = "quit")
	public String quit(@ModelAttribute("user") Users user, SessionStatus sessionStatus, HttpServletRequest request,
			HttpServletResponse response) {
		// response.addHeader("Set-Cookie", "isrerpd='undefined'; path=/;
		// Expires=-1");
		// response.addHeader("Set-Cookie", "rerpdcon='undefined'; path=/;
		// Expires=-1");
		// response.addHeader("Set-Cookie", loginSessionTag + "='undefined';
		// path=/; Expires=-1");
		// response.addHeader("Set-Cookie", "accountShow='undefined'; path=/;
		// Expires=-1");
		// TODO sission清除 上面的也不好使

		Cookie[] cookies = request.getCookies();

		if (null == cookies) {
			System.out.println("没有cookie==============");
		} else {
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				if ("isrerpd".equals(cookieName) || "rerpdcon".equals(cookieName) || loginSessionTag.equals(cookieName)
						|| "accountShow".equals(cookieName)) {
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}

		sessionStatus.setComplete();

		return "redirect:/home/index";
	}

	/**
	 * 测试接口
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "test")
	@ResponseBody
	public AjaxResult test(@ModelAttribute("user") Users user) {
		AjaxResult ajaxResult = AjaxResult.success();
		ajaxResult.addData("phone", user.getPhone());
		ajaxResult.addData("password", user.getPassword());
		return ajaxResult;
	}

	@RequestMapping(value = "test3")
	@ResponseBody
	public AjaxResult test3(@RequestParam("phone") String phone, @RequestParam("vcode") String vcode) {
		AjaxResult ajaxResult = AjaxResult.success();
		ajaxResult.addData("result", loginService.verifyVCode(phone, vcode));
		return ajaxResult;
	}

	/**
	 * 注册接口
	 * 
	 * @param user
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult register(@RequestBody UserModel user, Model model, HttpServletRequest request) {

		System.out.println("getPhone:" + user.getPhone());
		System.out.println("password:" + user.getPassword());
		System.out.println("getTypeCp:" + user.getTypeCp());
		System.out.println("getVCode:" + user.getVerCode());

		AjaxResult ajaxResult = AjaxResult.fail();

		// 先校验验证码是否正确
		if (!loginService.verifyVCode(user.getPhone(), user.getVerCode())) {
			ajaxResult.addData("message", "验证码不正确");
			return ajaxResult;
		}

		String passwordHashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(passwordHashed);

		Users loginUser = loginService.register(user);

		model.addAttribute("user", loginUser);

		ajaxResult = AjaxResult.success();
		ajaxResult.addData("location", "/loginPage");
		return ajaxResult;
	}

	@RequestMapping(value = "verifyPhoneVCode")
	@ResponseBody
	public AjaxResult verifyPhoneVCode(@RequestParam("mobile") String phone,
			@RequestParam("verifyCode") String verifyCode) {
		AjaxResult ajaxResult = AjaxResult.fail();
		if (!loginService.verifyVCode(phone, verifyCode)) {
			ajaxResult.addData("message", "验证码不正确");
			return ajaxResult;
		}
		ajaxResult = AjaxResult.success();
		return ajaxResult;
	}

	@RequestMapping(value = "verifyIsRegister")
	@ResponseBody
	public AjaxResult verifyIsRegister(@RequestParam("mobile") String phone) {
		AjaxResult ajaxResult = AjaxResult.fail();

		ajaxResult = AjaxResult.success();
		ajaxResult.addData("isRegister", loginService.verifyIsRegister(phone));
		return ajaxResult;
	}

	/**
	 * 获取验证码接口
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "getVCode")
	@ResponseBody
	public AjaxResult getVCode(@RequestParam("phone") String phone) {
		System.out.println("phone:" + phone);
		AjaxResult ajaxResult = AjaxResult.fail();
		if (phone != null && !"".equals(phone) && Utils.isPhoneNumber(phone) && loginService.getVCode(phone)) {
		//	 if (true) {
			ajaxResult = AjaxResult.success();
		}
		return ajaxResult;
	}

	/**
	 * 获取验证码接口
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "getVCodeByYzm")
	@ResponseBody
	public AjaxResult getVCodeByYzm(@RequestParam("mobile") String phone, @RequestParam("yzm") String yzm,
			HttpSession httpSession) {
		System.out.println("phone:" + phone);
		System.out.println("yzm:" + yzm);
		String randVCode = httpSession.getAttribute("rand").toString();
		System.out.println("randVCode:" + randVCode);
		AjaxResult ajaxResult = AjaxResult.fail();
		if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(yzm)
				&& StringUtils.equalsIgnoreCase(yzm, randVCode)) {
			if (!EarthUtils.isPhoneNumber(phone)) {
				ajaxResult.addData("message", "手机号码格式不正确");
				return ajaxResult;
			}
			if (loginService.getVCode(phone)) {
				ajaxResult = AjaxResult.success();
			}
		}
		return ajaxResult;
	}

	/**
	 * 校验图形验证码
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "verifyYzm")
	@ResponseBody
	public AjaxResult verifyYzm(@RequestParam("yzm") String yzm, HttpSession httpSession) {
		System.out.println("yzm:" + yzm);
		String randVCode = httpSession.getAttribute("rand").toString();
		System.out.println("randVCode:" + randVCode);
		AjaxResult ajaxResult = AjaxResult.fail();
		if (StringUtils.isNotBlank(yzm) && StringUtils.equalsIgnoreCase(yzm, randVCode)) {
			ajaxResult = AjaxResult.success();
		}
		return ajaxResult;
	}

	/**
	 * 重置密码
	 * 
	 * @param phone
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping(value = "resertPwd")
	@ResponseBody
	public AjaxResult resertPwd(@RequestParam("mobile") String phone, @RequestParam("verifyCode") String verifyCode,
			@RequestParam("pwd") String pwd) {
		AjaxResult ajaxResult = AjaxResult.fail();
		if (!loginService.verifyVCode(phone, verifyCode)) {
			ajaxResult.addData("message", "验证码不正确");
			return ajaxResult;
		}
		if (loginService.resetPwd(phone, pwd)) {
			ajaxResult.addData("message", "重置成功");
			ajaxResult = AjaxResult.success();
		}

		return ajaxResult;
	}

	@RequestMapping(value = "modifyPwd")
	@ResponseBody
	public AjaxResult modifyPwd(@RequestParam("oldPwdd") String oldPwd, @RequestParam("newPwdd") String newPwd,
			HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = AjaxResult.fail();

		if (StringUtils.isBlank(newPwd) || newPwd.length() < 6) {
			ajaxResult.addData("msg", "新密码小于六位,请重新输入");
			return ajaxResult;
		}

		Users user = (Users) request.getSession().getAttribute("user");
		System.out.println("user:" + user);
		if (user == null) {
			ajaxResult.addData("msg", "用户未登录");
			return ajaxResult;
		}

		boolean result = loginService.modifyPwd(user.getId(), oldPwd, newPwd);

		if (result) {
			ajaxResult = AjaxResult.success();
			ajaxResult.addData("location", "/loginPage");
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(loginSessionTag) || cookie.getName().equals("isrerpd")
							|| cookie.getName().equals("rerpdcon")) {
						cookie.setValue(null);
						cookie.setMaxAge(0);// 立即销毁cookie
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
			}

			return ajaxResult;
		}

		return ajaxResult;
	}

}
