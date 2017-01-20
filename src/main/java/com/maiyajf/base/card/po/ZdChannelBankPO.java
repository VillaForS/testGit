package com.maiyajf.base.card.po;

import java.io.Serializable;

/**
 * 渠道银行管理表PO
 * 
 * @author dell
 *
 */
public class ZdChannelBankPO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String sGuid;// 主键
    private String sCpNo;// 渠道产品编码
    private String sBankNo;// 银行编码
    private int iCardType;// 卡类型 1:借记卡 2:信用卡
    private double iSingleQuota;// 单笔限额，单位分，-1表示不限金额
    private double iDayQuota;// 单日限额，单位分，-1表示不限金额
    private double iMonthQuota;// 单月限额，单位分，-1表示不限金额
    private int iDelFlag;// 默认为1:有效0：无效
    private int iIsProtocolNo;// 是否需要协议号（默认：0，1：需要，0：不需要）

    private String bankName;// 银行名称
    private String icon; // 银行icon

    public String getsGuid() {
        return sGuid;
    }

    public void setsGuid(String sGuid) {
        this.sGuid = sGuid;
    }

    public String getsCpNo() {
        return sCpNo;
    }

    public void setsCpNo(String sCpNo) {
        this.sCpNo = sCpNo;
    }

    public String getsBankNo() {
        return sBankNo;
    }

    public void setsBankNo(String sBankNo) {
        this.sBankNo = sBankNo;
    }

    public int getiCardType() {
        return iCardType;
    }

    public void setiCardType(int iCardType) {
        this.iCardType = iCardType;
    }

    public double getiSingleQuota() {
        return iSingleQuota;
    }

    public void setiSingleQuota(double iSingleQuota) {
        this.iSingleQuota = iSingleQuota;
    }

    public double getiDayQuota() {
        return iDayQuota;
    }

    public void setiDayQuota(double iDayQuota) {
        this.iDayQuota = iDayQuota;
    }

    public double getiMonthQuota() {
        return iMonthQuota;
    }

    public void setiMonthQuota(double iMonthQuota) {
        this.iMonthQuota = iMonthQuota;
    }

    public int getiDelFlag() {
        return iDelFlag;
    }

    public void setiDelFlag(int iDelFlag) {
        this.iDelFlag = iDelFlag;
    }

    public int getiIsProtocolNo() {
        return iIsProtocolNo;
    }

    public void setiIsProtocolNo(int iIsProtocolNo) {
        this.iIsProtocolNo = iIsProtocolNo;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ZdChannelBankPO [sGuid=");
        builder.append(sGuid);
        builder.append(", sCpNo=");
        builder.append(sCpNo);
        builder.append(", sBankNo=");
        builder.append(sBankNo);
        builder.append(", iCardType=");
        builder.append(iCardType);
        builder.append(", iSingleQuota=");
        builder.append(iSingleQuota);
        builder.append(", iDayQuota=");
        builder.append(iDayQuota);
        builder.append(", iMonthQuota=");
        builder.append(iMonthQuota);
        builder.append(", iDelFlag=");
        builder.append(iDelFlag);
        builder.append(", iIsProtocolNo=");
        builder.append(iIsProtocolNo);
        builder.append("]");
        return builder.toString();
    }
}
