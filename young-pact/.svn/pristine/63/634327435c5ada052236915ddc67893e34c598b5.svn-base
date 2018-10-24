package com.young.pact.api.service.rest.purchasetermination;

import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.purchasetermination.PurchaseTerminationParam;
import com.young.pact.api.domain.param.rest.purchasetermination.PurchaseTerminationQueryParam;
import com.young.pact.api.domain.result.rest.purchasetermination.PurchaseTerminationBaseResult;
import com.young.pact.api.domain.result.rest.purchasetermination.PurchaseTerminationEditResult;
import com.young.pact.api.domain.result.rest.purchasetermination.PurchaseTerminationListResult;

/**
 * 
* @ClassName: PurchaseTerminationRestService
* @Description: 托进解约协议
* @author SunYiXuan
* @date 2018年8月9日 下午2:50:47
*
 */
public interface PurchaseTerminationRestService {

    /**
     * 
    * @Title: savePurchaseTermination
    * @Description: 添加解约协议
    * @author SunYiXuan
    * @param param
    * @return
    * @throws
     */
    ApiResult<Boolean> savePurchaseTermination(PurchaseTerminationParam param);

    
    /**
     * 
    * @Title: listPurchaseTermination
    * @Description: 查询解约协议列表
    * @author SunYiXuan
    * @param param
    * @return
    * @throws
     */
    ApiResult<PageBean<PurchaseTerminationListResult>> listPurchaseTermination(PurchaseTerminationQueryParam param);


    /**
     * 
    * @Title: getPurchaseTerminationBase
    * @Description: 获取托进合同解约协议基本信息
    * @author SunYiXuan
    * @param terminationCode
    * @return
    * @throws
     */
    ApiResult<PurchaseTerminationBaseResult> getPurchaseTerminationBase(PurchaseTerminationQueryParam param);


    /**
     * 
    * @Title: getPurchaseTerminationEdit
    * @Description: 获取拖进合同解约协议编辑页信息
    * @author SunYiXuan
    * @param terminationCode
    * @return
    * @throws
     */
    ApiResult<PurchaseTerminationEditResult> getPurchaseTerminationEdit(String terminationCode);


    /**
     * 
    * @Title: updatePurchaseTermination
    * @Description: 修改拖进合同解约协议
    * @author SunYiXuan
    * @param param
    * @return
    * @throws
     */
    ApiResult<Boolean> updatePurchaseTermination(PurchaseTerminationParam param);


    /**
     * 
    * @Title: removePurchaseTermination
    * @Description: 删除托进合同解约协议
    * @author SunYiXuan
    * @param param
    * @return
    * @throws
     */
    ApiResult<Boolean> removePurchaseTermination(PurchaseTerminationParam param);


    /**
     * 
    * @Title: purchaseTerminationApply
    * @Description: 申请审核
    * @author SunYiXuan
    * @param param
    * @return
    * @throws
     */
    ApiResult<Boolean> purchaseTerminationApply(PurchaseTerminationParam param);


    /**
     * 
    * @Title: purchaseTerminationReject
    * @Description: 审核驳回
    * @author SunYiXuan
    * @param param
    * @return
    * @throws
     */
    ApiResult<Boolean> purchaseTerminationReject(PurchaseTerminationParam param);
    /**
     * 
    * @Title: purchaseTerminationReview
    * @Description: TODO( 托进解约审核通过)
    * @author HeZeMin
    * @param param  审核信息
    * @return   返回审核结果
     */
    ApiResult<Boolean> purchaseTerminationReview(PurchaseTerminationParam param);
}
