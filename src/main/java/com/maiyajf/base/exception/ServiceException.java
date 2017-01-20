/** 
 * ServiceException.java
 * create on 2011-01-01
 * Copyright 2015 todaysteel All Rights Reserved.
 */
package com.maiyajf.base.exception;

/**
 * Service异常类
 * @since version1.0
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 7528648370607530970L;
	
	private String errCode;
	
	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message, String errCode) {
		super(message);
		this.errCode = errCode;
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
