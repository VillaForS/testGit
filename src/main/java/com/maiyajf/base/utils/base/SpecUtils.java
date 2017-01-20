/** 
 * SpecUtils.java
 * create on 2011-8-24
 * Copyright 2015 Todaysteel All Rights Reserved.
 */
package com.maiyajf.base.utils.base;

/**
 * 规格工具类
 * 
 * @author <a href="mailto:service@todaysteel.com">wangjianglin</a>
 * @since version1.0
 */
public class SpecUtils {

	/**
	 * 根据规格截取厚宽长
	 * @param sspec
	 * @return
	 */
	public static String[] getSpecABC(String sspec){
		String spec[] =  new String[3];
		if(sspec!=null){
			String sspecs[] = sspec.split("\\*");
			
			if(sspecs.length==2){
				spec[0] = sspecs[0].split("-")[0];
				spec[1] = sspecs[1].split("-")[0];
				spec[2] = null;
			}
			
			if(sspecs.length==3){
				spec[0] = sspecs[0].split("-")[0];
				spec[1] = sspecs[1].split("-")[0];
				spec[2] = sspecs[2].split("-")[0];
			}
		}
		return spec;
	}
}
