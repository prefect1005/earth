package com.gravity.common.support.springframework.view.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author bizhenchao
 */
@Setter
@Getter
@ToString
public class AjaxResult {
	private boolean isSuccess;
	private Map<String, Object> data = new HashMap<String, Object>();

	public AjaxResult addData(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	public AjaxResult addErrorMessage(String message) {
		this.data.put("errorMessage", message);
		return this;
	}

	public static AjaxResult success() {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.isSuccess = true;
		return ajaxResult;
	}

	public static AjaxResult fail() {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.isSuccess = false;
		return ajaxResult;
	}

}
