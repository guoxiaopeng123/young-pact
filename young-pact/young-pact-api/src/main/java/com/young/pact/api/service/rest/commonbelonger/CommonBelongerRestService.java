package com.young.pact.api.service.rest.commonbelonger;

import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.commonbelonger.CommonBelongerParam;

/**
 * 
* @ClassName: CommonBelongerRestService
* @Description: TODO( 合同责任人)
* @author HeZeMin
* @date 2018年8月5日 下午3:32:22
*
 */
public interface CommonBelongerRestService {
    /**
     * 
    * @Title: updateOperateBelonger
    * @Description: TODO( 分配运营管家)
    * @author HeZeMin
    * @param param  托进合同编码，运营管家pin
    * @return   返回分配结果
     */
    ApiResult<Boolean> updateOperateBelonger(CommonBelongerParam param);
    /**
     * 
    * @Title: updateServiceBelonger
    * @Description: TODO( 分配租后管家)
    * @author HeZeMin
    * @param param  托进合同编码，租后管家pin
    * @return   分配结果
     */
    ApiResult<Boolean> updateServiceBelonger(CommonBelongerParam param);
}
