package com.young.pact.common.constant;

/**
 * 
* @ClassName: StringConsts
* @Description: TODO 字符串常量
* @author HeZeMin
* @date 2018年1月30日 上午9:52:09
*
 */
public class StringConsts {
    public static final String TEL = "tel";
    public static final String CONTENT = "content";
    public static final String CODE = "code";
    public static final String DATE_TIME = "date";
    /**
     * 下划线 "_"
     */
    public static final String UNDERLINE = "_";
    /**
     *  连接线"-"
     */
    public static final String CROSSLINE = "-";
    /**
     * 分号";"
     */
    public static final String SEMICOLON = ";";
    /**
     * 收款对象为:租客
     */
    public static final String PAYEE_OBJECT_CUSTOMER = "租客";
    /**
     * 收款对象为:物业公司
     */
    public static final String PAYEE_OBJECT_PROPERTY_COMPANY = "物业公司";
    /**
     * 收款对象为:青春界
     */
    public static final String THE_YOUTH_WORLD = "青春界";
    /**
     * 收款对象为:供热站
     */
    public static final String PAYEE_OBJECT_COMPUTER = "供热站";
    /**
     * 收款对象为:业主
     */
    public static final String PAYEE_OBJECT_OWNER = "业主";
    /**
     * 维护人
     */
    public static final String BELONGER_PERSON = "维护人";
    /**
     * 签约管家
     */
    public static final String BELONGER_DEAL = "签约管家";
    /**
     * 运营管家
     */
    public static final String BELONGER_OPERATE = "运营管家";
    /**
     * 租后管家
     */
    public static final String BELONGER_SERVICE = "租后管家";
    /**
     * 托进合同-第一步 完善房源 业务码
     */
    public static final String HOUSE = "house";
    /**
     * 托进合同-第二步 业主信息 业务码
     */
    public static final String HOUSE_OWNER = "owner";
    /**
     * 托进合同-第三步 合同信息 业务码
     */
    public static final String PURCHASE = "purchase";
    /**
     * 托进合同-第四步 物业交接 业务码
     */
    public static final String PROPERTY_TRANSFER = "property_transfer";
    /**
     * 托出合同-第一步 房间信息 业务码
     */
    public static final String REDIS_ROOM_KEY = "young_room";
    
    /**
     * 托出合同-第二步 租客信息 业务码
     */
    public static final String REDIS_RENTER_KEY = "young_renter";
    /**
     * 托出合同-第三步 合同信息 业务码
     */
    public static final String REDIS_PACT_KEY = "young_pact";
    /**
     * 托出合同-第四步 物业交接  业务码
     */
    public static final String REDIS_PROPERTYTRANSFER_KEY = "young_property_transfer";
    /**
     * 托出合同房间-签约管家
     */
    public static final String RENT_USER_ROLE = "签约管家";
    /**
     * 托出合同房间-责任人
     */
    public static final String RENT_USER_ROLE_RES = "责任人";
    /**
     * 租客
     */
    public static final String RENTER = "租客";
    /**
     * 托出
     */
    public static final String GIVE_OUT = "托出";
    /**
     * 托出合同-第一步 转租协议 业务码
     */
    public static final String REDIS_RENT_TURN_KEY = "young_rent_turn";
    /**
     * 调房协议 第一步 业务码
     */
    public static final String REDIS_TRANSFER = "redis_transfer";
    /**
     * 调房协议 第三步 业务码
     */
    public static final String REDIS_TRANSFER_PACT = "redis_transfer_pact";
    
    
    /**
     * 托出合同-第四步 转租合同 业务码
     */
    public static final String REDIS_RENT_TURN_PACT_KEY = "young_rent_turn_pact";
    /**
     * 原条件
     */
    public static final String ORIGINAL_CONDITION = "原条件";
    /**
     * 变条件
     */
    public static final String VARIABL_CONDITION = "变条件";
    /**
     * 维护人
     */
    public static final String GUARDIAN = "维护人";
    /**
     * 托出合同续签-第一步 续签协议 业务码
     */
    public static final String REDIS_RENT_CONTINUED_KEY = "young_rent_continued";
   
