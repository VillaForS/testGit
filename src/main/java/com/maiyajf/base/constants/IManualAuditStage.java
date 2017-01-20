package com.maiyajf.base.constants;

/**
 * @ClassName: IManualAuditStage
 * @Description: 人工信审审核阶段
 * @author: yunlei.hua
 * @date: 2016年3月23日 下午6:48:17
 */
public interface IManualAuditStage {
	
	/** 待审核 */
	int WAIT_AUDIT_STAGE = 0;
	/** 初审 */
	int FIRST_AUDIT_STAGE = 10;
	/** 复审 */
	int AGAIN_AUDIT_STAGE = 20;
}
