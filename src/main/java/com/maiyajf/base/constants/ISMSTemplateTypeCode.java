package com.maiyajf.base.constants;

/**
 * @ClassName: ISMSTemplateTypeCode
 * @Description: 短信发送类型
 * @author: yunlei.hua
 * @date: 2015年11月11日 下午5:22:50
 */
public interface ISMSTemplateTypeCode {

	/** 注册验证码 */
	String REGISTER_CODE = "REGISTER_CODE";
	/** 银行卡变更 */
	String BANKCARD_CHANGE = "BANKCARD_CHANGE";
	/** 注册成功 */
	String REGISTER_SUCCESS = "REGISTER_SUCCESS";
	/** 修改密码 */
	String MODIFY_PASSWORD = "MODIFY_PASSWORD";
	/** 实名认证信息/银行卡认证信息审核结果通知 */
	String AUTHENTICATION_SMS = "AUTHENTICATION_SMS";
	/** 借款申请成功 */
	String LOAN_APPLY_SUCCESS = "LOAN_APPLY_SUCCESS";
	/** 借款申请失败 */
	String LOAN_APPLY_FAIL = "LOAN_APPLY_FAIL";
	/** 成功放款 */
	String GRAND_SUCCESS = "GRAND_SUCCESS";
	/** 还款提醒 */
	String REPAY_REMIND = "REPAY_REMIND";
	/** 催收短信 */
	String COLLECTION_SMS = "COLLECTION_SMS";
	/** 还款成功 */
	String REPAY_SUCCESS = "REPAY_SUCCESS";
	/** 活动短信 */
	String ACTIVITY_SMS = "ACTIVITY_SMS";
	/** 还款失败 */
	String REPAY_FAIL = "REPAY_FAIL";
	/** 忘记密码 */
	String FORGET_PASSWORD = "FORGET_PASSWORD";
	
}
