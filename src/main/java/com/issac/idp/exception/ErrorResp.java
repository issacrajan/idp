/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.idp.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author issac
 *
 */
public class ErrorResp {

	private int errorCode;
	private String errorValue;
	private String message;
	private Map<String, String> errors;

	public ErrorResp(int errorCode, String errorValue, String message) {
		this.errorCode = errorCode;
		this.errorValue = errorValue;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorValue() {
		return errorValue;
	}

	public void setErrorValue(String errorValue) {
		this.errorValue = errorValue;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public void addError(String errField, String errMessage) {
		if (errors == null) {
			errors = new HashMap<>();
		}
		errors.put(errField, errMessage);
	}

	@Override
	public String toString() {
		return "ExceptionResp [message=" + message + ", errorCode=" + errorCode + ", errorValue=" + errorValue
				+ ", errors=" + errors + "]";
	}

}
