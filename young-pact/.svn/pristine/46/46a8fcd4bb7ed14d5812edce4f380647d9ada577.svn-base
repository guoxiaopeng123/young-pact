package com.young.pact.rpc.house.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.young.common.dict.CommonEnum;
import com.young.common.domain.ApiResult;
import com.young.house.api.domain.result.rpc.house.GetHouseRpcResult;
import com.young.house.api.service.rpc.house.HouseRpcService;
import com.young.pact.common.constant.ErrorPactConsts;
import com.young.pact.common.exception.PactManagerExcepion;
import com.young.pact.rpc.house.HouseRpc;

/**
 * @描述 : 房源
 * @author : GuoXiaoPeng
 * @创建时间 : 2018年8月21日 下午8:14:27
 */
@Component("houseRpc")
public class HouseRpcImpl implements HouseRpc {
    private static final Log LOG = LogFactory.getLog(HouseRpcImpl.class);
    private HouseRpcService houseRpcService;
    @Override
    public List<GetHouseRpcResult> getHouseByCodeList(String houseCodes) {
        ApiResult<List<GetHouseRpcResult>> result = houseRpcService.getHouseByCodeList(houseCodes);
        if(null!=result&&CommonEnum.REQUEST_SUCCESS.getCode().equals(result.getCode())){
            return result.getData();
        }else{
            LOG.error(ErrorPactConsts.LIST_HOUSE_ERROR);
            throw new PactManagerExcepion(ErrorPactConsts.LIST_HOUSE_ERROR);
        }
    }
    
    public HouseRpcService getHouseRpcService() {
        return houseRpcService;
    }
    public void setHouseRpcService(HouseRpcService houseRpcService) {
        this.houseRpcService = houseRpcService;
    }
    
}