    /**
     * 托出合同续签-第二步  续签协议 业务码
     */
    public static final String REDIS_RENT_CONTINUED_PACT_KEY = "young_rent_continued_pact";
    /**
     * 操作日志-资源类型-添加
     */
    public static final String ADD = "add";
    /**
     * 操作日志-资源类型-修改
     */
    public static final String UPDATE = "update";
    /**
     * 操作日志-资源类型-删除
     */
    public static final String DELETE = "delete";
    /**
     * 新增了一条数据
     */
    public static final String ADD_DATA = "新增了一条数据";
    /**
     * 修改了一条数据
     */
    public static final String UPDATE_DATA = "修改了一条数据";
    /**
     * 删除了一条数据
     */
    public static final String DELETE_DATA = "删除了一条数据";
    /**
     * 申请审核了一条数据
     */
    public static final String AUDIT_DATA = "申请审核了一条数据";
    /**
     * 审核通过一条数据
     */
    public static final String REVIEWPASS_DATA = "审核通过了一条数据";
    /**
     * 审核驳回了一条数据
     */
    public static final String REVIEWDISMISSAL_DATA = "审核驳回了一条数据";
    /**
     * 成交
     */
    public static final String BARGAIN_SUCCESS = "bargain_success";
    /**
     * 未成交
     */
    public static final String BARGAIN_NOT = "bargain_not";
    /**
     * 签约中
     */
    public static final String DEALING = "dealing";
    /**
     * 男
     */
    public static final String MAN = "男";
    /**
     * 女
     */
    public static final String WOMAN= "女";
    /**
     * 分配运营管家
     */
    public static final String UPDATE_BELONGER_OPERATE = "分配运营管家";
    /**
     *  响应结果code
     */
    public static final String REQUEST_SUCCESS_CODE = "200";
    /**
     * 分配租后管家
     */
    public static final String UPDATE_BELONGER_SERVICE = "分配租后管家";
    /**
     *  物品
     */
    public static final String OPERATELOG_GOODS_SERVICETYPE = "goods";
    /**
     *  抄表
     */
    public static final String OPERATELOG_METER_SERVICETYPE = "meter";
    /**
     * 调房协议
     */
    public static final String RENT_TRANSFER = "调房协议";
    /**
     * 付
     */
    public static final String PAY = "付";
    /**
     * 解约
     */
    public static final String DISSOLUTION = "解约";
    /**
     * 操作日志-资源类型-应收收款
     */
    public static final String ANSWERCOLLECTRECEIPT = "answerCollectReceipt";
    /**
     *  操作日志-资源类型-实收确认收款
     */
    public static final String REALCOLLECTCONFIRM = "realCollectConfirm";
    /**
     *  操作日志-资源类型-实收驳回收款
     */
    public static final String REALCOLLECTREJECT = "realCollectReject";
    /**
     *  操作日志-资源类型-应支申请付款
     */
    public static final String ANSWEREXPENDAPPLY = "answerExpendApply";
    /**
     *  操作日志-资源类型-实支付款
     */
    public static final String ANSWEREXPENDRECEIPT = "answerExpendReceipt";
    /**
     *  操作日志-资源类型-添加应收
     */
    public static final String ADD_RECEIVABLE = "addReceivable";
    /**
     * 操作日志-资源类型-添加应支
     */
    public static final String ADD_BRANCH = "addBranch";
    /**
     * 操作日志-资源类型- 实支审核
     */
    public static final String REAL_SUPPORT_AUDIT = "realSupportAudit";
    /**
     * 月付
     */
    public static final String MONTHLY_PAYMENT = "monthly_payment";
    /**
     * 到期解约
     */
    public static final String DUE_TERMINATION = "due_termination";
    /**
     * 到期解约
     */
    public static final String EXPIR_TERMINATION_VALUE = "到期解约";
    /**
     * 违约解约
     */
    public static final String DUE_TERMINATION_VALUE = "违约解约";
    /**
     * 托进合同审核通过
     */
    public static final String PURCHASEBASE_PASSED = "purchaseBasePassed";
    /**
     * 托进合同审核驳回
     */
    public static final String PURCHASEBASE_REJECT = "purchaseBaseReject";
    /**
     * 托进解约协议审核通过
     */
    public static final String PURCHASETERMINATION_REVIEW = "purchaseTerminationReview";
    /**
     * 托进解约协议审核驳回
     */
    public static final String PURCHASETERMINATION_REJECT = "purchaseTerminationReject";
    /**
     * 托出合同审核通过
     */
    public static final String REVIEW_PASS_RENTBASE = "reviewPassRentBase";
    /**
     * 托出合同审核驳回
     */
    public static final String REVIEW_DISMISSAL_RENTBASE = "reviewDismissalRentBase";
    /**
     * 托出解约协议审核通过
     */
    public static final String REVIEW_PASS_RENTTERMINATION = "reviewPassRenttermination";
    /**
     * 托出解约协议审核驳回
     */
    public static final String REVIEW_DISMISSAL_RENTTERMINATION = "reviewDismissalRenttermination";
    /**
     * 转租协议审核通过
     */
    public static final String REVIEW_PASS_RENTTURN = "reviewPassRentTurn";
    /**
     * 转租协议审核驳回
     */
    public static final String REVIEW_DISMISSAL_RENTTURN = "reviewDismissalRentTurn";
    /**
     * 续租协议审核通过
     */
    public static final String REVIEW_PASS_RENTCONTINUED = "reviewPassRentContinued";
    /**
     * 续租协议审核驳回
     */
    public static final String REVIEW_DISMISSAL_RENTCONTINUED = "reviewDismissalRentContinued";
    /**
     * 调房协议审核通过
     */
    public static final String REVIEW_PASS_TRANSFER = "reviewPassTransfer";
    /**
     * 调房协议审核驳回
     */
    public static final String REVIEW_DISMISSAL_TRANSFER = "reviewDismissalTransfer";
    /**
     * 申报审核驳回
     */
    public static final String REVIEW_DISMISSAL_DECLARE = "reviewDismissalDeclare";
    /**
     * 分配工程监理
     */
    public static final String DISTRIBUTION_ENGINEERING_SUPERVISION = "distributionEngineeringSupervision";
    /**
     * 合同分配
     */
    public static final String TEMPLATE_PACT_DISTRIBUTION = "pactDistribution";
    /**
     * 合同分配
     */
    public static final String PACT_DISTRIBUTION = "合同分配";
    
