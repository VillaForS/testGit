package com.maiyajf.base.card.po;

import java.io.Serializable;

/**
 * 卡BIN缓存实体对象
 * 
 * @author dell
 *
 */
public class ZdCardBinPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6976982609277988587L;

	private String sCardBin;// 卡BIN

	private String sBankName;// 银行名称

	private int iCardLength;// 卡长度

	private int iCardType;// 卡类型

	private String sBankNo;// 银行编号

	private int iDelFlag;// 是否支持某银行

	private String icon;// 银行头像

	private int functionType;// 功能 功能1:放款，2:自动代扣，3:快捷支付

	private String sSimpleCode;// 银行简码
	
	private String sBankCode;
	
	public String getsBankCode() {
		return sBankCode;
	}

	public void setsBankCode(String sBankCode) {
		this.sBankCode = sBankCode;
	}

	public String getsSimpleCode() {
		return sSimpleCode;
	}

	public void setsSimpleCode(String sSimpleCode) {
		this.sSimpleCode = sSimpleCode;
	}

	public String getsBankNo() {
		return sBankNo;
	}

	public void setsBankNo(String sBankNo) {
		this.sBankNo = sBankNo;
	}

	public String getsCardBin() {
		return sCardBin;
	}

	public void setsCardBin(String sCardBin) {
		this.sCardBin = sCardBin;
	}

	public String getsBankName() {
		return sBankName;
	}

	public void setsBankName(String sBankName) {
		this.sBankName = sBankName;
	}

	public int getiCardLength() {
		return iCardLength;
	}

	public void setiCardLength(int iCardLength) {
		this.iCardLength = iCardLength;
	}

	public int getiCardType() {
		return iCardType;
	}

	public void setiCardType(int iCardType) {
		this.iCardType = iCardType;
	}

	public int getiDelFlag() {
		return iDelFlag;
	}

	public void setiDelFlag(int iDelFlag) {
		this.iDelFlag = iDelFlag;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getFunctionType() {
		return functionType;
	}

	public void setFunctionType(int functionType) {
		this.functionType = functionType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ZdCardBinPO [sCardBin=");
		builder.append(sCardBin);
		builder.append(", sBankName=");
		builder.append(sBankName);
		builder.append(", iCardLength=");
		builder.append(iCardLength);
		builder.append(", iCardType=");
		builder.append(iCardType);
		builder.append(", sBankNo=");
		builder.append(sBankNo);
		builder.append(", iDelFlag=");
		builder.append(iDelFlag);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", functionType=");
		builder.append(functionType);
		builder.append(", sSimpleCode=");
		builder.append(sSimpleCode);
		builder.append("]");
		return builder.toString();
	}
}
