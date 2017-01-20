package com.maiyajf.base.card.po;

import java.io.Serializable;

public class ZdBankNoConfigPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String sGuid;

	private String bankNo;

	private String bankName;

	private String iDelFlag;

	private String icon;

	private String sBankCode;

	private String sSimpleCode;

	public String getsGuid() {
		return sGuid;
	}

	public void setsGuid(String sGuid) {
		this.sGuid = sGuid;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getiDelFlag() {
		return iDelFlag;
	}

	public void setiDelFlag(String iDelFlag) {
		this.iDelFlag = iDelFlag;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ZdBankNoConfigPO [sGuid=");
		builder.append(sGuid);
		builder.append(", bankNo=");
		builder.append(bankNo);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", iDelFlag=");
		builder.append(iDelFlag);
		builder.append(", icon=");
		builder.append(icon);
		builder.append(", sBankCode=");
		builder.append(sBankCode);
		builder.append(", sSimpleCode=");
		builder.append(sSimpleCode);
		builder.append("]");
		return builder.toString();
	}
}
