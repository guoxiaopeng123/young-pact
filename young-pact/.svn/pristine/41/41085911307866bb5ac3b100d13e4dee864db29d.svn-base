package com.young.pact.dao.purchasehouse;

import com.young.common.exception.DaoException;
import com.young.pact.domain.purchasehouse.PurchaseHouseDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseQuery;
import com.young.pact.domain.purchasehouse.PurchaseHouseVO;

/**
 * 
* @ClassName: PurchaseHouseDao
* @Description: TODO( 托进合同房源)
* @author HeZeMin
* @date 2018年8月2日 下午9:08:51
*
 */
public interface PurchaseHouseDao {
    /**
     * 
    * @Title: savePurchaseHouse
    * @Description: TODO( 保存托进合同房源信息)
    * @author HeZeMin
    * @param purchaseHouseDO    托进合同房源信息
    * @return   返回id
    * @throws   异常
     */
    Long savePurchaseHouse(PurchaseHouseDO purchaseHouseDO) throws DaoException;
    /**
     * 
    * @Title: getPurchaseHouse
    * @Description: TODO( 根据托进合同编码查询托进合同房源信息)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同房源信息
    * @throws   异常
     */
    PurchaseHouseVO getPurchaseHouse(String purchaseCode) throws DaoException;
    /**
     * 
    * @Title: removePurchaseHouse
    * @Description: TODO( 根据托进合同编码删除托进合同房源信息)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回影响行数
    * @throws   异常
     */
    int removePurchaseHouse(String purchaseCode) throws DaoException;
    /**
     * 
    * @Title: updatePurchaseHouse
    * @Description: TODO( 修改托进合同房源信息)
    * @author HeZeMin
    * @param purchaseHouseDO    托进合同房源信息
    * @return   返回修改结果
    * @throws   异常
     */
    int updatePurchaseHouse(PurchaseHouseDO purchaseHouseDO) throws DaoException;
    /**
    * @Title: getPurchaseHouseByParam
    * @Description: TODO( 根据房源编码查询拖进合同是否存在 )
    * @author GuoXiaoPeng
    * @param purchaseHouseQuery 房源编码
    * @return 拖进房源
    * @throws DaoException
    * @throws 异常
     */
    PurchaseHouseVO getPurchaseHouseByParam(PurchaseHouseQuery purchaseHouseQuery)throws DaoException;
}
