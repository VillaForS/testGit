/** 
 * ValidateResult.java
 * create on 2011-01-01
 * Copyright 2015 todaysteel All Rights Reserved.
 */
package com.maiyajf.base.utils.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 验证结果类
 * 
 * 如果验证通过, 则保持此类的当前状态; 如果验证失败, 则设置successful为false, 并且将验证出错的对象放入objs里面.
 * objs对象是以当前对象为key, 其值为一个java.util.List，包含字符串数组对象。 此数组包含三个元素，第一个表示 报错的属性名称，
 * 第二个表示属性值，第三个表示报错信息。
 * 
 * @since version1.0
 */
public class ValidateResult {

	// 验证是否通过
	private boolean failure;

	/**
	 * 验证失败对象集合
	 * 
	 * Map对象说明： key-当前对象; value-String[]{当前属性名称，属性值，错误提示}
	 */
	private Map objs;

	public ValidateResult() {
		failure = false;
		objs = new HashMap();
	}

	public boolean isFailure() {
		return failure;
	}

	public void setFailure(boolean failure) {
		this.failure = failure;
	}

	public Map getObjs() {
		return objs;
	}

	public void setObjs(Map objs) {
		this.objs = objs;
	}
}
