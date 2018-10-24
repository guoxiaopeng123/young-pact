package com.young.pact.api.service.rpc.commonbelonger;

import java.util.List;

import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rpc.commonbelonger.CommonBelongerParam;

/**
 * 
* @ClassName: CommonBelongerRpcService
* @Description: TODO( 合同责任人)
* @author HeZeMin
* @date 2018年8月15日 上午11:46:14
*
 */
public interface CommonBelongerRpcService {
    /**
     * 
    * @Title: saveCommonBelonger
    * @Description: TODO( 批量添加合同责任人信息)
    * @author HeZeMin
    * @param params 合同责任人信息集合
    * @return   返回添加结果
     */
    ApiResult<Boolean> saveCommonBelonger(List<CommonBelongerParam> params);
    /**
    * @Title: updateCommonBelonger
    * @Description: TODO( 修改 合同责任人信息)
    * @author GuoXiaoPeng
    * @param params 责任人信息
    * @return 返回结果
    * @throws 异常
     */
    ApiResult<Boolean> updateCommonBelonger(CommonBelongerParam params);
}