    /**
     * 模板变量
     */
    public static final String VARIABLE = "variable";
    /**
     * 日志-资源转移常量
     */
    public static final String CONSTANT_RESOURCE_TRANSFER  = "把资源转移给";
    /**
     * 日志-资源转移常量合同编码
     */
    public static final String CONSTANT_RESOURCE_TRANSFER_PACTCODE  = ",合同编码：";
    /**
     * 提醒消息-合同审核标题
     */
    public static final String REMIND_AUDITING_TITLE  = "该合同审核完毕";
    /**
     * 提醒消息-应收
     */
    public static final String  REMIND_RECEIVABLE = "应收";
    /**
     * 提醒消息-应付
     */
    public static final String  REMIND_PAY = "应付";
    /**
     * 提醒消息-实收
     */
    public static final String  REMIND_REAL_RECEIVA = "实收";
    /**
     * 提醒消息-实付
     */
    public static final String  REMIND_REAL_PAY = "实付";
    /**
     * 提醒消息标题-应收款项审批提醒
     */
    public static final String  REMIND_RECEIVABLE_TITLE = "应收款项审批提醒";
    /**
     * 提醒消息-应收款项审批模板key
     */
    public static final String TEMPLATE_RECPAYAUDITING = "recPayAuditing";
    /**
     * 提醒消息-实收、实付驳回或通过模板key
     */
    public static final String TEMPLATE_REAL_RECPAYAUDITING = "ralRecPayAuditing";
    /**
     * 提醒消息-实收、实付驳回或通过模板变量 通过
     */
    public static final String REMIND_AUDITING_PASS = "通过";
    /**
     * 提醒消息-实收、实付驳回或通过模板变量 驳回
     */
    public static final String REMIND_AUDITING_REJECT = "驳回";
    /**
     * 提醒消息标题-应收款项状态改变提示
     */
    public static final String  REMIND_REAL_RECEIVE_TITLE = "应收款项状态改变提示";
    /**
     * 操作日志-资源类型-申请审核
     */
    public static final String  OPERATETYPE_APPLICATION_AUDIT  = "applicationAudit";
    /**
     * 操作日志-资源类型-审核通过
     */
    public static final String  OPERATETYPE_REVIEW_PASS   = "reviewPass";
    /**
     * 操作日志-资源类型-审核驳回
     */
    public static final String  OPERATETYPE_REVIEW_DISMISSAL   = "reviewDismissal";
    /**
     * 操作日志-资源类型-修改托出合同租客
     */
    public static final String  OPERATETYPE_UPDATE_RENT_CUSTOMER   = "updateRentCustomer";
    /**
     * 提醒消息-模板常量key-录入合同超过24小时未提交审核
     */
    public static final String  REMIND_TEMPLATE_UNSUBMITTED_KEY = "pactUnsubmitted";
    /**
     * 提醒消息-标题-录入合同超过24小时未提交审核
     */
    public static final String  REMIND_TEMPLATE_UNSUBMITTED_TITLE = "合同未提交审核提示";
    /**
     * 提醒消息-模板常量key-合同驳回后超过24小时未重新提交审核
     */
    public static final String  REMIND_TEMPLATE_REJECT_KEY= "pactReject";
    /**
     * 提醒消息-标题-合同未重新提交审核提示
     */
    public static final String  REMIND_TEMPLATE_REJECT_TITLE = "合同未重新提交审核提示";
    /**
     * 提醒消息-模板常量key-合同提交审核后超过24小时未审核
     */
    public static final String  REMIND_TEMPLATE_UNREVIEW_KEY= "pactUnreview";
    /**
     * 提醒消息-标题-合同提交审核后超过24小时未审核
     */
    public static final String  REMIND_TEMPLATE_UNREVIEW_TITLE = "合同未审核提示";
    /**
     * 提醒消息-模板常量key-合同审核通过后超过24小时未创建房间
     */
    public static final String  REMIND_TEMPLATE_UNCREATEROOM_KEY= "unCreateRoom";
    /**
     * 提醒消息-标题-未创建房间提示
     */
    public static final String  REMIND_TEMPLATE_UNCREATEROOM_TITLE = "未创建房间提示";
    
