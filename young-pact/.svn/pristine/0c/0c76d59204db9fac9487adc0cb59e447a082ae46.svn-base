package com.young.pact.manager.purchasehouse;

import com.young.pact.domain.purchasehouse.PurchaseHouseDO;
import com.young.pact.domain.purchasehouse.PurchaseHouseQuery;
import com.young.pact.domain.purchasehouse.PurchaseHouseVO;

/**
 * 
* @ClassName: PurchaseHouseManager
* @Description: TODO( 托进房源信息)
* @author HeZeMin
* @date 2018年8月1日 下午4:40:19
*
 */
public interface PurchaseHouseManager {
    /**
     * 
    * @Title: saveHouseRedis
    * @Description: TODO( 把托进房源信息存到redis中)
    * @author HeZeMin
    * @param purchaseHouseDO 托进房源信息
    * @param redisKey 业务redis key
     */
    void saveHouseRedis(PurchaseHouseDO purchaseHouseDO, String redisKey);
    /**
     * 
    * @Title: getHouseRedis
    * @Description: TODO( 根据业务redis key查询托进房源信息)
    * @author HeZeMin
    * @param redisKey   业务redis key
    * @return 托进房源信息
     */
    PurchaseHouseDO getHouseRedis(String redisKey);
    /**
     * 
    * @Title: getPurchaseHouse
    * @Description: TODO( 根据托进合同编码查询托进合同房源信息)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同房源信息
     */
    PurchaseHouseVO getPurchaseHouse(String purchaseCode);
    /**
     * 
    * @Title: updatePurchaseHouse
    * @Description: TODO( 修改托进合同房源信息)
    * @author HeZeMin
    * @param purchaseHouseDO    托进合同房源信息
    * @return   返回修改结果
     */
    boolean updatePurchaseHouse(PurchaseHouseDO purchaseHouseDO);
    /**
    * @Title: exitPurchaseHouseByParam
    * @Description: TODO( 根据房源编码查询拖进合同是否存在 )
    * @author GuoXiaoPeng
    * @param purchaseHouseQuery 房源编码
    * @return 存在返回true，否则返回false
    * @throws 异常
     */
    boolean exitPurchaseHouseByParam(PurchaseHouseQuery purchaseHouseQuery);
}
