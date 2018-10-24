package com.young.pact.manager.rentturncost.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.stereotype.Component;

import com.young.common.exception.DaoException;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.rentturncost.RentTurnCostDao;
import com.young.pact.domain.rentturncost.RentTurnCostVO;
import com.young.pact.manager.rentturncost.RentTurnCostManager;

/**
 * @描述 : 转租协议转让费用
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月9日 下午1:52:04
 */
@Component("rentTurnCostManager")
public class RentTurnCostManagerImpl implements RentTurnCostManager {

    private static Log LOG = LogFactoryImpl.getLog(RentTurnCostManagerImpl.class); 
    private RentTurnCostDao rentTurnCostDao;
    @Override
    public List<RentTurnCostVO> listTurnCostByReletCode(String reletCode) {
        try {
            List<RentTurnCostVO> costVOs  = rentTurnCostDao.listTurnCostByReletCode(reletCode);
            return costVOs;
        } catch (DaoException e) {
            LOG.error(ErrorPactConsts.QUERY_RENTTURNCOST_DETAIL_ERROR , e);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_RENTTURNCOST_DETAIL_ERROR , e);
        }
    }
    public RentTurnCostDao getRentTurnCostDao() {
        return rentTurnCostDao;
    }
    public void setRentTurnCostDao(RentTurnCostDao rentTurnCostDao) {
        this.rentTurnCostDao = rentTurnCostDao;
    }

    
}
