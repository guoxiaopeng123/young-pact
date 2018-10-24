package com.young.pact.manager.rentcommonowner.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.stereotype.Component;

import com.young.common.exception.DaoException;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.dao.rentcommonowner.CommonOwnerDao;
import com.young.pact.domain.rentcommonowner.RentCommonOwnerVO;
import com.young.pact.manager.rentcommonowner.CommonOwnerManager;

/**
 * @描述 : 托出合同租客共同居住人
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月5日 下午3:52:14
 */
@Component("commonOwnerManager")
public class CommonOwnerManagerImpl implements CommonOwnerManager {
    private static Log LOG = LogFactoryImpl.getLog(CommonOwnerManagerImpl.class);
    private CommonOwnerDao commonOwnerDao;
    @Override
    public List<RentCommonOwnerVO> listCommonOwnerByRenterCode(String renterCode) {
        try {
            List<RentCommonOwnerVO> list = commonOwnerDao.listCommonOwnerByRenterCode(renterCode);
            return list;
        } catch (DaoException e) {
            LOG.error(e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_RENT_COMMON_OWNER_BY_RENTERCODE_ERROR , e);
        } catch (Exception e) {
            LOG.error(e);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_RENT_COMMON_OWNER_BY_RENTERCODE_ERROR , e);
        }
    }
    public CommonOwnerDao getCommonOwnerDao() {
        return commonOwnerDao;
    }
    public void setCommonOwnerDao(CommonOwnerDao commonOwnerDao) {
        this.commonOwnerDao = commonOwnerDao;
    }
}
