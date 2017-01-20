/** 
 * DaoException.java
 * create on 2011-01-01
 * Copyright 2015 todaysteel All Rights Reserved.
 */
package com.maiyajf.base.exception;

/**
 * DAO运行时异常类
 * @since version1.0
 */
public class DaoRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 6940097306353628661L;

	public DaoRuntimeException(String message) {
		super(message);
	}

	public DaoRuntimeException(Throwable cause) {
		super(cause);
	}

	public DaoRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
