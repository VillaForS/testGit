package com.maiyajf.base.constants;

/**
 * @ClassName: IAuditResult
 * @Description: 审核结果
 * @author: yunlei.hua
 * @date: 2015年12月10日 下午8:14:08
 */
public interface IAuditResult {
	
	/** 审核中 */
	int AUDITING = 0;
	/** 审核通过 */
	int PASS_AUDIT = 1;
	/** 审核不通过 */
	int NOT_PASS_AUDIT = 2;
	
	/** 人工信审中 */
	int MANUAL_AUDITING = 0;
	/** 人工信审通过 */
	int MANUAL_PASS_AUDIT = 10;
	/** 人工信审不通过 */
	int MANUAL_NOT_PASS_AUDIT = 20;
	
}
