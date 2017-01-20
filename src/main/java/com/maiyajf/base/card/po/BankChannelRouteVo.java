package com.maiyajf.base.card.po;

import java.io.Serializable;

/**
 * @ClassName: BankChannelRouteVo
 * @Description: 银行支付渠道路由
 * @author: yunlei.hua
 * @date: 2016年9月18日 下午2:18:26
 */
public class BankChannelRouteVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sBankNo;
    private String sCpNo;
    private String sCpNoBackup;
    private int iPayType;

    public String getsBankNo() {
        return sBankNo;
    }

    public void setsBankNo(String sBankNo) {
        this.sBankNo = sBankNo;
    }

    public String getsCpNo() {
        return sCpNo;
    }

    public void setsCpNo(String sCpNo) {
        this.sCpNo = sCpNo;
    }

    public String getsCpNoBackup() {
        return sCpNoBackup;
    }

    public void setsCpNoBackup(String sCpNoBackup) {
        this.sCpNoBackup = sCpNoBackup;
    }

    public int getiPayType() {
        return iPayType;
    }

    public void setiPayType(int iPayType) {
        this.iPayType = iPayType;
    }

    @Override
    public String toString() {
        return "BankChannelRouteVo [sBankNo=" + sBankNo + ", sCpNo=" + sCpNo + ", sCpNoBackup=" + sCpNoBackup
                + ", iPayType=" + iPayType + "]";
    }

}
