package com.young.pact.api.service.rest.commontradingrecord;

import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.commontradingrecord.CommonTradingRecordQueryParam;
import com.young.pact.api.domain.result.rest.commontradingrecord.CommonTradingRecordResult;

/**
 * @描述 : 公共交易-交易记录
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年8月25日 上午8:49:41
 */
public interface CommonTradingRecordService {

    /**
    * @Title: listTradingRecord
    * @Description: TODO( 分页查询交易记录列表 )
    * @author GuoXiaoPeng
    * @param param
    * @return
    * @throws
     */
    ApiResult<PageBean<CommonTradingRecordResult>> listTradingRecord(CommonTradingRecordQueryParam param);
}
