package com.maiyajf.base.constants;

public interface IResponseCode {

    /**
     * 返回码
     */
    String RETURN_CODE_STR = "retcode";
    /**
     * 返回信息
     */
    String RETURN_INFO_STR = "retinfo";

    // ----------------下面4中编码和app定义的编码一一对应-------------
    String SUCCESS = "000000"; // 接口返回状态:成功
    String FAIL = "000001"; // 接口返回状态:失败
    String TOKEN_EXPIRED_OTHERLOGIN = "000002"; // token失效--账号已经在其他设备登录
    String TOKEN_EXPIRED_OVERTIME = "000004"; // token失效--登录超时
    String PROCESSING = "000003"; // 接口返回状态:处理中
    // ---------------------------------------------------

    String WRONG_REQUEST_PARAMETERS = "000050"; // 错误的请求参数

    String MISS_REQUEST_PARAMETERS = "000060"; // 丢失请求参数

    String UNKNOW = "000100"; // 接口返回状态:未知状态
    String THIRD_PART_ERROR = "000101";// // 接口返回状态:第三方异常
    String BATCHNO_NOT_EXIST = "000102"; // 批次号不存在
    /**透传Toast*/
    String TOAST_TRANS = "1000001"; 
    /**透传POP*/
    String POP_TRANS = "1000002"; 

    // ----------------------------------------------------------------

    // 网络连接异常
    // SOS！你已经失联了，请检查网络！
    String GLOBAL_NETWORK_EXCEPTION = "YHZX000700";

    // 在其他设备登录账户
    // 该账户已在其他设备登录，请注意安全
    String GLOBAL_OTHER_DEVICE_LOGIN = "YHZX000701";

    // 服务器异常
    // 网络异常
    String GLOBAL_SERVICE_EXCEPTION = "YHZX000702";

    // 24小时内不可以重复操作
    String GLOBAL_REPEAT_REQUEST_EXCEPTION = "YHZX000703";

    // ----------------------------------------------------------------

    // 手机号非1开头、非数字、不满11位
    // 手机号不正确
    String REGIST_PHONE_NUM_ERR = "YHZX000001";

    // 手机号之前已注册过
    // 手机号已注册
    String REGIST_PHONE_NUM_REGISTERED = "YHZX000002";

    // 图片验证码输入不正确，字母数字不符或位数不符
    // 图片验证码不正确
    String REGIST_IMG_VERIF_CODE_ERR = "YHZX000003";

    // 短信验证码输入不正确，非数字或位数不符
    // 短信验证码不正确
    String REGIST_SMS_VERIF_CODE_ERR = "YHZX000004";

    // 发送短信验证码超过15分钟有效期再输入
    // 短信验证码已失效
    String REGIST_SMS_VERIF_CODE_INVILID = "YHZX000005";

    // 密码输入为纯数字或字母或其他字符，未组合或位数不符
    // 密码必须为6-16位英文字母与数字组合
    String REGIST_PASSWD_ERR = "YHZX000006";

    // 该身份证号码已被注册用户认证，无法输入，请核实或咨询客服申请修改
    String IDNO_IS_REGISTED = "YHZX000313";

    // 注册完成并成功
    // 注册成功
    String REGIST_SUCCESS = "YHZX000007";

    // （手机号/图片验证码/短信验证码/密码）都未填写,点击注册
    // 请输入相关信息
    String REGIST_INFO_INCOMPLETE = "YHZX000008";

    // 手机号已填写，图片验证码未填写，点击注册
    // 请输入图片验证码
    String REGIST_IMG_VERIF_CODE_MISS = "YHZX000009";

    // 手机号&图片验证码已填写，短信验证码未填写，点击注册
    // 请输入短信验证码
    String REGIST_SMS_VERIF_CODE_MISS = "YHZX000010";

    // 手机号&验证码已填写，密码未填写，点击注册
    // 请输入密码
    String REGIST_SMS_VERIF_PASSWORD = "YHZX000011";

    // 请输入期望金额
    String REGIST_INFO_IEXPECTAMOUNT = "YHZX000012";

    // 请输入期望贷款限期
    String REGIST_INFO_ILOANPERIOD = "YHZX000013";

    // 请输入用户姓名
    String REGIST_INFO_SUSERNAME = "YHZX000014";

