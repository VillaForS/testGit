/** 
 * BankException.java
 * create on 2011-4-25
 * Copyright 2020 Todaysteel All Rights Reserved.
 */
package com.maiyajf.base.exception;

/**
 * 用来截获注册的错误消息
 * 
 * @author <a href=" mailto:haifeng.cauc@gmail.com">chain</a>
 * @since version1.0
 */
public class RegException extends RuntimeException {

	private static final long serialVersionUID = 8329144444757379351L;

	public RegException(String message) {
		super(message);
	}

	public RegException(Throwable cause) {
		super(cause);
	}

	public RegException(String message, Throwable cause) {
		super(message, cause);
	}
}
