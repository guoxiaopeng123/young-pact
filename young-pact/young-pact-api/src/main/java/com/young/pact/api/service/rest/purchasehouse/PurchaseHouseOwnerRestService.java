package com.young.pact.api.service.rest.purchasehouse;

import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.purchasehouse.PurchaseHouseOwnerParam;
import com.young.pact.api.domain.result.rest.purchasehouse.PurchaseHouseOwnerResult;

/**
 * 
* @ClassName: PurchaseHouseOwnerRestService
* @Description: TODO( 托进合同房主信息)
* @author HeZeMin
* @date 2018年8月1日 下午1:44:10
*
 */
public interface PurchaseHouseOwnerRestService {
    /**
     * 
    * @Title: saveHouseOwnerRedis
    * @Description: TODO( 保存业主信息到redis中)
    * @author HeZeMin
    * @param param 业主信息
    * @return 返回redis key
     */
    ApiResult<String> saveHouseOwnerRedis(PurchaseHouseOwnerParam param);
    /**
     * 
    * @Title: getHouseOwnerRedis
    * @Description: TODO( 根据redis key查询业主信息)
    * @author HeZeMin
    * @param redisKey 第二步rediskey
    * @return 业主信息
     */
    ApiResult<PurchaseHouseOwnerResult> getHouseOwnerRedis(String redisKey);
    /**
     * 
    * @Title: getOwnerByPurchaseCode
    * @Description: TODO( 根据托进合同编码查询托进合同业主信息)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同业主信息
     */
    ApiResult<PurchaseHouseOwnerResult> getOwnerByPurchaseCode(String purchaseCode);
    /**
     * 
    * @Title: updatePurchaseHouseOwner
    * @Description: TODO( 修改托进合同业主信息)
    * @author HeZeMin
    * @param param  托进合同业主信息
    * @return   返回修改结果
     */
    ApiResult<Boolean> updatePurchaseHouseOwner(PurchaseHouseOwnerParam param);
}
