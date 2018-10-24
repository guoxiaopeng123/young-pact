package com.young.pact.manager.purchasehouse;

import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseOwnerVO;

/**
 * 
* @ClassName: PurchaseHouseOwnerManager
* @Description: TODO( 托进业主信息)
* @author HeZeMin
* @date 2018年8月1日 下午4:40:33
*
 */
public interface PurchaseHouseOwnerManager {
    /**
     * 
    * @Title: saveHouseOwnerRedis
    * @Description: TODO( 保存托进业主信息到redis中)
    * @author HeZeMin
    * @param purchaseHouseOwnerDO 业主信息
    * @param redisKey 返回redis key
     */
    void saveHouseOwnerRedis(PurchaseHouseOwnerDO purchaseHouseOwnerDO, String redisKey);
    /**
     * 
    * @Title: getHouseOwnerRedis
    * @Description: TODO( 根据redis key查询托进业主信息)
    * @author HeZeMin
    * @param redisKey   redis key
    * @return   返回托进业主信息
     */
    PurchaseHouseOwnerDO getHouseOwnerRedis(String redisKey);
    /**
     * 
    * @Title: getPurchaseHouseOwner
    * @Description: TODO( 根据托进合同编码查询托进合同业主信息)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同业主信息
     */
    PurchaseHouseOwnerVO getPurchaseHouseOwner(String purchaseCode);
    /**
     * 
    * @Title: updatePurchaseHouseOwner
    * @Description: TODO( 修改托进合同业主信息)
    * @author HeZeMin
    * @param purchaseHouseOwnerDO   托进合同业主信息
    * @return   返回修改结果
     */
    boolean updatePurchaseHouseOwner(PurchaseHouseOwnerDO purchaseHouseOwnerDO);
}
