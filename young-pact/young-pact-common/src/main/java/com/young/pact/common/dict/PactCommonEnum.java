package com.young.pact.common.dict;
/**
 * 
* @ClassName: PactCommonEnum
* @Description: TODO    Pact公共枚举值
* @author HeZeMin
* @date 2018年1月22日 下午9:44:13
*
 */
public enum PactCommonEnum {
      
	/**
	 * 参数为null或者为空
	 */
	PARAM_IS_NULL("EC00001","参数为null或者为空"),
	/**
     * 电话格式不正确
     */
	TEL_NOT_MATCH("451","电话格式不正确"),
	/**
     * 填写完整信息
     */
	CACHE_INVALIDATION("452","请填写完整信息"),
	/**
	 * 申报审核状态只有'已审核'时才可以添加托进合同!
	 */
	DECLARE_STATE_NOTIS_2("EC00003","申报审核状态只有'已审核'时才可以添加托进合同!"),
	/**
	 * 第一步房源信息缓存已失效，请重新填写
	 */
	PURCHASE_HOUSE_REDIS_INVALID("EC00004","第一步房源信息缓存已失效，请重新填写"),
	/**
	 * 第二步业主信息缓存已失效，请重新填写
	 */
	PURCHASE_HOUSEOWNER_REDIS_INVALID("EC00005","第二步业主信息缓存已失效，请重新填写"),
	/**
	 * 第三步合同基本缓存已失效，请重新填写
	 */
	PURCHASE_BASE_REDIS_INVALID("EC00006","第三步合同基本缓存已失效，请重新填写"),
	/**
	 * 第四步物业交接缓存已失效，请重新填写
	 */
	PURCHASE_PROPERTY_REDIS_INVALID("EC00007","第四步物业交接缓存已失效，请重新填写"),
	/**
	 * 托进合同保存失败
	 */
	PURCHASE_SAVE_FAIL("EC00008","托进合同保存失败"),
	/**
	 * 序列化生成失败
	 */
	SYSTEM_SERIALIZE_FAIL("EC00009","序列化生成失败"),
	/**
	 * 托进合同已存在,不能再次创建
	 */
	PURCHASE_BASE_IS_EXIST("EC00002","托进合同已存在,不能再次创建"),
	/**
     * 托出房间信息缓存已失效
     */
	RENT_ROOM_REDIS_INVALID("EC00010","第一步房间信息缓存已失效，请重新填写"),
	/**
     * 托出租客信息缓存已失效
     */
    RENT_CUSTOMER_REDIS_INVALID("EC00011","第二步租客信息缓存已失效，请重新填写"),
    /**
     * 托出合同信息缓存已失效
     */
    RENT_BASE_REDIS_INVALID("EC00012","第三步合同信息缓存已失效，请重新填写"),
    /**
     * 托出物业交接信息缓存已失效
     */
    RENT_PROPERTY_REDIS_INVALID("EC00013","第四步物业交接信息缓存已失效，请重新填写"),
    /**
     * 托出合同保存失败
     */
    RENT_SAVE_FAIL("EC00014","托出合同保存失败"),
    /**
     * 房间状态是否为未租
     */
    ROOM_STATE_NOT_MATCH("EC00015","房间状态无法创建托出合同"),
    /**
     * 查询托出合同错误
     */
    RENT_BASE_QUERY_ERROR("EC00016","查询托出合同错误"),
    /**
     * 申请审核托出合同失败
     */
    RENT_BASE_AUDIT_ERROR("EC00017","申请审核托出合同失败"),
    /**
     * 审核通过托出合同失败
     */
    RENT_BASE_REVIEWPASS_ERROR("EC00018","审核通过托出合同失败"),
    /**
     * 审核驳回托出合同失败
     */
    RENT_BASE_REVIEWDISMISSAL_ERROR("EC00019","审核驳回托出合同失败"),
    /**
     * 修改托出合同失败
     */
    RENT_BASE_UPDATE_ERROR("EC00020","修改托出合同失败"),
    /**
     * 修改托出合同租客失败
     */
    RENT_CUSTOMER_UPDATE_ERROR("EC00021","修改托出合同租客失败"),
    /**
     * 删除托出合同失败
     */
    RENT_BASE_DELETE_ERROR("EC00022","删除托出合同失败"),
    /**
     * 托进合同编码无效
     */
    PURCHASE_CODE_INVALID("EC00100","托进合同编码无效"),
    /**
     * 托进合同状态只有'草稿'、'已驳回'才可以删除
     */
    PURCHASE_BASE_STATE_ERROR("EC00101","托进合同状态只有'草稿'、'已驳回'才可以删除"),
    /**
     * 托进合同状态只有'草稿'、'已驳回'才可以申请审核
     */
    PURCHASE_BASE_APPLY_ERROR("EC00102","托进合同状态只有'草稿'、'已驳回'才可以申请审核"),
    /**
     * 托进合同状态只有'草稿'、'已驳回'才可以修改
     */
    PURCHASE_BASE_STATE_UPDATE_ERROR("EC00104","托进合同状态只有'草稿'、'已驳回'才可以修改"),
    /**
     * 托进合同状态只有'待审核'才可以审核
     */
    PURCHASE_BASE_AUDIT_ERROR("EC00103","托进合同状态只有'待审核'才可以审核"),
    /**
     * 当前状态无法提交审核
     */
    PACT_AUDIT_STATE_ERROR("EC00023","当前状态无法提交审核"),
    /**
     * 审核状态是待审核的才可以审核通过
     */
    PACT_REVIEWPASS_STATE_ERROR("EC00024","审核状态是待审核的才可以审核通过"),
    /**
     * 审核状态是待审核的才可以驳回
     */
    PACT_REVIEW_DISMISSAL_STATE_ERROR("EC00025","审核状态是待审核的才可以驳回"),
    /**
     * 该审核状态下合同无法修改或删除
     */
    PACT_NOT_UPDATE_DELTER("EC00026","该审核状态下合同无法修改或删除"),
    /**
     * 根据托出合同编码查询物业地址出错
     */
    ROOM_PROPERTY_QUERY_ERROR("EC00027","根据托出合同编码查询物业地址出错"),
    /**
     * 根据托出合同编码查询租客收款人信息出错
     */
    RENTER_PAYEE_QUERY_ERROR("EC00028","根据托出合同编码查询租客收款人信息出错"),
    /**
     * 收支id无效
     */
    FINANCE_RECEIPT_PAY_ID_INVALID("EC00105","收支id无效"),
    /**
     * 收款金额不能大于未收金额
     */
    FINANCE_RECEIPT_BIG_AMOUNT_INVALID("EC00106","收款金额不能大于未收金额"),
    /**
     * 付款金额不能大于未支金额
     */
    FINANCE_PAY_BIG_AMOUNT_INVALID("EC00107","付款金额不能大于未支金额"),
    /**
     * 应支没有审核通过
     */
    FINANCE_PAY_STATE_NO_REVIEW_INVALID("EC00108","应支没有审核通过"),
    /**
     * 合同状态无法转租
     */
    RENT_BASE_STATE_NO_TURN("EC00029","合同状态无法转租"),
    /**
     * 第一步转租协议缓存已失效，请重新填写
     */
    RENT_TURN_CACHE_INVALID("EC00030","第一步转租协议缓存已失效，请重新填写"),
    /**
     * 第二步选择客源缓存已失效，请重新填写
     */
    RENT_TURN_CUSTOMER_CACHE_INVALID("EC00031","第二步选择客源缓存已失效，请重新填写"),
    
