/** 
 * FormatConstants.java
 * create on 2011-01-01
 * Copyright 2015 todaysteel All Rights Reserved.
 */
package com.maiyajf.base.utils.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 日期格式化类
 * @since version1.0
 */
public abstract class FormatConstants {
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd",java.util.Locale.CHINA);
	public static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss",java.util.Locale.CHINA);	
	public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",java.util.Locale.CHINA);
}
