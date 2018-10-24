package com.young.pact.service.commontradingrecord.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.tools.common.util.convert.BeanUtils;
import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.pact.api.domain.param.rest.commontradingrecord.CommonTradingRecordQueryParam;
import com.young.pact.api.domain.result.rest.commontradingrecord.CommonTradingRecordResult;
import com.young.pact.api.service.rest.commontradingrecord.CommonTradingRecordService;
import com.young.pact.common.dict.PactCommonEnum;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordQuery;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordVO;
import com.young.pact.manager.commontradingrecord.TradingRecordManager;

/**
 * @描述 : 公共交易-交易记录
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年8月25日 上午8:52:38
 */
@Service("commonTradingRecordService")
public class TradingRecordServiceImpl implements CommonTradingRecordService {
    private static final Log LOG = LogFactory.getLog(TradingRecordServiceImpl.class); 
    private TradingRecordManager tradingRecordManager;
    @Override
    public ApiResult<PageBean<CommonTradingRecordResult>> listTradingRecord(CommonTradingRecordQueryParam param) {
        ApiResult<PageBean<CommonTradingRecordResult>> result = new ApiResult<>();
        try {
            if(null == param){
                result.setCode(PactCommonEnum.PARAM_IS_NULL.getCode());
                result.setMessage(PactCommonEnum.PARAM_IS_NULL.getMessage());
                return result;
            }
            CommonTradingRecordQuery query = new CommonTradingRecordQuery();
            BeanUtils.copyProperties(param, query);
            PageBean<CommonTradingRecordVO> pageBean = tradingRecordManager.listTradingRecord(query);
            PageBean<CommonTradingRecordResult> page = new PageTableBean<>(query.getPageIndex(), query.getPageSize());
            page.setTotalItem(pageBean.getTotalItem());
            BeanUtils.copyListProperties(pageBean.getData(), page.getData(), CommonTradingRecordResult.class);
            result.setCode(CommonEnum.REQUEST_SUCCESS.getCode());
            result.setMessage(CommonEnum.REQUEST_SUCCESS.getMessage());
            result.setData(page);
        } catch(PactManagerExcepion e){
            LOG.error(e.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            result.setCode(CommonEnum.SYSTEM_EXCEPTION.getCode());
            result.setMessage(CommonEnum.SYSTEM_EXCEPTION.getMessage());
        }
        return result;
    }
    public TradingRecordManager getTradingRecordManager() {
        return tradingRecordManager;
    }
    public void setTradingRecordManager(TradingRecordManager tradingRecordManager) {
        this.tradingRecordManager = tradingRecordManager;
    }

}
