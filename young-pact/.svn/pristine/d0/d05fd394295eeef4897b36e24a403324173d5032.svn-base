package com.young.pact.api.service.rest.purchasebase;

import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.purchasebase.PurchaseBaseParam;
import com.young.pact.api.domain.param.rest.purchasebase.PurchaseBaseQueryParam;
import com.young.pact.api.domain.result.rest.purchasebase.PurchaseBaseListResult;
import com.young.pact.api.domain.result.rest.purchasebase.PurchaseBaseResult;

/**
 * 
* @ClassName: PurchaseBaseRestService
* @Description: TODO( 托进合同)
* @author HeZeMin
* @date 2018年8月2日 上午10:41:23
*
 */
public interface PurchaseBaseRestService {
    /**
     * 
    * @Title: savePurchaseBaseRedis
    * @Description: TODO( 保存第三步 合同信息到redis中)
    * @author HeZeMin
    * @param param 合同信息
    * @return   返回redis key
     */
    ApiResult<String> savePurchaseBaseRedis(PurchaseBaseParam param);
    /**
     * 
    * @Title: getPurchaseBaseRedis
    * @Description: TODO( 根据redis key 到缓存中查询第三步 合同信息)
    * @author HeZeMin
    * @param redisKey redis key
    * @return   返回合同信息
     */
    ApiResult<PurchaseBaseResult> getPurchaseBaseRedis(String redisKey);
    /**
     * 
    * @Title: savePurchaseBase
    * @Description: TODO( 保存托进合同)
    * @author HeZeMin
    * @param param  申报编码
    * @return   返回托进合同编码
     */
    ApiResult<String> savePurchaseBase(PurchaseBaseParam param);
    /**
     * 
    * @Title: listPurchaseBase
    * @Description: TODO( 托进合同列表)
    * @author HeZeMin
    * @param param  查询条件
    * @return   返回托进合同列表
     */
    ApiResult<PageBean<PurchaseBaseListResult>> listPurchaseBase(PurchaseBaseQueryParam param);
    /**
     * 
    * @Title: getPurchaseBase
    * @Description: TODO( 根据托进合同编码查询托进合同详情)
    * @author HeZeMin
    * @param param 托进合同编码
    * @return   返回托进合同详情
     */
    ApiResult<PurchaseBaseResult> getPurchaseBase(PurchaseBaseQueryParam param);
    /**
     * 
    * @Title: removePurchaseBase
    * @Description: TODO( 根据托进合同编码删除托进合同)
    * @author HeZeMin
    * @param param   托进合同编码
    * @return   返回删除是否成功
     */
    ApiResult<Boolean> removePurchaseBase(PurchaseBaseParam param);
    /**
     * 
    * @Title: purchaseBaseApply
    * @Description: TODO( 申请审核)
    * @author HeZeMin
    * @param param  托进合同编码
    * @return   返回审核结果
     */
    ApiResult<Boolean> purchaseBaseApply(PurchaseBaseParam param);
    /**
     * 
    * @Title: purchaseBasePassed
    * @Description: TODO( 审核通过)
    * @author HeZeMin
    * @param param  托进合同编码
    * @return   返回审核结果
     */
    ApiResult<Boolean> purchaseBasePassed(PurchaseBaseParam param);
    /**
     * 
    * @Title: purchaseBaseReject
    * @Description: TODO( 审核驳回)
    * @author HeZeMin
    * @param param  托进合同编码，原因
    * @return   返回审核结果
     */
    ApiResult<Boolean> purchaseBaseReject(PurchaseBaseParam param);
    /**
     * 
    * @Title: getPurchaseByPurchaseCode
    * @Description: TODO( 根据托进合同编码查询托进合同详情--修改托进合同需要)
    * @author HeZeMin
    * @param purchaseCode 托进合同编码
    * @return   返回托进合同详情
     */
    ApiResult<PurchaseBaseResult> getPurchaseByPurchaseCode(String purchaseCode);
    /**
     * 
    * @Title: updatePurchaseBase
    * @Description: TODO( 修改托进合同信息)
    * @author HeZeMin
    * @param param  托进合同信息
    * @return   返回修改结果
     */
    ApiResult<Boolean> updatePurchaseBase(PurchaseBaseParam param);
    /**
    * @Title: getRoomNumberByPurchaseCode
    * @Description: TODO( 根据拖进合同编码查询申报的拖进间数 )
    * @author GuoXiaoPeng
    * @param purchaseCode 拖进合同编码
    * @return 拖进间数 
    * @throws 异常
     */
    ApiResult<Integer> getRoomNumberByPurchaseCode(String purchaseCode);
}
