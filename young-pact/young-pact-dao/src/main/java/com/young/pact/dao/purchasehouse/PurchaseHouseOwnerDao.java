package com.young.pact.dao.purchasehouse;

import com.young.common.exception.DaoException;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerVO;

/**
 * 
* @ClassName: PurchaseHouseOwnerDao
* @Description: TODO( 托进合同业主)
* @author HeZeMin
* @date 2018年8月2日 下午9:08:55
*
 */
public interface PurchaseHouseOwnerDao {
    /**
     * 
    * @Title: savePurchaseHouseOwner
    * @Description: TODO( 保存托进合同业主信息)
    * @author HeZeMin
    * @param purchaseHouseOwnerDO   托进合同业主信息
    * @return   返回id
    * @throws   异常
     */
    Long savePurchaseHouseOwner(PurchaseHouseOwnerDO purchaseHouseOwnerDO) throws DaoException;
    /**
     * 
    * @Title: getPurchaseHouseOwner
    * @Description: TODO( 根据托进合同编码查询托进合同业主信息)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同业主信息
    * @throws   异常
     */
    PurchaseHouseOwnerVO getPurchaseHouseOwner(String purchaseCode) throws DaoException;
    /**
     * 
    * @Title: removePurchaseHouseOwner
    * @Description: TODO( 根据托进合同编码删除托进合同业主信息)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回影响行数
    * @throws   异常
     */
    int removePurchaseHouseOwner(String purchaseCode) throws DaoException;
    /**
     * 
    * @Title: updatePurchaseHouseOwner
    * @Description: TODO( 修改托进合同业主信息)
    * @author HeZeMin
    * @param purchaseHouseOwnerDO   托进合同业主信息
    * @return   返回修改结果
    * @throws   异常
     */
    int updatePurchaseHouseOwner(PurchaseHouseOwnerDO purchaseHouseOwnerDO) throws DaoException;
}
