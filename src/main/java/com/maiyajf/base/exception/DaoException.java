/** 
 * DaoException.java
 * create on 2011-01-01
 * Copyright 2015 todaysteel All Rights Reserved.
 */
package com.maiyajf.base.exception;

/**
 * DAO异常类
 * @since version1.0
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 6940097306353628661L;

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
