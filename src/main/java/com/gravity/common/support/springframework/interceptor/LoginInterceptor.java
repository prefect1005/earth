package com.gravity.common.support.springframework.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.gravity.api.login.service.LoginServiceIF;
import com.gravity.common.support.mybatis.auto.model.Users;
import com.gravity.common.util.SAES;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Value("#{config['config.login.url']}")
	private String LOGIN_URL;

	@Value("#{config['config.login.session.tag']}")
	private String loginSessionTag;

	@Value("#{config['config.qencodekey']}")
	private String qencodekey;

	@Autowired
	private LoginServiceIF loginService;

	private static final String[] IGNORE_URI = { "/loginPage", "/login", "/registerPage", "/register", "/verifyYzm",
			"/resertPwd", "/findPwdPage", "/authImage", "/getVCode", "/static", "/img", "favicon.ico", "error.html",
			"/verifyPhoneVCode", "/home", "/serviceDetail", "/serviceList", "/shopInfo", "/verifyIsRegister", "/pay/wechatPayReturnMsg"};

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String url = request.getRequestURL().toString();
		System.out.println(">>>: " + url);
		for (String s : IGNORE_URI) {
			// System.out.println("s:" + s);
			if (url.contains(s)) {
				System.out.println("sssss: " + s);
				return true;
			}
		}
		
		if ("http://localhost:8080/".equals(url)) {
			return true;
		}
		
		if ("http://121.42.44.172/".equals(url)) {
			return true;
		}
		
		if ("http://www.xdixin.com/".equals(url)) {
			return true;
		}

		HttpSession session = request.getSession(true);
		// 从session 里面获取用户名的信息
		Object user = session.getAttribute("user");
		// 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
		if (user == null || "".equals(user.toString())) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				String loginSessionAfterEncrypt = "";
				for (Cookie cookie : cookies) {
					if (loginSessionTag.equals(cookie.getName())) {
						loginSessionAfterEncrypt = cookie.getValue();
						//loginSessionAfterEncrypt = URLDecoder.decode(loginSessionAfterEncrypt);
						break;
					}
				}
				System.out.println("interceptorloginSessionAfterEncrypt:" + loginSessionAfterEncrypt);
				if (!"".equals(loginSessionAfterEncrypt)) {
					//String loginSessionFromCookieString = QEncodeUtil.aesDecrypt(loginSessionAfterEncrypt, qencodekey);
					//String loginSessionFromCookieString = AES.decrypt2Str(loginSessionAfterEncrypt, qencodekey);
					String loginSessionFromCookieString = SAES.decrypt(loginSessionAfterEncrypt, qencodekey);
					System.out.println("loginSessionFromCookieString:" + loginSessionFromCookieString);
					String[] loginSessionArray = loginSessionFromCookieString.split(",");
					Users userLogin = loginService.login(loginSessionArray[0]);
					if (userLogin != null && BCrypt.checkpw(loginSessionArray[1], userLogin.getPassword())) {
						request.getSession().setAttribute("user", userLogin);
						return true;
					}
				}
			}
			response.sendRedirect(LOGIN_URL);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

}
