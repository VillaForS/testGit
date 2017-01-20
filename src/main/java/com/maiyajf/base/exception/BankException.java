/** 
 * BankException.java
 * create on 2011-4-25
 * Copyright 2020 Todaysteel All Rights Reserved.
 */
package com.maiyajf.base.exception;

/**
 * 用来截获银行返回的错误消息
 * 
 * @author <a href=" mailto:haifeng.cauc@gmail.com">chain</a>
 * @since version1.0
 */
public class BankException extends RuntimeException {

	private static final long serialVersionUID = 8320144444757379351L;

	public BankException(String message) {
		super(message);
	}

	public BankException(Throwable cause) {
		super(cause);
	}

	public BankException(String message, Throwable cause) {
		super(message, cause);
	}
}
