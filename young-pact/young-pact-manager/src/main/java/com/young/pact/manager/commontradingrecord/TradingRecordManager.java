package com.young.pact.manager.commontradingrecord;

import com.young.common.page.PageBean;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordQuery;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordVO;

/**
 * @描述 : 公共交易-交易记录
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年8月25日 上午8:53:47
 */
public interface TradingRecordManager {
    /**
    * @Title: listTradingRecord
    * @Description: TODO( 分页查询交易列表 )
    * @author GuoXiaoPeng
    * @param query 查询条件
    * @return 交易列表
    * @throws 异常
     */
    PageBean<CommonTradingRecordVO> listTradingRecord(CommonTradingRecordQuery query);
}
