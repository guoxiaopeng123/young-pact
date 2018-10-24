package com.young.pact.rpc.serialize.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.rpc.serialize.SerializeRpc;
import com.young.system.api.service.rpc.serialize.SerializeRpcService;
/**
 * @描述 : 序列化
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月20日 下午8:51:18
 */
@Component("serializeRpc")
public class SerializeRpcImpl implements SerializeRpc{
    private static final Log LOG = LogFactory.getLog(SerializeRpcImpl.class);
    private SerializeRpcService serializeRpcService;
    @Override
    public String queryCodeBySerializeType(String serviceType) {
        ApiResult<String> apiResult = serializeRpcService.queryCodeBySerializeType(serviceType);
        if(CommonEnum.REQUEST_SUCCESS.getCode().equals(apiResult.getCode())){
            return apiResult.getData();
        }else{
            LOG.error(ErrorPactConsts.QUERY_SERIALIZE_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.QUERY_SERIALIZE_ERROR);
        }
    }
    public SerializeRpcService getSerializeRpcService() {
        return serializeRpcService;
    }
    public void setSerializeRpcService(SerializeRpcService serializeRpcService) {
        this.serializeRpcService = serializeRpcService;
    }
}
