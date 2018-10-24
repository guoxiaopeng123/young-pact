package com.young.pact.api.service.rest.rentturn;

import com.young.common.domain.ApiResult;
import com.young.common.page.PageBean;
import com.young.pact.api.domain.param.rest.rentbase.RentPactParam;
import com.young.pact.api.domain.param.rest.rentturn.RentTurnParam;
import com.young.pact.api.domain.param.rest.rentturn.RentTurnProtocolParam;
import com.young.pact.api.domain.result.rest.rentbase.RentPactResult;
import com.young.pact.api.domain.result.rest.rentturn.RentTurnProtocolResult;
import com.young.pact.api.domain.result.rest.rentturn.RentTurnResult;

/**
 * @描述 : 转租协议
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月6日 下午8:54:44
 */
public interface RentTurnService {

    /**
    * @Title: saveRentTurnRedis
    * @Description: TODO(录入转租协议 )
    * @author GuoXiaoPeng
    * @param 转租协议信息
    * @return 转租协议编码
    * @throws 异常
     */
    ApiResult<String> saveRentTurnRedis(RentTurnProtocolParam param);
    /**
    * @Title: getRentTurnRedis
    * @Description: TODO( 根据缓存的key查询转租协议)
    * @author GuoXiaoPeng]
    * @param redisKey 缓存key
    * @return  返回结果
    * @throws 异常
     */
    ApiResult<RentTurnProtocolResult> getRentTurnRedis(String redisKey);
    /**
    * @Title: saveRentTurnPactRedis
    * @Description: TODO(转租第四步录入合同 )
    * @author GuoXiaoPeng
    * @param rentBaseParam 合同信息
    * @return 返回结果
    * @throws 异常
     */
    ApiResult<String> saveRentTurnPactRedis(RentPactParam param);
    /**
    * @Title: getRentTurnPactRedis
    * @Description: TODO( 根据缓存key查询转租协议的合同信息)
    * @author GuoXiaoPeng
    * @param redisKey 缓存key
    * @return 返回结果
    * @throws 异常
     */
    ApiResult<RentPactResult> getRentTurnPactRedis(String redisKey);
    
    
    /**
     * @Title: saveRentTurnPact
     * @Description: TODO( 保存转租协议 )
     * @author GuoXiaoPeng
     * @param param 转租协议信息
     * @return 返回结果
     */
     ApiResult<String> saveRentTurnPact(RentTurnParam param);
     /**
     * @Title: listRentTurn
     * @Description: TODO(分页查询转租协议管理列表 )
     * @author GuoXiaoPeng
     * @param param 查询条件
     * @return 转租协议列表信息
     * @throws
     */
     ApiResult<PageBean<RentTurnResult>> listRentTurn(RentTurnParam param);
     /**
     * @Title: getRentTurn
     * @Description: TODO( 转租协议详情)
     * @author GuoXiaoPeng
     * @param param 转租协议编码
     * @return 转租协议详情信息
     * @throws
     */
     ApiResult<RentTurnProtocolResult> getRentTurn(RentTurnParam param);
     /**
     * @Title: auditRentTurn
     * @Description: TODO(申请审核转租协议 )
     * @author GuoXiaoPeng
     * @param param 转租协议编码
     * @return 返回结果
     * @throws
     */
     
     ApiResult<Boolean> auditRentTurn(RentTurnParam param);
     /**
     * @Title: reviewPassRentTurn
     * @Description: TODO(审核通过转租协议 )
     * @author GuoXiaoPeng
     * @param param 转租协议编码
     * @return 返回结果
     * @throws
     */
     ApiResult<Boolean> reviewPassRentTurn(RentTurnParam param);
     /**
     * @Title: reviewDismissalRentTurn
     * @Description: TODO(审核驳回转租协议 )
     * @author GuoXiaoPeng
     * @param rentPactCode 转租协议编码
     * @return 返回结果
     * @throws
     */
     ApiResult<Boolean> reviewDismissalRentTurn(RentTurnParam param);
     /**
     * @Title: updateRentTurn
     * @Description: TODO( 修改转租协议)
     * @author GuoXiaoPeng
     * @param param 转租协议信息
     * @return 返回结果
     * @throws
      */
     ApiResult<Boolean> updateRentTurn(RentTurnProtocolParam param);
     
     /**
     * @Title: rempveRentTurn
     * @Description: TODO(删除转租协议 )
     * @author GuoXiaoPeng
     * @param param 转租协议 编码
     * @return 返回结果
     * @throws
     */
     ApiResult<Boolean> removeRentTurn(RentTurnParam param);
}