    /**
     * 第三步登记租客缓存已失效，请重新填写
     */
    RENT_TURN_RENTER_CACHE_INVALID("EC00032","第三步登记租客缓存已失效，请重新填写"),
    /**
     * 第四步合同信息缓存已失效，请重新填写
     */
    RENT_TURN_PACT_CACHE_INVALID("EC00033","第四步合同信息缓存已失效，请重新填写"),
    /**
     * 保存托出转租协议失败
     */
    RENT_TURN_SAVE_FAIL("EC00034","保存托出转租协议失败"),

    /**
     * 托进合同审核状态只有'已审核'时才可以添加解约协议!
     */
    PURCHASE_STATE_NOTIS_3("EC00050","托进合同审核状态只有'已审核'时才可以添加解约协议"),
    /**
     * 托进合同解约协议已存在,不能再次创建
     */
    PURCHASE_TERMINATION_IS_EXIST("EC00051","托进解约协议已存在,不能再次创建"),
    /**
     * 托进合同解约协议编码无效
     */
    PURCHASE_TERMINATION_CODE_INVALID("EC00052","托进解约协议编码无效"),
    /**
     * 托进合同解约协议状态只有'草稿'、'已驳回'才可以修改
     */
    PURCHASE_TERMINATION_STATE_UPDATE_ERROR("EC00053","托进解约协议状态只有'草稿'、'已驳回'才可以修改"),
    /**
     * 托进合同解约协议状态只有'待审核'才可以审核
     */
    PURCHASE_TERMINATION_AUDIT_ERROR("EC00054","托进合同解约协议状态只有'待审核'才可以审核"),
    /**
     * 托进合同解约协议状态只有'待审核'才可以审核
     */
    PURCHASE_TERMINATION_RENTED_ERROR("EC00055","租客未清退，无法解约"),
    /**
     * 托进合同解约协议状态只有'草稿'、'已驳回'才可以删除
     */
    PURCHASE_TERMINATION_STATE_REMOVE_ERROR("EC00056","托进解约协议状态只有'草稿'、'已驳回'才可以删除"),

