package com.young.pact.dao.commontradingrecord.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.commontradingrecord.CommonTradingRecordDao;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordDO;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordQuery;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordVO;

/**
 * 
* @ClassName: CommonTradingRecordDaoImpl
* @Description: TODO( 公共交易-交易记录 )
* @author GuoXiaoPeng
* @date 2018年10月9日 上午10:19:37
*
 */
@Repository("commonTradingRecordDao")
public class CommonTradingRecordDaoImpl extends BaseDao implements CommonTradingRecordDao {

    @SuppressWarnings("deprecation")
    @Override
    public Long saveTradingRecord(CommonTradingRecordDO commonTradingRecordDO) throws DaoException {
        try {
            return (Long) this.insert("CommonTradingRecord.saveTradingRecord", commonTradingRecordDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<CommonTradingRecordVO> listTradingRecord(CommonTradingRecordQuery query) throws DaoException {
        try {
            return this.queryForList("CommonTradingRecord.listTradingRecord", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Integer countTradingRecord(CommonTradingRecordQuery query) throws DaoException {
        try {
            return  (Integer) this.queryForObject("CommonTradingRecord.countTradingRecord", query);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
