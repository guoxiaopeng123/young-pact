package com.young.pact.dao.rentcustomer.impl;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.rentcustomer.RentCustomerDao;
import com.young.pact.domain.rentcustomer.RentCustomerDO;
import com.young.pact.domain.rentcustomer.RentCustomerVO;

/**
 * @描述 : 托出租客基本操作dao
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月2日 下午9:59:07
 */
@Repository("rentCustomerDao")
public class RentCustomerDaoImpl extends BaseDao implements RentCustomerDao {

    @SuppressWarnings("deprecation")
    @Override
    public Long saveRentCustomer(RentCustomerDO rentCustomerDO)throws DaoException {
        try {
            return (Long) this.insert("RentCustomer.saveRentCustomer", rentCustomerDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public RentCustomerVO getRentCustomerByPactCode(String rentPactCode)throws DaoException {
        try {
            return  (RentCustomerVO) this.queryForObject("RentCustomer.getRentCustomerByPactCode", rentPactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Long updateRentCustomerByPactCode(RentCustomerDO rentCustomerDO) throws DaoException {
        try {
            return (long) this.update("RentCustomer.updateRentCustomerByPactCode", rentCustomerDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public Integer removeRentCustomer(String rentPactCode)throws DaoException  {
        try {
            return this.delete("RentCustomer.removeRentCustomer", rentPactCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
