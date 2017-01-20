package com.maiyajf.base.card.po;

import java.io.Serializable;

/**
 * 麦芽平台系统与第三方系统编码映射表PO
 * 
 * @author dell
 *
 */
public class ZdCodePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sGuid;// 主键
	private String sName;// Key
	private String sValue;// Value
	private int iDelFlag;// 默认为1:有效0：无效

	public String getsGuid() {
		return sGuid;
	}

	public void setsGuid(String sGuid) {
		this.sGuid = sGuid;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsValue() {
		return sValue;
	}

	public void setsValue(String sValue) {
		this.sValue = sValue;
	}

	public int getiDelFlag() {
		return iDelFlag;
	}

	public void setiDelFlag(int iDelFlag) {
		this.iDelFlag = iDelFlag;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ZdCodePO [sGuid=");
		builder.append(sGuid);
		builder.append(", sName=");
		builder.append(sName);
		builder.append(", sValue=");
		builder.append(sValue);
		builder.append(", iDelFlag=");
		builder.append(iDelFlag);
		builder.append("]");
		return builder.toString();
	}
}
