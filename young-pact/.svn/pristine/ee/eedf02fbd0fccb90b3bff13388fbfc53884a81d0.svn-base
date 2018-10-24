package com.young.pact.dao.declareprice;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.declareprice.DeclarePriceDO;
import com.young.pact.domain.declareprice.DeclarePriceVO;

/**
 * @描述 : 申报价格
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月13日 下午9:00:23
 */
public interface DeclarePriceDao {

    /**
    * @Title: saveDeclarePrice
    * @Description: TODO( 批量插入申报信息)
    * @author GuoXiaoPeng
    * @param declarePriceDOs 申报信息集合
    * @return 记录数、
    * @throws DaoException;
    * @throws 异常
     */
    int saveDeclarePrice(List<DeclarePriceDO> declarePriceDOs) throws DaoException;
    /**
    * @Title: listDeclarePriceByDecCode
    * @Description: TODO( 根据申报编码查询申报价格集合 )
    * @author GuoXiaoPeng
    * @param declareCode 申报编码
    * @return 申报价格集合
    * @throws DaoException;
    * @throws 异常
     */
    List<DeclarePriceVO> listDeclarePriceByDecCode(String declareCode)throws DaoException;

}
