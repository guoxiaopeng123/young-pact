package com.young.pact.dao.rentturncost.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.rentturncost.RentTurnCostDao;
import com.young.pact.domain.rentturncost.RentTurnCostDO;
import com.young.pact.domain.rentturncost.RentTurnCostVO;

/**
 * @描述 : 转租协议费用明细
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月8日 下午6:00:45
 */
@Repository("rentTurnCostDao")
public class RentTurnCostDaoImpl extends BaseDao implements RentTurnCostDao {

    @Override
    public int saveRentTurnCost(List<RentTurnCostDO> rentTurnCostDOs) throws DaoException{
        try {
            return this.batchInsert("RentTurnCost.saveRentTurnCost", rentTurnCostDOs);
        } catch (Exception e) {
            // TODO: handle exception
            throw new DaoException(e);
        }
    }

    @SuppressWarnings({ "deprecation", "unchecked" })
    @Override
    public List<RentTurnCostVO> listTurnCostByReletCode(String reletCode) throws DaoException {
        try {
            return this.queryForList("RentTurnCost.listTurnCostByReletCode", reletCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Long removeRentTurnCost(String reletCode) throws DaoException {
        try {
            return (long) this.delete("RentTurnCost.removeRentTurnCost", reletCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
