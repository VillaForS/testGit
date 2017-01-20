/** 
 * WebException.java
 * create on 2011-01-01
 * Copyright 2015 todaysteel All Rights Reserved.
 */
package com.maiyajf.base.exception;

/**
 * Action 异常类
 * @since version1.0
 */
public class WebException extends Exception {

	private static final long serialVersionUID = 5645227164781802573L;

	public WebException(String message) {
		super(message);
	}

	public WebException(Throwable cause) {
		super(cause);
	}

	public WebException(String message, Throwable cause) {
		super(message, cause);
	}

}
