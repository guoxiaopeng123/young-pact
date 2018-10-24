package com.young.pact.dao.commontradingrecord;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordDO;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordQuery;
import com.young.pact.domain.commontradingrecord.CommonTradingRecordVO;

/**
 * 
* @ClassName: CommonTradingRecordDao
* @Description: TODO( 公共交易-交易记录 )
* @author GuoXiaoPeng
* @date 2018年10月9日 上午10:19:16
*
 */
public interface CommonTradingRecordDao {

    /**
    * @Title: saveTradingRecord
    * @Description: TODO( 添加交易记录)
    * @author GuoXiaoPeng
    * @param commonTradingRecordDO 交易记录
    * @return 主键id
    * @throws DaoException;
    * @throws 异常
     */
    Long saveTradingRecord(CommonTradingRecordDO commonTradingRecordDO)throws DaoException;
    /**
    * @Title: listTradingRecord
    * @Description: TODO( 根据房间编号查询交易列表 )
    * @author GuoXiaoPeng
    * @param query 房间编号
    * @return 交易列表
    * @throws DaoException;
    * @throws 异常
     */
    List<CommonTradingRecordVO> listTradingRecord(CommonTradingRecordQuery query)throws DaoException;
    /**
    * @Title: countTradingRecord
    * @Description: TODO( 根据房间编号查询交易总数 )
    * @author GuoXiaoPeng
    * @param query 房间编号
    * @return 交易总数 
    * @throws DaoException
    * @throws 异常
     */
    Integer countTradingRecord(CommonTradingRecordQuery query)throws DaoException;
}