    // 期望金额需数字格式
    String REGIST_IEXPECTAMOUNT_ERR = "YHZX000015";

    // 期望贷款限期需数字格式
    String REGIST_ILOANPERIOD_ERR = "YHZX000016";

    // 图片验证码已失效
    String REGIST_IMG_VERIF_CODE_INVILID = "YHZX000017";

    // ----------------------------------------------------------------

    // 手机号非1开头、非数字、不满11位、手机号未注册过
    // 手机号不正确
    String LOGIN_PHONE_NUM_ERR = "YHZX000101";

    // 密码输入为纯数字或字母或其他字符，未组合或位数不符
    // 密码不正确
    String LOGIN_PASSWD_ILLEGAL = "YHZX000102";

    // 未填写手机号直接点击登录
    // 请输入手机号
    String LOGIN_PHONE_NUM_MISS = "YHZX000103";

    // 有手机号未填写密码直接点击登录
    // 请输入密码
    String LOGIN_PASSWD_MISS = "YHZX000104";

    // 登录成功
    // 登录成功
    String LOGIN_SUCCESS = "YHZX000105";
    // 密码输入错误提示
    // 密码错误，今日还有4（3、2、1）次输入机会，您可以找回密码
    String LOGIN_PASSWD_ERR = "YHZX000106";
    // 手机号或密码错误，今日还有4（3、2、1）次输入机会，您可以找回密码
    String LOGIN_MOBILE_OR_PASSWD_ERR = "YHZX100106";

    // 密码输入错误超过5次
    // 该账户已被锁定，您可以找回密码，次日自动解锁。
    String LOGIN_PASSWD_ERR5 = "YHZX000107";

    // ----------------------------------------------------------------

    // 手机号未注册过
    // 手机号未注册
    String RESET_PHONE_NUM_UNREGISTERED = "YHZX000201";

    // 手机号非1开头、非数字、不满11位
    // 手机号不正确
    String RESET_PHONE_NUM_ERR = "YHZX000202";

    // 图片验证码输入不正确，字母数字不符或位数不符
    // 图片验证码不正确
    String RESET_IMG_VERIF_CODE_ERR = "YHZX000203";

    // 短信验证码输入不正确，非数字或位数不符
    // 短信验证码不正确
    String RESET_SMS_VERIF_CODE_ERR = "YHZX000204";

    // 发送短信验证码超过15分钟有效期再输入
    // 短信验证码已失效
    String RESET_SMS_VERIF_CODE_INVILID = "YHZX000205";

    // 密码输入为纯数字或字母或其他字符，未组合或位数不符
    // 密码必须为6-16位英文字母与数字组合
    String RESET_PASSWD_ILLEGAL = "YHZX000206";

    // 重置密码成功
    // 重置密码成功
    String RESET_SUCCESS = "YHZX000207";

    // ----------------------------------------------------------------

    // 服务密码输入后点击保存，若服务密码验证不正确
    // 服务密码不正确
    String INFO_SERVICE_PASSWD_ERR = "YHZX000301";

    // 服务密码界面发送后短信验证码输入不正确，非数字或位数不符
    // 短信验证码不正确
    String INFO_SMS_VERIF_CODE_ERR = "YHZX000302";

    // 服务密码界面发送短信验证码超过15分钟有效期再输入
    // 短信验证码已失效
    String INFO_SMS_VERIF_CODE_INVILID = "YHZX000303";

    // 填写资料或修改资料并点击保存
    // 保存成功
    String INFO_SUCCESS = "YHZX000304";

    // 填写姓名、联系人姓名超过15个字，非汉字
    // 请输入真实姓名
    String INFO_NAME_ERR = "YHZX000305";

    // 填写身份证输入不正确，非数字或位数不符
    // 请输入真实身份证号
    String INFO_IDCARDNO_ERR = "YHZX000306";

    // 填写详细住址超过50个字，特殊字符
    // 请输入真实详细地址
    String INFO_ADDR_ERR = "YHZX000307";

    // 填写单位名称超过50个字，特殊字符
    // 请输入真实单位名称
    String INFO_CORP_NAME_ERR = "YHZX000308";

    // 填写单位固话/住宅固话不是3-4位区号，7-8位数字电话
    // 请输入真实单位固话/住宅固话
    String INFO_FIX_PHONE_ERR = "YHZX000309";