    /**
     * 申请审核转租协议失败
     */
    RENT_TURN_AUDIT_ERROR("EC00035","申请审核转租协议失败"),
    /**
     * 审核通过转租协议失败
     */
    RENT_TURN_REVIEWPASS_ERROR("EC00036","审核通过转租协议失败"),
    /**
     * 审核驳回转租协议失败
     */
    RENT_TURN_REVIEWDISMISSAL_ERROR("EC00037","审核驳回转租协议失败"),
    /**
     * 录入调房协议第一步redis已经失效
     */
    PACT_RENT_TRANSFER_REDIS_FAIL("EC01001","录入调房协议第一步缓存已经失效"),
    /**
     * 录入调房协议第二步缓存已经失效
     */
    PACT_RENT_ROOM_REDIS_FAIL("EC01002","录入调房协议第二步缓存已经失效"),
    /**
     * 录入调房协议第三步缓存已经失效
     */
    PACT_RENT_PACT_REDIS_FAIL("EC01003","录入调房协议第三步缓存已经失效"),
    /**
     * 录入调房协议第四步缓存已经失效
     */
    PACT_RENT_PROPERTY_REDIS_FAIL("EC01004","录入调房协议第四步缓存已经失效"),
    /**
     * 状态为待审核或已通过不允许修改或删除
     */
    PACT_RENT_TRANSFER_STATE_ERROR("EC01005","状态为待审核或已通过不允许修改或删除"),
    /**
     * 只有待审核才能审核通过
     */
    PACT_RENT_TRANSFER_CHECK_ERROR("EC01010","只有待审核才能审核通过"),
    /**
     * 保存物品信息报错
     */
    INSERT_GOODS_ERROR("EC01011","保存物品信息失败"),
    /**
     * 没有查询到物品信息
     */
    NO_GOODS_INFO("EC01012","没有查询到物品信息"),
    /**
     * 修改物品信息失败
     */
    UPDATE_GOODS_ERROR("EC01014","修改物品信息失败"),
    /**
     * 删除物品信息失败
     */
    DEL_GOODS_ERROR("EC01015","删除物品信息失败"),
    /**
     * 已经是删除状态
     */
    PACT_RENT_TRANSFER_ALREADY_DEL("EC01006","已经是删除状态"),
    /**
     * 只有草稿和已驳回状态可以申请审核
     */
    PACT_RENT_TRANSFER_APPLY_CHECK_STATE_ERROR("EC01007","只有草稿和已驳回状态可以申请审核"),
    /**
     * 没有查询到协议信息
     */
    PACT_RENT_TRANSFER_NO_INFO("EC01008","没有查询到协议信息"),
    /**
     * 只有待审核状态才能驳回
     */
    PACT_RENT_TRANSFER_REJECT_STATE_ERROR("EC01009","只有待审核状态才能驳回"),
    /**
     * 修改转租协议失败
     */
    RENT_TURN_UPDATE_ERROR("EC00038","修改转租协议失败"),
    /**
     * 删除转租协议失败
     */
    RENT_TURN_DELETE_ERROR("EC00039","删除转租协议失败"),
    /**
     * 合同状态无法续租
     */
    RENT_BASE_STATE_NO_CONTINUED("EC00040","合同状态无法续租"),
    /**
     * 第一步续签协议缓存已失效，请重新填写
     */
    RENT_CONTINUED_CACHE_INVALID("EC00041","第一步续签协议缓存已失效，请重新填写"),
    /**
     * 第二步续签协议合同缓存已失效，请重新填写
     */
    RENT_CONTINUED_PACT_CACHE_INVALID("EC00042","第二步续签协议合同缓存已失效，请重新填写"),
    /**
     * 保存续签协议失败
     */
    RENT_CONTINUED_SAVE_FAIL("EC00043","保存续签协议失败"),
    
