package com.maiyajf.base.constants;

/**
 * @ClassName: IApplyAuditStage
 * @Description: 借款申请审核阶段
 * @author: yunlei.hua
 * @date: 2015年11月3日 下午3:10:21
 */
public interface IApplyAuditStage {
	
	/** 待审核 */
	Integer UN_AUDIT_STAGE = 0;
	/** 系统审核 */
	Integer SYSTEM_AUDIT_STAGE = 10;
	/** 人工信审 */
	Integer MANUL_AUDIT_STAGE = 20;
}