    // 填写单位地址超过50个字，特殊字符
    // 请输入真实单位地址
    String INFO_CORP_ADDR_ERR = "YHZX000310";

    // 填写联系人手机号非1开头、非数字、不满11位
    // 请输入真实联系人电话
    String INFO_LINKMAN_PHONE_ERR = "YHZX000311";

    // 未填写资料，点击“保存”
    // 请输入相关资料
    String INFO_INCOMPLETE = "YHZX000312";

    // ----------------------------------------------------------------

    // 已经提交借款申请后，再次点"极速借款"按键，将弹出提示框
    // 您的借款申请正在处理中，请耐心等待
    String LOAN_PROCESSING = "JKZX000001";

    // 已经借款未还清借款后，再次点击"极速借款"按键
    // 您有一笔借款未还清
    String LOAN_NOT_PAYOFF = "JKZX000002";

    // 已经提交借款申请后，审核未通过，再次点击"极速借款"按键 你借款审核未通过，请稍后再试。
    // 你借款审核未通过，请稍后再试。
    // 请确保你的资料真实有效,2016-5-12 huayunlei
    String LOAN_CHECK_NOT_PASS = "JKZX000003";

    // 已经提交借款申请后，审核未通过，第4次点击"极速借款"按键 一天只能申请3次，请稍后再试。
    // 一天只能申请3次，请稍后再试。
    String LOAN_3TIMES_IN_1DAY = "JKZX000004";

    // 借款人的手机联系人信息少于指定条数，直接拒绝借款
    String LOAN_MEMBERCONTRACTSCOUNT_COUNT = "JKZX000005";
    // 优惠券不可用
    String COUPON_NOT_AVAILABLE = "JKZX000006";
    // 预授信结果-未知（你的资料正在授信中，请稍后再试）
    String PRE_AUDIT_RESULT_UNKNOW = "JKZX000007";
    // 预授信结果-未通过（你的资料授信未通过，请修改资料）
    String PRE_AUDIT_RESULT_UNPASS = "JKZX000008";
    /**银生宝协议过期*/
    String JKZX_YSB_EXPIRE = "JKZX000009";
    /**没有绑定有效代付代扣卡*/
    String JKZX_NOT_BIND_CARD = "JKZX000010";
    /**没有相应的子协议号*/
    String JKZX_NOT_AGREEMENT_NO = "JKZX000011";

    // ----------------------------------------------------------------

    // 添加收款卡
    // 绑定收款卡完成
    String CARD_IN_ADD_SUCCESS = "ZJZX000001";

    // 收款卡 实名认证时银行卡卡号输入不正确，非数字或位数不符
    // 银行卡号不正确
    String CARD_IN_NUM_ERR = "ZJZX000002";

    // 收款卡 实名认证填写的信息不符
    // 添加收款卡失败，请重新试试
    String CARD_IN_ADD_FAIL = "ZJZX000003";

    // 收款卡 只能绑定一张收款卡，多绑定则弹出提示框
    // 限绑定一张收款卡，若替换银行卡，请删除之前银行卡重新绑定
    String CARD_IN_ONLY_ONE = "ZJZX000004";

    // 收款卡 30天内删加收款卡卡超过3次，弹出POP框
    // 亲，最近30天只能验证3次哦
    String CARD_IN_3TIMES_IN_30DAYS = "ZJZX000005";

    // 收款卡 点击银行卡，删除银行卡,弹出POP框
    // 删除此张银行卡吗？
    String CARD_IN_DELETE = "ZJZX000006";

    // 收款卡 点击银行卡，删除银行卡,弹出POP框 您有借款未还，不能删除银行卡哦
    // 您有借款未还，不能删除银行卡哦
    String CARD_IN_CANNOT_DELETE = "ZJZX000007";

    // 还款卡 添加主动还款卡
    // 绑定主动还款卡完成
    String CARD_OUT_ADD_SUCCESS = "ZJZX000008";

    // 还款卡 实名认证时银行卡卡号输入不正确，非数字或位数不符
    // 银行卡号不正确
    String CARD_OUT_NUM_ERR = "ZJZX000009";

    // 还款卡 短信验证码输入不正确，非数字或位数不符
    // 短信验证码不正确
    String CARD_OUT_SMS_VERIF_CODE_ERR = "ZJZX000010";

