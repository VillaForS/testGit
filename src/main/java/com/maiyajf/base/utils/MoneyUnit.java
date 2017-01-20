package com.maiyajf.base.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * 金额单位转换
 * 
 * @author 冯星星
 * 
 */
public class MoneyUnit {

	/**
	 * 分转元
	 * 
	 * @param n
	 * @return
	 */
	public static String fen2yuanStr(long fen) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setRoundingMode(RoundingMode.HALF_UP); // 设置四舍五入
		nf.setMinimumFractionDigits(0); // 设置最小保留几位小数
		nf.setMaximumFractionDigits(2); // 设置最大保留几位小数
		return nf.format(fen / 100.0);
	}

	/**
	 * 分转元
	 * 
	 * @param n
	 * @return
	 */
	public static String fen2yuanStr(Long fen) {
		if (fen == null)
			throw new RuntimeException("Long类型参数为null");
		return fen2yuanStr(fen.longValue());
	}

	/**
	 * 分转元
	 * 
	 * @param n
	 * @return
	 */
	public static String fen2yuanStr(String fen) {
		// 整数检查
		if (!RegexUtil.isMatcher("^\\d+$", fen)) {
			throw new RuntimeException("数据格式非法：fen=" + fen);
		}
		return fen2yuanStr(Long.parseLong(fen));
	}

	/**
	 * 分转元
	 * 
	 * @param n
	 * @return
	 */
	public static Double fen2yuanDouble(String fen) {
		return Double.parseDouble(fen2yuanStr(fen));
	}

	/**
	 * 元转分
	 * 
	 * @param n
	 * @return
	 */
	public static String yuan2fenStr(double yuan) {
		return String.valueOf(Math.round(100 * yuan));
	}

	/**
	 * 元转分
	 * 
	 * @param n
	 * @return
	 */
	public static String yuan2fenStr(Double yuan) {
		if (yuan == null)
			throw new RuntimeException("Double类型参数为null");
		return yuan2fenStr(yuan.doubleValue());
	}

	/**
	 * 元转分
	 * 
	 * @param n
	 * @return
	 */
	public static String yuan2fenStr(String yuan) {
		// 小数格式检查
		if (!RegexUtil.isMatcher("^\\d*(\\.\\d+)?$", yuan)) {
			throw new RuntimeException("数据格式非法：yuan=" + yuan);
		}
		return yuan2fenStr(Double.parseDouble(yuan));
	}

}