    public static final String  STATISTIC_SERVICECODE = "serviceCode";
    public static final String  STATISTIC_PACTTYPE = "pactType";
    public static final String  STATISTIC_PIN = "pin";
    public static final String  STATISTIC_NAME = "name";
    public static final String  STATISTIC_DEPTCODE = "deptCode";
    public static final String  STATISTIC_DEPTNAME = "deptName";
    public static final String  STATISTIC_DATE = "date";
    public static final String  STATISTIC_YEAR = "year";
    public static final String  STATISTIC_MONTH = "month";
    public static final String  STATISTIC_DAY = "day";
    public static final String  STATISTIC_TYPE = "type";
    public static final String  STATISTIC_ISDO = "isDo";
    
    public static final String  NEW_OUT_COUNT = "新出量";
    public static final String  NEW_INTO_COUNT = "新进量";
    public static final String  RENEWAL_OF_RENT_COUNT = "续租量";
    public static final String  RENT_DISSOLUTION_COUNT = "解约量";
    public static final String  STATISTIC_PENDING_DECLARE = "托进申报";
    public static final String  STATISTIC_PENDING_PURCHBASE = "托进合同审核";
    public static final String  STATISTIC_PENDING_PURCHBASE_TERMINATION = "托进解约申请";
    public static final String  STATISTIC_PENDING_PURCHBASE_EXPIR = "托进到期预警";
    public static final String  STATISTIC_PENDING_RENT = "托出合同审核";
    public static final String  STATISTIC_PENDING_RENT_TERMINATION = "托出解约申请";
    public static final String  STATISTIC_PENDING_RENT_EXPIR = "托出到期预警";
    public static final String  STATISTIC_PENDING_RENT_TURN = "转租协议审核";
    public static final String  STATISTIC_PENDING_RENT_CONTINUED = "续租协议审核";
    public static final String  STATISTIC_PENDING_RENT_TRANSFER = "调房协议审核";
    public static final String  STATISTIC_PENDING_RECEIVABLES = "待收款";
    public static final String  STATISTIC_PENDING_RECEIPT_CONFIRMED = "收款待确认";
    public static final String  STATISTIC_PENDING_PENDING_PAYMENT = "待付款";
    public static final String  STATISTIC_PENDING_PAYMENT_AUDITED = "付款待审核";
    public static final String  STATISTIC_PENDING_PAYMENT_AUDITED_PAID = "付款已审核待支付";
    public static final String MODIFIEDNAME = "modifiedName";
    public static final String DELETE_LIST = "deleteList";
    public static final String TYPE_LIST = "typeList";
    public static final String IP = "ip";
    public static final String WHOLE_RENT = "整租";
    public static final String JOINT_RENT = "合租";
    public static final String PER_CAPITA_EFFICIENCY_THIS_MONTH= "本月人均效能";
    public static final String RENT_BEFORE = "托出租前";
    public static final String RENT_AFTER = "托出租后";
    public static final String COMPREHENSIVE_EFFICIENCY = "综合效能";
    public static final String  BUSINESS_INCOME_OF_THE_MONTH  = "本月营业收入";
    public static final String  SERVICECHARGE  = "服务费";
    public static final String  PENALTY = "违约金";
    public static final String  MANAGEMENTEXPENSE = "管理费";
    public static final String  CASH_FLOW_THIS_MONTH  = "本月现金流量";
    public static final String  CASH_INCOME  = "现金收入";
    public static final String  CASH_EXPENDITURE  = "现金支出";
    
    
    public static final String ANSWER_COLLECT_RECEIPT_DATA = "添加了一笔应收收款";
    public static final String REAL_COLLECT_CONFIRM_DATA = "实收确认收款";
    public static final String REAL_COLLECT_REJECT_DATA = "实收驳回收款";
    public static final String ANSWER_EXPEND_APPLY_DATA = "应支申请付款";
    public static final String ANSWER_EXPEND_REVIEW_DATA = "实支审核通过";
    public static final String ANSWER_EXPEND_REJECT_DATA = "实支审核驳回";
    public static final String ANSWER_EXPEND_RECEIPT_DATA = "实支付款";
    public static final String ADD_METERREAD_DATA = "添加了一条抄表数据";
    public static final String UPDATE_METERREAD_DATA = "修改了一条抄表数据";
    public static final String DELETE_METERREAD_DATA = "删除了一条抄表数据";
    public static final String DELETE_GOODS_DATA = "删除了一条物品数据";
    public static final String UPDATE_GOODS_DATA ="修改了一条物品数据";
    public static final String ADD_GOODS_DATA = "添加了一条物品数据";
    /** 私有构造，不允许new **/
    private StringConsts() {}
}