    // 还款卡 发送短信验证码超过15分钟有效期再输入
    // 短信验证码已失效
    String CARD_OUT_SMS_VERIF_CODE_INVILID = "ZJZX000011";

    // 还款卡 实名认证填写的信息不符
    // 添加主动还款卡失败，请重新试试
    String CARD_OUT_ADD_FAIL = "ZJZX000012";

    // 还款卡 点击银行卡，删除银行卡，弹出POP框
    // 删除此张银行卡吗？
    String CARD_OUT_DELETE = "ZJZX000013";

    // 还款卡 共绑定3张主动还款卡，绑完3张卡后，再点击添加主动还款卡
    // 亲，绑3张主动还款卡就可以了哦
    String CARD_OUT_CANNOT_DELETE = "ZJZX000014";

    // 还款卡 30天内删加主动还款卡超过3次，弹出POP框
    // 亲，最近30天只能验证3次哦
    String CARD_OUT_3TIMES_IN_30DAYS = "ZJZX000015";

    // 还款卡 如果用户绑卡第2张卡同第1张卡一样，无法重复绑卡
    // 亲，您绑了一样的卡
    String CARD_OUT_ADD_REPETITION = "ZJZX000016";

    // 无须还款时，添加主动还款卡，弹出POP
    // 暂时无法绑定主动还款卡
    String CARD_OUT_BIND_UNABLE = "ZJZX000017";

    /** ------------------保存信用卡------------- **/
    // 丢失token
    String BIND_CREDITCARD_TOKEN_MISS = "ZJZX000018";
    // 丢失卡号
    String BIND_CREDITCARD_CARDNO_MISS = "ZJZX000019";
    // 卡号格式是否正确
    String BIND_CREDITCARD_CARDNO_ERR = "ZJZX000020";
    // 丢失银行卡名
    String BIND_CREDITCARD_CARDNAME_MISS = "ZJZX000021";
    // 丢失注册手机号
    String BIND_CREDITCARD_PHONENO_MISS = "ZJZX000022";
    // 手机号格式是否正确
    String BIND_CREDITCARD_PHONENO_ERR = "ZJZX000023";
    
    /** ------------------京东支付------------- **/
    // 银行卡余额不足
    String JD_PAY_INSUFFICIENT_BALANCE = "ZJZX000024";
    // 银行卡超出单笔限额
    String JD_PAY_BEYOND_SINGLEQUOTA = "ZJZX000025";
    /**系统异常，请重试！*/
    String YSBCODE_SYS_EXCEPTION = "ZJZX000026";
    /**绑卡失败，请确保信息真实有效*/
    String YSBCODE_BIND_FAIL = "ZJZX000027";
    /**银生宝协议已过期*/
    String YSBCODE_AGREEMENT_EXPIRE = "ZJZX000028";
    /**没有绑定有效代付代扣卡*/
    String NOT_BIND_CARD = "ZJZX000029";
    /**没有相应的子协议号*/
    String NOT_AGREEMENT_NO = "ZJZX000030";

    // ----------------------------------------------------------------

    // 点击关闭还款弹出界面
    // 暂时不还款吗？
    String REPAY_STOP = "HKZX000001";

    // 还款处理中
    // 小主，财务和程序猿私奔了，您的还款正在处理中，您可以去核实下银行卡还款状态
    String REPAY_PROCESSING = "HKZX000002";

    // 还款失败
    // 小主，您的还款未成功，您需要继续还款
    String REPAY_FAIL = "HKZX000003";

    // 输入小于最低还款金额或非法输入（首次）
    // 请输入正确的还款金额
    String REPAY_AMOUNT_ERR = "HKZX000004";

    // 输入小于最低还款金额或非法输入（第2次要还清）
    // 请还清剩余借款
    String REPAY_PLEASE_PAYOFF = "HKZX000005";

    // 还款超出还款金额
    // 请核实下还款金额
    String REPAY_CHECK_AMOUNT = "HKZX000006";

