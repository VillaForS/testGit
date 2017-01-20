package com.maiyajf.base.po;

import java.io.Serializable;

/**
 * @ClassName: BaseEntity
 * @Description: 统一定义id的entity基类
 * @author: yunlei.hua
 * @date: 2015年9月15日 下午5:50:46
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
