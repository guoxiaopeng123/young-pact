package com.young.pact.api.service.rpc.purchasebase;

import java.util.List;

import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rpc.purchasebase.PurchaseBaseParam;
import com.young.pact.api.domain.result.rpc.purchasebase.PurchaseBaseResult;

/**
 * 
* @ClassName: PurchaseBaseRpcService
* @Description: TODO( 托进合同)
* @author HeZeMin
* @date 2018年8月15日 上午11:37:45
*
 */
public interface PurchaseBaseRpcService {
    /**
     * 
    * @Title: getPurchaseBaseByCode
    * @Description: TODO( 根据托进合同编码查询托进合同详情)
    * @author HeZeMin
    * @param purchaseCode   托进合同编码
    * @return   返回托进合同详情
     */
    ApiResult<PurchaseBaseResult> getPurchaseBaseByCode(String purchaseCode);
    /**
     * 
    * @Title: updateStandard
    * @Description: TODO( 修改托进合同标准化状态)
    * @author HeZeMin
    * @param param  标准化信息
    * @return   修改结果
     */
    ApiResult<Boolean> updateStandard(PurchaseBaseParam param);
    /**
     * 
    * @Title: listPurchaseBase
    * @Description: TODO( 查询当前状态为未解约的托进合同集合  )
    * @author GuoXiaoPeng
    * @param param 合同解约状态terminationState：1未解约 2到期解约  3违约解约 
    * @return 托进合同集合
    * @throws 异常
     */
    ApiResult<List<PurchaseBaseResult>> listPurchaseBase(PurchaseBaseParam param);
}
