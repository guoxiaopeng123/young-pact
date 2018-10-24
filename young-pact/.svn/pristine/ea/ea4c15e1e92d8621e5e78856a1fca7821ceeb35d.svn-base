package com.young.pact.api.service.rest.rentcontinued;

import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.rentbase.RentPactParam;
import com.young.pact.api.domain.param.rest.rentcontinued.RentContinuedParam;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;
import com.young.pact.api.domain.result.rest.rentcontinued.RentContinuedDetailResult;
import com.young.pact.api.domain.result.rest.rentcontinued.RentContinuedResult;

/**
 * @描述 : 续签协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月10日 下午8:48:46
 */
public interface RentContinuedService {
    
    /**
    * @Title: saveRentContinuedRedis
    * @Description: TODO( 缓存保存续签协议 )
    * @author GuoXiaoPeng
    * @param param 续签协议信息
    * @return 返回结果
    * @throws 异常
     */
    ApiResult<String> saveRentContinuedRedis(RentContinuedParam param);
    /**
    * @Title: getRentContinuedRedis
    * @Description: TODO(缓存查询续签协议 )
    * @author GuoXiaoPeng
    * @param redisKey 缓存的key 
    * @return 返回结果
    * @throws 异常
     */
    ApiResult<RentContinuedResult> getRentContinuedRedis(String redisKey);
    /**
    * @Title: saveRentContinuedPactRedis
    * @Description: TODO( 缓存保存合同)
    * @author GuoXiaoPeng
    * @param param 合同信息
    * @return 返回结果
    * @throws 异常
     */
    ApiResult<String> saveRentContinuedPactRedis(RentPactParam param);
    /**
    * @Title: getRentContinuedPactRedis
    * @Description: TODO(缓存查询合同 )
    * @author GuoXiaoPeng
    * @param redisKey 缓存的key 
    * @return 返回结果
    * @throws 异常
     */
    ApiResult<RentPactResult> getRentContinuedPactRedis(String redisKey);
    /**
     * @Title: saveRentContinuedPact
     * @Description: TODO( 保存续签协议 )
     * @author GuoXiaoPeng
     * @param param 续签协议信息
     * @return 返回结果
     */
     ApiResult<String> saveRentContinuedPact(RentContinuedParam param);
     /**
     * @Title: listRentContinued
     * @Description: TODO(分页查询续签协议管理列表 )
     * @author GuoXiaoPeng
     * @param param 查询条件
     * @return 续签协议列表信息
     * @throws
     */
     ApiResult<PageBean<RentContinuedResult>> listRentContinued(RentContinuedParam param);
     /**
     * @Title: getRentContinued
     * @Description: TODO( 续签协议详情)
     * @author GuoXiaoPeng
     * @param param 续签协议编码
     * @return 续签协议详情信息
     * @throws
     */
     ApiResult<RentContinuedDetailResult> getRentContinued(RentContinuedParam param);
     /**
     * @Title: auditRentContinued
     * @Description: TODO(申请审核续签协议 )
     * @author GuoXiaoPeng
     * @param param 续签协议编码
     * @return 返回结果
     * @throws
     */
     
     ApiResult<Boolean> auditRentContinued(RentContinuedParam param);
     /**
     * @Title: reviewPassRentContinued
     * @Description: TODO(审核通过续签协议 )
     * @author GuoXiaoPeng
     * @param param 续签协议编码
     * @return 返回结果
     * @throws
     */
     ApiResult<Boolean> reviewPassRentContinued(RentContinuedParam param);
     /**
     * @Title: reviewDismissalRentContinued
     * @Description: TODO(审核驳回续签协议 )
     * @author GuoXiaoPeng
     * @param rentPactCode 续签协议编码
     * @return 返回结果
     * @throws
     */
     ApiResult<Boolean> reviewDismissalRentContinued(RentContinuedParam param);
     /**
     * @Title: updateRentContinued
     * @Description: TODO( 修改续签协议)
     * @author GuoXiaoPeng
     * @param param 续签协议信息
     * @return 返回结果
     * @throws
      */
     ApiResult<Boolean> updateRentContinued(RentContinuedParam param);
     
     /**
     * @Title: rempveRentContinued
     * @Description: TODO(删除续签协议 )
     * @author GuoXiaoPeng
     * @param param 续签协议 编码
     * @return 返回结果
     * @throws
     */
     ApiResult<Boolean> removeRentContinued(RentContinuedParam param);
}
