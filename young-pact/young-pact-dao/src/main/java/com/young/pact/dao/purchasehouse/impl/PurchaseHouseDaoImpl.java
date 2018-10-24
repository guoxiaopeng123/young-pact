package com.young.pact.dao.purchasehouse.impl;

import org.springframework.stereotype.Repository;

import com.tools.common.ibatis.base.BaseDao;
import com.young.common.exception.DaoException;
import com.young.pact.dao.purchasehouse.PurchaseHouseDao;
import com.young.pact.domain.purchasehouse.PurchaseHouseDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseQuery;
import com.young.pact.domain.purchasehouse.PurchaseHouseVO;
/**
 * 
* @ClassName: PurchaseHouseDaoImpl
* @Description: TODO( 托进合同房源)
* @author HeZeMin
* @date 2018年8月2日 下午9:09:34
*
 */
@SuppressWarnings("all")
@Repository("purchaseHouseDao")
public class PurchaseHouseDaoImpl extends BaseDao implements PurchaseHouseDao {

    @Override
    public Long savePurchaseHouse(PurchaseHouseDO purchaseHouseDO) throws DaoException {
        try {
            return (Long) this.insert("PurchaseHouse.savePurchaseHouse", purchaseHouseDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PurchaseHouseVO getPurchaseHouse(String purchaseCode) throws DaoException {
        try {
            return (PurchaseHouseVO) this.queryForObject("PurchaseHouse.getPurchaseHouse", purchaseCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int removePurchaseHouse(String purchaseCode) throws DaoException {
        try {
            return this.delete("PurchaseHouse.removePurchaseHouse", purchaseCode);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int updatePurchaseHouse(PurchaseHouseDO purchaseHouseDO) throws DaoException {
        try {
            return this.update("PurchaseHouse.updatePurchaseHouse", purchaseHouseDO);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public PurchaseHouseVO getPurchaseHouseByParam(PurchaseHouseQuery purchaseHouseQuery) throws DaoException {
        try {
            return (PurchaseHouseVO) this.queryForObject("PurchaseHouse.getPurchaseHouseByParam", purchaseHouseQuery);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}
