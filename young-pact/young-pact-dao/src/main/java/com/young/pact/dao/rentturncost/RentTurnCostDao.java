package com.young.pact.dao.rentturncost;

import java.util.List;

import com.young.common.exception.DaoException;
import com.young.pact.domain.rentturncost.RentTurnCostDO;
import com.young.pact.domain.rentturncost.RentTurnCostVO;

/**
 * @描述 : 转租协议费用明细
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月8日 下午5:58:44
 */
public interface RentTurnCostDao {

    /**
    * @Title: saveRentTurnCost
    * @Description: TODO( 保存转租协议)
    * @author GuoXiaoPeng
    * @param rentTurnCostDOs 转租协议集合
    * @return 记录数
    * @throws DaoException
    * @throws 异常
     */
    int saveRentTurnCost(List<RentTurnCostDO> rentTurnCostDOs) throws DaoException;

    /**
    * @Title: listTurnCostByReletCode
    * @Description: TODO( 根据转租协议编码查询转让费用集合)
    * @author GuoXiaoPeng
    * @param reletCode 转租协议编码
    * @return 转让费用集合
    * @throws DaoException
    * @throws 异常
     */
    List<RentTurnCostVO> listTurnCostByReletCode(String reletCode)throws DaoException;
    /**
    * @Title: removeRentTurnCost
    * @Description: TODO(根据协议编码删除转租协议转让费 )
    * @author GuoXiaoPeng
    * @param reletCode 协议编码
    * @return 记录数
    * @throws DaoException
    * @throws  异常
     */
    Long removeRentTurnCost(String reletCode)throws DaoException;
}
