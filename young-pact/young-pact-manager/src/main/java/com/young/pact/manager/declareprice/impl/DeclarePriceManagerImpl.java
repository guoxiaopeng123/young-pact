package com.young.pact.manager.declareprice.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.exception.DaoException;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.declareprice.DeclarePriceDao;
import com.young.pact.domain.declareprice.DeclarePriceVO;
import com.young.pact.manager.declareprice.DeclarePriceManager;

/**
 * @描述 : 申报价格
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月13日 下午4:42:36
 */
@Component("declarePriceManager")
public class DeclarePriceManagerImpl implements DeclarePriceManager {
    private static final Log LOG = LogFactory.getLog(DeclarePriceManagerImpl.class);
    private DeclarePriceDao declarePriceDao;
    @Override
    public List<DeclarePriceVO> listDeclarePriceByDecCode(String declareCode) {
        try {
           return  declarePriceDao.listDeclarePriceByDecCode(declareCode);
        } catch (DaoException e) {
            // TODO: handle exception
            LOG.error(ErrorPactConsts.LIST_DECLARE_BY_DECCODE_ERROR,e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_DECLARE_BY_DECCODE_ERROR,e);
        }
    }
    public DeclarePriceDao getDeclarePriceDao() {
        return declarePriceDao;
    }
    public void setDeclarePriceDao(DeclarePriceDao declarePriceDao) {
        this.declarePriceDao = declarePriceDao;
    }
}
