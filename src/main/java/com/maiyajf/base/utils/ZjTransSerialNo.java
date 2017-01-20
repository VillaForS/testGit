package com.maiyajf.base.utils;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * 主要应用于中金绑卡快捷支付等交易流水号的生成，包括批次号，序列号，支付编号，流水号等。
 * 
 * @author jxx
 * @modify 2016-02-26 15:21
 */
public abstract class ZjTransSerialNo {
	private static Random random = new Random();
	private static String format = "yyyyMMddHHmmss";
//	private static SimpleDateFormat sdf = new SimpleDateFormat(format);

	/**
	 * 32位数字字符串：14位日期 + 18位随机数。
	 * 
	 * @return
	 */
	public static String getSerialNo() {
		return new SimpleDateFormat(format).format(System.currentTimeMillis()) + getRandomNumber(18);
	}

	/**
	 * 
	 * @param len
	 * @return
	 */
	public static String getRandomNumber(int len) {
		String str = "" + Math.abs(random.nextLong());
		while (str.length() < len) {
			str = str + Math.abs(random.nextLong());
		}
		return str.substring(0, len);
	}

	public static void main(String[] args) {
		System.out.println(getSerialNo());
	}
	
	/**
	 * 30位数字字符串：14位日期 + 16位随机数。
	 * 
	 * @return
	 */
	public static String getSerialNo30() {
//		if (sdf == null) {
//			synchronized (sdf) {
//				if (sdf == null) {
//					sdf = new SimpleDateFormat(format);
//				}
//			}
//		}
//		return sdf.format(System.currentTimeMillis()) + getRandomNumber(16);
		return new SimpleDateFormat(format).format(System.currentTimeMillis()) + getRandomNumber(16);
	}
}