    /**
     * 申请审核续签协议失败
     */
    RENT_CONTINUED_AUDIT_ERROR("EC00044","申请审核续签协议失败"),
    /**
     * 审核通过续签协议失败
     */
    RENT_CONTINUED_REVIEWPASS_ERROR("EC00045","审核通过续签协议失败"),
    /**
     * 审核驳回续签协议失败
     */
    RENT_CONTINUED_REVIEWDISMISSAL_ERROR("EC00046","审核驳回续签协议失败"),
    
    /**
     * 修改续签协议失败
     */
    RENT_CONTINUED_UPDATE_ERROR("EC00047","修改续签协议失败"),
    /**
     * 删除续签协议失败
     */
    RENT_CONTINUED_DELETE_ERROR("EC00048","删除续签协议失败"),
    /**
     * 已存在审核通过申报，无法创建申报
     */
    DECLARE_HOUSE_STATE_EXITS("EC00049","已存在审核通过申报，无法创建申报"),
    /**
     * 保存申报失败
     */
    DECLARE_SAVE_FAIL("EC00050","保存申报失败"),
    /**
     * 审核通过申报失败
     */
    DECLARE_REVIEWPASS_ERROR("EC00051","审核通过申报失败"),
    /**
     * 审核状态无法进行审核
     */
    DECLARE_REVIEW_STATE_ERROR("EC00052","审核状态无法进行审核"),
    /**
     * 已有审核通过申报,无法审核通过
     */
    DECLARE_HOUSE_PASS_EXIT("EC00053","已有审核通过申报,无法审核通过"),
    /**
     * 该申报无法进行驳回
     */
    DECLARE_REVIEWDISMISSAL_ERROR("EC00054","该申报无法进行驳回"),
    /**
     * 申报驳回失败
     */
    DECLARE_REVIEWDISMISSAL_FAIL("EC00055","申报驳回失败"),
    /**
     * 审核驳回才可以复制申报
     */
    DECLARE_STATE_NO_COPY("EC00056","审核驳回才可以复制申报"),
    
    /**
     * 当前状态下无法复制
     */
    DECLARE_HOUSE_PASS_NO_COPY("EC00057","当前状态下无法复制"),
    
