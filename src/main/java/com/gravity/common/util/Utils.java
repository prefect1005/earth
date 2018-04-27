package com.gravity.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	/**
	 * 判断账号是否为phoneNumber
	 */
	public static boolean isPhoneNumber(String account) {
		Pattern pattern = Pattern.compile("^1[3|4|5|7|8]\\d{9}$");
		Matcher m = pattern.matcher(account);
		return m.matches();
	}

}
