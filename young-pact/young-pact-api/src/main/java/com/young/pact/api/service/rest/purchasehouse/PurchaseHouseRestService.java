package com.young.pact.api.service.rest.purchasehouse;

import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.purchasehouse.PurchaseHouseParam;
import com.young.pact.api.domain.result.rest.purchasehouse.PurchaseHouseResult;

/**
 * 
* @ClassName: PurchaseHouseRestService
* @Description: TODO( 托进合同房源信息)
* @author HeZeMin
* @date 2018年8月1日 下午1:43:29
*
 */
public interface PurchaseHouseRestService {
    /**
     * 
    * @Title: saveHouseRedis
    * @Description: TODO( 保存托进合同房源信息到redis中)
    * @author HeZeMin
    * @param param 房源信息
    * @return 返回redis key
     */
    ApiResult<String> saveHouseRedis(PurchaseHouseParam param);
    /**
     * 
    * @Title: getHouseRedis
    * @Description: TODO( 根据redis key 查询托进合同房源信息)
    * @author HeZeMin
    * @param redisKey redis key
    * @return 房源信息
     */
    ApiResult<PurchaseHouseResult> getHouseRedis(String redisKey);
    /**
     * 
    * @Title: getHouseByPurchaseCode
    * @Description: TODO( 根据托进合同编码查询托进合同房源信息)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同房源信息
     */
    ApiResult<PurchaseHouseResult> getHouseByPurchaseCode(String purchaseCode);
    /**
     * 
    * @Title: updatePurchaseHouse
    * @Description: TODO( 修改托进合同房源信息)
    * @author HeZeMin
    * @param param  托进合同房源信息
    * @return   返回修改结果
     */
    ApiResult<Boolean> updatePurchaseHouse(PurchaseHouseParam param);
}