    /**
     * 设置延期时间失败
     */
    RENTBASE_SET_DELAYDATE_ERROR("EC00058","设置延期时间失败"),
    /**
     * 照片,物品,户型图未填
     */
    DECLARE_SURVEY_ERROR("EC00059","实勘的照片,物品,户型图未填,请完善相应信息"),
    /**
     * 保存托出解约协议失败
     */
    RENT_TERMINATION_SAVE_ERROR("EC00060","保存托出解约协议失败"),
	/**
	 * 没有该收支的权限
	 */
	FINANCERECEIPTPAY_NO_PERMISSIONS("EC00061","没有该收支的权限"),
	/**
     * 没有该拖进合同的权限
     */
	PURCHASEBASE_NO_PERMISSIONS("EC00062","没有该拖进合同的权限"),
	/**
     * 没有该拖进合同解约协议的权限
     */
	PURCHASETERMINATION_NO_PERMISSIONS("EC00063","没有该拖进合同解约协议的权限"),
	/**
     * 已经存在该房源的拖进合同
     */
	PURCHASEBASE_HOUSE_EXITS("EC00064","已经存在该房源的拖进合同"),
	/**
     * 没有该收支的权限
     */
    RENTBASE_NO_PERMISSIONS("EC00065","没有该托出合同的权限"), 
    /**
     * 没有该转租协议的权限
     */
    RENTTURN_NO_PERMISSIONS("EC00066","没有该转租协议的权限"), 
    /**
     * 没有该续租协议的权限
     */
    RENTCONTINUED_NO_PERMISSIONS("EC00067","没有该续租协议的权限"), 
    /**
     * 没有该调房协议的权限
     */
    TRANSFERROOM_NO_PERMISSIONS("EC00068","没有该调房协议的权限"), 
    /**
     * 没有该托出解约协议的权限
     */
    RENTTERMINATION_NO_PERMISSIONS("EC00069","没有该托出解约协议的权限"), 
    /**
     * 获取指定日期的天数出错
     */
    GETDAY_BYCALENDAR_ERROR("EC00070","获取指定日期的天数出错"),
    /**
     * 获取指定日期的月份出错
     */
    GETMONTH_BYCALENDAR_ERROR("EC00071","获取指定日期的月份出错"), 
    /**
     * 获取指定日期的年份出错
     */
    GETYEAR_BYCALENDAR_ERROR("EC00072","获取指定日期的年份出错"), 
    /**
     * 修改待办事务统计表的状态出错
     */
    UPDATE_BACKLOGSTATISTICS_ERROR("EC00073","修改待办事务统计表的状态出错"), 
    /**
     * 根据资源编码集合删除待办事务统计记录出错
     */
    DELETE_BACKLOGSTATISTICS_ERROR("EC00074","根据资源编码集合删除待办事务统计记录出错"), 
    /**
     * 保存本月人均效能/本月营业收入/本月现金流量统计记录出错
     */
    SAVE_SITUATIONDO_ERROR("EC00075","保存本月人均效能/本月营业收入/本月现金流量统计记录出错"),  
    /**
     * 保存首页托进房间托进房间数统计记录出错
     */
    SAVE_PUR_ROOMSTATIS_ERROR("EC00076","保存首页托进房间托进房间数统计记录出错"), 
    /**
     * 添加待办事务统计待收款统计记录出错
     */
    SAVE_RECEIVABLES_STATISTICS_ERROR("EC00077","添加待办事务统计待收款统计记录出错"), 
    /**
     * 添加待办事务统计待付款统计记录出错
     */
    SAVE_OBLIGATION_STATISTICS_ERROR("EC00078","添加待办事务统计待付款统计记录出错"), 
    /**
     * 原合同合同解约状态不合法，应该是未解约
     */
    OLD_PACT_CANCELSTATE_NOTVALID("EC00079","原合同合同解约状态不合法，应该是未解约"), 
    /**
     * 原合同合同到期状态不合法，应该是未到期
     */
    OLD_PACT_DUESTATE_NOTVALID("EC00080","原合同合同到期状态不合法，应该是未到期 "), 
    /**
     * 合同收支必填
     */
    RENT_CONTINUED_PACT_RECPAY_IS_NULL("EC00081","合同收支必填 "), 
    /**
     * 实支费用状态不是已付款
     */
    FINANCE_PAY_STATE_NO_PAY_INVALID("EC00082","实支费用状态不是已付款"),
    /**
     * 托出合同资源转移，找不到租后管家
     */
    TRANSFER_RENTBASE_ERROR("EC00083","找不到租后管家,合同编码："),
    /**
     * 托进解约协议没有维护人
     */
    TRANSFER_PURTERMINATION_ERROR("EC00084","托进解约协议没有维护人,托进解约协议编码："),
    /**
     * 托出解约协议没有维护人
     */
    TRANSFER_RENTTERMINATION_ERROR("EC00085","托出解约协议没有维护人,托出解约协议编码："), 
    /**
     * 转租协议没有维护人
     */
    TRANSFER_RENTTURN_ERROR("EC00086","转租协议没有维护人,转租协议编码："),
    /**
     * 续签协议没有维护人
     */
    TRANSFER_RENTCONTINUED_ERROR("EC00087","续签协议没有维护人,续签协议编码："), 
    /**
     * 调房协议没有维护人
     */
    TRANSFER_ROOM_ERROR("EC00088","调房协议没有维护人,续签协议编码："), 
    
    ;
	/**
	 * 错误码
	 */
	private String code;
	
    /**
     * 错误信息
     */
    private String message;
      
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
    private   PactCommonEnum(String code,String message){
    	this.code=code;
    	this.message=message;
    }
}
