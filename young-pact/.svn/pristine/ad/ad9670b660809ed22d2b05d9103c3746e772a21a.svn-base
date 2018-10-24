package com.young.pact.manager.commontradingrecord.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.exception.DaoException;
import com.young.common.page.PageBean;
import com.young.common.page.PageTableBean;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.commontradingrecord.CommonTradingRecordDao;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordQuery;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordVO;
import com.young.pact.manager.commontradingrecord.TradingRecordManager;

/**
 * @描述 : 公共交易-交易记录
 * @创建者 : guoxiaopeng
 * @创建时间 : 2018年8月25日 上午8:56:47
 */
@Component("tradingRecordManager")
public class TradingRecordManagerImpl implements TradingRecordManager {
    private static final Log LOG = LogFactory.getLog(TradingRecordManagerImpl.class);
    private CommonTradingRecordDao commonTradingRecordDao;
    @Override
    public PageBean<CommonTradingRecordVO> listTradingRecord(CommonTradingRecordQuery query) {
        try {
            Integer count =  commonTradingRecordDao.countTradingRecord(query);
            PageBean<CommonTradingRecordVO> page = new PageTableBean<>(query.getPageIndex(), query.getPageSize());
            if(null != count && count != 0) {
                page.setTotalItem(count);
                int startRow = page.getStartRow();
                if(startRow > 0) {
                    startRow -= 1;
                }
                query.setStartRow(startRow);
                List<CommonTradingRecordVO> list = commonTradingRecordDao.listTradingRecord(query);
                page.addAll(list);
            }
            return page;
        } catch (DaoException e) {
            // TODO: handle exception
            LOG.error(ErrorPactConsts.QUERY_TRADINGRECORD_TABLE_ERROR, e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_TRADINGRECORD_TABLE_ERROR,e);
        }
    }
    public CommonTradingRecordDao getCommonTradingRecordDao() {
        return commonTradingRecordDao;
    }
    public void setCommonTradingRecordDao(CommonTradingRecordDao commonTradingRecordDao) {
        this.commonTradingRecordDao = commonTradingRecordDao;
    }

}
