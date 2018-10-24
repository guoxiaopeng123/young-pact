package com.young.pact.api.service.rest.propertytransfer;

import com.young.common.domain.ApiResult;
import com.young.pact.api.domain.param.rest.propertytransfer.PropertyTransferParam;
import com.young.pact.api.domain.result.rest.propertytransfer.PropertyTransferResult;

/**
 * 
* @ClassName: PropertyTransferRestService
* @Description: TODO( 物业交接)
* @author HeZeMin
* @date 2018年8月1日 下午9:58:29
*
 */
public interface PropertyTransferRestService {
    /**
     * 
    * @Title: savePropertyTransferRedis
    * @Description: TODO( 把托进合同第四步 物业交接 保存到redis中)
    * @author HeZeMin
    * @param param  物业交接
    * @return   返回redis key
     */
    ApiResult<String> savePropertyTransferRedis(PropertyTransferParam param);
    /**
     * 
    * @Title: getPropertyTransferRedis
    * @Description: TODO( 根据redis key查询缓存中托进合同物业交接)
    * @author HeZeMin
    * @param redisKey   redis key
    * @return   返回托进合同物业交接
     */
    ApiResult<PropertyTransferResult> getPropertyTransferRedis(String redisKey);
}