    // 还款正在处理中，用户点击“我要还款”
    // 小主，您的还款正在处理中，请稍后
    String REPAY_CHECK_WAIT = "HKZX000007";
    // 还款渠道不支持该还款卡
    String REPAY_CHANNEL_NOT_SUPPORT = "HKZX000008";
    // 还款金额不等于充值金额
    String REPAY_AMOUNT_NOTEQUAL = "HKZX000009";
    /**系统异常*/
    String TRAN_SYS_EXCEPTION = "HKZX000010";
    /**程序员私奔了*/
    String PROGRAMMER_ELOPED = "HKZX000011";
    /**短信验证码错误，请输入正确的验证码*/
    String TRADE_VALIDECODE_ERROR_INT = "HKZX000012";
    /**该卡不支持，请更换还款卡！*/
    String NOT_SUPPORT_CHANGE_CARD = "HKZX000013";
    /**系统繁忙，请稍后再试*/
    String SYS_BUSY = "HKZX000014";
    /**短信验证码发送失败*/
    String TRADE_VALIDECODE_NORECIEVE_INT = "HKZX000015";
    /**验证码已过期，请重新获取*/
    String TRADE_VALIDECODE_EXPIRE_INT = "HKZX000016";
    /**验证码无效，请先获取验证码*/
    String INVALIDE_CODE = "HKZX000017";
    /**获取验证码失败！*/
    String VALIDE_CODE_FAIL = "HKZX000018";

    // ----------------------------------------------------------------

    // 修改密码成功
    // 密码修改成功
    String MORE_PASSWD_MODIFY_SUCCESS = "YHZX000401";

    // 未输入旧密码，点击“确定”
    // 请输入当前密码
    String MORE_INPUT_OLD_PASSWD = "YHZX000402";

    // 未输入新密码，点击“确定”
    // 请输入新密码
    String MORE_INPUT_NEW_PASSWD = "YHZX000403";

    // 未输入确认密码，点击“确定”
    // 请再次输入新密码
    String MORE_INPUT_NEW_PASSWD_AGAIN = "YHZX000404";

    // 超过5次填错旧密码
    // 密码修改过于频繁，请稍后重试
    String MORE_OLD_PASSWD_ERR5 = "YHZX000405";

    // 新密码输入为纯数字或字母或其他字符，未组合或位数不符
    // 密码必须6-16位英文字母与数字组合
    String MORE_NEW_PASSWD_ERR = "YHZX000406";

    // 两次密码输入不一致
    // 两次密码输入不一致
    String MORE_NEW_2PASSWD_DIFFER = "YHZX000407";

    // 旧密码不正确
    // 旧密码不正确
    String MORE_OLD_PASSWD_ERR = "YHZX000408";

    // 填写毕业院校超过15个字，特殊字符
    // 请输入真实院校
    String MORE_ACADEMY_ERR = "YHZX000501";

    // 填写公司邮箱超过50个字
    // 请输入真实邮箱
    String MORE_EMAIL_ERR = "YHZX000502";

    // 填写其他网络账号超过20个字
    // 请输入真实账号
    String MORE_NETWORD_ACCT_ERR = "YHZX000503";

    // 提交成功
    // 提交成功
    String MORE_SUBMIT_SUCCESS = "YHZX000504";

    // 填写其他网络账号密码超过20个字
    // 请输入真实密码
    String MORE_PASSWORD_ACCT_ERR = "YHZX000505";

    // ----------------------------------------------------------------

    // 头像更换成功
    // 恭喜您！更换新头像成功
    String HEAD_MODIFY_SUCCESS = "YHZX000601";

    // 更换头像失败
    // 更换头像失败
    String HEAD_MODIFY_ERR = "YHZX000602";

    // 校验卡BIN
    String REQUEST_MSG_NULL = "YHZX000603";// 请求参数为空
    String QUERY_RETURN_NULL = "YHZX000604";// 查询返回结果为空
    String CARDLEN_NOT_ENOUGH = "YHZX000605";// 银行卡号长度不对
    String NOTSUPPORTED_CARD_STR = "YHZX000607";// 不支持
    String INTERFACE_SERVICE_EXCEPTION = "YHZX000608";// 接口服务异常
    String BANKID_IS_NULL = "YHZX000609";// 卡BIN对应的第三方银行列表数据为空
    String CARD_NOT_MATCH = "YHZX000610";// 银行卡号和银行名称不匹配
    String CARD_BINED = "YHZX000611";// 该卡已经绑定

    String NOTSUPPORT_DEBITCARD = "YHZX000612";// 暂不支持此银行卡，请核实卡号

    // 邀请码不正确
    String INVITATION_CODE_INCORRECT = "HDZX000001";

}
